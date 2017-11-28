import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {WE_CHAT_GOODS_SEARCH_RENDER_AGAIN} from "../constants/ActionTypes";
import {hashHistory} from "react-router";
import {WEB_NAME} from "../../../common/common-constant";
import {initGoodsPage} from "../actions/index";

class WeChatGoodsSearch extends Component {

    componentWillMount() {
        document.title = WEB_NAME + "搜索药品";
    }

    search(e){
        if(e.keyCode == 13){
            const value = $(e.target).val();
            if(value){
                document.cookie = "LS_GOODS_SEARCH"+new Date().getTime()+"="+ encodeURIComponent(value.trim());
            }

            this.goSearch(value);
            this.context.store.dispatch({
                type:WE_CHAT_GOODS_SEARCH_RENDER_AGAIN
            })
        }
    }

    clearHistory(){
        const cookies = document.cookie.split(";")||[];
        cookies.map(cookie=>{
            if("LS_GOODS_SEARCH".indexOf(cookie.split("=")[0])){
                document.cookie = cookie.split("=")[0]+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
            }
        });
        this.context.store.dispatch({
            type:WE_CHAT_GOODS_SEARCH_RENDER_AGAIN
        })
    }

    goSearch(keywords){
        this.props.initGoodsPage();
        keywords = keywords.trim();
        const query = this.props.location.state || {};
        hashHistory.push({pathname: 'goods-list', state: {keywords, categoryId: query.categoryId || ''}});
    }

    componentDidMount(){
        $("#searchWords").click(function () {
            $(this).focus();
        }).click();
    }

    render() {
        const cookies = document.cookie.split(";")||[];
        const query = this.props.location.state || {};
        let goodsSearch = new Set();
        cookies.map(cookie=>{
            let arr = cookie.split("=");
            if(arr.length == 2){
                if(arr[0].indexOf("LS_GOODS_SEARCH") != -1){
                    let val = decodeURIComponent(arr[1]);
                    goodsSearch.add(val);
                }
            }
        });
        goodsSearch = Array.from(goodsSearch);
        return (
            <div  className="main-goods-search">
                <div className="top-search">
                    <form action="" style={{display: "inline"}}>
                        <input onKeyDown={e => this.search(e)} type="search" placeholder="搜索药品名称" id="searchWords" defaultValue={query.keyword}/>
                    </form>
                </div>
                <div className="historical-record">
                    {goodsSearch.length > 0 && <div className="dt"><span>历史搜索</span><a onClick={()=>this.clearHistory()}>清空</a></div>}
                    <ul className="dd">
                        {
                            goodsSearch.map((value, index)=>{
                                return (
                                    <li key={index}><a onClick={()=>this.goSearch(value)}>{value}</a></li>
                                )
                            })
                        }
                    </ul>
                </div>
            </div>
        )
    }
}

WeChatGoodsSearch.propTypes = {};

WeChatGoodsSearch.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        initGoodsPage
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatGoodsSearch);