/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component} from "react";
import PosBatchSelectModal from "./PosBatchSelectModal";
import {
    POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_UP,
    POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_DOWN,
    POS_CASHIER_SEARCH_BATCH_MODAL,
    POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_UP,
    POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_DOWN,
    POS_CASHIER_SELECT_BATCH_STATE,
    POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_RESET
} from "../constants/ActionTypes";

/*商品选择*/
class PosGoodsSelectModal extends Component {

    constructor(props) {
        super(props);
        this.changeFocusItem = this.changeFocusItem.bind(this)
    }

    componentDidMount() {
        window.addEventListener('keydown', this.changeFocusItem);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.changeFocusItem);
    }

    changeFocusItem(event) {
        const {store} = this.context;
        const {searchGoodsState, searchBatchState, searchGoodsFocusItemIndex} = store.getState().todos;
        const {goodsSearchData, batchSearchData} = this.props.actions;
        if(searchGoodsState && !searchBatchState) {
            switch(event.keyCode){
                //enter
                case 13:
                    event.preventDefault();
                    if(searchGoodsFocusItemIndex < 0) {
                        break;
                    }
                    store.dispatch({type: POS_CASHIER_SEARCH_BATCH_MODAL, isShow: true});
                    break;
                //esc
                case 27:
                    event.preventDefault();
                    let oldjs = document.getElementById("notyLayer");
                    if(oldjs) {
                        oldjs.parentNode.removeChild(oldjs);
                        break;
                    }
                    goodsSearchData(false);
                    break;
                //up
                case 38:
                    event.preventDefault();
                    store.dispatch({type: POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_UP});
                    if($("#selectGoodsTable").height() > $("#selectGoodsDiv").height()) {
                        $("#selectGoodsDiv").scrollTop(searchGoodsFocusItemIndex * 50)
                    }
                    break;
                //down
                case 40:
                    event.preventDefault();
                    // this.refs.windowSearch.blur();
                    store.dispatch({type: POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_DOWN});
                    if($("#selectGoodsTable").height() > $("#selectGoodsDiv").height()) {
                        $("#selectGoodsDiv").scrollTop(searchGoodsFocusItemIndex * 50)
                    }
                    break;
            }
        } else if(searchBatchState) {
            switch(event.keyCode){
                //enter
                case 13:
                    event.preventDefault();
                    store.dispatch({type: POS_CASHIER_SELECT_BATCH_STATE, isShow: true});
                    this.props.selectBatchQuantityFocus();
                    break;
                //esc
                case 27:
                    event.preventDefault();
                    batchSearchData(false);
                    break;
                //up
                case 38:
                    event.preventDefault();
                    store.dispatch({type: POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_UP});
                    break;
                //down
                case 40:
                    event.preventDefault();
                    store.dispatch({type: POS_CASHIER_SEARCH_BATCH_FOCUS_ITEM_INDEX_DOWN});
                    break;
            }
        }
    }

    resetGoodsFocusItemIndex() {
        this.context.store.dispatch({
            type: POS_CASHIER_SEARCH_GOODS_FOCUS_ITEM_INDEX_RESET
        })
    }

    boolStatCode(code){
        switch (code){
            case "Y":
                return "是";
            case "N":
                return "否";
            default:
                return "否";
        }
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {searchBatchState, searchGoodsList, goodsSearchFields, searchGoodsFocusItemIndex} = store.getState().todos;
        const {searchFieldsChange, goodsSearchData} = this.props.actions;

        return (
            <div className="layer">
                <div className="layer-box layer-choice w1075">
                    <div className="layer-header">
                        <span>商品选择</span>
                        <a href="javascript:void(0);" className="close" onClick={() => goodsSearchData(false)}/>
                    </div>
                    <div className="layer-body" id="selectGoodsDiv">
                        <div className="pos-table-box" id="selectGoodsTable">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="th-number">序号</th>
                                        <th className="th-coding">商品编码</th>
                                        <th className="common-name">通用名称</th>
                                        <th className="common-name">商品名称</th>
                                        <th className="manufacturer">生产厂商</th>
                                        <th className="standard">规格</th>
                                        <th className="units">单位</th>
                                        <th className="retail-price">销售价</th>
                                        <th className="retail-price">会员价</th>
                                        <th className="inventory">库存</th>
                                        <th className="prescription">处方药</th>
                                        <th className="ephedrine">麻黄碱</th>
                                    </tr>
                                </thead>
                                <tbody id="select-goods">
                                    {searchGoodsList.map((goods,index)=> {

                                        return (
                                            <tr key={index} className={searchGoodsFocusItemIndex == index ? "active" : ""}>
                                                <td>
                                                    <div className="td-cont td-number">{index + 1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.commonNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.unit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.retailPrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.memberPrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{goods.stock}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{this.boolStatCode(goods.prescriptionDrugs)}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{this.boolStatCode(goods.isEphedrine)}</div>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="layer-footer"></div>
                    {searchBatchState && <PosBatchSelectModal actions={actions} store={store}/>}
                </div>
            </div>
        )
    }
}

PosGoodsSelectModal.contextTypes = {
    store: React.PropTypes.object
};

export default PosGoodsSelectModal;
