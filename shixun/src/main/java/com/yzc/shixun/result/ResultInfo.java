package com.yzc.shixun.result;

public class ResultInfo {

    private String code;
    private String message;

    public ResultInfo(ResultEnum resultEnum) {
        this.code=resultEnum.getCode();
        this.message=resultEnum.getMessage();
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
