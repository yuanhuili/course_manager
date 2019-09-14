package Management.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:26
 * @Version 1.0
 */
public class MemberSexStrategy {
    private BigInteger id;

    private int minMale;

    private int maxMale;

    private int minFemale;

    private int maxFemale;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getMinMale() {
        return minMale;
    }

    public void setMinMale(int minMale) {
        this.minMale = minMale;
    }

    public int getMaxMale() {
        return maxMale;
    }

    public void setMaxMale(int maxMale) {
        this.maxMale = maxMale;
    }

    public int getMinFemale() {
        return minFemale;
    }

    public void setMinFemale(int minFemale) {
        this.minFemale = minFemale;
    }

    public int getMaxFemale() {
        return maxFemale;
    }

    public void setMaxFemale(int maxFemale) {
        this.maxFemale = maxFemale;
    }
}
