import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import orderTodos from "../../order/reducers/orderTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  orderTodos:orderTodos,
  form: formReducer
});

export default rootReducer
