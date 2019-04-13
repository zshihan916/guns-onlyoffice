/**
 * 目录详情对话框
 */
var FolderInfoDlg = {
    data: {
        folderId: "",
        folderName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 点击目录时
    $('#folderName').click(function () {
        var formName = encodeURIComponent("parent.FolderInfoDlg.data.folderName");
        var formId = encodeURIComponent("parent.FolderInfoDlg.data.folderId");
        var treeUrl = encodeURIComponent(Feng.ctxPath + "/folder/tree");

        layer.open({
            type: 2,
            title: '父级目录',
            area: ['300px', '200px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#folderId").val(FolderInfoDlg.data.folderId);
                $("#folderName").val(FolderInfoDlg.data.folderName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/folder/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});