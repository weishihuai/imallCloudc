/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component, PropTypes} from "react";

/*删除确认*/
class CommonConfirmComponent extends Component {

    render() {
        const {title, text, callback, close, zIndex, confirmBtn, cancelBtn} = this.props;

        return (

            <div className="layer"  style={{zIndex: zIndex}}>
                <div className="layer-box w430">
                    <div className="layer-header">
                        <span>{title || !title && "提示"}</span>
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

CommonConfirmComponent.contextTypes = {
    store: React.PropTypes.object
};

export default CommonConfirmComponent;
