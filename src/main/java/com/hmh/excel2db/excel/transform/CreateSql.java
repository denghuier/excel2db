package com.hmh.excel2db.excel.transform;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.domain.TaskTransformConfig;
import com.hmh.excel2db.transform.all.TransformDo;
import com.hmh.excel2db.transform.all.TransformFactory;
import com.hmh.excel2db.transform.all.springLocator.TransformServiceLocator;


import java.util.List;
import java.util.Map;

public class CreateSql {

    public void insertData(DataSource dataSource, String sql){
        dataSource.insert(sql);
    }

    public String getSql(String tableName, List<Map<Integer, String>> data, List<TaskTransformConfig> list) {
        TransformServiceLocator transformServiceLocator=new TransformServiceLocator();
        StringBuilder colSql=new StringBuilder();
        StringBuilder valueSql=new StringBuilder();
        Map<String, TransformFactory> factoryMap=transformServiceLocator.getMap();
        for(int i=0;i<data.size();i++){
            Map<Integer, String> map=data.get(i);
            for(int j=0;j<list.size();j++){
                TaskTransformConfig taskTransformConfig =list.get(j);
                //源端列名
                String sourceColumn= taskTransformConfig.getSourceColumn();
                //源端列号
                int sourceColumnNum= taskTransformConfig.getSourceColumnNum();
                //目标列名
                String targetColumn= taskTransformConfig.getTargetColumn();
                //转换类型
                String transformType= taskTransformConfig.getTransformType();
                //转换参数
                String configValue= taskTransformConfig.getConfigValue();
                //源端值
                String sourceValue=map.get(sourceColumnNum);
                if(i==0){
                    colSql.append(targetColumn).append(",");
                }
                String value=factoryMap.get(transformType).createTransform(new TransformDo(sourceValue,configValue)).transform();
                if (j==0){
                    valueSql.append("('").append(value).append("',");
                }else if (j==list.size()-1){
                    valueSql.append("'").append(value).append("'),");
                }else {
                    valueSql.append("'").append(value).append("',");
                }
            }
        }
        return "insert into "+tableName+"("+ colSql.substring(0,colSql.length()-1)
                +") VALUES "+
                valueSql.substring(0,valueSql.length()-1)+";";
    }
}
