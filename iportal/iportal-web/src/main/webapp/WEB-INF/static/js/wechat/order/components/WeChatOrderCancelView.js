import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import {updateOrderState, showOrderCancelView} from '../actions';

class WeChatOrderCancelView extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidMount(){
        const {store} = this.context;
        const {orderDetail} = store.getState().wechatOrderTodos;
        const showFuc = this.props.showOrderCancelView;
        const updateFuc = this.props.updateOrderState;

        $(".reason-item").on('click', function () {
            $(".reason-item").removeClass('cur');
            $(this).addClass('cur');
        });

        $('.layer').on('click', function (e) {
            if(e.target.className==='layer'){
                showFuc(false);
            }
        });

        $('.confirm').on('click', function(){
            const data = {
                id: orderDetail.id,
                orderStateCode: 'CLOSE',
                cancelOrderReason: $.trim($($('.cur')[0]).text())
            };
            updateFuc(data);
            showFuc(false);
        });
    }

    componentDidUpdate() {

    }

    render(){
        return(
            <div className="layer">
                <div className="layer-box">
                    <div className="mt">
                        <div className="rg-icon"><img src={iportal + '/static/img/wechat/combined-shape.png'}/></div>
                        <span>订单取消</span>
                    </div>
                    <p>请选择取消订单原因，帮助我们改进</p>
                    <div className="mc">
                        <div className="rs-item"><a href="javascript:void(0);" className="reason-item elli cur">我不想买了</a></div>
                        <div className="rs-item"><a href="javascript:void(0);" className="reason-item elli">买错商品</a></div>
                        <div className="rs-item"><a href="javascript:void(0);" className="reason-item elli">重复下单/误下单</a></div>
                        <div className="rs-item"><a href="javascript:void(0);" className="reason-item elli">订单信息有误</a></div>
                    </div>
                    <div className="md">
                        <a href="javascript:void(0);" onClick={() => this.props.showOrderCancelView(false)} className="close">关闭</a>
                        <a href="javascript:void(0);" className="confirm">提交</a>
                    </div>
                </div>
            </div>
        );
    }
}

WeChatOrderCancelView.propTypes = {
};

WeChatOrderCancelView.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        updateOrderState,
        showOrderCancelView
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatOrderCancelView);