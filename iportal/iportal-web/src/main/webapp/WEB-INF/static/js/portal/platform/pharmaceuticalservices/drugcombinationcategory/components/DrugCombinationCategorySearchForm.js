import React, {Component, PropTypes} from "react";

class DrugCombinationCategorySearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount(){

    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let categoryNm = $("#searchInput").val().trim();
        params = Object.assign(params, {
            categoryNm: categoryNm
        });
        actions.drugCombinationCategorySearch(params);
    }

    render() {
        return(
            <div className="lt-cont">
                <div className="search">
                    <input placeholder="分类名称" type="text" id="searchInput"/>
                    <a href="javascript:void(0);" onClick={this.search.bind(this)}/>
                </div>
            </div>
        );
    }
}

DrugCombinationCategorySearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCombinationCategorySearchForm;