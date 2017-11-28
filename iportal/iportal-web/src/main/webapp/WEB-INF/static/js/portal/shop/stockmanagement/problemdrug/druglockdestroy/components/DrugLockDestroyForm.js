import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';
import ApproveValidateComponent from '../../../../../../common/approvevalidate/components/ApproveValidateComponent';
import {checkValidForm,errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {inputField} from '../../../../../../common/redux-form-ext';

export const validate = (values, props) => {
    const errors = {};
    if(!values.approveManId){
        errors.approveManId = "请进行审批员审核";
        props.errorValidMessageFunction(errors.approveManId);
        return errors;
    }

    if(!values.reviewerId){
        errors.reviewerId = "请进行复核员审核";
        props.errorValidMessageFunction(errors.reviewerId);
        return errors;
    }
    props.errorValidMessageFunction("");
    return errors;
};

const fields = [{
        field: 'goodsCode',
        validate: {
            required: false,
            fieldNm: "商品编号",
            }
    }, {
        field: 'batch',
        validate: {
            required: false,
            fieldNm: "批号",
        }
    }, {
        field: 'supplierNm',
        validate: {
            required: false,
            fieldNm: "供应商名称",
        }
    }, {
        field: 'storageSpaceNm',
        validate: {
            required: false,
            fieldNm: "货位",
            maxlength:32,
        }
    }, {
        field: 'lockQuantity',
        validate: {
            required: false,
            fieldNm: "数量",
            maxlength:32,
        }
    },{
        field: 'destroyTimeString',
        validate: {
            required: false,
            fieldNm: "销毁时间",
        }
    }, {
        field: 'destroyPlace',
        validate: {
            required: false,
            fieldNm: "销毁地点 ",
            maxlength:32,
        }
    },{
        field: 'destroyMan',
        validate: {
            required: false,
            fieldNm: "销毁人",
            maxlength:32,
        }
    },{
        field: 'keeper',
        validate: {
            required: false,
            maxlength:32,
            fieldNm: "保管员",
        }
    },{
        field: 'destroyReason',
        validate: {
            required: false,
            fieldNm: "销毁原因",
            maxlength:128
        }
    }
];

/*隐藏域*/
export const hiddenField = ({type,input}) => (
    <input name={input.name} type={type} style={{display:"none"}} {...input} />
);

class DrugLockDestroyForm extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.findCurrentLoginUserMessage();
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        const {change} = this.props;
        $("#destroyTimeString").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("destroyTimeString", $('input[name="destroyTimeString"]').val());
                }
            });
        });
    }

    approveValidate(data){
        const {approveUserNames} = this.context.store.getState().todos;
        const {setApproveNames} = this.props.actions;
        const {change} = this.props;
        if (data.approveType && data.approveType === "approveMan") {
            change("approveManId",data.id);
            setApproveNames(Object.assign({},approveUserNames,{
                approveManName:data.realName
            }));
        }

        if (data.approveType && data.approveType === "reviewer") {
            change("reviewerId",data.id);
            setApproveNames(Object.assign({},approveUserNames,{
                reviewerManName:data.realName
            }));
        }
    }

    resetAndClose(){
        this.props.actions.drugLockDestroyModel(false);
        this.props.actions.setApproveNames({});
    }

    render() {
        const { handleSubmit, submitting,showValidateModel} = this.props;
        const {approveUserNames} = this.context.store.getState().todos || {};
        const approveManName = approveUserNames.approveManName || "";
        const reviewerManName = approveUserNames.reviewerManName || "";
        const {validFormState, errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                <div className="layer">
                    <div className="layer-box layer-info layer-destroy w860">
                        <div className="layer-header">
                            <span>药品销毁</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.resetAndClose(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <Field id="goodsCode" name="goodsCode" type="text" component={inputField} label="商品编号" disabled="disabled" readOnly/>
                                    <Field id="batch" name="batch" type="text" component={inputField} label="批号" readOnly disabled="disabled"/>
                                    <Field id="supplierNm" name="supplierNm" type="text" component={inputField} label="供应商名称" readOnly disabled="disabled"/>
                                    <Field id="storageSpaceNm" name="storageSpaceNm" type="text" component={inputField} label="货位" readOnly disabled="disabled"/>
                                    <Field id="lockQuantity" name="lockQuantity" type="text" component={inputField} label="数量" readOnly disabled="disabled"/>
                                    <Field id="destroyTimeString" name="destroyTimeString" inputClassName="form-control datepicker" type="text" component={inputField} label="销毁时间" readOnly/>
                                    <Field id="destroyPlace" name="destroyPlace" type="text" component={inputField} label="销毁地点"/>
                                    <Field id="destroyMan" name="destroyMan" type="text"  component={inputField} label="销毁人" disabled="disabled" readOnly/>
                                    <Field id="keeper" name="keeper" type="text" component={inputField} label="保管员"/>
                                    <div className="item">
                                        <p><i>*</i>审批员</p>
                                        {approveManName !== "" ? <p style={{color:"red"}}>{approveManName}</p> : <a href="javascript:void(0);" className="review" onClick={(e)=>{e.preventDefault();showValidateModel("approveMan")}}>点击审核</a>}
                                    </div>
                                    <Field  name="approveManId" type="text" component={hiddenField}/>
                                    <div className="item">
                                        <p><i>*</i>复核员</p>
                                        {reviewerManName !== "" ? <p style={{color:"red"}}>{reviewerManName}</p> : <a href="javascript:void(0);" className="review" onClick={(e)=>{e.preventDefault();showValidateModel("reviewer")}}>点击审核</a>}
                                    </div>
                                    <Field  name="reviewerId" type="text" component={hiddenField}/>
                                    <Field id="destroyReason" name="destroyReason" type="text" component={inputField} label="销毁原因"/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.resetAndClose(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

DrugLockDestroyForm.contextTypes = {
    store: React.PropTypes.object
};

DrugLockDestroyForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugLockDestroyForm = reduxForm({
    form: "drugLockDestroyForm",
    enableReinitialize: true,
})(DrugLockDestroyForm);


function mapStateToProps(state) {
    return {
        initialValues: Object.assign({},state.todos.data,{
            destroyMan:state.todos.loginUserMessage.realName
        }),
        fields: fields,
        state,
        validate
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({showValidateModel,checkValidForm,errorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugLockDestroyForm);