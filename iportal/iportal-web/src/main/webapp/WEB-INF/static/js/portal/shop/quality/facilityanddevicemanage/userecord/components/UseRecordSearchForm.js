import React, {Component, PropTypes} from "react";

class UseRecordSearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount(){
        $("#sDeviceTypeCode").jSelect();
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let deviceTypeCode = $("#sDeviceTypeCode").val().trim();
        let deviceNum = $("#sDeviceNum").val().trim();
        let deviceNm = $("#sDeviceNm").val().trim();
        let operationMan = $("#sOperationMan").val().trim();
        params = Object.assign(params, {
            deviceTypeCode: deviceTypeCode, //设备类型代码
            deviceNum: deviceNum,           //设备编号
            deviceNm: deviceNm,             //设备名称
            operationMan: operationMan      //操作人
        });
        actions.useRecordSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#sDeviceTypeCode").val('');
        $('#sDeviceTypeCode-clear').text("类型");
        $(".select").jSelectReset();
        $("#sDeviceNum").val('');
        $("#sDeviceNm").val('');
        $("#sOperationMan").val('');
        params = Object.assign(params, {
            deviceTypeCode:"",  //设备类型代码
            deviceNum:"",       //设备编号
            deviceNm:"",        //设备名称
            operationMan:""     //操作人
        });
        actions.useRecordSearch(params);
    }

    render() {
        const deviceTypeCode = [
            {code: 'TEMPERATURE_AND_HUMIDITY_TESTING', name: '温湿度检测设备'},
            {code: 'REFRIGERATION', name: '冷藏设备'},
            {code: 'CHINESE_HERBAL_PIECES_DISPENSING', name: '中药饮片调配设备'},
            {code: 'PHARMACEUTICAL_DISMANTLE', name: '药品拆零设备'},
            {code: 'SPECIAL_FOR_DRUG_MANAGEMENT', name: '特殊管理药品专用设备'},
            {code: 'LIGHT_AVOIDING', name: '避光设备'},
            {code: 'VENTILATION', name: '通风设备'},
            {code: 'FIRE_FIGHTING', name: '消防设备'},
            {code: 'LIGHTING', name: '照明设备'},
            {code: 'ACCEPTANCE_MAINTENANCE', name: '验收养护设备'}
        ];

        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="sDeviceTypeCode" className="select allSelect3">
                        <option value=''>类型</option>
                        {
                            deviceTypeCode.map((item, index)=>{
                                return(
                                    <option value={item.code} key={index}>{item.name}</option>
                                );
                            })
                        }
                    </select>
                </div>
                <div className="search">
                    <input placeholder="设备编号" type="text" id="sDeviceNum"/>
                </div>
                <div className="search">
                    <input placeholder="设备名称" type="text" id="sDeviceNm"/>
                </div>
                <div className="search">
                    <input placeholder="使用人" type="text" id="sOperationMan"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        );
    }
}

UseRecordSearchForm.prpoTypes = {
    actions: PropTypes.object.isRequired
};

UseRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default UseRecordSearchForm;