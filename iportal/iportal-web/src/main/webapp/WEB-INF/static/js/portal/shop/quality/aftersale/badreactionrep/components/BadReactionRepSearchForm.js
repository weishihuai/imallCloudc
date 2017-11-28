import React, {Component} from "react";

class BadReactionRepSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $("#repStartDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
        $("#repEndDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
        $("#repType").jSelect();
    }

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            repType: $.trim($("#repType").val()),
            patientName: $.trim($("#patientName").val()),
            repStartDateString: $.trim($("#repStartDateString").val()),
            repEndDateString: $.trim($("#repEndDateString").val()),
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.badReactionRepList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#repType").jSelectReset();
        $("#patientName").val('');
        $("#repStartDateString").val('');
        $("#repEndDateString").val('');

        this.props.actions.setSearchParams("");
        this.props.actions.badReactionRepList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="repType" className="select allSelect3">
                        <option value="">报告类型</option>
                        <option value="NOVEL">新的</option>
                        <option value="SERIOUS">严重</option>
                        <option value="COMMON">一般</option>
                    </select>
                </div>
                <div className="search">
                    <input id="patientName" placeholder="患者姓名" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="repStartDateString" id="repStartDateString" placeholder="报告时间" className="form-control datepicker" type="text" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="repEndDateString" id="repEndDateString" className="form-control datepicker" type="text" readOnly/>
                    </div>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

BadReactionRepSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default BadReactionRepSearchForm;