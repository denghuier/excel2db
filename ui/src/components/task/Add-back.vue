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

<!--          <el-form :model="form"-->
<!--              label-position="left" label-width="100px" style="max-width: 100%;margin-left: 50px">-->
<!--           -->

<!--          </el-form>-->
          <div v-show="active == 0">
            <el-form :model="excelForm"  label-width="120px">
              <el-form-item label="上传文件" prop="file">
                <el-upload
                    style="width: 100%"
                    drag
                    action="/dev-api/excel/upExcel"
                    :on-success="handleUploadSuccess"
                    multiple >
                  <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                  <div class="el-upload__text">
                    拖拽 或者
                    <em>点击上传</em>
                  </div>
                  <template #tip>
                    <div class="el-upload__tip">
                      上传excel 文件
                    </div>
                  </template>
                </el-upload>
              </el-form-item>
              <el-form-item label="文件名" prop="fileName">
                <el-input v-model="excelForm.fileName" readonly />
              </el-form-item>
              <el-form-item label="工作表" prop="worksheetNumber" >
                <el-select v-model="excelForm.worksheetNumber" placeholder="请选择"
                           @change="getTotalRow()">
                  <el-option
                      v-for="item in worksheetNameList"
                      :key="item"
                      :label="item"
                      :value="item"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="总行数" prop="totalRowNum" >
                <el-input v-model="excelForm.totalRowNum" readonly />
              </el-form-item>
              <el-form-item label="列序号" prop="headerNum" >
                <el-input v-model="excelForm.headerNum"  @change="getExcelCol"/>
              </el-form-item>
              <el-form-item label="读取范围" prop="readArea">
                <el-col :span="2">
                </el-col>
                <el-col :span="1">
                  <span>起始行：</span>
                </el-col>
                <el-col :span="2">
                  <el-input v-model="excelForm.readArea"  />
                </el-col>
                <el-col :span="1">

                </el-col>
                <el-col :span="1">
                  <span>结束行：</span>
                </el-col>
                <el-col :span="2">
                  <el-input v-model="excelForm.totalRowNum" readonly disabled />
                </el-col>
                -
                <el-col :span="2">
                  <el-input v-model="excelForm.totalRowNum"   />
                </el-col>

              </el-form-item>
              <el-form-item label="读取后操作" prop="readAfterAction">
                <el-radio-group v-model="excelForm.readAfterAction">
                  <el-radio  label="保留">保留</el-radio>
                  <el-radio  label="删除文件">删除文件</el-radio>

                </el-radio-group>
              </el-form-item>
              <el-form-item label="字段" prop="readAfterAction">
                <el-table :data="excelForm.fields" style="width: 100%">
                  <el-table-column type="selection" width="55"></el-table-column>
                  <el-table-column prop="colNum" label="字段序号" ></el-table-column>
                  <el-table-column prop="colName" label="中文说明"></el-table-column>
                  <el-table-column prop="dataType" label="数据类型" ></el-table-column>
                  <el-table-column prop="bindingRule" label="绑定数据转换规则" ></el-table-column>
                  <el-table-column prop="defaultValue" label="缺省值" ></el-table-column>
                  <el-table-column prop="order" label="顺序" ></el-table-column>
                </el-table>

                <!--                  <div class="field-options">-->
                <!--                    <el-radio-group v-model="excelForm.fieldOption">-->
                <!--                      <el-radio label="自定义字段">自定义字段(一一对应Excel中的列)</el-radio>-->
                <!--                      <el-radio label="第一行为字段名">Excel的第一行为字段名</el-radio>-->
                <!--                      <el-radio label="行标识为字段名">使用Excel的行标识为字段名</el-radio>-->
                <!--                    </el-radio-group>-->
                <!--                  </div>-->
              </el-form-item>

            </el-form>

            

          </div>
          <div v-show="active == 1">
            <el-form :model="excelForm"  label-width="120px">
              <el-form-item label="数据源" prop="dsId">
                <el-select v-model="form.dsId" placeholder="请选择"
                           @change="getTables()">
                  <el-option
                      v-for="item in dataSourceList"
                      :key="item.id"
                      :label="item.dsName"
                      :value="item.id"
                  />
                </el-select>

              </el-form-item>
              <el-form-item label="表" prop="tableName">
                <el-select v-model="form.tableName" placeholder="请选择"
                           @change="getColList()">
                  <el-option
                      v-for="item in tableNameList"
                      :key="item"
                      :label="item"
                      :value="item"
                  />
                </el-select>
              </el-form-item>

              <el-form-item label="字段" prop="tableName">
                <el-table :data="form.colList" style="width: 100%">
                  <el-table-column prop="columnName" label="名称"  />
                  <el-table-column prop="columnType" label="类型"  />
                  <el-table-column prop="columnRemarks" label="备注" />
                </el-table>
              </el-form-item>

            </el-form>

          </div>
          <div v-show="active == 2">
            <div class="line">
              <el-link type="primary" :onclick="sameRow">同行映射</el-link>
              <el-link type="primary" :onclick="sameName">同名映射</el-link>
              <el-link type="primary" :onclick="sameRemark">备注映射</el-link>
              <el-link type="warning" :onclick="cancel">取消映射</el-link>
              <div id="container" style="display: flex; justify-content: space-between; position: relative;">
                <el-table
                    ref="leftTable"
                    :data="excelForm.fields"
                    :row-class-name="({row}) => `leftRow Id-${row.colNum}`"
                    style="width: 40%; margin-bottom: 20px; display: inline-block;"
                    row-key="colNum"

                    default-expand-all
                >
                  <el-table-column prop="colNum" label="序号"/>
                  <el-table-column prop="colName" label="列名称"/>
                  <el-table-column >
                    <template #default="scope">
                      <el-select >
                        <el-option
                            v-for="item in excelForm.transformType"
                            :key="item"
                            :label="item.name"
                            :value="item.code"
                        />
                      </el-select>
                    </template>
                  </el-table-column>
                </el-table>
                <el-table
                    ref="rightTable"
                    :data="form.colList"
                    :row-class-name="({row}) => `rightRow Id-${row.columnName}`"
                    style="width: 40%; margin-bottom: 20px; display: inline-block;"
                    row-key="id"
                    default-expand-all
                >
                  <el-table-column prop="columnName" label="名称"/>
                  <el-table-column prop="columnType" label="类型"/>
                  <el-table-column prop="columnRemarks" label="备注"/>
                </el-table>
              </div>
            </div>
          </div>
          <div v-show="active == 3"></div>
          <div style="position: fixed;right: 40px;bottom:30px;z-index: 2">
            <!--                  <el-button type="primary" @click="saveForm">保存</el-button>-->
            <el-button type="primary" @click="next()">下一步</el-button>
            <!--                  <el-button @click="closeForm">关闭</el-button>-->
          </div>
        </el-main>


    </el-container>
  </div>

