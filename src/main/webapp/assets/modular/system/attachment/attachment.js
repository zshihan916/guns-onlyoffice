layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

    /**
     * 系统管理--目录管理
     */
    var Folder = {
        tableId: "folderTable",
        condition: {
            folderId: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Folder.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'folderId', hide: true, sort: true, title: 'id'},
            {field: 'name', sort: true, title: '目录全称'},
            {field: 'path', sort: true, title: '层级路径'},
            {field: 'sort', sort: true, title: '排序'},
            {field: 'description', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Folder.search = function () {
        var queryData = {};
        queryData['condition'] = $("#name").val();
        queryData['folderId'] = Folder.condition.folderId;
        table.reload(Folder.tableId, {where: queryData});
    };

    /**
     * 选择目录时
     */
    Folder.onClickFolder = function (e, treeId, treeNode) {
        Folder.condition.folderId = treeNode.id;
        Folder.search();
    };

    /**
     * 弹出添加
     */
    Folder.openAddFolder = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加目录',
            content: Feng.ctxPath + '/folder/folder_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Folder.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Folder.exportExcel = function () {
        var checkRows = table.checkStatus(Folder.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑目录
     *
     * @param data 点击按钮时候的行数据
     */
    Folder.onEditFolder = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改目录',
            content: Feng.ctxPath + '/folder/folder_update?folderId=' + data.folderId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Folder.tableId);
            }
        });
    };

    /**
     * 点击删除目录
     *
     * @param data 点击按钮时候的行数据
     */
    Folder.onDeleteFolder = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/folder/delete", function () {
                Feng.success("删除成功!");
                table.reload(Folder.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("folderId", data.folderId);
            ajax.start();
        };
        Feng.confirm("是否删除目录 " + data.name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Folder.tableId,
        url: Feng.ctxPath + '/folder/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Folder.initColumn()
    });

    //初始化左侧目录树
    var ztree = new $ZTree("folderTree", "/folder/tree");
    ztree.bindOnClick(Folder.onClickFolder);

    ztree.init();

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Folder.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Folder.openAddFolder();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Folder.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Folder.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Folder.onEditFolder(data);
        } else if (layEvent === 'delete') {
            Folder.onDeleteFolder(data);
        }
    });
});
