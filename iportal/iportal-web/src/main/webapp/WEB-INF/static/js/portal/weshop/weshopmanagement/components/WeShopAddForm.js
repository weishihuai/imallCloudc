import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, Fields, reduxForm, change as changeFieldValue} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../common/validForm/actions";
import ValidForm from "../../../../common/validForm/components/ValidForm";
import {inputField} from "../../../../common/redux-form-ext";
import * as types from "../constants/ActionTypes";
import {DOUBLE_REG, LONG_REG} from "../../../../common/common-constant";
import FileMgrModalComponent from "../../../../common/filemgr/components/FileMgrModalComponent";
import {showFileMgrModalHasCallbackFunc} from "../../../../common/filemgr/actions";

const validate = (values, props) => {
    const errors = {};
    if(!values.shopNm){
        props.errorValidMessageFunction("请输入门店名称");
        errors.shopNm = "请输入门店名称";
        return errors;
    }
    if (values.shopNm.length > 32){
        props.errorValidMessageFunction("门店名称不能大于32个字符");
        errors.shopNm = "门店名称不能大于32个字符";
        return errors;
    }
    if(values.shopBrief && values.shopBrief.length > 140){
        props.errorValidMessageFunction("门店介绍不能大于140个字符");
        errors.shopBrief = "门店介绍不能大于140个字符";
        return errors;
    }
    if(!values.shopZone){
        props.errorValidMessageFunction("请选择门店区域");
        errors.shopZone = "请选择门店区域";
        return errors;
    }
    if(!values.detailLocation){
        props.errorValidMessageFunction("请输入详细位置");
        errors.detailLocation = "请输入详细位置";
        return errors;
    }
    if (values.detailLocation.length > 256){
        props.errorValidMessageFunction("详细位置不能大于256个字符");
        errors.detailLocation = "详细位置不能大于256个字符";
        return errors;
    }
    if(!values.shopLat){
        props.errorValidMessageFunction("请点击定位，定位门店地址");
        errors.shopLat = "请点击定位，定位门店地址";
        return errors;
    }
    if(!values.deliveryRange){
        props.errorValidMessageFunction("请输入配送范围");
        errors.deliveryRange = "请输入配送范围";
        return errors;
    }
    if(!LONG_REG.test(values.deliveryRange)){
        props.errorValidMessageFunction("请输入正确的配送范围");
        errors.deliveryRange = "请输入正确的配送范围";
        return errors;
    }
    if(!values.contactTel){
        props.errorValidMessageFunction("请输入联系电话");
        errors.contactTel = "请输入联系电话";
        return errors;
    }
    if(!values.shopMgrWeChatPict){
        props.errorValidMessageFunction("请上传店长微信二维码");
        errors.contactTel = "请上传店长微信二维码";
        return errors;
    }
    if(!values.shopMgrWeChatPict.sysFileLibId){
        props.errorValidMessageFunction("请上传店长微信二维码");
        errors.contactTel = "请上传店长微信二维码";
        return errors;
    }
    if(!values.shopLogoPict){
        props.errorValidMessageFunction("请上传门店头像");
        errors.contactTel = "请上传门店头像";
        return errors;
    }
    if(!values.shopLogoPict.sysFileLibId){
        props.errorValidMessageFunction("请上传门店头像");
        errors.contactTel = "请上传门店头像";
        return errors;
    }
    if(values.contactTel.length > 32){
        props.errorValidMessageFunction("联系电话不能大于32个字符");
        errors.contactTel = "联系电话不能大于32个字符";
        return errors;
    }
    if(values.shopPromiseSendTime && !/^[1-9]\d{0,9}$/.test(values.shopPromiseSendTime)){
        props.errorValidMessageFunction("请输入正确的送达时间");
        errors.shopPromiseSendTime = "请输入正确的送达时间";
        return errors;
    }
    if(!values.startHour){
        props.errorValidMessageFunction("请选择营业开始时间");
        errors.startHour = "请选择营业开始时间";
        return errors;
    }
    if(!values.startMinute){
        props.errorValidMessageFunction("请选择营业开始时间");
        errors.startMinute = "请选择营业开始时间";
        return errors;
    }
    props.changeFieldValue("weShopAddForm", "sellStartTime", values.startHour + ":" + values.startMinute);
    if(!values.endHour){
        props.errorValidMessageFunction("请选择营业结束时间");
        errors.endHour = "请选择营业结束时间";
        return errors;
    }
    if(!values.endMinute){
        props.errorValidMessageFunction("请选择营业结束时间");
        errors.endMinute = "请选择营业结束时间";
        return errors;
    }
    props.changeFieldValue("weShopAddForm", "sellEndTime", values.endHour + ":" + values.endMinute);
    if(!values.isNormalSales){
        props.errorValidMessageFunction("请选择是否正常营业");
        errors.isNormalSales = "请选择是否正常营业";
        return errors;
    }
    if(!values.deliveryTypeCode){
        props.errorValidMessageFunction("请选择配送费用");
        errors.deliveryTypeCode = "请选择配送费用";
        return errors;
    }
    if(values.deliveryTypeCode == 'NEED_PAY'){
        if(!DOUBLE_REG.test(values.deliveryAmount)){
            props.errorValidMessageFunction("请输入配送费");
            errors.deliveryAmount = "请输入配送费";
            return errors;
        }
    }else if(values.deliveryTypeCode == 'FULL_AMOUNT_NOT_PAY'){
        if(!DOUBLE_REG.test(values.deliveryAmount)){
            props.errorValidMessageFunction("请输入配送费");
            errors.deliveryAmount = "请输入配送费";
            return errors;
        }
        if(!DOUBLE_REG.test(values.deliveryMinOrderAmount)){
            props.errorValidMessageFunction("请输入满多少免配送费");
            errors.deliveryMinOrderAmount = "请输入满多少免配送费";
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const hiddenField = ({input, id, meta: { touched, error }}) => (
    <input name={input.name} id={id} type="hidden" {...input}/>
);

const businessFields = fields => (
    <div className="item">
        <p><i>*</i>营业时间：</p>
        <div className="business-time">
            <span>开始时间</span>
            <select className="select time-select" {...fields.startHour.input}>
                <option value="">请选择</option>
                {
                    count(23).map(i => {
                        return (
                            <option key={i} value={i}>{i}</option>
                        );
                    })
                }
            </select>
            <select className="select time-select" {...fields.startMinute.input}>
                <option value="">请选择</option>
                {
                    count(59).map(i => {
                        return(
                            <option key={i} value={i}>{i}</option>
                        );
                    })
                }
            </select>
            <span style={{marginLeft: "15px"}}>结束时间</span>
            <select className="select time-select" {...fields.endHour.input}>
                <option value="">请选择</option>
                {
                    count(23).map(i => {
                        return(
                            <option key={i} value={i}>{i}</option>
                        );
                    })
                }
            </select>
            <select style={{marginRight: "80px"}} className="select time-select" {...fields.endMinute.input}>
                <option value="">请选择</option>
                {
                    count(59).map(i => {
                        return(
                            <option key={i} value={i}>{i}</option>
                        );
                    })
                }
            </select>
            <label><input type="radio" {...fields.isNormalSales.input} value="Y" checked={fields.isNormalSales.input.value==='Y'}/>正常营业</label>
            <label><input type="radio" {...fields.isNormalSales.input} value="N" checked={fields.isNormalSales.input.value==='N'}/>暂停营业</label>
        </div>
    </div>
);

const deliveryTypeFields = fields => (
    <div className="item">
        <p><i>*</i>配送费用：</p>
        <div className="distribution-costs">
            <label><input {...fields.deliveryTypeCode.input} value="NEVER_PAY" type="radio" checked={fields.deliveryTypeCode.input.value == 'NEVER_PAY'}/>免配送费</label>
            <label><input {...fields.deliveryTypeCode.input} value="NEED_PAY" type="radio" checked={fields.deliveryTypeCode.input.value == 'NEED_PAY'}/>收费配送：配送费<input {...fields.deliveryAmount.input} disabled={fields.deliveryTypeCode.input.value == 'NEED_PAY' ? '' : 'disabled'}  type="text" placeholder="0元"/></label>
            <label><input {...fields.deliveryTypeCode.input} value="FULL_AMOUNT_NOT_PAY" type="radio" checked={fields.deliveryTypeCode.input.value == 'FULL_AMOUNT_NOT_PAY'}/>满额免费：配送费<input disabled={fields.deliveryTypeCode.input.value == 'FULL_AMOUNT_NOT_PAY' ? '' : 'disabled'} {...fields.deliveryAmount.input} type="text" placeholder="0元"/>&nbsp;&nbsp;满<input disabled={fields.deliveryTypeCode.input.value == 'FULL_AMOUNT_NOT_PAY' ? '' : 'disabled'} {...fields.deliveryMinOrderAmount.input} type="text" placeholder="0元"/>免费</label>
        </div>
    </div>
);

const count = count => {
    let arr = [];
    arr.push("00");
    for (let i = 1; i <= count; i++) {
         arr.push(i + "");
    }
    return arr;
};

const detailLocationField = ({ input, locatedEvent, meta: { touched, error } }) => (
    <div className="item">
        <p><i>*</i>详细位置：</p>
        <input name={input.name} id="detailLocation" type="text" {...input}/>
        <a title="为了定位更准确，请先选择门店区域，再定位" href="javascript:;" onClick={e => locatedEvent(e)} className="black-btn">定位</a>
        <div style={{top:"-250px", width: "55%", height:"650px", left: "auto", right:"80px"}} id="map-container" className="md-map"></div>
    </div>
);

const textareaField = ({ input, label, placeholder, className, id, required, maxLength, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <textarea {...input} maxLength={maxLength || ''} placeholder={placeholder}></textarea>
    </div>
);

const shopPromiseSendTimeField = ({ input, maxLength, meta: { touched, error } }) => (
    <div className="item">
        <p>商家承若：</p>
        <div className="store-promises">商家承若"尽快配送"将在<input id="shopPromiseSendTime" {...input} onFocus={e => {input.onFocus(e);unitOnFocus(input.value, 'shopPromiseSendTime');}} onBlur={e => {unitOnBlur(input, 'shopPromiseSendTime', e);}} type="text" placeholder="0小时"/>内送达<span>（提示：请根据您的配送能力填写，告诉用户送达所需时间，别让用户久等喔～）</span></div>
    </div>
);

const unitOnFocus = (value, id) =>{
  if(/^[1-9]\d{0,9}$/.test(value)){
      $("#" + id).val(value);
  }
};

const unitOnBlur = (input, id, e) =>{
    () => input.onBlur(e);
    if(/^[1-9]\d{0,9}$/.test(input.value)){
        $("#" + id).val(input.value + "小时");
    }
};

const zoneFields = (fields) => (
    <div className="item">
        <p><i>*</i>门店区域：</p>
        <div className="md-area">
            <select {...fields.provinceId.input} onChange={e => {fields.provinceChange(e); fields.provinceId.input.onChange(e);}} id="province" className="select zone-select">
                <option value="">请选择</option>
                {
                    fields.province.map((p, i) => {
                        return (
                            <option key={i} value={p.id}>{p.name}</option>
                        );
                    })
                }
            </select>
            <select {...fields.cityId.input} onChange={e => {fields.cityChange(e);fields.cityId.input.onChange(e);}} id="city" className="select zone-select">
                <option value="">请选择</option>
                {
                    fields.city.map((p, i) => {
                        return (
                            <option key={i} value={p.id}>{p.name}</option>
                        );
                    })
                }
            </select>
            <select {...fields.areaId.input}  onChange={e => {fields.areaChange(e);fields.areaId.input.onChange(e);}} id="area" className="select zone-select">
                <option value="">请选择</option>
                {
                    fields.area.map((p, i) => {
                        return (
                            <option key={i} value={p.id}>{p.name}</option>
                        );
                    })
                }
            </select>
        </div>
    </div>
);

class WeShopAddForm extends Component{

    componentWillMount(){
        const {change, actions} = this.props;
        const _this = this;
        _this.idDrag = false;
        actions.getDetail(data => {
            let startArr = data.sellStartTime.split(":");
            let endArr = data.sellEndTime.split(":");
            change("startHour", startArr[0]);
            change("startMinute", startArr[1]);
            change("endHour", endArr[0]);
            change("endMinute", endArr[1]);
            $("#shopPromiseSendTime").val(data.shopPromiseSendTime + "小时");
            if(_this.qqMap){
                if(_this.marker){
                    _this.marker.setMap(null);
                }
                let position = new qq.maps.LatLng(data.shopLat, data.shopLng);
                _this.marker = new qq.maps.Marker({map:_this.qqMap, position: position, draggable: true});
                _this.qqMap.setCenter(position);
                //覆盖物拖拽结束事件
                qq.maps.event.addListener(_this.marker, 'dragend', function() {
                    change("shopLat", _this.marker.getPosition().lat);
                    change("shopLng", _this.marker.getPosition().lng);
                    _this.isDrag = true;
                    _this.geocoder.getAddress(_this.marker.getPosition());
                });
            }
        });
        actions.loadZone(1, types.WE_SHOP_PROVINCE_DATA);
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
        document.body.appendChild(script);

        window.init = function () {
            let values = _this.context.store.getState().form.weShopAddForm.values;
            let center;
            if(values.shopLat && values.shopLng){
                center = new qq.maps.LatLng(values.shopLat, values.shopLng);
            }else {
                center = new qq.maps.LatLng(39.916527,116.397128);
            }
            let myOptions = {zoom: 15, center, mapTypeId: qq.maps.MapTypeId.ROADMAP};
            _this.qqMap = new qq.maps.Map(document.getElementById("map-container"), myOptions);
            _this.marker = new qq.maps.Marker({map:_this.qqMap, position: center, draggable: true});
            //覆盖物拖拽结束事件
            qq.maps.event.addListener(_this.marker, 'dragend', function() {
                change("shopLat", _this.marker.getPosition().lat);
                change("shopLng", _this.marker.getPosition().lng);
                _this.isDrag = true;
                _this.geocoder.getAddress(_this.marker.getPosition());
            });
            _this.geocoder = new qq.maps.Geocoder({
                complete : function(result){
                    let location = result.detail.location;
                    _this.qqMap.setCenter(location);
                    if(_this.marker){
                        _this.marker.setMap(null);
                    }
                   change("shopLat", location.lat);
                   change("shopLng", location.lng);
                    if(_this.isDrag){
                        change("detailLocation", result.detail.addressComponents.street);
                    }
                    _this.marker = new qq.maps.Marker({map:_this.qqMap, position: location, draggable: true});
                    //覆盖物拖拽结束事件
                    qq.maps.event.addListener(_this.marker, 'dragend', function() {
                        change("shopLat", _this.marker.getPosition().lat);
                        change("shopLng", _this.marker.getPosition().lng);
                        _this.isDrag = true;
                        _this.geocoder.getAddress(_this.marker.getPosition());
                    });
                }
            });
        }
    }

    componentDidMount(){
        $(".jSelect").jSelect();
        const _this = this;
        $("#detailLocation").bind("keydown", function (e) {
            if(e.keyCode == 13){
                e.preventDefault();
                _this.locatedEvent(e);
            }
        });
    }

    provinceChange(e){
        let val = $(e.target).val();
        if(val){
            this.props.actions.loadZone(val, types.WE_SHOP_CITY_DATA);
            $("#city").val("");
            $("#area").val("");
        }else {
            this.props.actions.setZoneData(types.WE_SHOP_CITY_DATA, []);
            this.props.actions.setZoneData(types.WE_SHOP_AREA_DATA, []);
            this.props.change("shopZone", "");
        }
    }

    cityChange(e){
        let val = $(e.target).val();
        if(val){
            this.props.actions.loadZone(val, types.WE_SHOP_AREA_DATA);
            $("#area").val("");
        }else {
            this.props.actions.setZoneData(types.WE_SHOP_AREA_DATA, []);
            this.props.change("shopZone", "");
        }
    }

    areaChange(e){
        let val = $(e.target).val();
        this.props.change("shopZone", val);
    }

    locatedEvent(e){
        let values = this.context.store.getState().form.weShopAddForm.values;
        if(values.detailLocation && values.shopZone){
            this.isDrag = false;
            let province = $("#province option:selected").html();
            let city = $("#city option:selected").html();
            let area = $("#area option:selected").html();
            let addr = province + "," + city + "," + area + ",";
           this.geocoder.getLocation(addr + values.detailLocation);
        }
    }

    showShopMgrWeChatUpload(){
        const {change, showFileMgrModalHasCallbackFunc} = this.props;
        showFileMgrModalHasCallbackFunc(data => {
            let pict = data.find(d => {
                return d.fileTypeCode == 'IMAGE';
            });
            if(!pict){
                alert("请选择图片文件");
                return false;
            }
            change("shopMgrWeChatPict", {fileTypeCode: "IMAGE", sysFileLibId: pict.sysFileLibId, fileId: pict.fileId, smallFileUrl: pict.smallFileUrl});
        });
    }

    showShopLogoUpload(){
        const {change, showFileMgrModalHasCallbackFunc} = this.props;
        showFileMgrModalHasCallbackFunc(data => {
            let pict = data.find(d => {
                return d.fileTypeCode == 'IMAGE';
            });
            if(!pict){
                alert("请选择图片文件");
                return false;
            }
            if(!pict){
                alert("请选择图片文件");
                return false;
            }
            change("shopLogoPict", {fileTypeCode: "IMAGE", sysFileLibId: pict.sysFileLibId, fileId: pict.fileId, smallFileUrl: pict.smallFileUrl});
        });
    }

    showShopPictUpload(){
        const {change, showFileMgrModalHasCallbackFunc} = this.props;
        const {store} = this.context;
        showFileMgrModalHasCallbackFunc(data => {
            let shopPict = store.getState().form.weShopAddForm.values.shopPictList || [];
            for (const i in data){
                let d = data[i];
                if(d.fileTypeCode == "IMAGE"){
                    shopPict.push({fileTypeCode: "IMAGE", sysFileLibId: d.sysFileLibId, fileId: d.fileId, smallFileUrl: d.smallFileUrl});
                    if (shopPict.length == 5){
                        break;
                    }
                }
            }
            change("shopPictList", shopPict);
        });
    }

    deleteShopPict(index){
        let shopPictList = this.context.store.getState().form.weShopAddForm.values.shopPictList;
        let temp = [];
        shopPictList.map((p, i) => {
            if(i != index){
                temp.push(p);
            }
        });
        this.props.change("shopPictList", temp);
    }

    render(){
        const {actions, handleSubmit, submitting, checkValidForm} = this.props;
        const {store} = this.context;
        const {province, city, area} = store.getState().todos;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const {fileMgrModalState} = store.getState().fileMgrTodos;
        const values = store.getState().form.weShopAddForm.values || {};
        const shopPictList = values.shopPictList || [];
        const len = 5 - shopPictList.length;
        let arr = [];
        for (let i = 0; i < len; i++){
            arr.push(i);
        }
        return(
            <form onSubmit={handleSubmit(data => actions.saveOrUpdate(data))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
                {fileMgrModalState && <FileMgrModalComponent/>}
              <div className="main-box wd-md-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>门店管理</h5>
                            <a href="javascript:;">微店管理</a>
                            <span>></span>
                            <a href="javascript:;">门店管理</a>
                        </div>
                    </div>
                </div>
                   <div className="layer-box layer-info layer-order">
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <Field id="id" name="id" component={hiddenField} />
                                <Field id="shopNm" name="shopNm" type="text" required="required" component={inputField} label="门店名称：" />
                                <Field id="shopBrief" name="shopBrief" placeholder="140字符以内" maxLength="140" component={textareaField} label="门店介绍：" />
                                <Fields names={["provinceId", "cityId", "areaId"]} provinceChange={(e) => this.provinceChange(e)} cityChange={(e) => this.cityChange(e)} areaChange={(e) => this.areaChange(e)} province={province} city={city} area={area} component={zoneFields}/>
                                <Field name="shopZone" id="shopZone" component={hiddenField}/>
                                <Field name="detailLocation" locatedEvent={e => this.locatedEvent(e)} component={detailLocationField}/>
                                <Field id="deliveryRange" name="deliveryRange" type="text" required="required" placeholder="单位：m" component={inputField} label="配送范围：" />
                                <Field id="contactTel" name="contactTel" type="text" required="required" component={inputField} label="联系电话：" />
                                <div className="item">
                                    <p><i>*</i>店长微信：</p>
                                    <div className="manager-wechat">
                                        <div className="item-pic-box"><img style={{width: "100px", height: "100px"}} src={values.shopMgrWeChatPict && values.shopMgrWeChatPict.smallFileUrl ? values.shopMgrWeChatPict.smallFileUrl : ''}/></div>
                                        <a onClick={() => this.showShopMgrWeChatUpload()} className="gray-btn" href="javascript:;">上传图片</a>
                                    </div>
                                </div>
                                <div className="item">
                                    <p><i>*</i>门店头像：</p>
                                    <div className="manager-wechat">
                                        <div className="item-pic-box">
                                            {
                                                values.shopLogoPict && values.shopLogoPict.smallFileUrl ? <img style={{width: "100px", height: "100px"}} src={values.shopLogoPict.smallFileUrl}/> : <span>100px*100px以上的图片</span>
                                            }
                                            </div>
                                        <a onClick={() => this.showShopLogoUpload()} className="gray-btn" href="javascript:;">上传图片</a>
                                    </div>
                                </div>
                                <Field  name="shopPromiseSendTime" component={shopPromiseSendTimeField} />
                                <div className="item">
                                    <p>门店图片：</p>
                                    <div className="md-pic">
                                        {
                                            shopPictList.map((p, i) => {
                                                return (
                                                    <div key={i} className="md-pic-li">
                                                        <div className="item-pic-box item-pic-box2"><img src={p.smallFileUrl}/></div>
                                                        <a onClick={() => this.deleteShopPict(i)} className="gray-btn" href="javascript:;">删除</a>
                                                    </div>
                                                );
                                            })
                                        }
                                        {
                                            arr.map(a => {
                                                return(
                                                    <div key={a} className="md-pic-li">
                                                        <div className="item-pic-box"></div>
                                                        <a onClick={() => this.showShopPictUpload()} className="gray-btn" href="javascript:;">上传图片</a>
                                                    </div>
                                                );
                                            })
                                        }
                                    </div>
                                </div>
                                <Field id="placardInf" name="placardInf" placeholder="200字符以内" maxLength="200" component={textareaField} label="公告信息：" />
                                <Fields names={["isNormalSales", "startHour", "startMinute", "endHour", "endMinute"]} component={businessFields}/>
                                <Fields names={["deliveryTypeCode", "deliveryAmount", "deliveryMinOrderAmount"]} component={deliveryTypeFields}/>
                            </div>
                        </div>
                    </div>
                       <div className="layer-footer">
                           <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                           {/*<a href="javascript:;" className="cancel" onClick={() => {}}>取消</a>*/}
                       </div>
                </div>
              </div>
            </form>
        );
    }
}

WeShopAddForm.propTypes = {
    actions: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

WeShopAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        initialValues: state.todos.detailData,
        state
    };
}

WeShopAddForm = reduxForm({
    form: "weShopAddForm",
    validate,
    enableReinitialize: true
})(WeShopAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
            checkValidForm, errorValidMessageFunction, changeFieldValue, showFileMgrModalHasCallbackFunc
        }, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopAddForm);