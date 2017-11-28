import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import ApproveValidateComponent from "../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from '../../../../../common/approvevalidate/actions';

import {inputField} from './FieldComponent';
import {validateForEdit} from '../actions/index';

class DrugCuringEditForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        const {change} = this.props;
        change('itemList', store.getState().todos.data.itemList);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!=undefined && json!=null){
            const {change} = this.props;
            $("#curingManId").val(json.id);
            change('curingManId', json.id);
            $("#reviewBtn").hide();
            $("#realName").text(json.realName);
            $("#realName").show();
        }
    }

    render() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        const actions = this.props.actions;
        const data = store.getState().todos.data;
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {display} = this.context.store.getState().approveValidateTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit(data=>actions.saveOrUpdate(data, actions, params))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage}  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
                <input type="hidden" name="id"/>
                <Field name="curingManId" display="none" component={inputField} id="curingManId"/>
                <div className="layer">
                    <div className="layer-box drug-check w1500">
                        <div className="layer-header">
                            <span>药品养护</span>
                            <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showEditForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">养护信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>计划养护时间</p>
                                        <span>{data.planCuringTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>养护类型</p>
                                        <span>{data.curingTypeName}</span>
                                    </div>
                                    <div className="item" style={{'clear':'both'}}>
                                        <p><i>*</i>审核人</p>
                                        <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel()} id="reviewBtn">点击审核</a>
                                        <p id="realName" style={{display: 'none'}}/>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc">
                                    <table className="w1430">
                                        <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="th-ipt">养护数量</th>
                                            <th className="th-ipt">养护项目</th>
                                            <th className="th-ipt">不合格数量</th>
                                            <th className="th-ipt">处理意见</th>
                                            <th className="th-ipt">结论</th>
                                            <th className="th-ipt">备注</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="origin">产地</th>
                                            <th className="batch-number">批号</th>
                                            <th className="time">生产日期</th>
                                            <th className="time">有效期至</th>
                                            <th className="cargo-location">货位</th>
                                            <th className="number">库存数量</th>
                                            <th className="price">采购单价</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {
                                            data.itemList.map((item, index)=>{
                                                return(
                                                    <tr key={item.id}>
                                                        <td>
                                                            <div className="td-cont">
                                                                {index + 1}<input name={"itemList[" + index + "].id"} type="hidden" value={item.id}/>
                                                            </div>
                                                        </td>
                                                        <Field name={"itemList[" + index + "].curingQuantity"} type="text" component={inputField} tdClassName="pink-bg"/>
                                                        <Field name={"itemList[" + index + "].curingPrj"} type="text" component={inputField} maxLength="128"/>
                                                        <Field name={"itemList[" + index + "].notQualifiedQuantity"} type="text" component={inputField} tdClassName="pink-bg"/>
                                                        <Field name={"itemList[" + index + "].processOpinion"} type="text" component={inputField} maxLength="128"/>
                                                        <Field name={"itemList[" + index + "].conclusion"} type="text" component={inputField} maxLength="128"/>
                                                        <Field name={"itemList[" + index + "].remark"} type="text" component={inputField} maxLength="256"/>
                                                        <td>
                                                            <div className="td-cont">{item.goodsCode}</div>
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
                                                            <div className="td-cont">{item.batch}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.produceDateString}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.validDateString}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.storageSpaceNm}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.currentStock}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.purchasePrice}</div>
                                                        </td>
                                                    </tr>
                                                );
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.showEditForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}



DrugCuringEditForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCuringEditForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showValidateModel
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        validate: validateForEdit,
        state
    }
}

DrugCuringEditForm = reduxForm({
    form: 'drugCuringEditForm'
})(DrugCuringEditForm);

DrugCuringEditForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCuringEditForm);

export default DrugCuringEditForm;