import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../../common/validForm/reducers/validTodos";
import approveValidateTodos from "../../../../../../../js/common/approvevalidate/reducers/todos";
import authTodos from '../../../../../../common/imallbutton/reducers/authTodos'
import {reducer as formReducer} from "redux-form";
const rootReducer = combineReducers({
  todos:todos,
  validTodos:validTodos,
  authTodos:authTodos,
  approveValidateTodos:approveValidateTodos,
  form: formReducer
});

export default rootReducer
