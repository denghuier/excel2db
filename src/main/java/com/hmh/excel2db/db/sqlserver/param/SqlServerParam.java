package com.hmh.excel2db.db.sqlserver.param;


import com.hmh.excel2db.db.all.param.JdbcParam;
import com.hmh.excel2db.db.all.param.LinkParam;


import java.util.Arrays;
import java.util.List;


public class SqlServerParam extends JdbcParam {
    @Override
    public List<LinkParam> linkParams() {
        List<LinkParam> list=super.linkParams();
        list.stream().filter(f->f.getColName().equals("port")).forEach(e -> {
            e.setDefaultValue(1433);
        });
        list.add(new LinkParam("encoding","编码",1,0, Arrays.asList("utf8", "uft16","gbk")));
        return list;
    }
}
