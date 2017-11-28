import {STORAGE_SPACE_MOVE_LIST,
    STORAGE_SPACE_MOVE_ADD_MODEL,
    STORAGE_SPACE_MOVE_SEARCH_PARAMS,
    STORAGE_SPACE_MOVE_DETAIL_MODAL,
    STORAGE_SPACE_MOVE_DETAIL_DATA,
    STORAGE_SPACE_MOVE_APPROVE_VALIDATE,
    STORAGE_SPACE_MOVE_ALL_STORAGE_SPACE,
    STORAGE_SPACE_MOVE_GENERATE_NUM
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    moveManName: "",  //移动人姓名
    moveOrderNum: "",  //移动单号
    moveStartTimeString: "",  //移动时间(开始)
    moveEndTimeString: "",  //移动时间(结束)
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,   //新增窗体
    data: {},
    id: null,
    moveOrderNum: null,  //移动单号
    storageSpaceMoveDetailState: false,
    storageSpaceMoveDetailData:{},
    approveData: null,  //审核人信息
    goodsContent:{
        content: [],
        totalElements: 0,
        number: 0,
        size: 1000
    },   //勾选对象
    allStorageSpace: [], //货位信息
    moveNum:{moveNum:""},
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case STORAGE_SPACE_MOVE_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case STORAGE_SPACE_MOVE_ADD_MODEL: //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case STORAGE_SPACE_MOVE_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case STORAGE_SPACE_MOVE_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                storageSpaceMoveDetailState: action.isDetailShow,
                moveOrderNum: action.moveOrderNum
            });
        case STORAGE_SPACE_MOVE_DETAIL_DATA:  //详情信息
            return Object.assign({}, state, {
                storageSpaceMoveDetailData: action.data
            });
        case STORAGE_SPACE_MOVE_APPROVE_VALIDATE: //审核人验证
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        case STORAGE_SPACE_MOVE_ALL_STORAGE_SPACE: //货位信息
            return Object.assign({}, state, {
                allStorageSpace: action.allStorageSpace
            });
        case STORAGE_SPACE_MOVE_GENERATE_NUM:
            return Object.assign({},state,{
                moveNum:action.moveNum
            });
        default:
            return state
    }
}
