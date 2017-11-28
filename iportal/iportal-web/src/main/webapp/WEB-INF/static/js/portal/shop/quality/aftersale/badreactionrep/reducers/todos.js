import {
    BAD_REACTION_REP_ADD_MODEL,
    BAD_REACTION_REP_DETAIL_DATA,
    BAD_REACTION_REP_DETAIL_MODEL,
    BAD_REACTION_REP_LIST,
    BAD_REACTION_REP_SEARCH_PARAMS,
    BAD_REACTION_REP_UPDATE_MODEL,
    BAD_REACTION_REP_DRUG_INF_DATA
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    repType: "",  //报告类型
    patientName: "", // 患者姓名
    repStartDateString: "", // 报告日期(开始时间)
    repEndDateString: "", // 报告日期(结束时间)
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
    id: "",
    addState: false,   //新增窗体
    updateState: false,   //编辑窗体
    badReactionRepDetailState: false, //详情窗口状态
    badReactionRepDetailData:{} ,  //详情数据
    badReactionRepDrugInfData:{} ,  //药品信息
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case BAD_REACTION_REP_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case BAD_REACTION_REP_LIST:   //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case BAD_REACTION_REP_ADD_MODEL:  //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case BAD_REACTION_REP_UPDATE_MODEL:  //显示 隐藏 更新 页面
            return Object.assign({}, state, {
                updateState: action.isUpdateModelShow,
                data: action.data || {},
            });
        case BAD_REACTION_REP_DETAIL_MODEL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                badReactionRepDetailState: action.isDetailShow,
                id: action.id
            });
        case BAD_REACTION_REP_DETAIL_DATA:  //详情数据
            for(let i = action.data.suspectDrugInfList.length;i < 3;i++){
                 action.data.suspectDrugInfList.push({});
             }
            for(let i = action.data.blendDrugInfList.length;i < 3;i++){
                action.data.blendDrugInfList.push({});
            }
            return Object.assign({}, state, {
                badReactionRepDetailData: action.data,
            });
        case BAD_REACTION_REP_DRUG_INF_DATA:
            for(let i = action.data.suspectDrugInfList.length;i < 3;i++){
                action.data.suspectDrugInfList.push({});
            }
            for(let i = action.data.blendDrugInfList.length;i < 3;i++){
                action.data.blendDrugInfList.push({});
            }
            return Object.assign({}, state, {
                badReactionRepDrugInfData: action.data,
            });
        default:
            return state
    }
}