</template>

<script  lang="ts" setup>
import { reactive,ref,onMounted } from 'vue'

import { ArrowRight,UploadFilled  } from '@element-plus/icons-vue'
import { listDatasource,getTableList,getColumnList } from "@/api/datasource";
import {getTotalRowNum,getHeaderMap,getTransformType} from "@/api/excel"
import { jsPlumb } from 'jsplumb'

import excel from "./Excel.vue"


const form = reactive({
  name: '',
  dsId: '',
  dsName: '',
  dataSourceList: [],
  tableName: '',
  type: [],
  colList: []
})

const excelForm = reactive({
  file: '',
  fileName: '',
  fields: [],
  worksheetNumber: '',
  readAfterAction: '保留',
  fieldOption: '自定义字段',
  fileList: [],
  storePath: '',
  headerNum: '',
  totalRowNum: '',
  transformType: [],
})
const worksheetNameList = ref([])
// 上传文件-成功后回调
const handleUploadSuccess = (res, file)=> {

  worksheetNameList.value=res.data.sheetNames
  excelForm.fileName=res.data.originalFilename
  excelForm.storePath=res.data.storePath
}
// 获取Excel行数
const getTotalRow= () => {
  const data={
    "pathName":excelForm.storePath,
    "sheetName":excelForm.worksheetNumber,
  }
  getTotalRowNum(data).then(res=>{
    excelForm.totalRowNum=res.data
  })
}

