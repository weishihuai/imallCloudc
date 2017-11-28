/**
 * Created by ou on 2017/7/11.
 */
import React,{PropTypes} from "react";


class HealthDocDetailForm extends React.Component {

    closeAddForm(){
        this.props.actions.showDetail(false);
        this.props.actions.setDetail({});
    }

    render() {

        const detailObject = this.props.detailObject || {supplierCertificatesFileList:[]};
        return (
                <div className="layer" >
                    <div className="layer-box layer-info layer-receiving layer-buying-out layer-employee-files w860">
                        <div className="layer-header">
                            <span>员工档案</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => {this.closeAddForm()}}></a>

                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">员工信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>员工账号</p>
                                        <span>{detailObject.userName}</span>
                                    </div>
                                    <div className="item">
                                        <p>员工姓名</p>
                                        <span>{detailObject.realName}</span>
                                    </div>
                                    <div className="item">
                                        <p>性别</p>
                                        <span>{detailObject.sex}</span>
                                    </div>
                                    <div className="item">
                                        <p>出生日期</p>
                                        <span>{detailObject.birthdayString}</span>
                                    </div>
                                    <div className="item">
                                        <p>入职日期</p>
                                        <span>{detailObject.entryJobTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>员工状态</p>
                                        <span>{detailObject.state=="Y"?"启用":"禁用"}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">档案信息</div>
                                <div className="box-mc">
                                    <table className="w715">
                                        <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="check-number">检查编号</th>
                                            <th className="date">检查日期</th>
                                            <th className="inspection-agencies">检查机构</th>
                                            <th className="check-project">检查项目</th>
                                            <th className="check-result">检查结果</th>
                                            <th className="take-measures">采取措施</th>
                                            <th className="note">备注</th>
                                        </tr>
                                        </thead>
                                        {detailObject.staffHealthDocInfDetailVoList.length==0&&
                                            <tbody> <tr> <th colSpan="100" style={{textAlign:"center"}}>暂无数据</th> </tr> </tbody>
                                        }
                                        <tbody>
                                        {detailObject.staffHealthDocInfDetailVoList.map((staffHealthDocInf,index)=>{
                                            return <tr key={index}>
                                                <td>
                                                    <div className="td-cont">{index+1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.checkNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.checkDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.checkOrg}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.checkPrj}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.checkResult}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.takeMeasures}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{staffHealthDocInf.remark}</div>
                                                </td>
                                            </tr>
                                        })}
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <div className="layer-footer">
                        </div>
                    </div>
                </div>
        );

    }
}
HealthDocDetailForm.contextTypes = {
    store: React.PropTypes.object
};
export default HealthDocDetailForm