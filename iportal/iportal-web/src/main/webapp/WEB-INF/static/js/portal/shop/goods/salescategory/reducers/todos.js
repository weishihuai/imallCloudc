import {
    SALES_CATEGORY_ADD_MODULE,
    SALES_CATEGORY_ADD_PICT_SETTING,
    SALES_CATEGORY_LIST,
    SALES_CATEGORY_SEARCH_PARAMS,
    SALES_CATEGORY_UPDATE_MODULE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    categoryName:""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    isShowAdd:false,  //是否显示新增页面
    isShowEdit:false,  //是否显示编辑页面
    id:null,
    pict:{},    //图片
    page:{}  //列表数据
};

export default function todos(state = initialState, action) {
  switch (action.type) {
      //初始化搜索框的参数
      case SALES_CATEGORY_SEARCH_PARAMS:
          return Object.assign({}, state, {
              params: action.data || INIT_PARAMS
          });
      //设置列表信息
      case SALES_CATEGORY_LIST:
          return Object.assign({}, state, {
              page: action.data,
              params: Object.assign({}, state.params, {
                  page: action.data.number,
                  size: action.data.size
              })
          });
      //编辑
      case SALES_CATEGORY_UPDATE_MODULE:
          return Object.assign({}, state, {
              isShowEdit: action.isShowEdit,
              data: action.data
          });
      //新增
      case SALES_CATEGORY_ADD_MODULE:
          return Object.assign({}, state, {
              isShowAdd: action.isShowAdd
          });
      case SALES_CATEGORY_ADD_PICT_SETTING://销售分类图片
          return Object.assign({}, state, {
              pict:action.data
          });
      default:
          return state
  }
}
