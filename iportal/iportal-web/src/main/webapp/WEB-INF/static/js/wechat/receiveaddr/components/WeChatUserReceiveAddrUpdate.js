import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {validate, inputField, textareaField, hiddenField} from "../../../common/redux-form-ext-frontend";
import {selectZone, updateReceiveAddr, getReceiveAddr} from "../actions/index";
import {WEB_NAME} from "../../../common/common-constant";

const fields = [
    {
        field: 'id',
        validate: {
            required: true,
            fieldNm: '主键ID'
        }
    },
    {
        field: 'receiverName',
        validate: {
            required: true,
            fieldNm: '收货人',
            maxlength: 32
        }
    },
    {
        field: 'contactTel',
        validate: {
            required: true,
            fieldNm: '电话',
            maxlength: 32
        }
    },
    {
        field: 'deliveryAddr',
        validate: {
            required: true,
            fieldNm: '地区',
            maxlength: 64
        }
    },
    {
        field: 'detailAddr',
        validate: {
            required: true,
            fieldNm: '详细地址',
            maxlength: 128
        }
    }
];

class WeChatUserReceiveAddrUpdate extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "编辑收货地址";
        const {getReceiveAddr, params} = this.props;
        getReceiveAddr(params.id);
        const {change, selectZone} = this.props;
        selectZone(false);
        let oldViewportContent = document.getElementsByTagName('meta')['viewport'].content;
        window.addEventListener('message', function(event) {
            // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
            var loc = event.data;
            if (loc && loc.module === 'locationPicker') {//防止其他应用也会向该页面post信息，需判断module是否为'locationPicker'
                change("deliveryAddr", loc.poiaddress);
                change("addrLat", loc.latlng.lat);
                change("addrLng", loc.latlng.lng);
                change("positionName", loc.poiname);
                change("cityName", loc.cityname);
                selectZone(false);
                if(oldViewportContent) {
                    document.getElementsByTagName('meta')['viewport'].setAttribute("content",oldViewportContent);
                }
            }
        }, false);
    }

    submit(data) {
        return this.props.updateReceiveAddr(data);
    }

    showErrorMessage(){
        const {store} = this.context;
        const syncErrors = store.getState().form.weChatUserReceiveAddrUpdate.syncErrors || {};
        if(syncErrors !== null){
            for(const i in syncErrors){
                showError(syncErrors[i]);
            }
        }
    }

    componentWillUnmount() {
        if(this.oldViewportContent) {
            document.getElementsByTagName('meta')['viewport'].setAttribute("content",this.oldViewportContent);
        }
    }

    selectZone() {
        const {selectZone} = this.props;
        let viewport = document.getElementsByTagName('meta')['viewport'];
        this.oldViewportContent = viewport.content;
        viewport.setAttribute("content","width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no");
        selectZone(true);
    }

    render(){
        const {selectZone, handleSubmit, submitting} = this.props;
        const {store} = this.context;
        const {showSelectZone} = store.getState().wechatUserReceiveAddrTodos;
        const selectAddr = store.getState().form.weChatUserReceiveAddrUpdate.values.deliveryAddr || '';
        return(
            <div>
                {showSelectZone &&
                <iframe id="mapPage" width="100%" height="100%" frameBorder="0" src="http://apis.map.qq.com/tools/locpicker?policy=1&search=1&type=1&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp">
                </iframe>}
                {!showSelectZone &&
                <div className="add-address" style={{paddingTop:"0px",marginBottom: "-11rem"}}>
                    <form onSubmit={handleSubmit(this.submit.bind(this))}>
                        <Field id="id" name="id" component={hiddenField}/>
                        <Field id="addrLat" name="addrLat" component={hiddenField}/>
                        <Field id="addrLng" name="addrLng" component={hiddenField}/>
                        <Field id="id" name="id" component={hiddenField}/>
                        <Field id="positionName" name="positionName" component={hiddenField}/>
                        <Field id="cityName" name="cityName" component={hiddenField}/>
                        <Field id="receiverName" name="receiverName" label="收货人" placeholder="收货人姓名" type="text" component={inputField}/>
                        <Field id="contactTel" name="contactTel" label="电话" placeholder="收货人电话" maxLength="32" type="number" component={inputField}/>
                        <Field id="deliveryAddr" name="deliveryAddr" type="hidden" component={hiddenField}/>
                        <div className="region">
                            <a onClick={this.selectZone.bind(this)} href="javascript:void(0);"><span>地区</span><i>{selectAddr}</i></a>
                            <em/>
                        </div>
                        <Field id="detailAddr" name="detailAddr" placeholder="详细地址" rows="3" cols="20" component={textareaField}/>
                        <div className="submit">
                            <label><input disabled={submitting} type="submit" value="确定" onClick={()=>{this.showErrorMessage()}}/></label></div>
                    </form>
                </div>
                }
            </div>
        );
    }
}

WeChatUserReceiveAddrUpdate.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

WeChatUserReceiveAddrUpdate.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        initialValues: state.wechatUserReceiveAddrTodos.receiveAddrDetail,
        state,
        fields
    };
}

WeChatUserReceiveAddrUpdate = reduxForm({
    form: "weChatUserReceiveAddrUpdate",
    enableReinitialize: true,
    validate
})(WeChatUserReceiveAddrUpdate);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({selectZone, updateReceiveAddr, getReceiveAddr}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatUserReceiveAddrUpdate);