package com.server.constant;

public final class CommonConstant {

    private CommonConstant() {
    }

    //redis key过期时间
    private final static Long LOGIN_JWT_EXPIRE_TIME = 1800L;

    //jwt在redis中的标签 -- jwt过期时间 LOGIN_JWT_TIME_ + user_id
    private final static String LOGIN_JWT_TIME = "LOGIN_JWT_TIME_";
    //错误登录次数锁定
    private final static String LOGIN_ERROR_SECONDS = "LOGIN_ERROR_SECONDS_";

    //管理员邮箱地址
    private final static String SEND_EMAIL_ADMIN = "admin@hyq.a";
    //管理员邮箱密码
    private final static String SEND_EMAIL_ADMIN_PASSWORD = "admin";

    //存放不用token就可以访问的路径
    private final static String NOT_LOGIN_ALLOW_PATH = "NOT_LOGIN_ALLOW_PATH_INTERCEPTOR";

    public static Long getLoginJwtExpireTime() {
        return LOGIN_JWT_EXPIRE_TIME;
    }

    public static String getLoginJwtTime() {
        return LOGIN_JWT_TIME;
    }

    public static String getLoginErrorSeconds() {
        return LOGIN_ERROR_SECONDS;
    }

    public static String getNotLoginAllowPath() {
        return NOT_LOGIN_ALLOW_PATH;
    }

    public static String getSendEmailAdmin() {
        return SEND_EMAIL_ADMIN;
    }

    public static String getSendEmailAdminPassword() {
        return SEND_EMAIL_ADMIN_PASSWORD;
    }
}
