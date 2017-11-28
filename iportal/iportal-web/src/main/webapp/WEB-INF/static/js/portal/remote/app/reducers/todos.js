import { PORTAL_APP_LIST ,PORTAL_APP_ADD_MODAL,PORTAL_APP_UPDATE_MODAL,PORTAL_APP_LIST_ROW_SELECT,PORTAL_APP_LIST_MULTI_ROW_SELECT,PORTAL_APP_UPDATE_EXPORT_ROWS} from '../constants/ActionTypes';

const initialState = {
  page:{},
  appData:{},
  modalState: false,
  updateModalState: false,
  appId:null,
  appIds:[],
  exportRows:[]
}


export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_APP_LIST:
      return Object.assign({}, state, {
        page: action.data,
        appId:null,
        appIds:[]
      });
    case PORTAL_APP_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        appData: action.appData
      });
    case PORTAL_APP_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        appData: action.appData
      });
    case PORTAL_APP_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          appId:action.appObj.id,
          appIds: state.appIds.concat(action.appObj.id)
        });
      }else {
        var newAppIds = [];
        state.appIds.map(function(appid){
          if(appid!=action.appObj.id){
            newAppIds.push(appid)
          }
        });
        return Object.assign({}, state, {
          appIds: newAppIds
        });
      }
    case PORTAL_APP_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        appIds: action.appIds
      });
      case PORTAL_APP_UPDATE_EXPORT_ROWS:
          return Object.assign({}, state, {
              exportRows: action.exportRows
          });
    default:
      return state
  }
}
