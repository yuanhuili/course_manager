package Management.entity.strategy;

import Management.entity.Course;

import java.util.ArrayList;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/20 21:38
 * @Version 1.0
 */
public class CourseMemberLimitStrategy implements Strategy {
    Long id;
    Long courseId;
    Byte minMember;
    Byte maxMember;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }


    @Override
    public boolean isLegal(TeamMemberNum teamMemberNum) {
        Integer count=0;
        for(ArrayList<Course> courseEntities:teamMemberNum.getStudentEntities()){
            for(Course courseEntity:courseEntities){
                if(courseEntity.getId().equals(this.courseId)){
                    count++;
                    break;
                }
            }
        }
        if(count>minMember&&count<maxMember){
            return false;
        }else{
            return true;
        }
    }
}
