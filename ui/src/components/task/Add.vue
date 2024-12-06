<template>

  <div style="height: calc(98vh);width: 100%">
    <el-container direction="vertical">

        <el-header>
          <br>
          <el-breadcrumb :separator-icon="ArrowRight">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>上传</el-breadcrumb-item>

          </el-breadcrumb>
        </el-header>
        <el-main>
          <el-steps  :active="active" align-center>
            <el-step title="上传"  ></el-step>
            <el-step title="数据源" />
            <el-step title="传输配置"  />
            <el-step title="提交" />
          </el-steps>

          <div v-show="active == 0">
            <excel @sendData="excelData"></excel>
          </div>
          <div v-show="active == 1">
            <datasource @sendData="dataSourceData"></datasource>
          </div>
          <div v-show="active == 2">
            <transform v-if="active==2" :colObject="colObject" @sendData="transformData"></transform>
          </div>
          <div v-show="active == 3"></div>
          <div style="position: fixed;right: 40px;bottom:30px;z-index: 2">
            <!--                  <el-button type="primary" @click="saveForm">保存</el-button>-->
<!--            <el-button type="primary" @click="next()">下一步</el-button>-->
            <!--                  <el-button @click="closeForm">关闭</el-button>-->
          </div>
        </el-main>

    </el-container>
  </div>

</template>

<script  lang="ts" setup>
import { reactive,ref } from 'vue'
import { ArrowRight  } from '@element-plus/icons-vue'
import excel from "./Excel.vue"
import datasource from "./DataSource.vue"
import transform from "./Transform.vue"
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
    console.log(data[i].sourceId)
    let excelNum=data[i].sourceId
    let dataNum=data[i].targetId
    transformConfig.sourceColumnNum=excelNum
    transformConfig.sourceColumn=colObject.excelColList[excelNum].colName
    transformConfig.transformType=colObject.excelColList[excelNum].transformType
    transformConfig.configValue=colObject.excelColList[excelNum].configValue
    transformConfig.targetColumn=colObject.dataColList[dataNum].columnName
    form.list.push({ ...transformConfig })
  }

  console.log(form.list)
  console.log("传输返回数据")

  createTask(form).then(res => {
    console.log(res)
  })
  next()
}

const init=ref(null)
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
html ,body,#app,.el-container {
  height: 100%;
  width: 100%;
}

.el-header, .el-footer {
  background-color: #ebeef1;
  color: #333;
  text-align: center;
  height: 60px !important;
}
.el-main {
  background-color: #ebeef1;
}



</style>