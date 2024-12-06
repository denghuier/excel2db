<script setup lang="ts">
import { reactive,ref } from 'vue'
import { getAllDatasource,getTableList,getColumnList } from "@/api/datasource";
const form = reactive({
  name: '',
  dsId: '',
  dsName: '',
  dataSourceList: [],
  tableName: '',
  type: [],
  colList: []
})


const dataSourceList = ref([])

const tableNameList = ref([])


//初始化 获取数据源
getAllDatasource().then(res => {

  dataSourceList.value = res.data
})
//选择表
const getTables = () => {
  form.colList=""
  tableNameList.value=ref([])
  form.tableName=""
  getTableList(form.dsId).then(res => {
    tableNameList.value = res.data
  })
}

const getColList = () => {
  const data={"dsId":form.dsId,"tableName":form.tableName}
  getColumnList(data).then(res=>{
    form.colList = res.data
    // 提交数据
    // sendData()
  })

}


//定义一个向父组件add.vue提交数据的事件
const emits = defineEmits(['sendData'])
// 向父传递数据
const sendData=()=>{
  emits('sendData',form)
}
</script>

<template>
  <el-row >
    <el-col :span="2"></el-col>
  </el-row>
  <el-row>

    <el-col :span="2"></el-col>
    <el-col :span="20">
      <el-form :model="form"  label-width="120px">
        <el-row>
          <el-col :span="12">
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
          </el-col>
          <el-col :span="12">
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
          </el-col>
        </el-row>
              <el-form-item label="字段" prop="tableName">
                <el-table :data="form.colList" border show-header style="width: 100%">
                  <el-table-column prop="columnName" label="名称"  />
                  <el-table-column prop="columnType" label="类型"  />
                  <el-table-column prop="columnRemarks" label="备注" />
                </el-table>
              </el-form-item>

            </el-form>
    </el-col>
     <el-col :span="2">
    </el-col>
  </el-row>
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


.el-table{
  border: 1px solid #ccc;
}
</style>