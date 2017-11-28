import React, {Component, PropTypes} from "react";
import CommonConfirmComponent from "../../../../common/component/CommonConfirmComponent";

class IndexDecoration extends Component{

    componentWillMount(){
        document.body.style = "min-width: 100%; max-width: 100%; background: #f5f4fb;";
        document.body.className = "decoration";
        const {getSalesCategory, getGroupList, getShopData} = this.props.actions;
        getShopData();
        getGroupList(() => {
            new Swiper('.tab-tit', {freeMode : true, slidesPerView : 'auto'});
        });
        getSalesCategory(() => {
            new Swiper('.entry-nav', {
                pagination: '.swiper-pagination',
                slidesPerView: 5,
                paginationClickable: true,
            });
        });
    }

    componentDidMount(){

    }

    showAdd(){
        const showAdd = this.context.store.getState().todos.showAdd;
        if(!showAdd){
            this.props.actions.setShowAdd(true);
        }
    }

    saveGroup(){
        let groupNm = $("#groupNm").val().trim();
        if(!groupNm){
            return false;
        }
        const {saveGroup, setShowAdd} = this.props.actions;
        saveGroup(groupNm, type => {
            if("success" == type){
                setShowAdd(false);
                $("#groupNm").val("");
            }
        });
    }

    setEditGroup(e, groupId){
        if(e.target.tagName == "LI"){
            this.props.actions.setEditGroup(true, groupId);
        }
    }

    deleteGroup(e, groupId){
        if(e.target.tagName == "EM"){
            this.props.actions.setShowConfirm(true);
            this.groupId = groupId;
        }
    }

