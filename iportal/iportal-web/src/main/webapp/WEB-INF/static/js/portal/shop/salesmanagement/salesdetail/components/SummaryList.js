import React, {Component, PropTypes} from "react";

class SummaryList extends Component {

    render() {
        const {store} = this.context;
        const content = store.getState().todos.page.content;

        return (
            <div className="mc">
                <div className="table-box">
                    <table>
                        <thead>
                        <tr>
                            <th className="order-number">订单编号</th>
                            <th className="time">销售时间</th>
                            <th className="order-type">订单类型</th>
                            <th className="price">应收金额</th>
                            <th className="price">实收金额</th>
                            <th className="number">数量总计</th>
                            <th className="price">成本金额</th>
                            <th className="name">开单人</th>
                            <th className="status">状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        {content.length <= 0 &&
                        <tr >
                            <td colSpan="9" style={{textAlign:"center"}}>
                                <div className="empty-box">
                                    <span>暂无数据</span>
                                </div>
                            </td>
                        </tr>
                        }
                        {
                            content.map((ct, index) => {
                                return(
                                    <tr key={index}>
                                        <td>
                                            <div className="td-cont" title={ct.orderNum}>{ct.orderNum}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.sellTimeString}>{ct.sellTimeString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.orderSourceName}>{ct.orderSourceName}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.amountReceivable}>{ct.amountReceivable}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.amountReceived}>{ct.amountReceived}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.goodsTotalNum}>{ct.goodsTotalNum}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.costTotalAmount}>{ct.costTotalAmount}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.openOrderMan}>{ct.openOrderMan}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.orderStatName}>{ct.orderStatName}</div>
                                        </td>
                                    </tr>
                                );
                            })
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

SummaryList.contextTypes = {
    store: React.PropTypes.object
};

export default SummaryList;