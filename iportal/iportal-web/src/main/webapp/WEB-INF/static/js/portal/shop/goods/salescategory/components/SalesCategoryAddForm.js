import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {boolRadioField, inputField} from "./FieldComponent";
import {checkValidForm, errorValidMessageFunction, asyncErrorValidMessageFunction} from "../../../../../common/validForm/actions";
import {LONG_REG} from "../../../../../common/common-constant";
import {validate} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import FileMgrModalComponent from '../../../../../common/filemgr/components/FileMgrModalComponent';
import {showFileMgrModalHasCallbackFunc} from '../../../../../common/filemgr/actions';
import {niftyNoty} from "../../../../../common/common";

import {checkSalesCategory} from '../actions/index';

export const fields = [
    {
        field:'categoryName',
        validate:{
            fieldNm: "分类名称",
            required:true,
            maxlength:64
        }
    },
    {
        field:'dispalyPosition',
        validate:{
            fieldNm: "排序",
            required:true,
            regx: LONG_REG
        }
    },
    {
        field:'isFrontendShow',
        validate:{
            fieldNm: "是否前台展示",
            required:true
        }
    },
    {
        field:'isEnable',
        validate:{
            fieldNm: "是否启用",
            required:true
        }
    }
];

class SalesCategoryAddForm extends Component {
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        change("isFrontendShow", 'Y');
        change("isEnable", 'Y');
    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    showFileModal(){
        this.props.showFileMgrModalHasCallbackFunc((files)=>{
            if(files===undefined || files.length===0){
                return;
            }

            if(files.length>1){
                niftyNoty("只能选择一个文件");
            }else{
                const file = files[0];
                if (file.fileTypeCode !== "IMAGE") {
                    niftyNoty("只能上传图片");
                }else {
                    const {change} = this.props;
                    $("#pictFileId").val(file.fileId);
                    $("#sysFileLibId").val(file.sysFileLibId);
                    change("pictFileId", file.fileId);
                    change("sysFileLibId", file.sysFileLibId);
                    $("#picReview").attr('src', file.fileUrl);
                    $("#defaultReview").hide();
                    $("#picReview").show();
                    $("#removeImg").show();
                }
            }
        });
    }

    removeImg(){
        const {change} = this.props;
        $("#pictFileId").val("");
        $("#sysFileLibId").val("");
        change("pictFileId", "");
        change("sysFileLibId", "");
        $("#picReview").attr('src', "");
        $("#defaultReview").show();
        $("#picReview").hide();
        $("#removeImg").hide();
    }

    showAddForm(){
        const {actions} = this.props;
        this.props.asyncErrorValidMessageFunction("");
        actions.showCategoryAddForm(false)
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, asyncValidMessage, validFormState} = this.context.store.getState().validTodos;
        const fileMgrModalState = store.getState().fileMgrTodos.fileMgrModalState;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {fileMgrModalState && <FileMgrModalComponent store={store} actions={actions}/>}
                {validFormState && (errorValidMessage!=="" || asyncValidMessage!=="") && <ValidForm  checkValidForm={checkValidForm}/>}
                <input name="pictFileId" id="pictFileId" type="hidden"/>
                <input name="sysFileLibId" id="sysFileLibId" type="hidden"/>
                <div className="layer" id="salesCategoryAddForm">
                    <div className="layer-box layer-class w430">
                        <div className="layer-header">
                            <span>添加销售分类</span>
                            <a href="javascript:void(0);" className="close" onClick={this.showAddForm.bind(this)}/>
                        </div>
                        <div className="layer-body">
                            <Field id="categoryName" name="categoryName" type="text" component={inputField} label="分类名称" required="required"/>
                            <div className="item">
                                <div className="mlt">图标：</div>
                                <div className="mrt">
                                    <div className="pic">
                                        <a href="javascript:void(0);" id="defaultReview" onClick={()=>this.showFileModal()}>200*200</a>
                                        <img id="picReview" style={{display: "none", width: 50 + 'px', height: 50 + 'px'}} onClick={()=>this.showFileModal()}/>
                                        <em className="sales-category-em" id="removeImg" onClick={()=>this.removeImg()}/>
                                    </div>
                                </div>
                            </div>
                            <Field id="dispalyPosition" name="dispalyPosition" type="text" component={inputField} label="排序" required="required" />
                            <Field id="isFrontendShow" name="isFrontendShow" type="text" component={boolRadioField} label="是否前台展示" required="required"/>
                            <Field id="isEnable" name="isEnable" type="text" component={boolRadioField} label="是否启用" required="required"/>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.valid()}} disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={this.showAddForm.bind(this)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

SalesCategoryAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

SalesCategoryAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        asyncErrorValidMessageFunction,
        showFileMgrModalHasCallbackFunc
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        asyncBlurFields: ['categoryName'],
        asyncValidate: checkSalesCategory,
        state
    }
}

SalesCategoryAddForm = reduxForm({
    form: 'salesCategoryAddForm',
    enableReinitialize: true
})(SalesCategoryAddForm);

SalesCategoryAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SalesCategoryAddForm);


export default SalesCategoryAddForm;