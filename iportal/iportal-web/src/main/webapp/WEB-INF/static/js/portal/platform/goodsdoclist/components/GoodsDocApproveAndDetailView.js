import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

class GoodsDocApproveAndDetailView extends Component {

  buildYesOrNo(code){
      return code=="Y"?"是":"否";
  }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {detailData,isApproveOrDetail} = store.getState().todos;
        const otherFileList = detailData.otherFileVoList||[];
        const pictFileList = detailData.pictFileVoList||[];

        const {IS_DRUG,IS_OTHER,IS_CHINESE_MEDICINE_PIECES,IS_FOOD_HEALTH,IS_DAILY_NECESSITIES,IS_MEDICAL_INSTRUMENTS,IS_COSMETIC} = store.getState().todos;
        return (
            <div className="layer">
                <div className="layer-box layer-info w960">
                        <div className="layer-header">
                            <span>{isApproveOrDetail?"商品档案详情":"审核商品档案"}</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.changeGoodsDocApproveAndDetailState(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">基础信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p><i>*</i>商品编码</p>
                                            <span>{detailData.goodsCode}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>商品名称</p>
                                            <span>{detailData.goodsNm}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>商品类型</p>
                                            <span>{detailData.goodsTypeName}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>商品分类</p>
                                            <span>{detailData.pathName}</span>
                                        </div>
                                    </div>
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p><i>*</i>生产厂商</p>
                                            <span>{detailData.produceManufacturer}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>通用名称</p>
                                            <span>{detailData.commonNm}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>规格</p>
                                            <span>{detailData.spec}</span>
                                        </div>
                                        <div className="item">
                                            <p><i>*</i>单位</p>
                                            <span>{detailData.unit}</span>
                                        </div>
                                    </div>
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>品牌</p>
                                            <span>{detailData.brandNm}</span>
                                        </div>
                                        <div className="item">
                                            <p>条形码</p>
                                            <span>{detailData.barCode}</span>
                                        </div>
                                        <div className="item">
                                            <p>毒理代码</p>
                                            <span>{detailData.toxicologyName}</span>
                                        </div>
                                        <div className="item">
                                            <p>储存条件</p>
                                            <span>{detailData.storageConditionName}</span>
                                        </div>
                                    </div>
                                    {(IS_OTHER||IS_DRUG ||IS_COSMETIC||IS_CHINESE_MEDICINE_PIECES||IS_DAILY_NECESSITIES) &&
                                    <div className="item">
                                        <p>{(IS_OTHER||IS_DRUG ||IS_COSMETIC||IS_DAILY_NECESSITIES) &&<i>*</i>}批准文号</p>
                                        <span>{detailData.approvalNumber}</span>
                                    </div>}

                                    {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                    <div className="item">
                                        <p>{IS_DRUG&&<i>*</i>}批准文号期限</p>
                                            <div className="form-group float-left w140">
                                                <span>{detailData.approvalNumberTermString}</span>
                                            </div>
                                    </div>}

                                    {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                    <div className="item">
                                        <p>是否进口商品</p>
                                        <span>{this.buildYesOrNo(detailData.isImportGoods)}</span>
                                    </div>
                                    }

                                    {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                    <div className="item">
                                        <p>是否中药保护</p>
                                        <span>{this.buildYesOrNo(detailData.isChineseMedicineProtect)}</span>
                                    </div>
                                    }


                                    {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                    <div className="item">
                                        <p>批准日期</p>
                                        <div className="sel-date">
                                            <div className="form-group float-left w140">
                                                <span>{detailData.approveDateString}</span>
                                            </div>
                                        </div>
                                    </div>}

                                    {(IS_DRUG ||IS_CHINESE_MEDICINE_PIECES)&&
                                    <div className="item">
                                        <p><i>*</i>剂型</p>
                                        <span>{detailData.dosageFormName}</span>
                                    </div>}

                                    {IS_CHINESE_MEDICINE_PIECES &&
                                    <div className="item">
                                        <p><i>*</i>产地</p>
                                        <span>{detailData.productionPlace}</span>
                                    </div>}

                                    {IS_CHINESE_MEDICINE_PIECES &&
                                    <div className="item">
                                        <p>功效</p>
                                        <span>{detailData.effect}</span>
                                    </div>}

                                    {IS_MEDICAL_INSTRUMENTS &&
                                    <div className="item">
                                        <p><i>*</i>注册号</p>
                                        <span>{detailData.regNum}</span>
                                    </div>}

                                    {IS_MEDICAL_INSTRUMENTS &&
                                    <div className="item">
                                        <p><i>*</i>注册登记表号</p>
                                        <span>{detailData.regRegistrationFormNum}</span>
                                    </div>}

                                    {(IS_OTHER||IS_DAILY_NECESSITIES||IS_COSMETIC||IS_MEDICAL_INSTRUMENTS) &&
                                    <div className="item">
                                        <p>厂家地址</p>
                                        <span>{detailData.manufacturerAddr}</span>
                                    </div>}

                                    {IS_MEDICAL_INSTRUMENTS &&
                                    <div className="item">
                                        <p>适用范围</p>
                                        <span>{detailData.applyRange}</span>
                                    </div>}

                                    {IS_MEDICAL_INSTRUMENTS &&
                                    <div className="item">
                                        <p>产品标准号码</p>
                                        <span>{detailData.productStandardNum}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p><i>*</i>食品卫生许可证号</p>
                                        <span>{detailData.foodHygieneLicenceNum}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p><i>*</i>生产日期</p>
                                        <div className="sel-date">
                                            <div className="form-group float-left w140">
                                                <span>{detailData.productionDateString}</span>
                                            </div>
                                        </div>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p><i>*</i>保质期</p>
                                        <div className="sel-date">
                                            <div className="form-group float-left w140">
                                                <span>{detailData.expirationDateString}</span>
                                            </div>
                                        </div>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p><i>*</i>保健功能</p>
                                        <span>{detailData.healthCareFunc}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>适宜人群</p>
                                        <span>{detailData.appropriateCrowd}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>不适宜人群</p>
                                        <span>{detailData.notAppropriateCrowd}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>食用方法及用量</p>
                                        <span>{detailData.edibleMethodAndDosage}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>贮藏方法</p>
                                        <span>{detailData.storageMethod}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>执行标准</p>
                                        <span>{detailData.execStandard}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>功效成分</p>
                                        <span>{detailData.effectComposition}</span>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p>注意事项</p>
                                        <span>{detailData.notice}</span>
                                    </div>}
                                </div>
                            </div>
                            {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES) &&
                            <div className="md-box">
                                <div className="box-mt">GSP设置</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>处方药</p>
                                        <span>{detailData.prescriptionDrugsTypeName}</span>
                                    </div>
                                    <div className="item">
                                        <p>麻黄碱</p>
                                        <span>{this.buildYesOrNo(detailData.isEphedrine)}</span>
                                    </div>
                                    <div className="item">
                                        <p>重点养护</p>
                                        <span>{this.buildYesOrNo(detailData.isKeyCuring)}</span>
                                    </div>
                                    <div className="item">
                                        <p>医保商品</p>
                                        <span>{this.buildYesOrNo(detailData.isMedicalInsuranceGoods)}</span>
                                    </div>
                                    <div className="item medicalInsuranceGoodsState" style={{display:detailData.isMedicalInsuranceGoods=="Y"?"block":"none"}}>
                                        <p>医保号</p>
                                        <span>{detailData.medicalInsuranceNum}</span>
                                    </div>
                                </div>
                            </div>}

                            <div className="md-box">
                                <div className="box-mt">其他</div>
                                <div className="box-mc clearfix">
                                    <div className="item w450">
                                        <p>说明书</p>
                                        <span>{detailData.instructions}</span>
                                    </div>
                                    <div className="item w450">
                                        <p>用药指导</p>
                                        <span>{detailData.medicationGuide}</span>
                                    </div>
                                    <div className="item w450">
                                        <p>图片</p>
                                    </div>
                                    <div className="item-upload">
                                        {pictFileList.map((pict,index)=>{
                                            if(pict.fileTypeCode == "IMAGE"){
                                                return (
                                                    <div key={index} className="upload-operation">
                                                        <a target="_blank"  href={pict.fileUrl}><img src={pict.fileUrl}  alt=""/></a>
                                                    </div>
                                                )
                                            }
                                            return (
                                                <div key={index} className="upload-operation">
                                                    <a target="_blank"  href={pict.fileUrl}>{pict.fileNm}</a>
                                                </div>
                                            )
                                        })}
                                    </div>
                                    <div className="item w450">
                                        <p>附件</p>
                                    </div>
                                    <div className="item-upload">
                                        {otherFileList.map((pict,index)=>{
                                            if(pict.fileTypeCode == "IMAGE"){
                                                return (
                                                    <div key={index} className="upload-operation">
                                                        <a target="_blank"  href={pict.fileUrl}><img src={pict.fileUrl}  alt=""/></a>
                                                    </div>
                                                )
                                            }
                                            return (
                                                <div key={index} className="upload-operation">
                                                    <a target="_blank"  href={pict.fileUrl}>{pict.fileNm}</a>
                                                </div>
                                            )
                                        })}
                                    </div>
                                </div>
                            </div>
                        </div>
                        {!isApproveOrDetail&&
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} onClick={()=>actions.updatePassApprove(detailData.id,store.getState().todos.params)} type="button" >审核通过</button>
                            <a href="javascript:void(0);" className="cancel" type="button" onClick={()=>actions.updateDenyApprove(detailData.id,store.getState().todos.params)}>审核拒绝</a>
                        </div>}
                    </div>
            </div>
        )
    }
}


GoodsDocApproveAndDetailView.contextTypes = {
    store: React.PropTypes.object
};

export default GoodsDocApproveAndDetailView