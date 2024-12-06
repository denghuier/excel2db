package com.hmh.excel2db.db.mysql;


import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.db.all.param.LinkParam;
import com.hmh.excel2db.db.mysql.param.MysqlParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MysqlFactory implements DataSourceFactory {

    @Override
    public String getName() {
        return "mysql";
    }

    @Override
    public DataSource createSource(String value) {
        return new MysqlDataSource(value);
    }

    @Override
    public List<LinkParam> getLinkParams() {
        return new MysqlParam().linkParams();
    }

}
