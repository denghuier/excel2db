package com.hmh.excel2db.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmh.excel2db.db.all.DataSource;
import com.hmh.excel2db.db.all.serviceLocator.DBServiceLocator;
import com.hmh.excel2db.domain.DataSourceDO;
import com.hmh.excel2db.domain.Task;
import com.hmh.excel2db.domain.TaskTransformConfig;
import com.hmh.excel2db.excel.read.ExcelRead;
import com.hmh.excel2db.excel.read.ListenerDo;
import com.hmh.excel2db.mapper.DataSourceMapper;
import com.hmh.excel2db.mapper.TaskMapper;
import com.hmh.excel2db.mapper.TaskTransformConfigMapper;
import com.hmh.excel2db.service.ITaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements ITaskService {


    @Autowired
    private DataSourceMapper dataSourceMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskTransformConfigMapper taskTransformConfigMapper;

    @Override
    @Transactional
    public int createTask(Task task) {
//        ExcelRead excelRead = new ExcelRead();
//
//        ListenerDo listenerDo=new ListenerDo();
////        listenerDo.setHeadRowNumber(task.getHeaderNum());
//        listenerDo.setEndRowNumber(task.getTotalRowNum()-task.getRemoveEndNum());
//
//        DataSourceDO dataSourceDO = dataSourceMapper.selectById(task.getDsId());
//        JSONObject json = JSONUtil.parseObj(dataSourceDO, false);
//        String str= json.toStringPretty();
//        DataSource dataSource=DBServiceLocator.map.get(dataSourceDO.getDbType().toLowerCase()).createSource(str);
//        listenerDo.setDataSource(dataSource);
//        listenerDo.setTableName(task.getTableName());
//        listenerDo.setTransformConfigs(task.getList());

        taskMapper.insert(task);
        for (TaskTransformConfig taskTransformConfig : task.getList()){
            taskTransformConfig.setTaskId(task.getTaskId());
            taskTransformConfigMapper.insert(taskTransformConfig);
        }
//        listenerDo.setTaskId(task.getTaskId());
//        //读取excel
//        List<Map<Integer, String>> data=excelRead.read(task.getStorePath(),task.getSheetName(),task.getStartRowNum(),listenerDo);
        return Math.toIntExact(task.getTaskId());
    }

    @Override
    public int startTask(Long taskId) {
        Task task = taskMapper.selectById(taskId);
        task.setList(taskTransformConfigMapper.selectByTaskId(taskId));
        ListenerDo listenerDo=new ListenerDo();
        DataSourceDO dataSourceDO = dataSourceMapper.selectById(task.getDsId());
        JSONObject json = JSONUtil.parseObj(dataSourceDO, false);
        String str= json.toStringPretty();
        DataSource dataSource=DBServiceLocator.map.get(dataSourceDO.getDbType().toLowerCase()).createSource(str);
        listenerDo.setDataSource(dataSource);
        listenerDo.setTableName(task.getTableName());
        listenerDo.setTransformConfigs(task.getList());
        listenerDo.setTaskId(task.getTaskId());
        ExcelRead excelRead = new ExcelRead();
        excelRead.read(task.getStorePath(),task.getSheetName(),task.getStartRowNum(),listenerDo);
        return 1;
    }

    @Override
    public List<Task> list(Task task) {
        return taskMapper.list(task);
    }

    @Override
    public Task getTask(Long id) {
        return taskMapper.selectById(id);
    }


}
