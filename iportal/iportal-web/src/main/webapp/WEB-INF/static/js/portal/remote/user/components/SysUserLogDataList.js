import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

/**
 * 日志详情
 */

class SysUserLogDataList extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
    }

    componentDidMount() {
         const {store} = this.context;
         const logDataId = store.getState().todos.logDataId;
         this.props.actions.sysLogDataList(logDataId);
    }

    componentWillUnmount() {

    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const logDataList = store.getState().todos.logDataList;

        return (
        <div className="layer">
            <div className="layer-box layer-record w860">
                <div className="layer-header">
                    <span>日志详情</span>
                    <a href="javascript:void(0);" className="close" onClick={(e) => actions.logDataListModel(false, null)}/>
                </div>
                <div className="layer-body" style={{height:"400px"}}>
                    <div className="table-box" style={{ maxHeight: "none" }}>
                        <table>
                            <thead>
                            <tr>
                                <th className="th-number">序号</th>
                                <th className="attribute" style={{width: "100px"}}>属性名称</th>
                                <th className="attribute" style={{width: "210px"}}>原属性内容</th>
                                <th className="attribute" style={{width: "210px"}}>新属性内容</th>
                            </tr>
                            </thead>
                            <tbody>
                            {logDataList.map((logData, index) => {
                                var columnNameColor = "#000000";
                                if(logData.columnType==='PRIMARY_KEY'){
                                    columnNameColor = "#3399FF";
                                }
                                if(logData.columnType==='FOREIGN_KEY'){
                                    columnNameColor = "#00CC00";
                                }
                                if(logData.columnBeforeOperationValue!==logData.columnAfterOperationValue){
                                    columnNameColor = "#EE0000";
                                }
                                return (
                                    <tr key={index}>
                                        <td>
                                            <div className="td-cont td-number">{index + 1}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont" style={{color: columnNameColor}}>{logData.columnName}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{logData.columnBeforeOperationValue}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{logData.columnAfterOperationValue}</div>
                                        </td>
                                    </tr>
                                )
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="layer-footer"></div>
            </div>
        </div>
        )
    }
}

SysUserLogDataList.propTypes = {};

SysUserLogDataList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SysUserLogDataList);