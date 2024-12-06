package com.hmh.excel2db.db.all.param;



/**
 * 参数字段信息*
 */

public class LinkParam
{
    private String colName;

    private String colDescription;
    /**
     * 是否必填
     * 0：不必填
     * 1：必填
     */
    private int required;
    /**
     * 是否加密
     * 0：不加密
     * 1：加密*
     */
    private int encryption;
    /**
     * 默认值
     */
    private Object defaultValue;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColDescription() {
        return colDescription;
    }

    public void setColDescription(String colDescription) {
        this.colDescription = colDescription;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public int getEncryption() {
        return encryption;
    }

    public void setEncryption(int encryption) {
        this.encryption = encryption;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public LinkParam(String colName, String colDescription, int required, int encryption, Object defaultValue){
        this.colName = colName;
        this.colDescription=colDescription;
        this.encryption=encryption;
        this.required=required;
        this.defaultValue=defaultValue;
    }
}
