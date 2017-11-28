import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

import PrescriptionList from '../../prescription/components/PrescriptionList';

class PrescriptionRegisterList extends Component{
    render() {
        const {actions}=this.props;

        return(
            <div>
                <PrescriptionList actions={actions} prescriptionRegisterState="" modelType="prescriptionRegister"/>
            </div>
        );
    }
}

PrescriptionRegisterList.propTypes = {
    actions: PropTypes.object.isRequired
};

PrescriptionRegisterList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PrescriptionRegisterList);
