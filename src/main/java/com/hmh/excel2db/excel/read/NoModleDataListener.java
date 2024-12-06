package com.hmh.excel2db.excel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.util.ConverterUtils;
import com.alibaba.fastjson2.JSON;
import com.hmh.excel2db.excel.transform.CreateSql;
import com.hmh.excel2db.framework.socket.NoticeWebsocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoModleDataListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoModleDataListener.class);


    /**
     * 用于存储表头信息
     */
    private Map<Integer, String> headerMap;
    /**
     *  用于记录当前行号
     */
    private int lineNumber;

    private ListenerDo  listenerDo;

    public NoModleDataListener() {
    }

    public NoModleDataListener(ListenerDo listenerDo){
        this.listenerDo=listenerDo;
    }
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 4;
    List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
//        Integer rowNumber = context.readSheetHolder().getApproximateTotalRowNumber();
//        LOGGER.info("总行数：{}", rowNumber);
//        Object columnAValue = data.get(0); // A列的值
//        Object columnCValue = data.get(2); // C列的值
//
//        System.out.println("Column A Value: " + columnAValue);
//        System.out.println("Column C Value: " + columnCValue);
        lineNumber++;
        LOGGER.info("行号:{},数据:{}",lineNumber, JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
//        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
        // 如果想转成成 Map<Integer,String>
        // 方案1： 不要implements ReadListener 而是 extends AnalysisEventListener
        // 方案2： 调用 ConverterUtils.convertToStringMap(headMap, context) 自动会转换
        Map<Integer,String> headName=ConverterUtils.convertToStringMap(headMap, context);
        this.headerMap=headName;
        LOGGER.info("解析到一条头数据:{}", JSON.toJSONString(headName));
//        System.out.println("表头位于第 " + (listenerDo.getHeadRowNumber() + 1) + " 行");
        lineNumber++;
    }

    @Override
    public boolean  hasNext(AnalysisContext context) {
//        int num= context.getCurrentRowNum();
        if (listenerDo.getReadType().equals(readType.HEAD)){
            return lineNumber != listenerDo.getHeadRowNumber();
        }else if (listenerDo.readType.equals(readType.DATA)){

            if (lineNumber == listenerDo.getEndRowNumber()&& !list.isEmpty()){
                doAfterAllAnalysed(context);
            }
            return lineNumber != listenerDo.getEndRowNumber();
        }
        return true;
    }
    // Getter和Setter方法，以便在外部获取headerMap
    public Map<Integer, String> getHeaderMap() {
        return headerMap;
    }


    // 获取数据, 已弃用
    @Deprecated
    public List<Map<Integer, String>> getData(){
        return list;
    }
    /**
     * 加上存储数据库
     */
    private void saveData() {
        CreateSql createSql=new CreateSql();
        String sql=createSql.getSql(listenerDo.getTableName(), list,listenerDo.taskTransformConfigs);
        createSql.insertData(listenerDo.getDataSource(),sql);
        // 通知前端
        NoticeWebsocket.sendMessageByTaskId(String.valueOf(listenerDo.getTaskId()),String.valueOf(lineNumber));
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        LOGGER.info("存储数据库成功！");
    }
}
