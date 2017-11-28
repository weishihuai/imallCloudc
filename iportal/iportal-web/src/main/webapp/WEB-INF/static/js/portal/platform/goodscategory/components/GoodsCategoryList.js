import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import GoodsCategoryAddForm from "./GoodsCategoryAddForm";
import GoodsCategoryUpdateForm from "./GoodsCategoryUpdateForm";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";
import CommonConfirmComponent from "../../../../common/component/CommonConfirmComponent";


class GoodsCategoryList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.categoryList();
        this.props.portalOperationalAuth(["goods:portalCategory:add","goods:portalCategory:update","goods:portalCategory:delete","goods:portalCategory:subAdd"]);

    }

    componentDidMount() {
        $('#nestable2').nestable();
    }

    componentDidUpdate() {

    }


    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {id} = store.getState().todos;
        function buildNodes(children, level) {
            let html;
            let isExpand = level <= 2;
            if (children != null && children.length > 0) {
                html = <ol className="sp-class-list" style={{display: isExpand ? "block" : "none", maxHeight: "none"}}>
                    {
                        children.map(function (child) {
                            let collapseShow = false;
                            let expandShow = false;
                            isExpand = level + 1 <= 2;
                            if (isExpand) {
                                collapseShow = true;
                                expandShow = false;
                            }
                            if (child.isParent && !isExpand) {
                                collapseShow = false;
                                expandShow = true;
                            }
                            return <li className="sp-class-item" data-id={child.id} key={child.id}>
                                        <button  type="button" className="minus-icon " data-action="collapse" style={{display: collapseShow ? "block" : "none"}}/>
                                        <button type="button" className="add-icon " data-action="expand" style={{display: expandShow ? "block" : "none"}}/>
                                            <div className="sp-class-inner">
                                                <div className="fl">
                                                    <div className="sc-name">{child.name}</div>
                                                </div>
                                                <div className="fr">
                                                    {level < 3 ? <ImallA permissionCode="goods:portalCategory:subAdd" className="" href="javascript:void(0)" text="添加子级" onClick={()=>actions.saveIdAndOpenAddForm(child.id)}/> : <span/>}
                                                    {level < 3 ? "-" : ""}
                                                    <ImallA permissionCode="goods:portalCategory:update" className="" href="javascript:void(0)" text="修改" onClick={()=>actions.findGoodsCategoryDetailAndUpdateForm(child.id)}/>-
                                                    <ImallA permissionCode="goods:portalCategory:delete" className="" href="javascript:void(0)" text="删除" onClick={()=>actions.changeConfirmState(true,child.id)}/>
                                                </div>
                                            </div>
                                            {
                                                buildNodes(child.children, level + 1)
                                            }
                                    </li>
                        })
                    }
                </ol>
            }
            else {
                html = <div></div>
            }
            return html;
        }

        return (
                <div className="main-box">
                    {store.getState().todos.confirmModelState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="删除商品分类" text={"是否删除?"} confirmBtn="确定" callback={(result) => actions.updateDelete(id)} close={() => actions.changeConfirmState(false,"")}/>}
                    {store.getState().todos.addFormState && <GoodsCategoryAddForm store={store} actions={actions}/>}
                    {store.getState().todos.updateFormState && <GoodsCategoryUpdateForm store={store} actions={actions}/>}
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>商品分类管理</h5>
                                <a href="javascript:void(0)">商品分类</a>
                                <span>></span>
                                <a href="javascript:void(0)">商品分类管理</a>
                                <div className="lt-cont">
                                </div>
                            </div>
                            <div className="mt-rt">
                                <ImallA permissionCode="goods:portalCategory:add" href="javascript:void(0)" className="added" text="添加" onClick={()=>actions.saveIdAndOpenAddForm(1)}/>
                            </div>
                        </div>
                        { store.getState().todos.rootNodes.length <= 0 &&
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td colSpan="18" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        }

                        <div className="mc" style={{width: "calc(100% + 60px)",marginLeft: "-60px"}} id="nestable2">
                            {
                                buildNodes(store.getState().todos.rootNodes, 1)
                            }
                        </div>
                    </div>
                </div>
        );
    }
}
GoodsCategoryList.propTypes = {
    actions: PropTypes.object.isRequired
};

GoodsCategoryList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsCategoryList);
