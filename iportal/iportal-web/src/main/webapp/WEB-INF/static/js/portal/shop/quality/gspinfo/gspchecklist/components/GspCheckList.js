import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class GspCheckList extends Component{
    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.gspCheckList(params);
    }

    componentDidMount(){

    }

    downloadDoc(id){
        window.open(iportal + '/docInf/downloadDoc.json?id=' + id);
    }

    render(){
        const {store} = this.context;
        const record = store.getState().todos.record || [];

        return(
            <div className="main-box check-info-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>GSP检查表</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">GSP资料</a>
                            <span>></span>
                            <a href="javascript:void(0);">GSP检查表</a>
                        </div>
                    </div>
                </div>
                <div className="layer-box clearfix">
                    <table>
                        <thead>
                        <tr>
                            <th className="serial-number"><span/></th>
                            <th className="name"><span>表格名称</span></th>
                            <th className="operation"><span>操作</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            record.map((record, index) => {
                                return (
                                    index < 19 &&
                                    <tr key={record.id}>
                                        <td><span>{index + 1}</span></td>
                                        <td>{record.tableNm}</td>
                                        <td><a href="javascript:void(0);" onClick={()=>this.downloadDoc(record.id)} style={{color: "blue", textDecoration: 'underline'}}>下载</a></td>
                                    </tr>
                                );
                            })
                        }
                        </tbody>
                    </table>
                    <table>
                        <thead>
                        <tr>
                            <th className="serial-number"><span/></th>
                            <th className="name"><span>表格名称</span></th>
                            <th className="operation"><span>操作</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            record.map((record, index) => {
                                return (
                                    index >= 19 &&
                                    <tr key={record.id}>
                                        <td><span>{index + 1}</span></td>
                                        <td>{record.tableNm}</td>
                                        <td><a href="javascript:void(0);" onClick={()=>this.downloadDoc(record.id)} style={{color: "blue", textDecoration: 'underline'}}>下载</a></td>
                                    </tr>
                                );
                            })
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

GspCheckList.propTypes = {
    actions: PropTypes.object.isRequired
};

GspCheckList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GspCheckList);