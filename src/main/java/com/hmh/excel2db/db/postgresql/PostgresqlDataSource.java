package com.hmh.excel2db.db.postgresql;

import cn.hutool.json.JSONUtil;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.db.all.utils.HikarUtil;
import com.hmh.excel2db.db.postgresql.param.PostgresqlParam;
import com.zaxxer.hikari.HikariDataSource;



import java.util.List;

public class PostgresqlDataSource implements DataSource {


    private PostgresqlParam param;

    public PostgresqlDataSource(String jsonString){
        this.param= JSONUtil.toBean(jsonString, PostgresqlParam.class);
        this.param.setJdbcUrl("jdbc:postgresql://"+this.param.getHost()+":"+this.param.getPort()+"/"+this.param.getDatabase());
        this.param.setDriverClassName("org.postgresql.Driver");
    }
    @Override
    public Boolean ping() throws Exception {
        return null;
    }

    @Override
    public Boolean connection() throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
        boolean b=hikariDataSource.getConnection().createStatement().execute("select 1");
        hikariDataSource.close();
        return b;
    }

    @Override
    public List<String> tableList() throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
        List<String> list =hikarUtil.tables(hikariDataSource);
        return list;
    }

    @Override
    public List<ColInfo> colList(String table) throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
        List<ColInfo> list =hikarUtil.colList(hikariDataSource, table);
        return list;
    }

    @Override
    public boolean insert(String sql) {

        return true;
    }


    @Override
    public List<String> colList_annotation(String table) throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
        List<String> list =hikarUtil.colList_annotation(hikariDataSource, table);
        return list;
    }


}
