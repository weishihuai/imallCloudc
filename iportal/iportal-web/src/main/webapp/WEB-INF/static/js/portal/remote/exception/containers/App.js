import React, { Component, PropTypes } from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import ExceptionList from '../components/ExceptionList'
import * as TodoActions from '../actions'

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
                <ExceptionList actions={actions} />
            </div>
        )
    }
}

App.propTypes = {
    todos: PropTypes.object.isRequired,
    actions: PropTypes.object.isRequired
}

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

