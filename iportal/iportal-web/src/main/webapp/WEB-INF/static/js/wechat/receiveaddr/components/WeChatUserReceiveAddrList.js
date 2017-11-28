import React, {Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {getReceiveAddrList} from "../actions/index";
import {WEB_NAME} from "../../../common/common-constant";

class WeChatUserReceiveAddrList extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "地址管理";
        this.props.getReceiveAddrList();
    }

    render(){
        const {store} = this.context;
        const receiveAddrList = store.getState().wechatUserReceiveAddrTodos.receiveAddrList;
        return(
            <div className="main" style={{paddingTop:"0px"}}>
                <div className="mc">
                    {receiveAddrList.map((addr, index) => {
                        return(
                            <div className="item" key={index}>
                                <p style={{lineHeight: "inherit", height: "auto", marginBottom: "0.5rem"}}>{addr.deliveryAddr + addr.detailAddr}</p>
                                <span>{addr.receiverName}</span>
                                <span>{addr.contactTel}</span>
                                <a style={{height: "0.625rem", width: "0.625rem", float: "left", top: "initial", marginTop: "initial"}} href={"#/wechat-user-receive-addr-update/" + addr.id} className="compile"/>
                            </div>
                        );
                    })}
                </div>
                <div className="check-btn-auto">
                    <div className="check-btn-total">
                        <a href="#/wechat-user-receive-addr-add" className="add-btn">新增收货地址</a>
                    </div>
                </div>
            </div>
        );
    }
}

WeChatUserReceiveAddrList.propTypes = {
};

WeChatUserReceiveAddrList.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({getReceiveAddrList}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserReceiveAddrList);