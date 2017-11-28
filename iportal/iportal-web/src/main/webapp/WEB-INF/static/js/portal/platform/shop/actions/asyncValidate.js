import {showErrorMsg} from '../../../../common/common';
//检查同一个门店下用户名名称是否有重复
export const asyncValidateForSaveOrUpdate = (values, dispatch, props, blurredField) => {
    return checkUserNameIsExist( values.userName.trim(),values.id, dispatch,props)
};

const checkUserNameIsExist = (userName,id, dispatch,props) => {
    if(!id){
        id = "";
    }
    return new Promise((resolve, reject) => {
        props.asyncErrorValidMessageFunction("")
        if (userName) {
            if(userName.indexOf(" ") != -1||userName.indexOf(" ") != -1){
                reject({userName: "请不要输入空格"});
                props.asyncErrorValidMessageFunction("请不要输入空格")
            }

            let url = iportal+'/sysUser/checkUserNameIsExist.json';
            const data = {
                userName:userName,
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
                    reject({userName: "该用户名名称已经存在"});
                    props.asyncErrorValidMessageFunction("该用户名名称已经存在")
                } else {
                    resolve()
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({userName: "请输入用户名名称"});
            props.asyncErrorValidMessageFunction("请输入用户名名称")
        }
    })
};



