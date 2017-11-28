import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";

const rootReducer = combineReducers({
  todos,
  authTodos,
});

export default rootReducer
