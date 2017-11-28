import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import BadReactionRepSearchForm from "./BadReactionRepSearchForm";
import BadReactionRepDetail from "./BadReactionRepDetail";
import BadReactionRepAddForm from "./BadReactionRepAddForm";
import BadReactionRepUpdateForm from "./BadReactionRepUpdateForm";

/**
 * 不良反应报告列表
 */
class BadReactionRepList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.badReactionRepList(params, params.page, params.size);
        this.props.portalOperationalAuth(['quality:afterSale:badReactionRep:add','quality:afterSale:badReactionRep:update','quality:afterSale:badReactionRep:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.badReactionRepList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.badReactionRepList(params, page - 1, sizePerPage);
    }

    /*报告类型*/
    regTypeFormat(regType) {
        switch (regType) {
            case "NOVEL":
                return "新的";
            case "SERIOUS":
                return "严重";
            case "COMMON":
                return "一般";
        }
    }

    /*不良反应报告 结果类型*/
    badReactionResultFormat(badReactionResult) {
        switch (badReactionResult) {
            case "RECOVERY":
                return "痊愈";
            case "GETTING_BETTER":
                return "好转";
            case "NOT_GETTING_BETTER":
                return "未好转";
            case "UNKNOWN":
                return "不详";
            case "REMNANT_SYMPTOM":
                return "有后遗症";
            case "DEATH":
                return "死亡";
            default:
                return "痊愈";
        }
    }

    /*新增保存提交*/
    saveSubmit(data) {
        this.props.actions.badReactionRepSaveData(data);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const badReactionRepList = store.getState().todos.page.content || [];
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
                            <h5>不良反应报告</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">售后管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">不良反应报告</a>
                            <BadReactionRepSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:badReactionRep:add" className="added" text="添加" onClick={() => actions.badReactionRepAddModel(true)}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="time">报告日期</th>
                                    <th className="type">报告类型</th>
                                    <th className="name">患者姓名</th>
                                    <th className="ar-name">不良反应名称</th>
                                    <th className="time">发生日期</th>
                                    <th className="time">不良反应结果</th>
                                    <th className="operating" style={{width:"430px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { badReactionRepList.length <= 0 &&
                                <tr >
                                    <th colSpan="7" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {badReactionRepList.map((badReactionRep, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={badReactionRep.repDateString || "暂无"}>{badReactionRep.repDateString || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.regTypeFormat(badReactionRep.repType)}>{this.regTypeFormat(badReactionRep.repType)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={badReactionRep.patientName || "暂无"}>{badReactionRep.patientName || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={badReactionRep.badReaction || "暂无"}>{badReactionRep.badReaction || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={badReactionRep.badReactionOccurTimeString || "暂无"}>{badReactionRep.badReactionOccurTimeString || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.badReactionResultFormat(badReactionRep.badReactionResult)}>{this.badReactionResultFormat(badReactionRep.badReactionResult)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingLeft":0,"paddingRight":"25px","textAlign":"right"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:badReactionRep:update" className="gray-btn" text="修改" onClick={() => actions.badReactionRepUpdateModel(true,badReactionRep)}>修改</ImallA>
                                            <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:badReactionRep:detail" className="gray-btn" text="查看" onClick={() => actions.badReactionRepDetailModal(true,badReactionRep.id)}>查看</ImallA>
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
                    {store.getState().todos.updateState && <BadReactionRepUpdateForm store={store} actions={actions}  onSubmit={(data) => this.props.actions.badReactionRepUpdateData(data)}/>}
                    {store.getState().todos.addState &&　<BadReactionRepAddForm store={store} actions={actions} onSubmit={(data) => {this.saveSubmit(data)}}/>}
                    {store.getState().todos.badReactionRepDetailState && <BadReactionRepDetail store={store} actions={actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

BadReactionRepList.propTypes = {};

BadReactionRepList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(BadReactionRepList);