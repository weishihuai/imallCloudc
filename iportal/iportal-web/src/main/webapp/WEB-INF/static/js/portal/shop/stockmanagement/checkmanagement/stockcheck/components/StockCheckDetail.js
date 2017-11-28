import React, {Component} from "react";

class StockCheckDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.stockCheckDetailData(this.context.store.getState().todos.checkOrderNum);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    /*盘点状态输出格式化*/
    checkedStateCodeFormat(checkedStateCode) {
        switch (checkedStateCode) {
            case 'CHECKED':
                return '已盘点';
            case 'UNCHECKED':
                return '未盘点';
            case 'CANCEL':
                return '已取消';
            case "UN_POST":
                return '未过账';
        }
    }

    render() {
        const {store} = this.context;
        let stockCheckDetailData = store.getState().todos.stockCheckDetailData;
        let stockCheckGoodsVoList = stockCheckDetailData.stockCheckGoodsVoList || [];
        let checkOrderNum = store.getState().todos.checkOrderNum;
        return (
            <div className="layer">
                <div className="layer-box drug-check w1175">
                    <div className="layer-header">
                        <span>库存盘点详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.stockCheckDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">盘点信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>盘点单号</p>
                                        <span>{checkOrderNum}</span>
                                    </div>
                                    <div className="item">
                                        <p>操作人</p>
                                        <span>{stockCheckDetailData.operatorName || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>操作时间</p>
                                        <span>{stockCheckDetailData.operationTimeString || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>盘点状态</p>
                                        <span>{this.checkedStateCodeFormat(stockCheckDetailData.checkedStateCode)}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w1075">
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
                                        <th className="batch-number">批号</th>
                                        <th className="time">生产日期</th>
                                        <th className="time">有效期至</th>
                                        <th className="cargo-location">货位</th>
                                        <th className="number">库存数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {stockCheckGoodsVoList.map((stockCheckGoodsVo, index) => {
                                        return (<tr key={index}>
                                        <td>
                                            <div className="td-cont">{index + 1}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.goodsCommonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.productionPlace || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.validDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{stockCheckGoodsVo.currentStock}</div>
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

StockCheckDetail.contextTypes = {
    store : React.PropTypes.object
};

export default StockCheckDetail