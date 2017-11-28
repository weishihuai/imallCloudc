import { REQ_PORTAL_MENUS,SELECT_NEXT_MENU_ITEM,SELECT_LAST_MENU_ITEM, SELECT_ACTIVE_MENU_ITEM,MENU_ROUTER_ON_ENTER } from '../constants/ActionTypes'

const initialState = {
  menus: [], //第一级
  nextMenuItem:{iconClass:"", menuNm:"", nodeCode:"", subChildList:[]}, //第二级
  threeMenuItem:{iconClass:"", menuNm:"", nodeCode:"", subChildList:[]}, //第三级
  lastMenuItem:{iconClass:"", menuNm:"", nodeCode:"", subChildList:[]},  //第四级
  activeMenuItem:{iconClass:"", menuNm:"", nodeCode:"", subChildList:[]}, //第五级
  panelTitle:"",
  menuNodeCode:""
}


export default function menuTodos(state = initialState, action) {
  switch (action.type) {
    case REQ_PORTAL_MENUS:{
      var menuNodeCode = state.menuNodeCode;

      if(menuNodeCode==null || menuNodeCode.length==0){
        //处理访问http://localhost:8080/iportal/main.html时，默认展开
        if(action.data.length>0){
          const nextObject = action.data[0];
          if(nextObject.subChildList.length>0){
             menuNodeCode = nextObject.subChildList[0].nodeCode;
          }
        }
      }

      const nextMenuCode = menuNodeCode.length >= 8*2 ? menuNodeCode.substring(0, 8*2) : "";
      const threeMenuCode = menuNodeCode.length >= 8*3 ? menuNodeCode.substring(0, 8*3) : "";
      const lastMenuCode = menuNodeCode.length >= 8*4 ? menuNodeCode.substring(0, 8*4) : "";
      const activeMenuCode = menuNodeCode.length >= 8*5 ? menuNodeCode.substring(0, 8*5) : "";
      const nextArray = action.data.filter(menu => menu.nodeCode==nextMenuCode);
      const nextObject = nextArray.length>0 ? nextArray[0] : {iconClass:"", menuNm:"", nodeCode:"", subChildList:[]};

      const threeArray = nextObject.subChildList.length>0 ? nextObject.subChildList.filter(menu => menu.nodeCode==threeMenuCode) : [];
      const threeObject = threeArray.length>0 ? threeArray[0] : {iconClass:"", menuNm:"", nodeCode:"", subChildList:[]};

      const lastArray = threeObject.subChildList.length>0 ? threeObject.subChildList.filter(menu => menu.nodeCode==lastMenuCode) : [];
      const lastObject = lastArray.length>0 ? lastArray[0] : {iconClass:"", menuNm:"", nodeCode:"", subChildList:[]};

      const activeArray = lastObject.subChildList.length>0 ? lastObject.subChildList.filter(menu => menu.nodeCode==activeMenuCode) : [];
      const activeObject = activeArray.length>0 ? activeArray[0] : {iconClass:"", menuNm:"", nodeCode:"", routerKey:"", subChildList:[]};

      console.log(nextArray);
      console.log(lastArray);
      console.log(activeArray);
      return Object.assign({}, state, {
        menus: action.data,
        nextMenuItem: nextObject,
        threeMenuItem: threeObject,
        lastMenuItem: lastObject,
        activeMenuItem: activeObject,
        panelTitle: activeArray.length>0 ? activeObject.menuNm : (lastArray.length>0 ? lastObject.menuNm : "")
      });
    }
    case SELECT_NEXT_MENU_ITEM:
      return Object.assign({}, state, {
        nextMenuItem: action.data
      });
    case SELECT_LAST_MENU_ITEM:{
      var activeObject = {iconClass:"", menuNm:"", nodeCode:"", routerKey:"", subChildList:[]};
      for (var child of action.data.subChildList) {
        if(child.menuType=="MENU"){
          activeObject = child;
          break;
        }
      }

      return Object.assign({}, state, {
        lastMenuItem: action.data,
        activeMenuItem: activeObject,
        panelTitle:action.data.subChildList.length > 0 ? activeObject.menuNm : action.data.menuNm
      });
    }
    case SELECT_ACTIVE_MENU_ITEM:
      return Object.assign({}, state, {
        activeMenuItem: action.data,
        panelTitle:action.data.menuNm
      });
    case MENU_ROUTER_ON_ENTER:{
      return Object.assign({}, state, {
        menuNodeCode: action.data
      });
    }
    default:
      return state
  }
}
