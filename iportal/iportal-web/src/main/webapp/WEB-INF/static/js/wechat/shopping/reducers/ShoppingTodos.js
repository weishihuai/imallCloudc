import * as types from "../constants/ActionTypes";

const initState = {
    isEdit:false,
    shoppingCart: {orderTotalAmount:0, cartItems:[], receiverAddr: null},
    selectedIds:[],
    deliveryDate: "",
    deliveryTime: "",
    deliveryDateLayerStat: false,
    deliveryTimeVo:{today:"", tomorrow:"", todayHours:[], tomorrowHours:[]},
    isToday: true,
    addrList: [],
    showSelectZone: false,
    isDeleteModel: false
};

export default function shoppingTodos(state = initState, action) {
     switch (action.type){
         case types.WECHAT_SHOPPING_CART:
             return Object.assign({}, state, {shoppingCart: action.data});
         case types.WECHAT_SHOPPING_EDIT_STATE_CHANGE:
             return Object.assign({}, state, {isEdit: action.isEdit});
         case types.WECHAT_SHOPPING_EDIT_SELECT_CHANGE:{
             const selectedIds = state.selectedIds;
             selectedIds.push(action.skuId);
             return Object.assign({}, state, {selectedIds: selectedIds});
         }
         case types.WECHAT_SHOPPING_EDIT_SELECTALL_CHANGE:
             var selectedIds = [];
             if(action.selectAll){
                 selectedIds = state.shoppingCart.cartItems.map(cartItem => { return cartItem.skuId });
             }
             return Object.assign({}, state, {selectedIds: selectedIds});
         case types.WECHAT_SHOPPING_FORM_DELIVERY_DATE:{
             const isToday = action.data.todayHours.length > 0;
             let deliveryTime, deliveryDate;
             if (isToday){
                 deliveryDate = action.data.today;
                 if (action.data.inBusinessTime){
                     deliveryTime = '尽快送达';
                 }else {
                     deliveryTime = action.data.todayHours[0];
                 }
             }else {
                 deliveryTime = action.data.tomorrowHours[0];
                 deliveryDate = action.data.tomorrow;
             }
             return Object.assign({}, state, {deliveryTimeVo: action.data, isToday, deliveryDate, deliveryTime});
         }
         case types.WECHAT_SHOPPING_FORM_DELIVERY_DATE_LAYER_STAT_CHANGE:
             return Object.assign({}, state, {deliveryDateLayerStat: action.data});
         case types.WECHAT_SHOPPING_FORM_DELIVERY_DATE_CHANGE:
             return Object.assign({}, state, {isToday:action.data.isToday, deliveryDate: action.data.deliveryDate, deliveryTime:'尽快送达'});
         case types.WECHAT_SHOPPING_FORM_DELIVERY_TIME_CHANGE:
             return Object.assign({}, state, {deliveryTime: action.data});
         case types.WECHAT_SHOPPING_ADDRESS_LIST:
             return Object.assign({}, state, {addrList: action.data});
         case types.WECHAT_SHOPPING_SHOW_SELECT_ZONE:
             return Object.assign({}, state, {showSelectZone: action.showSelectZone});
         case types.WE_CHAT_SHOPPING_CART_DELETE_CONFIRM:
             return Object.assign({}, state, {
                 isDeleteModel: action.isDeleteModel
             });
         default:
             return state;
     }
}