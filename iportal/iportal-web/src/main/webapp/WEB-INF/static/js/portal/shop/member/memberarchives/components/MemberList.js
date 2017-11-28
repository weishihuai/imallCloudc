import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import MemberSearchForm from "./MemberSearchForm";
import ShopMemberAddForm from "./ShopMemberAddForm";
import ShopMemberDetail from "./ShopMemberDetail";
import ShopMemberUpdateForm from "./ShopMemberUpdateForm";
import ImallA from '../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../common/imallbutton/actions';

/**
 * 会员档案列表
 */
class MemberList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.memberList(params, params.page, params.size);
        this.props.portalOperationalAuth(['member:member:add','member:member:update','member:member:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.memberList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page: page - 1,size:sizePerPage}));
        this.props.actions.memberList(params, page - 1, sizePerPage);
    }

    memberAdd(row) {
        this.props.actions.memberAddModel(true,row);
    }

    memberEdit(row) {
        this.props.actions.memberEditModel(true,row);
    }

    sexCodeFormat(sexCode) {
        switch (sexCode) {
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
            case "SECRET":
                return "保密";
        }
    }

    cardUseStateCodeFormat(cardUseStateCode) {
        switch (cardUseStateCode) {
            case "NORMAL":
                return "正常";
            case "DISABLED":
                return "禁用";
            default:
                return "暂无";
        }
    }

    memberStateCodeFormat(memberStateCode) {
        switch (memberStateCode) {
            case "NORMAL":
                return "正常";
            case "DISABLED":
                return "禁用";
        }
    }

    render() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        const page = store.getState().todos.page;
        const number = page.number + 1;
        const memberList = store.getState().todos.page.content || [];
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
                            <h5>会员档案管理</h5>
                            <a href="javascript:void(0);">会员管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">会员档案管理</a>
                            <MemberSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="member:member:add" className="added" text="添加" onClick={()=> this.memberAdd()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="vip-name">会员卡号</th>
                                    <th className="name">会员姓名</th>
                                    <th className="phone-number">手机号</th>
                                    <th className="gender">性别</th>
                                    <th className="state">会员状态</th>
                                    <th className="state">卡使用状态</th>
                                    <th className="date">创建时间</th>
                                    <th className="operating" style={{width:"430px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { memberList.length <= 0 &&
                                <tr >
                                    <th colSpan="7" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {memberList.map((member, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={member.memberCardNum}>{member.memberCardNum}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={member.name}>{member.name}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={member.mobile}>{member.mobile}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.sexCodeFormat(member.sexCode)}>{this.sexCodeFormat(member.sexCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.memberStateCodeFormat(member.memberStateCode)}>{this.memberStateCodeFormat(member.memberStateCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.cardUseStateCodeFormat(member.cardUseStateCode)}>{this.cardUseStateCodeFormat(member.cardUseStateCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={member.createDateString}>{member.createDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingLeft":0,"paddingRight":"25px","textAlign":"right"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="member:member:update" className="gray-btn" text="修改" onClick={()=> this.memberEdit(member)}>修改</ImallA>
                                            <ImallA href="javascript:void(0);" permissionCode="member:member:detail" className="gray-btn" text="查看" onClick={()=>this.props.actions.memberDetailModal(true,member.id)}>查看</ImallA>
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
                    {store.getState().todos.addState && <ShopMemberAddForm store={store} actions={this.props.actions}/>}
                    {store.getState().todos.memberDetailState && <ShopMemberDetail store={store} actions={this.props.actions}/>}
                    {store.getState().todos.editState && <ShopMemberUpdateForm store={store} actions={this.props.actions} />}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

MemberList.propTypes = {};

MemberList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MemberList);