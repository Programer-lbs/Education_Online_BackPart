package org.edu.commonutil;


public enum Code {
    SUCCESS(20000),
    FAIL(20001);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    Code(Integer code){
        this.code = code;
    }
}
