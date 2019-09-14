package Management.entity;

import Management.entity.Share;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/11 20:58
 * @Version 1.0
 */
public class ShareRequest {
    private int id;

    private Share share;

    private String handleType;//枚举{ unhandle，accept，reject}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }
}
