import {
    FILE_UPLOAD_MODAL,
    FILE_TREE_MODAL,
    FILE_MGR_MODAL,
    FILE_MGR_MODAL_HAS_CALL_BACK_FUNC,
    FILE_SELECT_RESULT,
    FILE_CREATE_FILE_CATEGORY_MODAL,
    FILE_EDIT_FILE_CATEGORY_MODAL,
    FILE_DELETE_FILE_CATEGORY_MODAL,
    FILE_BATCH_DELETE_FILE_MODAL,
    FILE_BATCH_SET_FILE_LIST
} from "../constants/ActionTypes";

const initialState = {
  modalState:false,
  fileTreeModalState:false,
  fileMgrModalState:false,
  createFileCategoryModalState:false,   //新建文件目录窗口展示
  editFileCategoryModalState: false,    //编辑文件目录窗口展示
  deleteFileCategoryModalState: false,    //删除文件目录确认弹出框
  batchDeleteFileModalState: false,    //删除文件确认弹出框
  fileIdArray:[],                         //已选择

    page: {content: [], totalElements: 0, number: 0, size: 10},

}


export default function fileMgrTodos(state = initialState, action) {
  switch (action.type) {
    case FILE_UPLOAD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState
      });
    case FILE_TREE_MODAL:
      const fileTreeActualState = state.fileTreeModalState==undefined?false:state.fileTreeModalState;
      const fileTreeNewState = fileTreeActualState === false;
      return Object.assign({}, state, {
        fileTreeModalState:fileTreeNewState
      });
    case FILE_MGR_MODAL:
      const fileMgrActualState = state.fileMgrModalState==undefined?false:state.fileMgrModalState;
      const fileMgrNewState = fileMgrActualState === false;
      return Object.assign({}, state, {
        fileMgrModalState:fileMgrNewState
      });
    case FILE_MGR_MODAL_HAS_CALL_BACK_FUNC:{
      const fileMgrActualState = state.fileMgrModalState==undefined?false:state.fileMgrModalState;
      const fileMgrNewState = fileMgrActualState === false;
      return Object.assign({}, state, {
        fileMgrModalState:fileMgrNewState,
        callbackFunc:action.callbackFunc
      });
    }
    case FILE_SELECT_RESULT:
      return Object.assign({}, state, {
        fileIdArray:action.fileIdArray
      });
    case FILE_CREATE_FILE_CATEGORY_MODAL:
      return Object.assign({}, state, {
        createFileCategoryModalState:action.isShow
      });
    case FILE_EDIT_FILE_CATEGORY_MODAL:
      return Object.assign({}, state, {
        editFileCategoryModalState:action.isShow
      });
    case FILE_DELETE_FILE_CATEGORY_MODAL:
      return Object.assign({}, state, {
        deleteFileCategoryModalState:action.isShow
      });
    case FILE_BATCH_DELETE_FILE_MODAL:
      return Object.assign({}, state, {
        batchDeleteFileModalState:action.isShow
      });

    case FILE_BATCH_SET_FILE_LIST:
      return Object.assign({}, state, {
          page:action.json
      });
    default:
      return state
  }
}
