package com.hmh.excel2db.controller;

import com.hmh.excel2db.common.core.controller.BaseController;
import com.hmh.excel2db.common.core.domain.AjaxResult;
import com.hmh.excel2db.common.core.page.TableDataInfo;
import com.hmh.excel2db.domain.Task;
import com.hmh.excel2db.framework.socket.NoticeWebsocket;
import com.hmh.excel2db.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private ITaskService taskService;
    @PostMapping("create")
    public AjaxResult createTask(@RequestBody Task task){
        return AjaxResult.success(taskService.createTask(task));
    }

    @PostMapping("start")
    public AjaxResult startTask(@RequestParam("taskId")  Long taskId){
        return AjaxResult.success(taskService.startTask(taskId));
    }
    @GetMapping("list")
    public TableDataInfo list(Task task){
        startPage();
        List<Task> list = taskService.list(task);
        return getDataTable(list);
    }
    @GetMapping("test")
    public AjaxResult test(){
        NoticeWebsocket.sendMessageByTaskId("2","你好，WebSocket");
        return AjaxResult.success();
    }

}
