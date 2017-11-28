import React, {Component, PropTypes} from "react";

class DrugCuringSearchForm extends Component{
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

        $("#sCuringTypeCode").jSelect();
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let sCuringDocumentNum = $("#sCuringDocumentNum").val().trim();
        let sCuringTypeCode = $("#sCuringTypeCode").val().trim();
        let sFromPlanCuringTimeString = $("#sFromPlanCuringTimeString").val().trim();
        let sToPlanCuringTimeString = $("#sToPlanCuringTimeString").val().trim();
        params = Object.assign(params, {
            curingDocumentNum: sCuringDocumentNum,                      //计划养护单号
            curingTypeCode: sCuringTypeCode,                            //养护类型代码
            fromPlanCuringTimeString: sFromPlanCuringTimeString,        //开始时间
            toPlanCuringTimeString: sToPlanCuringTimeString             //结束时间
        });
        actions.drugCuringSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#sCuringDocumentNum").val('');
        $("#sCuringTypeCode").val('');
        $('#sCuringTypeCode-clear').text("养护类型");
        $(".select").jSelectReset();
        $("#sFromPlanCuringTimeString").val('');
        $("#sToPlanCuringTimeString").val('');
        params = Object.assign(params, {
            curingDocumentNum:"",            //计划养护单号
            curingTypeCode:"",               //养护类型代码
            fromPlanCuringTimeString:"",     //开始时间
            toPlanCuringTimeString:""        //结束时间
        });
        actions.drugCuringSearch(params);
    }

    render() {
        let curingTypeCode = [{code: 'FOCUS', name: '重点'}, {code: 'NORMAL', name: '常规'}];

        return(
            <div className="lt-cont">
                <div className="search">
                    <input className="batch" placeholder="计划养护单号" type="text" id='sCuringDocumentNum'/>
                </div>
                <div className="status">
                    <select className="select allSelect1" id='sCuringTypeCode'>
                        <option value=''>养护类型</option>
                        {
                            curingTypeCode.map((item, index)=>{
                                return(
                                    <option value={item.code} key={index}>{item.name}</option>
                                );
                            })
                        }
                    </select>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="datepicker" placeholder="计划时间" className="form-control datepicker" readOnly type="text" id="sFromPlanCuringTimeString"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" className="form-control datepicker" readOnly type="text" id="sToPlanCuringTimeString"/>
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

DrugCuringSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCuringSearchForm;