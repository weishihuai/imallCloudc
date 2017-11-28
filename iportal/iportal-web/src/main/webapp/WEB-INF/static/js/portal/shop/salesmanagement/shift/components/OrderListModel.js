import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";

/**
 * 交班记录
 */

class OrderListModel extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        const {orderParams} = store.getState().todos;
        this.props.actions.orderList(orderParams, orderParams.page, orderParams.size);
    }

    componentDidMount() {
    }

    componentWillUnmount() {
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {orderParams} = store.getState().todos;
        this.props.actions.orderList(orderParams, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {orderParams} = store.getState().todos;
        this.props.actions.orderList(orderParams, page - 1, sizePerPage);
    }

    render() {
        const {store} = this.context;
        const actions = this.props.actions;
        const { orderPage, shiftRecord} = store.getState().todos;
        const number = orderPage.number + 1;
        const options = {
            sizePerPage: orderPage.size > 0 ? orderPage.size : 10,
            sizePerPageList: orderPage.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: orderPage.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: orderPage.totalPages, //分页的页码
            paginationShowsTotal: null,
            hideSizePerPage: orderPage.totalElements <= orderPage.size
        };

        return (
            <div>
                <div className="layer" >
                    <div className="layer-box layer-info layer-receiving layer-buying-out w960">
                        <div className="layer-header">
                            <span>交班记录明细</span>
                            <a href="javascript:void(0);" className="close"
                               onClick={(e) => actions.orderListModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">交班信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>接班时间</p>
                                        <span>{shiftRecord.succeedTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>交班时间</p>
                                        <span>{shiftRecord.shiftTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>营业员</p>
                                        <span>{shiftRecord.posManName}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">订单信息</div>
                                <div className="box-mc">
                                    <table className="w860">
                                        <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="sales-time">销售时间</th>
                                            <th className="type">订单类型</th>
                                            <th className="order-number">订单编号</th>
                                            <th className="price">应收金额</th>
                                            <th className="price">实收金额</th>
                                            <th className="price">找零</th>
                                            <th className="price">总计</th>
                                            <th className="vip-number">会员卡号</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {orderPage.content.map((order, index) =>{
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont td-number">{index + 1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.createDateString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.orderType}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.orderNum}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.orderTotalAmount}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.realGeveCashAmount}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.returnSmall}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.goodsTotalAmount}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{order.memberCardNum}</div>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <div className="layer-footer">
                            <IMallPaginationBar options={options} actions={this.props.actions}/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

OrderListModel.propTypes = {};

OrderListModel.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OrderListModel);