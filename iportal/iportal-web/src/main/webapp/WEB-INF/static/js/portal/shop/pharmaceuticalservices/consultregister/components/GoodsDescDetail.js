/**
 * Created by HWJ on 2017/7/19
 */
import React, {Component, PropTypes} from "react";


class GoodsDescDetail extends Component {


    render() {

        const {goodsDetailData, goodsDetailTitle} = this.context.store.getState().todos;
        const {goodsDetail} = this.props.actions;

        return (
            <div className="layer" style={{zIndex: "300"}}>
                <div className="layer-box layer-text w860">
                    <div className="layer-header">
                        <span>{goodsDetailTitle}</span>
                        <a href="javascript:void(0)" className="close" onClick={() => goodsDetail(false, "", "")}/>
                    </div>
                    <div className="layer-body" style={{minHeight: "200px"}}>
                        <p>{goodsDetailData}</p>
                    </div>
                </div>
            </div>
        );

    }
}

GoodsDescDetail.contextTypes = {
    store: React.PropTypes.object
};

export default GoodsDescDetail