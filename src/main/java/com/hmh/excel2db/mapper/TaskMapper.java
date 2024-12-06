package com.hmh.excel2db.mapper;


import com.hmh.excel2db.domain.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {



    List<Task> list(Task task);

    int insert(Task task);

    Task selectById(Long id);

}
