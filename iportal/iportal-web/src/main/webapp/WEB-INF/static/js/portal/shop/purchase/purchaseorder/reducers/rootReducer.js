import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import goodsTodos from "../../../../../common/goodsselectwin/reducers/goodsTodos";
import supplieComponentTodos from "../../../../../common/supplierSelectwin/reducers/supplieComponentTodos";
import purchaseOrderDetailTodos from "../../purchaseorderdetail/reducers/purchaseOrderDetailTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import purchaseReceiveTodos from "../../purchasereceiveform/reducers/purchaseReceiveTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos,
  goodsTodos,
  supplieComponentTodos,
  todos,
  validTodos,
  purchaseOrderDetailTodos,
  purchaseReceiveTodos,
  form: formReducer
});

export default rootReducer
