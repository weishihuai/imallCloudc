/**
 * Created by ou on 2017/7/11.
 */
import React,{PropTypes} from "react";


class SupplierDocDetailForm extends React.Component {

    componentWillMount() {
        this.props.actions.findByAvailableAndDictType("BUSINESS_RANGE");
        this.props.actions.findByAvailableAndDictType("BUSINESS_CATEGORY");
    }

    shouldComponentUpdate(props, state){
    const detailObject = props.detailObject;
        if(detailObject!=null){
            return true;

        }else {
            return false;
        }
    }

    render() {

        const showDetail = this.props.actions.showDetail;
        const detailObject = this.props.detailObject || {supplierDocCertificatesFileList:[]};
        const todos = this.context.store.getState().todos;
        const supplierDocCertificatesFileList = todos.detailObject.supplierDocCertificatesFileList || [];
        const businessCategoryAllData = todos.businessCategoryAllData || [];
        const businessRangeAllData = todos.businessRangeAllData || [];
        const businessCategorySelect = todos.businessCategorySelect || [];
        const businessRangeDataSelect = todos.businessRangeDataSelect || [];
        let rang="";
        let cat="";
        return (
            <div className="layer" style={{display: "block" }}>
            <div className="layer-box layer-info w960">
                <div className="layer-header">
                    <span>供应商信息</span>
                    <a  href="javascript:void(0);"  className="close" onClick={() => {showDetail(false)}}></a>
                </div>
                <div className="layer-body">
                    <div className="md-box">
                        <div className="box-mt">基础信息</div>
                        <div className="box-mc clearfix">
                            <div className="item-line-box">
                                <div className="item">
                                    <p><i>*</i>供应商名称</p>
                                    <span>{detailObject.supplierNm}</span>
                                </div>
                                <div className="item">
                                    <p>供货单位性质</p>
                                    <span>{detailObject.unitNatureName}</span>
                                </div>
                                <div className="item">
                                    <p>质量负责人</p>
                                    <span>{detailObject.qualityResponseManName}</span>
                                </div>
                                <div className="item">
                                    <p>法定代表人</p>
                                    <span>{detailObject.legalRepresentative}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item">
                                    <p>业务负责人</p>
                                    <span>{detailObject.businessResponseManName}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>业务负责人电话</p>
                                    <span>{detailObject.businessResponseManTel}</span>
                                </div>
                                <div className="item">
                                    <p>业务负责人邮箱</p>
                                    <span>{detailObject.businessResponseManEmail}</span>
                                </div>
                                <div className="item">
                                    <p>注册资本(万)</p>
                                    <span>{detailObject.regCapital}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item">
                                    <p>注册地址</p>
                                    <span>{detailObject.regAddr}</span>
                                </div>
                                <div className="item">
                                    <p>传真</p>
                                    <span>{detailObject.fax}</span>
                                </div>
                                <div className="item w450">
                                    <p>退货地址</p>
                                    <span>{detailObject.returnedPurchaseAddr}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="md-box">
                        <div className="box-mt">经营范围</div>
                        <div className="box-mc clearfix">
                            <div className="item w860">
                                <p><i>*</i>经营品种</p>
                                {
                                    businessCategoryAllData.map((allItem) => {
                                        if (businessCategorySelect.indexOf(allItem.dictItemCode) > -1) {
                                            cat=cat+allItem.dictItemNm+"、";
                                        }
                                    })
                                }
                                <span style={{padding:'"0px'}}>{cat.substr(0,cat.length-1)}</span>
                            </div>
                            <div className="item w860">
                                <p><i>*</i>经营范围</p>
                                {
                                    businessRangeAllData.map((allItem) => {
                                        if (businessRangeDataSelect.indexOf(allItem.dictItemCode) > -1) {
                                            rang=rang+allItem.dictItemNm+"、";
                                        }
                                    })
                                }
                                <span style={{padding:'"0px'}}>{rang.substr(0,rang.length-1)}</span>
                            </div>
                        </div>
                    </div>
                    <div className="md-box">
                        <div className="box-mt">资质文件</div>
                        <div className="box-mc clearfix">

                            {
                                supplierDocCertificatesFileList.map((certificatesFile, index) => {
                                    switch (certificatesFile.certificatesType) {
                                        case "BUSINESS_LICENSE":
                                            //营业执照号
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                                            return(
                                                <div key={index}>
                                                    <div  className="item">
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
                </div>
                <div className="layer-footer">
                    <button  href="javascript:void(0);"  style={{border: "none"}} className="confirm" onClick={() => {showDetail(false)}}>返回
                    </button>
                </div>
            </div>
        </div>
        );

    }
}
SupplierDocDetailForm.contextTypes = {
    store: React.PropTypes.object
};
export default SupplierDocDetailForm