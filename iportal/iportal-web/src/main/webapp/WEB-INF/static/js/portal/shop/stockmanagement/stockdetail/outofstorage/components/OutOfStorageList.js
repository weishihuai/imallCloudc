import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {OUT_OF_STOCK_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";

/**
 * 出入库明细
 */
class OutOfStorageList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.outOfStorageList(params.page, params.size, params);
    }

    componentDidMount() {
        $("#root").css("min-width", "2100px");
        $("#logSourceTypeCode").jSelect();
        $(".datetimepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.outOfStorageList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.outOfStorageList(page - 1, sizePerPage, params);
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            searchFields: this.refs.searchFields.value,
            batch: this.refs.batch.value,
            fromDateString: this.refs.fromDateString.value,
            toDateString: this.refs.toDateString.value,
            logSourceTypeCode:  this.refs.logSourceTypeCode.value
        };
        store.dispatch({
            type: OUT_OF_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.outOfStorageList(0, 10, newParam);
    };


    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.searchFields.value = "";
        this.refs.batch.value = "";
        this.refs.fromDateString.value = "";
        this.refs.toDateString.value = "";
        $("#logSourceTypeCode").jSelectReset();
        const newParam = {
            searchFields: "",
            batch: "",
            fromDateString: "",
            toDateString: "",
            logSourceTypeCode: "",
        };

        this.context.store.dispatch({
            type: OUT_OF_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.outOfStorageList(0, 10, newParam);

    }


    stockStatCode(code){
        switch (code){
            case "PURCHASE_IN_STOCK":
                return "采购入库";
            case "OUT_STOCK":
                return "销售出库";
            case "SELL_RETURNED":
                return "销售退货";
            case "RETURNED_PURCHASE":
                return "采购退货";
            case "STOCK_CHECK":
                return "盘点";
            case "STORAGE_SPACE_MOVE":
                return "货位移动";
            case "OTHER_OUT_STOCK":
                return "其他出库";
            case "OTHER_IN_STOCK":
                return "其他入库";
            case "DRUG_IN_BUCKET":
                return "装斗";
            case "DRUG_CLEAR_BUCKET":
                return "清斗";
            case "DRUG_LOCK":
                return "药品锁定";
            case "RELEASE_LOCK":
                return "解除锁定";
            case "STOP_SALE":
                return "停售";
            case "RELEASE_SALE":
                return "解停";
            case "NOT_QUALIFIED":
                return "不合格";
            case "DRUG_DESTROY":
                return "药品销毁";
        }
    }


    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const outOfStorageList = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>出入库明细</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存明细</a>
                            <span>></span>
                            <a href="javascript:void(0);">出入库明细</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input className="batch" placeholder="拼音码|名称|编码" ref="searchFields" type="text"/>
                                </div>
                                <div className="search">
                                    <input className="batch" placeholder="批号" ref="batch" type="text"/>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input ref="fromDateString" placeholder="创建时间" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input ref="toDateString" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                </div>
                                <div className="status">
                                    <select id="logSourceTypeCode" name="logSourceTypeCode" ref="logSourceTypeCode"className="select" placeholder="供应状态">
                                        <option value="">库存类型</option>
                                        <option value="PURCHASE_IN_STOCK">采购入库</option>
                                        <option value="OUT_STOCK">销售出库</option>
                                        <option value="SELL_RETURNED">销售退货</option>
                                        <option value="RETURNED_PURCHASE">采购退货</option>
                                        <option value="STOCK_CHECK">盘点</option>
                                        <option value="STORAGE_SPACE_MOVE">货位移动</option>
                                        <option value="OTHER_OUT_STOCK">其他出库</option>
                                        <option value="OTHER_IN_STOCK">其他入库</option>
                                        <option value="DRUG_IN_BUCKET">装斗</option>
                                        <option value="DRUG_CLEAR_BUCKET">清斗</option>
                                        <option value="DRUG_LOCK">药品锁定</option>
                                        <option value="DRUG_DESTROY">药品销毁</option>
                                        <option value="RELEASE_LOCK">解除锁定</option>
                                        <option value="NOT_QUALIFIED">不合格</option>
                                        <option value="STOP_SALE">停售</option>
                                        <option value="RELEASE_SALE">解停</option>
                                    </select>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" onClick={() => this.handleSearch()} value="查询"/>
                                    <input className="sr-reset" type="button" onClick={() => this.onResetSearchParam()} value="重置"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="th-coding"  style={{width: "120px"}}>商品编码</th>
                                        <th className="commodity-name"  style={{width: "250px"}}>商品名称</th>
                                        <th className="common-name"  style={{width: "250px"}}>通用名称</th>
                                        <th className="standard"  style={{width: "100px"}}>规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers" style={{width:"200px"}}>生产厂商</th>
                                        <th className="approval-number" style={{width:"200px"}}>批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="batch-number" style={{width:"120px"}} >批号</th>
                                        <th className="th-coding" style={{width:"120px"}}>单号</th>
                                        <th className="inventory-form">库存类型</th>
                                        <th className="cargo-location" style={{width:"120px"}}>货位</th>
                                        <th className="retail-price">采购单价</th>
                                        <th className="inventory">数量</th>
                                        <th className="retail-price">金额</th>
                                        <th className="time" style={{width:"220px"}}>创建日期</th>
                                    </tr>
                                </thead>
                                {
                                    outOfStorageList.length <= 0 &&
                                    <tr>
                                        <td colSpan="100" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                }
                                <tbody>
                                {
                                    outOfStorageList.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont">{index+1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsCode}>{item.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsNm}>{item.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.commonNm}>{item.commonNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.spec}>{item.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.dosageForm}>{item.dosageForm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.unit}>{item.unit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.approvalNumber}>{item.approvalNumber}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.productionPlace}>{item.productionPlace}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.batch}>{item.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.objectOrderNum}>{item.objectOrderNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={this.stockStatCode(item.logSourceTypeCode)}>{this.stockStatCode(item.logSourceTypeCode)}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.storageSpaceNm}>{item.storageSpaceNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.unitPrice}>{item.unitPrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.quantity}>{item.quantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.amount}>{item.amount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.createDateString}>{item.createDateString}</div>
                                                </td>

                                            </tr>
                                        )
                                    })
                                }

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

OutOfStorageList.propTypes = {};

OutOfStorageList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OutOfStorageList);