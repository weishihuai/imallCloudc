
package com.imall.iportal.core.platform.repository.impl;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.platform.entity.*;
import com.imall.iportal.core.platform.repository.GoodsDocRepositoryCustom;
import com.imall.iportal.core.platform.vo.GoodsDocListSearchParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * (JPA持久化层)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
public class GoodsDocRepositoryImpl implements GoodsDocRepositoryCustom {

    @Resource
    private EntityManager entityManager;

    @Override
    public Page<GoodsDoc> query(Pageable pageable, GoodsDocListSearchParam goodsDocListSearchParam) {

        StringBuffer sql = new StringBuffer();
        Map<String, Object> paramMap = new HashMap<>();

        if (StringUtils.isBlank(goodsDocListSearchParam.getDocApproveNumber())) {
            sql.append(" from t_ptfm_goods_doc a  where 1=1 AND IS_DELETE='N'  ");

            if(StringUtils.isNotBlank(goodsDocListSearchParam.getIsGoodsAdd())&& BoolCodeEnum.fromCode(goodsDocListSearchParam.getIsGoodsAdd())==BoolCodeEnum.YES){
                sql.append(" AND APPROVE_STATE_CODE ='PASS_APPROVE' ");
            }else{
                if (StringUtils.isNotBlank(goodsDocListSearchParam.getApproveStateCode())) {
                    sql.append(" AND APPROVE_STATE_CODE =:approveStateCode ");
                    paramMap.put(GoodsDoc.APPROVE_STATE_CODE, goodsDocListSearchParam.getApproveStateCode());
                }
            }

            if(goodsDocListSearchParam.getGoodsCategoryId()!=null){
                sql.append(" AND GOODS_CATEGORY_ID =:goodsCategoryId ");
                paramMap.put(GoodsDoc.GOODS_CATEGORY_ID, goodsDocListSearchParam.getGoodsCategoryId());

            }

            //创建时间范围
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getFromDateString())) {
                sql.append(" and CREATE_DATE >=:startTimeString ");
                paramMap.put("startTimeString", DateTimeUtils.getDayStartTime(goodsDocListSearchParam.getFromDateString()));
            }
            //创建时间范围
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getToDateString())) {
                sql.append(" and CREATE_DATE <=:endTimeString ");
                paramMap.put("endTimeString", DateTimeUtils.getDayEndTime(goodsDocListSearchParam.getToDateString()));
            }
            //商品名称
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getKeyWords())) {
                sql.append(" AND ( GOODS_NM like:goodsNm");
                paramMap.put(GoodsDoc.GOODS_NM, "%" + goodsDocListSearchParam.getKeyWords() + "%");
            }
            //商品编码
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getKeyWords())) {
                sql.append(" or GOODS_CODE like:goodsCode");
                paramMap.put(GoodsDoc.GOODS_CODE, "%" + goodsDocListSearchParam.getKeyWords() + "%");
            }
            //通用名称
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getKeyWords())) {
                sql.append(" or COMMON_NM like:commonNm");
                paramMap.put(GoodsDoc.COMMON_NM, "%" + goodsDocListSearchParam.getKeyWords() + "%");
            }
            //拼音
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getKeyWords())) {
                sql.append(" or PINYIN like:pinyin ) ");
                paramMap.put(GoodsDoc.PINYIN, "%" + goodsDocListSearchParam.getKeyWords() + "%");
            }

            //以下两个参数为  商品新增的时候 选择商品档案列表
            if (StringUtils.isNotBlank(goodsDocListSearchParam.getDocProduceManufacturer())) {
                sql.append(" AND PRODUCE_MANUFACTURER like:produceManufacturer  ");
                paramMap.put(GoodsDoc.PRODUCE_MANUFACTURER, "%" + goodsDocListSearchParam.getDocProduceManufacturer() + "%");
            }

            if (StringUtils.isNotBlank(goodsDocListSearchParam.getDocGoodsNm())) {
                sql.append(" AND GOODS_NM like:goodsNm  ");
                paramMap.put(GoodsDoc.GOODS_NM, "%" + goodsDocListSearchParam.getDocGoodsNm() + "%");
            }
        }else{
            sql.append("  FROM t_ptfm_goods_doc a WHERE a.APPROVE_STATE_CODE='PASS_APPROVE' ");

            if (StringUtils.isNotBlank(goodsDocListSearchParam.getDocProduceManufacturer())) {
                sql.append(" AND a.PRODUCE_MANUFACTURER like:produceManufacturer  ");
                paramMap.put(GoodsDoc.PRODUCE_MANUFACTURER, "%" + goodsDocListSearchParam.getDocProduceManufacturer() + "%");
            }

            if (StringUtils.isNotBlank(goodsDocListSearchParam.getDocGoodsNm())) {
                sql.append(" AND a.GOODS_NM like:goodsNm  ");
                paramMap.put(GoodsDoc.GOODS_NM, "%" + goodsDocListSearchParam.getDocGoodsNm() + "%");
            }

            sql.append(" AND (\n" +
             "a.ID IN (SELECT b.GOODS_DOC_ID FROM  t_ptfm_goods_doc_chinese_medicine_pieces b WHERE b.`APPROVAL_NUMBER` LIKE :approvalNumber)\n" +
             "OR a.ID IN (SELECT c.GOODS_DOC_ID FROM  t_ptfm_goods_doc_cosmetic c WHERE c.`APPROVAL_NUMBER` LIKE :approvalNumber)\n" +
             "OR a.ID IN (SELECT d.GOODS_DOC_ID FROM  t_ptfm_goods_doc_daily_necessities d WHERE d.`APPROVAL_NUMBER` LIKE :approvalNumber)\n" +
             "OR a.ID IN (SELECT e.GOODS_DOC_ID FROM  t_ptfm_goods_doc_drug e WHERE e.`APPROVAL_NUMBER` LIKE :approvalNumber)\n" +
             "OR a.ID IN (SELECT f.GOODS_DOC_ID FROM  t_ptfm_goods_doc_other f WHERE f.`APPROVAL_NUMBER` LIKE :approvalNumber)\n" +
             " ) ");
            paramMap.put(GoodsDocChineseMedicinePieces.APPROVAL_NUMBER, "%" + goodsDocListSearchParam.getDocApproveNumber() + "%");
            paramMap.put(GoodsDocCosmetic.APPROVAL_NUMBER, "%" + goodsDocListSearchParam.getDocApproveNumber() + "%");
            paramMap.put(GoodsDocDailyNecessities.APPROVAL_NUMBER, "%" + goodsDocListSearchParam.getDocApproveNumber() + "%");
            paramMap.put(GoodsDocDrug.APPROVAL_NUMBER, "%" + goodsDocListSearchParam.getDocApproveNumber() + "%");
            paramMap.put(GoodsDocOther.APPROVAL_NUMBER, "%" + goodsDocListSearchParam.getDocApproveNumber() + "%");

        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        String sqlStr = sql.toString();
        Query resultQ = entityManager.createNativeQuery("select a.*" + sqlStr, GoodsDoc.class)
                .setFirstResult(firstIdx)
                .setMaxResults(pageSize);
        CommonUtil.formatQueryParameter(resultQ, paramMap);
        Query resultTotal = entityManager.createNativeQuery("SELECT COUNT(*) " + sqlStr);
        CommonUtil.formatQueryParameter(resultTotal, paramMap);
        return new PageImpl<GoodsDoc>(resultQ.getResultList(), pageable, ((BigInteger) resultTotal.getSingleResult()).longValue());
    }
}
