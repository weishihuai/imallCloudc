import {DRUG_IN_BUCKET_LIST,DRUG_IN_BUCKET_ADD_MODEL,DRUG_IN_BUCKET_SEARCH_PARAMS,DRUG_IN_BUCKET_DETAIL_MODAL,
    DRUG_IN_BUCKET_DETAIL_DATA,DRUG_IN_ADD_APPROVE,DRUG_IN_FIND_CURRENT_LOGIN_MESSAGE,DRUG_IN_FIND_ALL_STORAGE_SPACE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchFields: "",  //商品名称、编码、拼音码
    batch: "",  //商品批号
    inBucketManName: "",  //装斗人姓名
    inBucketStartTimeString: "",  //装斗时间(开始时间)
    inBucketEndTimeString: ""  //装斗时间(结束时间)
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,    //新增窗体
    data: {},
    id: null,
    drugInBucketDetailState: false,  //详情 窗体
    drugInBucketDetailData:{},   //装斗详情信息
    approveData:{},         //审核信息
    goodsContent:{
        content: [],
        totalElements: 0,
        number: 0,
        size: 1000
    },                      //勾选对象
    allStorageSpace:[],      //下拉菜单
    loginUserMessage:{},   //当前登录信息
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case DRUG_IN_BUCKET_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case DRUG_IN_BUCKET_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case DRUG_IN_BUCKET_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case DRUG_IN_BUCKET_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                drugInBucketDetailState: action.isDetailShow,
                id: action.id
            });
        case DRUG_IN_BUCKET_DETAIL_DATA:
            return Object.assign({}, state, {
                drugInBucketDetailData: action.data
            });
        case DRUG_IN_ADD_APPROVE:
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        case DRUG_IN_FIND_ALL_STORAGE_SPACE:
            return Object.assign({}, state, {
                allStorageSpace: action.allStorageSpace
            });
        case DRUG_IN_FIND_CURRENT_LOGIN_MESSAGE:
            return Object.assign({}, state, {
                loginUserMessage: action.loginUserMessage
            });
        default:
            return state
    }
}
