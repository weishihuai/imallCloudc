import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";

class DrugCheckDetail extends Component{
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
                <div className={record.checkStateCode=='CHECKED' ? "layer-box drug-check w1500" : "layer-box drug-check w1175"}>
                    <div className="layer-header">
                        <span>药品检查详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showDetail(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">检查信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>计划检查时间</p>
                                    <span>{record.planCheckTimeString}</span>
                                </div>
                                <div className="item">
                                    <p>检查类型</p>
                                    <span>{record.checkTypeName}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className={record.checkStateCode=='CHECKED'  ? "w1430" : "w1075"}>
                                    <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">检查数量</th>}
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">检查项目</th>}
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">不合格数量</th>}
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">处理意见</th>}
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">结论</th>}
                                            {record.checkStateCode=='CHECKED' && <th className="th-ipt">备注</th>}
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
                                        record.drugCheckItemDetailVoList.map((item, index)=>{
                                            return(
                                                <tr key={item.id}>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
                                                    </td>
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
                                                        <td className="pink-bg">
                                                            <div className="td-cont">{item.checkQuantity}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
                                                        <td>
                                                            <div className="td-cont">{item.checkPrj}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
                                                        <td className="pink-bg">
                                                            <div className="td-cont">{item.notQualifiedQuantity}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
                                                        <td>
                                                            <div className="td-cont">{item.processOpinion}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
                                                        <td>
                                                            <div className="td-cont">{item.conclusion}</div>
                                                        </td>
                                                    }
                                                    {
                                                        record.checkStateCode==='CHECKED' &&
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

DrugCheckDetail.propTypes = {

};

DrugCheckDetail.contextTypes = {
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

DrugCheckDetail = reduxForm({
    form: 'drugCheckDetail',
    enableReinitialize: true
})(DrugCheckDetail);

DrugCheckDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCheckDetail);

export default DrugCheckDetail;