import { PORTAL_CACHE_LIST,PORTAL_CACHE_UPDATE_MODAL,PORTAL_CACHE_LIST_ROW_SELECT,PORTAL_CACHE_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

const initialState = {
  page:{},
  cacheData:{},
  modalState: false,
  updateModalState: false,
  cacheId:null,
  cacheIds:[]
}


export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_CACHE_LIST:
      return Object.assign({}, state, {
        page: action.data,
        cacheId:null,
        cacheIds:[]
      });
    case PORTAL_CACHE_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        cacheData: action.cacheData
      });
    case PORTAL_CACHE_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          cacheId:action.cacheObj.id,
          cacheIds: state.cacheIds.concat(action.cacheObj.id)
        });
      }else {
        var newCacheIds = [];
        state.cacheIds.map(function(cacheId){
          if(cacheId!=action.cacheObj.id){
            newCacheIds.push(cacheId)
          }
        });
        return Object.assign({}, state, {
          cacheIds: newCacheIds
        });
      }
    case PORTAL_CACHE_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        cacheIds: action.cacheIds
      });
    default:
      return state
  }
}
