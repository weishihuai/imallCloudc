import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ConsultServiceAddForm from "./ConsultServiceAddForm";
import ConsultServiceDetail from "./ConsultServiceDetail";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";

class ConsultServiceList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        this.props.actions.queryList(0, 10, store.getState().todos.params);
        this.props.portalOperationalAuth(['pharmaceuticalCare:consultRegister:add:default', 'pharmaceuticalCare:consultRegister:detail:default']);
    }

    componentDidMount() {
        $("#datetimepicker1").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });

        $("#datetimepicker2").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    search() {
        const {store} = this.context;
        let {params} = store.getState().todos;
        let fromDateString = $('#datetimepicker1').val();
        let toDateString = $('#datetimepicker2').val();
        params.searchText= $.trim($('#searchText').val());
        params.fromDateString= fromDateString;
        params.toDateString= toDateString;

        this.props.actions.queryList(0, params.size, store.getState().todos.params);
        this.props.actions.setSearchParam(params);

    }

    resetSearch() {
        $('#searchText').val("");
        $('#datetimepicker1').val("");
        $('#datetimepicker2').val("");
        const {store} = this.context;
        this.props.actions.queryList(0, 10, {});
        this.props.actions.setSearchParam("");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        this.props.actions.queryList(0, sizePerPage,store.getState().todos.params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        this.props.actions.queryList(page - 1, sizePerPage,store.getState().todos.params,);
    }

    sexStatCode(code){
        switch (code){
            case "SECRET":
                return "保密";
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
        }
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.page;
        let consultList = page.content || [];
        const number = page.number + 1;
        const actions = this.props.actions;
        const {consultAddForm, consultDetail, saveConsultService} = this.props.actions;

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
                            <h5>咨询登记</h5>
                            <a href="javascript:void(0);">药学服务</a>
                            <span>></span>
                            <a href="javascript:void(0);">咨询登记</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input placeholder="拼音码|名称|编码" type="text" id="searchText"/>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input id="datetimepicker1" placeholder="咨询时间" className="form-control" type="text" readOnly="readOnly"/>
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input id="datetimepicker2" className="form-control" type="text" readOnly="readOnly"/>
                                    </div>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" value="查询" onClick={()=>this.search()}/>
                                    <input className="sr-reset" type="button" value="重置" onClick={()=>this.resetSearch()}/>
                                </div>
                            </div>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="pharmaceuticalCare:consultRegister:add:default" className="added" text="添加" onClick={()=>consultAddForm(true)}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="vip-name">会员卡号</th>
                                        <th className="name">患者姓名</th>
                                        <th className="age">年龄</th>
                                        <th className="gender">性别</th>
                                        <th className="phone-number">手机号</th>
                                        <th className="medical-history">过往病史</th>
                                        <th className="pharmacist">咨询药师</th>
                                        <th className="date">咨询时间</th>
                                        <th className="problem-description">问题描述</th>
                                        <th className="experts-answer">专家解答</th>
                                        <th className="operating" style={{width: "430px"}}>操作</th>
                                    </tr>
                                </thead>
                                {consultList.length <= 0 &&
                                    <tr >
                                        <td colSpan="11" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                }
                                <tbody>
                                {consultList.map((consult,index)=> {
                                    return (
                                        <tr key={index}>
                                            <td>
                                                <div className="td-cont" title={consult.memberCardNum}>{consult.memberCardNum}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.patientName}>{consult.patientName}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.age}>{consult.age}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={this.sexStatCode(consult.sex)}>{this.sexStatCode(consult.sex)}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.mobile}>{consult.mobile}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.prevMedicalHistory}>{consult.prevMedicalHistory}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.consultPharmacist}>{consult.consultPharmacist}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.consultTimeString}>{consult.consultTimeString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.questionDescr}>{consult.questionDescr}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={consult.expertAnswer}>{consult.expertAnswer}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                    <ImallA href="javascript:void(0);" permissionCode="pharmaceuticalCare:consultRegister:detail:default" className="gray-btn" text="查看" onClick={()=>consultDetail(consult.id, true)} />
                                                </div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <IMallPaginationBar options={options} actions={actions}/>
                {store.getState().todos.consultAddForm && <ConsultServiceAddForm store={store} actions={actions} onSubmit={(data) => saveConsultService(data)}/>}
                {store.getState().todos.consultDetailModal && <ConsultServiceDetail store={store} actions={actions} />}
            </div>
        );
    }
}

ConsultServiceList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {state};
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ConsultServiceList);