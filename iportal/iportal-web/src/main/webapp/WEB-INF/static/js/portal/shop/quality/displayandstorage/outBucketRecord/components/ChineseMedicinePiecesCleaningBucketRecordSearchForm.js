import React, {Component, PropTypes} from 'react';

class ChineseMedicinePiecesCleaningBucketRecordSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        $("#searchState").jSelect();
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                }
            });
        });
    }

    onReset() {
        this.props.actions.setSearchParams("");
        this.refs.searchValue.value="";
        this.refs.batch.value="";
        this.refs.cleaningBucketManNm.value="";
        this.refs.startDate.value="";
        this.refs.endDate.value="";
        this.props.actions.queryList({}, 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const searchValue = this.refs.searchValue.value;
        const batch = this.refs.batch.value;
        const cleaningBucketManNm =this.refs.cleaningBucketManNm.value;
        const startDate = this.refs.startDate.value;
        const endDate =this.refs.endDate.value;

        const newParam = {
            searchValue: searchValue.trim(),
            batch: batch.trim(),
            cleaningBucketManNm: cleaningBucketManNm.trim(),
            startTimeString: startDate.trim(),
            endTimeString: endDate.trim()
        };

        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.queryList(newParam, 0, 10);

    };

    render() {
        const {store} = this.context;
        return (

            <div className="lt-cont">
                <input className="batch" ref="searchValue"  id="searchValue" placeholder="拼音码|名称" type="text"/>
                <input className="batch" ref="batch" id="batch"  placeholder="批号" type="text"/>
                <input className="batch" ref="cleaningBucketManNm"  id="cleaningBucketManNm" placeholder="清斗人" type="text"/>

                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input type="text" ref="startDate"  id="startDate"  className="form-control datepicker " placeholder="清斗时间" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input type="text" ref="endDate" id="endDate" className="form-control datepicker " placeholder="" readOnly/>
                    </div>
                </div>

                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => { this.onReset()  }}/>
                </div>
            </div>

        )
    }
}

ChineseMedicinePiecesCleaningBucketRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default ChineseMedicinePiecesCleaningBucketRecordSearchForm;