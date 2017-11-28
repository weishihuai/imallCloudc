import * as types from "../constants/ActionTypes";

export function portalOperationalAuth(permissionCodes){

    return function (dispatch) {
        const authsMap = new Map();
        for (const code of permissionCodes) {
            authsMap.set(code, false);
        }
        const empty = {
            type: types.OPERATIONAL_AUTH_PERMISSION,
            data: authsMap
        };
        dispatch(empty);
        return fetch(iportal+'/checkAuthPermissions.json',  {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(permissionCodes)
        }).then(response =>
                response.json().then(json => ({
                        status: response.status,
                        json
                    })
                ))
            .then(
                ({ status, json }) => {
                    if (status == 200) {
                        dispatch({
                            type: types.OPERATIONAL_AUTH_PERMISSION,
                            data: json
                        })
                    } else {
                        dispatch(empty)
                    }
                },
                err => {
                    dispatch(empty)
                }
            );
    }
}

