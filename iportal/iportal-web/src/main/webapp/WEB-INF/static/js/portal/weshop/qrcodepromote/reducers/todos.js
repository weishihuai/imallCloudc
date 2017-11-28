import * as types from "../constants/ActionTypes";

const initialState = {
  qrCodeTicket: ''
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.QR_CODE_TICKET:
            return Object.assign({}, state, {qrCodeTicket: action.data});
        default:
            return state
    }
}
