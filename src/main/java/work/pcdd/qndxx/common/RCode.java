package work.pcdd.qndxx.common;

import lombok.Getter;

/**
 * @author pcdd
 */
@Getter
public enum RCode {

    /* 成功状态码 */
    SUCCESS(200, "OK"),
    SUCCESS_LAYUI(0, "OK"),

    // 系统错误
    ERROR(500, "error"),

    /* 参数错误：1001-1999 */
    PARAM_IS_INVALID(1001, "参数无效"),

    PARAM_IS_BLANK(1002, "参数为空"),

    PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),

    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    CAPTCHA_ERROR(1005, "验证码错误"),

    /* 用户错误 */
    USER_NOT_LOGGED_IN(2001, "用户未登录，访问的路径需要验证，请登录"),

    USER_LOGIN_ERROR(2002, "账户不存在或密码错误"),

    USER_ACCOUNT_FORBIDDEN(2003, "账号已被禁用"),

    USER_NOT_EXIST(2004, "用户不存在"),

    USER_HAS_EXISTED(2005, "用户已存在"),

    /*文件错误*/
    FILE_NOT_FOUND(3001, "文件不存在"),

    /* 管理员错误 */
    ADMIN_OLD_PASSWORD_ERROR(4001, "旧密码错误"),

    ADMIN_UPDATE_PASSWORD_FAIL(4002, "修改密码失败");


    private final Integer code;
    private final String message;

    RCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
