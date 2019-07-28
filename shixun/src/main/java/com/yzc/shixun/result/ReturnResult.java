package com.yzc.shixun.result;

public class ReturnResult {

    public boolean success;
    public Object datas;

    public ReturnResult(boolean success, Object data) {
        this.success = success;
        this.datas = data;
    }
    public ReturnResult(Object data) {
        this.success = false;
        this.datas = data;
    }
}
