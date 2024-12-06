<script setup lang="ts">
import { UploadFilled  } from '@element-plus/icons-vue'
import {  Plus } from '@element-plus/icons-vue'
import { reactive,ref } from 'vue'
import {getTotalRowNum,getHeaderMap,getTransformType} from "@/api/excel"
  const excelForm = reactive({
    file: '',
    fileName: '',
    colList: [],
    sheetName: '',
    readAfterAction: '保留',
    storePath: '',
    headerNum: '',
    totalRowNum: '',
    removeEndNum: 0,
    // 读取起始行
    startRowNum: '',
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
    "sheetName":excelForm.sheetName,
  }
  getTotalRowNum(data).then(res=>{
    excelForm.totalRowNum=res.data+1
  })
}
// 获取转换规则
const excelTransformType = ref([])
getTransformType().then(res=>{
  excelTransformType.value=res.data
})



// 获取Excel列
const getExcelCol = () => {
  const data={
    "pathName":excelForm.storePath,
    "sheetName":excelForm.sheetName,
    "headRowNumber":excelForm.headerNum
  }
   getHeaderMap(data).then(res=>{
    excelForm.colList = res.data
    // 发送数据
    // sendData()
  })


}

// 单元格点击事件
const changeInput=(row,column,cell,event)=>{

  if (column.property=='colName'){
    cell.children[0].children[0].classList.remove('none')
    cell.children[0].children[1].classList.add('none')
  }
}

const addRow=()=>{
  excelForm.colList.push({
    colNum:excelForm.colList.length,
    colName:'',
    transformType:excelTransformType.value[0].name,
    iseditor:true
  })
}





//定义一个向父组件add.vue提交数据的事件
const emits = defineEmits(['sendData'])
// 向父传递数据
const sendData=()=>{
  emits('sendData',excelForm)
}
</script>

<template>
  <el-row >
    <el-col :span="2"></el-col>
    <el-col :span="20">
      <el-form :model="excelForm"  label-width="120px" >
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
        <el-row>
          <el-col :span="8">
            <el-form-item label="工作表" prop="sheetName" >
              <el-select v-model="excelForm.sheetName" placeholder="请选择"
                         @change="getTotalRow()">
                <el-option
                    v-for="item in worksheetNameList"
                    :key="item"
                    :label="item"
                    :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="总行数" prop="totalRowNum" >
              <el-input-number v-model="excelForm.totalRowNum"  />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="列序号" prop="headerNum" >
              <el-input-number v-model="excelForm.headerNum"  @change="getExcelCol"/>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="起始行" prop="startRowNum" >
              <el-input-number v-model="excelForm.startRowNum"  />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="结束行" prop="removeEndNum" >
              <el-col :span="6">
                <el-input v-model="excelForm.totalRowNum"  disabled/>
              </el-col>
              -
              <el-col :span="6">
                <el-input v-model="excelForm.removeEndNum"   />
              </el-col>

            </el-form-item>
          </el-col>

        </el-row>

              <el-form-item label="读取后操作" prop="readAfterAction">
                <el-radio-group v-model="excelForm.readAfterAction">
                  <el-radio  label="保留">保留</el-radio>
                  <el-radio  label="删除文件">删除文件</el-radio>

                </el-radio-group>
              </el-form-item>
              <el-form-item label="字段" prop="readAfterAction">

                <el-table
                    :data="excelForm.colList"
                    style="width: 100%"
                    @cell-click="changeInput"
                >
                  <el-table-column type="selection" width="55"></el-table-column>
                  <el-table-column prop="colNum" label="字段序号" >

                  </el-table-column>
                  <el-table-column prop="colName" label="中文说明">
                    <template #default="scope" >
                      <div class="none">
                        <el-input  type="text" v-model="scope.row.colName"  />
                      </div>
                      <span v-show="!scope.row.iseditor">{{scope.row.colName}}</span>
                    </template>
                  </el-table-column>

                  <el-table-column prop="transformType" label="绑定数据转换规则" >
                    <template #default="scope">
                      <el-select v-model="scope.row.transformType" >
                        <el-option
                            v-for="item in excelTransformType"
                            :key="item"
                            :label="item.name"
                            :value="item.code"
                        />
                      </el-select>
                    </template>
                  </el-table-column>
                  <el-table-column prop="configValue" label="缺省值" >
                    <template #default="scope">
                      <el-input v-model="scope.row.configValue" />
                    </template>
                  </el-table-column>
                  <el-table-column width="60px" label="" >
                    <template #default="scope">

                      <el-button type="success" v-if="scope.$index==excelForm.colList.length-1" :icon="Plus" circle @click="addRow" ></el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-form-item>
            </el-form>
    </el-col>
    <el-col :span="2">

    </el-col>
    <div style="position: fixed;right: 40px;bottom:30px;z-index: 2">
      <!--                  <el-button type="primary" @click="saveForm">保存</el-button>-->
      <el-button type="primary" @click="sendData()">下一步</el-button>
      <!--                  <el-button @click="closeForm">关闭</el-button>-->
    </div>
  </el-row>

</template>

<style scoped>
.el-row{
  margin-top: 20px;
}

.none{
  display:none;
}
.block{
  display:block;
}
</style>