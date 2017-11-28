package com.imall.iportal.core.main.commons;

import com.imall.commons.base.dao.commons.TreeCodeGenerateBuilder;
import com.imall.iportal.core.example.service.SysDocService;
import com.imall.iportal.core.index.service.EsIndexManageService;
import com.imall.iportal.core.index.service.EsIndexQueueService;
import com.imall.iportal.core.main.service.*;
import com.imall.iportal.core.platform.service.*;
import com.imall.iportal.core.platform.service.ShopCertificatesFileService;
import com.imall.iportal.core.salespos.service.ShiftRecordService;
import com.imall.iportal.core.shop.service.*;
import com.imall.iportal.core.weshop.service.FansService;
import com.imall.iportal.core.weshop.service.WeChatApiService;
import com.imall.iportal.core.weshop.service.WeChatUserService;
import com.imall.iportal.core.weshop.service.WeShopOrderService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by ygw on 2017/3/21.
 * 仅供Controller使用
 */
@Component
public class ServiceManager implements InitializingBean {

    private static boolean inited;

    //core
    public static SysUserService sysUserService;
    public static SysUserOrgJobService sysUserOrgJobService;
    public static SysAuthService sysAuthService;
    public static SysDictService sysDictService;
    public static SysDictItemService sysDictItemService;
    public static SysFileLibService sysFileLibService;
    public static SysExceptionCodeService sysExceptionCodeService;
    public static SysAppService sysAppService;
    public static SysDeveloperAuthService sysDeveloperAuthService;
    public static SysFileCategoryService sysFileCategoryService;
    public static SysMenuService sysMenuService;
    public static SysResourceService sysResourceService;
    public static SysParamInfService sysParamInfService;
    public static SysResourceUrlService sysResourceUrlService;
    public static SysRoleMenuService sysRoleMenuService;
    public static SysRolePermissionService sysRolePermissionService;
    public static SysRoleService sysRoleService;
    public static SysTagService sysTagService;
    public static SysZoneService sysZoneService;
    public static TreeCodeGenerateBuilder treeCodeGenerateBuilder;
    public static SysOrgService sysOrgService;
    public static SysJobService sysJobService;
    public static SysCacheToolService sysCacheToolService;
    public static SysLogService sysLogService;
    public static SysLogDataService sysLogDataService;

    //index
    public static EsIndexManageService esIndexManageService;


    //example
    public static SysDocService sysDocService;
    public static EsIndexQueueService esIndexQueueService;

