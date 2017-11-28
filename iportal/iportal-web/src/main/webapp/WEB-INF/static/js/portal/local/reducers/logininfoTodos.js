/**
 * Created by ygw on 2016/6/22.
 */

import { REQ_LOGIN_USER,REQ_LOGIN_JOBS, OPT_MODIFY_PASSWORD } from '../constants/ActionTypes'

const initialState = {
    loginUser: {org: '', userName:'', realName:'', employeeCode: ''},
    jobsVo:{currJob:{jobName:''}, jobs: []},
    modalState: false
}


export default function logininfoTodos(state = initialState, action) {
    switch (action.type) {
        case REQ_LOGIN_USER:
            return Object.assign({}, state, {
                loginUser: action.data
            });
        case REQ_LOGIN_JOBS:
            return Object.assign({}, state, {
                jobsVo: action.data
            });
        case OPT_MODIFY_PASSWORD:
            const actualState = state.modalState==undefined?false:state.modalState;
            const newState = actualState === false;
            return Object.assign({}, state, {
                modalState:newState
            });
        default:
            return state
    }
}

