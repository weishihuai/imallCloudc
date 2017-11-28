package com.imall.iportal.core.main.log;

import com.imall.commons.base.entity.BaseEntity;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.base.util.JsonBinder;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysLogData;
import com.imall.iportal.core.main.log.annotation.LogTableName;
import com.imall.iportal.core.main.log.annotation.LogWriter;
import com.imall.iportal.core.main.log.dicts.DataOperationTypeCodeEnum;
import com.imall.iportal.core.main.vo.SysLogVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: ygw
 * Date: 17-8-29
 */

@Aspect
@Service
public class LogAop {


    private ExecutorService executor = Executors.newCachedThreadPool();


    //Controller  LogWriter 层切点
    @Pointcut(value="@annotation(com.imall.iportal.core.main.log.annotation.LogWriter)")
    public  void logWriterAspect() {
    }

    @Before(value = "logWriterAspect())")
    public  void doBeforeLogWriter(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogWriter logWriter = method.getAnnotation(LogWriter.class);

        SysLogVo logVo = new SysLogVo();
        logVo.setLogTime(new Date());
        logVo.setLogTypeCode(logWriter.type().toCode());
        logVo.setLogInnerCode("log_" + (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(logVo.getLogTime()));
        logVo.setLogTitle(logWriter.title());
        logVo.setLogDataList(new ArrayList<SysLogData>());
        logVo.setNav(logWriter.nav());
        LoggerUtils.setSysLogVo(logVo);
    }

    @AfterReturning(value = "logWriterAspect()")
    public  void doAfterLogWriter(JoinPoint joinPoint) {
        SysLogVo logVo = LoggerUtils.getSysLogVo();
        SysLogVo tempLogVo = new SysLogVo();
        tempLogVo.setLogInnerCode(logVo.getLogInnerCode());
        tempLogVo.setLogTime(logVo.getLogTime());
        tempLogVo.setLogTypeCode(logVo.getLogTypeCode());
        tempLogVo.setNav(logVo.getNav());
        tempLogVo.setLogTitle(logVo.getLogTitle());
        tempLogVo.setLogContentStr(logVo.getLogContentStr());
        tempLogVo.setLogDataList(new ArrayList<SysLogData>(logVo.getLogDataList()));
        logVo.setLogDataList(null); //解决收集数据的死循环

        //异步执行保存到数据库 (备注：如果日后如果需要做成独立的远程日志系统，可以使用Apache-Mina通信，把日志转成json发送到远程日志系统)
        //long stateTime = System.currentTimeMillis();
        //System.out.println("stateTime=" + stateTime);
        final BlockingQueue<SysLogVo> queue = new LinkedBlockingQueue<SysLogVo>();
        if(queue.add(tempLogVo)){
            FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {
                @Override
                public Boolean call(){
                    try {
                        //System.out.println("running-stateTime=" + System.currentTimeMillis());
                        ServiceManager.sysLogService.saveLog(queue.take());
                        //System.out.println("running-endTime=" + System.currentTimeMillis());
                    } catch (Throwable e) {
                       return false;
                    }
                    return true;
                }
            });
            executor.submit(futureTask);
            //System.out.println("futureTask.isDone=" + futureTask.isDone());
            //long endTime = System.currentTimeMillis();
            //System.out.println("endTime=" + endTime);
            //System.out.println("time=" + (stateTime-endTime));
            //executor.shutdown();
        }
    }

    //拦截保存
    @Before("execution(public * com.imall.iportal.core.*..repository.*.save(..)) || execution(public * org.springframework.data.repository.CrudRepository.save(..))")
    public void doBeforeSaveAspect(JoinPoint joinPoint) {
        if(LoggerUtils.isInit()){
            Object[] args = joinPoint.getArgs();
            String className = args[0].getClass().getName();

            //TODO 先去掉没有LogTableName注解的domain
            LogTableName tableNameAnnotation = (LogTableName)args[0].getClass().getAnnotation(LogTableName.class);
            if(tableNameAnnotation==null){
                return;
            }

            BaseEntity baseEntity = (BaseEntity)args[0];

            List<SysLogData> logDataList = LoggerUtils.getSysLogVo().getLogDataList();
            SysLogData logData = new SysLogData();
            logData.setTableKey(className);
            logData.setObjectId((Long) baseEntity.getId());
            if(baseEntity.getId()==null){
                logData.setDataOperationTypeCode(DataOperationTypeCodeEnum.SAVE.toCode());
                logData.setBeforeOperationDataStr(null);
            }
            else {
                logData.setDataOperationTypeCode(DataOperationTypeCodeEnum.UPDATE.toCode());
                logData.setBeforeOperationDataStr(JsonBinder.buildNormalBinder().toJson(((CrudRepository)joinPoint.getTarget()).findOne(baseEntity.getId())));
            }
            logDataList.add(logData);
        }
    }

    @AfterReturning("execution(public * com.imall.iportal.core.*..repository.*.save(..)) || execution(public * org.springframework.data.repository.CrudRepository.save(..))")
    public void doAfterSaveAspect(JoinPoint joinPoint) {
        if(LoggerUtils.isInit()) {
            Object[] args = joinPoint.getArgs();

            //TODO 先去掉没有LogTableName注解的domain
            LogTableName tableNameAnnotation = (LogTableName)args[0].getClass().getAnnotation(LogTableName.class);
            if(tableNameAnnotation==null){
                return;
            }

            List<SysLogData> logDataList = LoggerUtils.getSysLogVo().getLogDataList();
            SysLogData logData = logDataList.get(logDataList.size() - 1);
            logData.setAfterOperationDataStr(JsonBinder.buildNormalBinder().toJson(args[0]));
        }
    }

    //拦截删除
    @Before("execution(public * com.imall.iportal.core.*..repository.*.delete(..)) || execution(public * org.springframework.data.repository.CrudRepository.delete(..))")
    public void doBeforeDeleteAspect(JoinPoint joinPoint) {
        if(LoggerUtils.isInit()){
            Object[] args = joinPoint.getArgs();
            String className = args[0].getClass().getName();
            String simpleName = args[0].getClass().getSimpleName();

            //TODO 先去掉没有LogTableName注解的domain
            LogTableName tableNameAnnotation = (LogTableName)args[0].getClass().getAnnotation(LogTableName.class);
            if(tableNameAnnotation==null){
                return;
            }

            BaseEntity baseEntity = (BaseEntity)args[0];

            List<SysLogData> logDataList = LoggerUtils.getSysLogVo().getLogDataList();
            SysLogData logData = new SysLogData();
            logData.setTableKey(className);
            logData.setObjectId((Long) baseEntity.getId());
            logData.setDataOperationTypeCode(DataOperationTypeCodeEnum.DELETE.toCode());
            logData.setBeforeOperationDataStr(JsonBinder.buildNormalBinder().toJson(((CrudRepository) joinPoint.getTarget()).findOne(baseEntity.getId())));
            logDataList.add(logData);
        }
    }

    @AfterReturning("execution(public * com.imall.iportal.core.*..repository.*.delete(..)) || execution(public * org.springframework.data.repository.CrudRepository.delete(..))")
    public void doAfterDeleteAspect(JoinPoint joinPoint) {
//        if(LoggerUtils.isInit()) {
//            Object[] args = joinPoint.getArgs();
//
//            List<SysLogData> logDataList = LoggerUtils.getSysLogVo().getLogDataList();
//            SysLogData logData = logDataList.get(logDataList.size() - 1);
//            logData.setAfterOperationDataStr(JsonBinder.buildNormalBinder().toJson(args[0]));
//        }
    }

}
