import {combineReducers} from 'redux'
import authTodos from '../../../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import {reducer as formReducer} from 'redux-form'
import validTodos from '../../../../../../common/validForm/reducers/validTodos'
import approveValidateTodos from '../../../../../../common/approvevalidate/reducers/todos'
const rootReducer = combineReducers({
    approveValidateTodos: approveValidateTodos,
    authTodos: authTodos,
    validTodos: validTodos,
    todos: todos,
    form: formReducer
});

export default rootReducer
