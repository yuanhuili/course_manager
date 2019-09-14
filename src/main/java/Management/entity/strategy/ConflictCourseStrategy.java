package Management.entity.strategy;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:28
 * @Version 1.0
 */
public class ConflictCourseStrategy implements Strategy {
    Long id;
    Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean isLegal(TeamMemberNum teamMemberNum) {
        return false;
    }
}

