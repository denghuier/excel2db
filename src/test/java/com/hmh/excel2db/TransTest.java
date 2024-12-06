package com.hmh.excel2db;

import com.hmh.excel2db.excel.read.ExcelRead;
import com.hmh.excel2db.excel.read.ListenerDo;
import com.hmh.excel2db.domain.TaskTransformConfig;
import com.hmh.excel2db.transform.all.TransformDo;
import com.hmh.excel2db.transform.all.TransformFactory;

import com.hmh.excel2db.transform.all.springLocator.TransformServiceLocator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Excel2DBApplication.class)
public class TransTest {
    @Autowired
    TransformServiceLocator transformServiceLocator;
    public static void main(String[] args)
    {
//        dy();
    }

    @Test
    public  void  dy(){
        List<TaskTransformConfig>  list= new ArrayList<>();
        list.add(new TaskTransformConfig("","store_name","ADDSTRING","hmh"));
        list.add(new TaskTransformConfig("","business_date","ADDSTRING","hmh"));
        list.add(new TaskTransformConfig("订单送达时间","order_delivery_time","NORMAL",""));
        list.add(new TaskTransformConfig("订单编号","order_number","NORMAL",""));
        list.add(new TaskTransformConfig("订单类型","order_type","NORMAL",""));

        String path="C:\\Users\\hanmi\\Desktop\\test\\7月11日抖音外卖核销(1).xlsx";
        ExcelRead excelRead = new ExcelRead();
        //获取表头
        Map<Integer,String> headerMap=
                excelRead.getHeaderMap(path,"分账明细-正向-外卖",1);

        //表头的key、value转换
        Map<String, Integer> newHeaderMap=mergeMap(headerMap);
        System.out.println("表头");
        System.out.println(newHeaderMap);


        //获取总行数
        int totalRowNum= excelRead.getTotalRowNum(path,"分账明细-正向-外卖");
        ListenerDo listenerDo=new ListenerDo();
        listenerDo.setEndRowNumber(totalRowNum);
        listenerDo.setHeadRowNumber(1);
        //获取excel数据
        List<Map<Integer, String>> data=
                excelRead.read(path,"分账明细-正向-外卖",1,listenerDo);
        System.out.println("遍历获取得excel数据");
        /**
         * sql插入
         * INSERT INTO
         * [表名]([列名],[列名])
         *  VALUES
         * ([列值],[列值])),
         * ([列值],[列值])),
         * ([列值],[列值]));*
         */
        StringBuffer colSql=new StringBuffer();
        StringBuffer valueSql=new StringBuffer();
        Map<String, TransformFactory> factoryMap=transformServiceLocator.getMap();
        for(int i=0;i<data.size();i++){
            Map<Integer, String> rowData=data.get(i);

            //todo：将插入数据生成sql语句
            for(int j=0;j<list.size();j++){
                //源端列名
                String sourceColumn=list.get(j).getSourceColumn();
                //源端序号
                Integer sourceNum=newHeaderMap.get(sourceColumn);
                //源端值
                String sourceValue=rowData.get(sourceNum);
                //目标端列名
                String targetColumn=list.get(j).getTargetColumn();
                if(i==0){
                    colSql.append(targetColumn).append(",");
                }

                //传输类型
                String transformType=list.get(j).getTransformType();
                String value=factoryMap.get(transformType).createTransform(new TransformDo(sourceValue,list.get(j).getConfigValue())).transform();
//                System.out.println(value);
                if (j==0){
                    valueSql.append("('").append(value).append("',");
                }else if (j==list.size()-1){
                    valueSql.append("'").append(value).append("'),");
                }else {
                    valueSql.append("'").append(value).append("',");
                }
            }
        }
        System.out.println(
                "INSERT INTO [表名]("+
                        colSql.substring(0,colSql.length()-1)
                        +") VALUES "+
                        valueSql.substring(0,valueSql.length()-1)+";");

        //遍历传输配置
//        for (int i=0;i<list.size();i++){
//            //todo： 将插入数据生成sql语句
//            Integer sourceNum=newHeaderMap.get(list.get(i).getSourceColumn());
//            System.out.println(list.get(i).getTransformType());
//            for(int j=0;j<data.size();j++){
//                Map<Integer, String> rowData=data.get(j);
//                System.out.println(rowData.get(sourceNum));
//            }
//        }

    }

//    public void chooseData(Map<Integer, String> rowData, List<TransformDo> list, Map<String, Integer> newHeaderMap){
//        for (TransformDo transformDo:list){
//            System.out.println(transformDo.getTransformType());
//            if(rowData.get(newHeaderMap.get(transformDo.getSourceColumn()))!=null){
//                System.out.println(
//                        rowData.get(newHeaderMap.get(transformDo.getSourceColumn())));
//            }
//        }
//    }
    /**
     * *通过value获取key
     * @param map
     * @param value
     * @return
     */
    public static List<String> getKeysByValue(Map<String, String> map, String value) {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    /**
     * *map 键值对置换
     * @param oldMap
     * @return
     */
    public static Map<String, Integer> mergeMap(Map<Integer, String> oldMap){

        Map<String, Integer> newMap=new HashMap<>();
        // 遍历原始Map的entrySet，将键值对插入到新Map中
        for (Map.Entry<Integer, String> entry : oldMap.entrySet()) {
            newMap.put(entry.getValue(), entry.getKey());
        }
        return newMap;
    }
}
