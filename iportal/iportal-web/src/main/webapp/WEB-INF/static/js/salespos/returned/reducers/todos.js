import * as types from "../constants/ActionTypes";


const INIT_PARAMS = {
    page: 0,
    size: 10,
    orderNum: "",
    goodsSearchFields: "",
    formCreateDateString: "",
    toCreateDateString: ""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    selectOrderIndex: 0,
    orderItems: [],                  //订单项
    orderId:0,
    order:{},
    returnedOrder: {orderNum:"", returnedTimeString: ""},
    returnedOrderItems: [],                  //订单项
    returnedAmount:0,
    isOverallReturnedPurchase: 'N',
    orderSelectModelStat:false,
    returnedCalcFormStat:false,
    calcData:{orderId:0, isOverallReturnedPurchase: "", remark:"", approveManId:0, returnedOrderItems: [], refundTotalAmount:0, realReturnCashAmount:0, returnSmall:0}
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.RETURNED_PURCHASE_ORDER_LIST:{
            const orderId = action.data.content.length <= 0 ? 0 : action.data.content[0].id;
            const order = action.data.content.length <= 0 ? {orderNum:"", returnedTimeString: ""} : action.data.content[0];
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                }),
                orderId: orderId,
                order: order,
                selectOrderIndex: 0
            });
        }
        case types.RETURNED_PURCHASE_ORDER_ITEM_LIST:{
            return Object.assign({}, state, {
                orderItems: action.data
            });
        }
        //订单列表焦点调整至上一项
        case types.RETURNED_PURCHASE_ORDER_INDEX_UP:{
            const upCartNewIndex = state.selectOrderIndex <= 0 ? state.selectOrderIndex :
                !state.page.content || state.page.content.length == 0 ?  -1 : state.selectOrderIndex-1;
            const orderId = upCartNewIndex < 0 ? 0 : state.page.content[upCartNewIndex].id;
            const order = upCartNewIndex < 0 ? {orderNum:"", returnedTimeString: ""}  : state.page.content[upCartNewIndex];
            return Object.assign({}, state, {
                selectOrderIndex: upCartNewIndex,
                orderId: orderId,
                order: order
            });
        }
        //订单列表焦点调整至下一项
        case types.RETURNED_PURCHASE_ORDER_INDEX_DOWN:{
            const downCartNewIndex = state.selectOrderIndex >= state.page.content.length-1  ? state.page.content.length-1 :
            !state.page.content || state.page.content.length == 0 ?  -1 : state.selectOrderIndex+1;
            const orderId = downCartNewIndex < 0 ? 0 : state.page.content[downCartNewIndex].id;
            const order = downCartNewIndex < 0 ? {orderNum:"", returnedTimeString: ""} : state.page.content[downCartNewIndex];
            return Object.assign({}, state, {
                selectOrderIndex: downCartNewIndex,
                orderId: orderId,
                order: order
            });
        }
        //重置购订单列表焦点
        case types.RETURNED_PURCHASE_ORDER_SEARCH_SUBMIT:

            return Object.assign({}, state, {
                selectOrderIndex: -1,
                orderId: 0,
                order:{orderNum:"", returnedTimeString: ""}
            });
        case types.RETURNED_PURCHASE_SUM_RETURNED_AMOUNT:
            return Object.assign({}, state, {
                returnedAmount: action.data.toFixed(1)
            });
        case types.RETURNED_PURCHASE_ORDER_NUM_SEARCH_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    orderNum: action.data
                })
            });
        case types.RETURNED_PURCHASE_GOODS_SEARCH_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    goodsSearchFields: action.data
                })
            });
        case types.RETURNED_PURCHASE_ORDER_TIME_CHANGE:
            return Object.assign({}, state, {
                params: Object.assign({}, state.params, {
                    formCreateDateString: action.data.formCreateDateString,
                    toCreateDateString: action.data.toCreateDateString,
                })
            });
        case types.RETURNED_PURCHASE_ORDER_SELECT_MODEL_STAT_CHANGE:
            return Object.assign({}, state, {
                orderSelectModelStat: action.isShow
            });
        case types.RETURNED_PURCHASE_ORDER_SELECT_MODEL_SELECTED_OK:{
            const returnedOrderItems = [];
            state.orderItems.map(function(item){
                if(state.isOverallReturnedPurchase==='Y'){
                    item.returnedQuantity = item.allowReturnedQuantity;
                }
                else {
                    item.returnedQuantity = 0;
                }
                returnedOrderItems.push(item);
            });
            const returnedAmount =  returnedOrderItems.length > 0 ?
                returnedOrderItems.map(
                    function(item){
                        return item.goodsUnitPrice * item.returnedQuantity
                    }).reduce(function (previous, current, index, array) {
                return previous + current;
            }) : 0;
            return Object.assign({}, state, {
                orderSelectModelStat: action.isShow,
                returnedOrder:state.order,
                returnedOrderItems:returnedOrderItems,
                returnedAmount:returnedAmount.toFixed(1)
            });
        }
        case types.RETURNED_PURCHASE_RETURNED_CALC_FORM_STAT_CHANGE:
            return Object.assign({}, state, {
                returnedCalcFormStat: action.isShow
            });
        case types.RETURNED_PURCHASE_RETURNED_CALC_DATA_CHANGE:{
            const returnedOrderItems = [];
            state.returnedOrderItems.map(function(item){
                if(item.returnedQuantity > 0){
                    returnedOrderItems.push({orderItemId:item.id, returnedQuantity: item.returnedQuantity})
                }
            });

            return Object.assign({}, state, {
                calcData: {
                    orderId:state.orderId,
                    isOverallReturnedPurchase: state.isOverallReturnedPurchase,
                    remark:action.data.remark,
                    approveManId:action.data.approveManId,
                    returnedOrderItems: returnedOrderItems,
                    refundTotalAmount:state.returnedAmount,
                    realReturnCashAmount:state.returnedAmount,
                    returnSmall:0
                }
            });
        }
        case types.RETURNED_PURCHASE_RETURNED_QUANTITY_CHANGE:{
            const returnedOrderItems = [];
            state.returnedOrderItems.map(function(item){
                if(action.data.id === item.id){ //修改
                    returnedOrderItems.push(item);
                }
                else {
                    returnedOrderItems.push(item);
                }
            });

            const returnedAmount =  returnedOrderItems.length > 0 ? returnedOrderItems.map(function(item){ return item.goodsUnitPrice * item.returnedQuantity }).reduce(function (previous, current, index, array) {
                return previous + current;
            }) : 0;

            return Object.assign({}, state, {
                returnedOrderItems: returnedOrderItems,
                returnedAmount:returnedAmount.toFixed(1)
            });
        }
        case types.RETURNED_PURCHASE_OVERALL_RETURNED_CHANGE:{
            const returnedOrderItems = [];
            const isOverallReturnedPurchase = action.data;
            state.orderItems.map(function(item){
                if(isOverallReturnedPurchase==='Y'){
                    item.returnedQuantity = item.allowReturnedQuantity;
                }
                else {
                    item.returnedQuantity = 0;
                }
                returnedOrderItems.push(item);
            });
            const returnedAmount = returnedOrderItems.length > 0 ? returnedOrderItems.map(function(item){ return item.goodsUnitPrice * item.returnedQuantity }).reduce(function (previous, current, index, array) {
                return previous + current;
            }) : 0;
            return Object.assign({}, state, {
                isOverallReturnedPurchase:isOverallReturnedPurchase,
                returnedOrderItems:returnedOrderItems,
                returnedAmount:returnedAmount.toFixed(1)
            });
        }
        case types.RETURNED_PURCHASE_REAL_RETURN_CASH_AMOUNT_CHANGE:{

            return Object.assign({}, state, {
                calcData: action.data
            });
        }
        case types.RETURNED_PURCHASE_GOODS_SEARCH_PARAMS_INIT:
            return Object.assign({},state,{
                params:INIT_PARAMS
            });
        default:
            return state
    }
}
