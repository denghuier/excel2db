package com.hmh.excel2db.db.all;




import com.hmh.excel2db.db.all.domain.ColInfo;

import java.util.List;

public interface DataSource {

    /**
     * ping 网络是否联通
     *
     */
    Boolean ping()throws Exception;

    /**
     * 测试连通性
     */
    Boolean connection() throws Exception;

    /**
     * 返回表信息
     */
    List<String> tableList() throws Exception;

    /**
     * 返回字段
     */
    List<ColInfo> colList(String table) throws Exception;

    /**
     *
     */
    boolean insert(String sql);


    /**
     * 字段名称、字段类型、字段备注
     */
    @Deprecated
    List<String> colList_annotation(String table) throws Exception;
}
