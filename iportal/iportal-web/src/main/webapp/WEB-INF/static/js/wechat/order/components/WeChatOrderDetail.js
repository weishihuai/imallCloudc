import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {WEB_NAME} from "../../../common/common-constant";
import WeChatCommonConfirmComponent from "../../../common/component/WeChatCommonConfirmComponent";
import WeChatOrderCancelView from "./WeChatOrderCancelView";
import {findById, updateOrderState, buyAgain, showOrderCancelView, showConfirm} from "../actions";

class WeChatOrderDetail extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        document.title = WEB_NAME + '订单详情';
        const {id} = this.props.routeParams;
        const {showOrderCancelView, findById} = this.props;
        findById(id);
        showOrderCancelView(false);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    render() {
        const {store} = this.context;
        const {orderDetail, isShowCancel, isShowConfirm} = store.getState().wechatOrderTodos;

        return(
            <div>
                {isShowCancel && <WeChatOrderCancelView />}
                {isShowConfirm &&
                    <WeChatCommonConfirmComponent title="确认收货？" text="" callback={()=>this.props.updateOrderState({id: orderDetail.id, orderStateCode: 'FINISH'})} close={()=>this.props.showConfirm(false)} />
                }
                {
                    orderDetail &&
                    <div style={{marginBottom: "-11rem"}} className="order-detail-main">
                        {
                            orderDetail.orderStateCode === 'WAIT_SEND' &&
                            <div className="mt">
                                <div className="deliv"><img src={iportal + '/static/img/wechat/daifahuo.png'} alt=""/></div>
                                <p>待发货 </p>
                                <span>卖家备货中，请耐心等候</span>
                            </div>
                        }
                        {
                            orderDetail.orderStateCode === 'ALREADY_SENDED' &&
                            <div className="mt">
                                <div className="take"><img src={iportal + '/static/img/wechat/daishouhuo.png'} alt=""/></div>
                                <p>待收货  </p>
                                <span>货物已在路途中</span>
                            </div>
                        }
                        {
                            orderDetail.orderStateCode === 'FINISH' &&
                            <div className="mt">
                                <div className="accomplish"><img src={iportal + '/static/img/wechat/yiwancheng.png'} alt=""/></div>
                                <p>已完成</p>
                                <span>您已成功签收</span>
                            </div>
                        }
                        {
                            orderDetail.orderStateCode === 'CLOSE' &&
                            <div className="mt">
                                <div className="close"><img src={iportal + '/static/img/wechat/yiguanbi.png'} alt=""/></div>
                                <p>已关闭</p>
                                <span>{orderDetail.closeReasonName}</span>
                            </div>
                        }
                        <div className="mc">
                            <div className="mc-cont">
                                <div className="mc-top">
                                    <div className="merchant"><a href={"#/weshop-detail/" + orderDetail.weShopId} className="elli">{orderDetail.shopNm}</a></div>
                                    <a href={'tel:' + orderDetail.shopContactTel} className="linkman">联系门店</a>
                                </div>
                                <div className="mc-mod">
                                    {
                                        orderDetail.itemList.map((item)=>{
                                            return(
                                                <div className="mc-item" key={item.id}>
                                                    <div className="pic"><a href="javascript:void(0);"><img src={getSpecImg(item.goodsPicUrl, '160X160')} alt=""/></a></div>
                                                    <div className="mc-md" style={{width: 3.5 + 'rem'}}>
                                                        <a href="javascript:void(0);" className="title elli">{item.goodsNm}</a>
                                                        <p className="elli">规格: {item.spec}</p>
                                                        <p className="elli">x{item.quantity}</p>
                                                    </div>
                                                    <div className="mc-rt" style={{width: 2.8125 + 'rem'}}>
                                                        <p>¥{item.goodsUnitPrice}</p>
                                                        {item.prescriptionDrugsTypeCode && item.prescriptionDrugsTypeCode!=='OTC' && <span className="rx">处方药</span>}
                                                        {item.isEphedrine==='Y' && <span className="rx" style={{marginLeft: 0.1562 + 'rem'}}>麻黄碱</span>}
                                                    </div>
                                                </div>
                                            );
                                        })
                                    }
                                </div>
                                <div className="mc-bot">
                                    <p>
                                        <span>配送费</span>
                                        <i>¥{orderDetail.freightAmount}</i>
                                    </p>
                                    <p style={{fontWeight: 'bold'}}>
                                        <span>实付</span>
                                        <i>¥{orderDetail.orderTotalAmount}</i>
                                    </p>
                                </div>
                            </div>
                            <div className="delivery">
                                <div className="dv-mt">配送信息</div>
                                <div className="dv-mc">
                                    <div className="item">
                                        <span>送达时间</span>
                                        <p>{orderDetail.arrivedDay}</p>
                                        {
                                            orderDetail.arrivedTime &&
                                            <p>{orderDetail.arrivedTime}</p>
                                        }
                                    </div>
                                    <div className="item">
                                        <span>配送地址</span>
                                        <p>{orderDetail.deliveryAddr + orderDetail.detailAddr}</p>
                                        <p style={{paddingTop: 0.1562 + 'rem'}}><i style={{marginRight: 15 + 'px'}}>{orderDetail.receiverName}</i>{orderDetail.contactTel}</p>
                                    </div>
                                </div>
                            </div>
                            <div className="od-info">
                                <div className="od-mt">订单信息</div>
                                <div className="od-mc">
                                    <div className="item">
                                        <span>订单编号</span>
                                        <em className="elli">{orderDetail.orderNum}</em>
                                    </div>
                                   {/* <div className="item">
                                        <span>支付方式</span>
                                        <em className="elli">{orderDetail.paymentWayName}</em>
                                    </div>*/}
                                    <div className="item">
                                        <span>下单时间</span>
                                        <em className="elli">{orderDetail.createDateString}</em>
                                    </div>
                                    <div className="item" style={{height: 'auto'}}>
                                        <span style={{width: '0.9375rem'}}>备注</span>
                                        <em style={{height: 'auto',textAlign: 'left', float: 'none', textIndent: '1.25rem', width: '100%'}}>{orderDetail.remark}</em>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md">
                            {
                                orderDetail.orderStateCode === 'WAIT_SEND' &&
                                <a href="javascript:void(0);" onClick={()=>this.props.showOrderCancelView(true)}>取消订单</a>
                            }
                            {
                                orderDetail.orderStateCode === 'ALREADY_SENDED' &&
                                <a href="javascript:void(0);" onClick={()=>this.props.showConfirm(true)}>确认收货</a>
                            }
                            {
                                (orderDetail.orderStateCode === 'FINISH' || orderDetail.orderStateCode === 'CLOSE') &&
                                <a href="javascript:void(0);" onClick={()=>this.props.buyAgain(orderDetail.id)}>再次购买</a>
                            }
                        </div>
                    </div>
                }
            </div>
        );
    }
}

WeChatOrderDetail.propTypes = {
};

WeChatOrderDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        findById,
        updateOrderState,
        buyAgain,
        showOrderCancelView,
        showConfirm
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatOrderDetail);