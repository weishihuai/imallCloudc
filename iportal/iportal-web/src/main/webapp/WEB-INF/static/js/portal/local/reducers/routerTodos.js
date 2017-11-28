import { REQ_ROUTER_CONFIGS } from '../constants/ActionTypes'

const initialState = {
  menuRouters: []
}


export default function routerTodos(state = initialState, action) {
  switch (action.type) {
    case REQ_ROUTER_CONFIGS:
      return Object.assign({}, state, {
        menuRouters: action.data
      });

    default:
      return state
  }
}
