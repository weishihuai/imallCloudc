import {showErrorMsg} from '../../../../../common/common';

//检查同一个门店下货位名称是否有重复
export const asyncValidateForSaveOrUpdate = (values, dispatch, props, blurredField) => {
    return checkStorageSpaceNmIsExist( values.storageSpaceNm.trim(),values.id, dispatch,props)
};

const checkStorageSpaceNmIsExist = (storageSpaceNm,id, dispatch,props) => {
    if(!id){
        id = "";
    }
    return new Promise((resolve, reject) => {
        if (storageSpaceNm) {
            if(storageSpaceNm.indexOf(" ") !== -1){
                reject({storageSpaceNm: "请不要输入空格"});
                props.asyncErrorValidMessageFunction("请不要输入空格");
                return ;
            }

            let url = iportal+'/storageSpace/checkStorageSpaceNmIsExist.json';
            const data = {
                id:id,
                storageSpaceNm:storageSpaceNm
            };
            fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(function (response) {
                    if (response.status >= 200 && response.status < 300) {
                        return response
                    } else {
                        var error = new Error(response.statusText);
                        error.response = response;
                        throw error
                    }
                })
                .then(function (response) {
                    return response.json();
                }).then(function (json) {
                if (json === false || json === 'false') {
                    reject({storageSpaceNm: "该货位名称已经存在"});
                    props.asyncErrorValidMessageFunction("该货位名称已经存在")
                } else {
                    resolve();
                    props.asyncErrorValidMessageFunction("");
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({storageSpaceNm: "请输入货位名称"});
            props.asyncErrorValidMessageFunction("请输入货位名称")
        }
    })
};



