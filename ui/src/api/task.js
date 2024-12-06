import request from '@/utils/request'


// 新增
export function createTask(data) {
    return request({
        url: '/task/create',
        method: 'post',
        data: data
    })
}
// 启动任务
export function startTask(data) {
    return request({
        url: '/task/start',
        method: 'post',
        params: data
    })
}
//查询
export function getTaskList(query) {
    return request({
        url: '/task/list',
        method: 'get',
        params: query
    })
}



