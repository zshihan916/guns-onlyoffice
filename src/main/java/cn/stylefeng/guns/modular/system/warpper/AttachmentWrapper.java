package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author Chen 4/11/2019
 * @email 749829987@qq.com
 */
public class AttachmentWrapper extends BaseControllerWrapper {
    public AttachmentWrapper(Map<String, Object> single) {
        super(single);
    }

    public AttachmentWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public AttachmentWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public AttachmentWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
