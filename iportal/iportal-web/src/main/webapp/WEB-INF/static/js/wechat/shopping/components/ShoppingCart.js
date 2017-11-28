import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {getShoppingCart} from "../actions/index";
import BottomComponent from "../../index/components/BottomComponent";
import ShoppingEditComponent from "./ShoppingEditComponent";
import ShoppingListComponent from "./ShoppingListComponent";
import {WEB_NAME} from "../../../common/common-constant";

class ShoppingCart extends Component {

    componentWillMount() {
        document.title = WEB_NAME + "需求单";
        this.props.getShoppingCart();
    }

    render() {
        const {store} = this.context;
        const {isEdit, shoppingCart} = store.getState().shoppingTodos;
        return (
            <div>
                {
                    shoppingCart.cartItems.length == 0 ?
                        <div className="demand-order-main">
                            <img  src={iportal + "/static/img/wechat/zanwuxuqiudan.png"} />
                            <p>您的需求单为空</p>
                        </div>
                         :
                        isEdit ? <ShoppingEditComponent/> : <ShoppingListComponent/>
                }
                <BottomComponent/>
            </div>
        );
    }
}

ShoppingCart.propTypes = {};

ShoppingCart.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({getShoppingCart}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingCart);