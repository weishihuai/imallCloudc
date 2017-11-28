import {combineReducers} from "redux";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import todos from "./todos";
import {reducer as formReducer} from "redux-form";
import validTodos from '../../../../../../common/validForm/reducers/validTodos'
import goodsBatchTodos from '../../../../../../common/goodsbatchselectwin/reducers/goodsBatchTodos'

const rootReducer = combineReducers({
    authTodos: authTodos,
    todos: todos,
    validTodos: validTodos,
    goodsBatchTodos:goodsBatchTodos,
    form: formReducer
});

export default rootReducer
