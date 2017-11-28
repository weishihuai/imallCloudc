import React, {Component, PropTypes} from "react";
import GoodsDescDetail from "./GoodsDescDetail";


class ConsultServiceDetail extends Component {

    sexStatCode(code){
        switch (code){
            case "SECRET":
                return "保密";
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
            default:
                return "暂无";
        }
    }

    boolStatCode(code){
        switch (code){
            case "Y":
                return "是";
            case "N":
                return "否";
            default :
                return "暂无";
        }
    }


    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const consult = store.getState().todos.data;
        const {goodsDetail, consultDetail} = actions;
        const {consultGoodsDetailModal} = store.getState().todos;


        return (
            <div className="layer">
                {consultGoodsDetailModal && <GoodsDescDetail store={store} actions={actions}/>}

                <div className="layer-box layer-info layer-order layer-consulting-register w1075">
                    <div className="layer-header">
                        <span>咨询登记详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>consultDetail(null, false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">顾客信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>会员卡号</p>
                                        <span>{consult.memberCardNum || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>患者姓名</p>
                                        <span>{consult.patientName}</span>
                                    </div>
                                    <div className="item">
                                        <p>年龄</p>
                                        <span>{consult.age || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>性别</p>
                                        <span>{this.sexStatCode(consult.sex)}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>手机号</p>
                                        <span>{consult.mobile || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>身份证号</p>
                                        <span>{consult.identityCard || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>身高(cm)</p>
                                        <span>{consult.height || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>体重(kg)</p>
                                        <span>{consult.weight || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>地址</p>
                                        <span>{consult.addr || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>肾功能</p>
                                        <span>{consult.rebakFunction || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>是否怀孕</p>
                                        <span>{this.boolStatCode(consult.isPregnant)}</span>
                                    </div>
                                    <div className="item">
                                        <p>过往病史</p>
                                        <span>{consult.prevMedicalHistory || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>咨询药师</p>
                                        <span>{consult.consultPharmacist || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>咨询时间</p>
                                        <span>{consult.consultTimeString || "暂无"}</span>
                                    </div>
                                    <div className="item item-problem-description">
                                        <p>问题描述</p>
                                        <span>{consult.questionDescr || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item item-problem-description">
                                        <p>专家解答</p>
                                        <span>{consult.expertAnswer || "暂无"}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc clearfix">
                                <table>
                                    <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="origin">产地</th>
                                            <th className="price">零售价</th>
                                            <th className="number">库存</th>
                                            <th className="instructions">说明书</th>
                                            <th className="guidelines">用药指导</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        consult.goodsList.map((goods,index)=>{
                                            return(
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">{index+1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.goodsNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.commonNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.spec}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.dosageForm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.unit}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.produceManufacturer}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.approvalNumber}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.productionPlace}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.retailPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.currentStock}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">
                                                            {goods.instructionsStr === "" ? "暂无" : <a href="javascript:void(0);" onClick={()=> goodsDetail(true, "说明书", goods.instructionsStr)}>查看</a> }
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">
                                                            {goods.medicationGuideStr === "" ? "暂无" : <a href="javascript:void(0);" onClick={()=> goodsDetail(true, "用药指导", goods.medicationGuideStr)}>查看</a> }
                                                        </div>
                                                    </td>
                                                </tr>
                                            )
                                        })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );

    }
}

ConsultServiceDetail.contextTypes = {
    store: React.PropTypes.object
};

export default ConsultServiceDetail