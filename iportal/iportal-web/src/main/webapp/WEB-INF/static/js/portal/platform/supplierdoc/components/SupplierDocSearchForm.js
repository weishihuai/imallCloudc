import React, {Component, PropTypes} from 'react';

class SupplierDocSearchForm extends Component {

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
        $(".select").jSelectReset();
        $('select[name="searchState"]').val("");//固定下拉菜单
        $('input[name="startDate"]').val("");//日期-开始
        $('input[name="endDate"]').val("");//日期-结束
        $('input[name="supplierNm"]').val("");// 输入

        this.props.actions.supplierList({}, 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const state = $('select[name="searchState"]').val();//固定下拉菜单
        const startDate = $('input[name="startDate"]').val();//日期-开始
        const endDate = $('input[name="endDate"]').val();//日期-结束
        const supplierNm = $('input[name="supplierNm"]').val();// 输入

        const newParam = {
            state: state,
            startTimeString: startDate,
            endTimeString: endDate,
            supplierNm: supplierNm.trim()
        };

        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.supplierList(newParam, 0, 10);

    };

    render() {

        return (

            <div className="lt-cont">

                <div className="status">
                    <select id="searchState" name="searchState" className="select " placeholder="供应状态">
                        <option value="">供应状态</option>
                        <option value="N">禁用</option>
                        <option value="Y">启用</option>
                    </select>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input type="text" name="startDate" className="form-control datepicker " placeholder="创建时间" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input type="text" name="endDate" className="form-control datepicker " placeholder="" readOnly/>
                    </div>
                </div>
                <input type="text" className="batch" name="supplierNm" placeholder="供应商名称"/>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => {this.onReset()}}/>
                </div>
            </div>

        )
    }
}

SupplierDocSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SupplierDocSearchForm;