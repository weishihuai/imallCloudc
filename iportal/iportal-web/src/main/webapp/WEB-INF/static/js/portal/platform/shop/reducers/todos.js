import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    pinyin:"",
    stat: "",
    startTimeString:"",
    endTimeString:"",
    supplierNm:""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    isEnable: false,                                     //弹窗 是否开启 状态
    isUpdatePassWord: false,                             //弹窗 修改密码 状态
    isUpdatePassWordObject: false,                       //弹窗 修改密码 对象
    isEnableObject: {state:"Y"},                         //弹窗 是否开启 对象
    addState: false,                                     //弹窗 新增 状态
    detailState: false,                                  //弹窗 详情 状态
    editState: false,                                    //弹窗 编辑 状态
    detailObject: null,                                  //弹窗 详情 与 编辑 对象

    businessRangeDictType:"BUSINESS_RANGE",              //商品范围 字典 类型
    businessRangeAllData:null,                           //商品范围 数据
    businessRangeDataSelect:null,                        //商品范围 选中 数据
    shopCertificatesFileVoList:null,                   //资质文件

};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SHOP_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case types.SHOP_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SHOP_LIST_IS_ENABLE:
            return Object.assign({}, state, {
                isEnableObject:action.isEnableObject||{},
                isEnable: action.isEnable,

            });
        case types.SHOP_LIST_DETAIL_MODEL:
            return Object.assign({},state,{
                detailState:action.detailState,
            });
        case types.SHOP_LIST_UPDATE_MODEL:
            return Object.assign({},state,{
                editState:action.editState,
             });
        case types.SHOP_FIND_BUSINESS_RANGE:
            return Object.assign({}, state, {
                businessRangeAllData: action.businessRangeAllData?action.businessRangeAllData:[]
            });


        case types.SHOP_SET_BUSINESS_SELECT_RANGE:
            return Object.assign({}, state, {
                businessRangeDataSelect: action.businessRangeDataSelect?action.businessRangeDataSelect:[]
            });
        case types.SHOP_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.SHOP_SET_CERTIFICATES_FILE_DATA:
            return Object.assign({}, state, {
                shopCertificatesFileVoList:action.shopCertificatesFileVoList
            });
        case types.SHOP_SET_PASSWORD_STATE:
            return Object.assign({}, state, {
                isUpdatePassWord:action.isUpdatePassWord,
            });
        case types.SHOP_SET_PASSWORD_OBJECT:
            return Object.assign({}, state, {
                isUpdatePassWordObject:action.isUpdatePassWordObject,
            });

        case types.SHOP_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
                businessRangeDataSelect:action.detailObject.businessRange?action.detailObject.businessRange.split(","):[],
            });

        case types.SHOP_SET_INIT_VALUE:
            return Object.assign({},state,{
                isUpdatePassWordObject: false,                       //弹窗 修改密码 对象
                isEnableObject: {state:"Y"},                         //弹窗 是否开启 对象
                detailObject: null,                                  //弹窗 详情 与 编辑 对象
                businessRangeDataSelect:null,                        //商品范围 选中 数据
                shopCertificatesFileVoList:null,                   //资质文件
            });
        default:
            return state
    }
}
