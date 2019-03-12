package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 目录的映射
 */
public class FolderDict extends AbstractDictMap {

    @Override
    public void init() {
        put("folderId", "目录名称");
        put("num", "目录排序");
        put("pid", "上级名称");
        put("name", "目录名称");
        put("path", "目录路径");
        put("description", "备注");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("folderId", "getFolderName");
        putFieldWrapperMethodName("pid", "getFolderName");
    }
}
