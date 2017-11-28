import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import * as TodoActions from "../actions";
import DrugCombinationCategoryList from "../components/DrugCombinationCategoryList";

class App extends Component {

    constructor(props, context) {
        super(props, context)
    }

    componentWillMount(){

    }

    componentDidMount(){

    }


    render() {
        const {actions} = this.props;

        return (
            <div>
                <DrugCombinationCategoryList actions={actions}/>
            </div>
        )
    }
}

App.propTypes = {
    todos: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired
};

App.contextTypes = {
    store : React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(Object.assign({}, TodoActions), dispatch)
    }
}


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(App)

