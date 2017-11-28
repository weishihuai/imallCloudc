
package com.imall.iportal.core.shop.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.shop.entity.DrugReleaseNotice;
import com.imall.iportal.core.shop.repository.DrugReleaseNoticeRepositoryCustom;
import com.imall.iportal.core.shop.vo.ReleaseNoticeSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class DrugReleaseNoticeRepositoryImpl implements DrugReleaseNoticeRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<DrugReleaseNotice> query(Pageable pageable, ReleaseNoticeSearchParam releaseNoticeSearchParam, Long shopId) throws ParseException {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();
        sql.append(" from t_shp_drug_release_notice where 1=1 AND SHOP_ID=:shopId");
        paramMap.put(DrugReleaseNotice.SHOP_ID, shopId);

        //复核人名称
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getApproveManName())) {
            sql.append(" AND APPROVE_MAN_ID IN( SELECT ID FROM t_pt_sys_user WHERE REAL_NAME LIKE:realName )  ");
            paramMap.put(SysUser.REAL_NAME, "%" + releaseNoticeSearchParam.getApproveManName() + "%");
        }

        //制单人名称
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getDocMakerNm())) {
            sql.append(" AND DOC_MAKER_NM LIKE:docMakerNm ");
            paramMap.put(DrugReleaseNotice.DOC_MAKER_NM, "%" + releaseNoticeSearchParam.getDocMakerNm() + "%");
        }

        //停售单号
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getReleaseNum())) {
            sql.append(" AND RELEASE_NUM =:releaseNum ");
            paramMap.put(DrugReleaseNotice.RELEASE_NUM, releaseNoticeSearchParam.getReleaseNum());
        }

        //解停时间范围
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getFromReleaseDateStr())) {
            sql.append(" and RELEASE_DATE >=:startTimeString ");
            paramMap.put("startTimeString", DateTimeUtils.convertStringToDate(releaseNoticeSearchParam.getFromReleaseDateStr()));
        }
        //解停时间范围
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getToReleaseDateStr())) {
            sql.append(" and RELEASE_DATE <=:endTimeString ");
            paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(releaseNoticeSearchParam.getToReleaseDateStr()));
        }


        //制单时间范围
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getFromDocMakeTimeStr())) {
            sql.append(" and DOC_MAKE_TIME >=:beginTimeStr ");
            paramMap.put("beginTimeStr", DateTimeUtils.convertStringToDate(releaseNoticeSearchParam.getFromDocMakeTimeStr()));
        }
        //制单时间范围
        if (StringUtils.isNotBlank(releaseNoticeSearchParam.getToDocMakeTimeStr())) {
            sql.append(" and DOC_MAKE_TIME <=:endTimeStr ");
            paramMap.put("endTimeStr", DateTimeUtils.getDayEndTime(releaseNoticeSearchParam.getToDocMakeTimeStr()));
        }


        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select *" + sqlStr, DrugReleaseNotice.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<DrugReleaseNotice>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
