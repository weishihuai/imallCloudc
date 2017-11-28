import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    receiveOrderNum: ""    //收货单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    showAcceptance: false,
    showReceiveDetail: false,
    receiveDetailData: {itemList: []},
    receiveRecord:{itemList:[], storageSpaceList: []},
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.PURCHASE_ACCEPTANCE_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.PURCHASE_ACCEPTANCE_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.PURCHASE_ACCEPTANCE_SHOW_ACCEPTANCE:
            return Object.assign({}, state, {showAcceptance: !state.showAcceptance});
        case types.PURCHASE_ACCEPTANCE_INIT_DATA:
            let actionData = action.data;
            let chineseGoodsStorage = actionData.storageSpaceList.filter(storage => storage.storageSpaceType === "CHINESE_HERBAL_MEDICINE");
            let medicalStorage = actionData.storageSpaceList.filter(storage => storage.storageSpaceType === "MEDICAL_APPARATUS_INSTRUMENTS");
            let goodsStorage = actionData.storageSpaceList.filter(storage => storage.storageSpaceType != "MEDICAL_APPARATUS_INSTRUMENTS"&&storage.storageSpaceType != "CHINESE_HERBAL_MEDICINE");
            const newItems = [];
            actionData.itemList.map((item) => {

                switch (item.goodsTypeCode) {

                    case "CHINESE_MEDICINE_PIECES":
                        item = Object.assign({}, item, {storageSpaceList: chineseGoodsStorage});
                        break;
                    case "MEDICAL_INSTRUMENTS":
                        item = Object.assign(item, item, {storageSpaceList: medicalStorage});
                        break;
                    default:
                         item = Object.assign({}, item, {storageSpaceList: goodsStorage});
                        break;
                }

                newItems.push(item);
            });
            actionData=Object.assign({}, actionData, {itemList : newItems});
            return Object.assign({}, state, {receiveRecord: actionData});
        case types.PURCHASE_RECEIVE_DETAIL:
            return Object.assign({}, state, {showReceiveDetail: !state.showReceiveDetail, receiveDetailData: action.data});
        default:
            return state;
    }
}