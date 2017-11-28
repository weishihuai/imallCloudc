import { OPERATIONAL_AUTH_PERMISSION } from '../constants/ActionTypes'

const initialState = {
  authsMap: new Map()
}


export default function authTodos(state = initialState, action) {
  switch (action.type) {
    case OPERATIONAL_AUTH_PERMISSION:
      const authsMap = state.authsMap;
      for (var auth  in action.data) {
        authsMap.set(auth, action.data[auth]);
      }
      return Object.assign({}, state, {
        authsMap: authsMap
      });
    default:
      return state
  }
}
