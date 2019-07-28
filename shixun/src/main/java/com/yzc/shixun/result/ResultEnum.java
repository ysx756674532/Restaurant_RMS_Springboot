package com.yzc.shixun.result;

public enum  ResultEnum {
        LOGIN_SUCCESS("00001","登陆成功"),
        SYSTEM_FAIL("00002","系统错误"),
        LOGIN_FAIL_USERNAME("00003","登陆失败，用户名不存在"),
        LOGIN_FAIL_PASSWORD("00004","登陆失败，密码不正确"),
        LOGIN_FAIL_FACE("00005","登陆失败，人脸不匹配"),
        FACE_IDENTIFY_DETECT_NOTPEOPLE("00006","未检测到人脸"),
        FACE_IDENTIFY_DETECT_MANYPEOPLE("00007","检测张多张人脸"),
        FACE_IDENTIFY_SEARCH_FAIL("00008","人脸搜索失败，没有您的信息"),
        FACE_IDENTIFY_SEARCH_SUCCESS("00009","人脸匹配成功"),
        ADD_COMMENT_FAIL("00010","添加评论失败"),
        ADD_COMMENT_SUCCESS("00011","添加评论成功"),
        DELETE_COMMENT_SUCCESS("00012","删除评论成功"),
        DELETE_COMMENT_FAIL("00013","删除评论失败"),
        ADD_ORDER_SUCCESS("00014","添加订单成功"),
        ADD_ORDER_FAIL("00015","添加订单失败，该时间段无餐桌"),
        TIME_FORMAT_FAIL("00016","时间格式错误"),
        CANCEL_ORDER_SUCCESS("00017","取消订单成功"),
        CANCEL_FOODINFOS_SUCCESS("00018","删除所选菜单成功"),
        ADD_FOOD_SUCCESS("00019","新增菜品成功"),
        ADD_TABLE_SUCCESS("00020","新增餐桌成功"),
        ADD_TABLE_FAIL("00021","新增餐桌失败，请查看餐桌ID不能重复"),
        GET_CODE_SUCCESS("00022","获取验证码成功!"),
        GET_CODE_FAIL("00022","获取验证码失败,邮箱已经注册"),
        VERIFICATION_SUCCESS("00023","验证成功"),
        VERIFICATION_FAIL("00024","验证失败"),
        GET_IMAGE("00025","成功获取照片"),
        REGISTER_SUCCESS("00026","注册成功"),
        REGISTER_FAIL("00027","注册失败"),
    ;
    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
