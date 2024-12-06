package com.hmh.excel2db.db.ftp;



import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.domain.ColInfo;

import java.util.List;

public class FtpDataSource implements DataSource {

    public FtpDataSource(String jsonString){


    }
    @Override
    public Boolean ping() throws Exception {
        return null;
    }

    @Override
    public Boolean connection() throws Exception {
        return null;
    }

    @Override
    public List<String> tableList() throws Exception {
        return null;
    }

    @Override
    public List<ColInfo> colList(String table) throws Exception {
        return null;
    }

    @Override
    public boolean insert(String sql) {
        return true;
    }

    @Override
    public List<String> colList_annotation(String table) throws Exception {
        return null;
    }


}
