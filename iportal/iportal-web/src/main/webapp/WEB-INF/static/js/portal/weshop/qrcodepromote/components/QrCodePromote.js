import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class QrCodePromote extends Component{

    componentWillMount(){
        this.props.actions.getQrCodeTicket();
    }

    componentDidMount(){

    }

    render(){
        const {store} = this.context;
        const qrCodeTicket = store.getState().todos.qrCodeTicket;
        return(
            <div className="main-box wd-qr-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>二维码推广</h5>
                            <a href="javascript:;">微店管理</a>
                            <span>></span>
                            <a href="javascript:;">二维码推广</a>
                        </div>
                    </div>
                </div>
                <div className="layer-box layer-info layer-order">
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item-qr">
                                    <img id="qrCodeImg" src={'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=' + qrCodeTicket} alt="推广二维码"/>
                                </div>
                                <div className="item-table">
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>二维码边长(cm)</th>
                                            <th>建议扫描距离(米)</th>
                                            <th>下载链接</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>8cm</td>
                                            <td>0.5m</td>
                                            <td><a target="_blank" href={iportal + "/backendwechat/download.json?url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrCodeTicket + "&length=224"}>点击下载</a></td>
                                        </tr>
                                        <tr>
                                            <td>12cm</td>
                                            <td>0.8m</td>
                                            <td><a target="_blank" href={iportal + "/backendwechat/download.json?url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrCodeTicket + "&length=336"}>点击下载</a></td>
                                        </tr>
                                        <tr>
                                            <td>15cm</td>
                                            <td>1m</td>
                                            <td><a target="_blank" href={iportal + "/backendwechat/download.json?url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrCodeTicket + "&length=420"}>点击下载</a></td>
                                        </tr>
                                        <tr>
                                            <td>30cm</td>
                                            <td>1.5m</td>
                                            <td><a target="_blank" href={iportal + "/backendwechat/download.json?url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrCodeTicket + "&length=840"}>点击下载</a></td>
                                        </tr>
                                        <tr>
                                            <td>50cm</td>
                                            <td>2.5m</td>
                                            <td><a target="_blank" href={iportal + "/backendwechat/download.json?url=https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + qrCodeTicket + "&length=1400"}>点击下载</a></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

QrCodePromote.propTypes = {
    actions: PropTypes.object.isRequired
};

QrCodePromote.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(QrCodePromote);