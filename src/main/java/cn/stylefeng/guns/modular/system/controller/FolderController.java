package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.FolderDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.entity.Folder;
import cn.stylefeng.guns.modular.system.model.FolderDto;
import cn.stylefeng.guns.modular.system.service.FolderService;
import cn.stylefeng.guns.modular.system.warpper.FolderTreeWrapper;
import cn.stylefeng.guns.modular.system.warpper.FolderWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 文件夹控制器
 *
 * @author EricChen
 */
@Controller
@RequestMapping("/folder")
public class FolderController extends BaseController {

    private String PREFIX = "/modular/system/folder/";

    @Autowired
    private FolderService folderService;

    /**
     * 跳转到文件夹管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "folder.html";
    }

    /**
     * 跳转到添加目录
     */
    @RequestMapping("/folder_add")
    public String folderAdd() {
        return PREFIX + "folder_add.html";
    }


    /**
     * 跳转到修改目录
     */
    @Permission
    @RequestMapping("/folder_update")
    public String folderUpdate(@RequestParam("folderId") Long folderId) {

        if (ToolUtil.isEmpty(folderId)) {
            throw new RequestEmptyException();
        }

        //缓存目录修改前详细信息
        Folder folder = folderService.getById(folderId);
        LogObjectHolder.me().set(folder);

        return PREFIX + "folder_edit.html";
    }

    /**
     * 获取目录的tree列表，ztree格式
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.folderService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取目录的tree列表，treeview格式
     *
     */
    @RequestMapping(value = "/treeview")
    @ResponseBody
    public List<TreeviewNode> treeview() {
        List<TreeviewNode> treeviewNodes = this.folderService.treeviewNodes();

        //构建树
        DefaultTreeBuildFactory<TreeviewNode> factory = new DefaultTreeBuildFactory<>();
        factory.setRootParentId("0");
        List<TreeviewNode> results = factory.doTreeBuild(treeviewNodes);

        //把子节点为空的设为null
        FolderTreeWrapper.clearNull(results);

        return results;
    }

    /**
     * 新增目录
     */
    @BussinessLog(value = "添加目录", key = "simpleName", dict = FolderDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(Folder folder) {
        this.folderService.addFolder(folder);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有目录列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "folderId", required = false) String folderId) {
        Page<Map<String, Object>> list = this.folderService.list(condition, folderId);
        Page<Map<String, Object>> wrap = new FolderWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 目录详情
     */
    @RequestMapping(value = "/detail/{folderId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("folderId") Long folderId) {
        Folder folder = folderService.getById(folderId);
        FolderDto folderDto = new FolderDto();
        BeanUtil.copyProperties(folder, folderDto);
        folderDto.setPName(ConstantFactory.me().getFolderName(folderDto.getPid()));
        return folderDto;
    }

    /**
     * 修改目录
     */
    @BussinessLog(value = "修改目录", key = "simpleName", dict = FolderDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(Folder folder) {
        folderService.editFolder(folder);
        return SUCCESS_TIP;
    }

    /**
     * 删除目录
     */
    @BussinessLog(value = "删除目录", key = "folderId", dict = FolderDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long folderId) {

        //缓存被删除的目录名称
        LogObjectHolder.me().set(ConstantFactory.me().getFolderName(folderId));

        folderService.deleteFolder(folderId);

        return SUCCESS_TIP;
    }


}
