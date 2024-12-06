package com.hmh.excel2db.db.postgresql.param;


import com.hmh.excel2db.db.all.param.JdbcParam;
import com.hmh.excel2db.db.all.param.LinkParam;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component

public class PostgresqlParam extends JdbcParam {
    @Override
    public List<LinkParam> linkParams() {
        List<LinkParam> list=super.linkParams();
        list.stream().filter(f->f.getColName().equals("port")).forEach(e -> {
            e.setDefaultValue(5432);
        });
        list.add(new LinkParam("encoding","编码",1,0, Arrays.asList("UTF-8", "uft16","gbk")));
        return list;
    }
}
