package com.hmh.excel2db.db.mysql.param;


import com.hmh.excel2db.db.all.param.JdbcParam;
import com.hmh.excel2db.db.all.param.LinkParam;


import java.util.Arrays;
import java.util.List;

/**
 * mysql 连接参数
 * @author hmh
 * @date 2023-11-6
 */


public class MysqlParam extends JdbcParam {


    @Override
    public List<LinkParam> linkParams() {
        List<LinkParam> list=super.linkParams();
        list.stream().filter(f->f.getColName().equals("port")).forEach(e -> {
            e.setDefaultValue(3306);
        });
        list.add(new LinkParam("encoding","编码",1,0, Arrays.asList("UTF-8", "UTF-16","gbk")));
    return list;
    }
}
