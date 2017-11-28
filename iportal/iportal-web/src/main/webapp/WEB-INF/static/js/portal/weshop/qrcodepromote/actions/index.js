import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../common/common";

export function getQrCodeTicket() {
    return function (dispatch) {
        return fetch(iportal + '/backendwechat/qrCodeTicket.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({type: types.QR_CODE_TICKET, data: json.msg});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}
