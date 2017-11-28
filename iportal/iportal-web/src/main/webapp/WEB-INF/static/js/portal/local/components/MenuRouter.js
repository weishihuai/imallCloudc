import React, { PropTypes, Component } from 'react'
import { Router, Route, DefaultRoute, IndexRoute, Link, hashHistory } from 'react-router'
import JspInclude from './JspInclude'
import {MENU_ROUTER_ON_ENTER} from '../constants/ActionTypes';

class MenuRouter extends Component {

    constructor(props, context) {
        super(props, context)
    }

    componentWillMount() {
        this.props.reqRouterConfigs();
    }

    render() {
        const {store} = this.context;
        const routerlist =  store.getState().routerTodos.menuRouters.map(function(value) {
            const LazyLoadView = React.createClass({

                componentDidMount(){
                    // this runs the contents in script tag on a window/global scope
                    lazyload('routerTemplateJs', 'js', value.routerTemplateJs, false);

                    this.fixPosition();
                },

                componentDidUpdate(){
                    this.fixPosition();
                },

                fixPosition(){
                    setTimeout(function(){
                        // var urlSource = window.location.href;
                        // var url = urlSource.substring(urlSource.indexOf("#")+1,urlSource.indexOf("?")).replace("/","").replace(/\//g,'-');
                        // if(url==undefined||url==null||url==""){
                        //     return;
                        // }
                        // var element = $("."+url);
                        // if(element!=undefined){
                        //     $(element).parents("ul").addClass("in").attr("aria-expanded","true");
                        //     $(element).addClass("active");
                        //     $(element).parents("li").addClass("active");
                        //     $(".menuItem a").css("color","#adb3b8");
                        //     $(element).find("a").css("color","#F76C51");
                        // }
                    },10)
                },

                render() {
                    return (
                        <div id="lazyLoadView">
                            没有数据
                        </div>
                    )
                }
            });

            const routeEnter =  (nextState, replace) =>{
                store.dispatch({type:MENU_ROUTER_ON_ENTER, data: value.menuNodeCode })
            };

            return <Route key={value.resourceId}  path={"/"+value.routerKey} component={LazyLoadView} onEnter={ routeEnter }/>;
        });
        return routerlist.length==0 ? (<div></div>) : (<Router history={hashHistory}>{ routerlist }</Router>)
    }
}

MenuRouter.contextTypes = {
    store : React.PropTypes.object
}


export default MenuRouter
