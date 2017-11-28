/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component} from "react";

/*批号选择*/
class PosBatchSelectModal extends Component {

    render() {
        const {store} = this.context;
        const {searchBatchList, searchBatchFocusItemIndex} = store.getState().todos;
        const {batchSearchData} = this.props.actions;

        return (
            <div className="layer" style={{background: 'none'}}>
                <div className="layer-box layer-choice batch-choice w598">
                    <div className="layer-header">
                        <span>批号选择</span>
                        <a href="javascript:void(0);" className="close" onClick={(e) => batchSearchData(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="batch-number">批号</th>
                                    <th className="time">有效期</th>
                                    <th className="inventory">库存量</th>
                                    <th className="cargo-location">货位</th>
                                </tr>
                                </thead>
                                <tbody>
                                {searchBatchList.map((batch,index)=> {
                                    return (
                                        <tr key={index} className={searchBatchFocusItemIndex == index ? "active" : ""}>
                                            <td>
                                                <div className="td-cont td-number">{index+1}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{batch.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{batch.validDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{batch.stock}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{batch.storageSpaceNm}</div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

PosBatchSelectModal.contextTypes = {
    store: React.PropTypes.object
};

export default PosBatchSelectModal;
