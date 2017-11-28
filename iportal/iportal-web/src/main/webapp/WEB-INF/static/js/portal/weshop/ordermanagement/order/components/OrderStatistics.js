import React, {Component, PropTypes} from "react";

class OrderStatistics extends Component{
    componentWillMount() {
        this.props.actions.getOrderStatistics();
    }

    render() {
        const {store} = this.context;
        const {orderStatistics} = store.getState().orderTodos;

        return (
            orderStatistics &&
            <div className="lt-cont lt-cont-f">
                <div className="lt-cont-f-inner">
                    <div className="lt-item lt-item-first">
                        <p>{'近' + orderStatistics.statisticsDays + '天下单笔数'}</p>
                        <span>{orderStatistics.orderQuantity}</span>
                    </div>
                    <div className="lt-item">
                        <p>{'近' + orderStatistics.statisticsDays + '天成交金额'}</p>
                        <span>{orderStatistics.totalAmount}</span>
                    </div>
                    <div className="lt-item">
                        <p>待发货 ></p>
                        <span>{orderStatistics.waitSendQuantity}</span>
                    </div>
                    <div className="lt-item">
                        <p>已发货 ></p>
                        <span>{orderStatistics.alreadySendedQuantity}</span>
                    </div>
                </div>
            </div>
        );
    }
}

OrderStatistics.propTypes = {
    actions: PropTypes.object.isRequired
};

OrderStatistics.contextTypes = {
    store: React.PropTypes.object
};

export default OrderStatistics;