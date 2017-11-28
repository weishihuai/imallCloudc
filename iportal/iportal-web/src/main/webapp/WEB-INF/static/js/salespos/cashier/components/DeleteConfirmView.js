/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component, PropTypes} from "react";

/*删除确认*/
class DeleteConfirmView extends Component {

    constructor(props) {
        super(props);
        this.updateQuantityHandle = this.updateQuantityHandle.bind(this)

    }

    componentDidMount() {
        window.addEventListener('keydown', this.updateQuantityHandle);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.updateQuantityHandle);
    }

    updateQuantityHandle(event) {
        const {changeConfirmState,clearCart,removeCartItem} = this.props.actions;
        const {store} = this.context;
        const {uniqueKey,selectCartItem,isDeleteAll} = store.getState().todos;
        switch(event.keyCode){
            //enter
            case 13:
                event.preventDefault();
                if(isDeleteAll == "Y"){
                    clearCart(uniqueKey);
                    break;
                }
                removeCartItem(uniqueKey, selectCartItem.skuId, selectCartItem.batch);
                break;
            //esc
            case 27:
                event.preventDefault();
                let oldjs = document.getElementById("notyLayer");
                if(oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                    break;
                }
                changeConfirmState(false,"");
                break;
        }
    }

    render() {
        const { title,text, callback, close, zIndex, confirmBtn, cancelBtn} = this.props;
        return (
            <div className="layer"  style={{zIndex: zIndex}}>
                <div className="layer-box w430">
                    <div className="layer-header">
                        <span>{title}</span>
                        <a href="javascript:void(0);" className="close" onClick={() => close()}/>
                    </div>
                    <div className="layer-body" style={{padding: "27px", textAlign: "center"}}>
                <span style={{fontSize: "16px", lineHeight: "24px"}}>
                    {text}
                </span>
                    </div>
                    <div className="layer-footer" style={{padding: "12px 0 50px 150px"}}>
                        <a href="javascript:void(0);" className="confirm" onClick={() => callback()}>{confirmBtn ? confirmBtn : '删除'}</a>
                        <a href="javascript:void(0);" className="cancel" onClick={() => close()}>{cancelBtn ? cancelBtn : '取消'}</a>
                    </div>
                </div>
            </div>
        )
    }
}

DeleteConfirmView.contextTypes = {
    store: React.PropTypes.object
};

export default DeleteConfirmView;
