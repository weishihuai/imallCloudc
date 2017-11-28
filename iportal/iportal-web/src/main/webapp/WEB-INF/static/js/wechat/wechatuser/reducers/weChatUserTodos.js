import * as types from "../constants/ActionTypes";

const initState = {
    data:{},
    id: "",
    weChatUserInfoDetailData:{} ,  //详情数据
    updateWeChatUserNickNameUpdateState:false,
};

export default function weChatUserTodos(state = initState, action) {
     switch (action.type){
         case types.WE_CHAT_USER_INFO_DATA:
             return Object.assign({}, state, {
                 data: action.data
             });
         case types.WE_CHAT_USER_INFO_DETAIL_DATA:  //详情数据
             return Object.assign({}, state, {
                 weChatUserInfoDetailData: action.data,
             });
         case types.WE_CHAT_USER_UPDATE_NICKNAME_MODEL:
             return Object.assign({}, state, {
                 updateWeChatUserNickNameUpdateState: action.isUpdateModelShow,
                 data: action.data || {},
             });
         default:
             return state;
     }
}