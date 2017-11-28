import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";
import {niftyNoty} from '../../../../../common/common';
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import CommonConfirmComponent from "../../../../../../js/common/component/CommonConfirmComponent";
import CommonSingleInputComponent from "../../../../../../js/common/component/CommonSingleInputComponent";

import OrderSendForm from './OrderSendForm';

class OrderDetail extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.portalOperationalAuth([
            "weshop:order:tosendorder:send",
            "weshop:order:tosendorder:remark",
            "weshop:order:tosendorder:print",
            "weshop:order:tosendorder:close",
            "weshop:order:sendorder:remark",
            "weshop:order:sendorder:print",
            "weshop:order:sendorder:confirm",
            "weshop:order:sendorder:close",
            "weshop:order:finishorder:remark",
            "weshop:order:finishorder:print",
            "weshop:order:closehorder:remark",
            "weshop:order:closehorder:print"
        ]);
    }

    componentDidMount(){
        this.refs.orderDetailRoot.scrollIntoView();
    }

    remarkCallback(adminRemark){
        const newAdminRemark = $.trim(adminRemark);
        if(newAdminRemark.length===0){
            niftyNoty("备注不能为空", false);
            return;
        }else if(newAdminRemark.length>255){
            niftyNoty("备注长度不能超过255个字符", false);
            return;
        }

        const {store} = this.context;
        const {order} = store.getState().orderTodos;
        const {actions} = this.props;
        const remarkData = {id: order.id, adminRemark: newAdminRemark};
        actions.updateOrderRemark(remarkData, ()=>actions.showDetail(true, order.id));
    }

    gotoOrderList(){
        const {store} = this.context;
        const {params} = store.getState().orderTodos;
        const {actions} = this.props;
        actions.showDetail(false);
        const searchParams = Object.assign({}, params, {
            keyword:"",                     //商品编码/通用名称/通用名称首字母/商品名称
            receiverName:"",                //收货人姓名
            contactTel:"",                  //联系电话
            orderNum:"" ,                   //订单编号
            startCreateDateString:"",       //下单开始时间
            endCreateDateString:"",         //下单结束时间
            bookDeliveryTimeStartString:"", //预约送达时间开始
            bookDeliveryTimeEndString:"" ,  //预约送达时间结束
            paymentWayCode:""               //支付 方式 代码
        });
        actions.orderList(searchParams, searchParams.page, searchParams.size);
    }

    render() {
        const {store} = this.context;
        const {data, id, isShowConfirm, isShowRemark, isShowSend, confirmType} = store.getState().orderTodos;
        const {actions, orderStateCode} = this.props;
        const orderStateData = {
            id: data.id,
            orderStateCode: confirmType==='toFinish' ?  'FINISH' : 'CLOSE',
            cancelOrderReason: (confirmType==='toCancel' ? '管理员取消订单' : (confirmType==='toReject' ? '用户拒收' : ''))
        };

        return(
            <div className="main-box" style={{paddingBottom: 10 + 'px'}} ref="orderDetailRoot">
                {
                    isShowConfirm && (data.orderStateCode==='WAIT_SEND' || data.orderStateCode==='ALREADY_SENDED') &&
                    <CommonConfirmComponent
                        title={confirmType==='toCancel' ? "订单取消" : (confirmType==='toReject' ? "订单拒收" : (confirmType==='toFinish' ? "订单确认" : '打印小票'))}
                        text={confirmType==='toCancel' ? "确定取消订单？" : (confirmType==='toReject' ? "确定拒收订单？" : (confirmType==='toFinish' ? "确认送达？" : "确认打印小票？"))}
                        zIndex="100" confirmBtn="确定"
                        callback={()=>(confirmType==='toPrint' ? actions.printOrderSmallTicket(id) : actions.confirm(orderStateData, ()=>actions.showDetail(true, id)))}
                        close={()=>actions.showConfirm(false)} />
                }
                {isShowRemark && <CommonSingleInputComponent title="修改备注" label="备注" zIndex="100" value={data.adminRemark} close={()=>actions.showRemark(false)} callback={(adminRemark)=>this.remarkCallback(adminRemark)}/>}
                {isShowSend && <OrderSendForm actions={actions} onSubmit={(data)=>actions.updateOrderToSend(data)}/>}
                <div className="main order-main order-details-main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>订单详情</h5>
                            <a href="javascript:void(0);">微店管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">订单管理</a>
                            <span>></span>
                            {orderStateCode==='' && <a href="javascript:void(0);">全部订单</a>}
                            {orderStateCode==='WAIT_SEND' && <a href="javascript:void(0);">待发货订单</a>}
                            {orderStateCode==='ALREADY_SENDED' && <a href="javascript:void(0);">已发货订单</a>}
                            {orderStateCode==='FINISH' && <a href="javascript:void(0);">已完成订单</a>}
                            {orderStateCode==='CLOSE' && <a href="javascript:void(0);">已关闭订单</a>}
                            <span>></span>
                            <a href="javascript:void(0);">订单详情</a>
                            <div className="lt-cont">
                            </div>
                        </div>
                        <div className="mt-rt">
                        </div>
                    </div>
                    <div className="mc">
                        <div className="mc-box-status clearfix">
                            <div className="fl">
                                <p className="fl-head">{data.orderStateName}{data.isCod==='Y' && "（货到付款）"}</p>
                                {
                                    data.orderStateCode==='WAIT_SEND' &&
                                    <div className="fl-btn">
                                        <Imalla href="javascript:void(0);"  permissionCode="weshop:order:tosendorder:send" className="fl-btn1" text="立即发货" onClick={()=>actions.showOrderSend(true, data.id)}/>
                                        <Imalla href="javascript:void(0);"  permissionCode="weshop:order:tosendorder:close" className="fl-btn2" text="取消订单" onClick={()=>actions.showConfirm(true, 'toCancel', data.id)}/>
                                    </div>
                                }
                                {
                                    data.orderStateCode==='ALREADY_SENDED' &&
                                    <div className="fl-btn">
                                        <Imalla href="javascript:void(0);"  permissionCode="weshop:order:sendorder:confirm" className="fl-btn1" text="确认送达" onClick={()=>actions.showConfirm(true, 'toFinish', data.id)}/>
                                        <Imalla href="javascript:void(0);"  permissionCode="weshop:order:sendorder:close" className="fl-btn2" text="买家拒收" onClick={()=>actions.showConfirm(true, 'toReject', data.id)}/>
                                    </div>
                                }

                                {
                                    data.cancelOrderReason && <p style={{color: '#999'}}>{data.cancelOrderReason}</p>
                                }
                                {!data.cancelOrderReason && !data.remark && <p style={{marginTop: 20 + 'px', paddingTop: 10 + 'px', minHeight: 10 + 'px'}}/>}
                                {data.cancelOrderReason && !data.remark && <p style={{marginTop: 20 + 'px', paddingTop: 10 + 'px', minHeight: 10 + 'px', borderTop: '1px dashed #e1e1e1'}}/>}
                                {!data.cancelOrderReason && data.remark && <p style={{marginTop: 20 + 'px', paddingTop: 10 + 'px', borderTop: '1px dashed #e1e1e1'}}/>}
                                {data.cancelOrderReason && data.remark && <p style={{marginTop: 10 + 'px', paddingTop: 10 + 'px', borderTop: '1px dashed #e1e1e1'}}/>}
                                {
                                    data.remark && <p style={{minHeight: 50 + 'px', color: '#999'}}>用户备注：{data.remark}</p>
                                }
                                {
                                    data.adminRemark && <p style={{minHeight: 50 + 'px', color: '#999'}}>管理员备注：{data.adminRemark}</p>
                                }
                                <Imalla href="javascript:void(0);"  permissionCode={
                                        data.orderStateCode==='WAIT_SEND' ? 'weshop:order:tosendorder:print' :
                                            (data.orderStateCode==='ALREADY_SENDED' ? 'weshop:order:sendorder:print' :
                                                (data.orderStateCode==='FINISH' ? 'weshop:order:finishorder:print' :
                                                    (data.orderStateCode==='CLOSE' ? 'weshop:order:closehorder:print' :
                                                        '')))}
                                        className="" text="打印小票" onClick={()=>actions.showConfirm(true, 'toPrint', data.id)}/>
                                <Imalla href="javascript:void(0);"  permissionCode={
                                    data.orderStateCode==='WAIT_SEND' ? 'weshop:order:tosendorder:remark' :
                                        (data.orderStateCode==='ALREADY_SENDED' ? 'weshop:order:sendorder:remark' :
                                            (data.orderStateCode==='FINISH' ? 'weshop:order:finishorder:remark' :
                                                (data.orderStateCode==='CLOSE' ? 'weshop:order:closehorder:remark' :
                                                    '')))}
                                        className="" text="修改备注" onClick={()=>actions.showRemark(true, data)}/>
                                <span style={{position: "relative"}}><a  onClick={()=>this.gotoOrderList()} href="##" style={{display: "block", width: "88px", height: "28px", lineHeight: "28px", textAlign: "center", border: "1px solid #c9c9c9", color: "#666!important", background: "#fff", position: "absolute", top: "-7px", left: "0px"}}>返回</a></span>
                            </div>
                            {
                                data.orderStateCode === 'WAIT_SEND' &&
                                <div className="fr" style={{paddingLeft: '5%'}}>
                                    <div className="fr-list1 complete">
                                        <p className="fr-status">订单创建</p>
                                        <p className="fr-time">{data.createDateString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list2">
                                        <p className="fr-status">订单发货</p>
                                        <p className="fr-time"/>
                                        <div className="fr-icon1"><em>2</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list3">
                                        <p className="fr-status">订单完成</p>
                                        <p className="fr-time"/>
                                        <div className="fr-icon1"><em>3</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                </div>
                            }
                            {
                                data.orderStateCode === 'ALREADY_SENDED' &&
                                <div className="fr" style={{paddingLeft: '5%'}}>
                                    <div className="fr-list1 complete">
                                        <p className="fr-status">订单创建</p>
                                        <p className="fr-time">{data.createDateString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list2 complete">
                                        <p className="fr-status">订单发货</p>
                                        <p className="fr-time">{data.orderConfirmTimeString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list3">
                                        <p className="fr-status">订单完成</p>
                                        <p className="fr-time"/>
                                        <div className="fr-icon1"><em>3</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                </div>
                            }
                            {
                                data.orderStateCode === 'FINISH' &&
                                <div className="fr" style={{paddingLeft: '5%'}}>
                                    <div className="fr-list1 complete">
                                        <p className="fr-status">订单创建</p>
                                        <p className="fr-time">{data.createDateString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list2 complete">
                                        <p className="fr-status">订单发货</p>
                                        <p className="fr-time">{data.orderConfirmTimeString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list3 complete">
                                        <p className="fr-status">订单完成</p>
                                        <p className="fr-time">{data.finishTimeString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                </div>
                            }
                            {
                                data.orderStateCode === 'CLOSE' &&
                                <div className="fr" style={{paddingLeft: '5%'}}>
                                    <div className="fr-list1 complete">
                                        <p className="fr-status">订单创建</p>
                                        <p className="fr-time">{data.createDateString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                    <div className="fr-list3 complete">
                                        <p className="fr-status">订单关闭</p>
                                        <p className="fr-time">{data.orderCloseTimeString}</p>
                                        <div className="fr-icon1"><em>✔</em></div>
                                        <div className="fr-icon2"/>
                                    </div>
                                </div>
                            }
                        </div>
                        <div className="mc-box-info">
                            <div>
                                <p className="info-name">订单基本信息</p>
                                <p>订单编号：<span>{data.orderNum}</span></p>
                                <p>下单时间：<span>{data.createDateString}</span></p>
                            </div>
                            <div>
                                <p className="info-name">配送信息</p>
                                <p>配送方式：<span>门店配送</span></p>
                                <p>预约送达时间：<span>{data.bookDeliveryTimeStartString ? data.bookDeliveryTimeStartString + '-' + data.bookDeliveryTimeEndString : '尽快送达'}</span></p>
                            </div>
                            <div>
                                <p className="info-name">下单人信息</p>
                                <p>联系人：<span>{data.receiverName}</span></p>
                                <p>联系电话：<span>{data.contactTel}</span></p>
                                <p>配送地址：<span>{data.deliveryAddr}{data.detailAddr}</span></p>
                            </div>
                        </div>
                        <div className="order-table">
                            <ul className="table-th">
                                <li className="sp-info">商品信息</li>
                                <li className="unit-price">单价</li>
                                <li className="number">数量</li>
                                <li className="subtotal">小计</li>
                                <li className="batch">发货批号</li>
                            </ul>
                            <div className="table-tbody">
                                <div className="item">
                                    <div className="table-inner clearfix">
                                        <div className="table-l">
                                            {
                                                data.orderItemDetailVoList.map((orderItem)=>{
                                                    return(
                                                        <div className="table-lli" key={orderItem.id}>
                                                            <div className="sp-info-td">
                                                                <div className="goods-info-pic"><img src={orderItem.goodsPicUrl ? orderItem.goodsPicUrl : iportal + '/static/img/nopict_100X100.png'} alt=""/></div>
                                                                <div className="goods-info-text">
                                                                    <p className="goods-info-name">
                                                                        {orderItem.commonNm}
                                                                        {orderItem.prescriptionDrugsTypeCode && orderItem.prescriptionDrugsTypeCode!=='OTC' && <span>[处方药]</span>}
                                                                        {orderItem.isEphedrine==='Y' && <span>[含麻黄碱]</span>}
                                                                    </p>
                                                                    <p className="goods-info-specifications">规格：{orderItem.spec}</p>
                                                                    <p className="goods-info-unit">单位：{orderItem.unit}</p>
                                                                    <p className="goods-info-manufacturer">生产厂家：{orderItem.produceManufacturer}</p>
                                                                </div>
                                                            </div>
                                                            <div className="unit-price-td">¥{orderItem.goodsUnitPrice}</div>
                                                            <div className="number">{orderItem.quantity}</div>
                                                            <div className="subtotal">¥{orderItem.goodsTotalAmount}</div>
                                                            <div className="batch">
                                                                <div className="batch-box">
                                                                    {
                                                                        orderItem.orderSendOutBatchDetailVoList.length===0 &&
                                                                        <span>--</span>
                                                                    }
                                                                    {
                                                                        orderItem.orderSendOutBatchDetailVoList.map((batchInfo)=>{
                                                                            return(
                                                                                <span key={batchInfo.id}>【{batchInfo.batch}】   X {batchInfo.quantity} 件</span>
                                                                            );
                                                                        })
                                                                    }
                                                                </div>
                                                            </div>
                                                        </div>
                                                    );
                                                })
                                            }
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="order-amout">
                            <div><p>商品总金额：</p><span>¥{data.goodsTotalAmount}</span></div>
                            <div><p>配送费：</p><span>¥{data.freightAmount}</span></div>
                            <div className="order-amount-payable"><p>订单应付金额：</p><span>¥{data.orderTotalAmount}</span></div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

OrderDetail.propTypes = {
    actions: PropTypes.object.isRequired,
    orderStateCode: PropTypes.string.isRequired
};

OrderDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OrderDetail);