<script setup lang="ts">
import {ref, onMounted, nextTick, reactive} from 'vue';
import { useRouter } from 'vue-router';
import { startTask } from "@/api/task";

const router = useRouter()
const props = defineProps({
  // 变量 count 是通过父组件传递过来的
  form: {
    type: Object,
    default: null
  }
})
const taskId= props.form.taskId;
const transformTotal= props.form.totalRowNum-props.form.removeEndNum
const transformNum= ref(0)
const status=ref('warning')
const percentage= ref(0)

onMounted(() => {
  init()
  submitForm()
})



const submitForm = () => {
  let data = reactive({
    taskId:taskId
  })
  startTask(data).then(res => {
    if(res.code==200){
      // router.push({path:'/task'})
    }
  })
}

const init = () => {
  if(typeof(WebSocket) === "undefined"){
    alert("您的浏览器不支持socket")
  }else{
    const ws = new  WebSocket('ws://localhost:8080/notice/'+taskId);
    //  //连接发生错误的回调方法
    ws.onerror = function () {
      console.log("ws连接发生错误");
    };
    //连接成功建立的回调方法
    ws.onopen = function () {
      console.log("ws连接成功");
    }
    //接收到消息的回调方法
    ws.onmessage = function (event) {
      // console.log( '1的',event.data);
      transformNum.value = event.data
      percentage.value = Math.round(transformNum.value/transformTotal*100)
      if(transformNum.value==transformTotal){
        status.value='success'
      }
    }
    //连接关闭的回调方法
    ws.onclose = function () {
      console.log("ws连接关闭");
    }
  }
}

</script>

<template>

  <div class="main">
    <el-progress :percentage="percentage" :stroke-width="25" striped striped-flow />
    <el-result
        :icon="status"
        title="传输数量"
        :sub-title="transformNum"
    >
      <template #extra>
        <el-button type="primary" @click="router.push({path:'/task'})">返回</el-button>
      </template>
    </el-result>


  </div>

</template>

<style scoped>
.main{
  text-align: center; /*让div内部文字居中*/
  background-color: #ffffff;
  border-radius: 20px;
  width: 50%;
  height: 50%;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}

</style>