import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";

class PurchaseReturnGoodsSelectComponent extends Component {

    componentWillMount(){
        const {store} = this.context;
        const {returnGoodsData, goodsSelectParams} = store.getState().todos;
        this.props.actions.queryReturnableItem(goodsSelectParams, 0, returnGoodsData.size);
    }

    componentDidMount(){
        $(".return-select input.check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".return-goods-check").each(function () {
                $(this)[0].checked = checked;
            });
        });

        $(".return-select").on("click", ".return-goods-check", function () {
            $("input.check-all")[0].checked = $(".return-goods-check").length == $(".return-goods-check:checked").length
        });
    }

    componentWillUnmount(){
        $(".return-select").unbind();
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {returnGoodsData, goodsSelectParams} = store.getState().todos;
        this.props.actions.queryReturnableItem(goodsSelectParams, returnGoodsData.number, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {goodsSelectParams} = store.getState().todos;
        this.props.actions.queryReturnableItem(goodsSelectParams, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    search(){
        let goodsCode = $("#goodsCode").val().trim();
        let goodsNm = $("#goodsNm").val().trim();
        let produceManufacturer = $("#produceManufacturer").val().trim();
        const {store} = this.context;
        const goodsSelectParams = store.getState().todos.goodsSelectParams;
        let params = Object.assign({}, goodsSelectParams, {goodsCode, goodsNm, produceManufacturer});
        this.props.actions.setGoodsSelectParamsAndLoad(params);
    }

    resetSearchParams(){
        const {store} = this.context;
        const goodsSelectParams = store.getState().todos.goodsSelectParams;
        let params = Object.assign({}, goodsSelectParams, {goodsCode: "", goodsNm: "", produceManufacturer: ""});
        this.props.actions.setGoodsSelectParamsAndLoad(params);
    }

    confirm(){
        let data = [];
        const {store} = this.context;
        const content = store.getState().todos.returnGoodsData.content;
        $(".return-goods-check:checked").each(function () {
            data.push(content[$(this).val()]);
        });
        if(data.length > 0){
            this.props.actions.showGoodsSelect();
            this.props.callbackFunc(data);
        }
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const returnGoodsData = store.getState().todos.returnGoodsData;
        const options = {
            sizePerPage: returnGoodsData.size > 0 ? returnGoodsData.size : 10,
            sizePerPageList: returnGoodsData.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: returnGoodsData.number + 1,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: returnGoodsData.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: returnGoodsData.totalPages, //分页的页码
            paginationShowsTotal: returnGoodsData.totalElements > returnGoodsData.size ? this.renderShowsTotal : null,
            hideSizePerPage: returnGoodsData.totalElements <= returnGoodsData.size,
            isWindow:true
        };
        return (
            <div style={{zIndex: "300"}} className="layer return-select">
                <div className="layer-box layer-info layer-addsp w1075">
                    <div className="layer-header">
                        <span>添加商品</span>
                        <a href="javascript:;" onClick={() => actions.showGoodsSelect()} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <label>商品编码<input name="goodsCode" id="goodsCode" type="text"/></label>
                                </div>
                                <div className="item">
                                    <label>名称<input name="goodsNm" id="goodsNm" type="text"/></label>
                                </div>
                                <div className="item">
                                    <label>生产厂商<input name="produceManufacturer" id="produceManufacturer" type="text"/></label>
                                </div>
                                <a onClick={() => this.search()} href="javascript:;" className="gray-btn">查询</a>
                                <a onClick={() => this.resetSearchParams()} href="javascript:;" className="gray-btn">重置</a>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <a onClick={() => this.confirm()} className="item-determine black-btn" href="javascript:;">确定选中</a>
                                </div>
                            </div>
                            <div className="box-mc">
                                <table style={{width: "1210px"}}>
                                    <thead>
                                    <tr>
                                        <th className="th-checkbox"><label><input className="check-all" type="checkbox" value="checkbox"/></label></th>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
                                        <th className="commodity-code">批号</th>
                                        <th className="commodity-code">生产日期</th>
                                        <th className="commodity-code">有效期至</th>
                                        <th className="commodity-code">可退数量</th>
                                        <th className="price">单价</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    { returnGoodsData.content.length <= 0 && <tr><td colSpan="100" style={{textAlign:"center"}}>暂无数据</td></tr>}
                                    {
                                        returnGoodsData.content.map((ct, index) => {
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <input value={index} className="return-goods-check" type="checkbox"/>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.goodsBatch}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.productionDateString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.validityString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.returnableQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.purchaseUnitPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.goodsNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.goodsCommonNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.spec}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.dosageForm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.unit}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.produceManufacturer}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{ct.approvalNumber}</div>
                                                    </td>
                                                </tr>
                                            );
                                        })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <IMallPaginationBar options={options} actions={actions}/>
                    </div>
                </div>
            </div>);
    }
}

PurchaseReturnGoodsSelectComponent.propTypes = {
    actions: PropTypes.object.isRequired,
    callbackFunc: PropTypes.func.isRequired,
}

PurchaseReturnGoodsSelectComponent.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseReturnGoodsSelectComponent);