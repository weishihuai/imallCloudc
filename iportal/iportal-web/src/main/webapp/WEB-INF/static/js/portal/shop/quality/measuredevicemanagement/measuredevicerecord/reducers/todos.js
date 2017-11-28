import {
    MEASURE_RECORD_LIST,
    MEASURE_RECORD_SEARCH_PARAMS,
    MEASURE_RECORD_DETAIL_MODAL,
    MEASURE_RECORD_DETAIL_DATA
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    measuringDeviceNum: "",                      //计量器具编号
    manufacturingNum: "",                        //出厂编号
    measureDateStartString: "",                  //检测时间 (开始时间)
    measureDateEndString: ""                     //检测时间 (结束时间)
};

const initialState = {
    params: INIT_PARAMS,                              //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
    id: null,
    measureRecordDetailState: false,    //详情窗体状态
    measureRecordDetailData:{},     //详情数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case MEASURE_RECORD_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case MEASURE_RECORD_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case MEASURE_RECORD_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                measureRecordDetailState: action.isDetailShow,
                id: action.id
            });
        case MEASURE_RECORD_DETAIL_DATA:
            return Object.assign({}, state, {
                measureRecordDetailData: action.data
            });
        default:
            return state
    }
}
