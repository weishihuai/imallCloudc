import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";

class DrugCombinationDetail extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {actions, store} = this.props;
        const record = store.getState().todos.record;

        return(
            <div className="layer">
                <div className="layer-box layer-info layer-medication w960">
                    <div className="layer-header">
                        <span>联合用药详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => actions.showDetail(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item item-1-2">
                                    <span><i>*</i>分类名称</span>
                                    <p>{record.categoryNm}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span><i>*</i>病症</span>
                                    <p>{record.disease}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span><i>*</i>症状</span>
                                    <p>{record.symptom}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span><i>*</i>常识判断</span>
                                    <p>{record.commonSense}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span>用药原则</span>
                                    <p>{record.drugUsePrinciple}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span><i>*</i>主导用药</span>
                                    <p>{record.leadingDrug}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span>联合用药</span>
                                    <p>{record.drugCombination}</p>
                                </div>
                                <div className="item item-1-2">
                                    <span>专业关怀</span>
                                    <p>{record.majorConcern}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                    </div>
                </div>
            </div>
        );
    }
}

DrugCombinationDetail.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCombinationDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        state
    }
}

DrugCombinationDetail = reduxForm({
    form: 'drugCombinationDetail',
    enableReinitialize: true
})(DrugCombinationDetail);

DrugCombinationDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCombinationDetail);



export default DrugCombinationDetail;