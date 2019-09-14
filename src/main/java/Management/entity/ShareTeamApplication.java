package Management.entity;

import Management.controller.vo.CourseVO;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:20
 * @Version 1.0
 */
public class ShareTeamApplication {
    private BigInteger id;

    private BigInteger mainCourseId;

    private BigInteger subCourseId;

    private BigInteger subCourseTeacherId;


    private int status;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getMainCourseId() {
        return mainCourseId;
    }

    public void setMainCourseId(BigInteger mainCourseId) {
        this.mainCourseId = mainCourseId;
    }

    public BigInteger getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(BigInteger subCourseId) {
        this.subCourseId = subCourseId;
    }

    public BigInteger getSubCourseTeacherId() {
        return subCourseTeacherId;
    }

    public void setSubCourseTeacherId(BigInteger subCourseTeacherId) {
        this.subCourseTeacherId = subCourseTeacherId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
