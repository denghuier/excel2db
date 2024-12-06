package com.hmh.excel2db.domain;

/**
 * 转换配置
 * @author hmh
 */
public class TaskTransformConfig {

    public TaskTransformConfig(){}

    public TaskTransformConfig(String sourceColumn, String targetColumn, String transformType, String configValue){
        this.sourceColumn = sourceColumn;
        this.targetColumn = targetColumn;
        this.transformType = transformType;
        this.configValue = configValue;

    }

    public Long transformId;

    public Long taskId;
    public Integer sourceColumnNum;
    public String sourceColumn;

    public String targetColumn;

    public String transformType;


    public String configValue;
    public Long getTransformId() {
        return transformId;
    }

    public void setTransformId(Long transformId) {
        this.transformId = transformId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getSourceColumn() {
        return sourceColumn;
    }

    public void setSourceColumn(String sourceColumn) {
        this.sourceColumn = sourceColumn;
    }

    public String getTargetColumn() {
        return targetColumn;
    }

    public void setTargetColumn(String targetColumn) {
        this.targetColumn = targetColumn;
    }

    public String getTransformType() {
        return transformType;
    }

    public void setTransformType(String transformType) {
        this.transformType = transformType;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Integer getSourceColumnNum() {
		return sourceColumnNum;
	}
    public void setSourceColumnNum(Integer sourceColumnNum) {
		this.sourceColumnNum = sourceColumnNum;
	}
}
