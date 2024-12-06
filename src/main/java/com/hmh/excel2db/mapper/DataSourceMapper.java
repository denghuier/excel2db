package com.hmh.excel2db.mapper;

import com.hmh.excel2db.domain.DataSourceDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataSourceMapper {

    public DataSourceDO selectById(Long id);

    public List<DataSourceDO> list(DataSourceDO dataSource);

    public int insert(DataSourceDO dataSource);

    public int update(DataSourceDO dataSource);


}
