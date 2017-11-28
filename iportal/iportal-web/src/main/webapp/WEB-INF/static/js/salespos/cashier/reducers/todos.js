import * as types from "../constants/ActionTypes";

const initialState = {
    addMemberState: false,          //新增会员窗体
    memberData:{},                  //会员信息
    goodsSearchFields:"",           //查询关键词
    searchGoodsState: false,        //查找商品弹窗展示
    searchBatchState: false,        //选择批次展示
    searchGoodsList: {},            //选择商品列表
    searchGoodsFocusItemIndex: -1,    //选择商品列表获得焦点index，窗口打开默认搜索输入框焦点。
    searchBatchList: {},            //选择批次列表
    selectBatchQuantity:1,          //选择的批次商品数量
    searchBatchFocusItemIndex: 0,   //选择批次列表获得焦点index
    selectSkuId:"",                 //选择的商品skuId
    selectBatchId:"",               //选择的批次ID
    selectBatch: "",                //选择的批次批号
    selectGoodsNm: "",              //选择的商品名称
    selectGoodsStock: null,         //选择的商品批次库存
    selectGoodsUnitPrice: "",       //选择的商品单价
    shoppingCart: {},               //购物车
    uniqueKey:"",                   //购物车uniqueKey
    cartItems: [],                  //购物车项
    selectCartItemIndex: -1,        //选择的购物车项index
    selectCartItem: {batch:""},             //选择的购物车项
    updateItemQuantityStat: false,  //更改购物车项数量弹窗展示
    updateNewQuantity: 0,           //修改购物车项数量
    settlementStat: false,          //结算弹窗展示
    shiftRecordStat: false,         //交班弹窗展示
    cashPayAmount: 0,               //支付现金金额
    healthInsurancePayAmount: 0,    //医保支付金额
    returnCash: 0,                  //找零金额
    paymentWayCode: "",             //支付方式代码
    isCashPay: true,                //是否现金支付
    shiftData: {},                  //交班
    loginCashierData: {},           //收银员
    confirmModelState:false,        //删除整单确认提示框状态
    isDeleteAll:"",                 //是否删除整单
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //新增会员窗口展示状态修改
        case types.POS_CASHIER_MEMBER_ADD_MODEL:
            return Object.assign({}, state, {
                addMemberState: action.isMemberAddShow,
                memberData: action.data || {},
            });
        case types.POS_CASHIER_MEMBER_MODAL:
            return Object.assign({}, state, {
                memberData: action.data
            });
        case types.POS_CASHIER_MEMBER_DATA:
            return Object.assign({}, state, {
                memberData: action.data
            });
        //重置会员
        case types.POS_CASHIER_RESET_MEMBER:
            return Object.assign({}, state, {
                memberData: {}
            });
        //商品搜索词修改
        case types.POS_CASHIER_SEARCH_FIELDS_CHANGE:
            return Object.assign({}, state, {
                goodsSearchFields: action.searchFields
            });
        //选择商品窗口展示状态修改
        case types.POS_CASHIER_SEARCH_GOODS_MODAL:
            return Object.assign({}, state, {
                searchGoodsState: action.isShow,
                searchGoodsList: action.data,
                goodsSearchFields: action.searchFields,
                searchGoodsFocusItemIndex: 0
            });
        //选择批次窗口展示状态修改
        case types.POS_CASHIER_SEARCH_BATCH_MODAL:
            const goodsIndex = state.searchGoodsFocusItemIndex;
            return Object.assign({}, state, {
                searchBatchState: action.isShow,
                searchBatchList: state.searchGoodsList[goodsIndex].goodsBatchList
            });
        //选择下单商品数量修改
        case types.POS_CASHIER_SELECT_BATCH_QUANTITY_CHANGE:
            const selectBatchQuantity = isNaN(action.data.trim()) ? state.selectBatchQuantity : action.data.trim();
            return Object.assign({}, state, {
                selectBatchQuantity: selectBatchQuantity == "" ? "" : parseInt(selectBatchQuantity)
            });
        //选择商品项焦点调整至上一项
        case types.POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_UP:
            return Object.assign({}, state, {
                searchGoodsFocusItemIndex: state.searchGoodsFocusItemIndex <= 0 ? state.searchGoodsFocusItemIndex : state.searchGoodsFocusItemIndex-1
            });
        //选择商品项焦点调整至下一项
        case types.POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_DOWN:
            return Object.assign({}, state, {
                searchGoodsFocusItemIndex: state.searchGoodsFocusItemIndex == state.searchGoodsList.length-1  ? state.searchGoodsFocusItemIndex : state.searchGoodsFocusItemIndex+1
            });
        //重置商品项焦点
        case types.POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_RESET:
            return Object.assign({}, state, {
                searchGoodsFocusItemIndex: -1
            });
        //选择当前获取焦点的批次
        case types.POS_CASHIER_SELECT_BATCH_STATE:
            const batchIndex = state.searchBatchFocusItemIndex;
            const selectBatchObj = state.searchBatchList[batchIndex];
            const selectGoodsIndex = state.searchGoodsFocusItemIndex;
            const selectGoodsObj = state.searchGoodsList[selectGoodsIndex];
            return Object.assign({}, state, {
                searchBatchState: false,
                searchGoodsState: false,
                selectSkuId: selectGoodsObj.skuId,
                selectBatchId: selectBatchObj.id,
                selectBatch: selectBatchObj.batch,
                selectGoodsStock: selectBatchObj.stock,
                selectBatchQuantity: 1,
                selectGoodsNm: selectGoodsObj.commonNm,
                searchGoodsFocusItemIndex: -1,
                searchBatchFocusItemIndex: 0,
                selectGoodsUnitPrice: state.memberData && state.memberData.id > 0 ? selectGoodsObj.memberPrice : selectGoodsObj.retailPrice
            });
        //选择批次项焦点调整至上一项
        case types.POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_UP:
            return Object.assign({}, state, {
                searchBatchFocusItemIndex: state.searchBatchFocusItemIndex <= 0 ? state.searchBatchFocusItemIndex : state.searchBatchFocusItemIndex-1
            });
        //选择批次项焦点调整至下一项
        case types.POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_DOWN:
            return Object.assign({}, state, {
                searchBatchFocusItemIndex: state.searchBatchFocusItemIndex == state.searchBatchList.length-1  ? state.searchBatchFocusItemIndex : state.searchBatchFocusItemIndex+1
            });
        //修改购物车数据
        case types.POS_CASHIER_SHOPPING_CART_DATA:
            return Object.assign({}, state, {
                shoppingCart: action.data,
                uniqueKey: action.data.uniqueKey,
                cartItems: action.data.cartItems,
                goodsSearchFields: "",
                selectBatchId:"",
                selectSkuId:"",
                selectGoodsUnitPrice: "",
                selectCartItemIndex: -1,
                selectBatchQuantity:1,
                selectCartItem: {batch:""},
                selectBatch:"",
                selectGoodsNm:"",
                updateItemQuantityStat:false,
                updateNewQuantity:0,
                settlementStat: false,
                cashPayAmount: 0,
                healthInsurancePayAmount: 0,
                returnCash: 0,
                paymentWayCode: "",
                isCashPay: true,
            });
        //购物车项修改数量窗口展示状态修改
        case types.POS_CASHIER_UPDATE_QUANTITY_MODAL:
            return Object.assign({}, state, {
                updateItemQuantityStat: action.isShow,
                updateNewQuantity: action.isShow ? state.selectCartItem.quantity : 0
            });
        //购物车项修改数量值改变
        case types.POS_CASHIER_UPDATE_NEW_QUANTITY_CHANGE:
            const updateNewQuantity = isNaN(action.data.trim()) ? state.updateNewQuantity : action.data.trim();
            return Object.assign({}, state, {
                updateNewQuantity: updateNewQuantity == "" ? "" : parseInt(updateNewQuantity)
            });
        //购物车项焦点调整至上一项
        case types.POS_CASHIER_CART_ITEM_INDEX_UP:
            const upCartNewIndex = state.selectCartItemIndex <= 0 ? state.selectCartItemIndex :
                !state.cartItems || state.cartItems.length == 0 ?  -1 : state.selectCartItemIndex-1;
            return Object.assign({}, state, {
                selectCartItemIndex: upCartNewIndex,
                selectCartItem: state.cartItems[upCartNewIndex]
            });
        //购物车项焦点调整至下一项
        case types.POS_CASHIER_CART_ITEM_INDEX_DOWN:
            const downCartNewIndex = state.selectCartItemIndex == state.cartItems.length-1  ? state.selectCartItemIndex :
                !state.cartItems || state.cartItems.length == 0 ?  -1 : state.selectCartItemIndex+1;
            return Object.assign({}, state, {
                selectCartItemIndex: downCartNewIndex,
                selectCartItem: state.cartItems[downCartNewIndex]
            });
        //重置购物车项焦点
        case types.POS_CASHIER_CART_ITEM_INDEX_RESET:
            return Object.assign({}, state, {
                selectCartItemIndex: -1,
                selectCartItem: {batch:""}
            });
        //结算窗口展示状态修改
        case types.POS_CASHIER_SETTLEMENT_MODAL:
            return Object.assign({}, state, {
                settlementStat: action.isShow
            });
        //现金支付金额修改
        case types.POS_CASHIER_CASH_PAY_CHANGE:
            return Object.assign({}, state, {
                cashPayAmount: action.cashPayAmount,
                returnCash: action.returnCash
            });
        //医保支付金额修改
        case types.POS_CASHIER_HEALTH_INSURANCE_PAY_CHANGE:
            return Object.assign({}, state, {
                healthInsurancePayAmount: action.healthInsurancePayAmount,
                returnCash: action.returnCash
            });
        //支付方式修改
        case types.POS_CASHIER_PAYMENT_WAY_CHANGE:
            return Object.assign({}, state, {
                isCashPay: action.isCashPay,
                paymentWayCode: action.paymentWayCode,
                cashPayAmount: 0,
                returnCash: 0
            });
        case types.POS_CASHIER_SHIFT_MODEL:
            return Object.assign({}, state, {
                shiftRecordStat: action.isShiftShow,
                shiftData: action.data || {},
            });

        case types.POS_CASHIER_SHIFT_DATA_INIT:
            return Object.assign({}, state, {
                shiftData: Object.assign({},action.data,{
                    succeedReadyAmount:"",
                    keepReadyAmount:"",
                    handoverCashAmount:"",
                })
            });
        //当前登录收银员
        case types.POS_CASHIER_LOGIN_MAN_DATA:
            return Object.assign({}, state, {
                loginCashierData: action.data
            });
        //收银金额，结算完未更新todo购物车值时调用
        case types.POS_CASHIER_LOGIN_MAN_DATA_CHANGE:
            state.loginCashierData.addUpAmount = parseFloat(state.loginCashierData.addUpAmount) + parseFloat(state.shoppingCart.orderTotalAmount);
            state.loginCashierData.cashAmount = parseFloat(state.loginCashierData.cashAmount) + parseFloat(state.cashPayAmount) - parseFloat(state.returnCash);
            return Object.assign({}, state, {
                loginCashierData: state.loginCashierData
            });
        //当前选择的商品批次库存
        case types.POS_CASHIER_SELECTED_GOODS_BATCH_STOCK:
            return Object.assign({},state,{
                selectGoodsStock:null
            });
        //删除整单确认提示框
        case types.POS_CASHIER_CART_DELETE_VIEW_STATE:
            return Object.assign({},state,{
                confirmModelState:action.data,
                isDeleteAll:action.isDeleteAll
            });
        default:
            return state
    }
}
