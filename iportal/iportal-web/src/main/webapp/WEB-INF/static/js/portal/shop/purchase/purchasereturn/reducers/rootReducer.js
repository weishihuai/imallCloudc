import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import goodsTodos from "../../../../../common/goodsselectwin/reducers/goodsTodos";
import supplieComponentTodos from "../../../../../common/supplierSelectwin/reducers/supplieComponentTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import approveValidateTodos from "../../../../../common/approvevalidate/reducers/todos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos,
  goodsTodos,
  supplieComponentTodos,
  todos,
  validTodos,
  approveValidateTodos,
  form: formReducer
});

export default rootReducer
