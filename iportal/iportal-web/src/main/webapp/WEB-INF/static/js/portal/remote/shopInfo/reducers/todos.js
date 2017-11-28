import * as types from "../constants/ActionTypes";



const initialState = {
    detailObject: {},                                  //弹窗 详情 与 编辑 对象
    businessRangeAllData:[],                           //商品范围 数据
    businessRangeDataSelect:[],                        //商品范围 选中 数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {

        case types.SHOP_FIND_BUSINESS_RANGE:
            return Object.assign({}, state, {
                businessRangeAllData: action.businessRangeAllData?action.businessRangeAllData:[]
            });
        case types.SHOP_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
                businessRangeDataSelect:action.detailObject.businessRange?action.detailObject.businessRange.split(","):[],
            });

        default:
            return state
    }
}
