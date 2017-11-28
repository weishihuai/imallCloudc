import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import WeChatUserNickNameUpdate from "./WeChatUserNickNameUpdate";
import {updateNickNameModel,weChatUserInfoDetailData,getWeiXinJsConfig,uploadUserIcoFromWeiXin, setWeChatUserInfoDetailData} from "../actions/index";
import {bindActionCreators} from "redux";
import {WEB_NAME} from "../../../common/common-constant";

//判空
function isEmpty(obj){
    return obj === undefined || obj === null;
}

/*详情页面*/
class WeChatUserDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        document.title = WEB_NAME + "编辑资料";
        this.props.weChatUserInfoDetailData(this.props.params.id);
        this.props.getWeiXinJsConfig(data => {
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名，见附录1
                jsApiList: ['chooseImage', 'uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        });
    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {uploadUserIcoFromWeiXin, setWeChatUserInfoDetailData} = this.props;
        const _this = this;
        $('#btnUpload').click(function(){
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    let localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    if(!isEmpty(localIds)){
                        wx.uploadImage({
                            localId: localIds[0], // 需要上传的图片的本地ID，由chooseImage接口获得
                            isShowProgressTips: 0, // 默认为1，显示进度提示
                            success:function (res) {
                                uploadUserIcoFromWeiXin(res.serverId, data => {
                                    const weChatUserTodos = _this.context.store.getState().weChatUserTodos.weChatUserInfoDetailData;
                                    weChatUserTodos.imgUrl = data.msg;
                                    setWeChatUserInfoDetailData(weChatUserTodos);
                                });
                            }
                        });
                    }
                }
            });
        });
    }

    render() {
        const {store} = this.context;
        let {weChatUserInfoDetailData} = store.getState().weChatUserTodos || {};
        let {updateWeChatUserNickNameUpdateState} = store.getState().weChatUserTodos;

        return (
            <div style={{marginBottom: "-11rem"}} className="we-chat-user-info-detail">
                <div className="mt">
                    <span>头像</span>
                    <div className="pic">
                        <a href="javascript:void(0);" id="btnUpload"><img src={weChatUserInfoDetailData.imgUrl} alt=""/>
                        </a>
                    </div>
                </div>
                <div className="mc">
                    <div className="item">
                        <a onClick={() => this.props.updateNickNameModel(true,weChatUserInfoDetailData)}>
                            <span>用户名称</span>
                            <i className="elli">{weChatUserInfoDetailData.nickName}</i>
                        </a>
                    </div>
                    <div className="item">
                        <a href="#/bind-mobile">
                            <span>手机号码</span>
                            <i className="elli">{weChatUserInfoDetailData.mobile === "" ? "未绑定" : weChatUserInfoDetailData.mobile}</i>
                        </a>
                    </div>
                </div>
                <div className="tips">绑定手机号码，可获取更多优惠！赶紧行动吧</div>
                {updateWeChatUserNickNameUpdateState && <WeChatUserNickNameUpdate/>}
            </div>
        )
    }
}

WeChatUserDetail.propTypes = {};

WeChatUserDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        updateNickNameModel,
        weChatUserInfoDetailData,
        getWeiXinJsConfig,
        uploadUserIcoFromWeiXin,
        setWeChatUserInfoDetailData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserDetail);