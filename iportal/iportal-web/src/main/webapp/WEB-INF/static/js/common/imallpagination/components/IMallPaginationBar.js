import React, { Component, PropTypes } from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import IMallPaginationList from './IMallPaginationList';
import IMallConst from './../constants/IMallConst';

class IMallPaginationBar extends Component {

    constructor(props) {
        super(props);
    }
    isRemoteDataSource(props) {
        return (props || this.props).remote;
    }
    handlePaginationData(page, sizePerPage) {
        const { onPageChange, pageStartIndex } = this.props.options;
        if (onPageChange) {
            onPageChange(page, sizePerPage);
        }

        /*this.setState({
            currPage: page,
            sizePerPage
        });

        let normalizedPage;
        if (pageStartIndex !== undefined) {
            const offset = Math.abs(IMallConst.PAGE_START_INDEX - pageStartIndex);
            normalizedPage = page + offset;
        } else {
            normalizedPage = page;
        }

        const result = this.store.page(normalizedPage, sizePerPage).get();

        this.setState({ data: result });*/
    }

    render() {
        
        const {options} = this.props;
        return (
            <div>
                <IMallPaginationList
                    ref='pagination'
                    currPage={ options.page }
                    changePage={ this.handlePaginationData.bind(this) }
                    sizePerPage={ options.sizePerPage }
                    sizePerPageList={ options.sizePerPageList || IMallConst.IMALL_SIZE_PER_PAGE_LIST }
                    pageStartIndex={ options.pageStartIndex }
                    paginationShowsTotal={ options.paginationShowsTotal }
                    paginationSize={ options.paginationSize || IMallConst.IMALL_PAGINATION_SIZE }
                    remote={ options.remote }
                    dataSize={ options.dataTotalSize}
                    onSizePerPageList={ options.onSizePerPageList }
                    prePage={ options.prePage || IMallConst.IMALL_PRE_PAGE }
                    nextPage={ options.nextPage || IMallConst.IMALL_NEXT_PAGE }
                    firstPage={ options.firstPage || IMallConst.IMALL_FIRST_PAGE }
                    lastPage={ options.lastPage || IMallConst.IMALL_LAST_PAGE }
                    hideSizePerPage={ options.hideSizePerPage }
                    isWindow={options.isWindow}
                />
            </div>
        )
    }

}

IMallPaginationBar.propTypes = {
    actions: PropTypes.object.isRequired
};

IMallPaginationBar.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(IMallPaginationBar);