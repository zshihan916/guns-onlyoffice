package cn.stylefeng.guns.core.common.constant.dictmap;

import cn.stylefeng.guns.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 附件的映射
 *
 * @author Chen 4/11/2019
 * @email 749829987@qq.com
 */
public class AttachmentDict extends AbstractDictMap {

    @Override
    public void init() {
        put("attachmentId", "附件Id");
        put("name", "附件名称");
        put("description", "备注");
        put("sort", "附件排序");
        put("createTime", "创建时间");
        put("updateTime", "修改时间");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("attachmentId", "getAttachmentName");
    }
}
