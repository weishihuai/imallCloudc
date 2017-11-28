import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import {REGEXP_SIGNLESS_INT, REGEXP_PHONE, ERROR_PHONE_FORMAT, REGEXP_ID_CARD, ERROR_ID_CARD} from "../../../../../../common/common-constant";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import CommonOrderList from "../../../../../../common/orderselectwin/components/CommonOrderList";
import {showOrderSelectWin} from "../../../../../../common/orderselectwin/actions";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';
import FileMgrModalComponent from '../../../../../../common/filemgr/components/FileMgrModalComponent';
import {showFileMgrModalHasCallbackFunc} from '../../../../../../common/filemgr/actions';

import {selectField, clickInputField} from './FieldComponent';

export const fields = [
    {
        field:'prescriptionSellOrderCode',
        validate:{
            fieldNm: "处方订单信息",
            maxlength:32,
            required:true
        }
    },
    {
        field:'prescriptionDateString',
        validate:{
            fieldNm: "处方日期",
            required:true
        }
    },
    {
        field:'medicalOrg',
        validate:{
            fieldNm: "医疗机构",
            maxlength:64,
            required:true
        }
    },
    {
        field:'doctorName',
        validate:{
            fieldNm: "医师姓名",
            maxlength:32
        }
    },
    {
        field:'clinicDiagnosis',
        validate:{
            fieldNm: "临床诊断",
            maxlength:128
        }
    },
    {
        field:'doctorsAdvice',
        validate:{
            fieldNm: "医嘱",
            maxlength:128
        }
    },
    {
        field:'patientNm',
        validate:{
            fieldNm: "患者名称",
            maxlength:32,
            required:true
        }
    },
    {
        field:'age',
        validate:{
            fieldNm: "年龄",
            regx: REGEXP_SIGNLESS_INT,
            error: "年龄格式错误"
        }
    },
    {
        field:'addr',
        validate:{
            fieldNm: "地址",
            maxlength:128
        }
    },
    {
        field:'mobile',
        validate:{
            fieldNm: "手机",
            maxlength:32,
            regx: REGEXP_PHONE,
            error: ERROR_PHONE_FORMAT
        }
    },
    {
        field:'remark',
        validate:{
            fieldNm: "备注",
            maxlength:256
        }
    }
];

class PrescriptionRegisterAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    let registerDateString = $("#prescriptionDateString").val().trim();
                    change('prescriptionDateString', registerDateString);
                }
            });
        });
    }

    valid(data){
        let certTypeCode = $("#certTypeCode").val().trim();
        if(certTypeCode!==''){
            let certNum = $("#certNum").val().trim();
            if(certNum===''){
                this.props.errorValidMessageFunction("请输入证件号");
                return;
            }else{
                if(certTypeCode==='IDENTIFICATION' && !REGEXP_ID_CARD.test(certNum)){
                    this.props.errorValidMessageFunction(ERROR_ID_CARD);
                    return;
                }
            }
        }

        let approveManId = $("#approveManId").val().trim();
        if(approveManId===''){
            this.props.errorValidMessageFunction("请进行审核");
            return;
        }
        this.props.errorValidMessageFunction("");

        const fileVoList = [];
        if($(".sysFileLibId")) {
            const sysFileLibIds = $(".sysFileLibId");
            const fileIds = $(".fileId");
            for(let i=0; i<sysFileLibIds.length; i++){
                fileVoList.push({sysFileLibId: $(sysFileLibIds[i]).val(), fileId: $(fileIds[i]).val()});
            }
        }

        const {actions} = this.props;
        const {store} = this.context;
        const newData = Object.assign({}, data, {
            fileVoList: fileVoList
        });

        return actions.saveOrUpdate(newData, actions, store.getState().prescriptionTodos.params);
    }

    openOrderSelectWin(){
        this.props.showOrderSelectWin('', 'Y');
    }

    selectOrderCallBack(order){
        $("#orderId").val(order.id);
        $("#prescriptionSellOrderCode").val(order.orderNum);
        $("#memberCardNum").val(order.memberCardNum);

        const {change} = this.props;
        change('orderId', order.id);
        change('prescriptionSellOrderCode', order.orderNum);
        change('memberCardNum', order.memberCardNum);

        this.props.actions.queryOrderItem(order);
    }

    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!=undefined && json!=null){
            const {change} = this.props;
            $("#approveManId").val(json.id);
            change('approveManId', json.id);
            $("#reviewBtn").hide();
            $("#realName").text(json.realName);
            $("#realName").show();
        }
    }

    certTypeChange(){
        if($("#certTypeCode").val()===''){
            const {change} = this.props;
            $("#certNum").val('');
            change('certNum', '');
        }
    }

    showFileModal(){
        this.props.showFileMgrModalHasCallbackFunc((files)=>{
            const {store} = this.context;
            const oldFiles = store.getState().prescriptionTodos.files;
            if(oldFiles){
                files.map((newFile)=>{
                    if(!oldFiles.find((file)=>file.fileId===newFile.fileId)){
                        oldFiles.push(newFile);
                    }
                });
                this.props.actions.showFiles(oldFiles);
            }else{
                this.props.actions.showFiles(files);
            }
        });
    }

    removeFile(fileId){
        const {store} = this.context;
        const files = store.getState().prescriptionTodos.files;
        const newFiles = files.filter((file)=> file.fileId !== fileId);
        this.props.actions.showFiles(newFiles);
    }

    downloadFile(fileLibId){
        window.open(iportal + '/sysFileLib/downloadFile.json?id=' + fileLibId);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {isShowOrderWin} = this.context.store.getState().orderTodos;
        const {display} = this.context.store.getState().approveValidateTodos;
        let sexCode = [{code: 'MALE', name : '男'}, {code: 'FEMALE', name : '女'}, {code: 'SECRET', name : '保密'}];
        let certTypeCode = [{code: 'IDENTIFICATION', name : '身份证'}, {code: 'CERTIFICATE_OF_OFFICERS', name : '军官证'}, {code: 'PASSPORT', name : '护照'}];
        const items = store.getState().prescriptionTodos.items || [];
        const files = store.getState().prescriptionTodos.files || [];
        const fileMgrModalState = store.getState().fileMgrTodos.fileMgrModalState;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {fileMgrModalState && <FileMgrModalComponent store={store} actions={actions}/>}
                {isShowOrderWin && <CommonOrderList callback={(order)=>this.selectOrderCallBack(order)} actions={actions} isSingle={true}/>}
                {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
                <input type="hidden" name="orderId" id="orderId"/>
                <input type="hidden" name="approveManId" id="approveManId"/>
                <div className="layer">
                    <div className="layer-box layer-info layer-receiving w960">
                        <div className="layer-header">
                            <span>新增处方登记</span>
                            <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showAddForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">处方信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="prescriptionSellOrderCode" id="prescriptionSellOrderCode"  type="text" component={clickInputField} label="处方销售单号" required="required" readOnly="readOnly" maxLength="32" onClick={this.openOrderSelectWin.bind(this)} />
                                    <Field name="prescriptionDateString" id="prescriptionDateString"  type="text" component={inputField} label="处方日期" required="required" inputClassName="datepicker" readOnly="readOnly" />
                                    <Field name="medicalOrg"  type="text" component={inputField} label="医疗机构" required="required" maxLength="64"/>
                                    <Field name="doctorName"  type="text" component={inputField} label="医师姓名" maxLength="32"/>
                                    <Field name="clinicDiagnosis"  type="text" component={inputField} label="临床诊断" maxLength="128"/>
                                    <Field name="doctorsAdvice"  type="text" component={inputField} label="医嘱" maxLength="128"/>
                                    <Field name="memberCardNum" id="memberCardNum"  type="text" component={inputField} label="会员" readOnly="readOnly" />
                                    <Field name="patientNm"  type="text" component={inputField} label="患者名称" required="required" maxLength="32"/>
                                    <Field name="sexTypeCode"  type="text" component={selectField} label="性别" optionValue="code" optionName="name" items={sexCode}/>
                                    <Field name="age"  type="text" component={inputField} label="年龄"/>
                                    <Field name="certTypeCode" id="certTypeCode" type="text" component={selectField} label="证件类型" optionValue="code" optionName="name" items={certTypeCode} onChange={()=>this.certTypeChange()}/>
                                    <Field name="certNum" id="certNum" type="text" component={inputField} label="证件号码" maxLength="64"/>
                                    <Field name="mobile"  type="text" component={inputField} label="手机" maxLength="32"/>
                                    <Field name="addr"  type="text" component={inputField} label="地址" maxLength="128"/>
                                    <Field name="remark"  type="text" component={inputField} label="备注" maxLength="256"/>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel()} id="reviewBtn">点击审核</a>
                                        <p id="realName" style={{display: 'none'}}/>
                                    </div>
                                    <div className="item">
                                        <p>处方附件</p>
                                        <a href="javascript:void(0);" className="gray-btn uploading" onClick={this.showFileModal.bind(this)}>上传附件</a>
                                    </div>
                                    <div className="item-upload">
                                        {
                                            files.length>0 &&
                                            files.map((file)=>{
                                                {
                                                    return(
                                                        <div className="upload-operation" key={file.fileId}>
                                                            {file.fileTypeCode==="IMAGE" && <div><img src={file.fileUrl} alt={file.fileNm}/><em onClick={()=>this.removeFile(file.fileId)}/></div>}
                                                            {file.fileTypeCode!=="IMAGE" && <div><a href="javascript:void(0);" target="_blank" onClick={()=>this.downloadFile(file.sysFileLibId)}>{file.fileNm}</a><em onClick={()=>this.removeFile(file.fileId)}/></div>}
                                                            <input type="hidden" className="sysFileLibId" value={file.sysFileLibId}/>
                                                            <input type="hidden" className="fileId" value={file.fileId}/>
                                                        </div>
                                                    );
                                                }
                                            })
                                        }
                                    </div>
                                </div>
                            </div>
                            {
                                items.length>0 &&
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
                                                items.map((item, index)=>{
                                                    return(
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
                                                    );
                                                })
                                            }
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            }
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {checkValidForm(true)}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.showAddForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

PrescriptionRegisterAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

PrescriptionRegisterAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showOrderSelectWin,
        showValidateModel,
        showFileMgrModalHasCallbackFunc
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    }
}

PrescriptionRegisterAddForm = reduxForm({
    form: 'prescriptionRegisterAddForm'
})(PrescriptionRegisterAddForm);

PrescriptionRegisterAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(PrescriptionRegisterAddForm);

export default PrescriptionRegisterAddForm;