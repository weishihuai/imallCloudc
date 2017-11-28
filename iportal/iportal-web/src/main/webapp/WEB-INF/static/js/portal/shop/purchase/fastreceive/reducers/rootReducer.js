import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import goodsTodos from "../../../../../common/goodsselectwin/reducers/goodsTodos";
import supplieComponentTodos from "../../../../../common/supplierSelectwin/reducers/supplieComponentTodos";
import approveValidateTodos from "../../../../../common/approvevalidate/reducers/todos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos,
  validTodos,
  goodsTodos,
  supplieComponentTodos,
  approveValidateTodos,
  todos,
  form: formReducer
});

export default rootReducer
