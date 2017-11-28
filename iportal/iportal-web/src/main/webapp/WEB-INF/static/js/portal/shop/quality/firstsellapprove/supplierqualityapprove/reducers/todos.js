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
    approveFormState: false,                                     //弹窗 审核 状态
    approveFormStateObject: {state:"Y"},                         //弹窗 审核 对象
    detailState: false,                                  //弹窗 详情 状态
    detailObject: {supplierCertificatesFileList:[],firstManageSupplierQualityApproveInfDetailVo:[]},          //弹窗 详情 与 编辑 对象
    businessCategoryDictType: "BUSINESS_CATEGORY",       //商品分类 字典 类型
    businessCategoryAllData:null,                        //商品分类 数据
    businessCategorySelect:null,                         //商品分类 选中 数据
    businessRangeDictType:"BUSINESS_RANGE",              //商品范围 字典 类型
    businessRangeAllData:null,                           //商品范围 数据
    businessRangeDataSelect:null,                        //商品范围 选中 数据
    supplierCertificatesFileList:null,                   //资质文件
    approveUserNames:{}                                 //获取审核对象名字
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SUPPLIER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case types.SUPPLIER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SUPPLIER_LIST_IS_ENABLE:
            return Object.assign({}, state, {
                approveFormStateObject:action.approveFormStateObject||{},
                approveFormState: action.approveFormState,
            });
        case types.SUPPLIER_LIST_DETAIL_MODEL:
            return Object.assign({},state,{
                detailState:action.detailState,
            });
        case types.SUPPLIER_FIND_BUSINESS_CATEGORY:
            return Object.assign({}, state, {
                businessCategoryAllData: action.businessCategoryAllData?action.businessCategoryAllData:[]
            });
        case types.SUPPLIER_FIND_BUSINESS_RANGE:
            return Object.assign({}, state, {
                businessRangeAllData: action.businessRangeAllData?action.businessRangeAllData:[]
            });
        case types.SUPPLIER_SET_BUSINESS_SELECT_CATEGORY:
            return Object.assign({}, state, {
                businessCategorySelect: action.businessCategorySelect?action.businessCategorySelect:[]
            });
        case types.SUPPLIER_SET_BUSINESS_SELECT_RANGE:
            return Object.assign({}, state, {
                businessRangeDataSelect: action.businessRangeDataSelect?action.businessRangeDataSelect:[]
            });
        case types.SUPPLIER_SET_CERTIFICATES_FILE_DATA:
            return Object.assign({}, state, {
                supplierCertificatesFileList:action.supplierCertificatesFileList
            });
        case types.SUPPLIER_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
                businessCategorySelect:action.detailObject.businessCategory?action.detailObject.businessCategory.split(","):[],
                businessRangeDataSelect:action.detailObject.businessRange?action.detailObject.businessRange.split(","):[],
            });
        case types.SUPPLIER_SET_APPRO_NAMES:
            return Object.assign({},state,{
                approveUserNames:action.approveUserNames,
           });
        case types.SUPPLIER_SET_INIT_DATA:
            return Object.assign({},state, {
                approveFormState: false,                                     //弹窗 审核 状态
                approveFormStateObject: {state:"Y"},                         //弹窗 审核 对象
                detailState: false,                                  //弹窗 详情 状态
                detailObject: {supplierCertificatesFileList:[],firstManageSupplierQualityApproveInfDetailVo:[]},          //弹窗 详情 与 编辑 对象
                businessCategoryAllData:null,                        //商品分类 数据
                businessCategorySelect:null,                         //商品分类 选中 数据
                businessRangeAllData:null,                           //商品范围 数据
                businessRangeDataSelect:null,                        //商品范围 选中 数据
                supplierCertificatesFileList:null,                   //资质文件
                approveUserNames:{}                                 //获取审核对象名字
            });
        default:
            return state
    }
}
