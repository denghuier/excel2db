<template>
  <el-container direction="vertical" style="width: 100%;height: 100%;background-color: #ffffff">
    <el-main >
      <el-row>
        <el-col :span="24" >
          <el-button type="primary" size="small" @click="add"><el-icon><Plus /></el-icon>添加</el-button>
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="序号" align="center"  />
            <el-table-column prop="dsName" label="数据源名称"  />
            <el-table-column prop="dbType" label="数据源类型"  />
            <el-table-column prop="host" label="host" min-width="100px" />
            <el-table-column prop="port" label="端口" />
            <el-table-column prop="userName" label="用户" />
<!--            <el-table-column prop="URL" label="URL" />-->
            <el-table-column label="操作" >
              <template #default="scope">
                <el-button type="primary" size="small"  @click="edit(scope.row)">编辑</el-button>
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

  <el-dialog v-model="dialogVisible"  :title=title width="500" modal-append-to-body :append-to-body="true" >
    <el-form :model="form"  >
      <el-form-item label="名称" :label-width="formLabelWidth">
        <el-input v-model="form.dsName"  />
      </el-form-item>
      <el-form-item label="类型" :label-width="formLabelWidth">
        <el-select v-model="form.dbType" placeholder="请选择类型">
          <el-option
              v-for="item in dbTypeOptions"
              :key="item"
              :label="item"
              :value="item"
          />

        </el-select>
      </el-form-item>
      <el-form-item label="host" :label-width="formLabelWidth">
        <el-input v-model="form.host"  />
      </el-form-item>
      <el-form-item label="端口" :label-width="formLabelWidth">
        <el-input v-model="form.port"  />
      </el-form-item>
      <el-form-item label="账号" :label-width="formLabelWidth">
        <el-input v-model="form.userName"  />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form.password"  />
      </el-form-item>
      <el-form-item label="数据库" :label-width="formLabelWidth">
        <el-input v-model="form.database"  />
      </el-form-item>
      <el-form-item label="URL" :label-width="formLabelWidth">
        <el-input v-model="form.linkParams"  />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">
          提交
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { reactive,ref  } from 'vue'
import {ElMessageBox,ElMessage } from "element-plus";
import { listDatasource, getDatasource,addDatasource, updateDatasource, getDBType } from "@/api/datasource";
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  dsName: '',
  dbType: [],
  host: '',
  port: '',
  database: '',
  userName: '',
  password: '',
  linkParams: '',
})
var form = ref({
  id:'',
  dsName: '',
  dbType: '',
  database: '',
  host: '',
  port: '',
  userName: '',
  password: '',
  linkParams: '',
})
// let form=toRefs(formvalue);

var title=ref('')
var tableData = ref([])
const dbTypeOptions = ref([])
const total=ref(0)

const getTypeList = () => {
  getDBType().then(response => {

    dbTypeOptions.value=response.data

  })
}
getTypeList()
const getList = () => {
  listDatasource(queryParams).then(response => {

    tableData.value=response.rows
    total.value=response.total
  })
}
getList();
// 翻页
const pageChange = (currentPage,pageSize) => {
  queryParams.pageNum=currentPage
  getList()
}
var dialogVisible = ref(false)
const dialogFormVisible = ref(false)
const formLabelWidth = 'auto'
//提交
const submitForm = () => {

  if(form.value.id==null||form.value.id=='' ){
    addDatasource(form.value).then(response => {
      dialogVisible.value = false
      ElMessage.success('添加成功')
      getList()
    })
  }else {
    updateDatasource(form.value).then(response => {
      dialogVisible.value = false
      ElMessage.success('修改成功')
      getList()
    })
  }

}


//添加按钮
const add = () => {

  dialogVisible.value = true
  title.value='添加数据源'
  form.value={
    id:'',
    dsName: '',
    dbType: '',
    database: '',
    host: '',
    port: '',
    userName: '',
    password: '',
    linkParams: '',
  }
}

const edit = (row) => {
  dialogVisible.value = true

  form.value={
    id: row.id,
    dsName: row.dsName,
    dbType: row.dbType,
    database: row.database,
    host: row.host,
    port: row.port,
    userName: row.userName,
    password: row.password,
    linkParams: row.linkParams,
  }

  title.value='修改数据源'
}
const handleClose = (done: () => void) => {
  ElMessageBox.confirm('Are you sure to close this dialog?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.pagination{
  margin-top: 10px;
  float: right;
  margin-right: 4%;
}

</style>