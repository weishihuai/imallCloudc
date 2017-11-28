import React, {Component, PropTypes} from "react";

class ShopMemberDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.memberDetailData(this.context.store.getState().todos.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    sexCodeFormat(sexCode) {
        switch (sexCode) {
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
            case "SECRET":
                return "保密";
            default :
                return "暂无";
        }
    }

    isMedicalInsuranceCardFormat(isMedicalInsuranceCard) {
        switch (isMedicalInsuranceCard) {
            case "Y":
                return "是";
            case "N":
                return "否";
            default:
                return "暂无";
        }
    }

    render() {
        const {store} = this.context;
        let memberData = store.getState().todos.memberDetailData || {};
        let isGiveCardString = memberData.isGiveCard === "Y" ? "是" : "否";

        function cardUseStateCodeFormat(cardUseStateCode) {
            switch (cardUseStateCode) {
                case "NORMAL":
                    return "正常";
                case "DISABLED":
                    return "禁用";
                default :
                    return "暂无";
            }
        }

        function isGiveCardFieldRender(value) {
            if (value === "Y") {
                return (
                    <div>
                        <div className="item">
                            <p>使用状态</p>
                            <span>{cardUseStateCodeFormat(memberData.cardUseStateCode)}</span>
                        </div>
                        <div className="item">
                            <p>发卡人</p>
                            <span>{memberData.giveCardMan || "暂无"}</span>
                        </div>
                        <div className="item">
                            <p>发卡时间</p>
                            <span>{memberData.giveCardTimeString || "暂无"}</span>
                        </div>
                        <div className="item">
                            <p><i>*</i>生效时间</p>
                            <span>{memberData.effectTimeString || "暂无"}</span>
                        </div>
                        <div className="item">
                            <p><i>*</i>失效时间</p>
                            <span>{memberData.expireTimeString || "暂无"}</span>
                        </div>
                    </div>
                )
            }
            else {
                return (<div/>)
            }
        }

        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order layer-consulting-register w960">
                    <div className="layer-header">
                        <span>会员档案详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.memberDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">基本信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>姓名</p>
                                        <span>{memberData.name}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>手机号</p>
                                        <span>{memberData.mobile}</span>
                                    </div>
                                    <div className="item">
                                        <p>会员卡号</p>
                                        <span>{memberData.memberCardNum || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>性别</p>
                                        <span>{this.sexCodeFormat(memberData.sexCode)}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>医保卡</p>
                                        <span>{this.isMedicalInsuranceCardFormat(memberData.isMedicalInsuranceCard)}</span>
                                    </div>
                                    <div className="item">
                                        <p>职业</p>
                                        <span>{memberData.profession || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>生日</p>
                                        <span>{memberData.birthdayString || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>病史</p>
                                        <span>{memberData.diseaseHistory || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>常用药</p>
                                        <span>{memberData.commonlyUsedDrugs || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{memberData.remark || "暂无"}</span>
                                    </div>
                                    <div className="item item-problem-description">
                                        <p>地址</p>
                                        <span>{memberData.homeAddr || "暂无"}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">发卡信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>是否发卡</p>
                                    <span>{isGiveCardString || "暂无"}</span>
                                </div>
                                {isGiveCardFieldRender(memberData.isGiveCard)}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

ShopMemberDetail.contextTypes = {
    store : React.PropTypes.object
};

export default ShopMemberDetail