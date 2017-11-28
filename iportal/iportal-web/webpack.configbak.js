/**
 * Created by ygw on 2016/6/7.
 */

var webpack = require('webpack');
var commonsPlugin = new webpack.optimize.CommonsChunkPlugin('common.js');

module.exports = {
    entry: "./static/js/portal/main.js",
    output: {
        filename: "./static/build/build.js",
        path: __dirname + '/static/build/'
    },
    module: {
        loaders: [
            //.css 文件使用 style-loader 和 css-loader 来处理
            { test: /\.css$/, loader: "style!css" },
            //.js 文件使用 jsx-loader 来编译处理
            /* { test: /\.js$/,  loader: "jsx-loader" },
             { test: /\.js?$/, loader: 'babel-loader'},
             { test: /\.jsx$/, loader: 'jsx-loader'},
             { test: /\.jsx?$/,loader: 'babel-loader'}*/
            {
                test: /\.js?$/,         // Match both .js and .jsx files
                exclude: /node_modules/,
                loader: "babel",
                query:
                {
                    presets:['react']
                }
            }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx'],
        //模块别名定义，方便后续直接引用别名，无须多写长长的地址
        alias: { // 后面直接引用 require(“a”)即可引用到模块
            'jquery':'./static/lib/js/jquery-2.1.1.min',
            'bootstrap':'./static/lib/js/bootstrap',
            'nifty':'./static/lib/js/nifty',
            'raphael':'./static/lib/plugins/morris-js/raphael-js/raphael.min',
            'sparkline':'./static/lib/plugins/sparkline/jquery.sparkline',
            'skycons':'./static/lib/plugins/skycons/skycons',
            'switchery':'./static/lib/plugins/switchery/switchery',
            'bootstrap-select':'./static/lib/plugins/bootstrap-select/bootstrap-select',
            'pace':'./static/lib/plugins/pace/pace',
            /*            'react':'./static/lib/plugins/react-15.1.0/react',
             'react-dom':'./static/lib/plugins/react-15.1.0/react-dom',*/
            /*           'ReactRouter':'./static/lib/plugins/react-router/ReactRouter',*/
            /*            'angular':'./static/lib/plugins/angular-1.4.8/angular',
             'angular-cookies': './static/lib/plugins/angular-cookies/angular-cookies',
             'angularResource': './static/lib/plugins/angular-resource/angular-resource',
             'angular-sanitize': './static/lib/plugins/angular-sanitize/angular-sanitize.min',
             'angular-animate': './static/lib/plugins/angular-animate/angular-animate.min',*/
            'domReady': './static/lib/plugins/requirejs-domready/domReady',
            /*           'angularUIRouter': './static/lib/plugins/angular-ui-router/release/angular-ui-router',
             'angular-couch-potato': './static/lib/plugins/angular-couch-potato/dist/angular-couch-potato',
             'angular-easyfb': './static/lib/plugins/angular-easyfb/angular-easyfb.min',
             'angular-ocLazyLoad': './static/lib/plugins/angular-ocLazyLoad/ocLazyLoad.require',
             'angular-google-plus': './static/lib/plugins/angular-google-plus/dist/angular-google-plus.min',*/
            'bootbox': './static/lib/plugins/bootbox/bootbox.min',
            'bootstrap-table': './static/lib/plugins/bootstrap-table/bootstrap-table',
            'bootstrap-table-locale': './static/lib/plugins/bootstrap-table/locale/bootstrap-table-zh-CN',
            'bootstrap-editable': './static/lib/plugins/x-editable/js/bootstrap-editable.min',
            'jquery-base64': './static/lib/plugins/tableExport/jquery.base64',
            'tableExport': './static/lib/plugins/tableExport/tableExport',
            'bootstrap-table-export': './static/lib/plugins/bootstrap-table/extensions/export/bootstrap-table-export',
            /* 'ngDialog': './static/lib/js/ext/ngDialog',*/
            'ztree': './static/lib/plugins/ztree/js/jquery.ztree.all-3.5',
            /*            'w5cValidator':'./static/lib/plugins/angular-w5c-validator/w5cValidator',*/
            'jquery-ui':'./static/lib/plugins/jquery-ui/jquery-ui',
            'jquery-md5':'./static/lib/js/jquery.md5',
            'custom-common':'./static/js/common/common',
            'jquery-metisMenu':'vstatic/lib/plugins/form-builder/js/jquery.metisMenu',
            'beautifyhtml':'./static/lib/plugins/form-builder/js/beautifyhtml',
            'imall-upload-core':'./static/lib/plugins/html5_fileupload/core/imallUploadCore',
            'imall-upload-ctrl':'./static/lib/plugins/html5_fileupload/control/js/imallUploadCtrl',
            'fancybox':'./static/lib/plugins/jquery.fancybox-1.3.1/fancybox/jquery.fancybox-1.3.1',
            'bs-pagination':'./static/lib/plugins/bs_pagination-master/jquery.bs_pagination',
            'bs-pagination-local':'./static/lib/plugins/bs_pagination-master/localization/cn',
            'bootstrap-datetimepicker':'./static/lib/plugins/bootstrap-datetimepicker-0.0.11/js/bootstrap-datetimepicker.min',
            'bootstrap-datetimepicker-language':'./static/lib/plugins/bootstrap-datetimepicker-0.0.11/js/locales/bootstrap-datetimepicker.zh-CN',
            'bootstrap-multiselect': './static/lib/plugins/bootstrap-multiselect/js/bootstrap-multiselect',
            'ckeditor': './static/lib/plugins/ckeditor/ckeditor'
        }
    },
    plugins: [commonsPlugin]
};