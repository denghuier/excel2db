package com.hmh.excel2db.db.all.utils;

import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.db.all.param.JdbcParam;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HikarUtil {

    public HikariDataSource dataSource(JdbcParam baseParams) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("HikariCP 连接池");
        hikariConfig.setDriverClassName(baseParams.getDriverClassName());

        hikariConfig.setJdbcUrl(baseParams.getJdbcUrl());
        hikariConfig.setUsername(baseParams.getUserName());
        hikariConfig.setPassword(baseParams.getPassword());
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }

    public void insertData(HikariDataSource ds,String sql){

        try (Connection conn = ds.getConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ds.close(); // 关闭数据源
        }

    }

    public List<String>  tables(HikariDataSource hikariDataSource) throws SQLException {
        List<String> list = new ArrayList<String>();
        DatabaseMetaData metaData = hikariDataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            // 表名
            String tableName = tables.getString("TABLE_NAME");
            // 表类型
            String tableType = tables.getString("TABLE_TYPE");
            // 备注信息
            String remarks = tables.getString("REMARKS");
            list.add(tableName);
        }
        return list;
    }

    public List<ColInfo> colList(HikariDataSource hikariDataSource, String tableName) throws SQLException {
        List<ColInfo> list = new ArrayList<>();
        DatabaseMetaData metaData = hikariDataSource.getConnection().getMetaData();
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        ColInfo colInfo=null;
        while (columns.next()) {
            colInfo=new ColInfo();
            // 字段名
            String columnName = columns.getString("COLUMN_NAME");
            // 字段类型
            String columnType = columns.getString("TYPE_NAME");
            // 字段备注信息
            String columnRemarks = columns.getString("REMARKS");
            colInfo.setColumnName(columnName);
            colInfo.setColumnRemarks(columnRemarks);
            colInfo.setColumnType(columnType);
            list.add(colInfo);
        }
        return list;
    }

    public List<String> colList_annotation(HikariDataSource hikariDataSource, String table) throws SQLException {
        List<String> list = new ArrayList<String>();
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
