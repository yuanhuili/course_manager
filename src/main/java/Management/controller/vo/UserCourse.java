package Management.controller.vo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/5 19:40
 * @Version 1.0
 */
public class UserCourse {
    private BigInteger courseId;
    private BigInteger userId;

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
}
