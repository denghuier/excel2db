<template>

  <el-container direction="vertical" class="container">
    <el-main>
      <el-steps  :active="active" align-center>
        <el-step title="上传"  ></el-step>
        <el-step title="数据源" />
        <el-step title="传输配置"  />
        <el-step title="提交" />
      </el-steps>

      <div v-show="active == 0">
        <excel @sendData="excelData" ></excel>
      </div>
      <div v-show="active == 1">
        <datasource @sendData="dataSourceData"></datasource>
      </div>
      <div v-show="active == 2">
        <transform v-if="active==2" :colObject="colObject" @sendData="transformData"></transform>
      </div>
      <div v-show="active == 3">
        <submit v-if="active==3" :form="form"></submit>
      </div>

    </el-main>

  </el-container>

</template>

<script  lang="ts" setup>
import { reactive,ref } from 'vue'

import excel from "./Excel.vue"
import datasource from "./DataSource.vue"
import transform from "./Transform.vue"
import submit from "./Submit.vue"
import { createTask } from "@/api/task";

const form = reactive({
  fileName: "",
  tableName: "",
  sheetName: "",
  headerNum: "",
  startRowNum:"",
  totalRowNum:"",
  removeEndNum: "",
  storePath:"",
  list: [],
  dsId: "",
  taskId: ""
})
const transformConfig = reactive({
  sourceColumn: "",
  sourceColumnNum: "",
  targetColumn: "",
  transformType: "",
  configValue: ""
})
const colObject = reactive({
  excelColList: [],
  dataColList: []
})
// excel页面返回数据
function excelData(data){

  form.sheetName=data.sheetName
  form.fileName=data.fileName
  form.storePath=data.storePath
  form.startRowNum=data.startRowNum
  form.totalRowNum=data.totalRowNum
  form.headerNum=data.headerNum
  form.removeEndNum=data.removeEndNum
  colObject.excelColList=data.colList
  console.log("excel返回数据")
  next()
}

// 数据源页面返回数据
function dataSourceData(data){
  colObject.dataColList=data.colList
  form.dsId=data.dsId
  form.tableName=data.tableName
  console.log("数据源返回数据")
  next()
}

// 传输页面返回数据
function transformData(data){

  console.log(data)
  for (let i = 0; i < data.length; i++){
    let excelNum=data[i].sourceId
    let dataNum=data[i].targetId
    transformConfig.sourceColumnNum=excelNum
    transformConfig.sourceColumn=colObject.excelColList[excelNum].colName
    transformConfig.transformType=colObject.excelColList[excelNum].transformType
    transformConfig.configValue=colObject.excelColList[excelNum].configValue
    transformConfig.targetColumn=colObject.dataColList[dataNum].columnName
    form.list.push({ ...transformConfig })
  }


  createTask(form).then(res => {
    console.log(res)
    form.taskId=res.data
    next()
  })

}


// 下一步
const active = ref(0)
const next = () => {
  if (active.value++ > 3) {
    active.value = 0
  }
  // if (active.value==2){
  //   init.value.initJsPlumb(colObject);
  // }
}
</script>



<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}







</style>