package com.hmh.excel2db.transform.all;

/**
 *
 */
public class TransformDo {

    public TransformDo(){}

    public TransformDo(String value, String configValue){
        this.value = value;
        this.configValue = configValue;
    }
    //传输值
    private String value;
    //配置数值
    private String configValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
