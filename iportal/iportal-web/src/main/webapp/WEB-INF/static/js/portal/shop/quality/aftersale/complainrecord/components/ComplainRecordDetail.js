import React, {Component, PropTypes} from "react";


class ComplainRecordDetail extends Component {

    render() {
        const {store} = this.context;
        const complain = store.getState().todos.data;
        const {complainDetailModal} = this.props.actions;

        return (
            <div className="layer">
                <div className="layer-box layer-info complaints-record w960">
                    <div className="layer-header">
                        <span>投诉记录</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>complainDetailModal("", false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">投诉信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>顾客姓名</p>
                                        <span>{complain.customerName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>投诉日期</p>
                                        <span>{complain.complainDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p>手机</p>
                                        <span>{complain.mobile}</span>
                                    </div>
                                    <div className="item">
                                        <p>地址</p>
                                        <span>{complain.addr}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item item-1-2">
                                        <p><i>*</i>投诉内容</p>
                                        <span>{complain.complainCont}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">投诉商品</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>商品编码</p>
                                        <span>{complain.goodsCode}</span>
                                    </div>
                                    <div className="item">
                                        <p>商品名称</p>
                                        <span>{complain.goodsNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>规格</p>
                                        <span>{complain.spec}</span>
                                    </div>
                                    <div className="item">
                                        <p>剂型</p>
                                        <span>{complain.dosageForm}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>生产厂商</p>
                                        <span>{complain.produceManufacturer}</span>
                                    </div>
                                    <div className="item">
                                        <p>批准文号</p>
                                        <span>{complain.approvalNumber}</span>
                                    </div>
                                    <div className="item">
                                        <p>批号</p>
                                        <span>{complain.batch}</span>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <span>{complain.validDateString}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>数量</p>
                                        <span>{complain.quantity}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">处理措施</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>调查情况</p>
                                        <span>{complain.surveyCondition}</span>
                                    </div>
                                    <div className="item">
                                        <p>调查人姓名</p>
                                        <span>{complain.surveyManName}</span>
                                    </div>
                                    <div className="item">
                                        <p>调查日期</p>
                                        <span>{complain.surveyDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p>投诉意见或建议</p>
                                        <span>{complain.suggest}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>处理措施</p>
                                        <span>{complain.processMeasure}</span>
                                    </div>
                                    <div className="item">
                                        <p>处理人姓名</p>
                                        <span>{complain.processManName}</span>
                                    </div>
                                    <div className="item">
                                        <p>处理日期</p>
                                        <span>{complain.processDateString}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );

    }
}

ComplainRecordDetail.contextTypes = {
    store: React.PropTypes.object
};

export default ComplainRecordDetail