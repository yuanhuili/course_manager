package Management.controller.vo;

import Management.entity.Klass;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/21 22:09
 * @Version 1.0
 */
public class ClassVO {
    private BigInteger id;
    private Integer grade;
    private Integer classSerial;
    private String time;
    private String classRoom;

    public ClassVO(){ };
    public ClassVO(Klass klass){
        this.id=klass.getId();
        this.grade=klass.getGrade();
        this.classSerial=klass.getKlassSerial();
        this.time=klass.getKlassTime();
        this.classRoom= klass.getKlassLocation();
        this.classSerial=klass.getKlassSerial();
        this.grade=klass.getGrade();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

}
