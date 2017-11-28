import React, {PropTypes, Component} from "react";
import {browserHistory} from "react-router";
import PassWordForm from "./PassWordForm";
class Header extends Component {

    componentWillMount() {
        const { actions} = this.props;
        actions.getLoginUser();
        actions.findAllJobs();
        this.props.reqPortalMenus(
            function(){
                //
                lazyloadWithWait('niftyJs', 'js', iportal  +  '/static/lib/js/nifty.js', 1500, false);
            }
        );
    }



    componentDidMount(){
        $(".hd-user").on("mouseover", function () {
            $(".hd-user-info").show();
        }).on("mouseout", function () {
            $(".hd-user-info").hide();
        });

        $(".hd-user-info").on("mouseover", function () {
            $(this).show();
        }).on("mouseout", function () {
            $(this).hide();
        });
    }

    render() {
        const {store} = this.context;
        const loginUser = store.getState().logininfoTodos.loginUser;
        const {actions} = this.props;
        const {savePasswordFunc} = actions;
        const optModifyPasswordModal = actions.optModifyPasswordModal;
        const selectLastMenuItem = this.props.selectLastMenuItem;
        const jobsvo = store.getState().logininfoTodos.jobsVo;
        const currJob = jobsvo.currJob || "";
        const modalState = store.getState().logininfoTodos.modalState;
        return (<div>
            {modalState&& <PassWordForm  optModifyPasswordModal={optModifyPasswordModal} actions={actions}  onSubmit={(data) => savePasswordFunc(data)}/>}
            <div className="page-header">
                <div className="cont">
                    <div className="logo"><a href="##"><img src={iportal + "/static/lib/img/logo300x90.png"} alt=""/></a></div>
                    <div className="box">

                        {
                            store.getState().menuTodos.menus.map(mdlMenuItem => {
                                return (
                                    <div className="item" key={mdlMenuItem.id}>
                                        <a href="javascript:void(0);">{mdlMenuItem.menuNm}</a>
                                        <div className="m-box">
                                            {
                                                mdlMenuItem.subChildList.map(lastMenuItem => {
                                                    if (lastMenuItem.menuType == 'MDL') {
                                                        return (
                                                            <div className="dl" key={lastMenuItem.id}>
                                                                <div className="dt">{lastMenuItem.menuNm}</div>
                                                                <div className="dd">
                                                                    {
                                                                        lastMenuItem.subChildList.map(finalMenuItem => {
                                                                            return (
                                                                                <a href={"#" + finalMenuItem.routerKey}
                                                                                   className="dd-item"
                                                                                   key={finalMenuItem.id}
                                                                                   onClick={(e) => {
                                                                                       selectLastMenuItem(finalMenuItem);
                                                                                       if (store.getState().menuTodos.activeMenuItem.routerKey.length > 0) {
                                                                                           browserHistory.push(window.location.pathname + "#/" + store.getState().menuTodos.activeMenuItem.routerKey + "?_t=" + Math.random())
                                                                                       }
                                                                                   }}>{finalMenuItem.menuNm}
                                                                                </a>
                                                                            )
                                                                        })
                                                                    }
                                                                </div>
                                                            </div>
                                                        )
                                                    } else if (lastMenuItem.menuType == 'MENU') {
                                                        return (
                                                            <div className="dl" key={lastMenuItem.id}>
                                                                <a href={"#" + lastMenuItem.routerKey} className="dt"
                                                                   onClick={(e) => {
                                                                       selectLastMenuItem(lastMenuItem);
                                                                       if (store.getState().menuTodos.activeMenuItem.routerKey.length > 0) {
                                                                           browserHistory.push(window.location.pathname + "#/" + store.getState().menuTodos.activeMenuItem.routerKey + "?_t=" + Math.random())
                                                                       }
                                                                   }}>{lastMenuItem.menuNm}</a>
                                                            </div>
                                                        )
                                                    }
                                                })
                                            }
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>
                    <div className="hd-user">
                        <div className="pic"><a href="##"><img src={iportal + "/static/lib/img/pic40x40.jpg"}
                                                               alt=""/></a></div>
                        <div className="user-mc">
                            <a href="##" className="elli">{loginUser.userName}</a>
                            <p className="elli">{currJob.jobName}</p>
                        </div>
                        <a href={iportal + "/logout"} className="dp-out">退出</a>
                        <div className="hd-user-info" style={{display: "none"}}>
                            <div className="box1">
                                <p>当前岗位：{currJob.jobName}</p>
                                <p>所属机构：{loginUser.org}</p>
                                <p>登录帐户：{loginUser.userName}</p>
                                <p>工号：{loginUser.employeeCode}</p>
                                <p>真实姓名：{loginUser.realName}</p>
                            </div>
                            <div className="box2" style={{display: jobsvo.jobs.length>0?"block":"none"}}>
                                <p className="box-t" >切换岗位</p>
                                {
                                    jobsvo.jobs.map(function (job) {
                                        return (<p key={job.id}>
                                            <a className="btn-link" href="javascript:;"
                                               onClick={() => actions.switchTo(job.id)}>
                                                {job.jobName}
                                            </a>
                                        </p>)
                                    })
                                }
                            </div>
                            <div className="box3">
                                <p><a href="#" onClick={()=>{optModifyPasswordModal()}}>修改密码</a></p>
                            </div>
                            <div className="t-icon-box"><em className="t-icon">▴</em></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>);
    }



}

Header.propTypes = {
    actions: PropTypes.object.isRequired
}

Header.contextTypes = {
    store : React.PropTypes.object
}

export default Header
