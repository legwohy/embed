package com.cobra.common;


import java.io.Serializable;

public class Response<T> implements Serializable{
    private static final long serialVersionUID = 438211556926847917L;

    private final static String success = CobraStatus.SUCCESS.getCode();
    private String code;
    private String message;
    private T result;

    public Response(){

    }

    public Response(String code,String message){
        this.code = code;
        this.message = message;
    }
    public Response(T result){
        this.code =   CobraStatus.SUCCESS.getCode();
        this.message = CobraStatus.SUCCESS.getDesc();
        this.result = result;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
