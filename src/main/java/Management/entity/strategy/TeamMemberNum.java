package Management.entity.strategy;

import Management.entity.Course;

import java.util.ArrayList;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/28 11:18
 * @Version 1.0
 */
public class TeamMemberNum {
    Integer numOfMember;
    ArrayList<ArrayList<Course>> studentEntities;

    public Integer getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(Integer numOfMember) {
        this.numOfMember = numOfMember;
    }

    public ArrayList<ArrayList<Course>> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(ArrayList<ArrayList<Course>> studentEntities) {
        this.studentEntities = studentEntities;
    }
}
