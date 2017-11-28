import React, {Component, PropTypes} from "react";

class DrugCheckSearchForm extends Component{
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

        $("#sCheckTypeCode").jSelect();
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let sCheckDocumentNum = $("#sCheckDocumentNum").val().trim();
        let sCheckTypeCode = $("#sCheckTypeCode").val().trim();
        let fromPlanCheckTimeString = $("#fromPlanCheckTimeString").val().trim();
        let toPlanCheckTimeString = $("#toPlanCheckTimeString").val().trim();
        params = Object.assign(params, {
            checkDocumentNum: sCheckDocumentNum,                    //检查单号
            checkTypeCode: sCheckTypeCode,                          //检查类型代码
            fromPlanCheckTimeString: fromPlanCheckTimeString,       //开始时间
            toPlanCheckTimeString: toPlanCheckTimeString            //结束时间
        });
        actions.drugCheckSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#sCheckDocumentNum").val('');
        $("#sCheckTypeCode").val('');
        $('#sCheckTypeCode-clear').text("检查类型");
        $(".select").jSelectReset();
        $("#fromPlanCheckTimeString").val('');
        $("#toPlanCheckTimeString").val('');
        params = Object.assign(params, {
            checkDocumentNum:"",            //检查单号
            checkTypeCode:"",               //检查类型代码
            fromPlanCheckTimeString:"",     //开始时间
            toPlanCheckTimeString:""        //结束时间
        });
        actions.drugCheckSearch(params);
    }

    render() {
        let checkTypeCode = [{code: 'FOCUS', name: '重点'}, {code: 'NORMAL', name: '常规'}];

        return (
            <div className="lt-cont">
                <div className="search">
                    <input className="batch" placeholder="检查单号" type="text" id="sCheckDocumentNum"/>
                </div>
                <div className="status">
                    <select id="sCheckTypeCode" className="select allSelect1">
                        <option value=''>检查类型</option>
                        {
                            checkTypeCode.map((item, index)=>{
                                return(
                                    <option value={item.code} key={index}>{item.name}</option>
                                );
                            })
                        }
                    </select>
                </div>

                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="fromPlanCheckTimeString" placeholder="计划时间" className="form-control datepicker" type="text" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="toPlanCheckTimeString" className="form-control datepicker" type="text" readOnly/>
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

DrugCheckSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCheckSearchForm;