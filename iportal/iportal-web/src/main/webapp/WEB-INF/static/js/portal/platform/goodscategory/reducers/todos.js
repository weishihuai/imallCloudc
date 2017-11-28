import *as types from "../constants/ActionTypes";

const initialState = {
  rootNodes:[],     //所有分类数据
  addFormState:false,//添加页面状态
  updateFormState:false,//  编辑页面状态
  id:"",          //当前选择parentId
  data:{},        //回显的数据
  confirmModelState:"",   //删除确认框的状态
};


export default function todos(state = initialState, action) {
  switch (action.type) {
    case types.PRODUCT_SALES_CATEGORY_LIST:
      return Object.assign({}, state, {
        rootNodes: action.data
      });
    case types.GOODS_CATEGORY_ADD_FORM_STATE:
      return Object.assign({},state,{
        addFormState:action.data
      });
    case types.GOODS_CATEGORY_UPDATE_FORM_STATE:
      return Object.assign({},state,{
        updateFormState:action.data
      });
    case types.GOODS_CATEGORY_CURRENT_ID:
      return Object.assign({},state,{
        id:action.data
      });
    case types.GOODS_CATEGORY_DETAIL_DATA:
      return Object.assign({},state,{
        data:action.data
      });
    case types.GOODS_CATEGORY_DELETE_VIEW_STATE:
      return Object.assign({},state,{
        confirmModelState:action.data,
        id:action.id
      });
    default:
      return state
  }
}