    //商品货位Service
    public static StorageSpaceService storageSpaceService;
    //商品批次Service
    public static GoodsBatchService goodsBatchService;
    //商品Service
    public static GoodsService goodsService;
    //商品 药品 Service
    public static GoodsDrugService goodsDrugService;
    //商品 中药 饮片 Service
    public static GoodsChineseMedicinePiecesService goodsChineseMedicinePiecesService;
    //sku Service
    public static SkuService skuService;
    //商品其他 Service
    public static GoodsOtherService goodsOtherService;
    //商品 医疗器械 Service
    public static GoodsMedicalInstrumentsService goodsMedicalInstrumentsService;
    //商品  食品 保健品 service
    public static GoodsFoodHealthService goodsFoodHealthService;
    //商品 日用品 service
    public static GoodsDailyNecessitiesService goodsDailyNecessitiesService;
    //商品 化妆品 service
    public static GoodsCosmeticService goodsCosmeticService;
    //商品拆零 service
    public static GoodsSplitZeroService goodsSplitZeroService;
    //会员 Service
    public static MemberService memberService;
    //销售分类管理
    public static SalesCategoryService salesCategoryService;
    //温度湿度监控记录
    public static TemperatureMoistureMonitorRecordService temperatureMoistureMonitorRecordService;
    //销毁记录
    public static DestroyRecordService destroyRecordService;
    //采购订单 Service
    public static PurchaseOrderService purchaseOrderService;
    //采购项订单 Service
    public static PurchaseOrderItemService purchaseOrderItemService;
    //采购收货记录
    public static PurchaseReceiveRecordService purchaseReceiveRecordService;
    //采购收货记录项
    public static PurchaseReceiveRecordItemService purchaseReceiveRecordItemService;
    //采购验收记录
    public static PurchaseAcceptanceRecordService purchaseAcceptanceRecordService;
    //采购验收记录项
    public static PurchaseAcceptanceRecordItemService purchaseAcceptanceRecordItemService;
    //购进退出
    public static ReturnedPurchaseOrderService returnedPurchaseOrderService;
    //购进退出项
    public static ReturnedPurchaseOrderItemService returnedPurchaseOrderItemService;
    //供应商
    public static SupplierService supplierService;
    //清斗记录
    public static ChineseMedicinePiecesCleaningBucketRecordService chineseMedicinePiecesCleaningBucketRecordService;
    //装斗记录
    public static ChineseMedicinePiecesLoadingBucketRecordService chineseMedicinePiecesLoadingBucketRecordService;
    //供应商 首营 审核
    public static FirstManageSupplierQualityApproveService firstManageSupplierQualityApproveService;
    //供应商 首营 审核 信息
    public static FirstManageSupplierQualityApproveInfService firstManageSupplierQualityApproveInfService;
    //供应商 资质文件
    public static SupplierCertificatesFileService supplierCertificatesFileService;
    //平台供应商
    public static SupplierDocService supplierDocService;
    //平台供应商 资质文件
    public static SupplierDocCertificatesFileService supplierDocCertificatesFileService;
    //药品装斗 Service
    public static DrugInBucketService drugInBucketService;
    //药品清斗 Service
    public static DrugClearBucketService drugClearBucketService;
    //限购登记 Service
    public static LimitBuyRegisterService limitBuyRegisterService;
    //订单   Service
    public static OrderService orderService;
    //订单项   Service
    public static OrderItemService orderItemService;
    //出入库日志
    public static OutInStockLogService outInStockLogService;
    //商品批号修改记录
    public static GoodsBatchModRecordService goodsBatchModRecordService;
    //药品检查
    public static DrugCheckService drugCheckService;
    //药品检查项
    public static DrugCheckItemService drugCheckItemService;
    //药品养护
    public static DrugCuringService drugCuringService;
    //药品养护项
    public static DrugCuringItemService drugCuringItemService;
    //咨询服务
    public static ConsultServiceService consultServiceService;
    //咨询商品信息
    public static ConsultGoodsInfService consultGoodsInfService;
    //会员收货地址
    public static ReceiverAddrService receiverAddrService;
    //微门店
    public static WeShopService weShopService;
    //购物车持久化
    public static ShoppingCartStoreService shoppingCartStoreService;
    //联合用药分类
    public static DrugCombinationCategoryService drugCombinationCategoryService;
    //联合用药
    public static DrugCombinationService drugCombinationService;
    //门店
    public static ShopService shopService;
    //门店资质
    public static ShopCertificatesFileService shopCertificatesFileService;
    //库存盘点 Service
    public static StockCheckService stockCheckService;
    //其他出库
    public static OtherOutStockService otherOutStockService;
    //其他入库
    public static OtherInStockService otherInStockService;
    //药品锁定
    public static DrugLockService drugLockService;
    //计量器具
    public static MeasuringDeviceAccountsService measuringDeviceAccountsService;
    //计量器具 检测记录
    public static MeasureRecordService measureRecordService;

    //pos购物车
    public static ISalesPosShoppingFlowService salesPosShoppingFlowService;

    //pos购物车
    public static INormalShoppingFlowService normalShoppingFlowService;

    //货位移动
    public static StorageSpaceMoveService storageSpaceMoveService;
    //交班记录
    public static ShiftRecordService shiftRecordService;
    //销售退货订单
    public static SellReturnedPurchaseOrderService sellReturnedPurchaseOrderService;
    //销售退货订单项
    public static SellReturnedPurchaseOrderItemService sellReturnedPurchaseOrderItemService;
    //平台商品档案
    public static GoodsDocService goodsDocService;
    //商品分类
    public static GoodsCategoryService goodsCategoryService;
    //平台中药饮片档案
    public static GoodsDocChineseMedicinePiecesService goodsDocChineseMedicinePiecesService;
    //平台化妆品档案
    public static GoodsDocCosmeticService goodsDocCosmeticService;
    //平台日用品档案
    public static GoodsDocDailyNecessitiesService goodsDocDailyNecessitiesService;
    //平台药品档案
    public static GoodsDocDrugService goodsDocDrugService;
    //平台保健品档案
    public static GoodsDocFoodHealthService goodsDocFoodHealthService;
    //平台医疗机械档案
    public static GoodsDocMedicalInstrumentsService goodsDocMedicalInstrumentsService;
    //平台其他档案
    public static GoodsDocOtherService goodsDocOtherService;
    //处方管理
    public static PrescriptionRegisterService prescriptionRegisterService;

    //药品检查记录
    public static DrugCheckRecordService drugCheckRecordService;
    //药品养护记录
    public static DrugCuringRecordService drugCuringRecordService;
    //微信粉丝
    public static FansService fansService;
    //微信 用户
    public static WeChatUserService weChatUserService;
    //投诉记录
    public static ComplainRecordService complainRecordService;

