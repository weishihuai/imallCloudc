import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SummaryList from "./SummaryList";
import DetailList from "./DetailList";
import SearchForm from "./SearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";

class SalesDetailList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page, params, type} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size, type);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {page, params, type} = store.getState().todos;
        this.props.actions.query(params, page.number, sizePerPage, type);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        this.props.actions.query(params, page - 1, sizePerPage, type);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {type} = store.getState().todos;
        const {page} = store.getState().todos;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: [10, 20, 40],
            pageStartIndex: 1,
            page: page.number + 1,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "<",
            nextPage: ">",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage:page.totalElements <= page.size
        };
        return (
            <div>
              <div className="main-box">
                 <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>销售明细</h5>
                            <a href="javascript:;">销售管理</a>
                            <span>></span>
                            <a href="javascript:;">销售明细</a>
                            <SearchForm actions={actions}/>
                            </div>
                        </div>
                    <div className="mt-rt"></div>
                    {type == "SUMMARY" && <SummaryList actions={actions}/>}
                    {type == "DETAIL" && <DetailList actions={actions}/>}
                 </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

SalesDetailList.propTypes = {
    actions: PropTypes.object.isRequired
};

SalesDetailList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesDetailList);