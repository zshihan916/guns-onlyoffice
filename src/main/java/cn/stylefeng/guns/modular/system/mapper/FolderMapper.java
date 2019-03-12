package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.modular.system.entity.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Eric Chen
 */
public interface FolderMapper extends BaseMapper<Folder> {
    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有目录列表
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("condition") String condition, @Param("folderId") String folderId);

    /**
     * 获取所有目录树列表
     */
    List<TreeviewNode> treeviewNodes();

    /**
     * 获取父Folder
     */
    Folder getParentFolder(@Param("folder") Folder folder);

}
