import React, {Component, PropTypes} from "react";

class PrescriptionSearchForm extends Component{
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
        let params = store.getState().prescriptionTodos.params || {page: 0, size: 10};
        let prescriptionSellOrderCode = $("#sPrescriptionSellOrderCode").val().trim();
        let patientNm = $("#sPatientNm").val().trim();
        let startPrescriptionDateString = $("#sStartPrescriptionDateString").val().trim();
        let endPrescriptionDateString = $("#sEndPrescriptionDateString").val().trim();
        params = Object.assign(params, {
            prescriptionSellOrderCode: prescriptionSellOrderCode,                       //处方销售订单编码
            patientNm: patientNm,                                                       //患者名称
            startPrescriptionDateString: startPrescriptionDateString,                   //开始时间
            endPrescriptionDateString: endPrescriptionDateString                        //结束时间
        });
        actions.prescriptionSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().prescriptionTodos.params || {page: 0, size: 10};
        $("#sPrescriptionSellOrderCode").val('');
        $("#sPatientNm").val('');
        $("#sStartPrescriptionDateString").val('');
        $("#sEndPrescriptionDateString").val('');
        params = Object.assign(params, {
            prescriptionSellOrderCode:"",                       //处方销售订单编码
            patientNm:"",                                       //患者名称
            startPrescriptionDateString:"",                     //开始时间
            endPrescriptionDateString:""                        //结束时间
        });
        actions.prescriptionSearch(params);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input type="text" placeholder="订单编号" id="sPrescriptionSellOrderCode"/>
                </div>
                <div className="search">
                    <input type="text" placeholder="患者名称" id="sPatientNm"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="sStartPrescriptionDateString" placeholder="处方日期" className="form-control datepicker" type="text"readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="sEndPrescriptionDateString" className="form-control datepicker" type="text" readOnly/>
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

PrescriptionSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default PrescriptionSearchForm;