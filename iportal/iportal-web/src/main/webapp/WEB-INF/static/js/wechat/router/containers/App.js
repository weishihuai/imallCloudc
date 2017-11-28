import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import MenuRouter from '../components/MenuRouter'
import * as TodoActions from '../actions'

class App extends Component {

    constructor(props, context) {
        super(props, context)
    }

    render() {
        return (
            <MenuRouter />
        )
    }
}

App.propTypes = {
};

App.contextTypes = {
    store : React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(TodoActions, dispatch)
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(App)
