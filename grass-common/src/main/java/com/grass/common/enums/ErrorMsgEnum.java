package com.grass.common.enums;

/**
 * @Author Fenglixiong
 * @Create 2018.11.10 10:49
 * @Description
 **/
public enum ErrorMsgEnum {

    //正常返回的枚举
    SUCCESS(true, 200,"正常返回", "操作成功"),

    // 系统错误，50开头
    SYS_ERROR(false, 500, "系统错误", "亲，系统出错了哦~"),
    PARAM_CHECK(false,300,"字段校验异常","字段校验出现问题了哦~"),
    PARAM_MISSING(false, 400, "少参异常", "请求缺少参数了哦~"),
    PARAM_INVILAD(false, 600, "传参异常", "传参出现异常了哦~"),
    JSON_CONVERT_ERROR(false, 700, "JSON解析异常", "JSON解析出现异常了哦~"),
    UN_LOGIN(false, 4001, "尚未登录", "请登录后查看哦~"),
    LOGIN_LOCK(false, 4002, "用户禁用", "已被禁用请联系管理员处理~"),
    NO_PERMISSION(false, 4003, "暂无权限", "您现在暂无权限访问哦~"),
    LOGIN_ERROR(false, 4004, "用户名或者密码错误", "请仔细检查用户名密码哦~");


    private boolean status;
    private int code;
    private String msg;
    private String userMsg;

    private ErrorMsgEnum(boolean status, int code, String msg ,String userMsg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.userMsg = userMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }
}
