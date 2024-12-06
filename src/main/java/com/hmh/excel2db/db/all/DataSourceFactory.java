package com.hmh.excel2db.db.all;




import com.hmh.excel2db.db.all.param.LinkParam;

import java.util.List;

public interface DataSourceFactory {
    /**
     * *
     */
    String getName();

    /**
     * *
     */
    public DataSource createSource(String jsonString);

    /**
     * * 获取链接参数
     */
    public List<LinkParam> getLinkParams();







}
