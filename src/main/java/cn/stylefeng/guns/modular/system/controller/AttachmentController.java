package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.AttachmentDict;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.entity.Attachment;
import cn.stylefeng.guns.modular.system.model.AttachmentDto;
import cn.stylefeng.guns.modular.system.service.AttachmentService;
import cn.stylefeng.guns.modular.system.warpper.AttachmentWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 文件夹控制器
 *
 * @author EricChen
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController extends BaseController {

    private String PREFIX = "/modular/system/attachment/";

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 跳转到文件夹管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "attachment.html";
    }

    /**
     * 跳转到添加附件
     */
    @RequestMapping("/attachment_add")
    public String attachmentAdd() {
        return PREFIX + "attachment_add.html";
    }


    /**
     * 跳转到修改附件
     */
    @Permission
    @RequestMapping("/attachment_update")
    public String attachmentUpdate(@RequestParam("attachmentId") Long attachmentId) {

        if (ToolUtil.isEmpty(attachmentId)) {
            throw new RequestEmptyException();
        }

        //缓存附件修改前详细信息
        Attachment attachment = attachmentService.getById(attachmentId);
        LogObjectHolder.me().set(attachment);

        return PREFIX + "attachment_edit.html";
    }


    /**
     * 新增附件
     */
    @BussinessLog(value = "添加附件", key = "simpleName", dict = AttachmentDict.class)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public ResponseData add(AttachmentDto attachment) {
        //TODO
        return null;
    }

    /**
     * 获取所有附件列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long folderId) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }
        Page<Map<String, Object>> attachments = attachmentService.selectAttachments(name, beginTime, endTime, folderId);
        Page wrapped = new AttachmentWrapper(attachments).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 附件详情
     */
    @RequestMapping(value = "/detail/{attachmentId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("attachmentId") Long attachmentId) {
        //TODO
        return null;
    }

    /**
     * 修改附件
     */
    @BussinessLog(value = "修改附件", key = "simpleName", dict = AttachmentDict.class)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public ResponseData update(Attachment attachment) {
        return null;
    }

    /**
     * 删除附件
     */
    @BussinessLog(value = "删除附件", key = "attachmentId", dict = AttachmentDict.class)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long attachmentId) {
        return null;
    }



}
