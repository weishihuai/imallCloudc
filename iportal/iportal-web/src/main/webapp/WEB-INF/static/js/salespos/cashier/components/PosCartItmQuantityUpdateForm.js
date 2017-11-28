/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component, PropTypes} from "react";
import {niftyNoty} from "../../../common/common";
/*更新商品数量*/
class PosCartItmQuantityUpdateForm extends Component {


    constructor(props) {
        super(props);
        this.updateQuantityHandle = this.updateQuantityHandle.bind(this)
    }

    componentDidMount() {
        this.refs.updateQuantity.select();
        window.addEventListener('keydown', this.updateQuantityHandle);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.updateQuantityHandle);
    }

    updateQuantityHandle(event) {
        const {updateQuantityModal} = this.props.actions;

        switch(event.keyCode){
            //enter
            case 13:
                event.preventDefault();
                this.toUpdateQuantity();
                break;
            //esc
            case 27:
                event.preventDefault();
                let oldjs = document.getElementById("notyLayer");
                if(oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                    break;
                }
                updateQuantityModal(false);
                break;
        }
    }



    toUpdateQuantity(){
        const {uniqueKey, selectCartItem, updateNewQuantity} = this.context.store.getState().todos;
        if(updateNewQuantity == 0){
            niftyNoty("数量不能为0");
            return;
        }
        if(updateNewQuantity >selectCartItem.currentQuantity){
            niftyNoty("数量不能大于当前批次库存,剩"+selectCartItem.currentQuantity+"件");
            return;
        }
        return this.props.actions.updateQuantity(uniqueKey, selectCartItem.skuId, selectCartItem.batch, updateNewQuantity);
    }

    render() {
        const {updateQuantityModal, updateNewQuantityChange} = this.props.actions;
        const {selectCartItem, updateNewQuantity} = this.context.store.getState().todos;

        return (
            <div className="layer">
                <div className="layer-box layer-start w430">
                    <div className="layer-header">
                        <span>更新商品-{selectCartItem.goodsCode}</span>
                        <a href="javascript:void(0);" className="close" onClick={() => updateQuantityModal(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="item">
                            <div className="mlt">数&nbsp;&nbsp;量</div>
                            <div className="mrt">
                                <input type="text" name="quantity" id="quantity" maxLength="7" value={updateNewQuantity} ref="updateQuantity" onChange={(e) => updateNewQuantityChange(e.target.value)}/>
                            </div>
                        </div>
                        <div className="item">
                            <div className="mlt">单&nbsp;&nbsp;价</div>
                            <div className="mrt">
                                <input type="text" readOnly="readOnly" value={selectCartItem.unitPrice}/>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <a href="javascript:void(0);" className="confirm" onClick={() => this.toUpdateQuantity()}>确定</a>
                        <a href="javascript:void(0);" className="cancel" onClick={() => updateQuantityModal(false)}>取消</a>
                    </div>
                </div>
            </div>
        )
    }
}

PosCartItmQuantityUpdateForm.contextTypes = {
    store: React.PropTypes.object
};

export default PosCartItmQuantityUpdateForm;
