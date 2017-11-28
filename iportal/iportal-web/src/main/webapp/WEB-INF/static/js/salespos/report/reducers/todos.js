import * as types from "../constants/ActionTypes";


const INIT_PARAMS = {
    page: 0,
    size: 10,
    orderType: "SALES_ORDER", //订单类型：销售、退货
    orderNum: "",
    goodsSearchFields: "",
    memberSearchFields: "",
    formCreateDateString: "",
    toCreateDateString: ""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    orderId: null,
    sumOrderTotalAmount:0,
    orderItems: [],                  //订单项
    selectOrderIndex: 0,
    isSellReturnedPurchaseOrder:false,  //是否是退货订单
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SALES_REPORT_ORDER_LIST:{
            const orderId = action.data.content.length <= 0 ? 0 : action.data.content[0].id;
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                }),
                orderId: orderId,
                selectOrderIndex: 0
            });
        }
        case types.SALES_REPORT_ORDER_ITEM_LIST:{
            return Object.assign({}, state, {
                orderItems: action.data
            });
        }
        //订单列表焦点调整至上一项
        case types.SALES_REPORT_ORDER_INDEX_UP:{
            const upCartNewIndex = state.selectOrderIndex <= 0 ? state.selectOrderIndex :
                !state.page.content || state.page.content.length == 0 ?  -1 : state.selectOrderIndex-1;
            const orderId = upCartNewIndex < 0 ? 0 : state.page.content[upCartNewIndex].id;
            return Object.assign({}, state, {
                selectOrderIndex: upCartNewIndex,
                orderId: orderId
            });
        }
        //订单列表焦点调整至下一项
        case types.SALES_REPORT_ORDER_INDEX_DOWN:{
            const downCartNewIndex = state.selectOrderIndex >= state.page.content.length-1  ? state.page.content.length-1 :
            !state.page.content || state.page.content.length == 0 ?  -1 : state.selectOrderIndex+1;
            const orderId = downCartNewIndex < 0 ? 0 : state.page.content[downCartNewIndex].id;
            return Object.assign({}, state, {
                selectOrderIndex: downCartNewIndex,
                orderId: orderId
            });
        }
        //重置购订单列表焦点
        case types.SALES_REPORT_ORDER_SEARCH_SUBMIT:

            return Object.assign({}, state, {
                selectOrderIndex: -1,
                orderId: 0
            });
        case types.SALES_REPORT_SUM_ORDER_TOTAL_AMOUNT:
            return Object.assign({}, state, {
                sumOrderTotalAmount: action.data
            });
        case types.SALES_REPORT_ORDER_NUM_SEARCH_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    orderNum: action.data
                })
            });
        case types.SALES_REPORT_GOODS_SEARCH_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    goodsSearchFields: action.data
                })
            });
        case types.SALES_REPORT_MEMBER_SEARCH_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    memberSearchFields: action.data
                })
            });
        case types.SALES_REPORT_ORDER_TYPE_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    orderType: action.data,
                })
            });
        case types.SALES_REPORT_ORDER_TIME_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    formCreateDateString: action.data.formCreateDateString,
                    toCreateDateString: action.data.toCreateDateString,
                })
            });
        case types.SALES_REPORT_IS_RETURNED_PURCHASE_ORDER:
            return Object.assign({},state,{
                isSellReturnedPurchaseOrder:action.data
            });
        case types.SALES_REPORT_GOODS_SEARCH_PARAMS_SET:
            return Object.assign({},state,{
                params:action.data
            });
        default:
            return state
    }
}
