import React, {Component, PropTypes} from "react";

class TemperatureMoistureMonitorRecordSearchForm extends Component {
    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let storageSpaceId = $('#storageSpaceId').val().trim();
        let fromMonitorDateString = $('#fromMonitorDateString').val().trim();
        let toMonitorDateString = $('#toMonitorDateString').val().trim();
        params = Object.assign(params, {
            storageSpaceId: storageSpaceId,
            fromMonitorDateString: fromMonitorDateString,
            toMonitorDateString: toMonitorDateString
        });
        actions.temperatureMoistureMonitorRecordSearch(params);
    }

    resetForm(){
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $('#fromMonitorDateString').val('');
        $('#toMonitorDateString').val('');
        $("#storageSpaceId").val('');
        $('#storageSpaceId-clear').text("货位");
        params = Object.assign(params, {
            storageSpaceId: '',
            fromMonitorDateString: '',
            toMonitorDateString: ''
        });
        actions.temperatureMoistureMonitorRecordSearch(params);
    }

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        require('searchableSelect');
        this.props.actions.listAllEnableStorageSpace(() => $("#storageSpaceId").searchableSelect());
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

    render() {
        const {store} = this.context;
        const storageSpace = store.getState().todos.storageSpace||[];

        return(
            <div className="lt-cont">
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="fromMonitorDateString" id="fromMonitorDateString" placeholder="日期" className="form-control datepicker" type="text" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="toMonitorDateString" id="toMonitorDateString" className="form-control datepicker" type="text" readOnly/>
                    </div>
                </div>
                <div className="status" >
                    <select id="storageSpaceId" className="select">
                        <option value="">货位</option>
                        {storageSpace.map((item, index)=>{
                            return (
                                <option key={index} value={item.id}>{item.storageSpaceNm}</option>
                            )
                        })}
                    </select>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        )
    }
}

TemperatureMoistureMonitorRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default TemperatureMoistureMonitorRecordSearchForm;