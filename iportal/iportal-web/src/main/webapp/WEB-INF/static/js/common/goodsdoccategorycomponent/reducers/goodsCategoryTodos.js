import {GOODS_DOC_CATEGORY_STATE } from '../constants/ActionTypes'

const initialState = {
    options: {},
    articleCategoryModal: false,
    articleCategoryTreeDepth: 100,
    selectArticleCategoryOnSuccess: ()=>{}
};


export default function goodsCategoryTodos(state = initialState, action) {
    switch (action.type) {
        case GOODS_DOC_CATEGORY_STATE: {
            return Object.assign({}, state, {
                articleCategoryModal: action.isShow,
                options: action.options,
                selectArticleCategoryOnSuccess: action.onSuccess
            });
        }

        default:
            return state
    }
}