    //设施设备台账
    public static FacilityAndDeviceAccountsService facilityAndDeviceAccountsService;
    //设施设备维护记录
    public static MaintainingRecordService maintainingRecordService;
    //设施设备使用记录
    public static UseRecordService useRecordService;

    //不合格药品处理记录
    public static DisqualificationDrugProcessRecordService disqualificationDrugProcessRecordService;

    //不良反应报告
    public static BadReactionRepService badReactionRepService;
    //不良反应报告 药品信息
    public static BadReactionDrugInfService badReactionDrugInfService;

    //门店 首营药品审核
    public static FirstManageDrugQualityApproveService firstManageDrugQualityApproveService;
    //门店 首营药品审核信息
    public static FirstManageDrugQualityApproveInfService firstManageDrugQualityApproveInfService;
    //门店 停售单
    public static DrugStopSaleNoticeService drugStopSaleNoticeService;
    //门店 停售商品信息
    public static StopSaleGoodsInfService stopSaleGoodsInfService;
    //门店 解停单
    public static DrugReleaseNoticeService drugReleaseNoticeService;
    //门店 解停商品信息
    public static ReleaseGoodsInfService releaseGoodsInfService;
    //门店 员工档案
    public static StaffHealthDocService staffHealthDocService;
    //门店 员工档案 信息
    public static StaffHealthDocInfService staffHealthDocInfService;
    //商品停用/启用修改记录
    public static GoodsEnableRecordService goodsEnableRecordService;


    //GSP资料
    public static DocInfService docInfService;

    //文件管理
    public static FileMngService fileMngService;

    public static WeChatApiService weChatApiService;
    //微店订单
    public static WeShopOrderService weShopOrderService;

    //订单发货
    public static OrderSendOutBatchService orderSendOutBatchService;

    public static SkuLockStockService skuLockStockService;

    //装修推荐
    public static DecorationRecommendService decorationRecommendService;

    //装修分组
    public static DecorationRecommendGroupService decorationRecommendGroupService;

    //短信相关
    public static ValidateCodeLogService validateCodeLogService;
    public static SmsQueueService smsQueueService;
    public static SmsService smsService;

    public static JsonObjectEngineService jsonObjectEngineService;


    @Override
    public void afterPropertiesSet() throws Exception {
        inited = true;
    }

    public static boolean isInited() {
        return inited;
    }
    //quartz
   /* public static JobDetailsService jobDetailsService;*/

    @Autowired
    public void setSysJobService(SysJobService sysJobService) {
        ServiceManager.sysJobService = sysJobService;
    }

    @Autowired
    public void setSysExceptionCodeService(SysExceptionCodeService sysExceptionCodeService) {
        ServiceManager.sysExceptionCodeService = sysExceptionCodeService;
    }

