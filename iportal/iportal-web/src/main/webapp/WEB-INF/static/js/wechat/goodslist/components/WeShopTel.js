import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setShowTel} from "../actions/index";

class WeShopTel extends Component{

    componentWillMount(){

    }

    render(){
        return(
            <div className="layer">
                <div className="index-layer-box">
                    <div className="mc">
                        <span>{this.props.tel}</span>
                    </div>
                    <div className="md">
                        <a href="javascript:void(0);" className="cancel" onClick={() => this.props.setShowTel(false)}>取消</a>
                        <a href={"tel:" + this.props.tel} className="confirm">拨打</a>
                    </div>
                </div>
            </div>
        );
    }
}

WeShopTel.propTypes = {
    tel: PropTypes.string.isRequired
};

WeShopTel.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setShowTel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopTel);