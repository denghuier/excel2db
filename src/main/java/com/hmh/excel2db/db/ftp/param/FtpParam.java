package com.hmh.excel2db.db.ftp.param;


import com.hmh.excel2db.db.all.param.LinkParam;


import java.util.ArrayList;
import java.util.List;

/**
 * FTP 连接参数
 */

public class FtpParam {

    private String host;

    private String port;

    private String userName;

    private String password;

    private String dirPath;

    private String  subPath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
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

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public List<LinkParam> linkParams() {
        List<LinkParam> list=new ArrayList<LinkParam>();
        list.add(new LinkParam("host","链接地址",0,0,null));
        list.add(new LinkParam("port","端口号",0,0,null));
        list.add(new LinkParam("userName","用户名",1,0,null));
        list.add(new LinkParam("password","密码",0,1,null));
        list.add(new LinkParam("dirPath","目录路径",1,0,null));
        list.add(new LinkParam("subPath","订阅路径",1,0,null));
        return list;
    }
}
