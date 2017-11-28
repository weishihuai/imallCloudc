import {combineReducers} from "redux";
import todos from "./todos";
import authTodos from "../../../../../common/imallbutton/reducers/authTodos";
import validTodos from "../../../../../common/validForm/reducers/validTodos";
import fileMgrTodos from '../../../../../common/filemgr/reducers/fileMgrTodos';
import {reducer as formReducer} from "redux-form";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos:validTodos,
  fileMgrTodos:fileMgrTodos,
  form: formReducer
});

export default rootReducer
