import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {REGEXP_SIGNLESS_INT} from "../../../../../common/common-constant";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

/**
 * 设置搜索参数
 * @param params
 * @returns {Function}
 */
export function orderSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.ORDER_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 列表
 * @param params
 * @param page
 * @param size
 * @returns {Function}
 */
export function orderList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/weshopOrder/query.json?keyword=' + params.keyword + '&receiverName=' + params.receiverName + '&contactTel='
            + params.contactTel + '&orderNum=' + params.orderNum + '&orderStateCode=' + params.orderStateCode + '&startCreateDateString='
            + params.startCreateDateString + '&endCreateDateString=' + params.endCreateDateString + '&bookDeliveryTimeStartString=' + params.bookDeliveryTimeStartString
            + '&bookDeliveryTimeEndString=' + params.bookDeliveryTimeEndString + '&paymentWayCode=' + params.paymentWayCode + '&page=' + page + '&size=' + size , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.ORDER_LIST,
                orderStateCode: params.orderStateCode,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 搜索组件回调
 * @param params
 * @returns {Function}
 */
export function orderSearch(params) {
    return function (dispatch) {
        dispatch(orderSetSearchParams(params));
        dispatch(orderList(params, 0, params.size));
    }
}

/**
 * 订单统计信息
 * @returns {Function}
 */
