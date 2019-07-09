package com.cobra.common;


public enum  CobraStatus {
    SUCCESS("200","操作成功"),
    ERROR("500","系统错误");
    private String code;
    private String desc;
     CobraStatus(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
