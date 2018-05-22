$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pictures/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, sortable: false, key: true},
            {label: '图片地址', name: 'path', index: 'path', sortable: false, width: 105, formatter: imgFormatter},
            {label: '添加时间', name: 'time', index: 'time', sortable: false, width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    function imgFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"120\" width=\"135\"/>";
    }

});

var vm = new Vue({
    el: '#app',
    data: {
        showList: true,
        title: null,
        picture: {}
    },
    mounted: function () {
        new AjaxUpload('#upload', {
            action: baseURL + 'images',
            name: 'file',
            autoSubmit: true,
            responseType: "json",
            onSubmit: function (file, extension) {
                if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                    alert('只支持jpg、png、gif格式的图片！');
                    return false;
                }
            },
            onComplete: function (file, r) {
                if (r.code == 200) {
                    vm.picture.path = r.url;
                    console.log(r);
                    $("#img").attr("src", r.url);
                    $("#img").attr("style", "display:block;");
                    return false;
                } else {
                    alert(r.msg);
                }
            }
        });
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.picture = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.picture.id == null ? "pictures/save" : "pictures/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.picture),
                success: function (r) {
                    if (r.code === 200) {
                        alert('操作成功', function (index) {
                            $("#img").attr("style", "display:none;");
                            vm.reload();
                        });
                    } else {
                        $("#img").attr("style", "display:none;");
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "pictures/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 200) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "pictures/info/" + id, function (r) {
                vm.picture = r.picture;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});