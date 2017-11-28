import React, { PropTypes, Component } from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {showArticleCategoryModal} from '../actions';
import * as types from "../constants/ActionTypes";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
class GoodsDocSelectList extends Component {

    constructor(props){
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        this.props.actions.queryGoodsDocList(0, 10, store.getState().todos.docParams);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        this.props.actions.queryGoodsDocList(0, sizePerPage,store.getState().todos.docParams);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        this.props.actions.queryGoodsDocList(page - 1, sizePerPage,store.getState().todos.docParams,);
    }


    search() {
        const {store} = this.context;
        let {params} = store.getState().todos;
        params.docGoodsNm= $('#docGoodsNm').val();
        params.docApproveNumber= $('#docApproveNumber').val();
        params.docProduceManufacturer= $('#docProduceManufacturer').val();

        this.props.actions.queryGoodsDocList(0, params.size,store.getState().todos.params);
        this.props.actions.setDocSearchParam(params);

    }

    reset(){
        $('#docGoodsNm').val('');
        $('#docApproveNumber').val('');
        $('#docProduceManufacturer').val('');
        let params ={
            page:0,
            size:10,
            docGoodsNm:"",                    //商品名称
            docApproveNumber:"",              //批准文号
            docProduceManufacturer:"",        //生产厂家
        };
        this.props.actions.setDocSearchParam(params);
    }

    onRowSelect(id,store){
        store.dispatch({
            type:types.GOODS_DOC_SELECT_LIST_SELECTED_ID,
            data:id
        })
    }
    selectConfirm(){
        const {store} = this.context;
        const {selectedId} = store.getState().todos;
        this.props.actions.getGoodsDocDetail(selectedId,() => {
            const {store} = this.context;
            const {goodsDocDetail} = store.getState().todos;
            let formData  = store.getState().form.goodsAddForm.values;

            let newFormData = Object.assign({},formData,{
                goodsDocId:goodsDocDetail.id?goodsDocDetail.id:"",
                goodsNm:goodsDocDetail.goodsNm?goodsDocDetail.goodsNm:"",
                goodsTypeCode:goodsDocDetail.goodsTypeCode?goodsDocDetail.goodsTypeCode:"",
                produceManufacturer:goodsDocDetail.produceManufacturer?goodsDocDetail.produceManufacturer:"",
                commonNm:goodsDocDetail.commonNm?goodsDocDetail.commonNm:"",
                spec:goodsDocDetail.spec?goodsDocDetail.spec:"",
                unit:goodsDocDetail.unit?goodsDocDetail.unit:"",
                brandNm:goodsDocDetail.brandNm?goodsDocDetail.brandNm:"",
                barCode:goodsDocDetail.barCode?goodsDocDetail.barCode:"",
                toxicologyCode:goodsDocDetail.toxicologyCode?goodsDocDetail.toxicologyCode:"",
                storageCondition:goodsDocDetail.storageCondition?goodsDocDetail.storageCondition:"",
                approvalNumber:goodsDocDetail.approvalNumber?goodsDocDetail.approvalNumber:"",
                prescriptionDrugsTypeCode:goodsDocDetail.prescriptionDrugsTypeCode?goodsDocDetail.prescriptionDrugsTypeCode:"",
                isEphedrine:goodsDocDetail.isEphedrine?goodsDocDetail.isEphedrine:"",
                isKeyCuring:goodsDocDetail.isKeyCuring?goodsDocDetail.isKeyCuring:"",
                isMedicalInsuranceGoods:goodsDocDetail.isMedicalInsuranceGoods?goodsDocDetail.isMedicalInsuranceGoods:"",
                medicalInsuranceNum:goodsDocDetail.medicalInsuranceNum?goodsDocDetail.medicalInsuranceNum:"",
                approvalNumberTermString:goodsDocDetail.approvalNumberTermString?goodsDocDetail.approvalNumberTermString:"",
                isImportGoods:goodsDocDetail.isImportGoods?goodsDocDetail.isImportGoods:"",
                isChineseMedicineProtect:goodsDocDetail.isChineseMedicineProtect?goodsDocDetail.isChineseMedicineProtect:"",
                approveDateString:goodsDocDetail.approveDateString?goodsDocDetail.approveDateString:"",
                dosageForm:goodsDocDetail.dosageForm?goodsDocDetail.dosageForm:"",
                productionPlace:goodsDocDetail.productionPlace?goodsDocDetail.productionPlace:"",
                effects:goodsDocDetail.effects?goodsDocDetail.effects:"",
                regRegistrationFormNum:goodsDocDetail.regRegistrationFormNum?goodsDocDetail.regRegistrationFormNum:"",
                manufacturerAddr:goodsDocDetail.manufacturerAddr?goodsDocDetail.manufacturerAddr:"",
                applyRange:goodsDocDetail.applyRange?goodsDocDetail.applyRange:"",
                productStandardNum:goodsDocDetail.productStandardNum?goodsDocDetail.productStandardNum:"",
                foodHygieneLicenceNum:goodsDocDetail.foodHygieneLicenceNum?goodsDocDetail.foodHygieneLicenceNum:"",
                productionDateString:goodsDocDetail.productionDateString?goodsDocDetail.productionDateString:"",
                expirationDateString:goodsDocDetail.expirationDateString?goodsDocDetail.expirationDateString:"",
                healthCareFunc:goodsDocDetail.healthCareFunc?goodsDocDetail.healthCareFunc:"",
                appropriateCrowd:goodsDocDetail.appropriateCrowd?goodsDocDetail.appropriateCrowd:"",
                notAppropriateCrowd:goodsDocDetail.notAppropriateCrowd?goodsDocDetail.notAppropriateCrowd:"",
                edibleMethodAndDosage:goodsDocDetail.edibleMethodAndDosage?goodsDocDetail.edibleMethodAndDosage:"",
                storageMethod:goodsDocDetail.storageMethod?goodsDocDetail.storageMethod:"",
                execStandard:goodsDocDetail.execStandard?goodsDocDetail.execStandard:"",
                effectComposition:goodsDocDetail.effectComposition?goodsDocDetail.effectComposition:"",
                notice:goodsDocDetail.notice?goodsDocDetail.notice:"",
                instructions:goodsDocDetail.instructions?goodsDocDetail.instructions:"",
                medicationGuide:goodsDocDetail.medicationGuide?goodsDocDetail.medicationGuide:"",
            });

            store.dispatch({
                type:types.GOODS_ADD_FORM_CHANGE_GOODS_TYPE,
                data:goodsDocDetail.goodsTypeCode
            });

            store.dispatch({
                type:types.GOODS_ADD_FORM_SAVE_DATA_RE_INIT,
                data:newFormData
            });
            store.dispatch({
                type:types.GOODS_ADD_FORM_ADD_PICTURES,
                data:goodsDocDetail.pictFileVoList||[]
            })
            store.dispatch({
                type:types.GOODS_ADD_FORM_ADD_OTHER_FILES,
                data:goodsDocDetail.otherFileVoList||[]
            })

            $("#approvalNumberTermString").val(goodsDocDetail.approvalNumberTermString?goodsDocDetail.approvalNumberTermString:"",);
            $("#approveDateString").val(goodsDocDetail.approveDateString?goodsDocDetail.approveDateString:"",);
            $("#productionDateString").val(goodsDocDetail.productionDateString?goodsDocDetail.productionDateString:"",);
            $("#expirationDateString").val(goodsDocDetail.expirationDateString?goodsDocDetail.expirationDateString:"",);
        });
    }

