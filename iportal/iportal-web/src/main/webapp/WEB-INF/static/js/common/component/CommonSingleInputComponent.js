/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component, PropTypes} from "react";

/*单输入框*/
class CommonSingleInputComponent extends Component {

    render() {
        const {title, label, callback, value, close, zIndex} = this.props;

        return (
            <div className="layer"  style={{zIndex: zIndex}}>
                <div className="layer-box layer-start w430">
                    <div className="layer-header">
                        <span>{title}</span>
                        <a href="javascript:void(0);" className="close" onClick={() => close()}/>
                    </div>
                    <div className="layer-body">
                        <div className="item">
                            <div className="mlt">{label}</div>
                            <div className="mrt">
                                <input type="text" defaultValue={value} ref="singleInputWinValue" />
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <a href="javascript:void(0);" className="confirm" onClick={() => callback(this.refs.singleInputWinValue.value)}>保存</a>
                        <a href="javascript:void(0);" className="cancel" onClick={() => close()}>取消</a>
                    </div>
                </div>
            </div>
        )
    }
}

CommonSingleInputComponent.contextTypes = {
    store: React.PropTypes.object
};

export default CommonSingleInputComponent;
