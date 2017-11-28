import {combineReducers} from "redux";
import todos from "./todos";
import validTodos from "../../../../../../common/validForm/reducers/validTodos";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import prescriptionTodos from "../../prescription/reducers/prescriptionTodos";
import approveValidateTodos from "../../../../../../../js/common/approvevalidate/reducers/todos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  prescriptionTodos:prescriptionTodos,
  approveValidateTodos:approveValidateTodos,
  form: formReducer
});

export default rootReducer
