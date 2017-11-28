import {APPROVE_VALIDATE_COMPONENT_MODAL} from  '../constants/ActionTypes'

const initialState = {
  display: false,
  approveType:""
};

export default function approveValidateTodos(state = initialState, action) {
    switch (action.type){
        case APPROVE_VALIDATE_COMPONENT_MODAL:
            return Object.assign({}, state, {display: !state.display,approveType:action.data||""});
        default:
            return state;
    }
}