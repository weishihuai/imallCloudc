import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";
import {niftyNoty} from '../../../../../common/common';
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import CommonSingleInputComponent from "../../../../../../js/common/component/CommonSingleInputComponent";
import CommonConfirmComponent from "../../../../../../js/common/component/CommonConfirmComponent";

import OrderStatistics from  './OrderStatistics';
import OrderSearchForm from  './OrderSearchForm';
import OrderDetail from  './OrderDetail';
import OrderCloseForm from  './OrderCloseForm';

class OrderList extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        const {actions, orderStateCode}=this.props;
        const {params} = store.getState().orderTodos;
        switch(orderStateCode){
            case "WAIT_SEND":
                this.props.portalOperationalAuth([
                    "weshop:order:tosendorder:send",
                    "weshop:order:tosendorder:remark",
                    "weshop:order:tosendorder:print",
                    "weshop:order:tosendorder:detail",
                    "weshop:order:tosendorder:close"
                ]);
                break;
            case "ALREADY_SENDED":
                this.props.portalOperationalAuth([
                    "weshop:order:sendorder:remark",
                    "weshop:order:sendorder:print",
                    "weshop:order:sendorder:detail",
                    "weshop:order:sendorder:confirm",
                    "weshop:order:sendorder:close"
                ]);
                break;
            case "FINISH":
                this.props.portalOperationalAuth([
                    "weshop:order:finishorder:remark",
                    "weshop:order:finishorder:print",
                    "weshop:order:finishorder:detail"
                ]);
                break;
            case "CLOSE":
                this.props.portalOperationalAuth([
                    "weshop:order:closehorder:remark",
                    "weshop:order:closehorder:print",
                    "weshop:order:closehorder:detail"
                ]);
                break;
            default:
                this.props.portalOperationalAuth([
                    "weshop:order:allorder:send",
                    "weshop:order:allorder:remark",
                    "weshop:order:allorder:print",
                    "weshop:order:allorder:detail",
                    "weshop:order:allorder:confirm",
                    "weshop:order:allorder:close"
                ]);
                break;
        }
        const searchParams = Object.assign({}, params, {
            orderStateCode: orderStateCode
        });
        actions.orderList(searchParams, searchParams.page, searchParams.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().orderTodos;
        this.props.actions.orderList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().orderTodos;
        this.props.actions.orderList(params, page - 1, sizePerPage);
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
        const {params, order} = store.getState().orderTodos;
        const {actions} = this.props;
        const remarkData = {id: order.id, adminRemark: newAdminRemark};
        actions.updateOrderRemark(remarkData, ()=>actions.orderList(params, params.page, params.size));
    }

    render() {
        const {actions, orderStateCode}=this.props;
        const {store} = this.context;
        const {page, isShowDetail, isShowClose, isShowRemark, isShowConfirm, params, data, id, order} = store.getState().orderTodos;
        const number=page.number+1;
        const record = page.content || [];
        const options = {
            sizePerPage: page.size>0?page.size:10,
            sizePerPageList: page.totalElements>0?[10, 20, 40]:[],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            hideSizePerPage:page.totalElements <= page.size
        };

        if(isShowDetail && data){
            return(
                <OrderDetail actions={actions} orderStateCode={orderStateCode}/>
            );
        }

        return (
            <div className="main-box">
                {isShowClose && <OrderCloseForm actions={actions} onSubmit={(data) => actions.closeOrder(data, ()=>actions.orderList(params, params.page, params.size))}/>}
                {isShowRemark && <CommonSingleInputComponent title="修改备注" label="备注" zIndex="100" value={order.adminRemark ? order.adminRemark : ''} close={()=>actions.showRemark(false)} callback={(adminRemark)=>this.remarkCallback(adminRemark)}/>}
                {isShowConfirm && <CommonConfirmComponent title="打印小票" text="确认打印小票？" zIndex="100" confirmBtn="确定" callback={()=>actions.printOrderSmallTicket(id)} close={()=>actions.showConfirm(false)} />}
                <div className="main order-main all-order-main">
                    <div className="mt">
                        <div className="mt-lt">
                            {orderStateCode==='' && <h5>全部订单</h5>}
                            {orderStateCode==='WAIT_SEND' && <h5>待发货订单</h5>}
                            {orderStateCode==='ALREADY_SENDED' && <h5>已发货订单</h5>}
                            {orderStateCode==='FINISH' && <h5>已完成订单</h5>}
                            {orderStateCode==='CLOSE' && <h5>已关闭订单</h5>}
                            <a href="javascript:void(0);">微店管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">订单管理</a>
                            <span>></span>
                            {orderStateCode==='' && <a href="javascript:void(0);">全部订单</a>}
                            {orderStateCode==='WAIT_SEND' && <a href="javascript:void(0);">待发货订单</a>}
                            {orderStateCode==='ALREADY_SENDED' && <a href="javascript:void(0);">已发货订单</a>}
                            {orderStateCode==='FINISH' && <a href="javascript:void(0);">已完成订单</a>}
                            {orderStateCode==='CLOSE' && <a href="javascript:void(0);">已关闭订单</a>}
                            {orderStateCode==='' && <OrderStatistics actions={actions}/>}
                            <OrderSearchForm actions={actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="order-table">
                            <ul className="table-th">
                                <li className="sp-info">商品信息</li>
                                <li className="unit-price">单价</li>
                                <li className="number">数量</li>
                                <li className="subtotal">小计</li>
                                <li className="xd-vip">收货人</li>
                                <li className="time">预约送达时间</li>
                                <li className="status">订单状态</li>
                                <li className="order-total">订单总计</li>
                            </ul>
                            <div className="table-tbody">
                            {
                                record.length <= 0 &&
                                <div className="item">
                                    <div className="empty-box">
                                        <span>暂无数据</span>
                                    </div>
                                </div>
                            }
                            {
                                record.map((order) => {
                                    return(
                                        <div className="item" key={order.id}>
                                            <div className="table-bh">
                                                <div className="fl">
                                                    <p className="order-number">订单编号：<span>{order.orderNum}</span></p>
                                                    <p className="order-time">下单时间 ：<span>{order.createDateString}</span></p>
                                                </div>
                                                <div className="fr">
                                                    {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:detail" text="查看详情" className="" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                    {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:remark" text="备注" className="" onClick={()=>actions.showRemark(true, order)}/>}
                                                    {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:print" text="打印小票" className="" onClick={()=>actions.showConfirm(true, '', order.id)}/>}

                                                    {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:detail" text="查看详情" className="" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                    {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:remark" text="备注" className="" onClick={()=>actions.showRemark(true, order)}/>}
                                                    {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:print" text="打印小票" className="" onClick={()=>actions.showConfirm(true, '', order.id)}/>}

                                                    {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:detail" text="查看详情" className="" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                    {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:remark" text="备注" className="" onClick={()=>actions.showRemark(true, order)}/>}
                                                    {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:print" text="打印小票" className="" onClick={()=>actions.showConfirm(true, '', order.id)}/>}

                                                    {orderStateCode==='FINISH' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:finishorder:detail" text="查看详情" className="" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                    {orderStateCode==='FINISH' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:finishorder:remark" text="备注" className="" onClick={()=>actions.showRemark(true, order)}/>}
                                                    {orderStateCode==='FINISH' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:finishorder:print" text="打印小票" className="" onClick={()=>actions.showConfirm(true, '', order.id)}/>}

                                                    {orderStateCode==='CLOSE' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:closehorder:detail" text="查看详情" className="" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                    {orderStateCode==='CLOSE' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:closehorder:remark" text="备注" className="" onClick={()=>actions.showRemark(true, order)}/>}
                                                    {orderStateCode==='CLOSE' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:closehorder:print" text="打印小票" className="" onClick={()=>actions.showConfirm(true, '', order.id)}/>}
                                                </div>
                                            </div>
                                            <div className="table-inner clearfix">
                                                <div className="table-l">
                                                    {
                                                        order.orderItemDetailVoList.map((orderItem)=>{
                                                            return(
                                                                <div className="table-lli" key={orderItem.id}>
                                                                    <div className="sp-info-td">
                                                                        <div className="goods-info-pic">
                                                                            <img src={orderItem.goodsPicUrl ? orderItem.goodsPicUrl : iportal + '/static/img/nopict_100X100.png'} alt={orderItem.commonNm}/>
                                                                        </div>
                                                                        <div className="goods-info-text">
                                                                            <p className="goods-info-name">
                                                                                {orderItem.commonNm}
                                                                                {orderItem.prescriptionDrugsTypeCode && orderItem.prescriptionDrugsTypeCode !== 'OTC' && <span>[处方药]</span>}
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
                                                                </div>
                                                            );
                                                        })
                                                    }
                                                </div>
                                                <div className="table-r">
                                                    <div className="xd-vip">
                                                        <div className="table-r-cont">
                                                            <p>{order.receiverName}</p><span>{order.contactTel}</span>
                                                        </div>
                                                    </div>
                                                    <div className="time">
                                                        <div className="table-r-cont">
                                                            <p>{order.bookDeliveryDateString}</p><span>{order.bookDeliveryTimeStartString ? order.bookDeliveryTimeStartString + '-' + order.bookDeliveryTimeEndString : '尽快送达'}</span>
                                                        </div>
                                                    </div>
                                                    <div className="status">
                                                        {
                                                            order.orderStateCode==='WAIT_SEND' &&
                                                            <div className="table-r-cont">
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:detail" className="status-a1" text="待发货" onClick={()=>{}}/>}
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:send" className="status-a2" text="立即发货" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:close" className="status-a3" text="关闭订单" onClick={()=>actions.showCloseForm(true, order.id)}/>}

                                                                {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:detail" className="status-a1" text="待发货" onClick={()=>{}}/>}
                                                                {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:send" className="status-a2" text="立即发货" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='WAIT_SEND' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:tosendorder:close" className="status-a3" text="关闭订单" onClick={()=>actions.showCloseForm(true, order.id)}/>}
                                                            </div>
                                                        }
                                                        {
                                                            order.orderStateCode==='ALREADY_SENDED' &&
                                                            <div className="table-r-cont">
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:detail" className="status-a1" text="已发货" onClick={()=>{}}/>}
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:confirm" className="status-a2" text="确认送达" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:close" className="status-a3" text="关闭订单" onClick={()=>actions.showCloseForm(true, order.id)}/>}

                                                                {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:detail" className="status-a1" text="已发货" onClick={()=>{}}/>}
                                                                {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:confirm" className="status-a2" text="确认送达" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='ALREADY_SENDED' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:sendorder:close" className="status-a3" text="关闭订单" onClick={()=>actions.showCloseForm(true, order.id)}/>}
                                                            </div>
                                                        }
                                                        {
                                                            order.orderStateCode==='FINISH' &&
                                                            <div className="table-r-cont">
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:detail" className="status-a1" text="已完成" onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='FINISH' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:finishorder:detail" className="status-a1" text="已完成" onClick={()=>actions.showDetail(true, order.id)}/>}

                                                            </div>
                                                        }
                                                        {
                                                            order.orderStateCode==='CLOSE' &&
                                                            <div className="table-r-cont">
                                                                {orderStateCode==='' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:allorder:detail" className="status-a1" text='已关闭' onClick={()=>actions.showDetail(true, order.id)}/>}
                                                                {orderStateCode==='CLOSE' && <Imalla href="javascript:void(0);" permissionCode="weshop:order:closehorder:detail" className="status-a1" text='已关闭' onClick={()=>actions.showDetail(true, order.id)}/>}
                                                            </div>
                                                        }
                                                    </div>
                                                    <div className="order-total">
                                                        <div className="table-r-cont">
                                                            <p>¥{order.orderTotalAmount}</p><span>（含配送费：¥{order.freightAmount}）</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            {order.remark && <div className="table-bz"><p>用户备注：{order.remark}</p></div>}
                                            {order.adminRemark && <div className="table-bz"><p>管理员备注：{order.adminRemark}</p></div>}
                                        </div>
                                    );
                                })
                            }
                            </div>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        );
    }
}

OrderList.propTypes = {
    actions: PropTypes.object.isRequired,
    orderStateCode: PropTypes.string.isRequired
};

OrderList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OrderList);