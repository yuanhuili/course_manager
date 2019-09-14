package Management.entity;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/28 11:30
 * @Version 1.0
 */
public class SimpleCourseEntity {
    private Integer id;
    private Integer teacherId;
    private String courseName;
    private String introduction;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

