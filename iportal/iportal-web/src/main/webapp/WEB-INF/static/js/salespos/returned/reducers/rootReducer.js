import {combineReducers} from "redux";
import todos from "./todos";
import approveValidateTodos from "../../../../js/common/approvevalidate/reducers/todos";
import validTodos from "../../../common/validForm/reducers/validTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
    todos: todos,
    approveValidateTodos:approveValidateTodos,
    validTodos:validTodos,
    form: formReducer
});

export default rootReducer
