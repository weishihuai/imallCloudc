import React, {Component, PropTypes} from "react";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";

class EditGroup extends Component{

    componentWillMount(){
        const {groupId, goodsSearchParams} = this.context.store.getState().todos;
        this.props.actions.getGroupData(groupId);
        this.props.actions.getGoodsData(goodsSearchParams, groupId, 0, 5);
        this.props.actions.getSalesCategory((data) => {
            $(".select").jSelect();
        });
    }

    componentDidMount(){
        $(".page-footer").attr("style", "position:absolute");
        $("#recommend-check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".table-box-l tbody input[type='checkbox']").each(function () {
                 $(this)[0].checked = checked;
            });
        });

        $("#root").on("click", ".table-box-l tbody input[type='checkbox']", function () {
            $("#recommend-check-all")[0].checked = $(".table-box-l tbody input[type='checkbox']:checked").length == $(".table-box-l tbody input[type='checkbox']").length;
        }).on("click", ".table-box-r tbody input[type='checkbox']", function () {
            $("#goods-check-all")[0].checked = $(".table-box-r tbody input[type='checkbox']:checked").length == $(".table-box-r tbody input[type='checkbox']").length;
        });

        $("#goods-check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".table-box-r tbody input[type='checkbox']").each(function () {
                $(this)[0].checked = checked;
            });
        });
    }

    componentDidUpdate(){
        $(".page-footer").attr("style", "position:absolute;border-top:none;");
    }

    componentWillUnmount(){
        $(".page-footer").removeAttr("style");
    }

    doSearch(){
        let salesCategoryId = $("#select_class1").val();
        let keyword = $("#keyword").val().trim();
        const {groupId} = this.context.store.getState().todos;
        this.props.actions.getGoodsData({salesCategoryId, keyword}, groupId, 0, 5);
    }

    keyEvent(e){
        if (e.keyCode == 13){
            this.doSearch();
        }
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {page, goodsSearchParams, groupId} = store.getState().todos;
        this.props.actions.getGoodsData(goodsSearchParams, groupId, page.number, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {goodsSearchParams, groupId} = store.getState().todos;
        this.props.actions.getGoodsData(goodsSearchParams, groupId, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    saveBatchRecommend(e){
        $(e.target).attr("disabled", "disabled");
        let indexArr = [];
        $(".goods-checkbox:checked").each(function () {
             indexArr.push($(this).val());
        });
        if(indexArr.length == 0){
            return false;
        }
        const {store} = this.context;
        const {page, groupId} = store.getState().todos;
        let data = [];
        indexArr.map(i => {
            let goods = page.content[i];
            if(!goods.recommend){
                goods.recommend = true;
                data.push({decorationGroupId: groupId, goodsId: goods.goodsId, skuId: goods.skuId, displayPosition: 1});
            }
        });
        if(data.length == 0){
            return false;
        }
        const {saveBatchRecommend, setGoodsData} = this.props.actions;
        saveBatchRecommend(data, (type) => {
            setGoodsData(page);
            $(e.target).removeAttr("disabled");
        });
    }

    saveRecommend(e, index){
        $(e.target).attr("disabled", "disabled");
        const {store} = this.context;
        const {page, groupId} = store.getState().todos;
        let data = [];
        let goods = page.content[index];
        goods.recommend = true;
        data.push({decorationGroupId: groupId, goodsId: goods.goodsId, skuId: goods.skuId, displayPosition: 1});

        const {saveBatchRecommend, setGoodsData} = this.props.actions;
        saveBatchRecommend(data, (type) => {
            setGoodsData(page);
            $(e.target).removeAttr("disabled");
        });
    }

    deleteBatchRecommend(e){
        $(e.target).attr("disabled", "disabled");
        let ids = [];
        $(".recommend-goods:checked").each(function () {
              ids.push($(this).val());
        });
        if(ids.length == 0){
            return false;
        }
        const {groupId} = this.context.store.getState().todos;
        this.props.actions.deleteBatchRecommend(groupId, ids, (type) => {
            $(e.target).removeAttr("disabled");
            $("#recommend-check-all")[0].checked = false;
            $(".recommend-goods:checked").each(function () {
                $(this)[0].checked = false;
            });
        });
    }

    handleChange(e){
        const {groupData} = this.context.store.getState().todos;
        groupData.groupNm = e.target.value;
        this.props.actions.setGroupData(groupData);
    }

    positionKeyEvent(e){
        e.target.value = e.target.value.replace(/\D/g, '');
    }

    saveEditData(){
        const {groupData} = this.context.store.getState().todos;
        let recommendUpdateData = [];
        groupData.recommendList.map((data, index) => {
            let nVal = $(".table-box-l tbody .position:eq("+ index +")").val();
            if(nVal != data.displayPosition){
                recommendUpdateData.push({id: data.id, displayPosition: nVal});
            }
        });
        const {updateGroup, updateBatchRecommend, setEditGroup} = this.props.actions;
        if (recommendUpdateData.length > 0){
            updateBatchRecommend(groupData.id, recommendUpdateData);
        }
        updateGroup(groupData.id, groupData.groupNm, type => {
            if ("success" == type){
                showSuccess("保存成功");
                /*setEditGroup(false, '');*/
            }
        });
    }

    render(){
        const {store} = this.context;
        const {setEditGroup} = this.props.actions;
        const {groupData, salesCategoryList, page} = store.getState().todos;
        const number = page.number + 1;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 5,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage: page.totalElements <= page.size
        };
        return(
            <div className="main-box">
                <div style={{paddingBottom:"90px", marginBottom: "0"}} className="main group-main">
                    <div className="mt">
                        <div className="mt-lt" style={{paddingRight: "0"}}>
                            <h5>编辑分组</h5>
                            <a href="javascript:;">微店管理</a>
                            <span>></span>
                            <a href="javascript:;">装修管理</a>
                            <span>></span>
                            <a href="javascript:;">编辑分组</a>
                            <div style={{height: "90px"}} className="lt-cont lt-cont-f">
                                <div className="lt-cont-f-inner">
                                    <div className="lt-item">
                                        <label>分组名称：<input name="groupNm" id="groupNm" type="text" onChange={e => this.handleChange(e)} value={groupData.groupNm}/></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style={{paddingTop: "0"}} className="mc clearfix">
                        <div style={{maxHeight: "772px", overflowY: "auto"}} className="table-box-l">
                            <div className="dt clearfix"><p>已推荐商品</p></div>
                            <div className="dd">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="goods"><label><input id="recommend-check-all" type="checkbox"/>商品</label></th>
                                        <th className="price">价格</th>
                                        <th className="sort">排序</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        groupData.recommendList.map((re, index) => {
                                            return(
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont"><label><input className="recommend-goods" value={re.id} type="checkbox"/>{re.goodsNm}{re.prescription && <span>[处方药]</span>}{re.ephedrine && <span>[含麻黄碱]</span>}</label></div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">¥{re.retailPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont"><input className="position" autoComplete="off" onKeyUp={e => this.positionKeyEvent(e)} type="text" defaultValue={re.displayPosition}/></div>
                                                    </td>
                                                </tr>
                                            );
                                        })
                                    }
                                    </tbody>
                                </table>
                                <div className="fr-footer">
                                    <a onClick={e => this.deleteBatchRecommend(e)} className="white-btn" href="javascript:;" style={{marginBottom:"10px"}}>移除推荐</a>
                                </div>
                            </div>
                        </div>
                        <div className="table-box-r">
                            <div className="dt clearfix">
                                <p>选择商品推荐</p>
                                <div className="dt-search">
                                    <select id="select_class1" className="select">
                                        <option value="">销售分类</option>
                                        {salesCategoryList.map((c, i) => {
                                            return(
                                                <option key={i} value={c.id}>{c.categoryName}</option>
                                            );
                                        })}
                                    </select>
                                    <input onKeyDown={e => this.keyEvent(e)} id="keyword" style={{color: "#000"}} type="text" placeholder="搜索"/>
                                    <input onClick={() => this.doSearch()} type="button" value="搜索"/>
                                </div>
                            </div>
                            <div className="dd" style={{height: "725px"}}>
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="goods"><label><input id="goods-check-all" type="checkbox"/>商品</label></th>
                                        <th className="price">价格</th>
                                        <th className="inventory">库存</th>
                                        <th className="operation">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        page.content.map((goods, index) => {
                                            return(
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">
                                                            <label>
                                                                <input value={index} className="goods-checkbox" type="checkbox"/>
                                                                <div className="goods-info clearfix">
                                                                    <div className="goods-info-pic"><img src={goods.picUrl ? goods.picUrl : iportal + '/static/img/nopict_100X100.png'} /></div>
                                                                    <div className="goods-info-text">
                                                                        <p className="goods-info-name">{goods.goodsNm}</p>
                                                                        <p>规格：{goods.spec}</p>
                                                                        <p>生产厂家：{goods.produceManufacturer}</p>
                                                                    </div>
                                                                </div>
                                                            </label>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">¥{goods.retailPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.currentStock}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont td-cont-recommend">{goods.recommend ? '已推荐' : <a onClick={(e) => this.saveRecommend(e, index)} href="javascript:;">推荐</a>}</div>
                                                    </td>
                                                </tr>
                                            );
                                        })
                                    }

                                    </tbody>
                                </table>
                                <div className="fr-footer">
                                    <a onClick={(e) => this.saveBatchRecommend(e)} className="white-btn" href="javascript:;">批量推荐</a>
                                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div style={{position: "fixed", left: "0", right: "0", bottom: "0", zIndex: "100", padding: "20px 0"}} className="layer-keep">
                    <div className="layer-footer">
                        <a onClick={() => this.saveEditData()} href="javascript:;" className="confirm">保存</a>
                        <a onClick={() => setEditGroup(false)} href="javascript:;" className="cancel">返回</a>
                    </div>
                </div>
            </div>
        );
    }
}

EditGroup.contextTypes = {
    store: React.PropTypes.object
};

export default EditGroup;