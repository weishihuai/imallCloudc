import {showErrorMsg} from '../../../../common/common';
//检查名称是否有重复
export const asyncValidateForSaveOrUpdate = (values, dispatch, props, blurredField) => {
    return checkNameIsExist( values.supplierNm.trim(),values.id, dispatch,props)
};

const checkNameIsExist = (supplierNm,id, dispatch,props) => {
    if(!id){
        id = "";
    }
    return new Promise((resolve, reject) => {
        props.asyncErrorValidMessageFunction("");
        if (supplierNm) {
            if(supplierNm.indexOf(" ") != -1){
                reject({supplierNm: "请不要输入空格"});
                props.asyncErrorValidMessageFunction("供应商名称请不要输入空格");
                return ;
            }

            let url = iportal+'/supplier/checkNameIsExist.json';
            const data = {
                supplierNm:supplierNm,
                id:id
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
                if (json==false || json==='false') {
                    reject({supplierNm: "该供应商名称已经存在"});
                    props.asyncErrorValidMessageFunction("【"+supplierNm+"】"+"已存在")
                } else {
                    resolve();
                    props.asyncErrorValidMessageFunction("");
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({supplierNm: "请输入供应商名称"});
            props.asyncErrorValidMessageFunction("请输入供应商名称")
        }
    })
};



