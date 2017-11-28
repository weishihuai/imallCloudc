import React, {Component, PropTypes} from 'react';

class SupplierQualityApproveSearchForm extends Component {

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
        $('input[name="pinyin"]').val("");//拼音
        $("#searchState").jSelectReset();//审核类型
        $('input[name="startDate"]').val("");//日期-开始
        $('input[name="endDate"]').val("");//日期-结束
         this.props.actions.supplierList({}, 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const pinyin = $('input[name="pinyin"]').val();//拼音
        const state = $('select[name="searchState"]').val();//固定下拉菜单
        const startDate = $('input[name="startDate"]').val();//日期-开始
        const endDate = $('input[name="endDate"]').val();//日期-结束


        const newParam = {
            searchValue: pinyin.trim(),
            state: state,
            startTimeString: startDate,
            endTimeString: endDate,

        };

        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.supplierList(newParam, 0, 10);

    };

    render() {
        const {store} = this.context;
        return (

            <div className="lt-cont">

                <div className="status">
                    <select id="searchState" name="searchState" className="select">
                        <option value="">全部</option>
                        <option value="WAIT_APPROVE">待审核</option>
                        <option value="PASS_APPROVE">已审核</option>
                        <option value="REJECTED">已驳回</option>
                    </select>
                </div>
                <input className="batch" name="pinyin" placeholder="拼音码|名称|编码" type="text"/>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input type="text" name="startDate" className="form-control datepicker " placeholder="审核时间" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input type="text" name="endDate" className="form-control datepicker " placeholder="" readOnly/>
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

SupplierQualityApproveSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SupplierQualityApproveSearchForm;