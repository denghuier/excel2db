package com.hmh.excel2db.excel.read;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.domain.TaskTransformConfig;

import java.util.List;


public class ListenerDo {

    public ListenerDo(){}
    public ListenerDo(readType readType){
        this.readType = readType;
    }
    private Long taskId;
    // 读取类型
    public readType readType;

    // 读取数据开始行号
    private int headRowNumber;
    // 读取数据最后行号
    private int endRowNumber;
    // 数据源
    private DataSource dataSource;
    // 表名
    private String tableName;

    List<TaskTransformConfig> taskTransformConfigs;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public int getEndRowNumber() {
        return endRowNumber;
    }

    public void setEndRowNumber(int endRowNumber) {
        this.endRowNumber = endRowNumber;
    }

    public readType getReadType() {
        return readType;
    }

    public void setReadType(readType readType) {
        this.readType = readType;
    }

    public int getHeadRowNumber() {
        return headRowNumber;
    }

    public void setHeadRowNumber(int headRowNumber) {
        this.headRowNumber = headRowNumber;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<TaskTransformConfig> getTransformConfigs() {
        return taskTransformConfigs;
    }
    public void setTransformConfigs(List<TaskTransformConfig> taskTransformConfigs) {
        this.taskTransformConfigs = taskTransformConfigs;
    }

}



//读取类型
 enum readType{
    //读取表头
    HEAD,
    //读取数据
    DATA
}
