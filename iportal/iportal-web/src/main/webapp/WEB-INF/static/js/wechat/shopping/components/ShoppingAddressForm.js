import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {getReceiverAddrList, saveAddress} from "../actions/index";
class ShoppingAddressForm extends Component {

    componentWillMount(){
        this.props.getReceiverAddrList();
    }

    checkBeyondScope(){
        const addrList = this.context.store.getState().shoppingTodos.addrList;
        for (let i = 0; i < addrList.length; i++){
            if (!addrList[i].inDeliveryRange){
                return true;
            }
        }
        return false;
    }

    render() {
        const {store} = this.context;
        const addrList = store.getState().shoppingTodos.addrList;
        return (
            <div style={{paddingTop: "0"}} className="main">
                <div className="mc">
                    {addrList.map((addr, index) => {
                        return(
                        addr.inDeliveryRange ? <div className="item" onClick={() => this.props.saveAddress(addr.id)} key={index}>
                            <p style={{lineHeight: "inherit", height: "auto", marginBottom: "0.5rem"}}>{addr.deliveryAddr + addr.detailAddr}</p>
                            <span>{addr.receiverName}</span>
                            <span>{addr.contactTel}</span>
                        </div> : ''
                        );
                    })}

                </div>
                {
                    this.checkBeyondScope() ?  <div className="beyond-scope">
                        <p className="title">以下地址超出配送范围</p>
                        {addrList.map((addr, index) => {
                            return(
                                addr.inDeliveryRange ? '' : <div className="beyond-site" key={index}>
                                    <p>{addr.deliveryAddr + addr.detailAddr}</p>
                                    <span>{addr.receiverName}</span>
                                    <span>{addr.contactTel}</span>
                                </div>
                            );
                        })}
                    </div> : ''
                }

                <div className="check-btn-auto">
                    <div className="check-btn-total">
                        <a href="#/shopping-receive-addr-add" className="add-btn">新增收货地址</a>
                    </div>
                </div>
            </div>
        );
    }
}

ShoppingAddressForm.propTypes = {};

ShoppingAddressForm.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({getReceiverAddrList, saveAddress}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingAddressForm);