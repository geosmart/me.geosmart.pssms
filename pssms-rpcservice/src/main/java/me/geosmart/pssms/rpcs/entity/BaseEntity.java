/**
 *
 */
package me.geosmart.pssms.rpcs.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Domain:用户表
 *
 * @author geosmart
 * @date 2017-3-8
 */
public abstract class BaseEntity<T extends BaseEntity> extends Model<T> {
    @TableField("creation_time")
    Date creationTime;

    @TableField("last_modified_time")
    Date lastModifiedTime;

    @TableField("is_deleted")
    Boolean isDeleted;

    public BaseEntity() {
        this.creationTime = new Date();
        this.lastModifiedTime = new Date();
        this.isDeleted = false;
    }

    /**
     * 格式化时间
     *
     * @param time   时间
     * @param format 时间格式
     * @return 时间字串
     */
    public static String formatDateTime(java.util.Date time, String format) {
        if (time == null)
            return "";
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(time);
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    private String getCreationTimeString() {
        if (creationTime == null || creationTime.getTime() == 0) {
            return "-";
        } else {
            return formatDateTime(creationTime, "yyyy-MM-dd HH:mm:ss");

        }
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
