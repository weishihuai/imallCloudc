/**
 * Created by ou on 2017/7/11.
 */
import React,{PropTypes} from "react";


class SupplierQualityApproveDetailForm extends React.Component {

    componentWillMount() {
        this.props.actions.findByAvailableAndDictType("BUSINESS_RANGE");
        this.props.actions.findByAvailableAndDictType("BUSINESS_CATEGORY");
    }
    closeAddForm(){
        this.props.actions.showDetail(false);
        this.props.actions.setDetail({});
    }

    render() {
        const detailObject = this.props.detailObject || {supplierCertificatesFileList:[]};
        const todos = this.context.store.getState().todos;
        const supplierCertificatesFileList = todos.detailObject.supplierCertificatesFileList || [];
        const businessCategoryAllData = todos.businessCategoryAllData || [];
        const businessRangeAllData = todos.businessRangeAllData || [];
        const businessCategorySelect = todos.businessCategorySelect || [];
        const businessRangeDataSelect = todos.businessRangeDataSelect || [];
        var rang="";
        var cat="";
        return (<div className="layer">
            <div className="layer-box layer-info w960">
                <div className="layer-header">
                    <span>供应商信息</span>
                    <a  href="javascript:void(0);"  className="close" onClick={() => {this.closeAddForm()}}></a>
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
                            <div className="item item-1-1">
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
                            <div className="item item-1-1">
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
                        <div className="box-mt">审核人信息</div>
                        <div className="box-mc clearfix">
                            <div className="item-line-box">
                                <div className="item">
                                    <p><i>*</i>申请人</p>
                                    <span>{detailObject.applyMan}</span>
                                </div>
                                <div className="item">
                                    <p>申请时间</p>
                                    <span>{detailObject.applyDateString}</span>
                                </div>
                                <div className="item">
                                    <p>提交意见</p>
                                    <span>{detailObject.submitOpinion}</span>
                                </div>
                                <div className="item">
                                    <p>申请备注</p>
                                    <span>{detailObject.remark}</span>
                                </div>
                            </div>
                            {
                                detailObject.firstManageSupplierQualityApproveInfDetailVos.map((obj,index)=>(
                                        <div key={index}>
                                            <div className="item-line-box">
                                                <div className="item">
                                                    <p><i>*</i>{obj.approveType}人</p>
                                                    <span>{obj.approveMan}</span>
                                                </div>
                                                <div className="item">
                                                    <p>{obj.approveType}时间</p>
                                                    <span>{obj.approveTimeString}</span>
                                                </div>
                                                <div className="item">
                                                    <p><i>*</i>{obj.approveType}状态</p>
                                                    <span>{obj.approveState}</span>
                                                </div>
                                                <div className="item">
                                                    <p>{obj.approveType}备注</p>
                                                    <span>{obj.approveRemark}</span>
                                                </div>
                                            </div>
                                        </div>
                                ))
                            }
                        </div>
                    </div>
                </div>
                <div className="layer-footer">
                    <button  href="javascript:void(0);"  style={{border: "none"}} className="confirm" onClick={() => {this.closeAddForm()}}>返回
                    </button>
                </div>
            </div>
        </div>);

    }
}
SupplierQualityApproveDetailForm.contextTypes = {
    store: React.PropTypes.object
};
export default SupplierQualityApproveDetailForm