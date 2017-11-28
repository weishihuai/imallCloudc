import {showErrorMsg} from '../../../../../common/common';
//检查商品编码
export const asyncValidateForSave = (values, dispatch, props, blurredField) => {
    return checkGoodsCode( values.goodsCode.trim(),values.id, dispatch,props)
};

const checkGoodsCode = (goodsCode,id, dispatch,props) => {
    if(!id){
        id="";
    }
    return new Promise((resolve, reject) => {
        if (goodsCode) {
            if(goodsCode.indexOf(" ") != -1){
                reject({goodsCode: "商品编码请不要输入空格"});
                props.asyncErrorValidMessageFunction("商品编码请不要输入空格");
                return ;
            }
            let url =  iportal+'/goods/isGoodsCodeExist.json';
            const obj = {
                goodsCode:goodsCode,
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
                body: JSON.stringify(obj)
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
                    reject({goodsCode: "该商品编码已经存在"});
                    props.asyncErrorValidMessageFunction("该商品编码已经存在");
                } else {
                    props.asyncErrorValidMessageFunction("");
                    resolve()
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        } else {
            reject({goodsCode: "请输入商品编码"});
            props.asyncErrorValidMessageFunction("请输入商品编码")
        }
    })
}

