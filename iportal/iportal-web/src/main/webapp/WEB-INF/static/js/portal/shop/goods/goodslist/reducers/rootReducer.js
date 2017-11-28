import { combineReducers } from 'redux'
import authTodos from '../../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import {reducer as formReducer} from 'redux-form'
import fileMgrTodos from '../../../../../common/filemgr/reducers/fileMgrTodos';
import validTodos from "../../../../../common/validForm/reducers/validTodos";

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  fileMgrTodos:fileMgrTodos,
  validTodos: validTodos,
  form: formReducer
});

export default rootReducer
