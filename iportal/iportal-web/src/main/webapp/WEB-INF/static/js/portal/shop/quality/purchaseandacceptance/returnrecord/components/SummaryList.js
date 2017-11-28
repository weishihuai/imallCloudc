import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

class SummaryList extends Component {

    componentWillMount(){
        this.props.portalOperationalAuth(['quality:returnRecord:print']);
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
                             <th className="order-number" style={{"width": "220px"}}>退货单编号</th>
                             <th className="supplier">供应商单位名称</th>
                             <th className="procurement">采购员</th>
                             <th className="order-date">退货日期</th>
                             <th className="return-model">退货类型</th>
                             <th className="supplier">审核员</th>
                             <th className="supplier">出库员</th>
                             <th className="status" style={{display: 'none'}}>状态</th>
                             <th className="operating" style={{"width": "60px"}}>操作</th>
                         </tr>
                         </thead>
                         <tbody>
                         {content.length <= 0 &&
                         <tr >
                             <td colSpan="9" style={{textAlign:"center"}}>
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
                                             <div className="td-cont" title={ct.returnedPurchaseOrderNum}>{ct.returnedPurchaseOrderNum}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.purchaseMan}>{ct.purchaseMan}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.createDateString}>{ct.createDateString}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.returnedPurchaseType === 'NORMAL_RETURNED' ? '正常退货' : '不合格退货'}>{ct.returnedPurchaseType == 'NORMAL_RETURNED' ? '正常退货' : '不合格退货'}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.approveMan}>{ct.approveMan}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.outStorageMan}>{ct.outStorageMan}</div></td>
                                         <td style={{display: 'none'}}><div className="td-cont" title={ct.isPayed === 'Y'? '已结款' : '未结款'}>{ct.isPayed == 'Y'? '已结款' : '未结款'}</div></td>
                                         <td>
                                             <div className="td-cont" >
                                             <ImallA target="_blank" href={iportal + "/printer/printReturnedPurchase-" + ct.id + ".html"} permissionCode="quality:returnRecord:print" className="gray-btn" text="打印" onClick={() => {}}/>
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