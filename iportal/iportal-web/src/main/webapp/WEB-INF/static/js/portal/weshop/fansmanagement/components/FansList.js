import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import FansSearchForm from "./FansSearchForm";
import FansUpdateRemarkForm from "./FansUpdateRemarkForm";

/**
 * 粉丝列表
 */
class FansList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.fansList(params, params.page, params.size);
        this.props.portalOperationalAuth(['weshop:fans:list:remark']);
        this.props.actions.countFansTotal();
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.fansList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.fansList(params, page - 1, sizePerPage);
    }

    /*性别*/
    static sexCodeFormat(sexCode) {
        switch (sexCode) {
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
            case "SECRET":
                return "保密";
            default:
                return "暂无";
        }
    }

    static fansSourceCodeFormat(fansSourceCode) {
        switch (fansSourceCode) {
            case "SHOP":
                return "门店";
            case "OTHER":
                return "其他";
            default:
                return "暂无";
        }
    }

    static isMemberFormat(isMember) {
        switch (isMember) {
            case "Y":
                return "会员";
            case "N":
                return "非会员";
            default:
                return "暂无";
        }
    }

    submitData(data) {
        const saveData = {
            remark:data.remark,
            id:data.id,
        };
        this.props.actions.updateFansRemark(saveData)
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const fansList = store.getState().todos.page.content || [];
        const fansTotalData = store.getState().todos.fansTotalData || {};
        const totalPages = page.totalPages;
        const totalLength = fansList.length;

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

        function checkRemarkIsNull(remark) {
            return remark == null || remark == "";
        }

        function formatButton(text,fans,actions) {
            return <ImallA href="javascript:void(0);" permissionCode="weshop:fans:list:remark" className="gray-btn" text={text} onClick={() => actions.fansUpdateRemarkModel(true,fans,fans.id)}>{text}</ImallA>
        }

        return (
            <div className="main-box">
                <div className="main fans-main">
                    <div className="mt">
                        <div className="mt-lt" style={{paddingRight: "0px"}}>
                            <h5>粉丝管理</h5>
                            <a href="javascript:void(0);">微店管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">粉丝管理</a>
                            <div className="lt-cont lt-cont-f">
                                <div className="lt-cont-f-inner">
                                    <div className="lt-item lt-item-first">
                                        <p>粉丝总数</p>
                                        <span>{fansTotalData.fansTotalCount || 0}</span>
                                    </div>
                                    <div className="lt-item">
                                        <p>会员数</p>
                                        <span>{fansTotalData.fansIsMemberTotalCount || 0}</span>
                                    </div>
                                    <div className="lt-item">
                                        <p>非会员数</p>
                                        <span>{fansTotalData.fansIsNotMemberTotalCount || 0}</span>
                                    </div>
                                </div>
                            </div>
                            <FansSearchForm store={store} actions={actions}/>
                            <div className="lt-bot">
                            </div>
                        </div>
                        <div className="mt-rt">
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="name">姓名</th>
                                    <th className="phone-number" style={{width:"125px"}}>手机号码</th>
                                    <th className="age" style={{width:"60px"}}>性别</th>
                                    <th className="vip-number1">会员卡</th>
                                    <th className="address">住址</th>
                                    <th className="buy-number">购买次数</th>
                                    <th className="wx-number" style={{width:"140px"}}>微信号</th>
                                    <th className="name" style={{width:"125px"}}>微信昵称</th>
                                    <th className="fans-source">粉丝来源</th>
                                    <th className="status">粉丝身份</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { fansList.length <= 0 &&
                                <tr >
                                    <th colSpan="11" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {fansList.map((fans, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={fans.fansName}>{fans.fansName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.mobile}>{fans.mobile}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={FansList.sexCodeFormat(fans.sexCode)}>{FansList.sexCodeFormat(fans.sexCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.memberCardNum || "暂无"}>{fans.memberCardNum || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.homeAddr || "暂无"}>{fans.homeAddr || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.buyTimes}>{fans.buyTimes}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.openId}>{fans.openId}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={fans.nickName}>{fans.nickName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={FansList.fansSourceCodeFormat(fans.fansSourceCode)}>{FansList.fansSourceCodeFormat(fans.fansSourceCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={FansList.isMemberFormat(fans.isMember)}>{FansList.isMemberFormat(fans.isMember)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont td-cont-note" style={{paddingLeft: "0px", paddingRight: "25px",textAlign: "right"}}>
                                            {checkRemarkIsNull(fans.remark) ? formatButton("备注",fans,actions) : formatButton("修改备注",fans,actions)}
                                            {(fans.id === store.getState().todos.data.id) && store.getState().todos.updateState && <FansUpdateRemarkForm store={store} actions={actions} index={index} totalLength={totalLength} totalPages={totalPages} onSubmit={(data) => this.submitData(data)}/>}
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
                </div>
            </div>
        )
    }
}

FansList.propTypes = {};

FansList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FansList);