import {showErrorMsg} from '../../../../common/common';
//检查同一个门店下用户名名称是否有重复
export const asyncValidateForSaveOrUpdate = (values, dispatch, props, blurredField) => {
    return checkRoleCodeIsExist( values.roleCode,values.id, dispatch,props)
};

const checkRoleCodeIsExist = (roleCode,id, dispatch,props) => {
    if(!id){
        id = "";
    }
    return new Promise((resolve, reject) => {
        props.asyncErrorValidMessageFunction("")
        if (roleCode) {
            if(roleCode.indexOf(" ") != -1||roleCode.indexOf(" ") != -1){
                reject({roleCode: "请不要输入空格"});
                props.asyncErrorValidMessageFunction("请不要输入空格")
            }

            let url = iportal+'/sysRole/checkRoleCodeIsExist.json';
            const data = {
                roleCode:roleCode,
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
                    reject({roleCode: "该编码已经存在"});
                    props.asyncErrorValidMessageFunction("该编码已经存在")
                } else {
                    resolve()
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({roleCode: "请输入编码"});
            props.asyncErrorValidMessageFunction("请输入编码")
        }
    })
};



