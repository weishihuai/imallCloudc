/**
 * Created by ou on 2017/7/11.
 */
import React,{PropTypes} from "react";


class SysUserDetailForm extends React.Component {

    componentWillMount() {
        this.props.actions.findByAvailableAndDictType();

    }
    closeAddForm(){
        this.props.actions.showDetail(false);

    }

    render() {

        const detailObject = this.props.detailObject;
        const todos = this.context.store.getState().todos;
        const allJobs = todos.allJobs || [];
        const jobSelectList = todos.jobSelectList || [];

        var newJobList="";
        return (<div className="layer" >
            <div className="layer-box layer-info layer-order layer-staff-management w960">
                <div className="layer-header">
                    <span>员工档案</span>
                    <a  href="javascript:void(0);"  className="close" onClick={() => {this.closeAddForm()}}></a>
                </div>
                <div className="layer-body">
                    <div className="md-box">
                        <div className="box-mt">基本信息</div>
                        <div className="box-mc clearfix">
                            <div className="item-line-box">
                                <div className="item">
                                    <p><i>*</i>姓名</p>
                                    <span>{detailObject.realName}</span>
                                </div>
                                <div className="item">
                                    <p>手机号</p>
                                    <span>{detailObject.mobile}</span>
                                </div>
                                <div className="item">
                                    <p>身份证</p>
                                    <span>{detailObject.identityCard}</span>
                                </div>
                                <div className="item">
                                    <p>性别</p>
                                    <span>{detailObject.sexName}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item">
                                    <p>籍贯</p>
                                    <span>{detailObject.nativePlace}</span>
                                </div>
                                <div className="item">
                                    <p>婚姻状况</p>
                                    <span>{detailObject.marriageStatName}</span>
                                </div>
                                <div className="item">
                                    <p>出生日期</p>
                                    <span>{detailObject.birthday}</span>
                                </div>
                                <div className="item">
                                    <p>邮箱</p>
                                    <span>{detailObject.email}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item">
                                    <p>备注</p>
                                    <span>{detailObject.mark}</span>
                                </div>
                                <div className="item item-1-2">
                                    <p>住址</p>
                                    <span>{detailObject.homeAddr}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="md-box">
                        <div className="box-mt">教育经历</div>
                        <div className="box-mc clearfix">
                            <div className="item-line-box">
                                <div className="item">
                                    <p>学校名称</p>
                                    <span>{detailObject.schoolNm}</span>
                                </div>
                                <div className="item">
                                    <p>专业</p>
                                    <span>{detailObject.major}</span>
                                </div>
                                <div className="item">
                                    <p>学历</p>
                                    <span>{detailObject.academicQualificati}</span>
                                </div>
                                <div className="item">
                                    <p>毕业时间</p>
                                    <span>{detailObject.graduationTime}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item">
                                    <p>参加工作时间</p>
                                    <span>{detailObject.joinWorkTime}</span>
                                </div>
                                <div className="item">
                                    <p>技术职称</p>
                                    <span>{detailObject.technologyPostTitle}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="md-box">
                        <div className="box-mt">岗位</div>
                        <div className="box-mc clearfix">

                            <div className="item item-1-1">
                                {
                                    allJobs.map((allJob) => {
                                        if (jobSelectList.indexOf(allJob.id) > -1) {
                                            newJobList=newJobList+allJob.jobName+"、";
                                        }
                                    })
                                }
                                <span style={{padding:'"0px'}}>{newJobList.substr(0,newJobList.length-1)}</span>
                            </div>
                        </div>
                    </div>
                    <div className="md-box">
                        <div className="box-mt">行政信息</div>
                        <div className="box-mc clearfix">
                            <div className="item-line-box">
                                <div className="item">
                                    <p>入职时间</p>
                                    <span>{detailObject.entryJobTime}</span>
                                </div>
                                <div className="item">
                                    <p>上岗证号</p>
                                    <span>{detailObject.employeeCode}</span>
                                </div>
                                <div className="item">
                                    <p>上岗培训</p>
                                    <span>{detailObject.isPostsTrain=="Y"?"是":"否"}</span>
                                </div>
                                <div className="item">
                                    <p>离职时间</p>
                                    <span>{detailObject.leaveTime}</span>
                                </div>
                            </div>
                            <div className="item-line-box">
                                <div className="item item-1-2">
                                    <p>离职原因</p>
                                    <span>{detailObject.leaveReason}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="md-box">
                        <div className="box-mt">账号与密码</div>
                        <div className="box-mc clearfix">
                            <div className="item">
                                <p>账号</p>
                                <span>{detailObject.userName}</span>
                            </div>
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
SysUserDetailForm.contextTypes = {
    store: React.PropTypes.object
};
export default SysUserDetailForm