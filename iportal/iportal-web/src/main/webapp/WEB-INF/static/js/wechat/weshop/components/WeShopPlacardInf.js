import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {placardInfModel} from "../actions/index";

class WeShopPlacardInf extends Component{

    componentWillMount(){

    }

    render(){
        const {store} = this.context;
        const {data} = store.getState().weShopTodos;
        return(
            <div className="layer stores-announcement-box">
                <div className="stores-announcement">
                    <div className="dt">
                        <div className="dt-pic"><img src={data.logoUrl} alt=""/></div>
                        <div className="dt-title">门店公告</div>
                    </div>
                    <div className="dd">{data.placardInf}</div>
                    <a className="layer-btn" onClick={() => this.props.placardInfModel(false)}>关闭</a>
                    <em className="icon-l"/>
                    <em className="icon-r"/>
                </div>
            </div>
        );
    }
}

WeShopPlacardInf.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({placardInfModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopPlacardInf);