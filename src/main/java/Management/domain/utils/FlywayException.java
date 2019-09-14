package Management.domain.utils;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public class FlywayException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
    private Object data;

    public FlywayException(FlywayCodeEnum appExCode) {
        super(appExCode.getCode() + appExCode.getMessage());
        this.errorCode = appExCode.getCode();
        this.errorMessage = appExCode.getMessage();
    }

    public FlywayException(String errorCode, String errorMessage) {
        super(errorCode + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public FlywayException(String errorCode, String errorMessage, Object data) {
        super(errorCode + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return String.format("errorCode:%s,errorMessage:%s", this.errorCode, this.errorMessage);
    }
}
