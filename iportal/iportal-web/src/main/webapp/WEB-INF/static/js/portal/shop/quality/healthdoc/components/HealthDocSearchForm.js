import React, {Component, PropTypes} from 'react';

class HealthDocSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        $("#state").jSelect();
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
        this.props.actions.setSearchParams({});
        const refs = this.refs;
        refs.userName.value="";
        $(".select").jSelectReset();
        this.props.actions.queryList("", 0, 10);
    }


    handleSearch() {
        const {store} = this.context;
        const refs = this.refs;
        const params = store.getState().todos.params || {page: 0, size: 10};
        const userName =refs.userName.value;
        const state =refs.state.value;
        const newParam = {
            userName: userName.trim(),
            state: state,
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.queryList(newParam, 0, 10);

    };

    render() {
        return (
            <div className="lt-cont">
                <input className="batch" name="userName" ref="userName" placeholder="员工账号" type="text"/>
                <div className="status">
                    <select id="state" name="state"  ref="state" className="select" placeholder="状态">
                        <option value="">状态</option>
                        <option value="Y">启用</option>
                        <option value="N">禁用</option>
                    </select>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => { this.onReset()  }}/>
                </div>
            </div>

        )
    }
}

HealthDocSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default HealthDocSearchForm;