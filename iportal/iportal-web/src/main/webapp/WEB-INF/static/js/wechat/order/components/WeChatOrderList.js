import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import AutoLoadMore from "../../../common/component/AutoLoadMore";
import {WEB_NAME} from "../../../common/common-constant";
import {orderList} from "../actions";

class WeChatOrderList extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        document.title = WEB_NAME + '我的需求单';
        const {store} = this.context;
        const {params} = store.getState().wechatOrderTodos;
        this.props.orderList(params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    render() {
        const {store} = this.context;
        const {page, pageLength} = store.getState().wechatOrderTodos;
        const record = page.content || [];

        return(
            <div className="order-main" id="orderDiv">
                {
                    record.length===0 &&
                    <div className="empty">没有更多订单了哦～</div>
                }
                {
                    record.map((order) => {
                        return(
                            <div className="item" key={order.id}>
                                <div className="mt">
                                    <a href="javascript:void(0);" className="name">{order.shopNm}</a>
                                    <span className="state">{order.orderStateName}</span>
                                </div>
                                {
                                    order.itemList.length===1 &&
                                    <div className="mc">
                                        <div className="pic"><a href={'#/order-detail/' + order.id}><img src={getSpecImg(order.itemList[0].goodsPicUrl, '160X160')}/></a></div>
                                        <div className="gd-box">
                                            <a href={'#/order-detail/' + order.id} className="title">{order.itemList[0].goodsNm}</a>
                                            {(order.itemList[0].prescriptionDrugsTypeCode==='RX' || order.itemList[0].prescriptionDrugsTypeCode==='SG')  && <span className="rx" style={{marginRight: 0.5 + 'rem'}}>处方药</span>}
                                            {order.itemList[0].isEphedrine==='Y'  && <span className="rx">麻黄碱</span>}
                                        </div>
                                    </div>
                                }
                                {
                                    order.itemList.length>1 &&
                                    <div className="mc">
                                        {
                                            order.itemList.map((item)=>{
                                                return(
                                                    <div className="pic" key={item.id}>
                                                        <a href={'#/order-detail/' + order.id}>
                                                            <img src={getSpecImg(item.goodsPicUrl, '160X160')}/>
                                                        </a>
                                                    </div>
                                                );
                                            })
                                        }
                                    </div>
                                }
                                <div className="md">
                                    {/*<div className="alt">
                                        <span>共{order.totalQuantity}件</span>
                                        <span>实付: </span>
                                        <i>¥{order.orderTotalAmount}</i>
                                    </div>*/}
                                    <a href={'#/order-detail/' + order.id} className="md-btn">
                                        {order.orderStateCode==="WAIT_SEND" &&  '取消订单'}
                                        {order.orderStateCode==="ALREADY_SENDED" &&  '确认收货'}
                                        {(order.orderStateCode==="FINISH" || order.orderStateCode==="CLOSE") &&  '再次购买'}
                                    </a>
                                    <span  className="total-amount" >共{order.totalQuantity}件&nbsp;&nbsp;实付:<i style={{color: "#0C1828", fontWeight: "bold", marginLeft: "0.18rem"}}>¥{order.orderTotalAmount}</i></span>
                                </div>
                            </div>
                        );
                    })
                }
                {page.content && <AutoLoadMore container={'orderDiv'} loadMore={()=>this.props.orderList(page.number + 1, page.size, page.content)} pageNum={page.number} pageSize={page.size} totalCount={page.totalElements} nextPageLength={pageLength}/>}
            </div>
        );
    }
}

WeChatOrderList.propTypes = {
};

WeChatOrderList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        orderList
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatOrderList);