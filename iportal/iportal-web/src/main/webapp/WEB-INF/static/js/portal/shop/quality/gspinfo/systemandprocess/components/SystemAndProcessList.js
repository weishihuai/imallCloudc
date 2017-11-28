import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class SystemAndProcessList extends Component{
    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.systemAndProcessList(params);
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
                            <h5>制度与流程</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">GSP资料</a>
                            <span>></span>
                            <a href="javascript:void(0);">制度与流程</a>
                        </div>
                    </div>
                </div>
                <div className="layer-box clearfix">
                    <table>
                        <thead>
                        <tr>
                            <th className="name"><span>表格名称</span></th>
                            <th className="operation"><span>操作</span></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            record.map((record, index)=>{
                                return(
                                    <tr>
                                        <td>{index}{record.tableNm}</td>
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

SystemAndProcessList.propTypes = {
    actions: PropTypes.object.isRequired
};

SystemAndProcessList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SystemAndProcessList);