import { combineReducers } from 'redux'
import {reducer as formReducer} from "redux-form";
import routerTodos from "./routerTodos";
import receiveAddrTodos from "../../index/reducers/ReceiveAddrTodos";
import weChatGoodsListTodos from "../../goodslist/reducers/weChatGoodsListTodos";
import weChatUserTodos from "../../wechatuser/reducers/weChatUserTodos";
import weShopTodos from "../../weshop/reducers/weShopTodos";
import indexTodos from "../../index/reducers/IndexTodos";
import shoppingTodos from "../../shopping/reducers/ShoppingTodos";
import wechatOrderTodos from "../../order/reducers/wechatOrderTodos";
import wechatUserReceiveAddrTodos from "../../receiveaddr/reducers/ReceiveAddrTodos";

const rootReducer = combineReducers({
  form: formReducer,
  routerTodos,
  receiveAddrTodos,
  weChatGoodsListTodos,
  weChatUserTodos,
  weShopTodos,
  indexTodos,
  shoppingTodos,
  wechatOrderTodos,
  wechatUserReceiveAddrTodos
});

export default rootReducer
