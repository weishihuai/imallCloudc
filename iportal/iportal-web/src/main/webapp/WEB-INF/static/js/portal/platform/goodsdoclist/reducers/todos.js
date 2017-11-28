import * as types from "../constants/ActionTypes";
export const fields1 = [{
    field:'goodsCode',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"商品编码"
    }
},{
    field:'id',
    validate:{
        required:false,
    }
},{
    field:'goodsNm',
    validate:{
        required:true,
        maxlength:128,
        fieldNm:"商品名称"
    }
},{
    field:'goodsTypeCode',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"商品类型"
    }
},{
    field:'pathName',
    validate:{
        required:true,
        maxlength:1024,
        fieldNm:"商品分类"
    }
},{
    field:'produceManufacturer',
    validate:{
        required:true,
        maxlength:64,
        fieldNm:"生产厂商"
    }
},{
    field:'commonNm',
    validate:{
        required:true,
        maxlength:64,
        fieldNm:"通用名称"
    }
},{
    field:'spec',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"规格"
    }
},{
    field:'unit',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"单位"
    }
},{
    field:'brandNm',
    validate:{
        required:false,
        maxlength:32,
        fieldNm:"品牌"
    }
},{
    field:'barCode',
    validate:{
        required:false,
        maxlength:32,
        fieldNm:"条形码"
    }
},{
    field:'toxicologyCode',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"毒理代码"
    }
},{
    field:'storageCondition',
    validate:{
        required:true,
        maxlength:32,
        fieldNm:"储存条件"
    }
}];

export const fields2 = [{
    field:'instructions',
    validate:{
        required:false,
        fieldNm:"说明书"
    }
},{
    field:'medicationGuide',
    validate:{
        required:false,
        fieldNm:"用药指导"
    }
}];

const initialState = {
    params:{
        page:0,
        size:10,
        keyWords:"",                    //拼音/商品编码/商品名称
        fromDateString:"",              //创建时间
        toDateString:"",                //
        approveStateCode:"",            //审核状态
        goodsCategoryId:"",            //商品分类
    },
    page:{content:[]},                  //商品列表
    goodsDocAddFormState:false,         //添加页面状态
    fields1:fields1,                    //商品档案基本字段
    fields2:fields2,                    //其他字段
    fields:[],
    dosageFormList:[],                //剂型列表
    IS_DRUG:false,                    //是否是药品
    IS_OTHER:false,                   //是否是其他
    IS_CHINESE_MEDICINE_PIECES:false, //是否是中药饮片
    IS_FOOD_HEALTH:false,             //是否是食品保健品
    IS_DAILY_NECESSITIES:false,       //是否是日用品
    IS_MEDICAL_INSTRUMENTS:false,     //是否是医疗器械
    IS_COSMETIC:false,                //是否是化妆品
    initSaveData:{
        toxicologyCode:"NON_TOXIC",     //默认无毒
        storageCondition:"ORDINARY_TEMPERATURE",//默认常温
        prescriptionDrugsTypeCode:"OTC",//默认非处方药
        isImportGoods:"N",              //默认非进口商品
        isChineseMedicineProtect:"N",   //默认非中药保护
        isEphedrine:"N",                //默认非麻黄碱
        isKeyCuring:"N",                //默认非重点养护
        isMedicalInsuranceGoods:"N",    //默认非医保商品
        isSpecialPriceGoods:"N",        //默认非特价商品
        isSplitZero:"N",                //默认不拆零
    },
    detailData:{},                      //商品档案详细数据
    goodsDocCategories:[],              //商品档案分类
    goodsDocUpdateFormState:false,      //商品档案编辑页面状态
    goodsDocApproveAndDetailState:false,//商品档案审核和详情页面状态
    isApproveOrDetail:false,            //审核页面还是详情页面  false审核页面    true详情
    isUpdateToWaitApprove:"N",          //编辑后是否提交审核
    pictFileList:[],                    //图片列表
    otherFileList:[],                   //其他文件列表
    goodsCategoryList:[],               //商品分类列表
    confirmModelState:false,            //删除提示的
    goodsId:"",                         //商品档案id
};

