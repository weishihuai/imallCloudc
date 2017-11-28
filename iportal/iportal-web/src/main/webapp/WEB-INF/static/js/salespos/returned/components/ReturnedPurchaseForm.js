import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import OrderSelectModel from "./OrderSelectModel";
import ReturnedCalcForm from "./ReturnedCalcForm";
import ApproveValidateComponent from "../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from "../../../common/approvevalidate/actions";
import {niftyNoty} from "../../../common/common";
import {
    RETURNED_PURCHASE_ORDER_SELECT_MODEL_STAT_CHANGE,
    RETURNED_PURCHASE_RETURNED_CALC_FORM_STAT_CHANGE,
    RETURNED_PURCHASE_RETURNED_CALC_DATA_CHANGE,
    RETURNED_PURCHASE_RETURNED_QUANTITY_CHANGE,
    RETURNED_PURCHASE_OVERALL_RETURNED_CHANGE
} from "../constants/ActionTypes";

/**
 * 销售pos前端-退货
 */

class ReturnedPurchaseForm extends Component {

    constructor(props) {
        super(props);
        this.reportOption = this.reportOption.bind(this);
    }

    componentWillMount() {
    }

    componentDidMount() {
        window.addEventListener('keydown', this.reportOption);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.reportOption);
    }

    reportOption(event) {
        const {store} = this.context;
        switch(event.keyCode){
            //F1 点击审核
            case 112:
                event.preventDefault();
                // store.dispatch({type: RETURNED_PURCHASE_ORDER_TYPE_CHANGE, data: 'SALSE_ORDER'});
                break;
            //F2 添加退货订单
            case 113:
                event.preventDefault();
                store.dispatch({type: RETURNED_PURCHASE_ORDER_SELECT_MODEL_STAT_CHANGE, isShow: true});
                break;
            //F10
            case 121:
                event.preventDefault();
                this.submitEvent();
                break;
            default:
                break;
        }
    }

    submitEvent(){
        const {store} = this.context;
        if($("#submitBtn").hasClass("disabled")){
            return false;
        }
        const approveManId = $("#approveManId").val();
        if(!approveManId){
            niftyNoty("需要执业药师审核");
            return false;
        }

        store.dispatch({type: RETURNED_PURCHASE_RETURNED_CALC_DATA_CHANGE, data: {approveManId: approveManId , remark: $("#remark").val()}});

        const {calcData} = store.getState().todos;
        if(!calcData.returnedOrderItems ||calcData.returnedOrderItems.length == 0 ){
            niftyNoty("请添加退货订单,并填写退货数量");
            return false;
        }
        store.dispatch({type: RETURNED_PURCHASE_RETURNED_CALC_FORM_STAT_CHANGE, isShow: true});
    }

    orderSelectModelShowEvent(e){
        const {store} = this.context;
        store.dispatch({type: RETURNED_PURCHASE_ORDER_SELECT_MODEL_STAT_CHANGE, isShow: true});
    }

    returnedQuantityChangeEvent(e, item, returnedQuantity){
        const {store} = this.context;
        if(isNaN(returnedQuantity)){
            return;
        }
        if(returnedQuantity == 0){
            return ;
        }
        let test = new RegExp(/^[0-9]*$/);
        if(!test.test(returnedQuantity)){
           return;
        }
        item.returnedQuantity = returnedQuantity;
        if(item.returnedQuantity > item.allowReturnedQuantity){
            item.returnedQuantity = item.allowReturnedQuantity
        }
        store.dispatch({type: RETURNED_PURCHASE_RETURNED_QUANTITY_CHANGE, data: item});
    }


    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!=undefined && json!=null){
            $("#approveManId").val(json.id);
            $("#reviewBtn").hide();
            $("#realName").text(json.realName);
            $("#realName").show();
            $("#submitBtn").removeClass("disabled");
        }
    }

    overallReturnedChangeEvent(e){
        const checked = e.target.checked;
        const {store} = this.context;
        store.dispatch({type: RETURNED_PURCHASE_OVERALL_RETURNED_CHANGE, data: checked ? "Y" : "N"});
    }

    render() {
        const {store} = this.context;
        const {actions, showValidateModel} = this.props;
        const {returnedOrder, returnedOrderItems, returnedAmount, orderSelectModelStat, returnedCalcFormStat} = store.getState().todos;
        const {display} = store.getState().approveValidateTodos;

        return (
            <div>
                {display && <ApproveValidateComponent title="身份认证-执业药师" callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
            <div className="pos-box return">
                <div className="pos-lt">
                    <div className="lt-top">
                        <div className="item">
                            <span>销售订单号</span>
                            <input type="text" disabled="disabled" value={returnedOrder.orderNum}/>
                        </div>
                        <div className="item">
                            <span>退货时间</span>
                            <input type="text" disabled="disabled" value={returnedOrder.returnedTimeString}/>
                        </div>
                        <div className="item">
                            <span></span>
                            <label><input type="checkbox" id="isOverallReturnedPurchase" onChange={(e)=>this.overallReturnedChangeEvent(e)}/>整单退货</label>
                        </div>
                        <div className="item">
                            <span>审核人</span>
                            <a href="javascript:void(0);" className="check" onClick={(e)=>showValidateModel()} id="reviewBtn">点击审核</a>
                            <label id="realName" style={{display: 'none'}} />
                            <input type="hidden" id="approveManId" />
                        </div>
                        <div className="item">
                            <span>备注</span>
                            <input type="text" id="remark" autoFocus="autoFocus" maxLength="128"/>
                        </div>
                        <a href="javascript:void(0);" id="submitBtn" className="account disabled" onClick={(e) => this.submitEvent(e)}>结算[F10]</a>
                    </div>
                </div>
                <div className="main">
                    <div className="mt">
                        <a href="javascript:void(0);" className="add-btn" onClick={(e) => this.orderSelectModelShowEvent(e)}>添加退货订单[F2]</a>
                        <div className="amount">
                            <em>应退金额：</em>
                            <i>{returnedAmount}</i>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="th-coding">商品编码</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="standard">规格</th>
                                    <th className="units">单位</th>
                                    <th className="batch-number">批号</th>
                                    <th className="inventory">销售数量</th>
                                    <th className="retail-price">单价</th>
                                    <th className="statutory">小计</th>
                                    <th className="batch-number">可退数量</th>
                                    <th className="return-number">退货数量</th>
                                </tr>
                                </thead>
                                <tbody>
                                {returnedOrderItems.map((item, index) =>{
                                    return (
                                        <tr key={index}>
                                            <td>
                                                <div className="td-cont td-number">{index + 1}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsCoding}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.commonNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.produceManufacturer}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.spec}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.unit}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.quantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsUnitPrice}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsTotalAmount}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.allowReturnedQuantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont return-num">
                                                    <input type="text" value={item.returnedQuantity} onChange={(e) => this.returnedQuantityChangeEvent(e, item, e.target.value)}/>
                                                </div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            {orderSelectModelStat && <OrderSelectModel store={store} actions={actions} />}
            {returnedCalcFormStat && <ReturnedCalcForm store={store} actions={actions} onSubmit={(data) => actions.calcSubmit(data)}/>}
            </div>
        )
    }
}

ReturnedPurchaseForm.propTypes = {};

ReturnedPurchaseForm.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        showValidateModel
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ReturnedPurchaseForm);