package com.hmh.excel2db.db.sqlserver;

import cn.hutool.json.JSONUtil;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.db.all.param.LinkParam;
import com.hmh.excel2db.db.sqlserver.param.SqlServerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SqlServerFactory implements DataSourceFactory {

    @Override
    public String getName() {
        return "sqlserver";
    }

    @Override
    public DataSource createSource(String jsonString) {
        return new SqlServerDataSource(jsonString);
    }

    @Override
    public List<LinkParam> getLinkParams() {
        return new SqlServerParam().linkParams();
    }


}
