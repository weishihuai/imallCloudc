import {combineReducers} from "redux";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import todos from "./todos";
import {reducer as formReducer} from "redux-form";
import goodsTodos from '../../../../../../common/goodsselectwin/reducers/goodsTodos'
import approveValidateTodos from '../../../../../../common/approvevalidate/reducers/todos'
import validTodos from '../../../../../../common/validForm/reducers/validTodos'
import supplieComponentTodos from "../../../../../../common/supplierSelectwin/reducers/supplieComponentTodos";

const rootReducer = combineReducers({
    authTodos: authTodos,
    todos: todos,
    goodsTodos: goodsTodos,
    approveValidateTodos: approveValidateTodos,
    validTodos: validTodos,
    supplieComponentTodos: supplieComponentTodos,
    form: formReducer
});

export default rootReducer
