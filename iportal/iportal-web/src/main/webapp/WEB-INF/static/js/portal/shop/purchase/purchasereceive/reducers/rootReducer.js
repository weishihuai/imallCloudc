import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import purchaseOrderDetailTodos from "../../purchaseorderdetail/reducers/purchaseOrderDetailTodos";
import purchaseReceiveTodos from "../../purchasereceiveform/reducers/purchaseReceiveTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos,
  todos,
  purchaseOrderDetailTodos,
  purchaseReceiveTodos,
  validTodos,
  form: formReducer
});

export default rootReducer
