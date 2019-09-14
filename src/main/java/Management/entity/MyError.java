package Management.entity;

public class MyError {
    private int code;//错误状态码
    private String message;//错误提示信息
    public MyError(int code,String message){
        this.code=code;
        this.message=message;
    }
    public int getCode(){return code;}
    public String getMessage(){return message;}
}

