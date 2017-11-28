package com.imall.iportal.core.main.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.repository.SysUserRepositoryCustom;
import com.imall.iportal.core.main.vo.SysUserVo;
import com.imall.iportal.core.main.vo.UserParamsVo;
import com.imall.iportal.core.platform.entity.Shop;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygw on 2015/12/29.
 */
public class SysUserRepositoryImpl implements SysUserRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<SysUser> query(Pageable pageable, UserParamsVo userParamsVo) throws ParseException {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        //机构
        sql.append(" from t_pt_sys_user t  where 1=1 and t.ORG_ID =:orgId and IS_DEFAULT_ADMIN='N'");
        paramMap.put(SysUser.ORG_ID, userParamsVo.getOrgId());

        //姓名|用户名称
        if (StringUtils.isNotEmpty(userParamsVo.getName())) {
            sql.append("and (t.REAL_NAME like:realName OR t.USER_NAME like:userName ) ");
            paramMap.put(SysUser.REAL_NAME, "%" + userParamsVo.getName() + "%");
            paramMap.put(SysUser.USER_NAME, "%" + userParamsVo.getName() + "%");

        }
        //状态
        if (StringUtils.isNotEmpty(userParamsVo.getIsEnable())) {
            sql.append("and t.IS_ENABLE=:isEnable ");
            paramMap.put(SysUser.IS_ENABLE, BoolCodeEnum.fromCode(userParamsVo.getIsEnable()).toCode());
        }

        //创建时间范围
        if (StringUtils.isNotEmpty(userParamsVo.getStartTimeString())) {
            sql.append(" and t.CREATE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(userParamsVo.getStartTimeString()));
        }
        //创建时间范围
        if (StringUtils.isNotEmpty(userParamsVo.getEndTimeString())) {
            sql.append(" and t.CREATE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(userParamsVo.getEndTimeString()));
        }

        //手机
        if (StringUtils.isNotEmpty(userParamsVo.getMobile())) {
            sql.append("and t.MOBILE like:mobile ");
            paramMap.put(SysUser.MOBILE, "%" + userParamsVo.getMobile() + "%");
        }
        //门店名称
        if (StringUtils.isNotEmpty(userParamsVo.getShopNm())) {
            sql.append("and t.SHOP_ID in( select s.id from t_ptfm_shop s where s.ENT_NM like:entNm) ");
            paramMap.put(Shop.ENT_NM, "%" + userParamsVo.getShopNm() + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, SysUser.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<SysUser>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());

    }

    @Override
    public List<SysUserVo> findVoByServiceOrgId(Long serviceOrgId) {
        String sql = "SELECT u.* FROM `t_pt_sys_user` u WHERE u.`ID` IN(SELECT uoj.`USER_ID` FROM `t_pt_sys_user_org_job` uoj WHERE uoj.`ORG_ID`= :orgId)";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(SysUserVo.ORG_ID, serviceOrgId);
        Query query = entityManager.createNativeQuery(sql);
        CommonUtil.formatQueryParameter(query, paramMap);

        SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
        nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> mapList = nativeQuery.list();

        List<SysUserVo> sysUserVoList = new ArrayList<>();
        for (Map<String, Object> objectMap : mapList) {
            sysUserVoList.add(CommonUtil.copyFromDbMap(objectMap,  new SysUserVo()));
        }
        return sysUserVoList;
    }

    @Override
    public List<SysUser> findByServiceOrgIdNotDeleted(Long serviceOrgId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        StringBuilder sqlBuilder = new StringBuilder();
        if(serviceOrgId!=null){
            paramMap.put(SysUserVo.ORG_ID, serviceOrgId);
            sqlBuilder.append(" AND uoj.`ORG_ID`= :orgId");
        }
        Query query = entityManager.createNativeQuery("SELECT u.* FROM `t_pt_sys_user` u WHERE u.`IS_DELETED` ='N' AND u.`ID` IN(SELECT uoj.`USER_ID` FROM `t_pt_sys_user_org_job` uoj WHERE 1=1 " + sqlBuilder.toString() + ")", SysUser.class);
        CommonUtil.formatQueryParameter(query, paramMap);
        return query.getResultList();
    }

}
