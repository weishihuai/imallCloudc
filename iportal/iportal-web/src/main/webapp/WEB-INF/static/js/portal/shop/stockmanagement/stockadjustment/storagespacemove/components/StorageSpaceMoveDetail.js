import React, {Component, PropTypes} from "react";

/*货位移动详情*/
class StorageSpaceMoveDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.storageSpaceMoveDetailData(this.context.store.getState().todos.moveOrderNum);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.context;
        let storageSpaceMoveDetailData = store.getState().todos.storageSpaceMoveDetailData || {};
        let storageSpaceMoveGoodsVoList = storageSpaceMoveDetailData.storageSpaceMoveGoodsVoList || [];

        return (
            <div className="layer">
                <div className="layer-box drug-check w1290">
                    <div className="layer-header">
                        <span>货位移动</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.storageSpaceMoveDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>移动单号</p>
                                        <span>{storageSpaceMoveDetailData.moveOrderNum}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>移动人</p>
                                        <span>{storageSpaceMoveDetailData.moveManName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>移动时间</p>
                                        <span>{storageSpaceMoveDetailData.moveTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{storageSpaceMoveDetailData.remark || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item" style={{"clear": "both"}}>
                                        <p><i>*</i>审核人</p>
                                        <span>{storageSpaceMoveDetailData.approveManName}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w1210">
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="cargo-location">原货位</th>
                                        <th className="cargo-location">目标货位</th>
                                        <th className="number">库存数量</th>
                                        <th className="number">移动数量</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="batch-number">批号</th>
                                        <th className="time">生产日期</th>
                                        <th className="time">有效期至</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {storageSpaceMoveGoodsVoList.map((storageSpaceMoveGoodsVo, index) => {
                                        return (<tr key={index}>
                                        <td>
                                            <div className="td-cont">{index + 1}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.sourceStorageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.targetStorageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.currentStock}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.goodsCommonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{storageSpaceMoveGoodsVo.validDateString}</div>
                                        </td>
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

StorageSpaceMoveDetail.contextTypes = {
    store : React.PropTypes.object
};

export default StorageSpaceMoveDetail