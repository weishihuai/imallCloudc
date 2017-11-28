import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import {REGEXP_DOUBLE_2} from "../../../../../../common/common-constant";
import {inputField, validate} from "../../../../../../common/redux-form-ext";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";

export const fields = [
    {
        field:'storageSpaceId',
        validate:{
            fieldNm: "货位",
            required:true
        }
    },
    {
        field:'monitorDateString',
        validate:{
            fieldNm: "日期",
            required:true
        }
    },
    {
        field:'recordMan',
        validate:{
            fieldNm: "记录人",
            required:true,
            maxlength:32
        }
    },
    {
        field:'monitorTime',
        validate:{
            fieldNm: "时间",
            required:true,
            maxlength:32
        }
    },
    {
        field:'temperature',
        validate:{
            fieldNm: "温度",
            regx: REGEXP_DOUBLE_2,
            required:true,
            maxlength:22
        }
    },
    {
        field:'moisture',
        validate:{
            fieldNm: "湿度",
            regx: REGEXP_DOUBLE_2,
            required:true,
            maxlength:22
        }
    },
    {
        field:'controlMeasure',
        validate:{
            fieldNm: "调控措施",
            maxlength:32
        }
    },
    {
        field:'timeAfterControl',
        validate:{
            fieldNm: "调控后时间",
            maxlength:32
        }
    },
    {
        field:'temperatureAfterControl',
        validate:{
            fieldNm: "调控后温度℃",
            regx: REGEXP_DOUBLE_2,
            maxlength:22
        }
    },
    {
        field:'moistureAfterControl',
        validate:{
            fieldNm: "调控后湿度%",
            regx: REGEXP_DOUBLE_2,
            maxlength:22
        }
    }
];

export const selectField = ({ input, label, id, required, items, optionValue, optionName, event, meta: { touched, error } }) => (
    <div className="item item-1-2" style={{'width': 180 + 'px', minHeight: 60 + 'px'}}>
        <p><i>*</i>{label}</p>
        <select id={id} name={input.name} className="select" style={{'width': 100 + '%'}} {...input} >
            <option value="">请选择</option>
            {
                items.map((item,index)=>{
                    return (
                        <option key={index} value={item[optionValue]}>{item[optionName]}</option>
                    )
                })
            }
        </select>
    </div>
);

class TemperatureMoistureMonitorRecordAddForm extends  Component {
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
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    let monitorDateString = $("#monitorDateString").val().trim();
                    change('monitorDateString', monitorDateString);
                }
            });
        });
    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    render() {
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm, actions} = this.props;
        const storageSpace = store.getState().todos.storageSpace||[];
        const {errorValidMessage, validFormState} = store.getState().validTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit} id="addForm">
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer" >
                    <div className="layer-box layer-info w960">
                        <div className="layer-header">
                            <span>新增温度湿度监控记录</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>{actions.showAddForm(false)}}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt"></div>
                                <div className="box-mc clearfix">
                                    <Field id="storageSpaceId" name="storageSpaceId"  type="text" component={selectField} label="货位" required="required" className="item" items={storageSpace} optionValue="id" optionName="storageSpaceNm"/>
                                    <Field id="monitorDateString" name="monitorDateString" type="text" component={inputField} label="日期" required="required" className="item" inputClassName="datepicker" readOnly/>
                                    <Field id="recordMan" name="recordMan"  type="text" component={inputField} label="记录人" required="required" className="item" maxLength="32"/>
                                    <Field id="monitorTime" name="monitorTime"  type="text" component={inputField} label="时间" required="required" className="item" maxLength="32"/>
                                    <Field id="temperature" name="temperature"  type="text" component={inputField} label="温度℃" required="required" className="item" maxLength="22"/>
                                    <Field id="moisture" name="moisture"  type="text" component={inputField} label="湿度%" required="required" className="item" maxLength="22"/>
                                    <Field id="controlMeasure" name="controlMeasure"  type="text" component={inputField} label="调控措施" className="item" maxLength="32"/>
                                    <Field id="timeAfterControl" name="timeAfterControl"  type="text" component={inputField} label="调控后时间" className="item" maxLength="32"/>
                                    <Field id="temperatureAfterControl" name="temperatureAfterControl"  type="text" component={inputField} label="调控后温度℃" className="item" maxLength="22"/>
                                    <Field id="moistureAfterControl" name="moistureAfterControl"  type="text" component={inputField} label="调控后湿度%" className="item" maxLength="22"/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.valid()}} disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>{actions.showAddForm(false)}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

TemperatureMoistureMonitorRecordAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

TemperatureMoistureMonitorRecordAddForm.contextTypes = {
    store: React.PropTypes.object
};
function mapDispatchToProps(dispatch){
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    }
}

TemperatureMoistureMonitorRecordAddForm = reduxForm({
    form: 'temperatureMoistureMonitorRecordAddForm',
    enableReinitialize: true
})(TemperatureMoistureMonitorRecordAddForm);

TemperatureMoistureMonitorRecordAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(TemperatureMoistureMonitorRecordAddForm);

export default TemperatureMoistureMonitorRecordAddForm;
