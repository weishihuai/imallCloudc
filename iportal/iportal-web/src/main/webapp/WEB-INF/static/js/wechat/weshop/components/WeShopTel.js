import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {weShopTelModel} from "../actions/index";

class WeShopTel extends Component{

    componentWillMount(){

    }

    render(){
        const {store} = this.context;
        const {weShopContactTel} = store.getState().weShopTodos;
        return(
            <div className="layer">
                <div className="index-layer-box">
                    <div className="mc">
                        <span>{weShopContactTel}</span>
                    </div>
                    <div className="md">
                        <a href="javascript:void(0);" className="cancel" onClick={() => this.props.weShopTelModel(false)}>取消</a>
                        <a href={"tel:" + weShopContactTel } className="confirm">拨打</a>
                    </div>
                </div>
            </div>
        );
    }
}

WeShopTel.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({weShopTelModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopTel);