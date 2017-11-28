import { PORTAL_PARAMINF_LIST,PORTAL_PARAMINF_ADD_MODAL,PORTAL_PARAMINF_UPDATE_MODAL,PORTAL_PARAMINF_LIST_ROW_SELECT,PORTAL_PARAMINF_LIST_MULTI_ROW_SELECT } from '../constants/ActionTypes';

const initialState = {
  page:{},
  paramInfData:{},
  modalState: false,
  updateModalState: false,
  paramInfId:null,
  paramInfIds:[]
}


export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_PARAMINF_LIST:
      return Object.assign({}, state, {
        page: action.data,
        paramInfId:null,
        paramInfIds:[]
      });
    case PORTAL_PARAMINF_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        paramInfData: action.paramInfData
      });
    case PORTAL_PARAMINF_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        paramInfData: action.paramInfData
      });
    case PORTAL_PARAMINF_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          paramInfId:action.paramInfObj.id,
          paramInfIds:state.paramInfIds.concat(action.paramInfObj.id)
        });
      }else {
        var newParamInfIds = [];
        state.paramInfIds.map(function(paramInfid){
          if(paramInfid!=action.paramInfObj.id){
            newParamInfIds.push(paramInfid)
          }
        });
        return Object.assign({}, state, {
          paramInfIds:newParamInfIds
        });
      }
    case PORTAL_PARAMINF_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        paramInfIds: action.paramInfIds
      });
    default:
      return state
  }
}
