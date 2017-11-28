import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalOrgAddModal} from '../actions';
import {PORTAL_CHANGE_PROVINCE,PORTAL_CHANGE_CITY} from '../constants/ActionTypes';
import {validate} from '../../../../common/common-validate';

export const fields = ['parentId', 'orgName', 'orgCode', 'zoneCode' ,'address','phone','fax','orderby','isAvailable']


class OrgAddForm extends Component {
    provinceHandle(e,store){
        store.dispatch({type:PORTAL_CHANGE_PROVINCE ,provinceZoneCode:e.target.value});
    }
    cityHandle(e,store){
        store.dispatch({type:PORTAL_CHANGE_CITY,cityZoneCode:e.target.value});
    }
    render() {
        const { portalOrgAddModal,fields: {parentId,orgName, orgCode, zoneCode ,address,phone,fax,orderby,isAvailable}, resetForm, handleSubmit, submitting } = this.props;
        const {store} = this.context;
        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                <input  className="form-control" type="hidden" {...parentId}/>
                <div className="form-group">
                    <label className="control-label col-md-3">上级组织：</label>
                    <div className="col-md-6">
                        <input  className="form-control" type="text" value={store.getState().todos.pName} disabled/>
                    </div>
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">名称：</label>
                    <div className="col-md-6">
                        <input id="orgName" className="form-control required" type="text" data-max-length="128" placeholder="名称"  {...orgName}/>
                    </div>
                    {orgName.touched && orgName.error && <div className="form_hit">{orgName.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">组织编码：</label>
                    <div className="col-md-6">
                        <input id="orgCode" className="form-control required" type="text" data-max-length="9" placeholder="组织编码"  {...orgCode}/>
                    </div>
                    {orgCode.touched && orgCode.error && <div className="form_hit">{orgCode.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">所在区域：</label>
                    <div className="col-md-9">
                        <div className="col-md-3">
                            <select name="zoneCode" id="zoneCode" className="form-control" onChange={(e) => this.provinceHandle(e,store)}>
                                <option value="0">--请选择--</option>
                                {store.getState().todos.zones.map(zoneOption => <option value={zoneOption.zoneCode} key={zoneOption.zoneCode}>{zoneOption.zoneName}</option>)}
                            </select>
                        </div>
                        <div className="col-md-4">
                            <select name="city" id="city" className="form-control" onChange={(e) => this.cityHandle(e,store)}>
                                <option  value="0">--请选择--</option>
                                {store.getState().todos.cityZones.map(cityZone => <option value={cityZone.zoneCode} key={cityZone.zoneCode}>{cityZone.zoneName}</option>)}
                            </select>
                        </div>
                        <div className="col-md-4">
                            <select name="area" id="area" className="form-control"  {...zoneCode}>
                                <option>--请选择--</option>
                                {store.getState().todos.areaZones.map(areaZone => <option value={areaZone.zoneCode} key={areaZone.zoneCode}>{areaZone.zoneName}</option>)}
                            </select>
                        </div>
                    </div>
                    {zoneCode.touched && zoneCode.error && <div className="form_hit">{zoneCode.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">详细地址：</label>
                    <div className="col-md-6">
                        <input id="address" className="form-control" type="text" data-max-length="256" placeholder="hostname" {...address}/>
                    </div>
                    {address.touched && address.error && <div className="form_hit">{address.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">电话：</label>
                    <div className="col-md-6">
                        <input id="phone" className="form-control" type="text" data-max-length="32" placeholder="电话"  {...phone}/>
                    </div>
                    {phone.touched && phone.error && <div className="form_hit">{phone.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">fax：</label>
                    <div className="col-md-6">
                        <input id="fax" className="form-control" type="text" data-max-length="32" placeholder="传真号码"  {...fax}/>
                    </div>
                    {fax.touched && fax.error && <div className="form_hit">{fax.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">顺序(数字)：</label>
                    <div className="col-md-6">
                        <input id="orderby" className="form-control required" data-max-length="19" data-number-input type="text" placeholder="顺序"  {...orderby}/>
                    </div>
                    {orderby.touched && orderby.error && <div className="form_hit">{orderby.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">是否可用：</label>
                    <div className="col-md-6" id="isAvailable">
                        <label className="radio-inline">
                            <input type="radio" {...isAvailable} value="Y" checked={isAvailable.value === 'Y'}/> 可用
                        </label>
                        <label className="radio-inline">
                            <input type="radio" {...isAvailable} value="N" checked={isAvailable.value === 'N'}/> 不可用
                        </label>
                        {isAvailable.touched && isAvailable.error && <div className="form_hit">{isAvailable.error}</div>}
                    </div>
                </div>

                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalOrgAddModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}


OrgAddForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}
OrgAddForm.contextTypes = {
    store : React.PropTypes.object
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOrgAddModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        zones:state.todos.zones,
        cityZones:state.todos.cityZones,
        areaZones:state.todos.areaZones,
    }
}

OrgAddForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(OrgAddForm);

OrgAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(OrgAddForm);

export default OrgAddForm