package cn.stylefeng.guns.modular.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典信息
 */
@Data
public class FolderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long folderId;
    /**
     * 父文件夹id
     */
    private Long pid;

    /**
     * 父目录名称
     */
    private String pName;

    /**
     * 全称
     */
    private String name;
    /**
     * 层级路径
     */
    private String path;
    /**
     * 全称
     */
    private String fullName;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
}
