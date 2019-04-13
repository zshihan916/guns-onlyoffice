package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.entity.Attachment;
import cn.stylefeng.guns.modular.system.mapper.AttachmentMapper;
import cn.stylefeng.guns.modular.system.model.AttachmentDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Eric Chen
 */
@Service
public class AttachmentService extends ServiceImpl<AttachmentMapper, Attachment> {

    @Resource
    private AttachmentMapper attachmentMapper;

    public void addAttachment(AttachmentDto dto) {

    }

    /**
     * 修改附件
     */
    public void editAttachment(Attachment user) {
    }

    /**
     * 删除用户
     */
    public void deleteAttachment(Long userId) {

    }


    /**
     * 根据条件查询用户列表
     */
    public Page<Map<String, Object>> selectAttachments(String name, String beginTime, String endTime, Long folderId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectAttachments(page, name, beginTime, endTime, folderId);
    }


}
