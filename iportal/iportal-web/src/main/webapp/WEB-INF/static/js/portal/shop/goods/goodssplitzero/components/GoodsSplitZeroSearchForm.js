import React, {Component, PropTypes} from "react";

class GoodsSplitZeroSearchForm extends Component{
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
        let startTimeString = $("#startTimeString").val().trim();
        let endTimeString = $("#endTimeString").val().trim();
        let keyword = $("#keyword").val().trim();
        params = Object.assign(params, {
            keyword: keyword,                   //搜索名称
            startTimeString: startTimeString,   //开始时间
            endTimeString: endTimeString        //结束时间
        });
        actions.goodsSplitZeroSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#startTimeString").val('');
        $("#endTimeString").val('');
        $("#keyword").val('');
        params = Object.assign(params, {
            keyword:"",           //搜索名称
            startTimeString:"",   //开始时间
            endTimeString:""      //结束时间
        });
        actions.goodsSplitZeroSearch(params);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="startTimeString" id="startTimeString" placeholder="拆零时间" className="form-control datepicker" type="text" readOnly />
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="endTimeString" id="endTimeString" className="form-control datepicker" type="text" readOnly />
                    </div>
                </div>
                <div className="search">
                    <input type="text" placeholder="拼音码|名称|编码" id="keyword" />
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        );
    }
}

GoodsSplitZeroSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default GoodsSplitZeroSearchForm;