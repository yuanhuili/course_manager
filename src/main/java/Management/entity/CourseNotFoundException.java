package Management.entity;

import java.math.BigInteger;

public class CourseNotFoundException extends RuntimeException {
    private BigInteger courseId;
    public CourseNotFoundException(BigInteger courseId){ this.courseId=courseId; }
    public BigInteger getId() { return courseId; }
}
