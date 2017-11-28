import {combineReducers} from "redux";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import todos from "./todos";
import {reducer as formReducer} from "redux-form";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import goodsTodos from "../../../../../common/goodsselectwin/reducers/goodsTodos";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos: validTodos,
  goodsTodos: goodsTodos,
  form: formReducer
});

export default rootReducer
