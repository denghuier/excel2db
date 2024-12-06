package com.hmh.excel2db.domain;

import java.util.List;

public class Task {
    private Long taskId;

    private String taskName;
    // 文件名
    private String fileName;
    // 数据源id
    private Long dsId;
    // 表名
    private String tableName;
    // sheet名
    private String sheetName;
    // 表头行数
    private Integer headerNum;
    // 开始行数
    private Integer startRowNum;
    // 结束行数
    private Integer totalRowNum;
    // 剔除最后的行数
    private Integer removeEndNum;
    // 存储路径
    private String storePath;
    // 转换配置
    private List<TaskTransformConfig> list;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getDsId() {
        return dsId;
    }

    public void setDsId(Long dsId) {
        this.dsId = dsId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getHeaderNum() {
        return headerNum;
    }

    public void setHeaderNum(Integer headerNum) {
        this.headerNum = headerNum;
    }

    public Integer getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(Integer startRowNum) {
        this.startRowNum = startRowNum;
    }

    public Integer getTotalRowNum() {
        return totalRowNum;
    }

    public void setTotalRowNum(Integer totalRowNum) {
        this.totalRowNum = totalRowNum;
    }

    public Integer getRemoveEndNum() {
        return removeEndNum;
    }

    public void setRemoveEndNum(Integer removeEndNum) {
        this.removeEndNum = removeEndNum;
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public List<TaskTransformConfig> getList() {
        return list;
    }

    public void setList(List<TaskTransformConfig> list) {
        this.list = list;
    }
}
