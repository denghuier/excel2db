package com.hmh.excel2db.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmh.excel2db.common.core.domain.AjaxResult;
import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.db.all.serviceLocator.DBServiceLocator;
import com.hmh.excel2db.domain.DataSourceDO;
import com.hmh.excel2db.mapper.DataSourceMapper;
import com.hmh.excel2db.service.IDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataSourceServiceImpl implements IDataSourceService {
    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public List<DataSourceDO> list(DataSourceDO dataSource) {
        return dataSourceMapper.list(dataSource);
    }

    @Override
    public int create(DataSourceDO dataSource) {
        return dataSourceMapper.insert(dataSource);
    }

    @Override
    public int update(DataSourceDO dataSource) {
        return dataSourceMapper.update(dataSource);
    }

    @Override
    public List<String> getTableList(Long id) throws Exception {
        DataSourceDO dataSource = dataSourceMapper.selectById(id);
        JSONObject json = JSONUtil.parseObj(dataSource, false);
        String str= json.toStringPretty();
        return DBServiceLocator.map.get(dataSource.getDbType().toLowerCase()).createSource(str).tableList();
    }

    @Override
    public List<ColInfo> getColList(Long id, String tableName) throws Exception {
        DataSourceDO dataSource = dataSourceMapper.selectById(id);
        JSONObject json = JSONUtil.parseObj(dataSource, false);
        String str= json.toStringPretty();
        return DBServiceLocator.map.get(dataSource.getDbType().toLowerCase()).createSource(str).colList(tableName);
    }

    @Override
    public AjaxResult connect(DataSourceDO ds) throws Exception {


        JSONObject json = JSONUtil.parseObj(ds, false);
        String str= json.toStringPretty();
        Boolean b =DBServiceLocator.map.get(ds.getDbType().toLowerCase()).createSource(str).connection();
        return b?AjaxResult.success("成功链接"):AjaxResult.error("链接失败");
    }
}
