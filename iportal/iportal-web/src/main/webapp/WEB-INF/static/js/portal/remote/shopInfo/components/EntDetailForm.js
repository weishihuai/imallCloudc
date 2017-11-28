/**
 * Created by ou on 2017/7/11.
 */
import React, {PropTypes} from "react";


class EntDetailForm extends React.Component {

    componentWillMount() {
        this.props.actions.findShopDetail();
        this.props.actions.findByAvailableAndDictType("BUSINESS_RANGE");
    }


    render() {
        const todos = this.context.store.getState().todos;
        const detailObject = todos.detailObject || {shopCertificatesFileVoList: []};
        const shopCertificatesFileVoList = todos.detailObject.shopCertificatesFileVoList || [];
        const businessRangeAllData = todos.businessRangeAllData || [];
        const businessRangeDataSelect = todos.businessRangeDataSelect || [];
        var rang = "";
        return (
            <div className="main-box qy-info-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>企业信息</h5>
                            <a href="##">系统管理</a>
                            <span>&gt;</span>
                            <a href="##">企业信息</a>
                        </div>
                    </div>
                    <div className="layer-box layer-info layer-order">
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>企业编码</p>
                                        <span>{detailObject.shopCode}</span>
                                    </div>
                                    <div className="item">
                                        <p>企业名称</p>
                                        <span>{detailObject.entNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>法定代表人</p>
                                        <span>{detailObject.legalRepresentativeMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>企业负责人</p>
                                        <span>{detailObject.entResponseMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>企业类型</p>
                                        <span>{detailObject.entType}</span>
                                    </div>
                                    <div className="item">
                                        <p>是否医保店</p>
                                        <span>{detailObject.isMedicalInsuranceShop ? "是" : "否"}</span>
                                    </div>
                                    <div className="item">
                                        <p>经营方式</p>
                                        <span>{detailObject.businessWay}</span>
                                    </div>
                                    <div className="item">
                                        <p>注册资金(万)</p>
                                        <span>{detailObject.regCapital} </span>
                                    </div>
                                    <div className="item">
                                        <p>注册地址</p>
                                        <span>{detailObject.regAddr}</span>
                                    </div>
                                    <div className="item">
                                        <p>公司地址</p>
                                        <span>{detailObject.companyAddr}</span>
                                    </div>
                                    <div className="item ">
                                        <p>仓库地址</p>
                                        <span>{detailObject.storageAddr}</span>
                                    </div>
                                    <div className="item ">
                                        <p>公司电话</p>
                                        <span>{detailObject.companyTel}</span>
                                    </div>
                                    <div className="item ">
                                        <p>公司传真</p>
                                        <span>{detailObject.companyFax}</span>
                                    </div>
                                    <div className="item">
                                        <p>税务登记证号</p>
                                        <span>{detailObject.taxRegisterCertNum}</span>
                                    </div>
                                    <div className="item ">
                                        <p>年检日期</p>
                                        <span>{detailObject.annualInspectionDateString}</span>
                                    </div>
                                    <div className="item ">
                                        <p>公司简介</p>
                                        <span>{detailObject.companyBrief}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">经营范围</div>
                                <div className="box-mc clearfix">
                                    <div className="item item-1-1">
                                        <p>经营范围</p>
                                        {
                                            businessRangeAllData.map((allItem, index) => {
                                                if (businessRangeDataSelect.indexOf(allItem.dictItemCode) > -1) {
                                                    rang = rang + allItem.dictItemNm + "、";
                                                }
                                            })
                                        }
                                        <span style={{padding: '"0px'}}>{rang.substr(0, rang.length - 1)}</span>

                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">资质文件</div>
                                <div className="box-mc clearfix">
                                    {
                                        shopCertificatesFileVoList.map((certificatesFile, index) => {
                                            switch (certificatesFile.certificatesType) {
                                                case "BUSINESS_LICENSE":
                                                    //营业执照号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p><i>*</i>营业执照号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p><i>*</i>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                case "ORGANIZATION_CERTIFICATE":
                                                    //组织机构代码证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>组织机构代码证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                case "GSP_CERTIFICATE":
                                                    //GSP证书号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>GSP证书号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                case "COMMODITY_LICENSE":
                                                    //商品经营许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>商品经营许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                case "QUALITY_AGREEMENT":
                                                    //质量协议书号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>质量协议书号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                case "FOOD_CIRCULATION_LICENSE":
                                                    //食品流通许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>食品流通许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                    break;
                                                case "FOOD_HYGIENE_LICENSE":
                                                    //食品卫生许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>食品卫生许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                    break;
                                                case "HEALTH_PRODUCT_HYGIENE_LICENSE":
                                                    //保健品卫生许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>保健品卫生许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                    break;
                                                case "MEDIC_DEVICE_MANUFACTURE_PERMISS":
                                                    //医疗器械许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>医疗器械许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                    break;
                                                case "COSMETICS_BUSINESS_LICENSE":
                                                    //化妆品经营许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>化妆品经营许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                    break;
                                                case "COSMETICS_HYGIENIC_LICENSE":
                                                    //化妆品卫生许可证号
                                                    return (
                                                        <div key={index}>
                                                            <div className="item">
                                                                <p>化妆品卫生许可证号</p>
                                                                <span>{certificatesFile.certificatesNum}</span>
                                                            </div>
                                                            <div className="item">
                                                                <p>有效期至</p>
                                                                <span>{certificatesFile.certificatesValidityString}</span>
                                                            </div>
                                                        </div>
                                                    );
                                                    break;
                                                default :
                                            }

                                        })
                                    }
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">账号信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p><i>*</i>用户名</p>
                                        <span>{detailObject.userName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>E-Mail</p>
                                        <span>{detailObject.email}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>手机</p>
                                        <span>{detailObject.mobile}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">门店状态</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>门店状态</p>
                                        <span>{detailObject.isEnable == "Y" ? "启用" : "禁用"}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>

           );

    }
}
EntDetailForm.contextTypes = {
    store: React.PropTypes.object
};
export default EntDetailForm