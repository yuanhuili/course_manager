package Management.entity;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/26
 */
public class ShareSeminarApplivations {
    private BigInteger id;

    private String mainCourseName;

    private String subCourseName;

    private String subCourseTeacherName;

    private int status;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMainCourseName() {
        return mainCourseName;
    }

    public void setMainCourseName(String mainCourseName) {
        this.mainCourseName = mainCourseName;
    }

    public String getSubCourseName() {
        return subCourseName;
    }

    public void setSubCourseName(String subCourseName) {
        this.subCourseName = subCourseName;
    }

    public String getSubCourseTeacherName() {
        return subCourseTeacherName;
    }

    public void setSubCourseTeacherName(String subCourseTeacherName) {
        this.subCourseTeacherName = subCourseTeacherName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
