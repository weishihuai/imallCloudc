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
    isEnableObject: {state:"Y"},                         //弹窗 是否开启 对象
    addState: false,                                     //弹窗 新增 状态
    detailState: false,                                  //弹窗 详情 状态
    editState: false,                                    //弹窗 编辑 状态
    detailObject: null,                                  //弹窗 详情 与 编辑 对象
    businessCategoryDictType: "BUSINESS_CATEGORY",       //商品分类 字典 类型
    businessCategoryAllData:null,                        //商品分类 数据
    businessCategorySelect:null,                         //商品分类 选中 数据
    businessRangeDictType:"BUSINESS_RANGE",              //商品范围 字典 类型
    businessRangeAllData:null,                           //商品范围 数据
    businessRangeDataSelect:null,                        //商品范围 选中 数据
    supplierDocCertificatesFileList:null,                   //资质文件

};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SUPPLIER_DOC_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case types.SUPPLIER_DOC_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SUPPLIER_DOC_LIST_IS_ENABLE:
            return Object.assign({}, state, {
                isEnableObject:action.isEnableObject||{},
                isEnable: action.isEnable,

            });
        case types.SUPPLIER_DOC_LIST_DETAIL_MODEL:
            return Object.assign({},state,{
                detailState:action.detailState,
            });
        case types.SUPPLIER_DOC_LIST_UPDATE_MODEL:
            return Object.assign({},state,{
                editState:action.editState,
             });
        case types.SUPPLIER_DOC_FIND_BUSINESS_CATEGORY:
            return Object.assign({}, state, {
                businessCategoryAllData: action.businessCategoryAllData?action.businessCategoryAllData:[]
            });
        case types.SUPPLIER_DOC_FIND_BUSINESS_RANGE:
            return Object.assign({}, state, {
                businessRangeAllData: action.businessRangeAllData?action.businessRangeAllData:[]
            });

        case types.SUPPLIER_DOC_SET_BUSINESS_SELECT_CATEGORY:
            return Object.assign({}, state, {
                businessCategorySelect: action.businessCategorySelect?action.businessCategorySelect:[]
            });
        case types.SUPPLIER_DOC_SET_BUSINESS_SELECT_RANGE:
            return Object.assign({}, state, {
                businessRangeDataSelect: action.businessRangeDataSelect?action.businessRangeDataSelect:[]
            });
        case types.SUPPLIER_DOC_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.SUPPLIER_DOC_SET_CERTIFICATES_FILE_DATA:
            return Object.assign({}, state, {
                supplierDocCertificatesFileList:action.supplierDocCertificatesFileList
            });

        case types.SUPPLIER_DOC_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
                businessCategorySelect:action.detailObject.businessCategory?action.detailObject.businessCategory.split(","):[],
                businessRangeDataSelect:action.detailObject.businessRange?action.detailObject.businessRange.split(","):[],
            });

        default:
            return state
    }
}
