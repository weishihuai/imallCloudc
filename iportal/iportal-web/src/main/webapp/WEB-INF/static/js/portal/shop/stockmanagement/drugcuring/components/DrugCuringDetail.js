import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";

class DrugCuringDetail extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.props;
        const record = store.getState().todos.record;

        return(
            <div className="layer">
                <div className={record.curingStateCode==='CURED'  ? "layer-box drug-check w1500" : "layer-box drug-check w1175"}>
                    <div className="layer-header">
                        <span>药品养护详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showDetail(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">盘点信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>计划养护时间</p>
                                    <span>{record.planCuringTimeString}</span>
                                </div>
                                <div className="item">
                                    <p>养护类型</p>
                                    <span>{record.curingTypeName}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className={record.curingStateCode=='CURED'  ? "w1430" : "w1075"}>
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">养护数量</th>}
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">养护项目</th>}
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">不合格数量</th>}
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">处理意见</th>}
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">结论</th>}
                                        {record.curingStateCode==='CURED' && <th class="th-ipt">备注</th>}
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
                                        record.itemList.map((item, index)=>{
                                            return(
                                                <tr>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
                                                    </td>
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td className="pink-bg">
                                                            <div className="td-cont">{item.curingQuantity}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td>
                                                            <div className="td-cont">{item.curingPrj}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td className="pink-bg">
                                                            <div className="td-cont">{item.notQualifiedQuantity}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td>
                                                            <div className="td-cont">{item.processOpinion}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td>
                                                            <div className="td-cont">{item.conclusion}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.curingStateCode==='CURED' &&
                                                        <td>
                                                            <div className="td-cont">{item.remark}</div>
                                                        </td>
                                                    }
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
                </div>
            </div>
        );
    }
}

DrugCuringDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        state
    }
}

DrugCuringDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCuringDetail);

export default DrugCuringDetail;
