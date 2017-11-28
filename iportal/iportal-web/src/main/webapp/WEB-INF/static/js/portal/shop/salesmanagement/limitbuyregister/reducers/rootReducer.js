import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import orderTodos from "../../../../../../js/common/orderselectwin/reducers/orderTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  orderTodos:orderTodos,
  form: formReducer
});

export default rootReducer