    @Autowired
    public void setSysFileLibService(SysFileLibService sysFileLibService) {
        ServiceManager.sysFileLibService = sysFileLibService;
    }

    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        ServiceManager.sysDictService = sysDictService;
    }

    @Autowired
    public void setSysDictItemService(SysDictItemService sysDictItemService) {
        ServiceManager.sysDictItemService = sysDictItemService;
    }

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        ServiceManager.sysUserService = sysUserService;
    }

    @Autowired
    public void setSysUserOrgJobService(SysUserOrgJobService sysUserOrgJobService) {
        ServiceManager.sysUserOrgJobService = sysUserOrgJobService;
    }

    @Autowired
    public void setSysAuthService(SysAuthService sysAuthService) {
        ServiceManager.sysAuthService = sysAuthService;
    }

    @Autowired
    public void setSysAppService(SysAppService sysAppService) {
        ServiceManager.sysAppService = sysAppService;
    }

    @Autowired
    public void setSysDeveloperAuthService(SysDeveloperAuthService sysDeveloperAuthService) {
        ServiceManager.sysDeveloperAuthService = sysDeveloperAuthService;
    }

    @Autowired
    public void setSysFileCategoryService(SysFileCategoryService sysFileCategoryService) {
        ServiceManager.sysFileCategoryService = sysFileCategoryService;
    }

    @Autowired
    public void setSysMenuService(SysMenuService sysMenuService) {
        ServiceManager.sysMenuService = sysMenuService;
    }

    @Autowired
    public void setSysOrgService(SysOrgService sysOrgService) {
        ServiceManager.sysOrgService = sysOrgService;
    }

    @Autowired
    public void setSysResourceService(SysResourceService sysResourceService) {
        ServiceManager.sysResourceService = sysResourceService;
    }

    @Autowired
    public void setSysParamInfService(SysParamInfService sysParamInfService) {
        ServiceManager.sysParamInfService = sysParamInfService;
    }

    @Autowired
    public void setSysResourceUrlService(SysResourceUrlService sysResourceUrlService) {
        ServiceManager.sysResourceUrlService = sysResourceUrlService;
    }

    @Autowired
    public void setSysRoleMenuService(SysRoleMenuService sysRoleMenuService) {
        ServiceManager.sysRoleMenuService = sysRoleMenuService;
    }

    @Autowired
    public void setSysRolePermissionService(SysRolePermissionService sysRolePermissionService) {
        ServiceManager.sysRolePermissionService = sysRolePermissionService;
    }

    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        ServiceManager.sysRoleService = sysRoleService;
    }

    @Autowired
    public void setSysTagService(SysTagService sysTagService) {
        ServiceManager.sysTagService = sysTagService;
    }

    @Autowired
    public void setSysZoneService(SysZoneService sysZoneService) {
        ServiceManager.sysZoneService = sysZoneService;
    }

    @Autowired
    public void setTreeCodeGenerateBuilder(TreeCodeGenerateBuilder treeCodeGenerateBuilder) {
        ServiceManager.treeCodeGenerateBuilder = treeCodeGenerateBuilder;
    }

    @Autowired
    public void setSysCacheToolService(SysCacheToolService sysCacheToolService) {
        ServiceManager.sysCacheToolService = sysCacheToolService;
    }

    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        ServiceManager.sysLogService = sysLogService;
    }

    @Autowired
    public void setSysLogDataService(SysLogDataService sysLogDataService) {
        ServiceManager.sysLogDataService = sysLogDataService;
    }

    @Autowired
    public void setEsIndexManageService(EsIndexManageService esIndexManageService) {
        ServiceManager.esIndexManageService = esIndexManageService;
    }

    @Autowired
    public void setSysDocService(SysDocService sysDocService) {
        ServiceManager.sysDocService = sysDocService;
    }

    @Autowired
    public void setEsIndexQueueService(EsIndexQueueService esIndexQueueService) {
        ServiceManager.esIndexQueueService = esIndexQueueService;
    }

    @Autowired
    public void setStorageSpaceService(StorageSpaceService storageSpaceService) {
        ServiceManager.storageSpaceService = storageSpaceService;
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        ServiceManager.goodsService = goodsService;
    }

    @Autowired
    public void setGoodsBatchService(GoodsBatchService goodsBatchService) {
        ServiceManager.goodsBatchService = goodsBatchService;
    }

    @Autowired
    public void setGoodsDrugService(GoodsDrugService goodsDrugService) {
        ServiceManager.goodsDrugService = goodsDrugService;
    }

    @Autowired
    public void setGoodsChineseMedicinePiecesService(GoodsChineseMedicinePiecesService goodsChineseMedicinePiecesService) {
        ServiceManager.goodsChineseMedicinePiecesService = goodsChineseMedicinePiecesService;
    }

    @Autowired
    public void setSkuService(SkuService skuService) {
        ServiceManager.skuService = skuService;
    }

    @Autowired
    public void setGoodsOtherService(GoodsOtherService goodsOtherService) {
        ServiceManager.goodsOtherService = goodsOtherService;
    }

    @Autowired
    public void setGoodsMedicalInstrumentsService(GoodsMedicalInstrumentsService goodsMedicalInstrumentsService) {
        ServiceManager.goodsMedicalInstrumentsService = goodsMedicalInstrumentsService;
    }

    @Autowired
    public void setGoodsFoodHealthService(GoodsFoodHealthService goodsFoodHealthService) {
        ServiceManager.goodsFoodHealthService = goodsFoodHealthService;
    }

    @Autowired
    public void setGoodsDailyNecessitiesService(GoodsDailyNecessitiesService goodsDailyNecessitiesService) {
        ServiceManager.goodsDailyNecessitiesService = goodsDailyNecessitiesService;
    }

    @Autowired
    public void setGoodsCosmeticService(GoodsCosmeticService goodsCosmeticService) {
        ServiceManager.goodsCosmeticService = goodsCosmeticService;
    }

    @Autowired
    public void setSalesCategoryService(SalesCategoryService salesCategoryService) {
        ServiceManager.salesCategoryService = salesCategoryService;
    }

    @Autowired
    public void setGoodsSplitZeroService(GoodsSplitZeroService goodsSplitZeroService) {
        ServiceManager.goodsSplitZeroService = goodsSplitZeroService;
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        ServiceManager.memberService = memberService;
    }

    @Autowired
    public void setTemperatureMoistureMonitorRecordService(TemperatureMoistureMonitorRecordService temperatureMoistureMonitorRecordService) {
        ServiceManager.temperatureMoistureMonitorRecordService = temperatureMoistureMonitorRecordService;
    }

    @Autowired
    public void setDestroyRecordService(DestroyRecordService destroyRecordService) {
        ServiceManager.destroyRecordService = destroyRecordService;
    }

    @Autowired
    public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
        ServiceManager.purchaseOrderService = purchaseOrderService;
    }

    @Autowired
    public void setPurchaseOrderItemService(PurchaseOrderItemService purchaseOrderItemService) {
        ServiceManager.purchaseOrderItemService = purchaseOrderItemService;
    }

    @Autowired
    public void setPurchaseReceiveRecordService(PurchaseReceiveRecordService purchaseReceiveRecordService) {
        ServiceManager.purchaseReceiveRecordService = purchaseReceiveRecordService;
    }

    @Autowired
    public void setPurchaseReceiveRecordItemService(PurchaseReceiveRecordItemService purchaseReceiveRecordItemService) {
        ServiceManager.purchaseReceiveRecordItemService = purchaseReceiveRecordItemService;
    }

    @Autowired
    public void setPurchaseAcceptanceRecordService(PurchaseAcceptanceRecordService purchaseAcceptanceRecordService) {
        ServiceManager.purchaseAcceptanceRecordService = purchaseAcceptanceRecordService;
    }

    @Autowired
    public void setPurchaseAcceptanceRecordItemService(PurchaseAcceptanceRecordItemService purchaseAcceptanceRecordItemService) {
        ServiceManager.purchaseAcceptanceRecordItemService = purchaseAcceptanceRecordItemService;
    }

    @Autowired
    public void setReturnedPurchaseOrderService(ReturnedPurchaseOrderService returnedPurchaseOrderService) {
        ServiceManager.returnedPurchaseOrderService = returnedPurchaseOrderService;
    }

    @Autowired
    public void setReturnedPurchaseOrderItemService(ReturnedPurchaseOrderItemService returnedPurchaseOrderItemService) {
        ServiceManager.returnedPurchaseOrderItemService = returnedPurchaseOrderItemService;
    }

    @Autowired
    public void setSupplierService(SupplierService supplierService) {
        ServiceManager.supplierService = supplierService;
    }
    @Autowired
    public void setChineseMedicinePiecesCleaningBucketRecordService(ChineseMedicinePiecesCleaningBucketRecordService chineseMedicinePiecesCleaningBucketRecordService) {
        ServiceManager.chineseMedicinePiecesCleaningBucketRecordService = chineseMedicinePiecesCleaningBucketRecordService;
    }
    @Autowired
    public void setChineseMedicinePiecesLoadingBucketRecordService(ChineseMedicinePiecesLoadingBucketRecordService chineseMedicinePiecesLoadingBucketRecordService) {
        ServiceManager.chineseMedicinePiecesLoadingBucketRecordService = chineseMedicinePiecesLoadingBucketRecordService;
    }
    @Autowired
    public void setFirstManageSupplierQualityApproveService(FirstManageSupplierQualityApproveService firstManageSupplierQualityApproveService) {
        ServiceManager.firstManageSupplierQualityApproveService = firstManageSupplierQualityApproveService;
    }
    @Autowired
    public void setFirstManageSupplierQualityApproveInfService(FirstManageSupplierQualityApproveInfService firstManageSupplierQualityApproveInfService) {
        ServiceManager.firstManageSupplierQualityApproveInfService = firstManageSupplierQualityApproveInfService;
    }

    @Autowired
    public void setSupplierCertificatesFileService(SupplierCertificatesFileService supplierCertificatesFileService) {
        ServiceManager.supplierCertificatesFileService = supplierCertificatesFileService;
    }

    @Autowired
    public void setSupplierDocService(SupplierDocService supplierDocService) {
        ServiceManager.supplierDocService = supplierDocService;
    }

    @Autowired
    public void setSupplierDocCertificatesFileService(SupplierDocCertificatesFileService supplierDocCertificatesFileService) {
        ServiceManager.supplierDocCertificatesFileService = supplierDocCertificatesFileService;
    }

    @Autowired
    public void setDrugInBucketService(DrugInBucketService drugInBucketService) {
        ServiceManager.drugInBucketService = drugInBucketService;
    }

    @Autowired
    public void setDrugClearBucketService(DrugClearBucketService drugClearBucketService) {
        ServiceManager.drugClearBucketService = drugClearBucketService;
    }

    @Autowired
    public void setLimitBuyRegisterService(LimitBuyRegisterService limitBuyRegisterService) {
        ServiceManager.limitBuyRegisterService = limitBuyRegisterService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        ServiceManager.orderService = orderService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        ServiceManager.orderItemService = orderItemService;
    }

    @Autowired
    public void setOutInStockLogService(OutInStockLogService outInStockLogService) {
        ServiceManager.outInStockLogService = outInStockLogService;
    }

    @Autowired
    public void setGoodsBatchModRecordService(GoodsBatchModRecordService goodsBatchModRecordService) {
        ServiceManager.goodsBatchModRecordService = goodsBatchModRecordService;
    }

    @Autowired
    public void setDrugCheckService(DrugCheckService drugCheckService) {
        ServiceManager.drugCheckService = drugCheckService;
    }

    @Autowired
    public void setDrugCuringService(DrugCuringService drugCuringService) {
        ServiceManager.drugCuringService = drugCuringService;
    }

    @Autowired
    public void setDrugCheckItemService(DrugCheckItemService drugCheckItemService) {
        ServiceManager.drugCheckItemService = drugCheckItemService;
    }

    @Autowired
    public void setDrugCuringItemService(DrugCuringItemService drugCuringItemService) {
        ServiceManager.drugCuringItemService = drugCuringItemService;
    }

    @Autowired
    public void setReceiverAddrService(ReceiverAddrService receiverAddrService) {
        ServiceManager.receiverAddrService = receiverAddrService;
    }

    @Autowired
    public void setDrugCombinationCategoryService(DrugCombinationCategoryService drugCombinationCategoryService) {
        ServiceManager.drugCombinationCategoryService = drugCombinationCategoryService;
    }

    @Autowired
    public void setWeShopService(WeShopService weShopService) {
        ServiceManager.weShopService = weShopService;
    }

    @Autowired
    public void setShoppingCartStoreService(ShoppingCartStoreService shoppingCartStoreService) {
        ServiceManager.shoppingCartStoreService = shoppingCartStoreService;
    }

    @Autowired
    public void setDrugCombinationService(DrugCombinationService drugCombinationService) {
        ServiceManager.drugCombinationService = drugCombinationService;
    }

    @Autowired
    public void setConsultServiceService(ConsultServiceService consultServiceService) {
        ServiceManager.consultServiceService = consultServiceService;
    }

    @Autowired
    public void setConsultGoodsInfService(ConsultGoodsInfService consultGoodsInfService) {
        ServiceManager.consultGoodsInfService = consultGoodsInfService;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        ServiceManager.shopService = shopService;
    }

    @Autowired
    public void setShopCertificatesFileService(ShopCertificatesFileService shopCertificatesFileService) {
        ServiceManager.shopCertificatesFileService = shopCertificatesFileService;
    }

    @Autowired
    public void setStockCheckService(StockCheckService stockCheckService) {
        ServiceManager.stockCheckService = stockCheckService;
    }

    @Autowired
    public void setSalesPosShoppingFlowService(ISalesPosShoppingFlowService salesPosShoppingFlowService) {
        ServiceManager.salesPosShoppingFlowService = salesPosShoppingFlowService;
    }

    @Autowired
    public void setNormalShoppingFlowService(INormalShoppingFlowService normalShoppingFlowService) {
        ServiceManager.normalShoppingFlowService = normalShoppingFlowService;
    }

    @Autowired
    public void setStorageSpaceMoveService(StorageSpaceMoveService storageSpaceMoveService) {
        ServiceManager.storageSpaceMoveService = storageSpaceMoveService;
    }

    @Autowired
    public void setShiftRecordService(ShiftRecordService shiftRecordService) {
        ServiceManager.shiftRecordService = shiftRecordService;
    }

    @Autowired
    public void setSellReturnedPurchaseOrderService(SellReturnedPurchaseOrderService sellReturnedPurchaseOrderService) {
        ServiceManager.sellReturnedPurchaseOrderService = sellReturnedPurchaseOrderService;
    }

    @Autowired
    public void setSellReturnedPurchaseOrderItemService(SellReturnedPurchaseOrderItemService sellReturnedPurchaseOrderItemService) {
        ServiceManager.sellReturnedPurchaseOrderItemService = sellReturnedPurchaseOrderItemService;
    }

    @Autowired
    public void setGoodsDocService(GoodsDocService goodsDocService) {
        ServiceManager.goodsDocService = goodsDocService;
    }

    @Autowired
    public void setGoodsCategoryService(GoodsCategoryService goodsCategoryService) {
        ServiceManager.goodsCategoryService = goodsCategoryService;
    }

    @Autowired
    public void setGoodsDocChineseMedicinePiecesService(GoodsDocChineseMedicinePiecesService goodsDocChineseMedicinePiecesService) {
        ServiceManager.goodsDocChineseMedicinePiecesService = goodsDocChineseMedicinePiecesService;
    }

    @Autowired
    public void setGoodsDocCosmeticService(GoodsDocCosmeticService goodsDocCosmeticService) {
        ServiceManager.goodsDocCosmeticService = goodsDocCosmeticService;
    }

    @Autowired
    public void setGoodsDocDailyNecessitiesService(GoodsDocDailyNecessitiesService goodsDocDailyNecessitiesService) {
        ServiceManager.goodsDocDailyNecessitiesService = goodsDocDailyNecessitiesService;
    }

    @Autowired
    public void setGoodsDocDrugService(GoodsDocDrugService goodsDocDrugService) {
        ServiceManager.goodsDocDrugService = goodsDocDrugService;
    }

    @Autowired
    public void setGoodsDocFoodHealthService(GoodsDocFoodHealthService goodsDocFoodHealthService) {
        ServiceManager.goodsDocFoodHealthService = goodsDocFoodHealthService;
    }

    @Autowired
    public void setGoodsDocMedicalInstrumentsService(GoodsDocMedicalInstrumentsService goodsDocMedicalInstrumentsService) {
        ServiceManager.goodsDocMedicalInstrumentsService = goodsDocMedicalInstrumentsService;
    }

    @Autowired
    public void setGoodsDocOtherService(GoodsDocOtherService goodsDocOtherService) {
        ServiceManager.goodsDocOtherService = goodsDocOtherService;
    }

    @Autowired
    public void setOtherOutStockService(OtherOutStockService otherOutStockService) {
        ServiceManager.otherOutStockService = otherOutStockService;
    }

    @Autowired
    public void setOtherInStockService(OtherInStockService otherInStockService) {
        ServiceManager.otherInStockService = otherInStockService;
    }

    @Autowired
    public void setDrugLockService(DrugLockService drugLockService) {
        ServiceManager.drugLockService = drugLockService;
    }

    @Autowired
    public void setPrescriptionRegisterService(PrescriptionRegisterService prescriptionRegisterService) {
        ServiceManager.prescriptionRegisterService = prescriptionRegisterService;
    }

    @Autowired
    public void setFirstManageDrugQualityApproveService(FirstManageDrugQualityApproveService firstManageDrugQualityApproveService) {
        ServiceManager.firstManageDrugQualityApproveService = firstManageDrugQualityApproveService;
    }

    @Autowired
    public void setFirstManageDrugQualityApproveInfService(FirstManageDrugQualityApproveInfService firstManageDrugQualityApproveInfService) {
        ServiceManager.firstManageDrugQualityApproveInfService = firstManageDrugQualityApproveInfService;
    }

    @Autowired
    public void setDrugCheckRecordService(DrugCheckRecordService drugCheckRecordService) {
        ServiceManager.drugCheckRecordService = drugCheckRecordService;
    }

    @Autowired
    public void setDrugCuringRecordService(DrugCuringRecordService drugCuringRecordService) {
        ServiceManager.drugCuringRecordService = drugCuringRecordService;
    }

    @Autowired
    public void setFansService(FansService fansService) {
        ServiceManager.fansService = fansService;
    }

    @Autowired
    public void setWeChatUserService(WeChatUserService weChatUserService) {
        ServiceManager.weChatUserService = weChatUserService;
    }

    @Autowired
    public void setWeChatApiService(WeChatApiService weChatApiService) {
        ServiceManager.weChatApiService = weChatApiService;
    }

    @Autowired
    public void setComplainRecordService(ComplainRecordService complainRecordService) {
        ServiceManager.complainRecordService = complainRecordService;
    }

    @Autowired
    public void setMeasuringDeviceAccountsService(MeasuringDeviceAccountsService measuringDeviceAccountsService) {
        ServiceManager.measuringDeviceAccountsService = measuringDeviceAccountsService;
    }

    @Autowired
    public void setMeasureRecordService(MeasureRecordService measureRecordService) {
        ServiceManager.measureRecordService = measureRecordService;
    }

    @Autowired
    public void setFacilityAndDeviceAccountsService(FacilityAndDeviceAccountsService facilityAndDeviceAccountsService) {
        ServiceManager.facilityAndDeviceAccountsService = facilityAndDeviceAccountsService;
    }

    @Autowired
    public void setMaintainingRecordService(MaintainingRecordService maintainingRecordService) {
        ServiceManager.maintainingRecordService = maintainingRecordService;
    }

    @Autowired
    public void setUseRecordService(UseRecordService useRecordService) {
        ServiceManager.useRecordService = useRecordService;
    }
    @Autowired
    public void setDrugStopSaleNoticeService(DrugStopSaleNoticeService drugStopSaleNoticeService) {
        ServiceManager.drugStopSaleNoticeService = drugStopSaleNoticeService;
    }
    @Autowired
    public void setStopSaleGoodsInfService(StopSaleGoodsInfService stopSaleGoodsInfService) {
        ServiceManager.stopSaleGoodsInfService = stopSaleGoodsInfService;
    }
    @Autowired
    public void setDrugReleaseNotice(DrugReleaseNoticeService drugReleaseNotice) {
        ServiceManager.drugReleaseNoticeService = drugReleaseNotice;
    }
    @Autowired
    public void setReleaseGoodsInfService(ReleaseGoodsInfService releaseGoodsInfService) {
        ServiceManager.releaseGoodsInfService = releaseGoodsInfService;
    }

    @Autowired
    public void setDisqualificationDrugProcessRecordService(DisqualificationDrugProcessRecordService disqualificationDrugProcessRecordService) {
        ServiceManager.disqualificationDrugProcessRecordService = disqualificationDrugProcessRecordService;
    }

    @Autowired
    public void setStaffHealthDocService(StaffHealthDocService staffHealthDocService) {
        ServiceManager.staffHealthDocService = staffHealthDocService;
    }
    @Autowired
    public void setStaffHealthDocInfService(StaffHealthDocInfService staffHealthDocInfService) {
        ServiceManager.staffHealthDocInfService = staffHealthDocInfService;
    }
    @Autowired
    public void setDocInfService(DocInfService docInfService) {
        ServiceManager.docInfService = docInfService;
    }

    @Autowired
    public void setDrugReleaseNoticeService(DrugReleaseNoticeService drugReleaseNoticeService) {
        ServiceManager.drugReleaseNoticeService = drugReleaseNoticeService;
    }

    @Autowired
    public void setFileMngService(FileMngService fileMngService) {
        ServiceManager.fileMngService = fileMngService;
    }
    @Autowired
    public void setBadReactionRepService(BadReactionRepService badReactionRepService) {
        ServiceManager.badReactionRepService = badReactionRepService;
    }

    @Autowired
    public void setBadReactionDrugInfService(BadReactionDrugInfService badReactionDrugInfService) {
        ServiceManager.badReactionDrugInfService = badReactionDrugInfService;
    }

    @Autowired
    public void setOrderSendOutBatchService(OrderSendOutBatchService orderSendOutBatchService) {
        ServiceManager.orderSendOutBatchService = orderSendOutBatchService;
    }

    @Autowired
    public void setWeShopOrderService(WeShopOrderService weShopOrderService) {
        ServiceManager.weShopOrderService = weShopOrderService;
    }

    @Autowired
    public void setSkuLockStockService(SkuLockStockService skuLockStockService) {
        ServiceManager.skuLockStockService = skuLockStockService;
    }

    @Autowired
    public void setGoodsEnableRecordService(GoodsEnableRecordService goodsEnableRecordService) {
        ServiceManager.goodsEnableRecordService = goodsEnableRecordService;
    }

    @Autowired
    public void setDecorationRecommendService(DecorationRecommendService decorationRecommendService) {
        ServiceManager.decorationRecommendService = decorationRecommendService;
    }

    @Autowired
    public void setDecorationRecommendGroupService(DecorationRecommendGroupService decorationRecommendGroupService) {
        ServiceManager.decorationRecommendGroupService = decorationRecommendGroupService;
    }

    @Autowired
    public void setValidateCodeLogService(ValidateCodeLogService validateCodeLogService) {
        ServiceManager.validateCodeLogService = validateCodeLogService;
    }

    @Autowired
    public void setSmsQueueService(SmsQueueService smsQueueService) {
        ServiceManager.smsQueueService = smsQueueService;
    }

    @Autowired
    public void setSmsService(SmsService smsService) {
        ServiceManager.smsService = smsService;
    }

    @Autowired
    public void setJsonObjectEngineService(JsonObjectEngineService jsonObjectEngineService) {
        ServiceManager.jsonObjectEngineService = jsonObjectEngineService;
    }
}
