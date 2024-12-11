package com.hmh.excel2db.db.mysql;


import cn.hutool.json.JSONUtil;
import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.domain.ColInfo;
import com.hmh.excel2db.db.all.utils.HikarUtil;
import com.hmh.excel2db.db.mysql.param.MysqlParam;
import com.zaxxer.hikari.HikariDataSource;


import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDataSource implements DataSource {

    /**
     * 解析获取mysql对应参数
     */
    private MysqlParam param;

    public MysqlDataSource(String jsonString){
        //解析 jsonString，并赋予MysqlParam
        this.param=JSONUtil.toBean(jsonString, MysqlParam.class);
        this.param.setJdbcUrl("jdbc:mysql://"+this.param.getHost()
                +":"+this.param.getPort()+"/"+this.param.getDatabase()
                +"?useUnicode=true&characterEncoding=UTF-8");
        this.param.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
    @Override
    public Boolean ping() throws IOException {
        return InetAddress.getByName(param.getHost()).isReachable(3000);
    }

    @Override
    public Boolean connection() throws SQLException {
        HikarUtil hikarUtil = new HikarUtil();
        return hikarUtil.connection(param);
    }

    @Override
    public List<String> tableList() throws SQLException {
        List<String> list=new ArrayList<String>();
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
        ResultSet rs =hikariDataSource.getConnection().createStatement().executeQuery("show TABLES");
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        hikariDataSource.getConnection().close();
        return list;
    }

    @Override
    public List<ColInfo> colList(String table) throws Exception {
        HikarUtil hikarUtil = new HikarUtil();
        HikariDataSource hikariDataSource=hikarUtil.dataSource(param);
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
