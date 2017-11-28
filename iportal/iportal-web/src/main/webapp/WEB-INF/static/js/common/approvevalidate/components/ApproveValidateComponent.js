import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux'
import {showValidateModel, doValidate} from '../actions'
import {connect} from 'react-redux'

class ApproveValidateComponent extends Component{

    componentDidMount() {
        $("input").on("focus", function () {
            $(".error-tip").hide();
        });
    }

    doValidate(){
        const userName = $("#userName").val();
        if (!userName){
            $("#user-name-error").html("输入用户名").show();
            return;
        }
        const password = $("#password").val();
        if (!password){
            $("#password-error").html("输入密码").show();
            return;
        }
        const {approveType} = this.context.store.getState().approveValidateTodos;
        this.props.doValidate(userName, password,approveType,this.props.callbackFunc);
    }

    render(){
        $(".error-tip").hide();
        const {showValidateModel, title} = this.props;
        const {store} = this.context;
        const display = store.getState().approveValidateTodos.display;
        return (
            <div className="layer" style={{"display": display ? "block" : "none", "zIndex": "999"}}>
              <div className="layer-box layer-verify w430">
                 <div className="layer-header">
                   <span>{title ? title : "验证"}</span> <a href="javascript:;" onClick={() => showValidateModel()} className="close"/>
                 </div>
            <div className="layer-body">
              <div className="item">
                <div className="mlt"><span>*</span>用户名</div>
                   <div className="mrt">
                       <input autoComplete="off" style={{"display": "inline"}} className="required" id="userName" name="userName" type="text" />
                       <div id="user-name-error" className="error-tip" style={{"color": "red", "display": "none", "height": "34px", "lineHeight": "34px", "float": "right"}}></div>
                   </div>
              </div>
               <div className="item">
                   <div className="mlt"><span>*</span>密&nbsp;&nbsp;&nbsp;码</div>
                   <div className="mrt">
                       <input autoComplete="off" style={{"display": "inline"}} className="required" id="password" name="password" type="password"/>
                       <div id="password-error" className="error-tip" style={{"color": "red", "display": "none", "height": "34px", "lineHeight": "34px", "float": "right"}}></div>
                   </div>
               </div>
            </div>
            <div className="layer-footer">
              <a href="javascript:;" onClick={() => this.doValidate()} className="confirm">确定</a>
              <a href="javascript:;" onClick={() => showValidateModel()} className="cancel">取消</a>
            </div>
              </div>
            </div>
        );
    }
}

ApproveValidateComponent.propTypes = {
    callbackFunc: PropTypes.func.isRequired
};

ApproveValidateComponent.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({showValidateModel, doValidate}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ApproveValidateComponent);