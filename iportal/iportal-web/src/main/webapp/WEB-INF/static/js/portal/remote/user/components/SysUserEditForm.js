/**
 * Created by ou on 2017/7/11.
 */
import {checkValidForm,initValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from '../../../../common/validForm/actions';
import React, {PropTypes}from "react";
import {Field, reduxForm, change} from 'redux-form';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {validate,inputField,selectField,boolRadioField} from '../../../../common/redux-form-ext'
import ValidForm from '../../../../common/validForm/components/ValidForm';
import * as types from "../constants/ActionTypes";
import * as validMessage from '../../../../common/common-constant'
export const fields = [
    {
    field: 'realName',
    validate: {
        fieldNm: "姓名",
        required: true,
        maxLength: 32
    }
},
    {
    field: 'mobile',
    validate: {
        fieldNm: "手机号",
        regx:validMessage.REGEXP_PHONE,
        error:validMessage.ERROR_PHONE_FORMAT,

    }
},
    {
    field: 'identityCard',
    validate: {
        fieldNm: "身份证",
        regx:validMessage.REGEXP_ID_CARD,
        error:validMessage.ERROR_ID_CARD,
    }
},
    {
    field: 'email',
    validate: {
        fieldNm: "邮箱",
        regx:validMessage.REGEXP_EMAIL,
        error:validMessage.ERROR_EMAIL_FORMAT,
        maxLength: 32
    }
},
    {
    field: 'jobSelectList',
    validate: {
        fieldNm: "岗位",
        required: true,

    }
}
 ];
export const sexItems=[
    {
        name:"男",
        value:"MALE"
    },
    {
        name:"女",
        value:"FEMALE"
    },
    {
        name:"保密",
        value:"SECRET"
    },
];
export const marriageStatItems=[
    {
        name:"未婚",
        value:"SINGLE"
    },
    {
        name:"已婚",
        value:"UNMARRIED"
    },
    {
        name:"离异",
        value:"DIVORCED"
    },  {
        name:"保密",
        value:"SECRECY"
    },
];





// 岗位
export const businessCategoryRadioField = ({  input, selectedItems, allItems, changeEven = () => {} }) => {
    return (<div className="item w860">
        {
            allItems.map((allItem, index) => {
                if (selectedItems.includes(allItem.id)) {
                    return (
                        <label key={index}>
                            <input checked="checked" name={input.name} value={allItem.id}
                                   onChange={changeEven} type="checkbox"/>
                            {allItem.jobName}
                        </label>
                    );

                } else {
                    return (<label key={index}>
                            <input value={allItem.id} name={input.name} onChange={changeEven}
                                   type="checkbox"/>
                            {allItem.jobName}
                        </label>
                    );
                }

            })
        }
    </div>);
}

export const hiddenField = ({type, input}) => (
    <input name={input.name} type={type} style={{display: "none"}}  />
)


class SysUserEditForm extends React.Component {

    componentWillMount() {
        this.props.actions.findByAvailableAndDictType();
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    let target = e.target.name;
                    change(target,  $($("input[name=" + target + "]")[0]).val().trim());
                }
            });
        });
    }

    businessCategoryChangeEven(e){
        const {jobSelectList} = this.context.store.getState().todos;
        var $target = $(e.currentTarget);
        var newCategoryItemSelect = $target.val();
        var newAllJobsSelects =[];
        if($target.is(":checked")) {
            newAllJobsSelects = jobSelectList.concat(newCategoryItemSelect);
        } else {
            newAllJobsSelects = jobSelectList.filter(item => item != newCategoryItemSelect);
        }
        const {change} = this.props;
        change("jobSelectList", newAllJobsSelects);
        this.context.store.dispatch({
            type: types.SYS_USER_SET_ALL_JOBS,
            jobSelectList:newAllJobsSelects

        });

    }




    getSupCerFileDateAndValid(){
        this.props.checkValidForm(true);

    }

    closeForm(){
        this.props.actions.showUpdate(false);

        this.props.reset();
        this.props.initValidForm();
    }


    render() {
        const props = this.props;
        const actions = this.props.actions;
        const {handleSubmit, submitting} = this.props;
        const {store} = this.context;
        const todos = store.getState().todos;
        const detailObject = todos.detailObject;
        const selectedItems = todos.jobSelectList || [];
        const allJobs = todos.allJobs || [];
        const {errorValidMessage, validFormState,asyncValidMessage} = this.context.store.getState().validTodos;
        const messageState = errorValidMessage  || asyncValidMessage;
        return (
            <form onSubmit={handleSubmit}>
                {validFormState && messageState && <ValidForm />}
                <div className="layer" >
                    <div className="layer-box layer-info layer-order layer-staff-management w960">
                        <div className="layer-header">
                            <span>员工档案</span>
                            <a href="javascript:void(0);" className="close" onClick={() => {this.closeForm()}}></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                            <div className="box-mt">基本信息</div>
                            <div className="box-mc clearfix">
                                <Field name="id" type="text" component={hiddenField}/>
                                <Field name="realName" component={inputField} label="姓名" required="required" maxLength="32" type="text" />
                                <Field name="mobile" component={inputField} label="手机号"  maxLength="32" type="text" />
                                <Field name="identityCard" component={inputField} label="身份证"  maxLength="32" type="text" />
                                <Field name="sex" component={selectField} optionName="name" optionValue="value" items={sexItems} label="性别"   type="text"  />
                                <Field name="nativePlace" component={inputField} label="籍贯"  maxLength="32" type="text" />
                                <Field name="marriageStatCode" component={selectField} label="婚姻状况" optionName="name" optionValue="value" items={marriageStatItems}   maxLength="32" type="text" />
                                <Field name="birthday" component={inputField} inputClassName="form-control datepicker" readOnly="readOnly" label="出生日期"  maxLength="32" type="text" />
                                <Field name="email" component={inputField} label="邮箱"  maxLength="32" type="text" />
                                <Field name="mark" component={inputField} label="备注"  maxLength="512" type="text" />
                                <Field name="homeAddr" component={inputField} label="住址" className="item-1-2"  maxLength="128" type="text" />
                            </div>
                        </div>
                            <div className="md-box">
                                <div className="box-mt">教育经历</div>
                                <div className="box-mc clearfix">
                                    <Field name="schoolNm" component={inputField} label="学校名称"   maxLength="32" type="text" />
                                    <Field name="major" component={inputField} label="专业"  maxLength="32" type="text" />
                                    <Field name="academicQualificati" component={inputField} label="学历"  maxLength="32" type="text" />
                                    <Field name="graduationTime" component={inputField} inputClassName="form-control datepicker" readOnly="readOnly" label="毕业时间"  maxLength="32" type="text" />
                                    <Field name="joinWorkTime" component={inputField} inputClassName="form-control datepicker" readOnly="readOnly" label="参加工作时间"  maxLength="32" type="text" />
                                    <Field name="technologyPostTitle" component={inputField} label="技术职称"  maxLength="32" type="text" />
                                </div>
                            </div>

                            <div className="md-box">
                                <div className="box-mt">岗位</div>
                                <div className="box-mc clearfix">
                                    <Field label="岗位" name="jobSelectList" required="required" selectedItems={selectedItems} component={businessCategoryRadioField} changeEven={(e) => {this.businessCategoryChangeEven(e)}}  allItems={allJobs}/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">行政信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="entryJobTime" component={inputField} inputClassName="form-control datepicker" readOnly="readOnly" label="入职时间"  maxLength="32" type="text" />
                                    <Field name="isPostsTrain" component={boolRadioField} label="上岗培训"  />
                                    <Field name="leaveTime" component={inputField} inputClassName="form-control datepicker" readOnly="readOnly" label="离职时间"  maxLength="32" type="text" />
                                    <Field name="leaveReason" component={inputField} label="离职原因"  maxLength="128" type="text" />
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">账号与密码</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>账号</p>
                                        <span>{detailObject.userName}</span>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div className="layer-footer">
                            <button type="submit" style={{border: "none"}} className="confirm" onClick={() => {this.getSupCerFileDateAndValid()}} disabled={submitting}>{submitting ? <i/> : <i/>} 保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => {this.closeForm()}}>取消</a>
                        </div>
                    </div>
                </div>

            </form>
        );

    }
}

SysUserEditForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SysUserEditForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,checkValidForm,errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        initialValues:state.todos.detailObject,
        enableReinitialize:true,
        validate: validate,
        state
    };
}
SysUserEditForm = reduxForm({
    form: "sysUserEditForm",
    enableReinitialize: true,
})(SysUserEditForm);

SysUserEditForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SysUserEditForm);

export default SysUserEditForm