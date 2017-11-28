import React, {PropTypes, Component} from "react";
import {WEB_NAME} from "../../../common/common-constant";

class OrderSuccessComponent extends Component {

    componentWillMount() {
        document.title = WEB_NAME + "订单提交成功";
    }

    render() {
        const id = this.props.params.id;
        return (
            <div className="main-tjsucceed">
                <img style={{display: "block", width: "10rem", height: "4.375rem", margin: "0 auto"}} src={iportal + "/static/img/wechat/tijaiochengong.png"} />
                    <p className="tjsucceed-txt">恭喜您，您的订单提交成功！</p>
                    <div className="btn-box">
                        <a className="return-index" href="#">返回首页</a>
                        <a className="order-par" href={"#/order-detail/" + id}>订单详情</a>
                    </div>
            </div>
        );
    }
}

export default OrderSuccessComponent;