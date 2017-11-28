import {
    PORTAL_DICT_LIST,
    PORTAL_DICT_ADD_MODAL,
    PORTAL_DICT_UPDATE_MODAL,
    PORTAL_DICT_LIST_ROW_SELECT,
    PORTAL_DICT_LIST_MULTI_ROW_SELECT,
    PORTAL_DICT_ITEM_LIST_ROW_SELECT,
    PORTAL_DICT_ITEM_LIST_MULTI_ROW_SELECT,
    PORTAL_DICT_ITEM_ADD_MODAL,
    PORTAL_DICT_ITEM_UPDATE_MODAL,
    DICT_ITEM_MGR_MODAL} from '../constants/ActionTypes';

const initialState = {
  page:{},
  dictData:{},
  modalState: false,
  updateModalState: false,
  dictId:null,
  dictItemId:null,
  dictIds:[],
  dictItemIds:[],
  dictItemModalState: false,
  dictItemUpdateModalState: false,
  dictItemData:{},
  dictItemMgrModalState:false,
  dictItemMgrPage:{content:[],totalElements:0}
}

export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_DICT_LIST:
      return Object.assign({}, state, {
        page: action.data,
        dictId:null,
        dictIds:[]
      });
    case PORTAL_DICT_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        dictData: action.dictData
      });
    case PORTAL_DICT_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        dictData: action.dictData
      });
    case PORTAL_DICT_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          dictId:action.dictObj.id,
          dictIds: state.dictIds.concat(action.dictObj.id)
        });
      }else {
        var newDictIds = [];
        state.dictIds.map(function(dictid){
          if(dictid!=action.dictObj.id){
            newDictIds.push(dictid)
          }
        });
        return Object.assign({}, state, {
          dictIds: newDictIds
        });
      }
    case PORTAL_DICT_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        dictIds: action.dictIds
      });
    case PORTAL_DICT_ITEM_LIST_ROW_SELECT :
      if(action.isSelected){
        state.dictItemId = action.dictItemObj.id;
        state.dictItemIds = state.dictItemIds.concat(action.dictItemObj.id);
      }else {
        var newDictItemIds = [];
        state.dictItemIds.map(function(dictItemid){
          if(dictItemid!=action.dictItemObj.id){
            newDictItemIds.push(dictItemid)
          }
        });
        state.dictItemIds = newDictItemIds;
      }
      return state;
    case PORTAL_DICT_ITEM_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        dictItemIds: action.dictItemIds
      });
    case PORTAL_DICT_ITEM_ADD_MODAL:
      const dictItemActualState = state.dictItemModalState==undefined?false:state.dictItemModalState;
      const dictItemNewState = dictItemActualState === false;
      return Object.assign({}, state, {
        dictItemModalState:dictItemNewState,
        dictItemData: action.dictItemData
      });
    case PORTAL_DICT_ITEM_UPDATE_MODAL:
      const dictItemUpdateModalState = state.dictItemUpdateModalState==undefined?false:state.dictItemUpdateModalState;
      const dictItemUpdateNewState = dictItemUpdateModalState === false;
      return Object.assign({}, state, {
        dictItemUpdateModalState:dictItemUpdateNewState,
        dictItemData: action.dictItemData
      });
    case DICT_ITEM_MGR_MODAL:
      const dictItemMgrActualState = state.dictItemMgrModalState==undefined?false:state.dictItemMgrModalState;
      const dictItemMgrNewState = dictItemMgrActualState === false;

      return Object.assign({}, state, {
        dictItemMgrModalState:action.isReload?true:dictItemMgrNewState,
        dictItemMgrPage: action.dictItemMgrPage
      });
    default:
      return state
  }
}
