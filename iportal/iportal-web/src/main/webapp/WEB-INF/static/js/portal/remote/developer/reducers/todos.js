import { PORTAL_DEVELOPER_AUTH_LIST,PORTAL_DEVELOPER_AUTH_ADD_MODAL,PORTAL_DEVELOPER_AUTH_UPDATE_MODAL,PORTAL_DEVELOPER_AUTH_LIST_ROW_SELECT,PORTAL_DEVELOPER_AUTH_LIST_MULTI_ROW_SELECT,PORTAL_FIND_ABLE_APP } from '../constants/ActionTypes';

const initialState = {
    page:{},
    developerData:{},
    modalState: false,
    updateModalState: false,
    developerId:null,
    developerIds:[],
    apps:[]
}

export default function todos(state = initialState, action) {
    switch (action.type) {
        case PORTAL_DEVELOPER_AUTH_LIST:
            return Object.assign({}, state, {
                page: action.data,
                developerId:null,
                developerIds:[]
            });
        case PORTAL_DEVELOPER_AUTH_ADD_MODAL:
            const actualState = state.modalState==undefined?false:state.modalState;
            const newState = actualState === false;
            return Object.assign({}, state, {
                modalState:newState,
                developerData: action.developerData,
                apps:state.apps
            });
        case PORTAL_DEVELOPER_AUTH_UPDATE_MODAL:
            const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
            const updateNewState = updateActualState === false;
            return Object.assign({}, state, {
                updateModalState:updateNewState,
                developerData: action.developerData,
                apps:state.apps
            });
        case PORTAL_DEVELOPER_AUTH_LIST_ROW_SELECT:
            if(action.isSelected){
                return Object.assign({}, state, {
                    developerId:action.developerAuthObj.id,
                    developerIds: state.developerIds.concat(action.developerAuthObj.id)
                });
            }else {
                var newDeveloperIds = [];
                state.developerIds.map(function(developerId){
                    if(developerId!=action.developerAuthObj.id){
                        newDeveloperIds.push(developerId)
                    }
                });
                return Object.assign({}, state, {
                    developerIds: newDeveloperIds
                });
            }
        case PORTAL_DEVELOPER_AUTH_LIST_MULTI_ROW_SELECT:
            return Object.assign({}, state, {
                developerIds: action.developerIds
            });
        case PORTAL_FIND_ABLE_APP:
            return Object.assign({}, state, {
                apps:action.apps
            });
        default:
            return state
    }
}
