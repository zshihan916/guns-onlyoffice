package cn.stylefeng.guns.modular.system.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Chen 4/11/2019
 * @email 749829987@qq.com
 */
@Data
public class AttachmentDto {
    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long attachmentId;

    /**
     * 文件夹ID
     */
    private Long folderId;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private String status;


    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
