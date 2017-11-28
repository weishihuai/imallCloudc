/**
 * Created by ygw on 2016/6/13.
 */
import React, { PropTypes, Component } from 'react'

class JspInclude extends Component {

    constructor(props, context) {
        super(props, context)
        this.state = {
            remotehtml: ""
        }
    }

    componentWillMount() {
        const thisObj = this;
        const request = new XMLHttpRequest();
        request.onload = function() {
            if(request.status >= 200 && request.status < 400) {
                thisObj.setState({
                    remotehtml:request.responseText
                });
            } else {

            }
        };
        request.timeout = 5000;
        request.open('GET', thisObj.props.page, true);
        request.send();
    }

    render() {
        return (
            <div dangerouslySetInnerHTML={{__html: this.state.remotehtml}} />
        )
    }
}

JspInclude.propTypes = {
 page: PropTypes.string.isRequired
 }

export default JspInclude
