import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {Field, Fields, FieldArray, reduxForm} from "redux-form";
import {inputField} from "../../../../../common/redux-form-ext";
import {showPurchaseReceive, savePurchaseReceive} from '../actions'
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {getLoginUserInfo} from "../../../../../common/common-actions";
import {LONG_REG} from "../../../../../common/common-constant";

export const validate = (values, props) => {
    const errors = {};
    if(values.receiver && values.receiver.length > 32){
        props.errorValidMessageFunction("收货人不能大于32个字符");
        errors.reveiver = "收货人不能大于32个字符";
        return errors;
    }

    const itemList = values.itemList;
    if(itemList.length == 0){
        props.errorValidMessageFunction("没有可以收货的商品");
        errors.reveiver = "没有可以收货的商品";
        return errors;
    }
    errors.itemList = [];
    for (const i in itemList){
        const item = itemList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";
        if (!item.receiveQuantity){
            errorStr = str + "请输入收货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {receiveQuantity: errorStr};
            return errors;
        }
        if (!LONG_REG.test(item.receiveQuantity)){
            errorStr = str + "请输入正确的收货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {receiveQuantity: errorStr};
            return errors;
        }
        if(item.rejectionReason && item.rejectionReason.length > 128){
            props.errorValidMessageFunction("拒收原因不能大于128个字符");
            errors.itemList[i] = {rejectionReason: "拒收原因不能大于128个字符"};
            return errors;
        }
        if(item.receiveQuantity > item.purchaseQuantity){
            errorStr = str + "收货数量不能大于采购数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {receiveQuantity: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const hiddenField = ({ input, type, id, readOnly, meta: { touched, error } }) => (
    <input id={id} readOnly={readOnly ? readOnly : ''} type={type} {...input} />
);

const tdField = ({ input, type, id, readOnly,maxLength, meta: { touched, error } }) => (
    <input autoComplete="off" id={id} readOnly={readOnly ? readOnly : ''} className='td-cont' type={type} {...input}  maxLength={maxLength?maxLength:""}/>
);

const fieldArray = ({ fields, itemVoList, meta: { touched, error } }) => (
    <tbody>
    {
        fields.map((item, index) =>
            <tr key={index}>
                <td>
                    <input className="td-cont goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={itemVoList[index].goodsId}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <td className="pink-bg">
                    <Field id={"itemList[" + index + '].receiveQuantity'} name={"itemList[" + index + '].receiveQuantity'} type="text" component={tdField} />
                </td>
                <td>
                    <Field id={"itemList[" + index + '].rejectionReason'} name={"itemList[" + index + '].rejectionReason'} type="text" component={tdField} maxLength="128"/>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].purchaseQuantity}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].purchaseUnitPrice}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].goodsCode}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].goodsNm}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].commonNm}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].spec}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].dosageForm}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].unit}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].produceManufacturer}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].approvalNumber}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].productionPlace}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].retailPrice}</div>
                </td>
                <td>
                    <div className="td-cont">{itemVoList[index].purchaseTaxRate}</div>
                </td>
            </tr>
        )
    }
    </tbody>
);

class PurchaseReceiveForm extends Component {

    componentWillMount(){
        const {change} = this.props;
        this.props.getLoginUserInfo((data) => {
            change("receiver", data.realName);
        });
    }

    componentDidMount() {
        const {change} = this.props;
        $(".purchase-receive .datepicker").on("click",function(e){
            e.stopPropagation();
            const _this = $(this);
            _this.lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change(_this.attr("name"), _this.val());
                }
            });
        });

        $(".purchase-receive input.check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".goods-checkbox").each(function () {
                  $(this)[0].checked = checked;
            });
        });
        $(".purchase-receive .goods-checkbox").click(function () {
            $("input.check-all")[0].checked = $(".goods-checkbox").length == $(".goods-checkbox:checked").length
        });
    }

    componentWillUnmount(){
        $(".purchase-receive").unbind();
    }

    removeGoods(){
        let goodsIdArr = [];
        $(".goods-checkbox:checked").each(function () {
            goodsIdArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if (goodsIdArr.length != 0){
            const {store} = this.context;
            const itemList = store.getState().form.purchaseReceiveForm.values.itemList;
            this.props.array.removeAll("itemList");
            itemList.map((item, index) => {
                if($.inArray(item.goodsId, goodsIdArr) < 0){
                    this.props.array.push("itemList", item);
                }
            });
            $("input.check-all")[0].checked = false;
        }
    }

    render() {
        const {handleSubmit, submitting, checkValidForm, showPurchaseReceive, savePurchaseReceive, callback} = this.props;
        const {store} = this.context;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const itemVoList = store.getState().form.purchaseReceiveForm.values.itemList;
        return (
            <form onSubmit={handleSubmit(data => savePurchaseReceive(data, callback))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
               <div className="layer purchase-receive">
                <div className="layer-box layer-info layer-receiving w1290">
                    <div className="layer-header">
                        <span>采购收货</span>
                        <a onClick={() => showPurchaseReceive(null)} href="javascript:;" className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">收货信息</div>
                            <div className="box-mc clearfix">
                                <Field className="item" id="purchaseOrderId" name="purchaseOrderId" type="hidden" component={hiddenField} label="采购订单ID" />
                                <Field className="item" id="receiver" name="receiver" type="text" component={inputField} label="收货人" />
                                <Field className="item" inputClassName="datepicker" id="receiveTimeString" name="receiveTimeString" type="text" readOnly="readOnly" component={inputField} label="收货时间" />
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <div onClick={() => this.removeGoods()} className="item-del">删除商品</div>
                                </div>
                            </div>
                            <div className="box-mc">
                                <table>
                                    <thead>
                                    <tr>
                                        <th style={{width: "25px"}} className="th-checkbox"><label><input style={{width: "inherit"}} className="check-all" type="checkbox" value="checkbox"/></label></th>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
                                        <th className="number">收货数量</th>
                                        <th className="why">拒收原因</th>
                                        <th className="number">数量</th>
                                        <th className="price">采购单价（元）</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="price">零售价（元）</th>
                                        <th className="purchase-rate">采购税率</th>
                                    </tr>
                                    </thead>
                                    <FieldArray name="itemList" itemVoList={itemVoList} component={fieldArray}/>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => checkValidForm(true)}>{submitting ? <i/> : <i/>}保存</button>
                        <a href="javascript:;" className="cancel" onClick={() => showPurchaseReceive(null)}>取消</a>
                    </div>
                </div>
            </div>
            </form>
        );
    }
}
PurchaseReceiveForm.propTypex = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    callback: PropTypes.func.isRequired
}

PurchaseReceiveForm.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: {
            purchaseOrderId: state.purchaseReceiveTodos.purchaseOrderId,
            receiveTimeString: state.purchaseReceiveTodos.data.receiveTimeString,
            itemList: state.purchaseReceiveTodos.data.orderItemVoList
        },
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm, errorValidMessageFunction,
        showPurchaseReceive, savePurchaseReceive,
        getLoginUserInfo}, dispatch);
}

PurchaseReceiveForm = reduxForm({
    form: 'purchaseReceiveForm',
    validate
})(PurchaseReceiveForm);

PurchaseReceiveForm = connect(mapStateToProps, mapDispatchToProps)(PurchaseReceiveForm);

export default PurchaseReceiveForm;
