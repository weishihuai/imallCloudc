import React, {Component, PropTypes} from "react";

class LimitBuyRegisterSearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount(){
        $(".datepicker").on("click",function(e){
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
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let sellOrderCode = $("#sellOrderCode").val().trim();
        let patientNm = $("#patientNm").val().trim();
        let registerStartDateString = $("#registerStartDateString").val().trim();
        let registerEndDateString = $("#registerEndDateString").val().trim();
        params = Object.assign(params, {
            sellOrderCode:sellOrderCode,                       //订单编号
            patientNm:patientNm,                               //患者名称
            registerStartDateString:registerStartDateString,   //开始时间
            registerEndDateString:registerEndDateString        //结束时间
        });
        actions.limitBuyRegisterSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#sellOrderCode").val('');
        $("#patientNm").val('');
        $("#registerStartDateString").val('');
        $("#registerEndDateString").val('');
        params = Object.assign(params, {
            sellOrderCode:"",             //订单编号
            patientNm:"",                 //患者名称
            registerStartDateString:"",   //开始时间
            registerEndDateString:""      //结束时间
        });
        actions.limitBuyRegisterSearch(params);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input type="text" placeholder="订单编号" id="sellOrderCode"/>
                </div>
                <div className="search">
                    <input type="text" placeholder="患者名称" id="patientNm"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="registerStartDateString" placeholder="登记日期" className="form-control datepicker" type="text" readOnly="readOnly"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="registerEndDateString" className="form-control datepicker" type="text" readOnly="readOnly"/>
                    </div>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        );
    }
}

LimitBuyRegisterSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default LimitBuyRegisterSearchForm;