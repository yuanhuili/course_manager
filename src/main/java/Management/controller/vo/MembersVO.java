package Management.controller.vo;

import Management.entity.Student;

import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/26 3:58
 * @Version 1.0
 */
public class MembersVO {
    private List<Student> members;

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }
}
