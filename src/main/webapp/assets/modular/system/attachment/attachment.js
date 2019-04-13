layui.use(['layer', 'form', 'table', 'ztree', 'laydate', 'admin', 'ax'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ZTree = layui.ztree;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var admin = layui.admin;

    /**
     * 系统管理--附件管理
     */
    var Attachment = {
        tableId: "attachmentTable",    //表格id
        condition: {
            name: "",
            folderId: "",
            timeLimit: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Attachment.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'attachmentId', hide: true, sort: true, title: '附件id'},
            {field: 'path', sort: true, title: '路径'},
            {field: 'name', sort: true, title: '附件名'},
            {field: 'description', sort: form, title: '备注'},
            {field: 'createTime', sort: true, title: '创建时间'},
            {field: 'updateTime', sort: true, title: '最后更新'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 280}
        ]];
    };

    /**
     * 选择部门时
     */
    Attachment.onClickFolder = function (e, treeId, treeNode) {
        Attachment.condition.folderId = treeNode.id;
        Attachment.search();
    };

    /**
     * 点击查询按钮
     */
    Attachment.search = function () {
        var queryData = {};
        queryData['folderId'] = Attachment.condition.folderId;
        queryData['name'] = $("#name").val();
        queryData['timeLimit'] = $("#timeLimit").val();
        table.reload(Attachment.tableId, {where: queryData});
    };

    /**
     * 弹出添加附件对话框
     */
    Attachment.openAddAttachment = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加附件',
            content: Feng.ctxPath + '/attachment/attachment_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Attachment.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Attachment.exportExcel = function () {
        var checkRows = table.checkStatus(Attachment.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑附件按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Attachment.onEditAttachment = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '编辑附件',
            content: Feng.ctxPath + '/attachment/attachment_edit?attachmentId=' + data.attachmentId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Attachment.tableId);
            }
        });
    };

    /**
     * 点击删除附件按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Attachment.onDeleteAttachment = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/attachment/delete", function () {
                table.reload(Attachment.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("attachmentId", data.attachmentId);
            ajax.start();
        };
        Feng.confirm("是否删除附件" + data.account + "?", operation);
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Attachment.tableId,
        url: Feng.ctxPath + '/attachment/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Attachment.initColumn()
    });

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        range: true,
        max: Feng.currentDate()
    });

    //初始化左侧目录树    var ztree = new $ZTree("folderTree", "/folder/tree");
    var ztree = new $ZTree("folderTree", "/folder/tree");
    ztree.bindOnClick(Attachment.onClickFolder);
    ztree.init();

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Attachment.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Attachment.openAddAttachment();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Attachment.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Attachment.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Attachment.onEditAttachment(data);
        } else if (layEvent === 'delete') {
            Attachment.onDeleteAttachment(data);
        } else if (layEvent === 'roleAssign') {
            Attachment.roleAssign(data);
        } else if (layEvent === 'reset') {
            Attachment.resetPassword(data);
        }
    });

    // 修改attachment状态
    form.on('switch(status)', function (obj) {

        var attachmentId = obj.elem.value;
        var checked = obj.elem.checked ? true : false;

        Attachment.changeUserStatus(attachmentId, checked);
    });

});
