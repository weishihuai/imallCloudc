import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setScrollTop} from "../actions";
import {getShoppingCart} from "../../shopping/actions";

class BottomComponent extends Component{

    componentWillMount(){
        this.props.getShoppingCart();
    }

    componentDidUpdate(){
        const shoppingTodos = this.context.store.getState().shoppingTodos;
        if(shoppingTodos){
            let cartItems = shoppingTodos.shoppingCart.cartItems || [];
            let count = 0;
            cartItems.map(item => {
                count += parseInt(item.quantity);
            });
            $(".cart-num").html(count);
        }
    }

    toHash(hash){
        this.props.setScrollTop(document.body.scrollTop);
        window.location.hash = hash;
    }

    render(){
        let hash = window.location.hash;
        return(
            <div className="bottom-bar">
                <div className="bar-idx"><a href="#" className={hash == '#/' ? 'cur' : ''}>
                    <div className="pic"></div>
                    <span>首页</span>
                </a></div>
                <div className="bar-demand"><a onClick={() => this.toHash('#/shopping-list')} className={hash == '#/shopping-list' ? 'cur' : ''}>
                    <div className="pic"><span className="cart-num"></span></div>
                    <span>需求单</span>
                </a></div>
                <div className="bar-rec"><a onClick={() => this.toHash('#/wechat-user-info')} className={hash == '#/wechat-user-info' ? 'cur' : ''}>
                    <div className="pic"></div>
                    <span>我的</span>
                </a></div>
            </div>
        );
    }
}

BottomComponent.propTypes = {
};

BottomComponent.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setScrollTop, getShoppingCart}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(BottomComponent);