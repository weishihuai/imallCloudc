import React, { PropTypes, Component } from 'react'
import JspInclude from './JspInclude'
import { browserHistory } from 'react-router'

class Mainnav extends Component {

    constructor(props, context) {
        super(props, context)
    }

    componentWillMount() {
        this.props.reqPortalMenus(
            function(){
                //
                lazyloadWithWait('niftyJs', 'js', iportal  +  '/static/lib/js/nifty.js', 1500, false);
            }
        );
    }

    componentDidMount() {
        // this runs the contents in script tag on a window/global scope
        //lazyloadWithWait('niftyJs', 'js', iportal  +  '/static/lib/js/nifty.js', 3000, false);
        this.fixPosition();
    }

    componentDidUpdate(){
        this.fixPosition();
    }

    fixPosition(){
        setTimeout(function(){
            var $ = require("jquery");
            var urlSource = window.location.href;
            var url = urlSource.substring(urlSource.indexOf("#")+1,urlSource.indexOf("?")).replace("/","").replace(/\//g,'-');
            if(url==undefined||url==null||url==""){
                return;
            }
            var element = $("."+url);
            if(element!=undefined){
                $(element).parents("ul").addClass("in").attr("aria-expanded","true");
                $(element).addClass("active");
                $(element).parents("li").addClass("active");
                $(".menuItem a").css("color","#adb3b8");
                $(element).find("a").css("color","#F76C51");
            }

            //末级选中状态
            $("#mainnav-menu").find('.collapse').find("li").on("click", function(e){
                $("#mainnav-menu").find('.collapse').find("li").removeClass('active');
                $(this).addClass('active');
            });
        },1000)
    }


    render() {
        const {store} = this.context;
        const selectLastMenuItem = this.props.selectLastMenuItem;

        function clickMenuEvent(event){
            var $ = require("jquery");
            var target = $(event.target);
            menuEvent(target);
        }

        function menuEvent(target){
            if(target.next('.collapse').hasClass('in')){
                target.next('.collapse').removeClass("in").attr("aria-expanded","false");
            }
            else {
                target.next('.collapse').addClass("in").attr("aria-expanded","true");
            }

            var colapsed = target.next('.collapse');
            if(colapsed.length){
                colapsed.each(function(){
                    var cl = $(this);
                    if (cl.hasClass('in')){
                        cl.parent('li').addClass('active');
                    }else{
                        cl.parent('li').removeClass('active');
                    }
                });
            }
        }

        function triggerMenuEvent(event){
            var $ = require("jquery");
            var target = $(event.target).parent();
            menuEvent(target);
        }

        return (
        <div id="mainnav-container">
            <div id="mainnav">
                <div id="mainnav-shortcut">
                    <a href="javascript:;">
                        <i className="fa fa-edit"></i>
                        <svg className="imall-custom-small-icon" aria-hidden="true">
                            <use xlinkHref={"#" + store.getState().menuTodos.nextMenuItem.iconClass}></use>
                        </svg>&nbsp;&nbsp;
                        <span className="title">{store.getState().menuTodos.nextMenuItem.menuNm}</span>
                    </a>
                </div>
                <div id="mainnav-menu-wrap">
                    <div className="nano">
                        <div className="nano-content">
                            <ul id="mainnav-menu" className="list-group">
                                {
                                    store.getState().menuTodos.nextMenuItem.subChildList.map(function (mdlMenuItem) {
                                        return (
                                            <li key={mdlMenuItem.id} className={mdlMenuItem.nodeCode==store.getState().menuTodos.threeMenuItem.nodeCode ? "active" : ""}>
                                                <a href="javascript:;" onClick={(e) => clickMenuEvent(e)}>
                                                    <svg className="imall-custom-small-icon" aria-hidden="true">
                                                        <use xlinkHref={"#" + mdlMenuItem.iconClass} onClick={(e) => triggerMenuEvent(e)}></use>
                                                    </svg>&nbsp;&nbsp;
                                                    <span className="menu-title" onClick={(e) => triggerMenuEvent(e)}>{mdlMenuItem.menuNm}</span>
                                                    <i className="arrow" onClick={(e) => triggerMenuEvent(e)}></i>
                                                </a>
                                                <ul className={"collapse" + (mdlMenuItem.nodeCode==store.getState().menuTodos.threeMenuItem.nodeCode ? " in" : "")}>
                                                    {
                                                        mdlMenuItem.subChildList.map(function (lastMenuItem) {
                                                            if(lastMenuItem.subChildList.length > 0){
                                                                return (
                                                                    <li key={lastMenuItem.id} className={lastMenuItem.routerKey.replace("/","-") + "  menuItem" + (lastMenuItem.nodeCode==store.getState().menuTodos.lastMenuItem.nodeCode ? " active" : "")}>
                                                                        <a  href="javascript:;" onClick={(e) => {selectLastMenuItem(lastMenuItem); if(store.getState().menuTodos.activeMenuItem.routerKey.length>0){ browserHistory.push(window.location.pathname + "#/" + store.getState().menuTodos.activeMenuItem.routerKey + "?_t=" + Math.random())}} }>
                                                                            <span className="menu-title">{lastMenuItem.menuNm}</span>
                                                                            <i className="arrow"></i>
                                                                        </a>
                                                                    </li>)
                                                            }
                                                            else {
                                                                return (<li key={lastMenuItem.id} className={lastMenuItem.routerKey.replace("/","-") + "  menuItem" + (lastMenuItem.nodeCode==store.getState().menuTodos.lastMenuItem.nodeCode ? " active" : "")}>
                                                                    <a  href={"#" + lastMenuItem.routerKey} onClick={(e) => {selectLastMenuItem(lastMenuItem); if(store.getState().menuTodos.activeMenuItem.routerKey.length>0){ browserHistory.push(window.location.pathname + "#/" + store.getState().menuTodos.activeMenuItem.routerKey + "?_t=" + Math.random())} }}>
                                                                        <span className="menu-title"> {lastMenuItem.menuNm}</span>
                                                                    </a>
                                                                </li>)
                                                            }
                                                        })
                                                    }
                                                </ul>
                                            </li>
                                        );
                                    })
                                }
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>)
    }
}

Mainnav.contextTypes = {
    store : React.PropTypes.object
}

export default Mainnav
