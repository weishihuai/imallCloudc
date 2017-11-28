import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class PrescriptionDetail extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    downloadFile(fileLibId){
        window.open(iportal + '/sysFileLib/downloadFile.json?id=' + fileLibId);
    }

    render() {
        const {store} = this.context;
        const {actions}=this.props;
        const record = store.getState().prescriptionTodos.record;
        const files = record.fileVoList || [];

        return(
            <div className="layer">
                <div className="layer-box layer-info layer-receiving layer-buying-out w960">
                    <div className="layer-header">
                        <span>处方详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => actions.showDetail(false)}/>
                    </div>
                    <div className="layer-body">
                        {
                            record.prescriptionRegisterState==='HAD_DISPENSING' &&
                            <div className="md-box">
                                <div className="box-mt">调剂信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>调剂员</p>
                                            <span>{record.dispensingManName}</span>
                                        </div>
                                        <div className="item">
                                            <p>复核员</p>
                                            <span>{record.repeatApproveManRealName}</span>
                                        </div>
                                        <div className="item">
                                            <p>发药人</p>
                                            <span>{record.grantDrugManName}</span>
                                        </div>
                                        <div className="item">
                                            <p>审核人</p>
                                            <span>{record.approveManRealName}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        }
                        <div className="md-box">
                            <div className="box-mt">处方信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>处方销售单号</p>
                                        <span>{record.prescriptionSellOrderCode}</span>
                                    </div>
                                    <div className="item">
                                        <p>处方日期</p>
                                        <span>{record.prescriptionDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p>医疗机构</p>
                                        <span>{record.medicalOrg}</span>
                                    </div>
                                    <div className="item">
                                        <p>医师姓名</p>
                                        <span>{record.doctorName}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>临床诊断</p>
                                        <span>{record.clinicDiagnosis}</span>
                                    </div>
                                    <div className="item">
                                        <p>医嘱</p>
                                        <span>{record.doctorsAdvice}</span>
                                    </div>
                                    <div className="item">
                                        <p>会员</p>
                                        <span>{record.memberCardNum}</span>
                                    </div>
                                    <div className="item">
                                        <p>患者名称</p>
                                        <span>{record.patientNm}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>性别</p>
                                        <span>{record.sexTypeName}</span>
                                    </div>
                                    <div className="item">
                                        <p>年龄</p>
                                        <span>{record.age}</span>
                                    </div>
                                    <div className="item">
                                        <p>证件类型</p>
                                        <span>{record.certTypeName}</span>
                                    </div>
                                    <div className="item">
                                        <p>证件号码</p>
                                        <span>{record.certNum}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>手机</p>
                                        <span>{record.mobile}</span>
                                    </div>
                                    <div className="item">
                                        <p>地址</p>
                                        <span>{record.addr}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{record.remark}</span>
                                    </div>
                                    <div className="item">
                                        <p>创建用户名称</p>
                                        <span>{record.createBy}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>创建时间</p>
                                        <span>{record.createDateString}</span>
                                    </div>
                                </div>
                                <div className="item" style={{display: files.length>0 ? 'block' : 'none', width: 666 + 'px'}}>
                                    <p>处方附件</p>
                                    <div className="item-upload">
                                        {
                                            files.length>0 &&
                                            files.map((file)=>{
                                                {
                                                    return(
                                                        <div className="upload-operation" key={file.fileId}>
                                                            {file.fileTypeCode==="IMAGE" && <a href={file.fileUrl} target="_blank" ><img src={file.fileUrl} alt={file.fileNm}/></a>}
                                                            {file.fileTypeCode!=="IMAGE" && <a href="javascript:void(0);" target="_blank" onClick={()=>this.downloadFile(file.sysFileLibId)}>{file.fileNm}</a>}
                                                        </div>
                                                    );
                                                }
                                            })
                                        }
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w860">
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="number">数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        record.itemList.map((item, index)=> {
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.goodsCoding}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.goodsNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.commonNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.spec}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.dosageForm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.unit}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.produceManufacturer}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.approvalNumber}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.productionPlace}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.quantity}</div>
                                                    </td>
                                                </tr>
                                            )
                                        })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

PrescriptionDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

PrescriptionDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(PrescriptionDetail);

export default PrescriptionDetail;