import * as types from "../constants/ActionTypes";

const initState = {
    data:{},
    id: "",
    weShopDetailData:{} ,  //详情数据
    placardInfModelShowState:false,  //门店公告 弹窗状态
    shopMgrWeiXinModelShowState:false, //店长微信 弹窗状态
    telModelShowState:false,  //门店电话 弹窗状态
    weShopContactTel:"", //门店联系电话
    shopMgrWeiXinUrl:"",  //店长微信URL
};

export default function weShopTodos(state = initState, action) {
     switch (action.type){
         case types.WE_SHOP_DETAIL_DATA:  //详情数据
             return Object.assign({}, state, {
                 weShopDetailData: action.data,
             });
         case types.WE_SHOP_PLACARD_INF_MODEL:
             return Object.assign({}, state, {
                 placardInfModelShowState: action.isModelShow,
                 data: action.data || {},
             });
         case types.WE_SHOP_MGR_WEI_XIN_MODEL:
             return Object.assign({},state,{
                 shopMgrWeiXinModelShowState: action.isShow,
                 shopMgrWeiXinUrl: action.shopMgrWeiXinUrl || "",
             });
         case types.WE_SHOP_TEL_MODEL:
             return Object.assign({}, state, {
                 telModelShowState: action.isTelModelShow,
                 weShopContactTel: action.weShopContactTel || "",
             });
         default:
             return state;
     }
}