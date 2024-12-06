package com.hmh.excel2db.excel.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelRead {

    public void read(String pathName) {
        ListenerDo listenerDo = new ListenerDo(readType.DATA);
        EasyExcel.read(pathName, new NoModleDataListener(listenerDo)).sheet().doRead();
    }

    public void read(String pathName, String sheetName) {
        ListenerDo listenerDo = new ListenerDo(readType.HEAD);
        EasyExcel.read(pathName, new NoModleDataListener(listenerDo)).sheet(sheetName).doRead();
    }

    public List<Map<Integer, String>> read(String pathName,String sheetName,int headRowNumber,int endRowNumber){
        ListenerDo listenerDo = new ListenerDo(readType.DATA);
        listenerDo.setEndRowNumber(endRowNumber);
        NoModleDataListener listener = new NoModleDataListener(listenerDo);
        EasyExcel.read(pathName, listener).headRowNumber(headRowNumber).sheet(sheetName).doRead();
        return listener.getData();
    }

    public List<Map<Integer, String>> read(String pathName,String sheetName,int headRowNumber,ListenerDo listenerDo){
        listenerDo.setReadType(readType.DATA);
        NoModleDataListener listener = new NoModleDataListener(listenerDo);
        EasyExcel.read(pathName, listener).headRowNumber(headRowNumber).sheet(sheetName).doRead();
        return listener.getData();
    }



    /**
     * 获取所有sheet名称
     * @param pathName
     * @return
     */
    public List<String> getSheetName(String pathName) {
        List<String> sheetNameList = new ArrayList<>();
        List<ReadSheet> sheets = EasyExcel.read(pathName).build().excelExecutor().sheetList();
        for (ReadSheet sheet : sheets){
            sheetNameList.add(sheet.getSheetName());
        }
        return sheetNameList;
    }

    public List<String> getSheetName(InputStream inputStream){
        List<String> sheetNameList = new ArrayList<>();
        List<ReadSheet> sheets = EasyExcel.read(inputStream).build().excelExecutor().sheetList();
        for (ReadSheet sheet : sheets){
            sheetNameList.add(sheet.getSheetName());
        }
        return sheetNameList;
    }


    /**
     * 获取总行数
     * @param pathName
     * @param sheetName
     * @return
     */
    public int getTotalRowNum(String pathName,String sheetName){
        return EasyExcel.read(pathName).sheet(sheetName).doReadSync().size();
    }

    public int getTotalRowNum(InputStream inputStream,String sheetName){
        return EasyExcel.read(inputStream).sheet(sheetName).doReadSync().size();
    }

    /**
     * *获取表头
     * @param pathName
     * @param sheetName
     * @return
     */
    public Map<Integer,String> getHeaderMap(String pathName,String sheetName,int headRowNumber){
        ListenerDo listenerDo = new ListenerDo(readType.HEAD);
        listenerDo.setHeadRowNumber(headRowNumber);
        NoModleDataListener listener = new NoModleDataListener(listenerDo);
        EasyExcel.read(pathName, listener).headRowNumber(headRowNumber).sheet(sheetName).doRead();
        return listener.getHeaderMap();
    }

    public Map<Integer,String> getHeaderMap(InputStream inputStream,String sheetName,int headRowNumber){
        ListenerDo listenerDo = new ListenerDo(readType.HEAD);
        listenerDo.setHeadRowNumber(headRowNumber);
        NoModleDataListener listener = new NoModleDataListener(listenerDo);
        EasyExcel.read(inputStream, listener).headRowNumber(headRowNumber).sheet(sheetName).doRead();
        return listener.getHeaderMap();
    }



}
