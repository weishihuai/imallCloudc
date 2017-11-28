import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {shopMgrWeiXinModel} from "../actions/index";

class WeShopMgrWeiXin extends Component{

    componentWillMount(){

    }

    render(){
        const {store} = this.context;
        const {shopMgrWeiXinUrl} = store.getState().weShopTodos;
        return(
            <div className="layer check-wechat-box">
                <div className="check-wechat">
                    <img src={shopMgrWeiXinUrl} alt=""/>
                        <a href="javascript:void(0);" className="icon-close" onClick={() => this.props.shopMgrWeiXinModel(false)}/>
                </div>
            </div>
        );
    }
}

WeShopMgrWeiXin.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({shopMgrWeiXinModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopMgrWeiXin);