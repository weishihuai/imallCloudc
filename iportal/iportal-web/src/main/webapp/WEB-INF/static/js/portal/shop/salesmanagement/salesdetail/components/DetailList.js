import React, {Component, PropTypes} from "react";

class DetailList extends Component {

    render() {
        const {store} = this.context;
        const content = store.getState().todos.page.content;
        return (
            <div className="mc">
                <div className="table-box">
                    <table style={{width: "2700px"}}>
                        <thead>
                        <tr>
                            <th style={{width: "135px"}} className="order-number">订单编号</th>
                            <th style={{width: "135px"}} className="time">销售时间</th>
                            <th className="order-type">订单类型</th>
                            <th className="th-title">商品名称</th>
                            <th className="th-sy">处方药</th>
                            <th className="th-sy">麻黄碱</th>
                            <th className="common-name">通用名称</th>
                            <th className="standard">规格</th>
                            <th className="dosage">剂型</th>
                            <th className="units">单位</th>
                            <th className="manufacturer">生产厂商</th>
                            <th className="approval">批准文号</th>
                            <th className="cargo-location">货位</th>
                            <th className="price">单价</th>
                            <th className="number">数量</th>
                            <th className="price">小计</th>
                            <th className="price">成本金额</th>
                            <th className="vip-number">会员卡号</th>
                            <th className="phone-number">会员手机</th>
                            <th className="name">营业员</th>
                            <th className="status">状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        {content.length <= 0 &&
                        <tr >
                            <td colSpan="21" style={{textAlign:"center"}}>
                                <div className="empty-box">
                                    <span>暂无数据</span>
                                </div>
                            </td>
                        </tr>
                        }
                        {
                            content.map((ct, index) => {
                                return(
                                    <tr key={index}>
                                        <td>
                                            <div className="td-cont" title={ct.orderNum}>{ct.orderNum}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.sellTimeString}>{ct.sellTimeString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.orderSourceName}>{ct.orderSourceName}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.goodsNm}>{ct.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.isPrescription === 'Y' ?  '是' : '否'}>{ct.isPrescription == 'Y' ?  '是' : '否'}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.isEphedrine === 'Y' ?  '是' : '否'}>{ct.isEphedrine == 'Y' ?  '是' : '否'}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.commonNm}>{ct.commonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.spec}>{ct.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.dosageForm}>{ct.dosageForm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.unit}>{ct.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.produceManufacturer}>{ct.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.approvalNumber}>{ct.approvalNumber}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.storageSpaceNm}>{ct.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.goodsUnitPrice}>{ct.goodsUnitPrice}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.quantity}>{ct.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.goodsTotalAmount}>{ct.goodsTotalAmount}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.goodsCostTotalAmount}>{ct.goodsCostTotalAmount}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.memberCardNum}>{ct.memberCardNum}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.mobile}>{ct.mobile}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.sellerNm}>{ct.sellerNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" title={ct.orderStatName}>{ct.orderStatName}</div>
                                        </td>
                                    </tr>
                                );
                            })
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

DetailList.contextTypes = {
    store: React.PropTypes.object
};

export default DetailList;