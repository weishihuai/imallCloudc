import { PORTAL_RESOURCE_LIST ,PORTAL_RESOURCE_SEARCH,PORTAL_RESOURCE_FIND_TREE,PORTAL_RESOURCE_ADD_MODAL,PORTAL_RESOURCE_UPDATE_MODAL,PORTAL_RESOURCE_LIST_ROW_SELECT,PORTAL_RESOURCE_LIST_MULTI_ROW_SELECT,PORTAL_FIND_ABLE_APP,PORTAL_RESOURCE_URL_MGR_MODAL,PORTAL_RESOURCE_URL_LIST_ROW_SELECT,PORTAL_RESOURCE_URL_LIST_MULTI_ROW_SELECT,PORTAL_RESOURCE_TREE_REFRESH} from '../constants/ActionTypes';

const initialState = {
  page:{},
  searchData:{isAvailable:"",searchName:"resourceName",searchValue:""},
  treePId:"",
  data:{},
  modalState: false,
  updateModalState: false,
  id:null,
  ids:[],
  apps:[],
  resourceName:null,
  resourceUrlId:null,
  resourceUrlIds:[],
  resourceUrlModalState: false,
  resourceUrlData:{},
  resourceUrlMgrPage: {content: [], totalElements: 0, number: 1, size: 10},
  isRefreshForm:true,
  isRefreshTree:false
}

export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_RESOURCE_LIST:
      return Object.assign({}, state, {
        page: action.data,
        id:null,
        ids:[],
        resourceUrlId:null,
        resourceUrlIds:[],
      });
    case PORTAL_RESOURCE_SEARCH:
      return Object.assign({}, state, {
        searchData: action.searchData,
        isRefreshForm:action.isRefreshForm
      });
    case PORTAL_RESOURCE_FIND_TREE:
      return Object.assign({}, state, {
        treePId:action.treePId
      });
    case PORTAL_RESOURCE_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        data: action.data,
        apps:action.apps
      });
    case PORTAL_RESOURCE_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        data: action.data,
        apps:state.apps
      });
    case PORTAL_RESOURCE_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          resourceName:action.obj.resourceName,
          id:action.obj.id,
          ids: state.ids.concat(action.obj.id)
        });
      }else {
        var newids = [];
        state.ids.map(function(id){
          if(id!=action.obj.id){
            newids.push(id)
          }
        });
        return Object.assign({}, state, {
          ids: newids
        });
      }
    case PORTAL_RESOURCE_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        ids: action.ids
      });
    case PORTAL_FIND_ABLE_APP:
      return Object.assign({}, state, {
        apps:action.apps
      });
    case PORTAL_RESOURCE_URL_LIST_ROW_SELECT:
      if(action.isSelected){
        state.resourceUrlId = action.resourceUrlObj.id;
        state.resourceUrlIds = state.resourceUrlIds.concat(action.resourceUrlObj.id);
      }else {
        var newResourceUrlIds = [];
        state.resourceUrlIds.map(function(resourceUrlId){
          if(resourceUrlId!=action.resourceUrlObj.id){
            newResourceUrlIds.push(resourceUrlId)
          }
        });
        state.resourceUrlIds = newResourceUrlIds;
      }
      return state;
    case PORTAL_RESOURCE_URL_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        resourceUrlIds: action.resourceUrlIds
      });
    case PORTAL_RESOURCE_URL_MGR_MODAL:
      const resourceUrlMgrActualState = state.resourceUrlMgrModalState==undefined?false:state.resourceUrlMgrModalState;
      const resourceUrlMgrNewState = resourceUrlMgrActualState === false;
      state.resourceUrlData = action.resourceUrlData
      return Object.assign({}, state, {
        resourceUrlMgrModalState:action.isReload?true:resourceUrlMgrNewState,
        resourceUrlMgrPage: action.resourceUrlMgrPage,
      });
    case PORTAL_RESOURCE_TREE_REFRESH:
      return Object.assign({}, state, {
        isRefreshTree: action.isRefreshTree
      });
    default:
      return state
  }
}
