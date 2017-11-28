import React, {Component, PropTypes} from "react";

class DrugCombinationSearchForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        require('searchableSelect');
        this.props.actions.initCategories(() => $("#searchDrugCombinationCategoryId").searchableSelect());
    }

    componentDidUpdate() {

    }

    componentDidMount(){

    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        let drugCombinationCategoryId = $("#searchDrugCombinationCategoryId").val().trim();
        let disease = $("#searchDisease").val().trim();
        let symptom = $("#searchSymptom").val().trim();
        let commonSense = $("#searchCommonSense").val().trim();
        params = Object.assign(params, {
            drugCombinationCategoryId: drugCombinationCategoryId,
            disease: disease,
            symptom: symptom,
            commonSense: commonSense
        });
        actions.drugCombinationSearch(params);
    }

    resetForm(){
        const {actions} = this.props;
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        $("#searchDrugCombinationCategoryId").val('');
        $('#searchDrugCombinationCategoryId-clear').text("分类名称");
        $(".select").jSelectReset();
        $("#searchDisease").val('');
        $("#searchSymptom").val('');
        $("#searchCommonSense").val('');
        let drugCombinationCategoryId = '';
        let disease = '';
        let symptom = '';
        let commonSense = '';
        params = Object.assign(params, {
            drugCombinationCategoryId: drugCombinationCategoryId,
            disease: disease,
            symptom: symptom,
            commonSense: commonSense
        });
        actions.drugCombinationSearch(params);
    }

    render() {
        const {store} = this.context;
        const categories = store.getState().todos.categories||[];

        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="searchDrugCombinationCategoryId" className="select">
                        <option value="">分类名称</option>
                        {categories.map((item,index)=>{
                            return (
                                <option key={index} value={item.id}>{item.categoryNm}</option>
                            )
                        })}
                    </select>
                </div>
                <div className="search">
                    <input placeholder="病症" type="text" id="searchDisease"/>
                </div>
                <div className="search">
                    <input placeholder="症状" type="text" id="searchSymptom"/>
                </div>
                <div className="search">
                    <input placeholder="常识判断" type="text" id="searchCommonSense"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.search.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.resetForm.bind(this)}/>
                </div>
            </div>
        );
    }
}

DrugCombinationSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugCombinationSearchForm;