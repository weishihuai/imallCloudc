import React, {Component, PropTypes} from "react";

class CommonOrderSearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        $(".saleTime").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });

        $("#_orderSourceCode").jSelect();
    }

    /**
     * 查询
     */
    search() {
        const {store} = this.context;
        let params = store.getState().orderTodos.params || {page: 0, size: 10};
        let orderSourceCode = $("#_orderSourceCode").val();
        let orderNum = $("#_orderNum").val().trim();
        let formCreateDateString = $("#_formCreateDateString").val();
        let toCreateDateString = $("#_toCreateDateString").val();
        params = Object.assign(params, {
            orderSourceCode: orderSourceCode,               //订单 来源 代码
            orderNum: orderNum,                             //订单 编号
            formCreateDateString: formCreateDateString,     //销售开始时间
            toCreateDateString: toCreateDateString          //销售结束时间
        });
        this.props.commonOrderSearch(params);
    }

    /**
     * 重置
     */
    reset(){
        const {store} = this.context;
        let params = store.getState().orderTodos.params || {page: 0, size: 10};
        $("#_orderSourceCode").val('');
        $('#_orderSourceCode-clear').text("订单来源");
        $("#_orderSourceCode").jSelectReset();
        $("#_orderNum").val('');
        $("#_formCreateDateString").val('');
        $("#_toCreateDateString").val('');
        params = Object.assign(params, {
            orderSourceCode: '',        //订单 来源 代码
            orderNum: '',               //订单 编号
            formCreateDateString: '',   //销售开始时间
            toCreateDateString: ''      //销售结束时间
        });
        this.props.commonOrderSearch(params);
    }

    render() {
        let orderSource = [{code:'SALES_POS', name:'销售POS端'}, {code:'WEIXIN', name:'微信'}];

        return(
            <div className="md-box common-order-search">
                <div className="search item">
                    <input type="text" placeholder="订单编号" id="_orderNum"/>
                </div>
                <div className="status item">
                    <select id="_orderSourceCode" className="select allSelect1">
                        <option value="">订单来源</option>
                        {orderSource.map((item,index)=>{
                            return (
                                <option key={index} value={item.code}>{item.name}</option>
                            )
                        })}
                    </select>
                </div>
                <div className="sel-date item" style={{'marginLeft': 20 + 'px'}}>
                    <div className="form-group float-left w140 item">
                        <input id="_formCreateDateString" placeholder="销售开始时间" className="form-control datepicker saleTime" type="text" readOnly />
                    </div>
                    <div className="float-left form-group-txt item" style={{'verticalAlign':'middle','marginLeft': 5 + 'px','marginRight': 5 + 'px'}}>至</div>
                    <div className="form-group float-left w140 item">
                        <input id="_toCreateDateString" placeholder="销售结束时间" className="form-control datepicker saleTime" type="text" readOnly />
                    </div>
                </div>
                <a href="javascript:void(0)" className="green-btn" onClick={()=>this.search()}>查询</a>
                <a href="javascript:void(0)" className="gray-btn" onClick={()=>this.reset()}>重置</a>
            </div>
        );
    }
}

CommonOrderSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default CommonOrderSearchForm;