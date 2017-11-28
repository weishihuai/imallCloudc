import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm,change} from "redux-form";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import {validate,inputField} from "../../../../../common/redux-form-ext";
import {showValidateModel} from '../../../../../common/approvevalidate/actions'
import ApproveValidateComponent from '../../../../../common/approvevalidate/components/ApproveValidateComponent'

const fields = [{
    field: 'newBatch',
    validate: {
        required: true,
        maxlength: 64,
        fieldNm: "新批号",
        }
},{
    field: 'newProduceDateString',
    validate: {
        required: true,
        maxlength: 64,
        fieldNm: "新生产日期",
        }
    },{
        field: 'newValidDateString',
        validate: {
            required: true,
            maxlength: 64,
            fieldNm: "新有效期",
        }
    }
];

//隐藏域
export const hiddenField = ({input,readOnly}) => (
    <input type="text" name={input.name} style={{display: "none"}} readOnly={readOnly} {...input}/>
);

class GoodsBatchUpdateForm extends Component {
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.approveValidateData(null);
    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        $("#newProduceDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("newProduceDateString", $('input[name="newProduceDateString"]').val());
                }
            });
        });

        $("#newValidDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("newValidDateString", $('input[name="newValidDateString"]').val());
                }
            });
        });

    }

    submit(data) {
        const {store} = this.context;
        const {params} = store.getState().todos;

        let start = new Date(data.newProduceDateString);
        let end = new Date(data.newValidDateString);
        if(end < start){
            this.props.errorValidMessageFunction("有效期必须在生产日期之后");
            return ;
        }

        if(!data.approveDataId){
            this.props.errorValidMessageFunction("请审核");
            return;
        }
        return this.props.actions.goodsBatchUpdateData(data,params);
    }

    approveValidate(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.approveValidateData(data);
    }

    render() {
        const {store} = this.context;
        const {data} = store.getState().todos;
        const {actions} = this.props;
        const {handleSubmit, submitting} = this.props;
        const {validFormState, errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const {approveData} = this.context.store.getState().todos;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                <Field name="approveDataId" component={hiddenField}/>
                <div className="layer">
                    <div className="layer-box layer-info w960">
                        <div className="layer-header">
                            <span>修改商品批号</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.goodsBatchEditModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">批号信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>原批号</p>
                                        <span>{data.batch}</span>
                                    </div>
                                    <Field name="newBatch" component={inputField} label="新批号" required="required" type="text"  maxLength="64"/>
                                    <div className="item">
                                        <p>原生产日期</p>
                                        <span>{data.produceDateString}</span>
                                    </div>
                                    <Field id="newProduceDateString" name="newProduceDateString" inputClassName="form-control datepicker" required="required" type="text" component={inputField} label="新生产日期" readOnly/>
                                    <div className="item">
                                        <p>原有效期</p>
                                        <span>{data.validDateString}</span>
                                    </div>
                                    <Field id="newValidDateString" name="newValidDateString" inputClassName="form-control datepicker" required="required" type="text" component={inputField} label="新有效期" readOnly/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>商品编码</p>
                                        <span>{data.goodsCode}</span>
                                    </div>
                                    <div className="item">
                                        <p>商品名称</p>
                                        <span>{data.goodsNm}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        {approveData !== null ? <p style={{color:"red"}}>{approveData.realName}</p>:<a href="javascript:void(0);" className="review" onClick={()=>this.props.showValidateModel()}>点击审核</a>}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={(e) => actions.goodsBatchEditModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

GoodsBatchUpdateForm.contextTypes = {
    store: React.PropTypes.object
};

GoodsBatchUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

GoodsBatchUpdateForm = reduxForm({
    form: "goodsBatchUpdateForm",
    enableReinitialize: true,
    validate
})(GoodsBatchUpdateForm);


function mapStateToProps(state) {
    const {data} = state.todos;
    return {
        initialValues:Object.assign({}, data,{
            newBatch:data.batch,
            newProduceDateString:data.produceDateString,
            newValidDateString:data.validDateString
        }),
        fields: fields,
        validate:validate,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction, showValidateModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsBatchUpdateForm);
