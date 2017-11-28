import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import goodsBatchTodos from "../../../../../../js/common/goodsbatchselectwin/reducers/goodsBatchTodos";
import approveValidateTodos from "../../../../../../js/common/approvevalidate/reducers/todos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  goodsBatchTodos:goodsBatchTodos,
  approveValidateTodos:approveValidateTodos,
  form: formReducer
});

export default rootReducer
