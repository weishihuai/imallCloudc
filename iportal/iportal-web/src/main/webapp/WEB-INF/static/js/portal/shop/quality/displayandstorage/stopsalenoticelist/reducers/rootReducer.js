import { combineReducers } from 'redux'
import authTodos from '../../../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import {reducer as formReducer} from 'redux-form'
import validTodos from "../../../../../../common/validForm/reducers/validTodos";
import approveValidateTodos from "../../../../../../../js/common/approvevalidate/reducers/todos";
import goodsBatchTodos from '../../../../../../common/goodsbatchselectwin/reducers/goodsBatchTodos'

const rootReducer = combineReducers({
  authTodos:authTodos,
  todos:todos,
  validTodos: validTodos,
  approveValidateTodos: approveValidateTodos,
    goodsBatchTodos: goodsBatchTodos,
  form: formReducer
});

export default rootReducer
