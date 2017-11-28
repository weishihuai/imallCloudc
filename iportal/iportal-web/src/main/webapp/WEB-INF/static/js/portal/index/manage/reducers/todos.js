import {INDEX_MANAGE_LIST,INDEX_MANAGE_LIST_ROW_SELECT,INDEX_MANAGE_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

const initialState = {
  page:{},
  data:{},
  id:null,
  ids:[]
}


export default function todos(state = initialState, action) {
  switch (action.type) {
    case INDEX_MANAGE_LIST:
      return Object.assign({}, state, {
        page: action.data,
        id:null,
        ids:[]
      });
    case INDEX_MANAGE_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          id:action.obj.id,
          ids: state.ids.concat(action.obj.id)
        });
      }else {
        var newIds = [];
        state.ids.map(function(id){
          if(id!=action.obj.id){
            newIds.push(id)
          }
        });
        return Object.assign({}, state, {
          ids: newIds
        });
      }
    case INDEX_MANAGE_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        ids: action.ids
      });
    default:
      return state
  }
}
