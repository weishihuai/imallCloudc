/**
 * Created by ygw on 2016/6/7.
 */

var path = require('path')
var webpack = require('webpack')

const PATHS = {
    node_modules: path.join(__dirname, 'node_modules'),
    iportal_web:path.join(__dirname, 'src/main/webapp/WEB-INF')
};

//定义非直接引用依赖
//定义第三方直接用Script引入而不需要打包的类库
//使用方式即为var $ = require("jquery")
//eval-source-map source-map
module.exports = {
    devtool: 'source-map',
    entry: {
        // "app": PATHS.iportal_web  +  "/static/js/portal/remote/app/main.js",
        // "dict": PATHS.iportal_web  +  "/static/js/portal/remote/dict/main.js",
        // "exception": PATHS.iportal_web  +  "/static/js/portal/remote/exception/main.js",
        // "paraminf": PATHS.iportal_web  +  "/static/js/portal/remote/paraminf/main.js",
        // "resource": PATHS.iportal_web  +  "/static/js/portal/remote/resource/main.js",
        // "menu": PATHS.iportal_web  +  "/static/js/portal/remote/menu/main.js",
        // "developer": PATHS.iportal_web  +  "/static/js/portal/remote/developer/main.js",
        // "cache": PATHS.iportal_web  +  "/static/js/portal/remote/cache/main.js",
        // "index": PATHS.iportal_web +  "/static/js/portal/index/manage/main.js",
        // "org": PATHS.iportal_web  +  "/static/js/portal/remote/org/main.js",
        "main": PATHS.iportal_web +  "/static/js/portal/main.js",
        // "shopInfo": PATHS.iportal_web  +  "/static/js/portal/remote/shopInfo/main.js",
        // "user": PATHS.iportal_web  +  "/static/js/portal/remote/user/main.js",
        // "role": PATHS.iportal_web  +  "/static/js/portal/remote/role/main.js",
        // "job": PATHS.iportal_web  +  "/static/js/portal/remote/job/main.js",
        // "storageSpace": PATHS.iportal_web +  "/static/js/portal/shop/goods/storagespace/main.js",  //货位管理列表
        // "goodsBatch": PATHS.iportal_web +  "/static/js/portal/shop/goods/goodsbatch/main.js",   //商品批次修改列表
        // "salesCategory": PATHS.iportal_web +  "/static/js/portal/shop/goods/salescategory/main.js",   //销售分类
        // "temperatureMoistureMonitorRecord": PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/temperaturemoisturemonitorrecord/main.js", //温度湿度监控记录
        // "goods":  PATHS.iportal_web +  "/static/js/portal/shop/goods/goodslist/main.js",//商品管理
        // "combinationCategory":  PATHS.iportal_web +  "/static/js/portal/shop/pharmaceuticalservices/drugcombinationcategory/main.js",//联合用药分类
        // "combination":  PATHS.iportal_web +  "/static/js/portal/shop/pharmaceuticalservices/drugcombination/main.js",//联合用药
        // "member":  PATHS.iportal_web +  "/static/js/portal/shop/member/memberarchives/main.js",//会员档案信息
        // "goodsSplitZero":  PATHS.iportal_web +  "/static/js/portal/shop/goods/goodssplitzero/main.js",//	药品拆零
        // "supplier": PATHS.iportal_web  +  "/static/js/portal/customer/supplier/main.js",//门店供应商管理
        // "drugInBucket":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/chinesemedicinepieces/druginbucket/main.js", //药品装斗
        // "drugClearBucket":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/chinesemedicinepieces/drugclearbucket/main.js", //药品清斗
        // "consultRegister": PATHS.iportal_web +  "/static/js/portal/shop/pharmaceuticalservices/consultregister/main.js",   //咨询登记
        // "limitBuyRegister": PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/limitbuyregister/main.js",     //限购登记
        // "stockCheck":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/checkmanagement/stockcheck/main.js", //库存盘点
        // "stockoutWarning":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/warningmanagement/stockoutwarning/main.js", //缺货预警
        // "overdueWarning":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/warningmanagement/overduewarning/main.js", //过期药品预警
        // "expiryWarning":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/warningmanagement/expirywarning/main.js", //近效期催销
        // "purchaseOrder":  PATHS.iportal_web +  "/static/js/portal/shop/purchase/purchaseorder/main.js",//采购订单
        // "purchaseReceive":  PATHS.iportal_web +  "/static/js/portal/shop/purchase/purchasereceive/main.js",//采购收货
        // "purchaseCheckList":  PATHS.iportal_web +  "/static/js/portal/shop/purchase/purchaseacceptance/main.js",//采购验收
        // "fastReceive":  PATHS.iportal_web +  "/static/js/portal/shop/purchase/fastreceive/main.js",//快速收货
        // "purchaseReturn":  PATHS.iportal_web +  "/static/js/portal/shop/purchase/purchasereturn/main.js",//购进退出
        // "storageSpaceMove":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockadjustment/storagespacemove/main.js", //货位移动
        // "purchaseSaleStock":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockdetail/purchasesalestock/main.js", //进销存台帐
        // "stockPosting":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/checkmanagement/stockposting/main.js", //盘点过账
        // "stockOtherOutStock":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockadjustment/otheroutstock/main.js", //其他出库
        // "stockOtherInStock":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockadjustment/otherinstock/main.js", //其他入库
        // "drugExamine":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/drugcheck/main.js", //药品检查
        // "drugConserve":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/drugcuring/main.js", //药品养护
        // "batchActualStockList":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockdetail/batchactualstock/main.js", //实时库存（批号）
        // "goodsActualStockList":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockdetail/goodsactualstock/main.js", //实时库存（商品）
        // "salesDetail":  PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/salesdetail/main.js", //销售明细
        // "prescriptionRegister":  PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/prescriptionmanage/prescriptionregister/main.js", //处方登记
        // "prescriptionDispensing":  PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/prescriptionmanage/prescriptiondispensing/main.js", //处方调剂
        // "prescriptionRecord":  PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/prescriptionmanage/prescriptionrecord/main.js", //处方记录
        // "shiftRecord":  PATHS.iportal_web +  "/static/js/portal/shop/salesmanagement/shift/main.js", //交班记录
        // "drugLock":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/problemdrug/druglock/main.js", //药品锁定
        // "drugLockDeal":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/problemdrug/druglockdeal/main.js", //药品处理
        // "supplierQualityApprove":  PATHS.iportal_web +  "/static/js/portal/shop/quality/firstsellapprove/supplierqualityapprove/main.js", //供应商审核
        // "checkRecord": PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/drugcheckrecord/main.js", //药品检查记录
        // "curingRecord": PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/drugcuringrecord/main.js", //药品养护记录
        // "outOfStorage":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/stockdetail/outofstorage/main.js", //出入库明细
        // "complainRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/aftersale/complainrecord/main.js", //投诉记录
        // "drugQualityApprove":  PATHS.iportal_web +  "/static/js/portal/shop/quality/firstsellapprove/firstmanagedrugqualityapprove/main.js", //首营商品审核
        // "measureDeviceAccounts":  PATHS.iportal_web +  "/static/js/portal/shop/quality/measuredevicemanagement/measuredeviceaccounts/main.js", //计量器具
        // "measureDeviceRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/measuredevicemanagement/measuredevicerecord/main.js", //计量器具 检测记录
        // "facilityDeviceAccounts":  PATHS.iportal_web +  "/static/js/portal/shop/quality/facilityanddevicemanage/facilityanddeviceaccounts/main.js", //设备设施台账
        // "facilityDeviceUseRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/facilityanddevicemanage/userecord/main.js", //设备设施使用记录
        // "facilityDeviceMaintainRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/facilityanddevicemanage/maintainingrecord/main.js", //设备设施维护记录
        // "drugLockDestroy":  PATHS.iportal_web +  "/static/js/portal/shop/stockmanagement/problemdrug/druglockdestroy/main.js", //药品销毁
        // "outBucketRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/outBucketRecord/main.js", //清斗记录
        // "inBucketRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/inBucketRecord/main.js", //装斗记录
        // "stopSaleNotice":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/stopsalenoticelist/main.js", //药品停售单
        // "destroyRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/destroyrecord/main.js", //销毁记录
        // "releaseNotice":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/releasenoticelist/main.js", //药品解停单
        // "doc":  PATHS.iportal_web +  "/static/js/portal/shop/quality/gspinfo/doc/main.js", //档案管理
        // "gspCheckList":  PATHS.iportal_web +  "/static/js/portal/shop/quality/gspinfo/gspchecklist/main.js", //GSP检查表
        // "ruleProcess":  PATHS.iportal_web +  "/static/js/portal/shop/quality/gspinfo/systemandprocess/main.js", //制度与流程
        // "healthDoc":  PATHS.iportal_web +  "/static/js/portal/shop/quality/healthdoc/main.js", //健康档案
        // "salesReturnRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/aftersale/salesreturnrecord/main.js", //销售退货记录
        // "drugProcessRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/displayandstorage/disqualificationdrugprocessrecord/main.js", //不合格药品处理记录
        // "badReactionRep":  PATHS.iportal_web +  "/static/js/portal/shop/quality/aftersale/badreactionrep/main.js",  //不良反应报告
        // "purchaseRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/purchaseandacceptance/purchaserecord/main.js", //采购记录
        // "receiveRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/purchaseandaccepta nce/receiverecord/main.js", //收货记录
        // "returnRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/purchaseandacceptance/returnrecord/main.js", //购进退出记录
        // "acceptanceRecord":  PATHS.iportal_web +  "/static/js/portal/shop/quality/purchaseandacceptance/acceptancerecord/main.js", //验收记录
        //
        //  //微门店
        // "weshopMgr":  PATHS.iportal_web +  "/static/js/portal/weshop/weshopmanagement/main.js", //门店管理
        // "decorate":  PATHS.iportal_web +  "/static/js/portal/weshop/decorationrecommend/main.js", //装修推荐
        // "qrCodePromote":  PATHS.iportal_web +  "/static/js/portal/weshop/qrcodepromote/main.js", //二维码推广
        // "allOrder":  PATHS.iportal_web +  "/static/js/portal/weshop/ordermanagement/all/main.js", //全部订单
        // "toSendOrder":  PATHS.iportal_web +  "/static/js/portal/weshop/ordermanagement/waitsend/main.js", //待发货订单
        // "sendOrder":  PATHS.iportal_web +  "/static/js/portal/weshop/ordermanagement/alreadysended/main.js", //已发货订单
        // "finishOrder":  PATHS.iportal_web +  "/static/js/portal/weshop/ordermanagement/finish/main.js", //已完成订单
        // "closeOrder":  PATHS.iportal_web +  "/static/js/portal/weshop/ordermanagement/close/main.js", //已关闭订单
        // "fans":  PATHS.iportal_web +  "/static/js/portal/weshop/fansmanagement/main.js", //粉丝列表

        //平台
        // "portalSupplier": PATHS.iportal_web  +  "/static/js/portal/platform/supplierdoc/main.js",//平台供应商档案
        // "portalGoodsDoc": PATHS.iportal_web +  "/static/js/portal/platform/goodsdoclist/main.js",//平台商品档案
        // "portalGoodsCategory": PATHS.iportal_web +  "/static/js/portal/platform/goodscategory/main.js",//平台商品分类
        // "shop": PATHS.iportal_web  +  "/static/js/portal/platform/shop/main.js",//平台门店管理
        // "portalCombinationCategory":  PATHS.iportal_web +  "/static/js/portal/platform/pharmaceuticalservices/drugcombinationcategory/main.js",//联合用药分类
        // "portalCombination":  PATHS.iportal_web +  "/static/js/portal/platform/pharmaceuticalservices/drugcombination/main.js",//联合用药

        // POS
        // "posmain": PATHS.iportal_web +  "/static/js/salespos/cashier/main.js", //销售POS前端-收银
        // "posreport": PATHS.iportal_web +  "/static/js/salespos/report/main.js",//销售POS前端-销售报表
        // "posreturned":  PATHS.iportal_web +  "/static/js/salespos/returned/main.js", //退货

        //微门店前端
        "wechatmain": PATHS.iportal_web +  "/static/js/wechat/main.js", //微信

    },
    output: {
        path: path.join(PATHS.iportal_web,  'static/dist'),
        filename: "[name].js",
        chunkFilename: "[name].js"
    },
    externals: {
        jquery: "jQuery"
    },
    plugins: [
        new webpack.optimize.OccurrenceOrderPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new webpack.NoErrorsPlugin(),
        // new webpack.optimize.CommonsChunkPlugin({
        //     name: "common",
        //     minChunks: 2
        // }),
        // new webpack.optimize.CommonsChunkPlugin({name:"common", filename:"common.js", names:["main", "ent","app", "user","dict","exception","paraminf","resource","menu","role","job","org","cache","index","doc","filemgr",
        //     "salesCategory", "healthDoc","temperatureMoistureMonitorRecord", "combinationCategory", "combination","drugInBucket","drugClearBucket","storageSpace","goodsBatch","member","consultRegister","goodsSplitZero","goods",
        //     "stockCheck","stockoutWarning","overdueWarning","expiryWarning", "purchaseOrder", "purchaseReceive", "purchaseCheckList", "fastReceive","storageSpaceMove","posmain","posreport","purchaseSaleStock",
        //     "portalGoodsDoc","stockPosting","stockOtherOutStock", "purchaseReturn", "drugExamine", "shop", "drugConserve", "purchaseRecord", "receiveRecord", "checkRecord", "returnRecord","salesDetail",
        //     "prescriptionRegister", "prescriptionDispensing", "prescriptionRecord","drugLock","drugLockDeal","stockOtherInStock","supplier","supplierQualityApprove", "checkRecord", "batchActualStockList", "goodsActualStockList",
        //     "outOfStorage", "curingRecord", "outBucketRecord","inBucketRecord","complainRecord","drugQualityApprove","measureDeviceAccounts","measureDeviceRecord","drugLockdestroy" , "facilityDeviceAccounts",
        //     "stopSaleNotice","portalGoodsCategory","facilityDeviceMaintainRecord", "facilityDeviceUseRecord","destroyRecord","releaseNotice", "weshopMgr", "qrCodePromote","drugProcessRecord", "gspCheckList", "ruleProcess","badReactionRep",
        //     "allOrder", "toSendOrder", "sendOrder", "finishOrder", "closeOrder","fans","decorate", "portalCombinationCategory", "portalCombination"]}),
        /*new webpack.optimize.UglifyJsPlugin({
         compress: {
         warnings: false
         }
         }),*/
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('development')
        }),
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            requirejs: "requirejs"
        })
    ],
    node: {
        fs: 'empty',
        net: 'empty',
        tls: 'empty',
    },
    module: {
        loaders: [
            {
                test: /\.js?$/,
                loaders: ['babel'],
                exclude: /node_modules/,
                include: __dirname
            },
            {
                test: /\.less/,
                loader: 'style-loader!css-loader!less-loader'
            }, {
                test: /\.(css)$/,
                loader: 'style-loader!css-loader'
            },
            {
                test: /\.css$/,
                loader: "style!css"
            },
            {
                test: /\.css?$/,
                loaders: [ 'style', 'raw' ],
                include: __dirname
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx'],
        //模块别名定义，方便后续直接引用别名，无须多写长长的地址
        alias: { // 后面直接引用 require("a")即可引用到模块
            /*  'react': path.resolve(PATHS.node_modules, 'react/dist/react.js'),
             'react-dom': path.resolve(PATHS.node_modules, 'react-dom/dist/react-dom.js'),
             'react-redux': path.resolve(PATHS.node_modules, 'react-redux/dist/react-redux.js'),
             'react-router': path.resolve(PATHS.node_modules, 'react-router/lib/index.js'),
             'redux': path.resolve(PATHS.node_modules, 'redux/dist/redux.js'),*/
            'jquery': path.resolve(PATHS.iportal_web,  '/static/lib/js/jquery-2.1.1.min'),
            'bootbox': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/bootbox/bootbox.min'),
            'jquery-md5': path.resolve(PATHS.iportal_web  + '/static/lib/js/jquery.md5'),
            'ztree': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/ztree/js/jquery.ztree.all-3.5'),
            'bs-pagination': path.resolve(PATHS.iportal_web +'/static/lib/plugins/bs_pagination-master/jquery.bs_pagination'),
            'bs-pagination-local': path.resolve(PATHS.iportal_web + '/static/lib/plugins/bs_pagination-master/localization/cn'),
            'imallUploadCore': path.resolve(PATHS.iportal_web + '/static/lib/plugins/html5_fileupload/core/imallUploadCore'),
            'imallUploadCtrl': path.resolve(PATHS.iportal_web + '/static/lib/plugins/html5_fileupload/control/js/imallUploadCtrl'),
            'fancybox': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/jquery.fancybox-1.3.1/fancybox/jquery.fancybox-1.3.1'),
            'searchableSelect': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/searchableSelect/jquery.searchableSelect.js'),
            'bootstrap-datetimepicker': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/bootstrap-datetimepicker-0.0.11/js/bootstrap-datetimepicker'),
            'queryObject': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/jquery.queryObject'),
            'chosen': path.resolve(PATHS.iportal_web  + '/static/lib/plugins/chosen/chosen.jquery.js'),
        }
    },
    devServer:{
        port: 3000,
        historyApiFallback:true,
        inline:true,
        hot:true,
        contentBase:'./static'
    }
};