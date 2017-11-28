import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import EditGroup from "./EditGroup";
import IndexDecoration from "./IndexDecoration";

class DecorationRecommend extends Component{

    componentWillMount(){

    }

    componentDidMount(){

    }

    render(){
        const {store} = this.context;
        const {editGroup} = store.getState().todos;
        return(
            <div>
                {!editGroup && <IndexDecoration actions={this.props.actions}/>}
                {editGroup && <EditGroup actions={this.props.actions}/>}
            </div>
        );
    }
}

DecorationRecommend.propTypes = {
    actions: PropTypes.object.isRequired
};

DecorationRecommend.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch)
}

export default connect(mapStateToProps, mapDispatchToProps)(DecorationRecommend);