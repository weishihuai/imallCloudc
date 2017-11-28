import React, {Component, PropTypes} from "react";
import ChineseMedicinePiecesCleaningBucketRecordSearchForm from "./ChineseMedicinePiecesCleaningBucketRecordSearchForm";
import IMallPaginationBar from '../../../../../../common/imallpagination/components/IMallPaginationBar';

class ChineseMedicinePiecesCleaningBucketRecordSearchList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.queryList(params, params.page, params.size);

    }

    componentDidMount() {

    }



    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    onSizePerPageList(sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.queryList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.queryList(params, page - 1, sizePerPage);
    }


    render() {

        const {store} = this.context;
        const todos = store.getState().todos;
        const cleaningBucketRecordList =todos.page.content || [];
        const page = store.getState().todos.page;
        const number = page.number + 1;
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
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage: page.totalElements <= page.size
        };


        return (
            <div className="main-box">

                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>中药饮片清斗记录</h5>
                            <a  href="javascript:void(0);" >质量管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >陈列与存储</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >中药饮片清斗记录</a>
                            <ChineseMedicinePiecesCleaningBucketRecordSearchForm store={store} actions={this.props.actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="time">清斗人</th>
                                    <th className="manufacturer">清斗数量</th>
                                    <th className="time">清斗日期</th>
                                    <th className="degree">审核人</th>
                                    <th className="sm-inventory">商品编码</th>
                                    <th className="status">商品名称</th>
                                    <th className="time">通用名称</th>
                                    <th className="time">规格</th>
                                    <th className="time">剂型</th>
                                    <th className="time">单位</th>
                                    <th className="time">生产厂商</th>
                                    <th className="time">批准文号</th>
                                    <th className="time">产地</th>
                                    <th className="time">批号</th>
                                    <th className="time">生产日期</th>
                                    <th className="time">有效期至</th>
                                    <th className="time">货位</th>
                                </tr>
                                </thead>
                                <tbody>
                                { cleaningBucketRecordList.length <= 0 &&
                                <tr >
                                    <th colSpan="17" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {cleaningBucketRecordList.map((cleaningBucketRecord, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont" title={cleaningBucketRecord.cleaningBucketManNm}>{cleaningBucketRecord.cleaningBucketManNm}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.cleaningBucketQuantity}>{cleaningBucketRecord.cleaningBucketQuantity}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.cleaningBucketDateString}>{cleaningBucketRecord.cleaningBucketDateString}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.approveManName}>{cleaningBucketRecord.approveManName}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.goodsCode}>{cleaningBucketRecord.goodsCode}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.goodsNm}>{cleaningBucketRecord.goodsNm}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.commonNm}>{cleaningBucketRecord.commonNm}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.spec}>{cleaningBucketRecord.spec}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.dosageForm}>{cleaningBucketRecord.dosageForm}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.unit}>{cleaningBucketRecord.unit}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.produceManufacturer}>{cleaningBucketRecord.produceManufacturer}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.approvalNumber}>{cleaningBucketRecord.approvalNumber}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.productionPlace}>{cleaningBucketRecord.productionPlace}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.batch}>{cleaningBucketRecord.batch}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.produceTimeString}>{cleaningBucketRecord.produceTimeString}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.validDateString}>{cleaningBucketRecord.validDateString}</div></td>
                                                <td><div className="td-cont" title={cleaningBucketRecord.storageSpaceNm}>{cleaningBucketRecord.storageSpaceNm}</div></td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                    </div>
            </div>
        )
    }
}


ChineseMedicinePiecesCleaningBucketRecordSearchList.contextTypes = {
    store: React.PropTypes.object
};




export default ChineseMedicinePiecesCleaningBucketRecordSearchList;