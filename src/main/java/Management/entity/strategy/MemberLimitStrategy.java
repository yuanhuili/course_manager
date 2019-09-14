package Management.entity.strategy;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:25
 * @Version 1.0
 */
public class MemberLimitStrategy implements Strategy {
    Long id;
    Byte minMember;
    Byte maxMember;

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
        return false;
    }


}
