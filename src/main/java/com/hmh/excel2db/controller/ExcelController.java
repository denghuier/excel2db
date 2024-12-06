package com.hmh.excel2db.controller;


import com.hmh.excel2db.common.config.Excel2DBConfig;
import com.hmh.excel2db.common.core.domain.AjaxResult;
import com.hmh.excel2db.common.utils.file.FileUploadUtils;
import com.hmh.excel2db.common.utils.file.FileUtils;
import com.hmh.excel2db.common.utils.file.MimeTypeUtils;
import com.hmh.excel2db.domain.ExcelHeaderCol;
import com.hmh.excel2db.excel.read.ExcelRead;
import com.hmh.excel2db.framework.config.ServerConfig;
import com.hmh.excel2db.transform.all.enums.TransformTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/excel")
public class ExcelController {


    @PostMapping("/video")
    public AjaxResult upVideo(MultipartFile file) throws Exception{
        try
        {
            // 上传文件路径
            String filePath = Excel2DBConfig.getUploadPath();
            // 上传并返回新文件名称
            Map map = FileUploadUtils.upload(filePath, file);

            return AjaxResult.success(map);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
    @PostMapping("/upExcel")
    public AjaxResult upExcel(MultipartFile file) throws Exception{
        try
        {
            ExcelRead excelRead = new ExcelRead();
            byte [] byteArr = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            List<String> sheetNames =excelRead.getSheetName(inputStream);
            // 上传文件路径
            String filePath = Excel2DBConfig.getUploadPath();
            // 上传并返回新文件名称
            Map map = FileUploadUtils.upload(filePath, file);
            map.put("sheetNames",sheetNames);

            return AjaxResult.success(map);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
    @PostMapping("/getTotalRowNum")
    public AjaxResult getTotalRowNum(@RequestParam("pathName") String pathName,
                                     @RequestParam("sheetName") String sheetName) throws Exception{
        ExcelRead excelRead = new ExcelRead();
        int totalRowNum = excelRead.getTotalRowNum(pathName,sheetName);
        return AjaxResult.success(totalRowNum);
    }


    @PostMapping("/getHeaderMap")
    public AjaxResult getHeaderMap(@RequestParam("pathName") String pathName,
                                  @RequestParam("sheetName") String sheetName,
                                  @RequestParam("headRowNumber") int headRowNumber) throws Exception{
        ExcelRead excelRead = new ExcelRead();
        Map<Integer,String> headerMap = excelRead.getHeaderMap(pathName,sheetName,headRowNumber);
        List<ExcelHeaderCol> list = new ArrayList<>();
        ExcelHeaderCol headerCol=null;
        for (Integer key: headerMap.keySet()){
            headerCol= new ExcelHeaderCol();
            headerCol.setColNum(key);
            headerCol.setColName(headerMap.get(key));
            headerCol.setTransformType(TransformTypeEnum.NORMAL.getCode());

            list.add(headerCol);
        }
        return AjaxResult.success(list);
    }

     @GetMapping("/transform/type")
    public AjaxResult transformType(){
        return AjaxResult.success(
                TransformTypeEnum.getEnumList()
        );
    }
}
