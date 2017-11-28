import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
class GoodsList extends Component {

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {goodsId} = store.getState().todos;
        this.props.actions.queryRecordList(0, sizePerPage,goodsId);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {goodsId} = store.getState().todos;
        this.props.actions.queryRecordList(page - 1,sizePerPage,goodsId);
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.recordPage;
        let goodsRecordList = page.content || [];
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
            hideSizePerPage: page.totalElements <= page.size,
            isWindow:true
        };
        return (
        <div className="layer" >
            <div className="layer-box layer-record w1075">
                <div className="layer-header">
                    <span>修改记录</span>
                    <a onClick={()=>this.props.actions.changeGoodsRecordListState(false,"")} className="close"/>
                </div>
                <div className="layer-body">
                    <div className="table-box">
                        <table>
                            <thead>
                            <tr>
                                <th className="th-number">序号</th>
                                <th className="th-coding">商品编码</th>
                                <th className="common-name">商品名称</th>
                                <th className="operating">操作</th>
                                <th className="reason">原因</th>
                                <th className="operator">操作人</th>
                                <th className="time">操作时间</th>
                            </tr>
                            </thead>
                            { goodsRecordList.length <= 0 &&
                            <tbody><tr ><th colSpan="7" style={{textAlign:"center"}}>暂无记录</th></tr></tbody>
                            }
                            <tbody>
                            {
                                goodsRecordList.map((record,index)=>{
                                    return (
                                        <tr key={index}>
                                            <td><div className="td-cont td-number">{index+1}</div></td>
                                            <td><div className="td-cont">{record.goodsCode}</div></td>
                                            <td><div className="td-cont">{record.goodsNm}</div></td>
                                            <td><div className="td-cont">{record.operationState=="DISABLE"?"禁用":"启用"}</div></td>
                                            <td><div className="td-cont">{record.reason}</div></td>
                                            <td><div className="td-cont">{record.lastModifiedBy}</div></td>
                                            <td><div className="td-cont">{record.lastModifiedDateString}</div></td>
                                        </tr>
                                    )
                                })
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="layer-footer">
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        </div>
        );
    }
}
GoodsList.propTypes = {
    actions: PropTypes.object.isRequired
};

GoodsList.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
    }, dispatch);
}

function mapStateToProps(state) {
    return state;

}

GoodsList = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsList);

export default GoodsList