    render(){
        const {store} = this.context;
        const {actions} = this.props;
        const page = store.getState().todos.goodsDocPage||{};
        const selectedId = store.getState().todos.selectedId||"";
        const goodsDocList = page.content || [];
        const number = page.number + 1;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            hideSizePerPage: page.totalElements <= page.size,
            isWindow:true
        };
        return (
            <div className="layer" style={{zIndex:"300"}}>
                <div className="layer-box layer-info layer-addsp w1175">
                    <div className="layer-header">
                        <span>添加商品</span>
                        <a href="javascript:void(0)" className="close" onClick={()=>actions.changeGoodsDocSelectListState(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <label>商品名称<input type="text" id="docGoodsNm"/></label>
                                </div>
                                <div className="item">
                                    <label>批准文号<input type="text" id="docApproveNumber" /></label>
                                </div>
                                <div className="item">
                                    <label>生产厂家<input type="text" id="docProduceManufacturer"/></label>
                                </div>
                                <a href="javascript:void(0)" className="green-btn" onClick={()=>this.search()}>查询</a>
                                <a href="javascript:void(0)" className="gray-btn" onClick={()=>this.reset()}>重置</a>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <button type="button" className="item-determine black-btn" style={{border:"none"}} href="javascript:void(0)" onClick={()=>this.selectConfirm()} disabled={selectedId==""}>确定选中</button>
                                </div>
                            </div>
                            <div className="box-mc">
                                <div className="table-box">
                                    <table className="w1075">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" ><input type="checkbox" disabled="disabled"/></th>
                                            <th className="th-coding">商品编码</th>
                                            <th className="th-title">商品名称</th>
                                            <th className="common-name">通用名称</th>
                                            <th className="standard">规格</th>
                                            <th className="standard">剂型</th>
                                            <th className="units">单位</th>
                                            <th className="manufacturer">生产厂商</th>
                                            <th className="standard">产地</th>
                                            <th className="time">创建时间</th>
                                        </tr>
                                        </thead>
                                        { goodsDocList.length <= 0 &&
                                        <tbody><tr ><th colSpan="100" style={{textAlign:"center"}}>暂无数据</th></tr></tbody>
                                        }
                                        <tbody>
                                        {
                                            goodsDocList.map((goods,index)=>{
                                                return (
                                                    <tr key={index}>
                                                        <td onClick={()=>this.onRowSelect(goods.id,store)}><input type="checkbox" checked={selectedId==goods.id}/></td>
                                                        <td><div className="td-cont">{goods.goodsCode}</div></td>
                                                        <td><div className="td-cont">{goods.goodsNm}</div></td>
                                                        <td><div className="td-cont">{goods.commonNm}</div></td>
                                                        <td><div className="td-cont">{goods.spec}</div></td>
                                                        <td><div className="td-cont">{goods.dosageForm}</div></td>
                                                        <td><div className="td-cont">{goods.unit}</div></td>
                                                        <td><div className="td-cont">{goods.produceManufacturer}</div></td>
                                                        <td><div className="td-cont">{goods.productionPlace}</div></td>
                                                        <td><div className="td-cont">{goods.createDateString}</div></td>
                                                    </tr>
                                                );
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <IMallPaginationBar options={options} actions={this.props.actions}/> </div>
                </div>
            </div>
        );
    }
}



GoodsDocSelectList.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsDocSelectList);