import { PORTAL_ORG_LIST ,PORTAL_ORG_SEARCH,PORTAL_ORG_FIND_TREE,PORTAL_ORG_ADD_MODAL,PORTAL_ORG_UPDATE_MODAL,PORTAL_ORG_LIST_ROW_SELECT,PORTAL_ORG_LIST_MULTI_ROW_SELECT,PORTAL_LOAD_ZONE,PORTAL_CHANGE_CITY,PORTAL_CHANGE_PROVINCE,PORTAL_ORG_TREE_REFRESH} from '../constants/ActionTypes';
const initialState = {
  page:{},
  searchData:{isAvailable:"",searchName:"orgName",searchValue:""},
  treePId:"",
  data:{},
  modalState: false,
  updateModalState: false,
  id:null,
  ids:[],
  parentId:null,
  zones:[],
  cityZones:[],
  areaZones:[],
  provinceZoneCode:null,
  cityZoneCode:null,
  pName:null,
  isRefreshForm:true,
  isRefreshTree:false
}

export default function todos(state = initialState, action) {
  switch (action.type) {
    case PORTAL_ORG_LIST:
      return Object.assign({}, state, {
        page: action.data,
        id:null,
        ids:[]
      });
    case PORTAL_ORG_SEARCH:
    return Object.assign({}, state, {
      searchData: action.searchData,
      isRefreshForm:action.isRefreshForm
    });
    case PORTAL_ORG_FIND_TREE:
      return Object.assign({}, state, {
        treePId:action.treePId,
        pName:action.pName
      });

    case PORTAL_ORG_ADD_MODAL:
      const actualState = state.modalState==undefined?false:state.modalState;
      const newState = actualState === false;
      return Object.assign({}, state, {
        modalState:newState,
        zones:state.zones,
        data: {isAvailable:'Y',parentId:state.treePId}
      });
    case PORTAL_ORG_UPDATE_MODAL:
      const updateActualState = state.updateModalState==undefined?false:state.updateModalState;
      const updateNewState = updateActualState === false;
      if(action.data.zoneCode==undefined||action.data.zoneCode==null){
        return Object.assign({}, state, {
          updateModalState:updateNewState,
          data: action.data,
          zones:state.zones
        });
      }

      var zoneCode=action.data.zoneCode;
      var provinceZoneCode=zoneCode.substring(0,8);
      var cityZoneCode=zoneCode.substring(0,12);
      var areaZoneCode=zoneCode;
      var province=[];
      var city=[];
      var area=[];
      for(var i =0;i<state.zones.length;i++){
        if(state.zones[i].zoneCode==provinceZoneCode){
          province=state.zones[i];
          break;
        }
      }
      for(var i =0;i<province.cityList.length;i++){
        if(province.cityList[i].zoneCode==cityZoneCode){
          city=province.cityList[i];
          break;
        }
      }
      for(var i =0;i<city.areaList.length;i++){
        if(city.areaList[i].zoneCode==areaZoneCode){
          area=city.areaList[i];
          break;
        }
      }
      action.data.zoneCode=areaZoneCode;
      state.cityZones=province.cityList;
      state.areaZones=city.areaList;
      return Object.assign({}, state, {
        updateModalState:updateNewState,
        data: action.data,
        zones:state.zones,
        cityZones:state.cityZones,
        areaZones:state.areaZones,
        provinceZoneCode:provinceZoneCode,
        cityZoneCode:cityZoneCode
      });
    case PORTAL_ORG_LIST_ROW_SELECT:
      if(action.isSelected){
        return Object.assign({}, state, {
          id: action.obj.id,
          ids:state.ids.concat(action.obj.id)
        });
      }else {
        var newAppIds = [];
        state.ids.map(function(id){
          if(id!=action.obj.id){
            newAppIds.push(id)
          }
        });
        return Object.assign({}, state, {
          ids:newAppIds
        });
      }
    case PORTAL_ORG_LIST_MULTI_ROW_SELECT:
      return Object.assign({}, state, {
        ids: action.ids
      });
    case PORTAL_LOAD_ZONE:
      return Object.assign({}, state, {
        zones:action.zones
      });
    case PORTAL_CHANGE_PROVINCE:
        if(action.provinceZoneCode==0||action.provinceZoneCode==undefined){
          return Object.assign({}, state, {
            cityZones:[],
            areaZones:[],
            provinceZoneCode:null,
            cityZoneCode:null,
            data:Object.assign({}, state.data, {zoneCode:null})
          });
        }
      for(var i =0;i<state.zones.length;i++){
        if(state.zones[i].zoneCode==action.provinceZoneCode){
          state.cityZones=state.zones[i].cityList;
          break;
        }
      }
        state.areaZones=state.cityZones[0].areaList;
      return Object.assign({}, state, {
        cityZones:state.cityZones,
        areaZones:state.cityZones[0].areaList,
        data:Object.assign({}, state.data)
      });
    case PORTAL_CHANGE_CITY:
      if(action.cityZoneCode==0||action.cityZoneCode==undefined){
        return Object.assign({}, state, {
          areaZones:[]
        });
      }
      for(var i =0;i<state.cityZones.length;i++){
        if(state.cityZones[i].zoneCode==action.cityZoneCode){
          state.areaZones=state.cityZones[i].areaList;
          break;
        }
      }
      return Object.assign({}, state, {
        areaZones:state.areaZones
      });
    case PORTAL_ORG_TREE_REFRESH:
      return Object.assign({}, state, {
        isRefreshTree: action.isRefreshTree
      });
    default:
      return state
  }
}
