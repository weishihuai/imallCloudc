import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import Header from '../components/Header'
// import Mainnav from '../components/Mainnav'
// import Footer from '../components/Footer'
import MenuRouter from '../components/MenuRouter'
import * as TodoActions from '../actions'

class App extends Component {

    constructor(props, context) {
        super(props, context)
    }

    componentWillMount(){

    }

    componentDidUpdate(){
    }

    render() {
        const {store} = this.context;
        const { actions} = this.props;
        const menuTodos = store.getState().menuTodos;
        var menuHeader;

        function clickMenu(event){
            var $ = require("jquery");
            var target = $(event.target);
            target.parents("ul").find("li").removeClass('active');
          /*  target.parents("ul").find("li").find("a").css("color","rgb(173, 179, 184)");*/
            if(!target.hasClass("btn-add")){ //不是按钮则改变颜色
                target.parent('li').addClass('active');
            }
          /*  target.css("color","rgb(247, 108, 81)");*/
        }

        var menuPanel;
        if(menuTodos.lastMenuItem.subChildList.length>0){
            var $ = require("jquery");
            menuPanel = (<div id="page-side">
                <h2 className="title">{menuTodos.lastMenuItem.menuNm}</h2>
                <ul className="nav nav-tabs bord-no">
                    {
                        menuTodos.lastMenuItem.subChildList.map(function (subMenuItem) {
                            return (<li key={subMenuItem.id} className={subMenuItem.routerKey.replace("/","-") + "  menuItem"}>
                                <a className={subMenuItem.menuType == "BTN" ? "btn-add" : ""}  href={"#" + subMenuItem.routerKey} onClick={(e) => {clickMenu(e); actions.selectActiveMenuItem(subMenuItem)} }>
                                    {subMenuItem.menuNm}
                                </a>
                            </li>)
                        })
                    }
                </ul>
                </div>)
        }
        else {
            menuPanel = (<div style={{height: "0px"}}></div>)
        }

        return (
            <div id="container" className="mainnav-lg">
                <Header actions={actions}  reqPortalMenus={actions.reqPortalMenus} selectLastMenuItem={actions.selectLastMenuItem} />
                <div className="boxed">
                    <div id="content-container">
                        {
                            menuHeader
                        }
                        {
                            menuPanel
                        }
                        <div id="page-content" className="page-content">
                            <MenuRouter reqRouterConfigs={actions.reqRouterConfigs} />
                        </div>
                    </div>
               </div>

            </div>
        )
    }
}

App.propTypes = {
    logininfoTodos: PropTypes.object.isRequired,
    menuTodos: PropTypes.object.isRequired,
    routerTodos: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired
}

App.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(TodoActions, dispatch)
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(App)
