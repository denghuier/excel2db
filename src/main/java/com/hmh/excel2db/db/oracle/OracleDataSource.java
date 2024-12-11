package com.hmh.excel2db.db.oracle;

import cn.hutool.json.JSONUtil;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.db.all.utils.HikarUtil;
import com.hmh.excel2db.db.oracle.param.OracleParam;
import com.zaxxer.hikari.HikariDataSource;


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OracleDataSource implements DataSource {

    private OracleParam param;

    public OracleDataSource(String jsonString) {
        this.param = JSONUtil.toBean(jsonString, OracleParam.class);
        this.param.setJdbcUrl("jdbc:oracle:thin:@" + this.param.getHost() + ":" + this.param.getPort() + ":" + this.param.getDatabase());
        this.param.setDriverClassName("oracle.jdbc.driver.OracleDriver");

    }

    @Override
    public Boolean ping() throws Exception {
        return null;
    }

    @Override
    public Boolean connection() throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        return hikarUtil.connection(param);
    }

    @Override
    public List<String> tableList() throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource = hikarUtil.dataSource(param);
        List<String> list = hikarUtil.tables(hikariDataSource);
        return list;
    }

    @Override
    public List<ColInfo> colList(String table) throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource = hikarUtil.dataSource(param);
        return hikarUtil.colList(hikariDataSource, table);
    }

    @Override
    public boolean insert(String sql) {

        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource = hikarUtil.dataSource(param);
        hikarUtil.insertData(hikariDataSource,sql);
        return true;
    }


    @Override
    public List<String> colList_annotation(String table) throws Exception {
        List<String> list = new ArrayList<String>();
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource = hikarUtil.dataSource(param);
        DatabaseMetaData metaData = hikariDataSource.getConnection().getMetaData();
        ResultSet columns = metaData.getColumns(null, null, table, null);
        while (columns.next()) {
            // 字段名
            String columnName = columns.getString("COLUMN_NAME");
            // 字段类型
            String columnType = columns.getString("TYPE_NAME");
            // 字段备注信息
            String columnRemarks = columns.getString("REMARKS");
            list.add(columnRemarks);
        }
        return list;
    }


}


