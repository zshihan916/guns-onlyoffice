package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件夹表
 * </p>
 *
 * @author EricChen
 * @since 2018-12-07
 */
@TableName("sys_folder")
public class Folder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "FOLDER_ID", type = IdType.ID_WORKER)
    private Long folderId;

    /**
     * 父文件夹id
     */
    @TableField("PID")
    private Long pid;

    /**
     * 父级ids
     */
    @TableField("PIDS")
    private String pids;

    /**
     * 全称
     */
    @TableField("NAME")
    private String name;

    /**
     * 层级路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 版本（乐观锁保留字段）
     */
    @TableField("VERSION")
    private Integer version;

    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folderId=" + folderId +
                ", pid=" + pid +
                ", pids='" + pids + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", description='" + description + '\'' +
                ", version=" + version +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }
}
