import React, {Component} from "react";

class FansSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $("#fansSourceCode").jSelect();
        $("#buyTimes").jSelect();
        $("#isMember").jSelect();
    }

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            fansName: $.trim($("#fansName").val()),
            mobile: $.trim($("#mobile").val()),
            nickName: $.trim($("#nickName").val()),
            fansSourceCode: $.trim($("#fansSourceCode").val()),
            buyTimes: $.trim($("#buyTimes").val()),
            isMember: $.trim($("#isMember").val()),
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.fansList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#fansSourceCode").jSelectReset();
        $("#buyTimes").jSelectReset();
        $("#isMember").jSelectReset();
        $("#fansName").val('');
        $("#mobile").val('');
        $("#nickName").val('');
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="fansName" name="fansName" placeholder="姓名" type="text"/>
                </div>
                <div className="search">
                    <input id="mobile" name="mobile" placeholder="手机号码" type="text"/>
                </div>
                <div className="search">
                    <input id="nickName" name="nickName" placeholder="微信昵称" type="text"/>
                </div>
                <div className="status">
                    <select id="fansSourceCode" className="select">
                        <option value="">粉丝来源</option>
                        <option value="SHOP">门店</option>
                        <option value="OTHER">其他</option>
                    </select>
                </div>
                <div className="status">
                    <select id="buyTimes" className="select">
                        <option value="">购买次数</option>
                        <option value="0">0</option>
                        <option value="1">1+</option>
                        <option value="2">2+</option>
                        <option value="3">3+</option>
                        <option value="4">4+</option>
                        <option value="5">5+</option>
                        <option value="10">10+</option>
                        <option value="15">15+</option>
                        <option value="20">20+</option>
                    </select>
                </div>
                <div className="status">
                    <select id="isMember" className="select">
                        <option value="">粉丝身份</option>
                        <option value="Y">会员</option>
                        <option value="N">非会员</option>
                    </select>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

FansSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default FansSearchForm;