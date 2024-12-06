package com.hmh.excel2db.db.oracle;

import cn.hutool.json.JSONUtil;

import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.db.all.param.LinkParam;
import com.hmh.excel2db.db.oracle.param.OracleParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OracleFactory implements DataSourceFactory {

    @Override
    public String getName() {
        return "oracle";
    }

    @Override
    public DataSource createSource(String jsonString) {
        return null;
    }

    @Override
    public List<LinkParam> getLinkParams() {
        return new OracleParam().linkParams();
    }


}
