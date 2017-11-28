import React, {Component, PropTypes} from "react";

class DrugCheckRecordSearchForm extends Component{
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
        let checkNum = $("#checkNum").val().trim();
        let startCheckTimeString = $("#startCheckTimeString").val().trim();
        let endCheckTimeString = $("#endCheckTimeString").val().trim();
        params = Object.assign(params, {
            keyword: keyword,
            checkNum: checkNum,                             //检查 单码
            startCheckTimeString: startCheckTimeString,     //检查开始日期
            endCheckTimeString: endCheckTimeString          //检查结束日期
        });
        actions.drugCheckRecordSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#keyword").val('');
        $("#checkNum").val('');
        $("#startCheckTimeString").val('');
        $("#endCheckTimeString").val('');
        params = Object.assign(params, {
            keyword:"",
            checkNum:"",                //检查 单码
            startCheckTimeString:"",    //检查开始日期
            endCheckTimeString:""       //检查结束日期
        });
        actions.drugCheckRecordSearch(params);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input placeholder="拼音码|名称|编码" type="text" id="keyword"/>
                </div>
                <div className="search">
                    <input placeholder="检查单码" type="text" id="checkNum"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input id="startCheckTimeString" placeholder="检查日期" className="form-control datepicker" type="text" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input id="endCheckTimeString" className="form-control datepicker" type="text" readOnly/>
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

DrugCheckRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCheckRecordSearchForm;