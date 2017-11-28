import {combineReducers} from "redux";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import todos from "./todos";
import {reducer as formReducer} from "redux-form";
import validTodos from '../../../../../../common/validForm/reducers/validTodos'
import approveValidateTodos from '../../../../../../common/approvevalidate/reducers/todos'

const rootReducer = combineReducers({
    authTodos: authTodos,
    todos: todos,
    validTodos: validTodos,
    approveValidateTodos: approveValidateTodos,
    form: formReducer
});

export default rootReducer
