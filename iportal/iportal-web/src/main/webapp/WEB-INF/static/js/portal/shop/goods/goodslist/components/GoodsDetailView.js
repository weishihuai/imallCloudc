import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

class GoodsDetailView extends Component {

  buildYesOrNo(code){
      return code=="Y"?"是":"否";
  }


  buildIsEnable(isEnable){
    return isEnable=="Y"?"启用":"停用";
  }

  buildPrescriptionDrugsType(prescriptionDrugsTypeName){
      switch (prescriptionDrugsTypeName){
          case "RX":
              return "是";
          case "OTC":
              return "否";
          case "SG":
              return "双轨制";
      }
  }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {detailData} = store.getState().todos;
        const otherFileList = detailData.otherFileVoList||[];
        const pictFileList = detailData.pictFileVoList||[];
        const {IS_DRUG,IS_OTHER,IS_CHINESE_MEDICINE_PIECES,IS_FOOD_HEALTH,IS_DAILY_NECESSITIES,IS_MEDICAL_INSTRUMENTS,IS_COSMETIC} = store.getState().todos;
        return (
            <div className="layer">
                <div className="layer-box layer-info w960">
                        <div className="layer-header">
                            <span>商品详情</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.changeGoodsDetailViewState(false)}/>
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
                                            <span>{detailData.sellsCategoryStr}</span>
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
                                        <span>{detailData.approvalNumberTermString}</span>
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
                                            <span>{detailData.approveDateString}</span>
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
                                            <span>{detailData.productionDateString}</span>
                                        </div>
                                    </div>}

                                    {IS_FOOD_HEALTH &&
                                    <div className="item">
                                        <p><i>*</i>保质期</p>
                                        <div className="sel-date">
                                            <span>{detailData.expirationDateString}</span>
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
                                        <span>{this.buildPrescriptionDrugsType(detailData.prescriptionDrugsTypeCode)}</span>
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
                                        <p><i>*</i>医保号</p>
                                        <span>{detailData.medicalInsuranceNum}</span>
                                    </div>
                                </div>
                            </div>}

                            <div className="md-box">
                                <div className="box-mt">价格设置</div>
                                <div className="box-mc clearfix">
                                    <div  className="item">
                                        <p><i>*</i>零售价</p>
                                        <span>{detailData.retailPrice}</span>
                                    </div>
                                    <div className="item">
                                        <p>会员价</p>
                                        <span>{detailData.memberPrice}</span>
                                    </div>
                                    <div className="item">
                                        <p>市场价</p>
                                        <span>{detailData.marketPrice}</span>
                                    </div>
                                    <div className="item">
                                        <p>成本价</p>
                                        <span>{detailData.costPrice}</span>
                                    </div>
                                    <div className="item">
                                        <p>当前库存</p>
                                        <span>{detailData.currentStock}</span>
                                    </div>
                                    <div className="item">
                                        <p>安全库存</p>
                                        <span>{detailData.securityStock}</span>
                                    </div>
                                    <div className="item">
                                        <p>采购税率</p>
                                        <span>{detailData.purchaseTaxRate}</span>
                                    </div>
                                    <div className="item">
                                        <p>销售税率</p>
                                        <span>{detailData.sellTaxRate}</span>
                                    </div>
                                    <div className="item">
                                        <p>特价商品</p>
                                        <span>{this.buildYesOrNo(detailData.isSpecialPriceGoods)}</span>
                                    </div>
                                    <div className="item">
                                        <p>是否拆零</p>
                                        <span>{this.buildYesOrNo(detailData.isSplitZero)}</span>
                                    </div>

                                    <div className="item splitZeroState" style={{display:detailData.isSplitZero=="Y"?"block":"none"}}>
                                        <p><i>*</i>拆零单位</p>
                                        <span>{detailData.splitZeroUnit}</span>
                                    </div>

                                    <div className="item splitZeroState" style={{display:detailData.isSplitZero=="Y"?"block":"none"}}>
                                        <p><i>*</i>拆零数量</p>
                                        <span>{detailData.splitZeroQuantity}</span>
                                    </div>

                                    <div className="item splitZeroState" style={{display:detailData.isSplitZero=="Y"?"block":"none"}}>
                                        <p><i>*</i>拆零规格</p>
                                        <span>{detailData.splitZeroSpec}</span>
                                    </div>
                                    <div className="item splitZeroState" style={{display:detailData.isSplitZero=="Y"?"block":"none"}}>
                                        <p><i>*</i>拆零零售价</p>
                                        <span>{detailData.splitZeroRetailPrice}</span>
                                    </div>
                                    <div className="item splitZeroState" style={{display:detailData.isSplitZero=="Y"?"block":"none"}}>
                                        <p><i>*</i>拆零会员价</p>
                                        <span>{detailData.splitZeroMemberPrice}</span>
                                    </div>
                                    <div className="item"></div>
                                    <div className="item w450">
                                        <p>说明书</p>
                                        <span>{detailData.instructions}</span>
                                    </div>
                                    <div className="item w450">
                                        <p>用药指导</p>
                                        <span>{detailData.medicationGuide}</span>
                                    </div>
                                </div>
                            </div>

                            <div className="md-box">
                                <div className="box-mt">其他</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>药品状态</p>
                                        <span>{this.buildIsEnable(detailData.isEnable)}</span>
                                    </div>
                                    <div className="item">
                                        <p>首营</p>
                                        <span>{this.buildYesOrNo(detailData.isFirstSell)}</span>
                                    </div>
                                    <div className="item"></div>
                                    <div className="item"></div>
                                    <div className="item w450">
                                        <p>说明书</p>
                                        <span>{detailData.submitIdea}</span>
                                    </div>
                                    <div className="item w450">
                                        <p>用药指导</p>
                                        <span>{detailData.remark}</span>
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
                    </div>
            </div>
        )
    }
}


GoodsDetailView.contextTypes = {
    store: React.PropTypes.object
};

export default GoodsDetailView