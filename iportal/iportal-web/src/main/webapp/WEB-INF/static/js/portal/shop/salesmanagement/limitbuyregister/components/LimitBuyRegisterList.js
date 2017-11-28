import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import LimitBuyRegisterSearchForm from "./LimitBuyRegisterSearchForm";
import LimitBuyRegisterAddForm from "./LimitBuyRegisterAddForm";
import LimitBuyRegisterEditForm from "./LimitBuyRegisterEditForm";
import LimitBuyRegisterDetail from "./LimitBuyRegisterDetail";

class LimitBuyRegisterList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["sales:limitBuyRegister:add:default", "sales:limitBuyRegister:update:default", "sales:limitBuyRegister:detail:default"]);
        this.props.actions.limitBuyRegisterList(params, params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    showAddForm(){
        this.props.actions.showAddForm(true);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.limitBuyRegisterSetSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.limitBuyRegisterList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.limitBuyRegisterSetSearchParams(Object.assign({}, params, {page:page - 1,size:sizePerPage}));
        this.props.actions.limitBuyRegisterList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params} = store.getState().todos;
        const {page} = store.getState().todos;
        const number=page.number+1;
        const record = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size>0?page.size:10,
            sizePerPageList: page.totalElements>0?[10, 20, 40]:[],
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
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                {store.getState().todos.isShowAdd && <LimitBuyRegisterAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)} /> }
                {store.getState().todos.isShowEdit && <LimitBuyRegisterEditForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)} /> }
                {store.getState().todos.isShowDetail && <LimitBuyRegisterDetail store={store} actions={this.props.actions} /> }
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>限购登记</h5>
                            <a href="javascript:void(0);">销售管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">限购登记</a>
                            <LimitBuyRegisterSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="sales:limitBuyRegister:add:default" className="added" onClick={this.showAddForm.bind(this)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="odd-number">销售订单单号</th>
                                    <th className="name">患者名称</th>
                                    <th className="phone-number">手机</th>
                                    <th className="id-number">身份证</th>
                                    <th className="name">会员卡号码</th>
                                    <th className="time">登记日期</th>
                                    <th className="operating" style={{'width': 100 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {record.length <= 0 &&
                                    <tr>
                                        <th colSpan="7" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record, index) => {
                                        return (
                                            <tr key={record.id}>
                                                <td>
                                                    <div className="td-cont" title={record.sellOrderCode}>{record.sellOrderCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.patientNm}>{record.patientNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.mobile}>{record.mobile}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.identityCard}>{record.identityCard}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.memberCardNum}>{record.memberCardNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.registerDateString}>{record.registerDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="sales:limitBuyRegister:update:default" className="gray-btn" text="修改" onClick={() => this.props.actions.showEditForm(true, record.id)}  />
                                                        <Imalla href="javascript:void(0);" permissionCode="sales:limitBuyRegister:detail:default" className="gray-btn" text="查看" onClick={() => this.props.actions.showDetail(true, record.id)}  />
                                                    </div>
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
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        );
    }
}

LimitBuyRegisterList.propTypes = {
    actions: PropTypes.object.isRequired
};

LimitBuyRegisterList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(LimitBuyRegisterList);