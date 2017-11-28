import React, {Component} from "react";
import StorageSpaceUpdateForm from "./StorageSpaceUpdateForm";
import StorageSpaceAddForm from "./StorageSpaceAddForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import StorageSpaceSearchForm from "./StorageSpaceSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from '../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../common/imallbutton/actions';

/**
 * 商品货位列表
 */
class StorageSpaceList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.storageSpaceList(params, params.page, params.size);
        this.props.portalOperationalAuth(['goods:storageSpace:add','goods:storageSpace:update']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.storageSpaceList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.storageSpaceList(params, page - 1, sizePerPage);
    }

    storageSpaceTypeFormat(storageSpaceType){
        switch (storageSpaceType){
            case "GOODS_STORAGE_SPACE":
                return "商品货位";
            case "CHINESE_HERBAL_MEDICINE":
                return "中药饮片";
            case "MEDICAL_APPARATUS_INSTRUMENTS":
                return "医疗器械";
        }
    }

    enableStatCodeFormat(enableStatCode){
        let enableStatCodeString = "未启用";
        if(enableStatCode === "Y") {
            enableStatCodeString = "已启用";
        }
        return enableStatCodeString;
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.page;
        const params = store.getState().todos.params;
        const number = page.number + 1;
        const storageSpaceList = store.getState().todos.page.content || [];
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
                            <h5>货位管理</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">货位管理</a>
                            <StorageSpaceSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="goods:storageSpace:add" className="added" text="添加" onClick={()=> this.props.actions.storageSpaceAddModel(true)}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="name" style={{width:"200px"}}>货位名称</th>
                                    <th className="types">货位类型</th>
                                    <th className="status">启用状态</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { storageSpaceList.length <= 0 &&
                                <tr >
                                    <th colSpan="4" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {storageSpaceList.map((storageSpace, index) => {
                                    return (<tr key={index}>
                                            <td>
                                                <div className="td-cont" title={storageSpace.storageSpaceNm}>{storageSpace.storageSpaceNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={this.storageSpaceTypeFormat(storageSpace.storageSpaceType)}>{this.storageSpaceTypeFormat(storageSpace.storageSpaceType)}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={this.enableStatCodeFormat(storageSpace.isEnable)}>{this.enableStatCodeFormat(storageSpace.isEnable)}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" style={{"paddingLeft":0,"paddingRight":"25px","textAlign":"right"}}>
                                                    <ImallA href="javascript:void(0);" permissionCode="goods:storageSpace:update" className="gray-btn" text="修改" onClick={()=> this.props.actions.storageSpaceEditModel(true,storageSpace)}>修改</ImallA>
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
                    {store.getState().todos.editState && <StorageSpaceUpdateForm store={store} actions={this.props.actions}/>}
                    {store.getState().todos.addState && <StorageSpaceAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.storageSpaceAddData(data,params)}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

StorageSpaceList.propTypes = {};

StorageSpaceList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StorageSpaceList);