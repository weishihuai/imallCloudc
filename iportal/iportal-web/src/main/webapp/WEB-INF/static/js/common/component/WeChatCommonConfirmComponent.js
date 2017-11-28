/**
 * 微信确认框
 */
import React, {Component, PropTypes} from "react";

class WeChatCommonConfirmComponent extends Component{
    render() {
        const {title, text, callback, close, zIndex, confirmBtn, cancelBtn} = this.props;

        return(
            <div className="layer del-demand-box" style={{zIndex: zIndex ? zIndex : 100}}>
                <div className="del-demand">
                    <p className="del-demand-title">{title}</p>
                    <p className="del-demand-txt">{text}</p>
                    <div className="del-demand-btn">
                        <a className="del-demand-btn1" href="javascript:void(0);" onClick={() => close()}>{confirmBtn ? confirmBtn : '取消'}</a>
                        <a className="del-demand-btn2" href="javascript:void(0);" onClick={() => callback()}>{cancelBtn ? cancelBtn : '确定'}</a>
                    </div>
                </div>
            </div>
        );
    }
}

WeChatCommonConfirmComponent.propTypes = {
    title: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,
    callback: PropTypes.func.isRequired,
    close: PropTypes.func.isRequired
};

WeChatCommonConfirmComponent.contextTypes = {
    store: React.PropTypes.object
};

export default WeChatCommonConfirmComponent;