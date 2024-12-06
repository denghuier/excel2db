package com.hmh.excel2db.db.all.param;

import java.util.ArrayList;
import java.util.List;

public class JdbcParam implements AllParam{

    /**
     * 端口
     */
    protected int port;
    /**
     * 地址
     */
    protected String host;
    /**
     * 数据库
     */
    protected String database;
    /**
     * 拼接后地址
     */
    protected String jdbcUrl;
    /**
     * 驱动名称
     */
    protected String driverClassName;

    /**
     * 用户名
     */
    protected String userName;
    /**
     * 密码
     */
    protected String password;
    /**
     * 编码
     */
    private String encoding;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<LinkParam> linkParams(){
        List<LinkParam> list=new ArrayList<LinkParam>();
        list.add(new LinkParam("host","数据库IP",1,0,null));
        list.add(new LinkParam("port","端口",1,0,null));
        list.add(new LinkParam("database","数据库",1,0,null));
        list.add(new LinkParam("userName","用户名",1,0,null));
        list.add(new LinkParam("password","密码",1,1,null));
//        list.add(new LinkParam("encoding","编码",1,0, Arrays.asList("utf8", "uft16")));
        return list;
    }
}
