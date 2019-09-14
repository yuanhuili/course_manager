package Management.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:13
 * @Version 1.0
 */
public class KlassRound {
    private BigInteger klassId;

    private BigInteger roundId;

    private BigInteger enrollNumber;

    private Integer classSerial;

    private Integer grade;

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public BigInteger getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(BigInteger enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public Integer getClassSerial() {
        return classSerial;
    }

    public void setClassSerial(Integer classSerial) {
        this.classSerial = classSerial;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
