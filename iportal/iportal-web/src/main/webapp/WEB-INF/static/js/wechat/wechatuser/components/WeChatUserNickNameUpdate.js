import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {Field, reduxForm} from "redux-form";
import {validate, inputField, hiddenField} from "../../../common/redux-form-ext-frontend";
import {updateWeChatUserNickName, weChatUserInfoDetailData,updateNickNameModel} from "../actions/index";

const fields = [
    {
        field: 'id',
        validate: {
            required: true,
            fieldNm: '主键ID'
        }
    },
    {
        field: 'nickName',
        validate: {
            required: true,
            fieldNm: '昵称',
            maxlength: 32
        }
    }
];

export const nickNameField = ({ input, label, type, className, id, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={className || ''}>
        <label>
            <span>{label}</span>
            <input {...input} id={id} style={{color:"#000000"}} maxLength={maxLength || ''} type={type} placeholder={placeholder} disabled={disabled || ''} readOnly={readOnly || ''}/>
        </label>
    </div>
);

class WeChatUserNickNameUpdate extends Component{

    componentWillMount(){

    }

    render(){
        const {store} = this.context;


        const {handleSubmit, submitting, updateWeChatUserNickName} = this.props;
        return(
            <form onSubmit={handleSubmit(data => updateWeChatUserNickName(data))}>
                <div className="we-chat-user-update-layer">
                    <div className="we-chat-user-update-layer-box">
                        <div className="mc">
                            <p>用户名称</p>
                            <Field id="id" name="id" component={hiddenField}/>
                            <Field id="nickName" name="nickName" label="" placeholder="1～20个字符" type="text" component={nickNameField}/>
                        </div>
                        <div className="md">
                            <a onClick={() => this.props.updateNickNameModel(false)} className="cancel">取消</a>
                            <button type="submit" className="confirm">确定</button>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

WeChatUserNickNameUpdate.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

WeChatUserNickNameUpdate.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        initialValues: state.weChatUserTodos.data,
        state,
        fields
    };
}

WeChatUserNickNameUpdate = reduxForm({
    form: "weChatUserNickNameUpdate",
    enableReinitialize: true,
    validate
})(WeChatUserNickNameUpdate);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({updateWeChatUserNickName, weChatUserInfoDetailData,updateNickNameModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserNickNameUpdate);