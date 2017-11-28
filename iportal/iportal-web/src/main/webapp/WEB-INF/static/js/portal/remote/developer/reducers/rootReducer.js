import { combineReducers } from 'redux'
import authTodos from '../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import {reducer as formReducer} from 'redux-form'

const rootReducer = combineReducers({
    authTodos:authTodos,
    todos:todos,
    form: formReducer
})

export default rootReducer