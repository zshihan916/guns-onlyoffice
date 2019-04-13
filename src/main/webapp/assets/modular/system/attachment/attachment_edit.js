/**
 * 角色详情对话框
 */
var FolderInfoDlg = {
    data: {
        pid: "",
        pName: ""
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

    //获取目录信息
    var ajax = new $ax(Feng.ctxPath + "/folder/detail/" + Feng.getUrlParam("folderId"));
    var result = ajax.start();
    form.val('folderForm', result);

    // 点击上级角色时
    $('#pName').click(function () {
        var formName = encodeURIComponent("parent.FolderInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.FolderInfoDlg.data.pid");
        var treeUrl = encodeURIComponent(Feng.ctxPath + "/folder/tree");

        layer.open({
            type: 2,
            title: '父级目录',
            area: ['300px', '200px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#pid").val(FolderInfoDlg.data.pid);
                $("#pName").val(FolderInfoDlg.data.pName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/folder/update", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});