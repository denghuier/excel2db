<script setup lang="ts" >
import { reactive,ref,onMounted  } from 'vue'

import {getTaskList} from "@/api/task";
import { useRouter } from 'vue-router';

const router = useRouter();

const taskList = ref([])
const total=ref(0)
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,

})
onMounted(() => {
  getList()
})
const getList = () => {
  getTaskList(queryParams).then(response => {
    taskList.value = response.rows
    total.value=response.total
  })
}

// 翻页
const pageChange = (currentPage,pageSize) => {
  queryParams.pageNum=currentPage
  getList()
}

//添加按钮
const add = () => {
  router.push("/taskAdd");
}
// 查看按钮
const info = (row) => {

}
</script>

<template>
  <el-container direction="vertical" class="container">
    <el-main >
      <el-row>
        <el-col :span="24" >
          <el-button type="primary" size="small" @click="add"><el-icon><Plus /></el-icon>创建</el-button>
          <el-table :data="taskList" style="width: 100%">
            <el-table-column prop="taskId" label="序号" align="center"  />
            <el-table-column prop="fileName" label="文件名称" min-width="100px" />
            <el-table-column prop="tableName" label="表名称"  />
            <el-table-column prop="sheetName" label="sheet"  />
            <el-table-column prop="headerNum" label="表头" />
            <el-table-column prop="startRowNum" label="开始" />
            <el-table-column label="操作" >
              <template #default="scope">
                <el-button type="primary" size="small" @click="info(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-pagination
          background
          layout="prev, pager, next"
          size="small"
          v-show="total>0"
          :total="total"
          :default-page-size="queryParams.pageNum"
          :page-size="queryParams.pageSize"
          @change  ="pageChange"
          class="pagination"
      />
    </el-main>

  </el-container>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
}
.pagination{
  margin-top: 10px;
  float: right;
  margin-right: 4%;
}
</style>