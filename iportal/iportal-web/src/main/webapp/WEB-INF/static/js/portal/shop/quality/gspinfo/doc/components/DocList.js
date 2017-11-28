import React, {Component} from "react";

class DocList extends Component{
    componentDidMount(){

    }
    
    render(){
        return(
            <div className="main-box archives-info-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>档案管理</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">GSP资料</a>
                            <span>></span>
                            <a href="javascript:void(0);">档案管理</a>
                        </div>
                    </div>
                </div>
                <div className="layer-box">
                    <table>
                        <caption>药品零售企业GSP认证档案目录（供参考）</caption>
                        <thead>
                            <tr>
                                <th className="serial-number"><span>序号</span></th>
                                <th className="name" style={{width: 195 + 'px'}}><span>名称</span></th>
                                <th className="content"><span>内容</span></th>
                                <th className="note"><span>备注</span></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowSpan="3"><span>1</span></td>
                            <td rowSpan="3"><span>企业资质</span></td>
                            <td>《药品经营许可证》副本</td>
                            <td rowSpan="3"><span>档案盒1</span></td>
                        </tr>
                        <tr>
                            <td>《药品经营质量管理规范》证书复印件（加盖企业原印章）</td>
                        </tr>
                        <tr>
                            <td>《营业执照》副本</td>
                        </tr>
                        <tr>
                            <td rowSpan="3"><span>2</span></td>
                            <td rowSpan="3"><span>组织机构与框图</span></td>
                            <td>建立质量管理机构（以文件形式公布）</td>
                            <td rowSpan="3"><span>档案盒2</span></td>
                        </tr>
                        <tr>
                            <td>质量管理组织职能框图</td>
                        </tr>
                        <tr>
                            <td>质管管理人员职责</td>
                        </tr>
                        <tr>
                            <td rowSpan="4"><span>3</span></td>
                            <td rowSpan="4"><span>*药品质量档案</span></td>
                            <td>药品质量档案表</td>
                            <td rowSpan="4"><span>档案盒3</span></td>
                        </tr>
                        <tr>
                            <td>药品质量标准</td>
                        </tr>
                        <tr>
                            <td>药品批准文号、注册证、药品包装、标签、说明书等药品</td>
                        </tr>
                        <tr>
                            <td>合法性相关证明文件</td>
                        </tr>
                        <tr>
                            <td rowSpan="3"><span>4</span></td>
                            <td rowSpan="3"><span>药品质量查询</span></td>
                            <td>药品质量查询表</td>
                            <td rowSpan="3"><span>档案盒4</span></td>
                        </tr>
                        <tr>
                            <td>药品投诉记录与处理结果记录</td>
                        </tr>
                        <tr>
                            <td>药品质量事故的调查、处理报告表</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>5</span></td>
                            <td rowSpan="2"><span>药品质量信息</span></td>
                            <td>药品法规、药品质量公告、通报等式相关文件、资料的采集</td>
                            <td rowSpan="2"><span>档案盒5</span></td>
                        </tr>
                        <tr>
                            <td>对照药品质量信息联系实际的分析报告</td>
                        </tr>
                        <tr>
                            <td rowSpan="5"><span>6</span></td>
                            <td rowSpan="5"><span>人员培训与继续教育</span></td>
                            <td>*年度培训计划一览表</td>
                            <td rowSpan="5"><span>档案盒6</span></td>
                        </tr>
                        <tr>
                            <td>培训教材、讲稿等材料</td>
                        </tr>
                        <tr>
                            <td>培训记录及培训人员登记记录</td>
                        </tr>
                        <tr>
                            <td>考试考核成绩记录资料等</td>
                        </tr>
                        <tr>
                            <td>人员继续教育记录资料</td>
                        </tr>
                        <tr>
                            <td rowSpan="5"><span>7</span></td>
                            <td rowSpan="5"><span>质量管理制度</span></td>
                            <td>药品质量管理制度汇编</td>
                            <td rowSpan="5"><span>档案盒7</span></td>
                        </tr>
                        <tr>
                            <td>药品质量管理制度的制定与批准执行文件</td>
                        </tr>
                        <tr>
                            <td>各相关人员岗位职责</td>
                        </tr>
                        <tr>
                            <td>各相关岗位操作程序</td>
                        </tr>
                        <tr>
                            <td>各项制度定期检查和考核制度</td>
                        </tr>
                        <tr>
                            <td><span>8</span></td>
                            <td><span>质量管理制度的考核</span></td>
                            <td>各项管理制度执行情况的定期检查和考核记录</td>
                            <td><span>档案盒8</span></td>
                        </tr>
                        <tr>
                            <td rowSpan="6"><span>9</span></td>
                            <td rowSpan="6"><span>企业人员档案</span></td>
                            <td>企业人员花名册</td>
                            <td rowSpan="6"><span>档案盒9</span></td>
                        </tr>
                        <tr>
                            <td>企业药品从业人员的学历证明（原件或复印件）</td>
                        </tr>
                        <tr>
                            <td>企业药品从业人员的职称证明（原件或复印件）</td>
                        </tr>
                        <tr>
                            <td>企业药品从业人员的上岗证（原件）</td>
                        </tr>
                        <tr>
                            <td>执业、从业、药师及质量负责人等技术人员的资格证书（原件）</td>
                        </tr>
                        <tr>
                            <td>药品从业人员在职在岗的劳动用工合同等（原件）</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>10</span></td>
                            <td rowSpan="2"><span>企业人员健康档案</span></td>
                            <td>企业人员体检情况汇总表</td>
                            <td rowSpan="2"><span>档案盒10</span></td>
                        </tr>
                        <tr>
                            <td>健康检查表</td>
                        </tr>
                        <tr>
                            <td rowSpan="4"><span>11</span></td>
                            <td rowSpan="4"><span>设备、设施档案</span></td>
                            <td>设备、设施一览表</td>
                            <td rowSpan="4"><span>档案盒11</span></td>
                        </tr>
                        <tr>
                            <td>设备使用说明书、合作证等</td>
                        </tr>
                        <tr>
                            <td>计量器具检定证书及合作证等</td>
                        </tr>
                        <tr>
                            <td>计量器管理记录表</td>
                        </tr>
                        <tr>
                            <td rowSpan="8"><span>12</span></td>
                            <td rowSpan="8"><span>*首营企业及合法供货企业资质</span></td>
                            <td>首营企业审批表</td>
                            <td rowSpan="8"><span>档案盒12</span></td>
                        </tr>
                        <tr>
                            <td>合格供货企业审查表</td>
                        </tr>
                        <tr>
                            <td>《药品经营企业许可证》或《药品生产企业许可证》（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td>《营业执照》（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td>销售单位法人委托书（原件）</td>
                        </tr>
                        <tr>
                            <td>销售人员身份证（复印件）、药品从业人员岗位证书（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td>质量保证协议或购货合同（原件）</td>
                        </tr>
                        <tr>
                            <td>GMP或GSP认证证书（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td rowSpan="6"><span>13</span></td>
                            <td rowSpan="6"><span>*首营品种档案资料</span></td>
                            <td>首营品种审批表</td>
                            <td rowSpan="6"><span>档案盒13</span></td>
                        </tr>
                        <tr>
                            <td>该药品的批准文号、注册证批准文件（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td>药品质量标准</td>
                        </tr>
                        <tr>
                            <td>同批号药品的检验报告书（加盖供货企业原印章）</td>
                        </tr>
                        <tr>
                            <td>药品包装、标签、说明书备案材料复印件</td>
                        </tr>
                        <tr>
                            <td>合法供货企业资质资料等</td>
                        </tr>
                        <tr>
                            <td><span>14</span></td>
                            <td><span>药品购进验收</span></td>
                            <td>药品购进（验收）记录</td>
                            <td><span>电脑档案</span></td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>15</span></td>
                            <td rowSpan="2"><span>药品购进票据</span></td>
                            <td>合法的票据</td>
                            <td rowSpan="2"><span>档案盒14</span></td>
                        </tr>
                        <tr>
                            <td>连锁门店可简化验收程序，验收人员按配送单对照实物核对，并在配送单上签字</td>
                        </tr>
                        <tr>
                            <td rowSpan="4"><span>16</span></td>
                            <td rowSpan="4"><span>*进口药品的购进验收</span></td>
                            <td>进口药品验收记录</td>
                            <td rowSpan="4"><span>档案盒15</span></td>
                        </tr>
                        <tr>
                            <td>进口药品注册证或医药产品注册证</td>
                        </tr>
                        <tr>
                            <td>进口药品检验报告书或进口药品通关单</td>
                        </tr>
                        <tr>
                            <td>生物制品批件，批发合格证及相关合法性资质材料</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>17</span></td>
                            <td rowSpan="2"><span>拆零药品管理</span></td>
                            <td>拆零药袋、拆零药品目录表</td>
                            <td rowSpan="2"><span>档案盒16</span></td>
                        </tr>
                        <tr>
                            <td>拆零药品销售记录</td>
                        </tr>
                        <tr>
                            <td rowSpan="5"><span>18</span></td>
                            <td rowSpan="5"><span>中药饮片管理</span></td>
                            <td>中药饮片合法供货企业资质（加盖供货企业原印章）</td>
                            <td rowSpan="5"><span>档案盒17</span></td>
                        </tr>
                        <tr>
                            <td>中药饮片购进（验收）记录（连锁门店验收程序 同第15项要求）</td>
                        </tr>
                        <tr>
                            <td>中药饮片质量养护记录</td>
                        </tr>
                        <tr>
                            <td>中药饮片装斗复核记录</td>
                        </tr>
                        <tr>
                            <td>中药饮片销售处方留存</td>
                        </tr>
                        <tr>
                            <td rowSpan="5"><span>19</span></td>
                            <td rowSpan="5"><span>不合格药品管理</span></td>
                            <td>不合格药品登记表</td>
                            <td rowSpan="5"><span>档案盒18</span></td>
                        </tr>
                        <tr>
                            <td>不合格药品确认表</td>
                        </tr>
                        <tr>
                            <td>不合格药品报告表</td>
                        </tr>
                        <tr>
                            <td>不合格药品报损审批表</td>
                        </tr>
                        <tr>
                            <td>不合格药品销毁记录</td>
                        </tr>
                        <tr>
                            <td><span>20</span></td>
                            <td><span>陈列药品质量检查</span></td>
                            <td>陈列药品按月检查记录</td>
                            <td rowSpan="3"><span>电脑档案</span></td>
                        </tr>
                        <tr>
                            <td><span>21</span></td>
                            <td><span>库存药品质量检查</span></td>
                            <td>库存药品按季检查记录</td>
                        </tr>
                        <tr>
                            <td><span>22</span></td>
                            <td><span>药品重点品种养护</span></td>
                            <td>药品重点品种养护检查记录表（易霉变、易潮解、效期短的品种）</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>23</span></td>
                            <td rowSpan="2"><span>仪器设备使用与养护</span></td>
                            <td>设备设施维修保养记录</td>
                            <td rowSpan="2"><span>档案盒19</span></td>
                        </tr>
                        <tr>
                            <td>设备设施使用记录</td>
                        </tr>
                        <tr>
                            <td><span>24</span></td>
                            <td><span>近效期药品催销</span></td>
                            <td>近效期药品催销报表</td>
                            <td><span>电脑档案</span></td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>25</span></td>
                            <td rowSpan="2"><span>药品的退出处理</span></td>
                            <td>购进药品退货记录</td>
                            <td rowSpan="2"><span>档案盒20</span></td>
                        </tr>
                        <tr>
                            <td>药品退出报告与处理</td>
                        </tr>
                        <tr>
                            <td><span>26</span></td>
                            <td><span>温湿度调控</span></td>
                            <td>药品陈列/储存的环境温湿度记录表（陈列于库存药品分开）</td>
                            <td><span>档案盒21</span></td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>27</span></td>
                            <td rowSpan="2"><span>药品销售管理</span></td>
                            <td>处方登记销售记录</td>
                            <td rowSpan="2"><span>档案盒22</span></td>
                        </tr>
                        <tr>
                            <td>处方留存</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>28</span></td>
                            <td rowSpan="2"><span>药品不良反应监测</span></td>
                            <td>药品不良反应/事件报告表</td>
                            <td rowSpan="2"><span>档案盒23</span></td>
                        </tr>
                        <tr>
                            <td>药品不良反应监测网络基层单位使用注册申请表</td>
                        </tr>
                        <tr>
                            <td rowSpan="2"><span>29</span></td>
                            <td rowSpan="2"><span>顾客意见记录</span></td>
                            <td>顾客意见簿</td>
                            <td rowSpan="2"><span>档案盒24</span></td>
                        </tr>
                        <tr>
                            <td>药品缺货记录</td>
                        </tr>
                    </tbody>
                </table>
                <p className="instructions">说明：*代表锁门店可不需建立的档案内容</p>
            </div>
            </div>
        );
    }
}

export default DocList;