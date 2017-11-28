import {combineReducers} from 'redux'
import authTodos from '../../../../common/imallbutton/reducers/authTodos'
import todos from './todos'
import {reducer as formReducer} from 'redux-form'
import validTodos from '../../../../common/validForm/reducers/validTodos'
import supplieDocComponentTodos from '../../../../common/supplierdocselectwin/reducers/supplieDocComponentTodos'
import fileMgrTodos from '../../../../common/filemgr/reducers/fileMgrTodos';

const rootReducer = combineReducers({
    authTodos: authTodos,
    validTodos: validTodos,
    todos: todos,
    fileMgrTodos: fileMgrTodos,
    supplieDocComponentTodos: supplieDocComponentTodos,
    form: formReducer
});

export default rootReducer
