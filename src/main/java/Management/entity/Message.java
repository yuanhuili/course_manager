package Management.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.math.BigInteger;
import java.util.Date;
/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:28
 * @Version 1.0
 */
public class Message {
    @TableField(value="id")
    private BigInteger id;

    @TableField(value="gmtCreate")
    private  Date gmtCreate;

    @TableField(value="gmtModified")
    private  Date gmtModified;

    @TableField(value="context")
    private String context;

    @TableField(value="isAccept")
    private int isAccept;

    @TableField(value="teacherId")
    private  BigInteger teacherId;

    @TableField(value="fromPersonId")
    private BigInteger fromPersonId;

    @TableField(value="type")
    private int type;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(int isAccept) {
        this.isAccept = isAccept;
    }

    public BigInteger getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(BigInteger teacherId) {
        this.teacherId = teacherId;
    }

    public BigInteger getFromPersonId() {
        return fromPersonId;
    }

    public void setFromPersonId(BigInteger fromPersonId) {
        this.fromPersonId = fromPersonId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
