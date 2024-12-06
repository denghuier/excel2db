<template>

  <div style="height: calc(98vh);width: 100%">
    <el-container direction="vertical">

        <el-header>
          <br>
          <el-breadcrumb :separator-icon="ArrowRight">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>数据源</el-breadcrumb-item>

          </el-breadcrumb>
        </el-header>
        <el-main>
          <el-steps  :active="active" align-center>
            <el-step title="数据"  >
            </el-step>
            <el-step title="Step 2" description="Some description" />
            <el-step title="Step 3" description="Some description" />
            <el-step title="Step 4" description="Some description" />
          </el-steps>
          <el-button style="margin-top: 12px" @click="next">Next step</el-button>
          <el-form :model="form"
              label-position="left"
              label-width="100px"
              style="width: 400px;margin-left: 50px">

            <div v-show="active == 1">
              <el-form-item label="设备名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入" />
              </el-form-item>
            </div>
            <div v-show="active == 2"></div>
            <div v-show="active == 3"></div>
            <div v-show="active == 4"></div>

          </el-form>

        </el-main>


    </el-container>
  </div>



</template>

<script  lang="ts" setup>
import { reactive } from 'vue'
import { ref} from 'vue'
import { ArrowRight } from '@element-plus/icons-vue'
const active = ref(0)
const next = () => {
  if (active.value++ > 3) active.value = 0
}

const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})
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