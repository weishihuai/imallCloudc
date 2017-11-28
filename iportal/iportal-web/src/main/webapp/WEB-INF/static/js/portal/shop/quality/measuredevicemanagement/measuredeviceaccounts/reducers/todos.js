import {
    MEASURE_DEVICE_ACCOUNTS_LIST,
    MEASURE_DEVICE_ACCOUNTS_UPDATE_MODEL,
    MEASURE_DEVICE_ACCOUNTS_SEARCH_PARAMS,
    MEASURE_DEVICE_ACCOUNTS_ADD_MODAL,
    MEASURE_DEVICE_ACCOUNTS_UPDATE_DATA,
    MEASURE_DEVICE_ACCOUNTS_CHECK_MODAL
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    measuringDeviceNum: "",  //计量器具编号
    manufacturingNum: "",  //出厂编号
    useStateCode: ""  //使用状态代码
};

const initialState = {
    params: INIT_PARAMS,  //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    editState: false,   //编辑窗体
    addState: false,   //新增窗体
    data:{},
    checkState:false,  //检测窗体
    id:""               //计量器具主键ID
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case MEASURE_DEVICE_ACCOUNTS_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case MEASURE_DEVICE_ACCOUNTS_UPDATE_MODEL:
            return Object.assign({}, state, {
                editState: action.isUpdateShow,
                data: action.data || {},
            });
        case MEASURE_DEVICE_ACCOUNTS_ADD_MODAL:
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case MEASURE_DEVICE_ACCOUNTS_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case MEASURE_DEVICE_ACCOUNTS_UPDATE_DATA:
            return Object.assign({}, state, {
                data: action.data
            });
        case MEASURE_DEVICE_ACCOUNTS_CHECK_MODAL:
            return Object.assign({}, state, {
                checkState: action.isCheckShow,
                id: action.id || "",
            });
        default:
            return state
    }
}
