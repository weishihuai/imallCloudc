import {showErrorMsg} from '../../../../common/common';
//检查商品编码
export const asyncValidateForSave = (values, dispatch, props, blurredField) => {
    return checkGoodsCode( values.categoryName.trim(),values.id, dispatch,props)
};

const checkGoodsCode = (categoryName,id, dispatch,props) => {
    if(!id){
        id="";
    }
    return new Promise((resolve, reject) => {
        if (categoryName) {
            if(categoryName.indexOf(" ") != -1){
                reject({categoryName: "请不要输入空格"});
                props.asyncErrorValidMessageFunction("请不要输入空格")
                return;
            }
            fetch(iportal+'/goodsCategory/isGoodsCategoryExist.json?categoryName='+categoryName+'&id='+id, {
                mode: 'cors',
                credentials: 'include',
                method: 'get'
            })
                .then(function (response) {
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
                    reject({categoryName: "该分类名称已经存在"});
                    props.asyncErrorValidMessageFunction("该分类名称已经存在")
                } else {
                    props.asyncErrorValidMessageFunction("")
                    resolve()
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({categoryName: "请输入分类名称"});
            props.asyncErrorValidMessageFunction("请输入分类名称")
        }
    })
}

