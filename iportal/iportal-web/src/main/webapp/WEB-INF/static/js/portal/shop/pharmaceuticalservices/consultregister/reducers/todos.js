import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchText: "",
    fromDateString: "",              //创建时间
    toDateString: "",                //
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content:[]},     //列表数据
    data: {},   //详情、编辑咨询登记数据
    goodsDetailTitle: "",
    goodsDetailData: "",     //查看-说明书、用药指导内容
    consultAddForm: false,       //添加窗口显示状态
    consultDetailModal: false,       //详情窗口显示状态
    consultGoodsDetailModal: false,      //查看-说明书、用药指导窗口显示状态
    goods: [],      //咨询的商品
    goodsIds: [],      //咨询的商品ID
    isSelectAll: false,      //是否全选
    selectIds: [],      //新建界面勾选的商品id
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //列表数据
        case types.CONSULT_LIST:
            return Object.assign({},state,{
                page:action.data
            });
        //设置搜索参数
        case types.CONSULT_LIST_SET_PARAMS:
            return Object.assign({},state,{
                params:action.params||initialState
            });
        //新增弹窗
        case types.CONSULT_ADD_FORM_MODAL:
            return Object.assign({},state,{
                consultAddForm:action.isShow,
                data: action.data||{},
                goods: [],
                goodsIds: [],
                isSelectAll: false,
                selectIds: [],
            });
        //详情展示
        case types.CONSULT_DETAIL_MODAL:
            return Object.assign({},state,{
                consultDetailModal:action.isShow,
                data:action.data,
            });
        //商品说明书、用药指导详情展示
        case types.CONSULT_GOODS_DETAIL_MODAL:
            return Object.assign({},state,{
                consultGoodsDetailModal:action.isShow,
                goodsDetailData:action.data,
                goodsDetailTitle:action.title,
            });
        //修改商品
        case types.CONSULT_GOODS_CHANGE:
            return Object.assign({},state,{
                goodsIds: action.goodsIds,
                goods: action.goods,
                isSelectAll: action.isSelectAll
            });
        //选择商品
        case types.CONSULT_SELECT_GOODS:
            return Object.assign({},state,{
                isSelectAll: action.isSelectAll,
                selectIds: action.ids
            });
        default:
            return state
    }
}