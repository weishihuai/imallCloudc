import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, selectField, validate} from "../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import {showOrderSelectWin} from "../../../../../common/orderselectwin/actions";
import {ERROR_ID_CARD, ERROR_PHONE_FORMAT, REGEXP_ID_CARD, REGEXP_PHONE} from "../../../../../common/common-constant";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import CommonOrderList from "../../../../../common/orderselectwin/components/CommonOrderList";

export const fields = [
    {
        field:'sellOrderCode',
        validate:{
            fieldNm: "订单信息",
            maxlength:32,
            required:true
        }
    },
    {
        field:'memberCardNum',
        validate:{
            fieldNm: "会员卡号码",
            maxlength:32,
            required:false
        }
    },
    {
        field:'patientNm',
        validate:{
            fieldNm: "患者名称",
            maxlength:32,
            required:true
        }
    },
    {
        field:'identityCard',
        validate:{
            fieldNm: "身份证",
            maxlength:32,
            required:true,
            regx: REGEXP_ID_CARD,
            error: ERROR_ID_CARD
        }
    },
    {
        field:'mobile',
        validate:{
            fieldNm: "手机号",
            maxlength:32,
            regx: REGEXP_PHONE,
            error: ERROR_PHONE_FORMAT
        }
    },
    {
        field:'addr',
        validate:{
            fieldNm: "地址",
            maxlength:128
        }
    },
    {
        field:'remark',
        validate:{
            fieldNm: "备注",
            maxlength:128
        }
    }
];

class LimitBuyRegisterAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    let registerDateString = $("#registerDateString").val().trim();
                    change('registerDateString', registerDateString);
                }
            });
        });
    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    openOrderSelectWin(){
        this.props.showOrderSelectWin('Y', '');
    }

    selectOrderCallBack(order){
        $("#orderId").val(order.id);
        $("#sellOrderCode").val(order.orderNum);
        $("#memberCardNum").val(order.memberCardNum);

        const {change} = this.props;
        change('orderId', order.id);
        change('sellOrderCode', order.orderNum);
        change('memberCardNum', order.memberCardNum);

        this.props.actions.queryOrderItem(order);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {isShowOrderWin} = this.context.store.getState().orderTodos;
        let sexCode = [{code: 'MALE', name : '男'}, {code: 'FEMALE', name : '女'}, {code: 'SECRET', name : '保密'}];
        const items = store.getState().todos.items || [];

        return(
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {isShowOrderWin && <CommonOrderList callback={(order)=>this.selectOrderCallBack(order)} actions={actions} isSingle={true}/>}
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <input type="hidden" name="orderId" id="orderId"/>
                <div className="layer">
                    <div className="layer-box layer-info layer-receiving layer-buying-out w960">
                        <div className="layer-header">
                            <span>添加限购登记</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showAddForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={this.openOrderSelectWin.bind(this)}>选择订单</div>
                                    </div>
                                </div>
                                <div className="box-mc clearfix">
                                    <Field id="sellOrderCode" name="sellOrderCode"  type="text" component={inputField} label="销售订单编号" required="required" disabled="disabled" />
                                    <Field id="memberCardNum" name="memberCardNum"  type="text" component={inputField} label="会员卡号码" disabled="disabled" />
                                    <Field id="patientNm" name="patientNm"  type="text" component={inputField} label="患者名称" required="required"/>
                                    <Field id="identityCard" name="identityCard"  type="text" component={inputField} label="身份证" required="required"/>
                                    <Field id="sexCode" name="sexCode"  type="text" component={selectField} label="性别" optionValue="code" optionName="name" items={sexCode}/>
                                    <Field id="mobile" name="mobile"  type="text" component={inputField} label="手机"/>
                                    <Field id="addr" name="addr"  type="text" component={inputField} label="地址"/>
                                    <Field id="registerDateString" name="registerDateString"  type="text" component={inputField} label="登记日期" inputClassName="datepicker" readOnly="readOnly" />
                                    <Field id="remark" name="remark"  type="text" component={inputField} label="备注"/>
                                </div>
                                {
                                    items.length>0 &&
                                    <div className="md-box">
                                        <div className="box-mt">商品信息</div>
                                        <div className="box-mc">
                                            <table className="w860">
                                                <thead>
                                                <tr>
                                                    <th className="serial-number">序号</th>
                                                    <th className="commodity-code">商品编码</th>
                                                    <th className="commodity-name">商品名称</th>
                                                    <th className="general-name">通用名称</th>
                                                    <th className="specifications">规格</th>
                                                    <th className="dosage-form">剂型</th>
                                                    <th className="unit">单位</th>
                                                    <th className="manufacturers">生产厂商</th>
                                                    <th className="approval-number">批准文号</th>
                                                    <th className="origin">产地</th>
                                                    <th className="number">数量</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {
                                                    items.map((item, index)=>{
                                                        return(
                                                            <tr key={index}>
                                                                <td>
                                                                    <div className="td-cont">{index + 1}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.goodsCoding}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.goodsNm}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.commonNm}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.spec}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.dosageForm}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.unit}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.produceManufacturer}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.approvalNumber}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.productionPlace}</div>
                                                                </td>
                                                                <td>
                                                                    <div className="td-cont">{item.quantity}</div>
                                                                </td>
                                                            </tr>
                                                        );
                                                    })
                                                }
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                }
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.valid()}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.showAddForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

LimitBuyRegisterAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

LimitBuyRegisterAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showOrderSelectWin
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    }
}

LimitBuyRegisterAddForm = reduxForm({
    form: 'limitBuyRegisterAddForm'
})(LimitBuyRegisterAddForm);

LimitBuyRegisterAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(LimitBuyRegisterAddForm);

export default LimitBuyRegisterAddForm;