// 获取Excel列
const getExcelCol = () => {
  const data={
    "pathName":excelForm.storePath,
    "sheetName":excelForm.worksheetNumber,
    "headRowNumber":excelForm.headerNum
  }
   getHeaderMap(data).then(res=>{
    excelForm.fields = res.data
  })
}
// 获取转换类型
getTransformType().then(res=>{
  console.log(res.data)
  excelForm.transformType=res.data
})
const dataSourceList = ref([])

const tableNameList = ref([])


//初始化 获取数据源
listDatasource().then(res => {
  dataSourceList.value = res.rows
})

const getTables = () => {

  getTableList(form.dsId).then(res => {
    tableNameList.value = res.data
  })
}

const getColList = () => {
  const data={"dsId":form.dsId,"tableName":form.tableName}
  getColumnList(data).then(res=>{
    form.colList = res.data
  })
}



// 左侧静态数据
const leftData = ref([]);

const leftExcelData =  ref([]);
// 右侧静态数据
const rightData = ref([]);
const rightDataSourceData = ref([]);
const leftTable = ref(null);
const rightTable = ref(null);

var instance = reactive(null);
function init() {

  instance = jsPlumb.getInstance({
    Connector: "Straight", //连接线形状 Bezier: 贝塞尔曲线 Flowchart: 具有90度转折点的流程线 StateMachine: 状态机 Straight: 直线
    PaintStyle: { strokeWidth: 3, stroke: "#dfbee7" }, //连接线样式
    Endpoint: ["Blank", { radius: 1 }], //端点
    anchor: 'Right',
    // 绘制箭头
    Overlays: [['Arrow', { width: 12, length: 12, location: 1 }]],
    HoverPaintStyle: { stroke: "#000000" },
    //鼠标滑过线的样式
    EndpointStyle: { fill: "#000000" }, //端点样式
    // ConnectionsDetachable: true,
    // EndpointHoverStyle: {fill: 'red'},
    Container: "container", //目标容器id
    ListStyle: {
      endpoint: ["Rectangle", { width: 30, height: 30 }],
    },
  });
}

// 设置可以连线的元素
function setContainer() {
  const leftElList = document.querySelectorAll('.leftRow'); // 左侧行元素集合
  const rightElList = document.querySelectorAll('.rightRow');  // 右侧行元素集合
  // 将dom元素设置为连线的起点或者终点 设置了起点的元素才能开始连线 设置为终点的元素才能为连线终点
  instance.batch(function () {
    [leftElList, rightElList].forEach((trList, index) => {
      trList.forEach((tr) => {
        const id = interceptId(tr.classList[2]);
        if (index === 0) {
          const item = leftData.value.find(i => i.id == id);
          // 判断是否有子项,若没有则设置为起点
          !item?.hasChild && instance.makeSource(tr, {
            allowLoopback: false,
            anchor: ["Right"], // 设置端点位置
            maxConnections: 1
          });
        } else {
          const item = rightData.value.find(i => i.id == id);
          // 判断是否有子项,若没有则设置为终点
          !item?.hasChild && instance.makeTarget(tr, {
            anchor: ["Left"],
            maxConnections: 1
          });
        }
      });
    });
  });
}
var relationship = reactive([])
// 设置默认连线
function setConnect(relationship) {
  setTimeout(() => {
    relationship.forEach(function (data) {
// source是连线起点元素id target是连线终点元素id
      instance.connect({
        source: document.querySelector(`.Id-${data.sourceId}`),
        target: document.querySelector(`.Id-${data.targetId}`)
      });
    });
  })
}
//  绑定事件监听
function setEvent() {
  // 连线事件
  instance.bind("connection", function (connInfo, originalEvent) {
    // connInfo是jsPlumb对象 可以打印出来康康有哪些东西
    // console.log(connInfo, originalEvent, 'connInfo')
    console.log(connInfo.source.classList[2],connInfo.target.classList[2])
  });
  // 点击连接线删除该条线
  instance.bind('click', function (connection, originalEvent) {
    console.log(connection.source.classList[2],connection.target.classList[2])
    instance.deleteConnection(connection);
  })
}


