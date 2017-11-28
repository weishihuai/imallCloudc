package com.imall.commons.base.validate;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Aop拦截方法，验证方法参数合法性
 */
@Component
@Aspect
public class ServiceMethodParamValidate {
    private Logger logger = LogManager.getLogger(getClass());


    @SuppressWarnings("unchecked")
    @Before(value="execution(@com.imall.commons.base.validate.ValidateMark public * com.imall.*.core.rpc.proxy.impl.*.*(..))")
    public void validate(JoinPoint joinPoint) throws IOException, IllegalAccessException {

        Object[] values = joinPoint.getArgs();
        Signature sign = joinPoint.getSignature();

        MethodSignature msign = (MethodSignature) sign;
        Method method = msign.getMethod();
        Annotation[][] an =  method.getParameterAnnotations();

        if(an.length>0){
            for(int i=0;i<an.length;i++){
                for(int j=0;j<an[i].length;j++){
                    MethodParamValidate paramValidate = (MethodParamValidate) an[i][j];

                    String classNm = joinPoint.getTarget().getClass().getSimpleName();
                    String methodNm = method.getName();
                    String path = classNm+"."+methodNm+"."+ paramValidate.name();

                    validate(
                            values[i],
                            paramValidate.name(),
                            paramValidate.regex(),
                            paramValidate.min(),
                            paramValidate.max(),
                            paramValidate.required(),
                            paramValidate.errorMsg(),
                            paramValidate.clazz(),
                            path);
                }
            }
        }
    }


    private void validate(Object paramValue,String name,String regex, long min,long max ,boolean required, String errorMsg,Class clazz,String path) throws IllegalAccessException {

        if(paramValue!=null){

            //基础类型Long，Integer，Double，String
            if(paramValue instanceof Long || paramValue instanceof Integer ||paramValue instanceof Double ||paramValue instanceof String){
                String value = String.valueOf(paramValue);

                if(paramValue instanceof String){
                    // 最小长度
                    if (min != -1 && value.length() < min) {
                        throw new RuntimeException("".equals(errorMsg) ? ("" + path + "：最小长度" + min + ",当前" + value.length()) : errorMsg);
                    }
                    // 最大长度
                    if (max != -1 && value.length() > max) {
                        throw new RuntimeException("".equals(errorMsg) ? ("" + path + "：最大长度" + max + ",当前" + value.length()) : errorMsg);
                    }
                }

                if(!"".equals(regex)){
                    //正则验证参数
                    if(!value.matches(regex)){
                        logger.warn("参数格式错误"+path+"="+value);
                        throw new RuntimeException("".equals(errorMsg)?("参数格式错误"+path+"="+value):errorMsg);
                    }
                }
            }

            //数组
            if(paramValue instanceof Array ){
                Object[] os = (Object[]) paramValue;

                // 最小数量
                if (min != -1 && os.length < min) {
                    throw new RuntimeException("".equals(errorMsg) ? (path + "：最小数量限制" + min) : errorMsg);
                }
                // 最大数量
                if (max != -1 && os.length > max) {
                    throw new RuntimeException("".equals(errorMsg) ? (path + "：最大数量限制" + max) : errorMsg);
                }

                // 数组里是对象集合，则需要遍历验证
                if (os.length > 0 && os[0] instanceof IParamObject) {
                    for (Object o : os) {
                        validateObject(o,path);
                    }
                }

            }

            // 列表
            if(paramValue instanceof ArrayList){
                List os = (List)paramValue;

                // 最小数量
                if (min != -1 && os.size() < min) {
                    throw new RuntimeException("".equals(errorMsg) ? (path + "：最小数量限制" + min) : errorMsg);
                }
                // 最大数量
                if (max != -1 && os.size() > max) {
                    throw new RuntimeException("".equals(errorMsg) ? (path + "：最大数量限制" + max) : errorMsg);
                }

                // 列表里是对象集合，则需要遍历验证
                if (os.size() > 0 &&os.get(0) instanceof IParamObject) {
                    for (Object o : os) {
                        validateObject(o,path);
                    }
                }
            }


            // 对象
            if(paramValue instanceof IParamObject){

                Field[] fieldArray = clazz.getDeclaredFields();
                for(Field field : fieldArray){
                    field.setAccessible(true);
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    if(annotations.length==0){
                        continue;
                    }

                    MethodParamValidate paramValidate = (MethodParamValidate) annotations[0];
                    validate(
                            field.get(paramValue),
                            paramValidate.name(),
                            paramValidate.regex(),
                            paramValidate.min(),
                            paramValidate.max(),
                            paramValidate.required(),
                            paramValidate.errorMsg(),
                            paramValidate.clazz(),
                            path+"."+paramValidate.name());
                }
            }


        }else if(required){
            throw new RuntimeException("".equals(errorMsg) ? ("缺少必要参数：" + path) : errorMsg);
        }
    }

    private void validateObject(Object o,String path) throws IllegalAccessException {

        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getDeclaredAnnotations();

            if (annotations.length == 0) {
                continue;
            }

            MethodParamValidate paramValidate = (MethodParamValidate) annotations[0];
            validate(
                    field.get(o),
                    paramValidate.name(),
                    paramValidate.regex(),
                    paramValidate.min(),
                    paramValidate.max(),
                    paramValidate.required(),
                    paramValidate.errorMsg(),
                    paramValidate.clazz(),
                    path + "." + paramValidate.name());
        }
    }
}

