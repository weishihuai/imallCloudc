import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';

class StopSaleNoticeDetailView extends Component {

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {detailData} = store.getState().todos;
        return (
            <div className="layer" >
                <div className="layer-box layer-info layer-order layer-receiving w1175">
                    <div className="layer-header">
                        <span>药品停售通知单</span>
                        <a href="javascript:void(0)" className="close" onClick={()=>actions.changeStopSaleNoticeDetailViewState(false)}/>
                    </div>
                    <div className="layer-body" style={{paddingTop: "0"}}>
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p><i>*</i>审核人</p>
                                    <span>{detailData.approveManName}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">停售信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>制单人</p>
                                        <span>{detailData.docMakerNm}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>停售日期</p>
                                        <span>{detailData.stopSaleDateStr}</span>
                                    </div>
                                    <div className="item">
                                        <p>质量状况</p>
                                        <span>{detailData.qualityState||"暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>停售意见</p>
                                        <span>{detailData.stopSaleSuggest||"暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>制单时间</p>
                                        <span>{detailData.makeTimeStr}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc clearfix">
                                <table className="w960">
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
                                        <th className="approval-number">批号</th>
                                        <th className="approval-number">有效期至</th>
                                    </tr>
                                    </thead>
                                    { detailData.stopSaleGoodsInfVos.length <= 0 &&
                                    <tbody><tr ><th colSpan="100" style={{textAlign:"center"}}>暂无数据</th></tr></tbody>
                                    }
                                    <tbody>
                                    {
                                        detailData.stopSaleGoodsInfVos.map((content,index)=>{
                                            return (
                                                <tr key={index}>
                                                    <td><div className="td-cont" >{index+1}</div></td>
                                                    <td><div className="td-cont" title={content.goodsCode}>{content.goodsCode}</div></td>
                                                    <td><div className="td-cont" title={content.goodsNm}>{content.goodsNm}</div></td>
                                                    <td><div className="td-cont" title={content.commonNm}>{content.commonNm}</div></td>
                                                    <td><div className="td-cont" title={content.spec}>{content.spec}</div></td>
                                                    <td><div className="td-cont" title={content.dosageFormName}>{content.dosageFormName}</div></td>
                                                    <td><div className="td-cont" title={content.unit}>{content.unit}</div></td>
                                                    <td><div className="td-cont" title={content.produceManufacturer}>{content.produceManufacturer}</div></td>
                                                    <td><div className="td-cont" title={content.approvalNumber}>{content.approvalNumber}</div></td>
                                                    <td><div className="td-cont" title={content.productionPlace}>{content.productionPlace}</div></td>
                                                    <td><div className="td-cont" title={content.batch}>{content.batch}</div></td>
                                                    <td><div className="td-cont" title={content.validDateStr}>{content.validDateStr}</div></td>
                                                </tr>
                                            )
                                        })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


StopSaleNoticeDetailView.contextTypes = {
    store: React.PropTypes.object
};

export default StopSaleNoticeDetailView