import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

class SummaryList extends Component {

    componentWillMount(){
        this.props.portalOperationalAuth(['quality:purchaseRecord:print']);
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
                             <th className="order-number">采购订单编号</th>
                             <th className="supplier">供应商单位名称</th>
                             <th className="procurement">采购人名称</th>
                             <th className="purchase-amount">采购金额</th>
                             <th className="order-date">订单日期</th>
                             <th className="estimated-time">已清时间</th>
                             <th className="status">状态</th>
                             <th className="operating" style={{"width": "60px"}}>操作</th>
                         </tr>
                         </thead>
                         <tbody>
                         {content.length <= 0 &&
                         <tr >
                             <td colSpan="8" style={{textAlign:"center"}}>
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
                                             <div className="td-cont" title={ct.purchaseOrderNum}>{ct.purchaseOrderNum}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.purchaseMan}>{ct.purchaseMan}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.purchaseTotalAmount}>{ct.purchaseTotalAmount}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.createDateString}>{ct.createDateString}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.clearingTimeString}>{ct.clearingTimeString}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.purchaseOrderStateName}>{ct.purchaseOrderStateName}</div></td>
                                         <td>
                                             <div className="td-cont" >
                                                 <ImallA target="_blank" href={iportal + "/printer/printPurchaseOrder-" + ct.id + ".html"} permissionCode="quality:purchaseRecord:print" className="gray-btn" text="打印" onClick={() => {}}/>
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