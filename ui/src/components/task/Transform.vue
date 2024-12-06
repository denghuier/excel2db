<script setup lang="ts">
import { reactive,ref,onMounted,nextTick } from 'vue'
import { jsPlumb } from 'jsplumb'

const props = defineProps({
    // 变量 count 是通过父组件传递过来的
    colObject: {
      type: Object,
      default: null
    }
  })


// 页面加载完毕
onMounted(() => {
 // initJsPlumb()
  leftData.value=props.colObject.excelColList
  rightData.value=props.colObject.dataColList
})

nextTick(()=>{
  initJsPlumb()
})

// 左侧静态数据
const leftData = ref([
    // {colNum: "name1", colName: "name1", axis: '', type:''},
    //     {colNum: "name2", colName: "name2", axis: '', type:''},
    //     {colNum: "name3", colName: "name3", axis: '', type:''},
    //     {colNum: "name4", colName: "name4", axis: '', type:''},
    //     {colNum: "name5", colName: "name5", axis: '', type:''},
    //     {colNum: "name6", colName: "name6", axis: '', type:''}

]);
// 右侧静态数据
const rightData = ref([
    // {columnName: "name1", columnRemarks: "name1", axis: '', type:''},
    //     {columnName: "name2", columnRemarks: "name2", axis: '', type:''},
    //     {columnName: "name3", columnRemarks: "name3", axis: '', type:''},
    //     {columnName: "name4", columnRemarks: "name4", axis: '', type:''},
    //     {columnName: "name5", columnRemarks: "name5", axis: '',}
]);



// 初始化jsplumb
const initJsPlumb = () => {
  console.log("初始化jsplumb")
   jsPlumb.ready(function () {
  // 初始化jsPlumb 创建jsPlumb实例
      init();

  // 设置可以为连线起点和连线终点的元素
      setContainer();
  // 绑定事件监听
      setEvent();
    });
}




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
    ConnectionsDetachable: true,
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
        source: document.querySelector(`.lId-${data.sourceId}`),
        target: document.querySelector(`.rId-${data.targetId}`)
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


//同名映射
const sameName =()=>{
  //取消映射
  cancel()
  //选择数据集较大的数组作为循环次数
  let m= leftData.value.length > rightData.value.length ? leftData.value.length:rightData.value.length
  let dataColMap=new Map();
  let excelColMap=new Map();
  let map= new Map();
  for (let n=0;n<m-1;n++){
    if(leftData.value.length-1>=n){
      let leftItem=leftData.value[n]
      excelColMap.set(leftItem.colName,n)
      if (dataColMap.has(leftItem.colName) && !map.has(n)){
        map.set(n,dataColMap.get(leftItem.colName));
      }
    }
    if(rightData.value.length-1>=n){
      let rightItem=rightData.value[n]
      dataColMap.set(rightItem.columnName,n)
      let key=excelColMap.get(rightItem.columnName);
      if (excelColMap.has(rightItem.columnName) && !map.has(n)){
        map.set(key,n);
      }
    }
  }
 console.log(map)
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
  let m=leftData.value.length > rightData.value.length ? rightData.value.length: leftData.value.length

  for (let n=0;n<m;n++){

    relationship.push({
      sourceId:n,
      targetId:n
    })
  }

  setConnect(relationship)
}
// 备注映射
const sameRemark = () =>{
  //取消映射
  cancel()
//选择数据集较大的数组作为循环次数
  let m=leftData.value.length > rightData.value.length ? leftData.value.length: rightData.value.length
  let dataColMap=new Map();
  let excelColMap=new Map();
   let map= new Map();
  for (let n=0;n<m-1;n++){

    if(leftData.value.length-1>=n){
      let leftItem=leftData.value[n]
      excelColMap.set(leftItem.colName,n)
      if (dataColMap.has(leftItem.colName) && !map.has(n)){
        map.set(n,dataColMap.get(leftItem.colName));
      }
    }
    if(rightData.value.length-1>=n){
      let rightItem=rightData.value[n]
      dataColMap.set(rightItem.columnRemarks,n)
      let key=excelColMap.get(rightItem.columnRemarks);
      if (excelColMap.has(rightItem.columnRemarks) && !map.has(n)){
        map.set(key,n);
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
// 取消映射
const cancel = () =>{
  instance.deleteEveryConnection()
  relationship=reactive([])
  console.log(relationship)

}

//定义一个向父组件add.vue提交数据的事件
const emits = defineEmits(['sendData'])
// 向父传递数据
const sendData=()=>{
  const  relationArr = reactive([])
  instance.getAllConnections().forEach(function(conn) {

    console.log(conn.source.classList[2],conn.target.classList[2])
    relationArr.push({
      sourceId:interceptId(conn.source.classList[2] ),
      targetId:interceptId(conn.target.classList[2] )
    })

  });

  emits('sendData',relationArr)
}

// 暴露方法
// defineExpose({
//   initJsPlumb
// });
</script>

<template>
 <div class="line">
              <el-link type="primary" :onclick="sameRow">同行映射</el-link>
              <el-link type="primary" :onclick="sameName">同名映射</el-link>
              <el-link type="primary" :onclick="sameRemark">备注映射</el-link>
              <el-link type="warning" :onclick="cancel">取消映射</el-link>

              <div id="container" style="display: flex; justify-content: space-between; position: relative;">
                <el-table
                    :data="leftData"
                    :row-class-name="({row,rowIndex}) => `leftRow lId-${rowIndex}`"
                    style="width: 40%; margin-bottom: 20px; display: inline-block;"
                >
                  <el-table-column  label="行号">
                    <template #default="scope">
                      <span>{{ scope.$index}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="colNum" label="序号"/>
                  <el-table-column prop="colName" label="列名称"/>

                </el-table>
                <el-table
                    :data="rightData"
                    :row-class-name="({row,rowIndex}) => `rightRow rId-${rowIndex}`"
                    style="width: 40%; margin-bottom: 20px; display: inline-block;"
                >
                  <el-table-column  label="行号">
                    <template #default="scope">
                      <span>{{ scope.$index}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="columnName" label="名称"/>
                  <el-table-column prop="columnType" label="类型"/>
                  <el-table-column prop="columnRemarks" label="备注"/>
                </el-table>
              </div>
            </div>

  <div style="position: fixed;right: 40px;bottom:30px;z-index: 2">
    <!--                  <el-button type="primary" @click="saveForm">保存</el-button>-->
    <el-button type="primary" @click="sendData()">下一步</el-button>
    <!--                  <el-button @click="closeForm">关闭</el-button>-->
  </div>
</template>

<style scoped>
.el-row{
  margin-top: 20px;
}
.el-link {
  margin-right: 8px;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}
.line{
  width: 80%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px
}
</style>