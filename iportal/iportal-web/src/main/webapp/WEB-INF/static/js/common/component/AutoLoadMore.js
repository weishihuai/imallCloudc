/**
 * Created by chencheng on 2016-11-02
 */

import React, {PropTypes, Component} from "react";
import {Router, Route, Link, browserHistory} from "react-router";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
/**
 * 自动加载下一页-滚动到列表最后自动加载更多
 * 参数列表：
 *          loadMore：必须，加载更多function ，各自页面自己实现自己的加载更多方法
 *          lastPage: 必填，是否最后一页
 */
class AutoLoadMore extends Component {

    componentDidMount(){
        this.initLoadMore(this)
    }

    //加载更多数据
    initLoadMore(pageObj) {
        let $container = $('#'+pageObj.props.container);
        window.addEventListener("touchend", function () {
            if ($(document).scrollTop() >= ($(document).height() - $(window).height())) {//滚动到最下方了
                if($container.is(":visible")) {//容器是可见的
                    let loadMore = pageObj.props.loadMore;//加载更多方法
                    let lastPage = pageObj.props.lastPage;//是否最后一页
                    let $loading = $(pageObj.refs.loading);//加载中样式
                    //还有下一页
                    if (!lastPage) {
                        $loading.show();
                        setTimeout(
                            () => {
                                $loading.hide();
                                loadMore();//执行真正的加载更多方法
                            }, 500);
                    }
                }
            }
        });
    }

    render() {
        let lastPage = this.props.lastPage;
        return (
            <div ref='loadMore' className="loadMore" style={{textAlign: "center",paddingTop:"0.15rem", display: lastPage ? 'none' : 'block'}}>
                <a ref="loading" style={{height: "0.625rem", lineHeight: "0.625rem", display: 'none'}}>
                    <img style={{height: "0.625rem", width: "0.625rem"}} src={iportal + "/static/img/wechat/loading.gif"}/>
                </a>
            </div>
        )
    }

}

AutoLoadMore.propTypes = {
    loadMore: PropTypes.func.isRequired,
    lastPage: PropTypes.bool.isRequired
};

AutoLoadMore.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators({}, dispatch)
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(AutoLoadMore)



