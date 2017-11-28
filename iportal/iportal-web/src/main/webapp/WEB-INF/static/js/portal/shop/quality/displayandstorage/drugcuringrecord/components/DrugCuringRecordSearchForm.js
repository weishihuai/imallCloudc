import React, {Component, PropTypes} from "react";

class DrugCuringRecordSearchForm extends Component{
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
        let keyword = $("#keyword").val().trim();
        let curingPlanNum = $("#curingPlanNum").val().trim();
        let startCuringDateString = $("#startCuringDateString").val().trim();
        let endCuringDateString = $("#endCuringDateString").val().trim();
        params = Object.assign(params, {
            keyword: keyword,
            curingPlanNum: curingPlanNum,                             //检查 单码
            startCuringDateString: startCuringDateString,     //检查开始日期
            endCuringDateString: endCuringDateString          //检查结束日期
        });
        actions.drugCuringRecordSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#keyword").val('');
        $("#curingPlanNum").val('');
        $("#startCuringDateString").val('');
        $("#endCuringDateString").val('');
        params = Object.assign(params, {
            keyword:"",
            curingPlanNum:"",          //养护计划单号
            startCuringDateString:"",  //养护开始日期
            endCuringDateString:""     //养护结束日期
        });
        actions.drugCuringRecordSearch(params);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input placeholder="拼音码|名称|编码" type="text" id="keyword"/>
                </div>
                <div className="search">
                    <input placeholder="养护计划单号" type="text" id="curingPlanNum"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input id="startCuringDateString" placeholder="养护日期" className="form-control datepicker" type="text" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input id="endCuringDateString" className="form-control datepicker" type="text" readOnly/>
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

DrugCuringRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCuringRecordSearchForm;