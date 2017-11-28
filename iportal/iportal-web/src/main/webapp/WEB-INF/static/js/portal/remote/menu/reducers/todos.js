import {
    PORTAL_MENU_LIST,
    PORTAL_MENU_SEARCH,
    PORTAL_MENU_FIND_TREE,
    PORTAL_MENU_ADD_MODAL,
    RESOURCE_FORM_CHANGE_TO_STATE,
    RESOURCE_SELECT_MODAL,
    ICON_SELECT_MODAL,
    RELOAD_MENU_ADD_FORM,
    BIND_FORM_VLAUE,
    PORTAL_MENU_UPDATE_MODAL,
    PORTAL_MENU_LIST_ROW_SELECT,
    PORTAL_MENU_LIST_MULTI_ROW_SELECT,
    PORTAL_MENU_TREE_REFRESH
} from "../constants/ActionTypes";

const initialState = {
  page:{},
  searchData:{menuName:""},
  treePId:"",
  menuData:{},
  modalState:false,
  updateModalState:false,
  resourceSelectModalState:false,
  iconSelectModalState:false,
  menuId:null,
  menuIds:[],
  isRefreshTree:false
}

export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_MENU_LIST:
      return Object.assign({}, state, {
        page: action.data,
        menuId:null,
        menuIds:[]
      });
    case PORTAL_MENU_SEARCH:
      return Object.assign({}, state, {
        searchData: action.searchData
      });
    case PORTAL_MENU_FIND_TREE:
      return Object.assign({}, state, {
        treePId:action.treePId
      });
    case PORTAL_MENU_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        menuData: action.menuData
      });
    case PORTAL_MENU_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        menuData: action.menuData
      });
    case RESOURCE_FORM_CHANGE_TO_STATE:
      return Object.assign({}, state, {
        menuData:  Object.assign({}, state.menuData, action.formData)
      });
    case RESOURCE_SELECT_MODAL:
      const resourceActualState = state.resourceSelectModalState==undefined?false:state.resourceSelectModalState;
      const resourceNewState = resourceActualState === false;
      return Object.assign({}, state, {
        resourceSelectModalState:resourceNewState
      });
    case ICON_SELECT_MODAL:
      const iconActualState = state.iconSelectModalState==undefined?false:state.iconSelectModalState;
      const iconNewState = iconActualState === false;
      return Object.assign({}, state, {
        iconSelectModalState:iconNewState
      });
    case RELOAD_MENU_ADD_FORM:
      return Object.assign({}, state, {
        menuData : Object.assign({}, state.menuData, {
          resourceId:action.menuData.resourceId==null?state.menuData.resourceId:action.menuData.resourceId,
          resourceName:action.menuData.resourceName==null?state.menuData.resourceName:action.menuData.resourceName,
          icon:action.menuData.icon==null||action.menuData.icon==undefined?(state.menuData.icon==undefined?"":state.menuData.icon):action.menuData.icon
        })
      });
    case BIND_FORM_VLAUE:
      return Object.assign({}, state, {
        menuData : Object.assign({}, state.menuData, {
          [action.field] : action.inputValue
        })
      });
    case PORTAL_MENU_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          menuId: action.menuObj.id,
          menuIds: state.menuIds.concat(action.menuObj.id)
        });
      }else {
        var newMenuIds = [];
        state.menuIds.map(function(menuid){
          if(menuid!=action.menuObj.id){
            newMenuIds.push(menuid)
          }
        });
        return Object.assign({}, state, {
          menuIds: newMenuIds
        });
      }
    case PORTAL_MENU_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        menuIds: action.menuIds
      });
    case PORTAL_MENU_TREE_REFRESH:
      return Object.assign({}, state, {
        isRefreshTree: action.isRefreshTree
      });
    default:
      return state
  }
}
