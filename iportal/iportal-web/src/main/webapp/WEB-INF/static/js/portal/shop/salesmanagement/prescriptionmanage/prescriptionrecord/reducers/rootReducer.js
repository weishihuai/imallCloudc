import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../../common/imallbutton/reducers/authTodos";
import prescriptionTodos from "../../prescription/reducers/prescriptionTodos";
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  prescriptionTodos:prescriptionTodos,
  form: formReducer
});

export default rootReducer
