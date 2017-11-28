import React, {Component, PropTypes} from "react";
import HealthDocAddForm  from "./HealthDocAddForm";
import HealthDocDetailForm from "./HealthDocDetailForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import HealthDocSearchForm from "./HealthDocSearchForm";
import IMallPaginationBar from '../../../../../common/imallpagination/components/IMallPaginationBar';
import {portalOperationalAuth} from '../../../../../common/imallbutton/actions';
import ImallA from '../../../../../common/imallbutton/components/ImallA';
class HealthDocList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.queryList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'quality:healthDoc:add:default',
            'quality:healthDoc:detail:default',
        ]);

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

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }


    submitData(data,state=false){
        const {params} = this.context.store.getState().todos;
        this.props.actions.saveAndUpdateData(data,state,params);
    }

    showAddForm(index){
        const {setIndex,showAddForm} = this.props.actions;
        setIndex(index);
        showAddForm(true);
    }

    render() {
        const actions = this.props.actions;
        const {showDetail} = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const haalthDocLists =todos.page.content || [];
        const detailObject =todos.detailObject || {};
        const detailState =todos.detailState ||"";
        const addState =todos.addState ;
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
                            <h5>员工健康档案</h5>
                            <a  href="javascript:void(0);" >质量管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >员工健康档案</a>
                            <HealthDocSearchForm store={store} actions={this.props.actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="employees-account"style={{width: "10.4%"}}>员工账号</th>
                                    <th className="name" style={{width: "10.1%"}}>员工姓名</th>
                                    <th className="age" style={{width: "4.2%"}}>性别</th>
                                    <th className="time" style={{width: "12.6%"}}>出生日期</th>
                                    <th className="time" style={{width: "12.4%"}}>入职日期</th>
                                    <th className="status"style={{width: "11.2%"}}>员工状态</th>
                                    <th className="operating" style={{width: "39.1%"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { haalthDocLists.length <= 0 &&
                                <tr >
                                    <th colSpan="7" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {haalthDocLists.map((haalthDoc, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont" title={haalthDoc.userName}>{haalthDoc.userName}</div></td>
                                                <td><div className="td-cont" title={haalthDoc.realName}>{haalthDoc.realName}</div></td>
                                                <td><div className="td-cont" title={haalthDoc.sex}>{haalthDoc.sex}</div></td>
                                                <td><div className="td-cont" title={haalthDoc.birthdayString}>{haalthDoc.birthdayString}</div></td>
                                                <td><div className="td-cont" title={haalthDoc.entryJobTimeString}>{haalthDoc.entryJobTimeString}</div></td>
                                                <td><div className="td-cont" title={haalthDoc.isEnable=="Y"?"启用":"禁用"}>{haalthDoc.isEnable=="Y"?"启用":"禁用"}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA text="查看" permissionCode="quality:healthDoc:detail:default"  href="javascript:void(0);"  className="gray-btn"onClick={()=>{showDetail(true,haalthDoc.id)}}/>
                                                        <ImallA text="添加档案" permissionCode="quality:healthDoc:add:default"  href="javascript:void(0);"  className="gray-btn"onClick={()=>{this.showAddForm(index)}}/>
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
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                     {detailState&&<HealthDocDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                     {addState&&<HealthDocAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}

                </div>
            </div>
        )
    }
}


HealthDocList.contextTypes = {
    store: React.PropTypes.object
};


function mapDispatchToProps(dispatch){
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HealthDocList);