export default function todos(state = initialState, action) {
  switch (action.type) {
      case types.GOODS_LIST:                          //列表数据
          return Object.assign({},state,{
              page:action.data
          });
      case types.GOODS_LIST_SET_PARAMS:               //设置搜索参数
          return Object.assign({},state,{
              params:action.params
          });
      case types.GOODS_DOC_ADD_FORM_STATE:
          let fields = [];
          if(action.isOpen){
              state.fields1.map((field)=>{
                  fields.push(field);
              });
              state.fields2.map((field)=>{
                  fields.push(field);
              });
          }
          return Object.assign({},state,{
              goodsDocAddFormState:action.isOpen,
              IS_DRUG:false,
              IS_OTHER:false,
              IS_CHINESE_MEDICINE_PIECES:false,
              IS_FOOD_HEALTH:false,
              IS_DAILY_NECESSITIES:false,
              IS_MEDICAL_INSTRUMENTS:false,
              IS_COSMETIC:false,
              fields:fields,
              pictFileList:[],
              otherFileList:[],
              initSaveData:{
                  toxicologyCode:"NON_TOXIC",     //默认无毒
                  storageCondition:"ORDINARY_TEMPERATURE",//默认常温
                  prescriptionDrugsTypeCode:"OTC",  //默认非处方药
                  isImportGoods:"N",              //默认非进口商品
                  isChineseMedicineProtect:"N",   //默认非中药保护
                  isEphedrine:"N",                //默认非麻黄碱
                  isKeyCuring:"N",                //默认非重点养护
                  isMedicalInsuranceGoods:"N",    //默认非医保商品
                  isSpecialPriceGoods:"N",        //默认非特价商品
                  isSplitZero:"N",                //默认不拆零
              },
          });
      case types.GOODS_DOC_ADD_FORM_CHANGE_GOODS_TYPE:
          let [_IS_DRUG,_IS_OTHER,_IS_CHINESE_MEDICINE_PIECES,_IS_FOOD_HEALTH,_IS_DAILY_NECESSITIES,_IS_MEDICAL_INSTRUMENTS,_IS_COSMETIC] = [false,false,false,false,false,false,false];
          let addViewFields = [];
          state.fields1.map((field)=>{
              addViewFields.push(field);
          });
          switch (action.data){
              case "DRUG":
                  _IS_DRUG = true;
                  addViewFields.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  addViewFields.push({field:'approvalNumberTermString', validate:{required:true, maxlength:64, fieldNm:"批准文号期限"}});
                  addViewFields.push({field:'isImportGoods', validate:{required:true, maxlength:1, fieldNm:"是否进口商品"}});
                  addViewFields.push({field:'isChineseMedicineProtect', validate:{required:true, maxlength:1, fieldNm:"是否中药保护"}});
                  addViewFields.push({field:'approveDateString', validate:{required:false, fieldNm:"批准日期"}});
                  addViewFields.push({field:'dosageForm', validate:{required:true, maxlength:64, fieldNm:"剂型"}});
                  addViewFields.push({field:'prescriptionDrugsTypeCode', validate:{required:true, maxlength:32, fieldNm:"处方药"}});
                  addViewFields.push({field:'isEphedrine', validate:{required:true, maxlength:1, fieldNm:"麻黄碱"}});
                  addViewFields.push({field:'isKeyCuring', validate:{required:true, maxlength:1, fieldNm:"重点养护"}});
                  addViewFields.push({field:'isMedicalInsuranceGoods', validate:{required:true, maxlength:1, fieldNm:"医保商品"}});
                  addViewFields.push({field:'medicalInsuranceNum', validate:{required:true, maxlength:1024, fieldNm:"医保号"}});
                  break;
              case "OTHER":
                  _IS_OTHER = true;
                  addViewFields.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  addViewFields.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              case "CHINESE_MEDICINE_PIECES":
                  _IS_CHINESE_MEDICINE_PIECES = true;
                  addViewFields.push({field:'approvalNumber', validate:{required:false, maxlength:64, fieldNm:"批准文号"}});
                  addViewFields.push({field:'approvalNumberTermString', validate:{required:false, fieldNm:"批准文号期限"}});
                  addViewFields.push({field:'isImportGoods', validate:{required:true, maxlength:1, fieldNm:"是否进口商品"}});
                  addViewFields.push({field:'isChineseMedicineProtect', validate:{required:true, maxlength:1, fieldNm:"是否中药保护"}});
                  addViewFields.push({field:'approveDateString', validate:{required:false, fieldNm:"批准日期"}});
                  addViewFields.push({field:'dosageForm', validate:{required:true, maxlength:64, fieldNm:"剂型"}});
                  addViewFields.push({field:'prescriptionDrugsTypeCode', validate:{required:true, maxlength:32, fieldNm:"处方药"}});
                  addViewFields.push({field:'isEphedrine', validate:{required:true, maxlength:1, fieldNm:"麻黄碱"}});
                  addViewFields.push({field:'isKeyCuring', validate:{required:true, maxlength:1, fieldNm:"重点养护"}});
                  addViewFields.push({field:'isMedicalInsuranceGoods', validate:{required:true, maxlength:1, fieldNm:"医保商品"}});
                  addViewFields.push({field:'medicalInsuranceNum', validate:{required:true, maxlength:1024, fieldNm:"医保号"}});
                  addViewFields.push({field:'productionPlace', validate:{required:true, maxlength:32, fieldNm:"产地"}});
                  addViewFields.push({field:'effect', validate:{required:false, maxlength:512, fieldNm:"功效"}});
                  break;
              case "FOOD_HEALTH":
                  _IS_FOOD_HEALTH = true;
                  addViewFields.push({field:'foodHygieneLicenceNum', validate:{required:true, maxlength:64, fieldNm:"食品卫生许可证号"}});
                  addViewFields.push({field:'productionDateString', validate:{required:true, fieldNm:"生产日期"}});
                  addViewFields.push({field:'expirationDateString', validate:{required:true, fieldNm:"保质期"}});
                  addViewFields.push({field:'healthCareFunc', validate:{required:true, maxlength:128, fieldNm:"保健功能"}});
                  addViewFields.push({field:'appropriateCrowd', validate:{required:false, maxlength:64, fieldNm:"适宜人群"}});
                  addViewFields.push({field:'notAppropriateCrowd', validate:{required:false, maxlength:64, fieldNm:"不适宜人群"}});
                  addViewFields.push({field:'edibleMethodAndDosage', validate:{required:false, maxlength:64, fieldNm:"食用方法及用量"}});
                  addViewFields.push({field:'storageMethod', validate:{required:false, maxlength:64, fieldNm:"贮藏方法"}});
                  addViewFields.push({field:'execStandard', validate:{required:false, maxlength:64, fieldNm:"执行标准"}});
                  addViewFields.push({field:'effectComposition', validate:{required:false, maxlength:64, fieldNm:"功效成分"}});
                  addViewFields.push({field:'notice', validate:{required:false, maxlength:128, fieldNm:"注意事项"}});
                  break;
              case "DAILY_NECESSITIES":
                  _IS_DAILY_NECESSITIES = true;
                  addViewFields.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  addViewFields.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              case "MEDICAL_INSTRUMENTS":
                  _IS_MEDICAL_INSTRUMENTS = true;
                  addViewFields.push({field:'regNum', validate:{required:true, maxlength:64, fieldNm:"注册号"}});
                  addViewFields.push({field:'regRegistrationFormNum', validate:{required:true, maxlength:64,fieldNm:"注册登记表号"}});
                  addViewFields.push({field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  addViewFields.push({field:'applyRange', validate:{required:false, maxlength:32, fieldNm:"适用范围"}});
                  break;
              case "COSMETIC":
                  _IS_COSMETIC = true;
                  addViewFields.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  addViewFields.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              default:
                  return Object.assign({},state,{
                      IS_DRUG:false,                    //是否是药品
                      IS_OTHER:false,                   //是否是其他
                      IS_CHINESE_MEDICINE_PIECES:false, //是否是中药饮片
                      IS_FOOD_HEALTH:false,             //是否是食品保健品
                      IS_DAILY_NECESSITIES:false,       //是否是日用品
                      IS_MEDICAL_INSTRUMENTS:false,     //是否是医疗器械
                      IS_COSMETIC:false,                //是否是化妆品
                  });
          }
          state.fields2.map((field)=>{
              addViewFields.push(field);
          });
          return Object.assign({},state,{
              IS_DRUG:_IS_DRUG,
              IS_OTHER:_IS_OTHER,
              IS_CHINESE_MEDICINE_PIECES:_IS_CHINESE_MEDICINE_PIECES,
              IS_FOOD_HEALTH:_IS_FOOD_HEALTH,
              IS_DAILY_NECESSITIES:_IS_DAILY_NECESSITIES,
              IS_MEDICAL_INSTRUMENTS:_IS_MEDICAL_INSTRUMENTS,
              IS_COSMETIC:_IS_COSMETIC,
              fields:addViewFields
          });
      case types.GOODS_DOC_GET_DOSAGE_FORM_LIST:
          return Object.assign({},state,{
              dosageFormList:action.data
          });
      case types.GOODS_DOC_ADD_SET_GOODS_CATEGORY:
          return Object.assign({},state,{
              initSaveData:action.data
          });
      case types.GOODS_DOC_UPDATE_FORM_STATE:
          let [IS_DRUG,IS_OTHER,IS_CHINESE_MEDICINE_PIECES,IS_FOOD_HEALTH,IS_DAILY_NECESSITIES,IS_MEDICAL_INSTRUMENTS,IS_COSMETIC] = [false,false,false,false,false,false,false];
          let fieldsUpdate = [];
          state.fields1.map((field)=>{
              fieldsUpdate.push(field);
          });
          switch (state.detailData.goodsTypeCode){
              case "DRUG":
                  IS_DRUG = true;
                  fieldsUpdate.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  fieldsUpdate.push({field:'approvalNumberTermString', validate:{required:true, maxlength:64, fieldNm:"批准文号期限"}});
                  fieldsUpdate.push({field:'isImportGoods', validate:{required:true, maxlength:1, fieldNm:"是否进口商品"}});
                  fieldsUpdate.push({field:'isChineseMedicineProtect', validate:{required:true, maxlength:1, fieldNm:"是否中药保护"}});
                  fieldsUpdate.push({field:'approveDateString', validate:{required:false, fieldNm:"批准日期"}});
                  fieldsUpdate.push({field:'dosageForm', validate:{required:true, maxlength:64, fieldNm:"剂型"}});
                  fieldsUpdate.push({field:'prescriptionDrugsTypeCode', validate:{required:true, maxlength:32, fieldNm:"处方药"}});
                  fieldsUpdate.push({field:'isEphedrine', validate:{required:true, maxlength:1, fieldNm:"麻黄碱"}});
                  fieldsUpdate.push({field:'isKeyCuring', validate:{required:true, maxlength:1, fieldNm:"重点养护"}});
                  fieldsUpdate.push({field:'isMedicalInsuranceGoods', validate:{required:true, maxlength:1, fieldNm:"医保商品"}});
                  fieldsUpdate.push({field:'medicalInsuranceNum', validate:{required:true, maxlength:1024, fieldNm:"医保号"}});
                  break;
              case "OTHER":
                  IS_OTHER = true;
                  fieldsUpdate.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  fieldsUpdate.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              case "CHINESE_MEDICINE_PIECES":
                  IS_CHINESE_MEDICINE_PIECES = true;
                  fieldsUpdate.push({field:'approvalNumber', validate:{required:false, maxlength:64, fieldNm:"批准文号"}});
                  fieldsUpdate.push({field:'approvalNumberTermString', validate:{required:false, fieldNm:"批准文号期限"}});
                  fieldsUpdate.push({field:'isImportGoods', validate:{required:true, maxlength:1, fieldNm:"是否进口商品"}});
                  fieldsUpdate.push({field:'isChineseMedicineProtect', validate:{required:true, maxlength:1, fieldNm:"是否中药保护"}});
                  fieldsUpdate.push({field:'approveDateString', validate:{required:false, fieldNm:"批准日期"}});
                  fieldsUpdate.push({field:'dosageForm', validate:{required:true, maxlength:64, fieldNm:"剂型"}});
                  fieldsUpdate.push({field:'prescriptionDrugsTypeCode', validate:{required:true, maxlength:32, fieldNm:"处方药"}});
                  fieldsUpdate.push({field:'isEphedrine', validate:{required:true, maxlength:1, fieldNm:"麻黄碱"}});
                  fieldsUpdate.push({field:'isKeyCuring', validate:{required:true, maxlength:1, fieldNm:"重点养护"}});
                  fieldsUpdate.push({field:'isMedicalInsuranceGoods', validate:{required:true, maxlength:1, fieldNm:"医保商品"}});
                  fieldsUpdate.push({field:'medicalInsuranceNum', validate:{required:true, maxlength:1024, fieldNm:"医保号"}});
                  fieldsUpdate.push({field:'productionPlace', validate:{required:true, maxlength:32, fieldNm:"产地"}});
                  fieldsUpdate.push({field:'effect', validate:{required:false, maxlength:512, fieldNm:"功效"}});
                  break;
              case "FOOD_HEALTH":
                  IS_FOOD_HEALTH = true;
                  fieldsUpdate.push({field:'foodHygieneLicenceNum', validate:{required:true, maxlength:64, fieldNm:"食品卫生许可证号"}});
                  fieldsUpdate.push({field:'productionDateString', validate:{required:true, fieldNm:"生产日期"}});
                  fieldsUpdate.push({field:'expirationDateString', validate:{required:true, fieldNm:"保质期"}});
                  fieldsUpdate.push({field:'healthCareFunc', validate:{required:true, maxlength:128, fieldNm:"保健功能"}});
                  fieldsUpdate.push({field:'appropriateCrowd', validate:{required:false, maxlength:64, fieldNm:"适宜人群"}});
                  fieldsUpdate.push({field:'notAppropriateCrowd', validate:{required:false, maxlength:64, fieldNm:"不适宜人群"}});
                  fieldsUpdate.push({field:'edibleMethodAndDosage', validate:{required:false, maxlength:64, fieldNm:"食用方法及用量"}});
                  fieldsUpdate.push({field:'storageMethod', validate:{required:false, maxlength:64, fieldNm:"贮藏方法"}});
                  fieldsUpdate.push({field:'execStandard', validate:{required:false, maxlength:64, fieldNm:"执行标准"}});
                  fieldsUpdate.push({field:'effectComposition', validate:{required:false, maxlength:64, fieldNm:"功效成分"}});
                  fieldsUpdate.push({field:'notice', validate:{required:false, maxlength:128, fieldNm:"注意事项"}});
                  break;
              case "DAILY_NECESSITIES":
                  IS_DAILY_NECESSITIES = true;
                  fieldsUpdate.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  fieldsUpdate.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              case "MEDICAL_INSTRUMENTS":
                  IS_MEDICAL_INSTRUMENTS = true;
                  fieldsUpdate.push({field:'regNum', validate:{required:true, maxlength:64, fieldNm:"注册号"}});
                  fieldsUpdate.push({field:'regRegistrationFormNum', validate:{required:true, maxlength:64,fieldNm:"注册登记表号"}});
                  fieldsUpdate.push({field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  fieldsUpdate.push({field:'applyRange', validate:{required:false, maxlength:32, fieldNm:"适用范围"}});
                  break;
              case "COSMETIC":
                  IS_COSMETIC = true;
                  fieldsUpdate.push({field:'approvalNumber', validate:{required:true, maxlength:64, fieldNm:"批准文号"}});
                  fieldsUpdate.push( {field:'manufacturerAddr', validate:{required:false, maxlength:32, fieldNm:"厂家地址"}});
                  break;
              default:
                  return Object.assign({},state,{
                      IS_DRUG:false,                    //是否是药品
                      IS_OTHER:false,                   //是否是其他
                      IS_CHINESE_MEDICINE_PIECES:false, //是否是中药饮片
                      IS_FOOD_HEALTH:false,             //是否是食品保健品
                      IS_DAILY_NECESSITIES:false,       //是否是日用品
                      IS_MEDICAL_INSTRUMENTS:false,     //是否是医疗器械
                      IS_COSMETIC:false,                //是否是化妆品
                  });
          }
          state.fields2.map((field)=>{
              fieldsUpdate.push(field);
          });
          return Object.assign({},state,{
              goodsDocUpdateFormState:action.isOpen,
              IS_DRUG:IS_DRUG,
              IS_OTHER:IS_OTHER,
              IS_CHINESE_MEDICINE_PIECES:IS_CHINESE_MEDICINE_PIECES,
              IS_FOOD_HEALTH:IS_FOOD_HEALTH,
              IS_DAILY_NECESSITIES:IS_DAILY_NECESSITIES,
              IS_MEDICAL_INSTRUMENTS:IS_MEDICAL_INSTRUMENTS,
              IS_COSMETIC:IS_COSMETIC,
              fields:fieldsUpdate
          });
      case types.GOODS_DOC_DETAIL_DATA:
          let data = action.data;
          data.toxicologyCode = data.toxicologyCode?data.toxicologyCode:"NON_TOXIC";
          data.storageCondition = data.storageCondition?data.storageCondition:"ORDINARY_TEMPERATURE";
          data.prescriptionDrugsTypeCode = data.prescriptionDrugsTypeCode?data.prescriptionDrugsTypeCode:"OTC";
          data.isImportGoods = data.isImportGoods?data.isImportGoods:"N";
          data.isChineseMedicineProtect = data.isChineseMedicineProtect?data.isChineseMedicineProtect:"N";
          data.isEphedrine = data.isEphedrine?data.isEphedrine:"N";
          data.isKeyCuring = data.isKeyCuring?data.isKeyCuring:"N";
          data.isMedicalInsuranceGoods = data.isMedicalInsuranceGoods?data.isMedicalInsuranceGoods:"N";
          data.isSpecialPriceGoods = data.isSpecialPriceGoods?data.isSpecialPriceGoods:"N";
          data.isSplitZero = data.isSplitZero?data.isSplitZero:"N";
          return Object.assign({},state,{
              detailData:data,
              pictFileList:data.pictFileVoList,
              otherFileList:data.otherFileVoList,
          });
      case types.GOODS_DOC_UPDATE_SET_GOODS_CATEGORY:
          return Object.assign({},state,{
              detailData:action.data
          });
      case types.GOODS_DOC_APPROVE_OR_DETAIL_STATE:
          let [IS_DRUG_,IS_OTHER_,IS_CHINESE_MEDICINE_PIECES_,IS_FOOD_HEALTH_,IS_DAILY_NECESSITIES_,IS_MEDICAL_INSTRUMENTS_,IS_COSMETIC_] = [false,false,false,false,false,false,false];
          switch (state.detailData.goodsTypeCode){
              case "DRUG":
                  IS_DRUG_ = true;
                  break;
              case "OTHER":
                  IS_OTHER_ = true;
                  break;
              case "CHINESE_MEDICINE_PIECES":
                  IS_CHINESE_MEDICINE_PIECES_ = true;
                  break;
              case "FOOD_HEALTH":
                  IS_FOOD_HEALTH_ = true;
                  break;
              case "DAILY_NECESSITIES":
                  IS_DAILY_NECESSITIES_ = true;
                  break;
              case "MEDICAL_INSTRUMENTS":
                  IS_MEDICAL_INSTRUMENTS_ = true;
                  break;
              case "COSMETIC":
                  IS_COSMETIC_ = true;
                  break;
              default:
                  return ;
          }
          return Object.assign({},state,{
              goodsDocApproveAndDetailState:action.isOpen,
              IS_DRUG:IS_DRUG_,
              IS_OTHER:IS_OTHER_,
              IS_CHINESE_MEDICINE_PIECES:IS_CHINESE_MEDICINE_PIECES_,
              IS_FOOD_HEALTH:IS_FOOD_HEALTH_,
              IS_DAILY_NECESSITIES:IS_DAILY_NECESSITIES_,
              IS_MEDICAL_INSTRUMENTS:IS_MEDICAL_INSTRUMENTS_,
              IS_COSMETIC:IS_COSMETIC_,
          });
      case types.GOODS_DOC_IS_APPROVE_OR_DETAIL:
          return Object.assign({},state,{
              isApproveOrDetail:action.isApproveOrDetail
          });
      case types.GOODS_DOC_IS_UPDATE_TO_WAIT_APPROVE:
          return Object.assign({},state,{
              isUpdateToWaitApprove:action.data
          });
      case types.GOODS_DOC_ADD_FORM_ADD_PICTURES:
          return Object.assign({},state,{
              pictFileList:action.data
          });
      case types.GOODS_DOC_ADD_FORM_ADD_OTHER_FILES:
          return Object.assign({},state,{
              otherFileList:action.data
          });
      case types.GOODS_CATEGORY_LIST:
          return Object.assign({},state,{
              goodsCategoryList:action.data
          });
      case types.GOODS_DOC_CONFIRM_DELETE_VIEW_STATE:
          return Object.assign({},state,{
              goodsId:action.goodsId,
              confirmModelState:action.data
          });
      default:
          return state
  }
}
