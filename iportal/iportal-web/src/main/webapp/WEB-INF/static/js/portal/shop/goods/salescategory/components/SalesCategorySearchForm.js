import React, {Component, PropTypes} from "react";
class SalesCategorySearchForm extends Component {
    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let categoryName = $('#searchInput').val().trim();
        params = Object.assign(params, {
            categoryName: categoryName
        });
        actions.salesCategorySearch(params);
    }

    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        return(
            <div className="lt-cont" style={{height:80 + 'px'}}>
                <div className="search">
                    <input type="text" placeholder="分类名称" id="searchInput"/>
                    <a href="javascript:void(0);" onClick={this.search.bind(this)}/>
                </div>
            </div>
        )
    }
}

SalesCategorySearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SalesCategorySearchForm;