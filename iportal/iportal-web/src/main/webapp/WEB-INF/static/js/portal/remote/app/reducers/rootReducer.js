import { combineReducers } from 'redux'
import authTodos from '../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import fileMgrTodos from '../../../../common/filemgr/reducers/fileMgrTodos';
import {reducer as formReducer} from 'redux-form'

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  fileMgrTodos:fileMgrTodos,
  form: formReducer
})

export default rootReducer
