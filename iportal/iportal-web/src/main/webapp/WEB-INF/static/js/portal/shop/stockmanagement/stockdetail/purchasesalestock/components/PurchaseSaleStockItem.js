import React, {Component} from "react";

/*进销存台帐 项*/
class PurchaseSaleStockItem extends Component {

    render() {
        const {item, index} = this.props;

        return (
            <tr>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.goodsCode}>{item.goodsCode}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.goodsNm}>{item.goodsNm}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.commonNm}>{item.commonNm}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.spec}>{item.spec}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.dosageForm}>{item.dosageForm}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.unit}>{item.unit}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.approvalNumber}>{item.approvalNumber}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.productionPlace}>{item.productionPlace}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.clearingPrevQuantity}>{item.clearingPrevQuantity}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.clearingPrevAmount}>{item.clearingPrevAmount}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curInStockQuantity}>{item.curInStockQuantity}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curInStockAmount}>{item.curInStockAmount}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curOutStockQuantity}>{item.curOutStockQuantity}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curOutStockAmount}>{item.curOutStockAmount}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curStockQuantity}>{item.curStockQuantity}</div>
                </td>
                <td>
                    <div className="td-cont" title={item.curStockAmount}>{item.curStockAmount}</div>
                </td>
            </tr>
        )
    }
}

PurchaseSaleStockItem.contextTypes = {
    store: React.PropTypes.object
};

export default PurchaseSaleStockItem;