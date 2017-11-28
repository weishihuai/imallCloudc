import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../../common/validForm/reducers/validTodos";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import approveValidateTodos from "../../../../../../common/approvevalidate/reducers/todos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  approveValidateTodos:approveValidateTodos,
  form: formReducer
});

export default rootReducer
