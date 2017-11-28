import * as types from "../constants/ActionTypes";

export const fields1 = [{
  field:'goodsCode',
  validate:{
    required:true,
    maxlength:128,
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
  field:'sellCategoryIds',
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
  field:'retailPrice',
  validate:{
    required:true,
    fieldNm:"零售价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"零售价须大于零,且只能带两位小数"
  }
},{
  field:'memberPrice',
  validate:{
    required:false,
    fieldNm:"会员价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"会员价须大于零,且只能带两位小数"
  }
},{
  field:'marketPrice',
  validate:{
    required:false,
    fieldNm:"市场价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"市场价须大于零,且只能带两位小数"
  }
},{
  field:'costPrice',
  validate:{
    required:false,
    fieldNm:"成本价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"成本价须大于零,且只能带两位小数"
  }
},{
  field:'currentStock',
  validate:{
    required:false,
    fieldNm:"当前库存",
    regx:/^([1-9]\d*)$/,
    error:"当前库存为大于零的正整数,最多七位"
  }
},{
  field:'securityStock',
  validate:{
    required:false,
    fieldNm:"安全库存",
    regx:/^([1-9]\d*)$/,
    error:"安全库存为大于零的正整数,最多七位"
  }
},{
  field:'purchaseTaxRate',
  validate:{
    required:false,
    fieldNm:"采购税率",
      regx:/^(100|[1-9]\d|\d)$/,
      error:"采购税率为0到100的整数"
  }
},{
  field:'sellTaxRate',
  validate:{
    required:false,
    fieldNm:"销售税率",
      regx:/^(100|[1-9]\d|\d)$/,
      error:"销售税率为0到100的整数"
  }
},{
  field:'isSpecialPriceGoods',
  validate:{
    required:true,
    maxlength:1,
    fieldNm:"特价商品"
  }
},{
  field:'isSplitZero',
  validate:{
    required:true,
    maxlength:1,
    fieldNm:"是否拆零",

  }
},{
  field:'splitZeroUnit',
  validate:{
    required:true,
    maxlength:32,
    fieldNm:"拆零单位"
  }
},{
  field:'splitZeroQuantity',
  validate:{
    required:true,
    fieldNm:"拆零数量",
    regx:/^([1-9]\d*)$/,
    error:"拆零数量为大于零的正整数,最多七位"
  }
},{
  field:'splitZeroSpec',
  validate:{
    required:true,
    maxlength:32,
    fieldNm:"拆零规格"
  }
},{
  field:'splitZeroRetailPrice',
  validate:{
    required:true,
    fieldNm:"拆零零售价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"拆零零售价须大于零,且只能带两位小数"
  }
},{
  field:'splitZeroMemberPrice',
  validate:{
    required:true,
    fieldNm:"拆零会员价",
    regx:/^(0|[1-9]\d*)(\.\d{1,2})?$/,
    error:"拆零会员价须大于零,且只能带两位小数"
  }
},{
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
},{
  field:'submitIdea',
  validate:{
    required:false,
    maxlength:64,
    fieldNm:"提交意见"
  }
},{
  field:'remark',
  validate:{
    required:false,
    maxlength:64,
    fieldNm:"备注"
  }
}];
export const enableField = [{
  field:'reason',
  validate:{
    required:true,
    maxlength:128,
    fieldNm:"启用原因"
  }
}];



const initialState = {
  params:{
    page:0,
    size:10,
    storageSpaceId:"",              //货位ID
    isEnable:"",                    //是否启用
    approveStateCode:"",            //审核状态
    keyWords:"",                    //拼音/商品编码/商品名称
    fromDateString:"",              //创建时间
    toDateString:"",                //
  },
  goodsAddFormState:false,          //产品新增页面状态\
  page:{content:[]},                //商品列表
  storageSpaceList:[],              //货位
  salesCategoryList:[],             //销售分类
  dosageFormList:[],                //剂型列表
  IS_DRUG:false,                    //是否是药品
  IS_OTHER:false,                   //是否是其他
  IS_CHINESE_MEDICINE_PIECES:false, //是否是中药饮片
  IS_FOOD_HEALTH:false,             //是否是食品保健品
  IS_DAILY_NECESSITIES:false,       //是否是日用品
  IS_MEDICAL_INSTRUMENTS:false,     //是否是医疗器械
  IS_COSMETIC:false,                //是否是化妆品
  initSaveData:{
    sellCategoryIds:[],             //保存的商品销售分类ids
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
    isFirstSell:"N",                //默认非首营
    isEnable:"Y",                   //默认药品启用状态
  },
  detailData:{},                    //商品详细数据
  goodsUpdateFormState:false,       //编辑页面状态
  fields:[],                        //所有字段
  fields2:fields2,
  fields1:fields1,
  echoSellCategoryIds:[],
  goodsDocSelectListState:false,    //商品档案选择列表状态
  goodsDocPage:{content:[]},        //商品档案选择列表
  docParams:{
    page:0,
    size:10,
    docGoodsNm:"",                  //商品名称
    docApproveNumber:"",            //批准文号
    docProduceManufacturer:"",      //生产厂家
  },
  selectedId:"",                    //已选中的id
  goodsDocDetail:{},                //商品档案详情
  goodsDetailViewState:false,       //商品详情
  pictFileList:[],                  //图片列表
  otherFileList:[],                 //其他文件列表
  enableFormState:false,            //启用禁用页面状态
  enableData:{},                    //启用禁用页面初始值
  recordPage: {content: [], number: 0, totalElements: 0, size: 10},   //修改记录列表
  recordPageView:false,             //修改记录列表
  enableField:enableField,          //
};



export default function todos(state = initialState, action) {
  switch (action.type) {
    case types.GOODS_ADD_FORM_STATE:                //添加页面状态
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
        goodsAddFormState:action.isOpen,
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
              sellCategoryIds:[],             //保存的商品销售分类ids
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
              isFirstSell:"N",                //默认非首营
              isEnable:"Y",                   //默认药品启用状态
          },
      });
    case types.GOODS_LIST:                          //列表数据
      return Object.assign({},state,{
        page:action.data
      });
    case types.GOODS_LIST_SET_PARAMS:               //设置搜索参数
      return Object.assign({},state,{
        params:action.params
      });

    case types.GOODS_LIST_STORAGE_SPACE_LIST:       //列表货位下拉框
      return Object.assign({},state,{
        storageSpaceList:action.data
      });

    case types.GOODS_ADD_FORM_SALES_CATEGORY_LIST:  //商品添加页面销售分类下拉框
      return Object.assign({},state,{
        salesCategoryList:action.data
      });

    case types.GOODS_ADD_FORM_DOSAGE_FORM_LIST:     //剂型下拉框
          return Object.assign({},state,{
            dosageFormList:action.data
          });
    case types.GOODS_ADD_FORM_CHANGE_GOODS_TYPE:
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
    case types.GOODS_DETAIL_DATA:
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
      data.isFirstSell = data.isFirstSell?data.isFirstSell:"N";
      data.isEnable = data.isEnable?data.isEnable:"Y";
      return Object.assign({},state,{
        detailData:data,
        pictFileList:data.pictFileVoList,
        otherFileList:data.otherFileVoList,
        echoSellCategoryIds:action.data.sellCategoryIds
      });
    case types.GOODS_UPDATE_FORM_STATE:
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
        goodsUpdateFormState:action.isOpen,
        IS_DRUG:IS_DRUG,
        IS_OTHER:IS_OTHER,
        IS_CHINESE_MEDICINE_PIECES:IS_CHINESE_MEDICINE_PIECES,
        IS_FOOD_HEALTH:IS_FOOD_HEALTH,
        IS_DAILY_NECESSITIES:IS_DAILY_NECESSITIES,
        IS_MEDICAL_INSTRUMENTS:IS_MEDICAL_INSTRUMENTS,
        IS_COSMETIC:IS_COSMETIC,
        fields:fieldsUpdate,
      });
    case types.GOODS_ADD_FORM_SAVE_DATA_RE_INIT:
      return Object.assign({},state,{
        initSaveData:action.data
      });

    case types.GOODS_UPDATE_FORM_CHANGE_SELL_CATEGORY_IDS:
      return Object.assign({},state,{
        echoSellCategoryIds:action.data||[]
      });
    case types.GOODS_DOC_SELECT_LIST_STATE:
      return Object.assign({},state,{
        goodsDocSelectListState:action.isOpen,
        selectedId:""

      });
    case types.GOODS_DOC_SELECT_LIST:
      return Object.assign({},state,{
        goodsDocPage:action.data
      });
    case types.GOODS_DOC_SELECT_SET_SEARCH_PARAM:
      return Object.assign({},state,{
        docParams:action.data
      });
    case types.GOODS_DOC_SELECT_LIST_SELECTED_ID:
      return Object.assign({},state,{
        selectedId:action.data
      });
    case types.GOODS_DOC_SELECT_DETAIL:
      return Object.assign({},state,{
        goodsDocDetail:action.data
      });
    case types.GOODS_UPDATE_FORM_DATA_RE_INIT:
      return Object.assign({},state,{
        detailData:action.data
      });
    case types.GOODS_DETAIL_VIEW_STATE:
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
        goodsDetailViewState:action.isOpen,
        IS_DRUG:IS_DRUG_,
        IS_OTHER:IS_OTHER_,
        IS_CHINESE_MEDICINE_PIECES:IS_CHINESE_MEDICINE_PIECES_,
        IS_FOOD_HEALTH:IS_FOOD_HEALTH_,
        IS_DAILY_NECESSITIES:IS_DAILY_NECESSITIES_,
        IS_MEDICAL_INSTRUMENTS:IS_MEDICAL_INSTRUMENTS_,
        IS_COSMETIC:IS_COSMETIC_,
      });
    case types.GOODS_ADD_FORM_ADD_PICTURES:
      return Object.assign({},state,{
        pictFileList:action.data
      });
    case types.GOODS_ADD_FORM_ADD_OTHER_FILES:
      return Object.assign({},state,{
        otherFileList:action.data
      });
    case types.GOODS_LIST_ENABLE_FORM_STATE:
      return Object.assign({},state,{
        enableFormState:action.data
      });
    case types.GOODS_LIST_ENABLE_FORM_INIT_DATA:
      let enableField = state.enableField;
        if(action.data.operationState == "Y"){
          enableField = [{
            field:'reason',
            validate:{
              required:true,
              maxlength:128,
              fieldNm:"禁用原因"
            }
          }]
        }
      return Object.assign({},state,{
        enableData:action.data,
        enableField:enableField
      });
    case types.GOODS_RECORD_LIST_DATA:
      return Object.assign({},state,{
        recordPage:action.data
      });
    case types.GOODS_RECORD_LIST_STATE:
      return Object.assign({},state,{
        recordPageView:action.isOpen,
        goodsId:action.id
      });
    default:
      return state
  }
}
