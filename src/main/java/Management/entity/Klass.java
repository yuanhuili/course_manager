package Management.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import java.math.BigInteger;
import java.util.Date;
/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:11
 * @Version 1.0
 */
public class Klass {
    private BigInteger id;

    private BigInteger courseId;

    private int grade;

    private int klassSerial;

    private String klassTime;

    private String klassLocation;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(int klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getKlassTime() {
        return klassTime;
    }

    public void setKlassTime(String klassTime) {
        this.klassTime = klassTime;
    }

    public String getKlassLocation() {
        return klassLocation;
    }

    public void setKlassLocation(String klassLocation) {
        this.klassLocation = klassLocation;
    }
}
