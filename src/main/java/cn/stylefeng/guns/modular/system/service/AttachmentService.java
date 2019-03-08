package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.entity.Attachment;
import cn.stylefeng.guns.modular.system.mapper.AttachmentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Eric Chen
 */
@Service
public class AttachmentService extends ServiceImpl<AttachmentMapper, Attachment> {

    @Resource
    private AttachmentMapper attachmentMapper;
}
