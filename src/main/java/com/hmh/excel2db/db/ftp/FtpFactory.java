package com.hmh.excel2db.db.ftp;


import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.DataSourceFactory;
import com.hmh.excel2db.db.all.param.LinkParam;
import com.hmh.excel2db.db.ftp.param.FtpParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FtpFactory implements DataSourceFactory {
    @Override
    public String getName() {
        return "ftp";
    }

    @Override
    public DataSource createSource(String jsonString) {
        return new FtpDataSource(jsonString);
    }

    @Override
    public List<LinkParam> getLinkParams() {
        FtpParam param = new FtpParam();

        return param.linkParams();
    }


}
