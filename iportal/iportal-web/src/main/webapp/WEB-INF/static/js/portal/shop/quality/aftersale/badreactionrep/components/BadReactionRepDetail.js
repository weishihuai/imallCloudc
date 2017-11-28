import React, {Component} from "react";

/*不良反应报告 详情页面*/
class BadReactionRepDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.badReactionRepDetailData(this.context.store.getState().todos.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    /*是否首次报告*/
    isFirstRepFormat(isFirstRep) {
        switch (isFirstRep) {
            case "FIRST_REG":
                return "首次报告";
            case "FOLLOW_REG":
                return "跟踪报告";
        }
    }

    /*报告类型*/
    regTypeFormat(regType) {
        switch (regType) {
            case "NOVEL":
                return "新的";
            case "SERIOUS":
                return "严重";
            case "COMMON":
                return "一般";
        }
    }

    /*报告单位类型*/
    repDepartmentFormat(repDepartment) {
        switch (repDepartment) {
            case "MEDICAL_INSTITUTION":
                return "医疗机构";
            case "OPERATE_ENTERPRISE":
                return "经营企业";
            case "MANUFACTURING_ENTERPRISE":
                return "生产企业";
            case "PERSONAL":
                return "个人";
            case "OTHER":
                return "其他";
        }
    }

    /*性别*/
    sexCodeFormat(sexCode) {
        switch (sexCode) {
            case "MALE":
                return "男";
            case "FEMALE":
                return "女";
            case "SECRET":
                return "保密";
        }
    }

    /*既往药品不良反应类型*/
    drugBadReactionFormat(drugBadReaction) {
        switch (drugBadReaction) {
            case "HAS":
                return "有";
            case "NO":
                return "无";
            case "UNSPECIFIED":
                return "不详";
        }
    }

    /*相关重要信息类型*/
    badReactionReferImportantInfFormat(badReactionReferImportantInf) {
        switch (badReactionReferImportantInf) {
            case "SMOKE_HISTORY":
                return "吸烟史";
            case "DRINK_HISTORY":
                return "饮酒史";
            case "GESTATION_PERIOD":
                return "妊娠期";
            case "HEPATOPATHY_HISTORY":
                return "肝病史";
            case "NEPHROPATHY_HISTORY":
                return "肾病史";
            case "ALLERGIC_HISTORY":
                return "过敏史";
            case "OTHER":
                return "其他";
        }
    }

    /*不良结果类型*/
    badReactionResultFormat(badReactionResult){
        switch (badReactionResult) {
            case "RECOVERY":
                return "痊愈";
            case "GETTING_BETTER":
                return "好转";
            case "NOT_GETTING_BETTER":
                return "未好转";
            case "UNKNOWN":
                return "不详";
            case "REMNANT_SYMPTOM":
                return "有后遗症";
            case "DEATH":
                return "死亡";
        }
    }

    /*反应是否消失类型*/
    responseIsEaseFormat(responseIsEase) {
        switch (responseIsEase) {
            case "Y":
                return "是";
            case "N":
                return "否";
            case "UNKNOWN":
                return "不明";
            case "WITHOUT_OR_WITHOUT_REDUCTION":
                return "未停药或未减量";
        }
    }

    /*是否再次出现同样反应类型*/
    badRegReactionIsAppearAgainFormat(badRegReactionIsAppearAgain) {
        switch (badRegReactionIsAppearAgain) {
            case "Y":
                return "是";
            case "N":
                return "否";
            case "UNKNOWN":
                return "不明";
            case "NO_LONGER_USED":
                return "未再使用";
        }
    }

    /*对原疾病影响类型*/
    effectToOriginalDiseaseFormat(effectToOriginalDisease) {
        switch (effectToOriginalDisease) {
            case "NON_CLEAR":
                return "不明显";
            case "PROLONGE_COURSE":
                return "病程延长";
            case "ILLNESS_WORSE":
                return "病情加重";
            case "CAUSE_SEQUEL":
                return "导致后遗症";
            case "CAUSE_DEATH":
                return "导致死亡";
        }
    }

    /*评价类型*/
    repEvaluateTypeFormat(repEvaluateType) {
        switch (repEvaluateType) {
            case "CERTAIN":
                return "肯定";
            case "IN_ALL_PROBABILITY":
                return "很可能";
            case "PROBABLY":
                return "可能";
            case "PROBABLY_IRRELEVANT":
                return "可能无关";
            case "WAIT_APPRAISE":
                return "待评价";
            case "CANNOT_APPRAISE":
                return "无法评价";
        }
    }

    /*职业*/
    repManProfessionTypeFormat(repManProfessionType) {
        switch (repManProfessionType) {
            case "DOCTOR":
                return "医生";
            case "APOTHECARY":
                return "药师";
            case "NURSE":
                return "护士";
            case "OTHER":
                return "其他";
        }
    }

    /*生产企业信息来源*/
    infSourceFormat(infSource) {
        switch (infSource) {
            case "MEDICAL_INSTITUTION":
                return "医疗机构";
            case "OPERATE_ENTERPRISE":
                return "经营企业";
            case "PERSONAL":
                return "个人";
            case "POST_MARKETING_STUDY":
                return "上市后研究";
            case "DOCUMENT_REPORT":
                return "文献报道";
            case "OTHER":
                return "其他";
        }
    }

    render() {
        const {store} = this.context;
        let badReactionRepDetailData = store.getState().todos.badReactionRepDetailData || {};
        let suspectDrugInfList = badReactionRepDetailData.suspectDrugInfList || [];  //怀疑药品信息
        let blendDrugInfList = badReactionRepDetailData.blendDrugInfList || [];  //并用药品信息

        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order layer-adverse-reactions w1617">
                    <div className="layer-header">
                        <span>不良反应报告详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.badReactionRepDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <table>
                                    <thead>
                                    <tr><th colSpan="9">药品不良反应/事件报告</th></tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><span>是否首次报告：</span></td>
                                        <td>{this.isFirstRepFormat(badReactionRepDetailData.isFirstRep)}</td>
                                        <td><span>报告类型：</span></td>
                                        <td>{this.regTypeFormat(badReactionRepDetailData.repType)}</td>
                                        <td><span>报告单位类别：</span></td>
                                        <td>{this.repDepartmentFormat(badReactionRepDetailData.repDepartment)}</td>
                                        <td><span>编码：</span></td>
                                        <td>{badReactionRepDetailData.code}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>患者姓名：</span></td>
                                        <td>{badReactionRepDetailData.patientName}</td>
                                        <td><span>性别：</span></td>
                                        <td>{this.sexCodeFormat(badReactionRepDetailData.sexCode)}</td>
                                        <td><span>出生日期：</span></td>
                                        <td>{badReactionRepDetailData.birthDateString}</td>
                                        <td><span>年龄：</span></td>
                                        <td>{badReactionRepDetailData.age}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>民族：</span></td>
                                        <td>{badReactionRepDetailData.nation}</td>
                                        <td><span>体重(kg)：</span></td>
                                        <td>{badReactionRepDetailData.weight}</td>
                                        <td><span>联系方式：</span></td>
                                        <td>{badReactionRepDetailData.contactWay}</td>
                                        <td><span>原患疾病：</span></td>
                                        <td>{badReactionRepDetailData.originalDisease}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>医院名称：</span></td>
                                        <td>{badReactionRepDetailData.hospitalNm}</td>
                                        <td><span>病例号/门诊号：</span></td>
                                        <td>{badReactionRepDetailData.medicalRecordNo}</td>
                                        <td><span>既往药品不良反应/事件：</span></td>
                                        <td>{this.drugBadReactionFormat(badReactionRepDetailData.pastDrugBadReaction)}</td>
                                        <td><span>家族药品不良反应/事件：</span></td>
                                        <td>{this.drugBadReactionFormat(badReactionRepDetailData.familyDrugBadReaction)}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>相关重要信息：</span></td>
                                        <td>{this.badReactionReferImportantInfFormat(badReactionRepDetailData.referImportantInf)}</td>
                                        <td><span>不良反应/事件名称：</span></td>
                                        <td>{badReactionRepDetailData.badReaction}</td>
                                        <td><span>不良反应/事件发生时间：</span></td>
                                        <td>{badReactionRepDetailData.badReactionOccurTimeString}</td>
                                        <td><span>不良反应/事件结果：</span></td>
                                        <td>{this.badReactionResultFormat(badReactionRepDetailData.badReactionResult)}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>报告日期：</span></td>
                                        <td>{badReactionRepDetailData.repDateString}</td>
                                        <td><span>后遗症表现：</span></td>
                                        <td>{badReactionRepDetailData.sequelaPerform}</td>
                                        <td><span>直接死因：</span></td>
                                        <td>{badReactionRepDetailData.causeOfDeath}</td>
                                        <td><span>死亡时间：</span></td>
                                        <td>{badReactionRepDetailData.deadTimeString}</td>
                                        <td/>
                                    </tr>
                                    <tr className="tr-title">
                                        <td>药品</td>
                                        <td>批准文号</td>
                                        <td>商品名称</td>
                                        <td>通用名称<br/>（含剂型）</td>
                                        <td>生产厂家</td>
                                        <td>生产批号</td>
                                        <td>用法用量<br/>（次剂量、途径、日次数）</td>
                                        <td>用药起止时间</td>
                                        <td>用药原因</td>
                                    </tr>
                                    { suspectDrugInfList.map((suspectDrugInf, index) => {
                                        return (<tr key={index}>
                                                {index == 0 && <td rowSpan={suspectDrugInfList.length >= 3 ? suspectDrugInfList.length : 3}><span style={{textAlign: "center"}}>怀疑药品</span></td>}
                                                <td>{suspectDrugInf.suspectApprovalNumber||""}</td>
                                                <td>{suspectDrugInf.suspectGoodsNm||""}</td>
                                                <td>{suspectDrugInf.suspectCommonNm||""}</td>
                                                <td>{suspectDrugInf.suspectProduceManufacturer||""}</td>
                                                <td>{suspectDrugInf.suspectBatch||""}</td>
                                                <td>{suspectDrugInf.suspectUsageAndDosage||""}</td>
                                                <td>{suspectDrugInf.suspectDrugUseTime||""}</td>
                                                <td>{suspectDrugInf.suspectDrugUseReason||""}</td>
                                            </tr>
                                            )
                                        })
                                    }

                                    {blendDrugInfList.map((blendDrugInf, index) => {
                                        return (<tr key={index}>
                                                {index === 0 && <td rowSpan={blendDrugInfList.length >= 3 ? blendDrugInfList.length : 3}><span style={{textAlign: "center"}}>并用药品</span></td>}
                                                <td>{blendDrugInf.blendApprovalNumber}</td>
                                                <td>{blendDrugInf.blendGoodsNm}</td>
                                                <td>{blendDrugInf.blendCommonNm}</td>
                                                <td>{blendDrugInf.blendProduceManufacturer}</td>
                                                <td>{blendDrugInf.blendBatch}</td>
                                                <td>{blendDrugInf.blendUsageAndDosage}</td>
                                                <td>{blendDrugInf.blendDrugUseTime}</td>
                                                <td>{blendDrugInf.blendDrugUseReason}</td>
                                            </tr>
                                        )
                                    })
                                    }
                                    <tr>
                                        <td colSpan="2"><span>停药或减量后，反应/事件是否消失或减轻：</span></td>
                                        <td>{this.responseIsEaseFormat(badReactionRepDetailData.responseIsEase)}</td>
                                        <td colSpan="2"><span>再次使用可疑药品后是否再次出现同样反应/事件：</span></td>
                                        <td>{this.badRegReactionIsAppearAgainFormat(badReactionRepDetailData.isAppearAgain)}</td>
                                        <td><span>对原患疾病的影响：</span></td>
                                        <td>{this.effectToOriginalDiseaseFormat(badReactionRepDetailData.effectToOriginalDisease)}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>报告评价：</span></td>
                                        <td colSpan="8">{this.repEvaluateTypeFormat(badReactionRepDetailData.repManEvaluate)}</td>
                                    </tr>
                                    <tr>
                                        <td><span>报告人电话：</span></td>
                                        <td>{badReactionRepDetailData.repManTel}</td>
                                        <td><span>报告人职业：</span></td>
                                        <td>{this.repManProfessionTypeFormat(badReactionRepDetailData.repManProfession)}</td>
                                        <td><span>报告人邮箱：</span></td>
                                        <td>{badReactionRepDetailData.repManEmail}</td>
                                        <td><span>报告人姓名：</span></td>
                                        <td>{badReactionRepDetailData.repManName}</td>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>报告单位评价：</span></td>
                                        <td colSpan="8">{this.repEvaluateTypeFormat(badReactionRepDetailData.repDepartmentEvaluate)}</td>
                                    </tr>
                                    <tr>
                                        <td><span>报告单位名称：</span></td>
                                        <td>{badReactionRepDetailData.repDepartmentNm}</td>
                                        <td><span>联系人：</span></td>
                                        <td>{badReactionRepDetailData.repDepartmentContactMan}</td>
                                        <td><span>电话：</span></td>
                                        <td>{badReactionRepDetailData.repDepartmentTel}</td>
                                        <td/>
                                        <td/>
                                        <td/>
                                    </tr>
                                    <tr>
                                        <td><span>生产企业请填写信息来源：</span></td>
                                        <td>{this.infSourceFormat(badReactionRepDetailData.infSource)}</td>
                                        <td><span>备注：</span></td>
                                        <td colSpan="5">{badReactionRepDetailData.remark}</td>
                                    </tr>
                                    <tr><td colSpan="9">不良反应/时间过程描述（包括症状、体征、临床体验等）及处理情况(可附页)：</td></tr>
                                    <tr>
                                        <td colSpan="9">{badReactionRepDetailData.badReactionProcessDescr}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

BadReactionRepDetail.contextTypes = {
    store : React.PropTypes.object
};

export default BadReactionRepDetail