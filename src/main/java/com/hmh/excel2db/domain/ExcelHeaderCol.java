package com.hmh.excel2db.domain;

/**
 * excel 表头对象
 * @author hmh
 */
public class ExcelHeaderCol {
    /**
     * 字段名称
     */
    private String colName;
    /**
     * 字段序号
     */
    private Integer colNum;

    private String transformType;

    private String transformValue;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Integer getColNum() {
        return colNum;
    }

     public void setColNum(Integer colNum) {
         this.colNum = colNum;
     }

    public String getTransformType() {
        return transformType;
    }

    public void setTransformType(String transformType) {
        this.transformType = transformType;
    }

    public String getTransformValue() {
        return transformValue;
    }

    public void setTransformValue(String transformValue) {
        this.transformValue = transformValue;
    }
}