    groupNmClick(index){
        const {groupList} = this.context.store.getState().todos;
        groupList.map(group => {
            group.cur = false;
        });
        let group = groupList[index];
        group.cur = true;
        const {setGroupList, getGoodsList} = this.props.actions;
        setGroupList(groupList);
        getGoodsList(group.id);
    }
    moveIndexUp(olxIndex){
        const {store} = this.context;
        const {groupList} = store.getState().todos;
        const {setGroupList} = this.props.actions;
        if(olxIndex>0){
            const temp = groupList[olxIndex-1];
            groupList[olxIndex-1] = groupList[olxIndex];
            groupList[olxIndex] = temp;
        }

        setGroupList(groupList);

    }
    moveIndexDown(olxIndex){
        const {store} = this.context;
        const {groupList} = store.getState().todos;
        const {setGroupList} = this.props.actions;

        if(olxIndex<groupList.length){
            const temp = groupList[olxIndex+1];
            groupList[olxIndex+1] = groupList[olxIndex];
            groupList[olxIndex] = temp;
        }

        setGroupList(groupList);
    }
    render(){
        const {store} = this.context;
        const {setShowAdd} = this.props.actions;
        const {groupList, salesCategoryList, showAdd, goodsList, shopData, showConfirm} = store.getState().todos;
        const groupId = this.groupId;
        return(
            <div className="main-box" style={{paddingBottom: "0"}}>
                {showConfirm && <CommonConfirmComponent text="确认删除吗？" confirmBtn="确认" close={() => this.props.actions.setShowConfirm(false)} callback={() => this.props.actions.deleteGroup(groupId)} />}
                <div className="main group-main" style={{position: "static", top: "0", background: "none", borderRadius: "0"}}>
                    <div className="mt clearfix" style={{top: "0", paddingBottom: "0"}}>
                        <div className="mt-lt" style={{paddingRight: "0"}}>
                            <h5>装修管理</h5>
                            <a href="javascript:;">微店管理</a>
                            <span>></span>
                            <a href="javascript:;">装修管理</a>
                        </div>
                    </div>
                    <div className="mc" style={{paddingTop: "50px"}}>

                        <div className="group-iframe" style={{position: "relative"}}>
                            <div className="shop-header" style={{height: "14.6rem"}}>
                                <div className="address"><a href="javascript:;" className="elli">送至：XXX</a></div>
                                <a href="javascript:;" className="nearby">附近门店</a>
                                <div className="search"><a href="javascript:;">搜索药品名称</a></div>
                            </div>
                            <div className="main" style={{top: "0", minWidth: "100%"}}>
                                <div className="mt"  style={{paddingTop: "0"}}>
                                    <div className="mt-pic"><img src={shopData.logoUrl}/></div>
                                    <div className="name"><a href="javascript:;">{shopData.shopNm}</a></div>
                                    <div className="contact">
                                        <a href="javascript:;" className="wechat">微信</a>
                                        <a href="javascript:;" className="phone">电话</a>
                                    </div>
                                </div>
                                <div className="swiper-container entry-nav">
                                    <div className="swiper-wrapper" style={{height: "auto"}}>
                                        {
                                            salesCategoryList.map((c, i) => {
                                                return(
                                                    i%2 == 0 ? <div className="swiper-slide">
                                                        <div className="item"><a href="javascript:;">
                                                            <span className="item-img"><img src={iportal + "/static/img/wechat/pic70x70.jpg"} /></span>
                                                            <span className="item-tit">{c.categoryName}</span>
                                                        </a></div>
                                                        {i < salesCategoryList.length - 1 &&
                                                        <div className="item">
                                                            <a href="javascript:;">
                                                                <span className="item-img"><img src={iportal + "/static/img/wechat/pic70x70.jpg"}  /></span>
                                                                <span className="item-tit">{salesCategoryList[i + 1].categoryName}</span>
                                                            </a>
                                                        </div>
                                                        }
                                                    </div> : ''
                                                );
                                            })
                                        }
                                    </div>
                                    <div className="swiper-pagination"></div>
                                </div>
                                <div className="choice" style={{position: "relative"}}>
                                    <div className="ch-mt">
                                        <div className="mt-top">店长推荐</div>
                                        <div className="mt-bot">
                                            <div className="tab-tit swiper-container">
                                                <ul className="tab-nav swiper-wrapper clearfix">
                                                    {groupList.map((group, index) => {
                                                        return(
                                                            <li onClick={() => this.groupNmClick(index)} key={index} className={"swiper-slide" + (group.cur ? ' cur' : '')}>
                                                                <a href="javascript:;">{group.groupNm}</a>
                                                            </li>
                                                        );
                                                    })}
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="ch-mc">
                                        <div className="mc-box clearfix">
                                            {
                                                goodsList.map((goods, index) => {
                                                    return(
                                                        <div key={index} className="item">
                                                            <div className="pic"><a href="javascript:;"><img src={goods.picUrl} /></a></div>
                                                            <a href="javascript:;" className="title elli">{goods.goodsNm}</a>
                                                            <p>规格: {goods.spec}</p>
                                                            <div className="price">¥{goods.retailPrice}</div>
                                                            <div className="dw-btn"><a href="javascript:;"><img src={iportal + "/static/img/wechat/pic66x66.png"} /></a></div>
                                                        </div>
                                                    );
                                                })
                                            }
                                        </div>
                                    </div>

                                    <div className="ch-packet-edit">
                                        <div className="ch-pa-dt">推荐分组</div>
                                        <div className="ch-pa-dd">
                                            <ul>
                                                {groupList.map((group, index) => {
                                                    return(
                                                        <li key={index} onClick={(e) => this.setEditGroup(e, group.id)} className="current">{group.groupNm}({group.goodsCount}) &gt;<i className="arrow-t" onClick={()=>{this.moveIndexUp(index)}}></i><i className="arrow-b" onClick={()=>{this.moveIndexDown(index)}}></i><em onClick={(e) => this.deleteGroup(e, group.id)}></em></li>
                                                    );
                                                })}
                                            </ul>
                                            <a onClick={() => this.showAdd()} className="ch-pa-new" href="javascript:;">+创建新分组</a>
                                            {showAdd && <div className="ch-pa-b">
                                                <input id="groupNm" maxLength="32" type="text" placeholder="新分组名称"/>
                                                    <a onClick={() => this.saveGroup()} className="ch-confirm" href="javascript:;">创建</a>
                                                    <a onClick={() => setShowAdd(false)} className="ch-cancel" href="javascript:;">取消</a>
                                            </div>}
                                        </div>
                                        <em className="ch-pa-icon"></em>
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

IndexDecoration.contextTypes = {
    store: React.PropTypes.object
};

export default IndexDecoration;