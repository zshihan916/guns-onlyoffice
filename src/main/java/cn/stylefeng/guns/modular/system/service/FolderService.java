package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.entity.Folder;
import cn.stylefeng.guns.modular.system.mapper.FolderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Eric Chen
 */
@Service
public class FolderService extends ServiceImpl<FolderMapper, Folder> {

    @Resource
    private FolderMapper folderMapper;

}