// 截取元素类名中的id
const interceptId = className => {
  return className.slice(className.indexOf('-') + 1);
}
const initJsPlumb = () => {
  console.log("jsplumb")
  jsPlumb.ready(function () {
// 初始化jsPlumb 创建jsPlumb实例
    init();
// 设置可以为连线起点和连线终点的元素
    setContainer();
// 绑定事件监听
    setEvent();
  });
}
//同名映射
const sameName =()=>{
  //取消映射
  cancel()
  //选择数据集较大的数组作为循环次数
  let m=excelForm.fields.length > form.colList.length ? excelForm.fields.length:form.colList.length
  let dataColMap=new Map();
  let excelColMap=new Map();
  let map= new Map();
  for (let n=0;n<m-1;n++){

    if(excelForm.fields.length-1>=n){
      let item=excelForm.fields[n]
      excelColMap.set(item.colName,item.colNum)
      if (dataColMap.has(item.colName) && !map.has(item.colNum)){
        map.set(item.colNum,dataColMap.get(item.colName));
      }
    }
    if(form.colList.length-1>=n){
      let item2=form.colList[n]
      dataColMap.set(item2.columnName,item2.columnName)
      let key=excelColMap.get(item2.columnName);
      if (excelColMap.has(item2.columnName) && !map.has(key)){
        map.set(key,item2.columnName);
      }
    }
  }
  map.forEach(function(value, key) {

    relationship.push({
      sourceId:key,
      targetId:value
    })
  })
  setConnect(relationship)
}
// 同行映射
const sameRow = () =>{
  //取消映射
  cancel()
  // 选择数据集较小的数组作为循环次数
  let m=excelForm.fields.length > form.colList.length ? form.colList.length:excelForm.fields.length

  for (let n=0;n<m;n++){
    let item=excelForm.fields[n]
    let item2=form.colList[n]

    relationship.push({
      sourceId:item.colNum,
      targetId:item2.columnName
    })

  }

  setConnect(relationship)
}
// 备注映射
const sameRemark = () =>{
  //取消映射
  cancel()
//选择数据集较大的数组作为循环次数
  let m=excelForm.fields.length > form.colList.length ? excelForm.fields.length:form.colList.length
  let dataColMap=new Map();
  let excelColMap=new Map();
  let map= new Map();
  for (let n=0;n<m-1;n++){

    if(excelForm.fields.length-1>=n){
      let item=excelForm.fields[n]
      excelColMap.set(item.colName,item.colNum)
      if (dataColMap.has(item.colName) && !map.has(item.colNum)){
        map.set(item.colNum,dataColMap.get(item.colName));
      }
    }
    if(form.colList.length-1>=n){
      let item2=form.colList[n]
      dataColMap.set(item2.columnRemarks,item2.columnName)
      let key=excelColMap.get(item2.columnRemarks);
      if (excelColMap.has(item2.columnRemarks) && !map.has(key)){
        map.set(key,item2.columnName);
      }
    }
  }
  map.forEach(function(value, key) {
    console.log(key+"=="+value)
    relationship.push({
      sourceId:key,
      targetId:value
    })
  })
  setConnect(relationship)
}
// 取消映射
const cancel = () =>{
  instance.deleteEveryConnection()

  relationship=reactive([])
  console.log(relationship)
  // setConnect(relationship)
}
// 页面加载完毕
onMounted(() => {

})
// 下一步
const active = ref(0)
const next = () => {
  if (active.value++ > 3) {
    active.value = 0
  }
  if (active.value==2){
    initJsPlumb();
  }
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
.line{
  width: 80%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px
}

.el-link {
  margin-right: 8px;
}
.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}
</style>