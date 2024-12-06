package com.hmh.excel2db.service;

import com.hmh.excel2db.domain.Task;

import java.util.List;

public interface ITaskService {

    /**
     * 创建任务
     * @param task
     * @return
     */
    public int createTask(Task task);

    /**
     * 启动任务
     * @param taskId
     * @return
     */
    public int startTask(Long taskId);


    /**
     * 获取任务列表
     * @param task
     * @return
     */
    public List<Task> list(Task task);

    /**
     * 根据id获取任务
     * @param id
     * @return
     */
    public Task getTask(Long id);



}
