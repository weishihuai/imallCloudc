import React, {Component, PropTypes} from "react";

class OrderSearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount(){
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });

        $(".select").jSelect();
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().orderTodos.params || {page: 0, size: 10};
        let keyword = $.trim(this.refs.keyword.value);
        let receiverName = $.trim(this.refs.receiverName.value);
        let contactTel = $.trim(this.refs.contactTel.value);
        let orderNum = $.trim(this.refs.orderNum.value);
        let startCreateDateString = $.trim(this.refs.startCreateDateString.value);
        let endCreateDateString = $.trim(this.refs.endCreateDateString.value);
        let bookDeliveryTimeStartString = $.trim(this.refs.bookDeliveryTimeStartString.value);
        let bookDeliveryTimeEndString = $.trim(this.refs.bookDeliveryTimeEndString.value);
        let paymentWayCode = $("#paymentWayCode").val().trim();

        params = Object.assign(params, {
            keyword: keyword,                                           //商品编码/通用名称/通用名称首字母/商品名称
            receiverName: receiverName,                                 //收货人姓名
            contactTel: contactTel,                                     //联系电话
            orderNum: orderNum,                                         //订单编号
            startCreateDateString: startCreateDateString,               //下单开始时间
            endCreateDateString: endCreateDateString,                   //下单结束时间
            bookDeliveryTimeStartString: bookDeliveryTimeStartString,   //预约送达时间开始
            bookDeliveryTimeEndString: bookDeliveryTimeEndString,       //预约送达时间结束
            paymentWayCode: paymentWayCode                              //支付 方式 代码
        });
        actions.orderSearch(params);
    }

    resetForm() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().orderTodos.params || {page: 0, size: 10};
        this.refs.keyword.value = '';
        this.refs.receiverName.value = '';
        this.refs.contactTel.value = '';
        this.refs.orderNum.value = '';
        this.refs.startCreateDateString.value = '';
        this.refs.endCreateDateString.value = '';
        this.refs.bookDeliveryTimeStartString.value = '';
        this.refs.bookDeliveryTimeEndString.value = '';
        $("#paymentWayCode").val('');
        $('#paymentWayCode-clear').text("养护类型");
        $(".select").jSelectReset();
        params = Object.assign(params, {
            keyword:"",                     //商品编码/通用名称/通用名称首字母/商品名称
            receiverName:"",                //收货人姓名
            contactTel:"",                  //联系电话
            orderNum:"" ,                   //订单编号
            startCreateDateString:"",       //下单开始时间
            endCreateDateString:"",         //下单结束时间
            bookDeliveryTimeStartString:"", //预约送达时间开始
            bookDeliveryTimeEndString:"" ,  //预约送达时间结束
            paymentWayCode:""               //支付 方式 代码
        });
        actions.orderSearch(params);
    }

    render() {
        const payWayType = [
                {code: 'WEBCHAT_PAY', name: '微信支付'},
                {code: 'RECHARGE_PAY', name: '预存款支付'},
                {code: 'ADMIN_PAY', name: '管理员支付'},
                {code: 'ALIPAY_PAY', name: '支付宝支付'},
                {code: 'BANK_CARD_PAY', name: '银行卡支付'},
                {code: 'CASH_PAY', name: '现金支付'}
            ];

        return (
            <div className="lt-cont clearfix" style={{height: 'auto'}}>
                <div className="search search1" style={{marginBottom: 10 + 'px', width: 175 + 'px'}}>
                    <input placeholder="商品名称/商品编码" type="text" style={{width: 150 + 'px'}} ref="keyword"/>
                </div>
                <div className="search" style={{marginBottom: 10 + 'px'}}>
                    <input placeholder="收货人" type="text" ref="receiverName"/>
                </div>
                <div className="search" style={{marginBottom: 10 + 'px'}}>
                    <input placeholder="手机号" type="text" ref="contactTel"/>
                </div>
                <div className="search" style={{marginBottom: 10 + 'px'}}>
                    <input placeholder="订单编号" type="text" ref="orderNum"/>
                </div>
                <div className="sel-date" style={{marginBottom: 10 + 'px', width: 280 + 'px'}}>
                    <div className="form-group float-left w140">
                        <input name="datepicker" placeholder="下单时间" className="form-control datepicker" type="text" ref="startCreateDateString" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" className="form-control datepicker" type="text" ref="endCreateDateString" readOnly/>
                    </div>
                </div>
                <div className="sel-date" style={{marginBottom: 10 + 'px', width: 280 + 'px'}}>
                    <div className="form-group float-left w140">
                        <input name="datepicker" placeholder="送达时间" className="form-control datepicker" type="text" ref="bookDeliveryTimeStartString" readOnly/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" className="form-control datepicker" type="text" ref="bookDeliveryTimeEndString" readOnly/>
                    </div>
                </div>
                <div className="status" style={{marginBottom: 10 + 'px', display: 'none'}}>
                    <select className="select" id="paymentWayCode">
                        <option value="">付款方式</option>
                        {
                            payWayType.map((item, index)=>{
                                return(
                                    <option value={item.code} key={index}>{item.name}</option>
                                );
                            })
                        }
                    </select>
                </div>
                <div className="search-reset" style={{float:'right', marginBottom: 10 + 'px', marginRight: 10 + 'px'}}>
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        );
    }
}

OrderSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default OrderSearchForm;