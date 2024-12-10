import request from '@/utils/request'

// excel总行数
export function getTotalRowNum(data) {
    return request({
        url: '/excel/getTotalRowNum',
        method: 'post',
        params: data
    })
}

// excel 表头
export function getHeaderMap(data) {
    return request({
        url: '/excel/getHeaderMap',
        method: 'post',
        params: data
    })
}

export function getExcelData(data) {
    return request({
        url: '/excel/getExcelData',
        method: 'post',
        params: data
    })
}

export function getTransformType() {
    return request({
        url: '/excel/transform/type',
        method: 'get',

    })
}

export function uploadExcel(data) {
    return request({
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        url: '/excel/upExcel',
        method: 'post',
        data: data
    })

}