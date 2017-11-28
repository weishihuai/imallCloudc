/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/kayalshri/tableExport.jquery.plugin
 */

(function ($) {
    'use strict';

    var TYPE_NAME = {
        json: 'JSON',
        xml: 'XML',
        png: 'PNG',
        csv: 'CSV',
        txt: 'TXT',
        sql: 'SQL',
        doc: 'MS-Word',
        excel: 'Ms-Excel',
        powerpoint: 'Ms-Powerpoint',
        pdf: 'PDF'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showExport: false,
        exportDataType: 'basic', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['json', 'xml', 'csv', 'txt', 'sql', 'excel'],
        exportOptions: {}
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initToolbar = BootstrapTable.prototype.initToolbar;

    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showExport;

        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));

        //TODO 新增showExportType，将导出类型整合到按钮组
        if (this.options.showExportType) {
            var that = this,$btnGroup = this.$toolbar.find('>.btn-group'),
                $exportType = $btnGroup.find('div.exportType');

            /*glyphicon-list*/

            if (!$exportType.length) {
                $exportType = $([
                    '<div class="exportType btn-group">',
                    '<button class="btn btn-default dropdown-toggle" ' +
                    'data-toggle="dropdown" type="button">',
                    '<i class="glyphicon icon-share">导出当前页</i> ',
                    '<span class="caret"></span>',
                    '</button>',
                    '<ul class="dropdown-menu export-type" role="menu">',
                    '</ul>',
                    '</div>'].join('')).appendTo($btnGroup);

                var $menu = $exportType.find('.export-type');

                $menu.append(['<li data-type="basic">',
                    '<a href="javascript:void(0)">导出数量：当前页</a>',
                    '</li>'].join(''))
                    .append(['<li data-type="all">',
                    '<a href="javascript:void(0)">导出数量：全部</a>',
                    '</li>'].join(''))
                    .append(['<li data-type="selected">',
                        '<a href="javascript:void(0)">导出数量：选中</a>',
                        '</li>'].join(''));

                $menu.find('li').click(function () {
                    var type = $(this).data('type');
                    if(type=="basic"){
                        $exportType.find('.icon-share').html("导出当前页");
                    }
                    if(type=="all"){
                        $exportType.find('.icon-share').html("导出全部");
                    }
                    if(type=="selected"){
                        $exportType.find('.icon-share').html("导出选中");
                    }

                    that.refreshOptions({exportDataType:type});
                });
            }
        }

        if (this.options.showExport) {
            var that = this,
                $btnGroup = this.$toolbar.find('>.btn-group'),
                $export = $btnGroup.find('div.export');

            /*glyphicon-export*/
            if (!$export.length) {
                $export = $([
                    '<div class="export btn-group">',
                    '<button class="btn btn-default dropdown-toggle" ' +
                    'data-toggle="dropdown" type="button">',
                    '<i class="glyphicon  icon-share">导出格式</i> ',
                    '<span class="caret"></span>',
                    '</button>',
                    '<ul class="dropdown-menu" role="menu">',
                    '</ul>',
                    '</div>'].join('')).appendTo($btnGroup);

                var $menu = $export.find('.dropdown-menu'),
                    exportTypes = this.options.exportTypes;

                if (typeof this.options.exportTypes === 'string') {
                    var types = this.options.exportTypes.slice(1, -1).replace(/ /g, '').split(',');

                    exportTypes = [];
                    $.each(types, function (i, value) {
                        exportTypes.push(value.slice(1, -1));
                    });
                }
                $.each(exportTypes, function (i, type) {
                    if (TYPE_NAME.hasOwnProperty(type)) {
                        $menu.append(['<li data-type="' + type + '">',
                            '<a href="javascript:void(0)">',
                            TYPE_NAME[type],
                            '</a>',
                            '</li>'].join(''));
                    }
                });

                $menu.find('li').click(function () {
                    var type = $(this).data('type'),
                        doExport = function () {
                            that.$el.tableExport($.extend({}, that.options.exportOptions, {
                                type: type,
                                escape: false
                            }));
                        };

                    if (that.options.exportDataType === 'all' && that.options.pagination) {
                        that.togglePaginationAll(that.options.pageSize,function(){
                            doExport();
                            that.togglePagination();
                        });

                    } else if (that.options.exportDataType === 'selected') {
                        var data = that.getData(),
                            selectedData = that.getAllSelections();

                        //TODO 修改导出
                        var aData = {
                            'rows' : data,
                            'total' : data.length
                        };

                        var sData = {
                            'rows' : selectedData,
                            'total' : selectedData.length
                        };

                        that.exportLoad(sData);                 //TODO 修改导出
                        doExport();
                        that.exportLoad(aData);                 //TODO 修改导出
                    } else {
                        doExport();
                    }
                });
            }
        }
    };
})(jQuery);