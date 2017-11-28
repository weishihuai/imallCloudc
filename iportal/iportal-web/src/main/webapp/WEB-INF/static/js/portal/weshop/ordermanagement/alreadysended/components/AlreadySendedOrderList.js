import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import OrderList from '../../order/components/OrderList';

class AlreadySendedOrderList extends Component{
    render() {
        const {actions}=this.props;

        return(
            <div>
                <OrderList actions={actions} orderStateCode="ALREADY_SENDED" />
            </div>
        );
    }
}

AlreadySendedOrderList.propTypes = {
    actions: PropTypes.object.isRequired
};

AlreadySendedOrderList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(AlreadySendedOrderList);