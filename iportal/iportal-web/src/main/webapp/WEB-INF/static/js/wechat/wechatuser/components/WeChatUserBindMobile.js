import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {bindMobile} from "../actions/index";
import {getLoginUserInfo, sendNormalValidateCode} from "../../../common/common-frontend-actions";

class WeChatUserBindMobile extends Component{

    componentWillMount(){
        this.props.getLoginUserInfo(data => {
            $("#mobile").val(data.mobile);
            $("#id").val(data.id);
        });
    }

    getValidateCode(e){
        let mobile = $("#mobile").val();
        const {sendNormalValidateCode} = this.props;
        if(mobile && mobile.length == 11){
            sendNormalValidateCode(mobile, success => {
                if (success){
                    let count = 60;
                    $("#get").hide();
                    $("#count").html("重新获取"+ count +"s").show();
                    let interval = setInterval(function () {
                        count--;
                       $("#count").html("重新获取"+ count +"s");
                        if (count == 0){
                            $("#count").hide();
                            $("#get").show();
                            clearInterval(interval);
                        }
                    }, 1000);
                }
            })
        }
    }

    bindMobile(){
        let mobile = $("#mobile").val().trim();
        if(!mobile){
            return false;
        }
        if (mobile.length != 11){
            showError("请输入正确的手机号");
        }
        let validateCode = $("#validateCode").val().trim();
        if(!validateCode){
            return false;
        }
        this.props.bindMobile($("#id").val(), mobile, validateCode);
    }

    onInput(e, len){
        let val = e.target.value;
        if(val.length> len){
            e.target.value = val.slice(0, len)
        }
    }

    render(){
        return (
            <div className="main-bind">
                <div className="mc">
                    <div className="item">
                        <a href="javascript:;">
                            <span>手机号</span>
                            <input autoComplete="off" onInput={e => this.onInput(e, 11)} id="mobile" type="number" placeholder="请输入手机号码"/>
                            <input autoComplete="off"  id="id" type="hidden"/>
                        </a>
                    </div>
                    <div className="item">
                        <a href="javascript:;">
                            <span>验证码</span>
                            <input autoComplete="off" id="validateCode" onInput={e => this.onInput(e, 6)} type="number" placeholder="请输入验证码" style={{width: "3.4375rem"}}/>
                                <a style={{marginLeft: "0.1562rem"}} id="get" onClick={() => this.getValidateCode()} href="javascript:;">获取验证码</a>
                                <a style={{display: "none", marginLeft: "0.1562rem"}} id="count" href="javascript:;" className="again"></a>
                        </a>
                    </div>
                </div>
                <a onClick={() => this.bindMobile()} href="javascript:;" className="bd-btn">确认绑定</a>
            </div>
        );
    }
}

WeChatUserBindMobile.propTypes = {
};

WeChatUserBindMobile.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({getLoginUserInfo, sendNormalValidateCode, bindMobile}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserBindMobile);