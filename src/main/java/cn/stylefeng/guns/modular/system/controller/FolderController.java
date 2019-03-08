package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.service.DeptService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件夹控制器
 *
 * @author EricChen
 */
@Controller
@RequestMapping("/folder")
public class FolderController extends BaseController {

    private String PREFIX = "/modular/system/attachment/";

    @Autowired
    private DeptService deptService;

    /**
     * 跳转到文件夹管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "attachment.html";
    }

    /**
     * 跳转到添加文件夹
     */
    @RequestMapping("/dept_add")
    public String deptAdd() {
        return PREFIX + "attachment_add.html";
    }



}
