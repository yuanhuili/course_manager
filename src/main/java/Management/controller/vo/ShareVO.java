package Management.controller.vo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/22 12:02
 * @Version 1.0
 */
public class ShareVO {
    private BigInteger  id;
    private BigInteger mainCourseId;
    private BigInteger subCourseId;
    private CourseVO mainCourse;
    private CourseVO subCourse;


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

    public CourseVO getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(CourseVO mainCourse) {
        this.mainCourse = mainCourse;
    }

    public CourseVO getSubCourse() {
        return subCourse;
    }

    public void setSubCourse(CourseVO subCourse) {
        this.subCourse = subCourse;
    }
}
