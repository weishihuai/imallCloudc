import * as types from "../constants/ActionTypes";

const initialState = {
   initData: {},
    province: [],
    city: [],
    area: [],
    detailData: {},
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.WE_SHOP_PROVINCE_DATA:
            return Object.assign({}, state, {province: action.data, city:[], area: []});
        case types.WE_SHOP_CITY_DATA:
            return Object.assign({}, state, {city: action.data, area: []});
        case types.WE_SHOP_AREA_DATA:
            return Object.assign({}, state, {area: action.data});
        case types.WE_SHOP_DETAIL_DATA:
            let province = action.data.province;
            let city = action.data.city;
            let area = action.data.area;
            return Object.assign({}, state, {detailData: action.data, province: province, city: city , area: area});
        default:
            return state
    }
}
