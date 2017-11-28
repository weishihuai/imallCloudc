import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {
    asyncErrorValidMessageFunction,
    checkValidForm,
    errorValidMessageFunction
} from "../../../../../common/validForm/actions";
import {validate} from "../../../../../common/redux-form-ext";
import {asyncValidateForSaveOrUpdate} from "../actions/asyncValidate";

const fields = [{
        field: 'storageSpaceNm',
        validate: {
            required: true,
            maxlength: 32,
            fieldNm: "货位名称",
            }
    }, {
        field: 'storageSpaceType',
        validate: {
            required: true,
            maxlength: 32,
            fieldNm: "货位类型",
        }
    }, {
        field: 'isEnable',
        validate: {
            required: true,
            maxlength: 1,
            fieldNm: "货位状态",
        }
    }
];

export const boolRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div>
        <label>
            <input type="radio" {...input} value="Y" checked={input.value === 'Y'} style={{width:"13px",height:"13px"}}/> 启用
        </label>
        <label>
            <input type="radio" {...input} value="N" checked={input.value === 'N'} style={{width:"13px",height:"13px"}}/> 未启用
        </label>
    </div>
);

export const storageSpaceTypeField = ({ input, label, type, id, required, templateTypeList, meta: { touched, error } }) => (
    <div className="item">
        <div className="mlt"><span >{required ? '*' : ''}</span>{label}{label ? '：' : ''}</div>
        <div className="mrt">
            <select id={id} name={input.name} {...input} style={{width: "180px", height: "30px", marginTop: "5px",border:"1px solid #e5e5e5",color:"#333"}}>
                <option value="">请选择</option>
                <option value="GOODS_STORAGE_SPACE">商品货位</option>
                <option value="CHINESE_HERBAL_MEDICINE">中药饮片</option>
                <option value="MEDICAL_APPARATUS_INSTRUMENTS">医疗器械</option>
            </select>
        </div>
    </div>
);

export const inputField = ({ input, label, type, id, required, isShow, meta: { touched, error } }) => (
    <div className="item" style={{'display': isShow==='false' ? 'none' : 'block'}}>
        <div className="mlt"><span >{required ? '*' : ''}</span>{label}{label ? '：' : ''}</div>
        <div className="mrt">
            <input id={id} name={input.name} className={"form-control " + required} type={type}  {...input}/>
        </div>
    </div>
);

class StorageSpaceAddForm extends Component {

    constructor(props) {
        super(props);
    }

    componentDidUpdate() {
    }

    componentDidMount() {

    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting } = this.props;
        const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const validState = asyncValidMessage !== "" || errorValidMessage !== "";

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer" >
                    <div className="layer-box layer-verify w430">
                        <div className="layer-header">
                            <span>添加货位信息</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.storageSpaceAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <Field id="storageSpaceNm" name="storageSpaceNm" type="text" component={inputField} label="货位名称" required="required"  maxLength="32"/>
                            <Field id="storageSpaceTypeAdd" name="storageSpaceType" type="text" component={storageSpaceTypeField} label="货位类型" required="required"/>
                            <div className="item">
                                <div className="mlt"><span>*</span>状态：</div>
                                <div className="mrt">
                                    <Field id="isEnable" name="isEnable" type="text" component={boolRadioField} label="" required="required" />
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={(e) => actions.storageSpaceAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

StorageSpaceAddForm.contextTypes = {
    store: React.PropTypes.object
};

StorageSpaceAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

StorageSpaceAddForm = reduxForm({
    form: "storageSpaceAddForm",
    enableReinitialize: true,
    validate,
    asyncBlurFields: ['storageSpaceNm'],
    asyncValidate: asyncValidateForSaveOrUpdate
})(StorageSpaceAddForm);


function mapStateToProps(state) {
    return {
        initialValues: {
            isEnable:"Y"
        },
        fields: fields,
        validate:validate,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StorageSpaceAddForm);