import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

class FirstManageDrugApproveDetail extends Component {

    buildStateCode(code){
        switch (code){
            case "PASS_APPROVE":
                return "已审核";
            case "REJECTED":
                return "已驳回"
        }
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {detailData} = store.getState().todos;
        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order w1290">
                    <div className="layer-header">
                        <span>商品信息</span>
                        <a  href="javascript:void(0)" onClick={()=>actions.changeFirstManageDrugQualityApproveDetail(false)} className="close"/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>商品编码</p>
                                    <span>{detailData.goodsCode}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>商品名称</p>
                                    <span>{detailData.goodsNm}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>通用名称</p>
                                    <span>{detailData.commonNm}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>规格</p>
                                    <span>{detailData.spec}</span>
                                </div>
                                {(detailData.goodsTypeCode=="CHINESE_MEDICINE_PIECES"||detailData.goodsTypeCode=="DRUG")&&
                                <div className="item">
                                    <p>{detailData.goodsTypeCode=="DRUG"&&<i>*</i>}批准文号</p>
                                    <span>{detailData.approvalNumber}</span>
                                </div>}
                                {(detailData.goodsTypeCode=="CHINESE_MEDICINE_PIECES"||detailData.goodsTypeCode=="DRUG")&&
                                <div className="item">
                                    <p><i>*</i>剂型</p>
                                    <span>{detailData.dosageForm}</span>
                                </div>}
                                <div className="item">
                                    <p><i>*</i>单位</p>
                                    <span>{detailData.unit}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>生产厂商</p>
                                    <span>{detailData.produceManufacturer}</span>
                                </div>
                                {detailData.goodsTypeCode=="CHINESE_MEDICINE_PIECES"&&
                                <div className="item">
                                    <p>产地</p>
                                    <span>{detailData.productionPlace}</span>
                                </div>}

                                {(detailData.goodsTypeCode=="CHINESE_MEDICINE_PIECES"||detailData.goodsTypeCode=="DRUG")&&
                                <div className="item">
                                    <p>{detailData.goodsTypeCode=="DRUG"&&<i>*</i>}批准文号期限</p>
                                    <span>{detailData.approvalNumberTermString}</span>
                                </div>}
                                <div className="item">
                                    <p>毒理</p>
                                    <span>{detailData.toxicologyCode}</span>
                                </div>
                                <div className="item">
                                    <p>储存条件</p>
                                    <span>{detailData.storageCondition}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>零售价</p>
                                    <span>{detailData.retailPrice}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>会员价</p>
                                    <span>{detailData.memberPrice}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">审核人信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>申请人</p>
                                        <span>{detailData.applyManName}</span>
                                    </div>
                                    <div className="item">
                                        <p>申请时间</p>
                                        <span>{detailData.createDateStr}</span>
                                    </div>
                                    <div className="item">
                                        <p>提交意见</p>
                                        <span>{detailData.submitAdvice?detailData.submitAdvice:"无"}</span>
                                    </div>
                                    <div className="item item-note">
                                        <p>申请备注</p>
                                        <span>{detailData.applyRemark?detailData.applyRemark:"无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    {detailData.purchaseApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>采购审核人</p>
                                        <span>{detailData.purchaseApproveManName}</span>
                                    </div>}
                                    {detailData.purchaseApproveStateCode&&
                                    <div className="item">
                                        <p>采购审核时间</p>
                                        <span>{detailData.purchaseApproveTimeStr?detailData.purchaseApproveTimeStr:"无"}</span>
                                    </div>}
                                    {detailData.purchaseApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>采购审核状态</p>
                                        <span>{this.buildStateCode(detailData.purchaseApproveStateCode)}</span>
                                    </div>}
                                    {detailData.purchaseApproveStateCode&&
                                    <div className="item item-note">
                                        <p>采购审核备注</p>
                                        <span>{detailData.purchaseApproveRemark?detailData.purchaseApproveRemark:"无"}</span>
                                    </div>}
                                </div>
                                <div className="item-line-box">
                                    {detailData.qualityApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>质量审核人</p>
                                        <span>{detailData.qualityApproveManName}</span>
                                    </div>}
                                    {detailData.qualityApproveStateCode&&
                                    <div className="item">
                                        <p>质量审核时间</p>
                                        <span>{detailData.qualityApproveTimeStr?detailData.qualityApproveTimeStr:"无"}</span>
                                    </div>}
                                    {detailData.qualityApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>质量审核状态</p>
                                        <span>{this.buildStateCode(detailData.qualityApproveStateCode)}</span>
                                    </div>}
                                    {detailData.qualityApproveStateCode&&
                                    <div className="item item-note">
                                        <p>质量审核备注</p>
                                        <span>{detailData.qualityApproveRemark?detailData.qualityApproveRemark:"无"}</span>
                                    </div>}
                                </div>
                                <div className="item-line-box">
                                    {detailData.enterpriseApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>企业审核人</p>
                                        <span>{detailData.enterpriseApproveManName}</span>
                                    </div>}
                                    {detailData.enterpriseApproveStateCode&&
                                    <div className="item">
                                        <p>企业审核时间</p>
                                        <span>{detailData.enterpriseApproveTimeStr?detailData.enterpriseApproveTimeStr:"无"}</span>
                                    </div>}
                                    {detailData.enterpriseApproveStateCode&&
                                    <div className="item">
                                        <p><i>*</i>企业审核状态</p>
                                        <span>{this.buildStateCode(detailData.enterpriseApproveStateCode)}</span>
                                    </div>}
                                    {detailData.enterpriseApproveStateCode&&
                                    <div className="item item-note">
                                        <p>企业审核备注</p>
                                        <span>{detailData.enterpriseApproveRemark?detailData.enterpriseApproveRemark:"无"}</span>
                                    </div>}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


FirstManageDrugApproveDetail.contextTypes = {
    store: React.PropTypes.object
};

export default FirstManageDrugApproveDetail