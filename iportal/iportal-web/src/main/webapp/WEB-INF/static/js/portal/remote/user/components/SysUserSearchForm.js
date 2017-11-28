import React, {Component, PropTypes} from 'react';

class SysUserSearchForm extends Component {

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
        $('input[name="shopNm"]').val("");//拼音
        $('input[name="name"]').val("");//拼音
        $('input[name="mobile"]').val("");//拼音
        $("#searchState").jSelectReset();
        // $('select[name="searchState"]').val("");//固定下拉菜单
        $('input[name="startDate"]').val("");//日期-开始
        $('input[name="endDate"]').val("");//日期-结束

        this.props.actions.sysUserList({}, 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const shopNm = $('input[name="shopNm"]').val();//拼音
        const name = $('input[name="name"]').val();//拼音
        const mobile = $('input[name="mobile"]').val();//拼音
        const state = this.refs.searchState.value;//固定下拉菜单
        const startDate = $('input[name="startDate"]').val();//日期-开始
        const endDate = $('input[name="endDate"]').val();//日期-结束

        const newParam = {
            shopNm: shopNm.trim(),
            name: name.trim(),
            mobile: mobile.trim(),
            state: state,
            startTimeString: startDate,
            endTimeString: endDate,
        };

        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.sysUserList(newParam, 0, 10);

    };

    render() {
        const {store} = this.context;
        return (

            <div className="lt-cont">
                <input className="batch" name="shopNm" placeholder="门店名称" type="text" style={{display: "none"}}/>
                <input className="batch" name="name" placeholder="用户姓名|账号" type="text"/>
                <input className="batch" name="mobile" placeholder="联系方式" type="text"/>
                <div className="status">
                    <select id="searchState" name="searchState" ref="searchState"className="select " placeholder="供应状态">
                        <option value="">状态</option>
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
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => { this.onReset()  }}/>
                </div>
            </div>

        )
    }
}

SysUserSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SysUserSearchForm;