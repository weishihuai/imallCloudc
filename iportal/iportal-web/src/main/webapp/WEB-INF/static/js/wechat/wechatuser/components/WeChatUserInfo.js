import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {findWeChatUserInfo,weChatUserInfoDetailData} from "../actions/index";
import BottomComponent from "../../index/components/BottomComponent"
import {WEB_NAME} from "../../../common/common-constant";

class WeChatUserInfo extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "我的";
        this.props.findWeChatUserInfo();
    }

    openWeChatUserDetailView(id) {
        this.props.weChatUserInfoDetailData(id);
    }

    render(){
        const {store} = this.context;
        const data = store.getState().weChatUserTodos.data;

        return (
            <div className="we-chat-user-info">
                <div className="mt">
                    <p className="elli">{data.nickName}</p>
                    <a href="javascript:void(0);" onClick={() => this.openWeChatUserDetailView(data.id)}>编辑资料</a>
                    <div className="pic"><img src={data.imgUrl} alt=""/></div>
                </div>
                <div className="mc">
                    <div className="item">
                        <a href="#/order-list">
                            <span>我的需求单</span>
                            <i className="elli">{data.demandTotal}笔</i>
                        </a>
                    </div>
                    <div className="item">
                        <a href="#/wechat-user-receive-addr-list">
                            <span>地址管理</span>
                            <i className="elli">{data.receiveAddressTotal}个</i>
                        </a>
                    </div>
                </div>
                <BottomComponent/>
            </div>

        );
    }
}

WeChatUserInfo.propTypes = {
};

WeChatUserInfo.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({findWeChatUserInfo,weChatUserInfoDetailData}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserInfo);