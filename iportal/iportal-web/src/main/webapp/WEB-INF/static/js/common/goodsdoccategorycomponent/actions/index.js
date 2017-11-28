import * as types from "../constants/ActionTypes";
export function showArticleCategoryModal(isShow, onSuccess=()=>{}, options={}){
    const empty = {
        type: types.GOODS_DOC_CATEGORY_STATE,
        isShow: isShow,
        onSuccess: onSuccess,
        options: options
    };
    return function (dispatch) {
        dispatch(empty);
    };
}
