package com.hmh.excel2db.db.all.domain;

/**
 * 字段信息
 */
public class ColInfo {
    private String columnName;
    private String columnType;
    private String columnRemarks;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnRemarks() {
        return columnRemarks;
    }

    public void setColumnRemarks(String columnRemarks) {
        this.columnRemarks = columnRemarks;
    }
}