export function getOrderStatistics() {
    return function (dispatch) {
        return fetch(iportal + '/weshopOrder/getOrderStatistics.json' , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.ORDER_STATISTICS_MODULE,
                orderStatistics: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 订单详情
 * @param isShowDetail
 * @param id
 */
export function showDetail(isShowDetail, id=null) {
    return function (dispatch) {
        if(isShowDetail){
            return fetch(iportal + '/weshopOrder/findById.json?id=' + id , {
                mode: 'cors',
                credentials: 'include',
                method: 'get'
            }).then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                dispatch({
                    type: types.ORDER_DETAIL_MODULE,
                    isShowDetail: isShowDetail,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }else{
            dispatch({
                type: types.ORDER_DETAIL_MODULE,
                isShowDetail: isShowDetail
            });
        }
    }
}

/**
 * 订单关闭窗口显示与关闭
 * @param isShowClose
 * @param id
 * @returns {Function}
 */
export function showCloseForm(isShowClose, id='') {
    return function (dispatch) {
        dispatch({
            type: types.ORDER_CLOSE_MODULE,
            isShowClose: isShowClose,
            data: {id: id, orderStateCode: 'CLOSE'}
        });
    }
}

/**
 * 关闭订单
 * @param data
 * @param callBack
 */
export function closeOrder(data, callBack=()=>{}) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/weshopOrder/updateOrderState.json' , {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(data))
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.ORDER_CLOSE_MODULE,
                isShowClose: false,
            });
            callBack();
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 确认
 * @param data
 * @param callBack
 * @returns {Function}
 */
export function confirm(data, callBack=()=>{}) {
    return function (dispatch) {
        dispatch(closeOrder(data, callBack));
        dispatch(showConfirm(false));
    }
}

/**
 * 确认框显示与隐藏
 * @param isShowConfirm
 * @param confirmType
 * @param id
 */
export function showConfirm(isShowConfirm, confirmType='', id=null){
    return function (dispatch) {
        dispatch({
            type: types.ORDER_CONFIRM_MODULE,
            isShowConfirm: isShowConfirm,
            confirmType: confirmType,
            id: id
        });
    }
}

/**
 * 备注窗口显示与隐藏
 * @param isShowRemark
 * @param order
 */
export function showRemark(isShowRemark, order={}) {
    return function (dispatch) {
        dispatch({
            type: types.ORDER_REMARK_MODULE,
            isShowRemark: isShowRemark,
            order: order
        });
    }
}

/**
 * 订单备注
 * @param data
 * @param callBack
 */
export function updateOrderRemark(data, callBack=()=>{}) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/weshopOrder/updateOrderRemark.json' , {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(data))
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch(showRemark(false));
            callBack();
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 发货窗口显示与隐藏
 * @param isShowSend
 * @param id
 * @returns {Function}
 */
export function showOrderSend(isShowSend, id='') {
    return function (dispatch) {
        if(isShowSend){
            return fetch(iportal + '/weshopOrder/findOrderItemSendDetail.json?orderId=' + id , {
                mode: 'cors',
                credentials: 'include',
                method: 'get'
            }).then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                const newItemData = [];
                json.itemList.map((item, index)=>{
                    item =  Object.assign({}, item, {
                        index: index + 1
                    });
                    newItemData.push(item);
                });

                json = Object.assign({}, json, {
                    itemList: newItemData
                });

                dispatch({
                    type: types.ORDER_SEND_MODULE,
                    isShowSend: isShowSend,
                    sendData: json,
                    sendAddNum: 0
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }else{
            dispatch({
                type: types.ORDER_SEND_MODULE,
                isShowSend: isShowSend,
                sendData: {},
                sendAddNum: 0
            });
        }
    }
}

/**
 * 订单发货
 * @param data
 * @returns {Function}
 */
export function updateOrderToSend(data) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/weshopOrder/updateOrderToSend.json' , {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(data))
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch(showOrderSend(false));
            dispatch(showDetail(true, data.id));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 添加批次
 * @param data
 * @param sendData
 */
export function addBatch(data, sendData) {
    return function (dispatch) {
        const newItemList = [];
        sendData.itemList.map((item)=>{
            item = Object.assign({}, item, {
                index: newItemList.length + 1
            });
            newItemList.push(item);

            if(item.index===data.index){
                let newData = Object.assign({}, data, {
                    add: true,
                    index: newItemList.length + 1,
                    sendQuantity: '',
                    batchId: ''
                });
                newItemList.push(newData);
            }
        });

        sendData = Object.assign({}, sendData, {
            itemList: newItemList
        });

        dispatch({
            type: types.ORDER_SEND_MODULE,
            isShowSend: true,
            sendData: sendData
        });
    }
}

/**
 * 批次删除
 * @param data
 * @param sendData
 */
export function removeBatch(data, sendData) {
    return function (dispatch) {
        const itemList = sendData.itemList.filter((item)=> (data.index!==item.index));
        sendData = Object.assign({}, sendData, {
            itemList: itemList
        });

        dispatch({
            type: types.ORDER_SEND_MODULE,
            isShowSend: true,
            sendData: sendData
        });
    }
}

/**
 * 发货数量修改
 * @param sendQuantity
 * @param data
 * @param sendData
 */
export function quantityChange(sendQuantity, data, sendData) {
    return function (dispatch) {
        const newItemList = [];
        sendData.itemList.map((item, index)=>{
            if(data.index===item.index){
                item = Object.assign({}, item, {
                    index: index + 1,
                    sendQuantity: sendQuantity
                });
            }else{
                item = Object.assign({}, item, {
                    index: index + 1
                });
            }

            newItemList.push(item);
        });

        sendData = Object.assign({}, sendData, {
            itemList: newItemList
        });

        dispatch({
            type: types.ORDER_SEND_MODULE,
            isShowSend: true,
            sendData: sendData
        });
    }
}

/**
 * 发货批次修改
 * @param batchId
 * @param data
 * @param sendData
 */
export function batchChange(batchId, data, sendData) {
    return function (dispatch) {
        const newItemList = [];
        let batchData = [];
        sendData.itemList.map((item, index)=>{
            if(data.index===item.index){
                batchData = item.batchList.filter(file=>file.id===parseInt(batchId))[0];
                item = Object.assign({}, item, {
                    index: index + 1,
                    batchId: batchId,
                    batch: batchData ? batchData.batch : '',
                    batchCurrentStock: batchData ? batchData.currentStock : ''
                });
            }else{
                item = Object.assign({}, item, {
                    index: index + 1
                });
            }

            newItemList.push(item);
        });

        sendData = Object.assign({}, sendData, {
            itemList: newItemList
        });

        dispatch({
            type: types.ORDER_SEND_MODULE,
            isShowSend: true,
            sendData: sendData
        });
    }
}

/**
 * 订单发货校验
 * @param values
 * @param props
 * @returns {{}}
 */
export const sendOrderValidate = (values, props) => {
    const errors = {};
    const itemBatchMap = new Map();
    errors.responset = {status: 200};

    let items = values.itemList;
    errors.itemList = [];
    for (const index in items){
        const item = items[index];
        const batchId = item.batchId;
        if(isEmpty(batchId)){
            props.errorValidMessageFunction("请选择" + item.commonNm + "发货批号");
            errors.itemList[index] = {batchId: "请选择" + item.commonNm + "发货批号"};
            return errors;
        }

        if(itemBatchMap.has(item.id + '_' + item.batchId)){
            props.errorValidMessageFunction(item.commonNm + "所有的发货批号必须不同");
            errors.itemList[index] = {batchId: item.commonNm + "所有的发货批号必须不同"};
            return errors;
        }else{
            itemBatchMap.set(item.id + '_' + item.batchId, {id: item.id, batchId: item.batchId});
        }

        const sendQuantity = item.sendQuantity;
        if(isEmpty(sendQuantity)){
            props.errorValidMessageFunction("请填写" + item.commonNm + "【" + item.batch + "】" + "发货数量");
            errors.itemList[index] = {sendQuantity: "请填写" + item.commonNm + "【" + item.batch + "】" + "发货数量"};
            return errors;
        }

        if(!REGEXP_SIGNLESS_INT.test(sendQuantity)){
            props.errorValidMessageFunction(item.commonNm + "【" + item.batch + "】" + "发货数量格式错误");
            errors.itemList[index] = {sendQuantity: item.commonNm + "【" + item.batch + "】" + "发货数量格式错误"};
            return errors;
        }

        const quantity = item.quantity < item.batchCurrentStock ? item.quantity : item.batchCurrentStock;
        if(sendQuantity>quantity){
            props.errorValidMessageFunction(item.commonNm + "【" + item.batch + "】" + "发货数量不能大于" + quantity);
            errors.itemList[index] = {sendQuantity: item.commonNm + "【" + item.batch + "】" + "发货数量不能大于" + quantity};
            return errors;
        }
    }

    props.errorValidMessageFunction("");
    return errors
};

/**
 * 打印小票
 * @param orderId
 * @param callBack
 */
export function printOrderSmallTicket(orderId) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/weshopOrder/printOrderSmallTicket.json?orderId=' + orderId , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch(showConfirm(false));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

function isEmpty(obj){
    return obj === undefined || obj === null || obj.toString().trim() === '';
}