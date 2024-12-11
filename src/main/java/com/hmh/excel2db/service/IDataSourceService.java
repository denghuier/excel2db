package com.hmh.excel2db.service;

import com.hmh.excel2db.common.core.domain.AjaxResult;
import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.domain.DataSourceDO;

import java.util.List;

public interface IDataSourceService {

    public List<DataSourceDO> list(DataSourceDO dataSource);

    public int create(DataSourceDO dataSource);

    public int update(DataSourceDO dataSource);

    public List<String> getTableList(Long id) throws Exception;


    public List<ColInfo> getColList(Long id, String tableName) throws Exception;

    public AjaxResult connect(DataSourceDO ds) throws Exception;

}
