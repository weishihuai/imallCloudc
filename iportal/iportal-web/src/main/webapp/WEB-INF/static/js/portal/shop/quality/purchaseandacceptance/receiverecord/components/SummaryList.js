import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

class SummaryList extends Component {

    componentWillMount(){
        this.props.portalOperationalAuth(['quality:receiveRecord:print']);
    }

    render() {
        const {store} = this.context;
        const content = store.getState().todos.page.content;

        return (
             <div className="mc">
                 <div className="table-box">
                     <table>
                         <thead>
                         <tr>
                             <th className="order-number">收货单编号</th>
                             <th className="supplier">供应商单位名称</th>
                             <th className="procurement">收货人</th>
                             <th className="purchase-amount">采购金额</th>
                             <th className="order-date">收货时间</th>
                             <th className="status">状态</th>
                             <th className="operating" style={{"width": "60px"}}>操作</th>
                         </tr>
                         </thead>
                         <tbody>
                         {content.length <= 0 &&
                         <tr >
                             <td colSpan="7" style={{textAlign:"center"}}>
                                 <div className="empty-box">
                                     <span>暂无数据</span>
                                 </div>
                             </td>
                         </tr>
                         }
                         {
                             content.map((ct, i)=> {
                                 return (
                                     <tr key={i}>
                                         <td>
                                             <div className="td-cont" title={ct.receiveOrderNum}>{ct.receiveOrderNum}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.receiver}>{ct.receiver}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.purchaseTotalAmount}>{ct.purchaseTotalAmount}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.receiveTimeString}>{ct.receiveTimeString}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.purchaseOrderStateName}>{ct.purchaseOrderStateName}</div></td>
                                         <td>
                                             <div className="td-cont" >
                                                 <ImallA target="_blank" href={iportal + "/printer/printPurchaseReceive-" + ct.id + ".html"} permissionCode="quality:receiveRecord:print" className="gray-btn" text="打印" onClick={() => {}}/>
                                             </div>
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

SummaryList.propTypes = {
    actions: PropTypes.object.isRequired
}

SummaryList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SummaryList);