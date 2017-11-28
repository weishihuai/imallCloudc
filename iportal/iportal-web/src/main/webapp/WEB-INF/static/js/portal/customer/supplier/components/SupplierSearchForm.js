import React, {Component, PropTypes} from 'react';

class SupplierSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        $("#searchState").jSelect();
        $("#approveStateCode").jSelect();
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
        $("#searchState").jSelectReset();
        $("#approveStateCode").jSelectReset();
        $('input[name="startDate"]').val("");//日期-开始
        $('input[name="endDate"]').val("");//日期-结束
        $('input[name="supplierNm"]').val("");// 输入

        this.props.actions.supplierList({}, 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const pinyin = $('input[name="pinyin"]').val();//拼音
        const state = this.refs.searchState.value;//固定下拉菜单
        const approveStateCode = this.refs.approveStateCode.value;//固定下拉菜单
        const startDate = $('input[name="startDate"]').val();//日期-开始
        const endDate = $('input[name="endDate"]').val();//日期-结束


        const newParam = {
            pinyin: pinyin.trim(),
            state: state,
            approveStateCode: approveStateCode,
            startTimeString: startDate,
            endTimeString: endDate,

        };

        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.supplierList(newParam, newParam.page, newParam.size);

    };

    render() {
        const {store} = this.context;
        return (

            <div className="lt-cont">
                <input className="batch" name="pinyin" placeholder="拼音码|名称|编码" type="text"/>
                <div className="status">
                    <select id="searchState" name="searchState" ref="searchState"className="select " placeholder="供应状态">
                        <option value="">启用状态</option>
                        <option value="Y">启用</option>
                        <option value="N">禁用</option>
                    </select>
                </div>
                <div className="status">
                    <select id="approveStateCode" name="approveStateCode" ref="approveStateCode"className="select" placeholder="供应状态">
                        <option value="">审核状态</option>
                        <option value="WAIT_APPROVE">待审核</option>
                        <option value="PASS_APPROVE">已审核</option>
                        <option value="REJECTED">已驳回</option>
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

                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => { this.onReset()  }}/>
                </div>
            </div>

        )
    }
}

SupplierSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SupplierSearchForm;