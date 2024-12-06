package com.hmh.excel2db.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hmh.excel2db.common.core.domain.BaseEntity;


public class DataSourceDO extends BaseEntity {
    @JsonProperty("id")
    private Long id;

    /** 数据源名称 */
    @JsonProperty("dsName")
    private String dsName;

    @JsonProperty("dbType")
    private String dbType;

    /** 数据库主机 */
    @JsonProperty("host")
    private String host ;

    /** 端口号 */
    @JsonProperty("port")
    private int port;

    /** 用户名 */
    @JsonProperty("userName")
    private String userName ;

    /** 密码 */
    @JsonProperty("password")
    private String password;
    /**
     * 数据库
     */
    @JsonProperty("database")
    private String database;
    /**
     * 连接参数
     */
    @JsonProperty("linkParams")
    private String linkParams;

    /**
     *编码*
     */
    @JsonProperty("encoding")
    private int encoding;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getLinkParams() {
        return linkParams;
    }

    public void setLinkParams(String linkParams) {
        this.linkParams = linkParams;
    }

    public int getEncoding() {
        return encoding;
    }

    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }
}
