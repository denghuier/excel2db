package com.hmh.excel2db.controller;


import com.hmh.excel2db.common.core.controller.BaseController;
import com.hmh.excel2db.common.core.domain.AjaxResult;
import com.hmh.excel2db.common.core.page.TableDataInfo;
import com.hmh.excel2db.db.all.enums.DataSourceTypeEnum;
import com.hmh.excel2db.domain.DataSourceDO;
import com.hmh.excel2db.service.IDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/datasource")
public class DataSourceController extends BaseController {

    @Autowired
    IDataSourceService dataSourceService;

    @GetMapping("/all")
    public AjaxResult all(){
        List<DataSourceDO> list =dataSourceService.list(null);
        return AjaxResult.success(list);
    }

    @GetMapping("/list")
    public TableDataInfo list(DataSourceDO dataSource){
        startPage();
        List<DataSourceDO> list = dataSourceService.list(dataSource);
        return getDataTable(list);
    }
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DataSourceDO ds){
        return toAjax(dataSourceService.create(ds));
    }

    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody DataSourceDO ds){
        return toAjax(dataSourceService.update(ds));
    }

    @GetMapping("/type/list")
    public AjaxResult typeList(){
        return AjaxResult.success(DataSourceTypeEnum.values());
    }

    @GetMapping("tableList/{dsId}")
    public AjaxResult tableList(@PathVariable Long dsId) throws Exception {
        return AjaxResult.success(dataSourceService.getTableList(dsId));
    }

    /**
     * 获取表结构
     * @param dsId
     * @param tableName
     * @return
     * @throws Exception
     */
    @GetMapping("colList")
    public AjaxResult colList( Long dsId, String tableName) throws Exception {
        return AjaxResult.success(dataSourceService.getColList(dsId, tableName));
    }

    /**
     * 测试连通性
     * @param ds
     * @return
     * @throws Exception
     */
    @PostMapping("connect")
    public AjaxResult connect(@RequestBody DataSourceDO ds) throws Exception{
        return dataSourceService.connect(ds);
    }


}
