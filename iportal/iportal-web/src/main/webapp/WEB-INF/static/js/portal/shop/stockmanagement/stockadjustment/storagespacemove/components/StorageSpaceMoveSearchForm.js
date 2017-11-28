import React, {Component} from "react";

/*货位移动 搜索表单*/
class StorageSpaceMoveSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
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

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            moveManName: $.trim($("#moveManName").val()),
            moveOrderNum: $.trim($("#moveOrderNum").val()),
            moveStartTimeString: $("#moveStartTimeString").val(),
            moveEndTimeString: $("#moveEndTimeString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.storageSpaceMoveList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#moveOrderNum").val('');
        $("#moveManName").val('');
        $("#moveStartTimeString").val('');
        $("#moveEndTimeString").val('');
        this.props.actions.setSearchParams("");
        this.props.actions.storageSpaceMoveList({}, 0, 10);
    }

    render() {
        return (
        <div className="lt-cont">
            <div className="search">
                <input id="moveManName" className="batch" placeholder="移动人" type="text"/>
            </div>
            <div className="search">
                <input id="moveOrderNum" className="batch" placeholder="移动单号" type="text"/>
            </div>
            <div className="sel-date">
                <div className="form-group float-left w140">
                    <input name="moveStartTimeString" id="moveStartTimeString" placeholder="移动时间" readOnly className="form-control datepicker" type="text"/>
                </div>
                <div className="float-left form-group-txt">至</div>
                <div className="form-group float-left w140">
                    <input name="moveEndTimeString" id="moveEndTimeString" className="form-control datepicker" readOnly type="text"/>
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

StorageSpaceMoveSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default StorageSpaceMoveSearchForm;