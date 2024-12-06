package com.hmh.excel2db;

import com.hmh.excel2db.excel.read.ExcelRead;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class ExcelReadTest {

    @Test
    public void  readExcel()
    {

        String fileName = this.getClass().getClassLoader().getResource("供应商梳理.xlsx").getPath(); //获取文件路径

        String path="C:\\Users\\hanmi\\Desktop\\20240624\\美团管家外卖数据6.23\\美团管家全平台外卖菜品成本毛利率（6月23日） (1).xlsx";
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
            System.out.println(fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ExcelRead excelRead = new ExcelRead();
//        excelRead.getSheetName(path);
        excelRead.read(path);
//        int a=excelRead.getTotalRowNum(path,"");
//        System.out.println(a);
    }

    @Test
    public void getSheetName()
    {
//        String fileName = this.getClass().getClassLoader().getResource("供应商梳理.xlsx").getPath(); //获取文件路径

        String path="C:\\Users\\hanmi\\Desktop\\test\\7月11日抖音外卖核销(1).xlsx";
        ExcelRead excelRead = new ExcelRead();
        List<String> sheetNames =excelRead.getSheetName(path);
        System.out.println(sheetNames);
    }

    @Test
    public void getHeaderMap()
    {
//        String fileName = this.getClass().getClassLoader().getResource("供应商梳理.xlsx").getPath(); //获取文件路径

        String path="C:\\Users\\hanmi\\Desktop\\20240624\\美团管家外卖数据6.23\\美团管家全平台外卖菜品成本毛利率（6月23日） (1).xlsx";
        ExcelRead excelRead = new ExcelRead();
        Map<Integer,String> map=excelRead.getHeaderMap(path,"平台外卖订单明细",3);
        System.out.println(map);
    }

    @Test
    public void getData(){
//        String fileName = this.getClass().getClassLoader().getResource("供应商梳理.xlsx").getPath();
        String path="C:\\Users\\hanmi\\Desktop\\20240624\\美团管家外卖数据6.23\\美团管家全平台外卖菜品成本毛利率（6月23日） (1).xlsx";
        ExcelRead excelRead = new ExcelRead();
        excelRead.read(path,"平台外卖订单明细",3,4);
    }

    @Test
    public void getTotalNum(){
//        String fileName = this.getClass().getClassLoader().getResource("供应商梳理.xlsx").getPath();
        String path="C:\\Users\\hanmi\\Desktop\\20240624\\美团管家外卖数据6.23\\美团管家全平台外卖菜品成本毛利率（6月23日） (1).xlsx";
        ExcelRead excelRead = new ExcelRead();
        int a=excelRead.getTotalRowNum(path,"平台外卖订单明细");
        System.out.println(a);
    }
}
