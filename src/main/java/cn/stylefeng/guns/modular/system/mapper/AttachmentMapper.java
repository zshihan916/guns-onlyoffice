package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Attachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: Eric Chen
 */
public interface AttachmentMapper extends BaseMapper<Attachment> {

    Page<Map<String, Object>> selectAttachments(@Param("page") Page page, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("folderId") Long folderId);

}
