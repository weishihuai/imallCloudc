import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../../common/validForm/reducers/validTodos";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import prescriptionTodos from "../../prescription/reducers/prescriptionTodos";
import orderTodos from "../../../../../../../js/common/orderselectwin/reducers/orderTodos";
import approveValidateTodos from "../../../../../../../js/common/approvevalidate/reducers/todos";
import fileMgrTodos from '../../../../../../common/filemgr/reducers/fileMgrTodos';
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  prescriptionTodos:prescriptionTodos,
  orderTodos:orderTodos,
  approveValidateTodos:approveValidateTodos,
  fileMgrTodos:fileMgrTodos,
  form: formReducer
});

export default rootReducer
