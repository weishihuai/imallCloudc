import {combineReducers} from 'redux'
import logininfoTodos from './logininfoTodos'
import menuTodos from './menuTodos'
import routerTodos from './routerTodos'
import {reducer as formReducer} from 'redux-form'
import validTodos from '../../../common/validForm/reducers/validTodos'
const rootReducer = combineReducers({
    logininfoTodos: logininfoTodos,
    validTodos: validTodos,
    menuTodos: menuTodos,
    routerTodos: routerTodos,
    form: formReducer
})

export default rootReducer
