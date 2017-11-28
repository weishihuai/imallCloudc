import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import StorageSpaceMoveSearchForm from "./StorageSpaceMoveSearchForm";
import StorageSpaceMoveDetail from "./StorageSpaceMoveDetail";
import StorageSpaceMoveAddForm from "./StorageSpaceMoveAddForm";
import ImallA from '../../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';

/**
 * 货位移动列表
 */
class StorageSpaceMoveList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.storageSpaceMoveList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:adjust:storageSpaceMove:add','stock:adjust:storageSpaceMove:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.storageSpaceMoveList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.storageSpaceMoveList(params, page - 1, sizePerPage);
    }

    submitData(data){
       const saveDataObject = {
            approveManId: data.approveDataId,
            moveTimeString:data.moveTimeString,
            storageSpaceMoveGoodsSaveVoList: data.goodsList,
            remark:data.remark,
            moveManName:data.moveManName,
            moveNum:this.context.store.getState().todos.moveNum.moveNum,
        };
        const {store} = this.context;
        const {params} = store.getState().todos;

        return this.props.actions.saveStorageSpaceMove(saveDataObject,params);
    }

    /*新增窗体*/
    storageSpaceMoveAdd() {
        this.props.actions.storageSpaceMoveAddModel(true);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const storageSpaceMoveList = store.getState().todos.page.content || [];
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
                            <h5>货位移动</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存调整</a>
                            <span>></span>
                            <a href="javascript:void(0);">货位移动</a>
                            <StorageSpaceMoveSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:storageSpaceMove:add" text="添加" className="added" onClick={()=> this.storageSpaceMoveAdd()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"250px"}}>移动单号</th>
                                    <th className="procurement" style={{width:"200px"}}>移动人</th>
                                    <th className="time" style={{width:"200px"}}>移动日期</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { storageSpaceMoveList.length <= 0 &&
                                <tr >
                                    <th colSpan="4" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {storageSpaceMoveList.map((storageSpaceMove, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={storageSpaceMove.moveOrderNum}>{storageSpaceMove.moveOrderNum}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={storageSpaceMove.moveManName}>{storageSpaceMove.moveManName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={storageSpaceMove.moveTimeString}>{storageSpaceMove.moveTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingRight": "20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:storageSpaceMove:detail" className="gray-btn" text="查看" onClick={()=>actions.storageSpaceMoveDetailModal(true,storageSpaceMove.moveOrderNum)}>查看</ImallA>
                                        </div>
                                    </td>
                                </tr>
                                )
                                })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {store.getState().todos.addState && <StorageSpaceMoveAddForm actions={actions}  onSubmit={(data) => {this.submitData(data)}}/>}
                    {store.getState().todos.storageSpaceMoveDetailState && <StorageSpaceMoveDetail store={store} actions={actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

StorageSpaceMoveList.propTypes = {};

StorageSpaceMoveList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StorageSpaceMoveList);