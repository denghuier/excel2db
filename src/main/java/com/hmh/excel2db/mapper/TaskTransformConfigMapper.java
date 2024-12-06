package com.hmh.excel2db.mapper;



import com.hmh.excel2db.domain.TaskTransformConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskTransformConfigMapper {

    public int insert(TaskTransformConfig taskTransformConfig);

    public List<TaskTransformConfig> selectByTaskId(Long taskId);

}
