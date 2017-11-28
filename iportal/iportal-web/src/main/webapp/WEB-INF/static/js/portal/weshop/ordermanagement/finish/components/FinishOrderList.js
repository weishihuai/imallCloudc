import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import OrderList from '../../order/components/OrderList';

class FinishOrderList extends Component{
    render() {
        const {actions}=this.props;

        return(
            <div>
                <OrderList actions={actions} orderStateCode="FINISH" />
            </div>
        );
    }
}

FinishOrderList.propTypes = {
    actions: PropTypes.object.isRequired
};

FinishOrderList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FinishOrderList);