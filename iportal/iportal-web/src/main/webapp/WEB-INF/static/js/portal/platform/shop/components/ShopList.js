import React, {Component, PropTypes} from "react";
import ShopEnableUpdateForm from "./ShopEnableUpdateForm";
import ShopPassWordUpdateForm from "./ShopPassWordUpdateForm";
import ShopEditForm from "./ShopEditForm";
import ShopAddForm from "./ShopAddForm";
import ShopDetailForm from "./ShopDetailForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ShopSearchForm from "./ShopSearchForm";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import {initValidForm} from "../../../../common/validForm/actions";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";

class ShopList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.shopList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'shop:portalShop:add',
            'shop:portalShop:update',
            'shop:portalShop:detail',
            'shop:portalShop:enable',
            'shop:portalShop:updatePassword'
        ]);

    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.shopList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.shopList(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }


    submitData(data,isAdd=false){
        const {store} = this.context;
        const {params} = store.getState().todos;
        require("jquery-md5");
        // 获取资质文件
        const shopCertificatesFileVoList=this.context.store.getState().todos.shopCertificatesFileVoList;
        if(isAdd){
            data = Object.assign({}, data, {
                shopCertificatesFileVoList: shopCertificatesFileVoList,
                password: $.md5(data.password)
            });
        }else{
            data = Object.assign({}, data, {
                shopCertificatesFileVoList: shopCertificatesFileVoList,
            });
        }


       return this.props.actions.saveAndUpdateData(data, isAdd, params);
    }


    /**
     * 弹出 启动/禁用 窗口
     * 初始化 表单
     * @param  信息
     */
    changeIsEnableStateAndInitValidForm(shop){
        this.props.actions.showIsEnableState({
            id: shop.id,
            state: shop.isEnable,
            shopName: shop.entNm
        });
        this.props.initValidForm();
    }

    render() {
        const actions = this.props.actions;
        const {showAddForm,showUpdate,showDetail,showIsEnableState,updatePassword,showUpdatePassword,updateShopEnable } = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const shopList =todos.page.content || [];
        const detailObject =todos.detailObject || {};
        const detailState =todos.detailState ||"";
        const editState =todos.editState ||"";
        const addState =todos.addState ;
        const {isEnable, isEnableObject,isUpdatePassWordObject,isUpdatePassWord} = todos;
        const {params} = store.getState().todos;

        const page = store.getState().todos.page;
        const number = page.number + 1;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            hideSizePerPage: page.totalElements <= page.size
        };


        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>门店管理</h5>
                            <a  href="javascript:void(0);" >门店管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >门店管理</a>
                            <ShopSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA text="添加" permissionCode="shop:portalShop:add" href="javascript:void(0);"  className="added" onClick={()=>{showAddForm(true)}}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="taxation">门店编码</th>
                                    <th className="taxation">企业名称</th>
                                    <th className="types">公司地址</th>
                                    <th className="statutory">企业负责人</th>
                                    <th className="phone" style={{width:"90px"}}>手机</th>
                                    <th className="status">公司电话</th>
                                    <th className="status">门店状态</th>
                                    <th className="time">创建时间</th>
                                    <th className="operating" style={{width: "300px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { shopList.length <= 0 &&
                                <tr >
                                    <th colSpan="9" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {shopList.map((shop, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont">{shop.shopCode}</div></td>
                                                <td><div className="td-cont">{shop.entNm}</div></td>
                                                <td><div className="td-cont">{shop.companyAddr}</div></td>
                                                <td><div className="td-cont">{shop.entResponseMan}</div></td>
                                                <td><div className="td-cont">{shop.mobile}</div></td>
                                                <td><div className="td-cont">{shop.companyTel}</div></td>
                                                <td><div className="td-cont">{shop.isEnable=="Y"?"启用":"禁用"}</div></td>
                                                <td><div className="td-cont">{shop.createTimeString}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA text="修改密码" permissionCode="shop:portalShop:add" href="javascript:void(0);"  className="gray-btn" onClick={()=>{showUpdatePassword(true,shop.id,shop.orgId)}}/>
                                                        <ImallA text="启用/禁用" permissionCode="shop:portalShop:updatePassword" href="javascript:void(0);"  className="gray-btn" onClick={()=> this.changeIsEnableStateAndInitValidForm(shop)}/>
                                                        <ImallA text="修改" permissionCode="shop:portalShop:update"  href="javascript:void(0);"  className="gray-btn"onClick={()=>{showUpdate(true,shop.id,shop.orgId)}}/>
                                                        <ImallA text="查看" permissionCode="shop:portalShop:detail"  href="javascript:void(0);"  className="gray-btn"onClick={()=>{showDetail(true,shop.id,shop.orgId)}}/>
                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar options={options} actions={actions}/>
                    {isEnable&& <ShopEnableUpdateForm   isEnableObject={isEnableObject} showIsEnableState={showIsEnableState} actions={actions}  onSubmit={(data) => updateShopEnable(data, params)}/>}
                    {isUpdatePassWord&& <ShopPassWordUpdateForm   isUpdatePassWordObject={isUpdatePassWordObject} showUpdatePassword={showUpdatePassword} actions={actions}  onSubmit={(data) => updatePassword(data, params)}/>}
                    {detailState&&<ShopDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                    {editState&&<ShopEditForm  editState={editState}    actions={actions} onSubmit={(data) => this.submitData(data)}/>}
                    {addState&&<ShopAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}
                    </div>
            </div>
        )
    }
}


ShopList.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({portalOperationalAuth,initValidForm}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ShopList);