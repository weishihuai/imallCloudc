import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import approveValidateTodos from "../../../../../common/approvevalidate/reducers/todos";
import purchaseOrderDetailTodos from "../../purchaseorderdetail/reducers/purchaseOrderDetailTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos,
  validTodos,
  approveValidateTodos,
  todos,
  purchaseOrderDetailTodos,
  form: formReducer
});

export default rootReducer
