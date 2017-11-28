import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    page: 0,
    size: 10,
    modelType:"",                                       //模块类型
    prescriptionRegisterState:"",                       //处方登记状态
    prescriptionSellOrderCode:"",                       //处方销售订单编码
    patientNm:"",                                       //患者名称
    startPrescriptionDateString:"",                     //开始时间
    endPrescriptionDateString:""                        //结束时间
};

const initialState = {
    params: INIT_PARAMS,         //搜索参数
    isShowAdd:false,            //是否显示新增页面
    isShowDetail:false,         //是否显示详情页面
    isShowEdit:false,           //是否显示编辑页面
    isShowDispensing:false,     //是否显示调剂页面
    record:null,                //详情
    items:null,                 //订单项
    files:null,                 //附件
    addFileList:[],             //新增的附件
    delFileList:[],             //删除的附件
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case types.PRESCRIPTION_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
        case types.PRESCRIPTION_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size,
                    prescriptionRegisterState: action.prescriptionRegisterState
                })
            });
        //新增
        case types.PRESCRIPTION_REGISTER_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd,
                items: null,
                files: null
            });
        //订单项回显
        case types.PRESCRIPTION_REGISTER_ADD_ORDER_ITEM_MODULE:
            return Object.assign({}, state, {
                items: action.items
            });
        //文件上传回显
        case types.PRESCRIPTION_REGISTER_ADD_FILE_MODULE:
            return Object.assign({}, state, {
                files: action.files,
                addFileList: action.addFileList,
                delFileList: action.delFileList
            });
        //详情
        case types.PRESCRIPTION_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                record: action.record
            });
        //编辑
        case types.PRESCRIPTION_REGISTER_EDIT_MODULE:
            return Object.assign({}, state, {
                isShowEdit: action.isShowEdit,
                data: action.data,
                files: null,
                addFileList: [],
                delFileList: []
            });
        //调剂
        case types.PRESCRIPTION_DISPENSING_MODULE:
            return Object.assign({}, state, {
                isShowDispensing: action.isShowDispensing,
                data: action.data
            });
        default:
            return state;
    }
}