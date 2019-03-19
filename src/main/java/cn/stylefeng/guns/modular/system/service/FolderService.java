package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.TreeviewNode;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.entity.Folder;
import cn.stylefeng.guns.modular.system.mapper.FolderMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: Eric Chen
 */
@Service
public class FolderService extends ServiceImpl<FolderMapper, Folder> {

    @Resource
    private FolderMapper folderMapper;

    /**
     * 新增目录
     */
    @Transactional(rollbackFor = Exception.class)
    public void addFolder(Folder folder) {

        if (ToolUtil.isOneEmpty(folder, folder.getName(), folder.getPath(), folder.getPid(), folder.getDescription())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.folderSetPids(folder);

        //完善路径，加上所有父亲的path
        this.setParentPath(folder);

        this.save(folder);
    }

    /**
     * 修改目录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editFolder(Folder folder) {

        if (ToolUtil.isOneEmpty(folder, folder.getFolderId(), folder.getName(), folder.getPath(), folder.getPid(), folder.getDescription())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.folderSetPids(folder);

        //完善路径，加上所有父亲的path
//        this.setParentPath(folder);

        this.updateById(folder);
    }

    /**
     * 删除目录
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteFolder(Long folderId) {
        Folder folder = folderMapper.selectById(folderId);

        //根据like查询删除所有级联的目录
        QueryWrapper<Folder> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("PIDS", "%[" + folder.getFolderId() + "]%");
        List<Folder> subFolders = folderMapper.selectList(wrapper);
        for (Folder temp : subFolders) {
            this.removeById(temp.getFolderId());
        }

        this.removeById(folder.getFolderId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有目录列表
     */
    public Page<Map<String, Object>> list(String condition, String folderId) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition, folderId);
    }

    /**
     * 设置目录的父级ids
     */
    private void folderSetPids(Folder folder) {
        if (ToolUtil.isEmpty(folder.getPid()) || folder.getPid().equals(0L)) {
            folder.setPid(0L);
            folder.setPids("[0],");
        } else {
            Long pid = folder.getPid();
            Folder temp = this.getById(pid);
            String pids = temp.getPids();
            folder.setPid(pid);
            folder.setPids(pids + "[" + pid + "],");
        }
    }

    /**
     * 设置 父路径
     *
     * @param folder
     * @return
     */
    private void setParentPath(Folder folder) {
        Folder parent = getParent(folder);
        if (ToolUtil.isNotEmpty(parent)) {
            String path = folder.getPath();
            folder.setPath(parent.getPath() + path);
        }
    }

    public Folder getParent(Folder folder) {
        if (folder == null || folder.getPid() == null || folder.getPid() == 0) {
            return null;
        }
        Folder parent = folderMapper.selectById(folder.getPid());
        if (parent == null || ToolUtil.isEmpty(parent.getPath())) {
            return null;
        }
        return parent;
    }


}
