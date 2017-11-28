import { PORTAL_EXCEPTION_LIST,PORTAL_EXCEPTION_UPDATE_MODAL,PORTAL_EXCEPTION_LIST_ROW_SELECT,PORTAL_EXCEPTION_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

const initialState = {
  page:{},
  exceptionData:{},
  modalState: false,
  updateModalState: false,
  exceptionId:null,
  exceptionIds:[]
}


export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_EXCEPTION_LIST:
      return Object.assign({}, state, {
        page: action.data,
        exceptionId:null,
        exceptionIds:[]
      });
    case PORTAL_EXCEPTION_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        exceptionData: action.exceptionData
      });
    case PORTAL_EXCEPTION_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          exceptionId:action.exceptionObj.id,
          exceptionIds: state.exceptionIds.concat(action.exceptionObj.id)
        });
      }else {
        var newExceptionIds = [];
        state.exceptionIds.map(function(exceptionId){
          if(exceptionId!=action.exceptionObj.id){
            newExceptionIds.push(exceptionId)
          }
        });
        return Object.assign({}, state, {
          exceptionIds: newExceptionIds
        });
      }
    case PORTAL_EXCEPTION_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        exceptionIds: action.exceptionIds
      });
    default:
      return state
  }
}
