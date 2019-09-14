package Management.domain.utils;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public enum  FlywayCodeEnum {
        SUCCESS("00", "处理成功"),
        SEND_ERROR("01","发送邮件失败"),
        UNKNOW_ERROR("1000", "未知错误"),
        VERSION_NOT_SUPPORT("1001", "版本号不正确"),
        INTERFACE_INVALID("1002", "暂不支持的接口类型"),
        REMOTE_ACCESS_ERROR("1003", "远程访问异常"),
        DATA_ACCESS_ERROR("1004", "数据访问异常"),
        INVALID_REQUEST_FORMAT("1005", "请求格式错误"),
        SYS_TOO_BUSY("1006", "系统太忙啦，请稍后"),
        SYS_INIT_ERROR("1007", "系统初始化错误"),
        USER_NOT_LOGIN("1008", "用户未登录"),
        ACCOUNT_LOCKED("1009", "账号被锁定!"),
        LOGIN_FAILED("10010", "登录失败!"),
        USER_NAME_OR_PASSWORK_ERROR("10011", "账号或密码错误"),
        USER_NOT_PERMISSIONS("10012", "您暂无权限进行此操作,请联系管理员!"),
        MISS_REQUEST("10013", "请求参数缺失"),
        USER_NOT_PROJECT_ACCESSES("10014", "用户没用项目权限"),
        WECHAT_ERROR("10015", "微信端错误"),
        DB_INSERT_ERROR("2001", "数据库插入失败"),
        DB_UPDATE_ERROR("2002", "数据库更新失败"),
        DB_DELETE_ERROR("2003", "数据库删除失败"),
        DB_SELECT_ERROR("2004", "数据库查询不到数据"),
        DATA_PARSE_ERROR("3001", "数据转化错误"),
        DUPLICATE_KEY_ERROR("3002", "唯一性冲突!"),
        UPLOAD_EXCEL_FILE("3003", "请上传excel文件!"),
        DATE_PARSE_ERROR("3004", "日期格式错误"),
        EXCEL_ERROR("B80001", "不是合法的Excel模板"),
        EXPORT_EXCEL_ERROR("B80002", "导出excel文件出错"),
        ZIP_FILE_ERROR("B80003", "压缩文件出错"),
        EXCEL_CONTENT_EMPTY("B80004", "excel内容为空"),
        EXCEL_TEMP_ERROR("B80001", "导入文件格式错误！请根据下载模板进行导入"),
        MAX_MALICIOUS_REQUEST_TIMES("100016", "请求太频繁了，请稍后！");

private String code;
private String message;

public static String getDescription(String errorCode) {
    FlywayCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            FlywayCodeEnum item = var1[var3];
        if (item.getCode().equals(errorCode)) {
        return item.getMessage();
        }
        }

        return null;
        }

private FlywayCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
        }

public FlywayException getException() {
        return new FlywayException(this.getCode(), this.getMessage());
        }

public String getCode() {
        return this.code;
        }

public String getMessage() {
        return this.message;
        }
        }
