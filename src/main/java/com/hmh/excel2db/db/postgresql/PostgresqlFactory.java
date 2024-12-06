package com.hmh.excel2db.db.postgresql;

import cn.hutool.json.JSONUtil;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.db.all.param.LinkParam;
import com.hmh.excel2db.db.postgresql.param.PostgresqlParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostgresqlFactory implements DataSourceFactory {

    @Override
    public String getName() {
        return "postgresql";
    }

    @Override
    public DataSource createSource(String jsonString) {
        return new PostgresqlDataSource(jsonString);
    }

    @Override
    public List<LinkParam> getLinkParams() {
        return new PostgresqlParam().linkParams();
    }


}
