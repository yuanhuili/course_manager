package Management.entity;

import Management.entity.Course;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/11 20:44
 * @Version 1.0
 */
public class Share {
    private int id;

    private  String shareType;

    private Course masterCourse;

    private Course receiveCourse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Course getMasterCourse() {
        return masterCourse;
    }

    public void setMasterCourse(Course masterCourse) {
        this.masterCourse = masterCourse;
    }

    public Course getReceiveCourse() {
        return receiveCourse;
    }

    public void setReceiveCourse(Course receiveCourse) {
        this.receiveCourse = receiveCourse;
    }
}
