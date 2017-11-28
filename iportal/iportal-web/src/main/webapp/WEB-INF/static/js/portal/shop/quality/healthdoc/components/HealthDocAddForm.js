/**
 * Created by ou on 2017/7/11.
 */
import {checkValidForm,initValidForm, errorValidMessageFunction} from '../../../../../common/validForm/actions';
import React, {PropTypes}from "react";
import {Field, FieldArray,reduxForm} from 'redux-form';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import ValidForm from '../../../../../common/validForm/components/ValidForm';


import {showFileMgrModalHasCallbackFunc} from '../../../../../common/filemgr/actions'
export const validate = (values, props) => {
    props.errorValidMessageFunction("");
    const errors = {};
    errors.healthDocList = [];
    if(values.healthDocList!=undefined&&values.healthDocList.length>0){
        values.healthDocList.forEach((healthDoc, index) => {

            const batchMsgErrors = {};
            if (!healthDoc.checkNum) {
                batchMsgErrors.checkNum =  "检查编号必填";
                errors.healthDocList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.checkNum);
                return errors;
            }
            if (!healthDoc.checkDateString) {
                batchMsgErrors.checkDateString = "检查日期必填";
                errors.healthDocList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.checkDateString);
                return errors;
            }
            if (!healthDoc.checkOrg) {
                batchMsgErrors.checkOrg = "检查机构必填";
                errors.healthDocList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.checkOrg);
                return errors;
            }
            if (!healthDoc.checkPrj) {
                batchMsgErrors.checkPrj = "检查项目必填";
                errors.healthDocList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.checkPrj);
                return errors;
            }
            if (!healthDoc.checkResult) {
                batchMsgErrors.checkResult = "检查结果必填";
                errors.healthDocList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.checkResult);
                return errors;
            }

        });

    }
    return errors;
};

export const inputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
        <input  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} style={{width:"74px"}}className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""} {...input}/>
);

export const hiddenField = ({type, input}) => (
    <input name={input.name} type={type} style={{display: "none"}} {...input} />
);

export const healthDocFieldArray=({fields})=>(
    <tbody>
    {
        fields.map((field,index)=>{
            return(<tr className="clickHere" key={index}>
                <Field component={hiddenField} name="staffHealthDocId"/>
                <td className="td-cont">
                    <div className="td-cont">{index+1}</div>
                </td>
                <td className="pink-bg">
                    <Field name={'healthDocList[' + index + '].checkNum'}  maxLength="32" component={inputField} type="text"/>
                </td>
                <td className="pink-bg">
                    <Field name={'healthDocList[' + index + '].checkDateString'} maxLength="32" inputClassName="datepicker" readOnly="readOnly"component={inputField} type="text"/>
                </td>
                <td className="pink-bg">
                    <Field name={'healthDocList[' + index + '].checkOrg'}  maxLength="32"component={inputField} type="text"/>
                </td>
                <td className="pink-bg">
                    <Field name={'healthDocList[' + index + '].checkPrj'} maxLength="32" component={inputField} type="text"/>
                </td>
                <td className="pink-bg">
                    <Field name={'healthDocList[' + index + '].checkResult'}  maxLength="32" component={inputField} type="text"/>
                </td>
                <td className="td-cont">
                    <Field name={'healthDocList[' + index + '].takeMeasures'} maxLength="32" component={inputField} type="text"/>
                </td>
                <td className="td-cont">
                    <Field name={'healthDocList[' + index + '].remark'} maxLength="32" component={inputField} type="text"/>
                </td>
            </tr>)
        })
    }
    </tbody>

);
class HealthDocAddForm extends React.Component {

    componentDidMount() {
        const change = this.props.change;
        $(".box-mc table").on("click", ".datepicker", function (e) {
            e.stopPropagation();
            const _this = $(this);
            _this.lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change(_this.attr("name"), _this.val());
                }
            });
        })
    }
    componentWillMount() {
        this.addHealthDoc()
    }

    closeAddForm(){
        this.props.actions.showAddForm(false);
        this.props.actions.setDetail({});
        this.props.reset();
        this.props.initValidForm();
    }
    addHealthDoc(){
        const {store} = this.context;
        const todos = store.getState().todos;
        const haalthDocLists =todos.page.content || [];
        const index = todos.index;
        const newHealthDoc = {
            staffHealthDocId:haalthDocLists[index].id,
            checkNum:"",
            checkDateString:"",
            checkOrg:"",
            checkPrj:"",
            checkResult:"",
            takeMeasures:"",
            remark:"",
        };
        this.props.array.push("healthDocList", newHealthDoc);
    }
    render() {
        const {handleSubmit, submitting} = this.props;
        const {store} = this.context;
        const todos = store.getState().todos;
        const haalthDocLists =todos.page.content || [];
        const index = todos.index;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage != "" && <ValidForm />}
                <div className="layer" >
                    <div className="layer-box layer-info layer-receiving layer-buying-out layer-employee-files w860">
                        <div className="layer-header">
                            <span>员工档案</span>
                            <a href="javascript:void(0);" className="close" onClick={() => {this.closeAddForm()}}></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">员工信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>员工账号</p>
                                        <span>{haalthDocLists[index].userName}</span>
                                    </div>
                                    <div className="item">
                                        <p>员工姓名</p>
                                        <span>{haalthDocLists[index].realName}</span>
                                    </div>
                                    <div className="item">
                                        <p>性别</p>
                                        <span>{haalthDocLists[index].sex}</span>
                                    </div>
                                    <div className="item">
                                        <p>出生日期</p>
                                        <span>{haalthDocLists[index].birthdayString}</span>
                                    </div>
                                    <div className="item">
                                        <p>入职日期</p>
                                        <span>{haalthDocLists[index].entryJobTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>员工状态</p>
                                        <span>{haalthDocLists[index].state="NORMAL"?"正常":"冻结"}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">档案信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={()=>{this.addHealthDoc()}}>添加</div>
                                    </div>
                                </div>
                                <div className="box-mc ">
                                    <table className="w715">
                                        <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="check-number">检查编号</th>
                                            <th className="date">检查日期</th>
                                            <th className="inspection-agencies">检查机构</th>
                                            <th className="check-project">检查项目</th>
                                            <th className="check-result">检查结果</th>
                                            <th className="take-measures">采取措施</th>
                                            <th className="note">备注</th>
                                        </tr>
                                        </thead>
                                        <FieldArray name="healthDocList" component={healthDocFieldArray} />

                                    </table>
                                </div>
                            </div>

                        </div>
                        <div className="layer-footer">
                            <button type="submit" style={{border: "none"}} className="confirm" onClick={()=>{this.props.checkValidForm(true)}} disabled={submitting}>{submitting ? <i/> : <i/>} 保存
                            </button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => {this.closeAddForm()}}>取消</a>
                        </div>
                    </div>
                </div>

            </form>
        );

    }
}

HealthDocAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
HealthDocAddForm.contextTypes =  {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,checkValidForm, showFileMgrModalHasCallbackFunc,errorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        enableReinitialize:true,
        validate: validate,
        state
    };
}
HealthDocAddForm = reduxForm({
    form: "healthDocAddForm",
})(HealthDocAddForm);

HealthDocAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(HealthDocAddForm);

export default HealthDocAddForm