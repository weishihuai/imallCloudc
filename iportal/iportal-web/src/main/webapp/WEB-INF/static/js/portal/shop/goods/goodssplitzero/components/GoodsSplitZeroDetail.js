import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";

class GoodsSplitZeroDetail extends Component{
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
                <div className="layer-box layer-info w960">
                    <div className="layer-header">
                        <span>药品拆零详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showDetail(false)} />
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>商品编码</p>
                                        <span>{record.goodsCode}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>商品名称</p>
                                        <span>{record.goodsNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>通用名称</p>
                                        <span>{record.commonNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>规格</p>
                                        <span>{record.spec}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>剂型</p>
                                        <span>{record.dosageForm}</span>
                                    </div>
                                    <div className="item">
                                        <p>单位</p>
                                        <span>{record.unit}</span>
                                    </div>
                                    <div className="item">
                                        <p>生产厂商</p>
                                        <span>{record.produceManufacturer}</span>
                                    </div>
                                    <div className="item">
                                        <p>货位</p>
                                        <span>{record.storageSpaceNm}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>批号</p>
                                        <span>{record.batch}</span>
                                    </div>
                                    <div className="item">
                                        <p>有效日期</p>
                                        <span>{record.validDate}</span>
                                    </div>
                                    <div className="item">
                                        <p>生成日期</p>
                                        <span>{record.produceDate}</span>
                                    </div>
                                    <div className="item">
                                        <p>入库日期</p>
                                        <span>{record.inStockDate}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">拆零信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>拆零数量</p>
                                        <span>{record.splitZeroQuantity}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>拆后小包装数量</p>
                                        <span>{record.splitSmallPackageQuantity}</span>
                                    </div>
                                    <div className="item">
                                        <p>用法</p>
                                        <span>{record.usageText}</span>
                                    </div>
                                    <div className="item">
                                        <p>用量</p>
                                        <span>{record.dosage}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>经办人</p>
                                        <span>{record.operator}</span>
                                    </div>
                                    <div className="item">
                                        <p>拆零单位</p>
                                        <span>{record.splitZeroUnit}</span>
                                    </div>
                                    <div className="item">
                                        <p>拆零规格</p>
                                        <span>{record.splitZeroSpec}</span>
                                    </div>
                                    <div className="item">
                                        <p>拆零零售价格</p>
                                        <span>{record.splitZeroRetailPrice}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>拆零会员价格</p>
                                        <span>{record.splitZeroMemberPrice}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

GoodsSplitZeroDetail.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

GoodsSplitZeroDetail.contextTypes = {
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

GoodsSplitZeroDetail = reduxForm({
    form: 'goodsSplitZeroDetail',
    enableReinitialize: true
})(GoodsSplitZeroDetail);

GoodsSplitZeroDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsSplitZeroDetail);

export default GoodsSplitZeroDetail;