import {combineReducers} from "redux";
import authTodos from "../../../../common/imallbutton/reducers/authTodos";
import todos from "./todos";

const rootReducer = combineReducers({
    authTodos,
    todos
});

export default rootReducer
