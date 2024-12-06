import request from '@/utils/request'



// 查询数据源列表
export function getAllDatasource() {
  return request({
    url: '/datasource/all',
    method: 'get',

  })
}

// 查询数据源列表
export function listDatasource(query) {
  return request({
    url: '/datasource/list',
    method: 'get',
    params: query
  })
}



// 查询类型
export function getDBType() {
  return request({
    url: '/datasource/type/list' ,
    method: 'get'
  })
}

// 查询表
export function getTableList(id) {
  return request({
    url: '/datasource/tableList/'+id,
    method: 'get'
  })
}

export function getColumnList(query) {
  return request({
    url: '/datasource/colList',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getDatasource(id) {
  return request({
    url: '/datasource/' + id,
    method: 'get'
  })
}

// 新增
export function addDatasource(data) {
  return request({
    url: '/datasource/add',
    method: 'post',
    data: data
  })
}

// 修改
export function updateDatasource(data) {
  console.log(data)
  return request({
    url: '/datasource/edit',
    method: 'put',
    data: data
  })
}

// 删除
export function delDatasource(id) {
  return request({
    url: '/datasource/del/' + id,
    method: 'delete'
  })
}
