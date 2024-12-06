<script setup lang="ts">
import { CaretBottom, Fold } from '@element-plus/icons-vue'
import { ref } from 'vue'
const userName = 'admin'
const subMenuVisible = ref(false)
const toggleSubMenu = () => {
  subMenuVisible.value = !subMenuVisible.value
}
const userInfo = ref({})


</script>

<template>

  <div class="full-screen-bg" >
    <div class="common-layout" style="height: 100%;">
      <el-container class="layout-container">
        <el-aside class="aside" >
          <div class="asideflex">
            <div style="width: 100%;margin-top: 20px;">
              <img src="@/assets/excel.png" alt="logo" style="width: 30px;height: 30px;margin-left: 20px">
              <span style="font-size: 20px;margin-left: 10px">excel传输</span>
            </div>
            <el-scrollbar style="margin-top: 30px">
              <el-menu class="menu" router>
                <el-menu-item index="index">

                  <span slot="title">首页</span>
                </el-menu-item>
                <el-sub-menu index="datasource">
                  <template #title>数据源</template>
                  <el-menu-item index="datasource">
                    <i class="el-icon-s-data"></i>
                    <span slot="title">数据源列表</span>
                  </el-menu-item>
                </el-sub-menu>
                <el-sub-menu index="task">
                  <template #title>任务管理</template>
                  <el-menu-item index="task">
                    <i class="el-icon-s-data"></i>
                    <span slot="title">任务列表</span>
                  </el-menu-item>
                </el-sub-menu>
              </el-menu>
            </el-scrollbar>
          </div>



        </el-aside>
        <el-container>
          <el-header class="header" >
            <div class="logo">
              <!-- 放置图标 -->
              <el-icon><Fold /></el-icon>
            </div>
            <div class="user-info" @click="toggleSubMenu" style="float: right">
              {{ userName }}
              <el-dropdown v-model="subMenuVisible">
                <el-icon><CaretBottom /></el-icon>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item>个人中心</el-dropdown-item>
                    <el-dropdown-item divided>退出登录</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>

            </div>
          </el-header>
          <el-main>
            <router-view/>
          </el-main>

        </el-container>
      </el-container>
    </div>

  </div>



</template>

<style >

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

/* 确保页面高度为100%，并且不出现滚动条 */
html, body, #app {
  height: 100%;
  overflow: hidden; /* 禁止滚动条 */
}

.full-screen-bg {
  width: 100%;
  height: 100%;
  background-color: #f4f4f4; /* 设置底色 */
  /*display: flex;*/
  justify-content: center;
  align-items: center;
}


.common-layout{
  height: 100%;
  display: flex;
  width: 100%;
}

.menu {
  border-right: none;
}
.layout-container .el-main {
  padding: 0.5%;
 font-size:0;
}
.aside{
  background-color: #ffffff;
  width: 200px;
  height: 100%;
  color: var(--el-text-color-primary);
  box-shadow: 0 15px 12px 12px rgba(0, 0, 0, 0.1);
  z-index: 9;
}

.asideflex{
  display: flex;
  flex-direction: column;
}

.header{
  background-color: #ffffff;
  box-shadow: 0 0 4px 4px rgba(0, 0, 0, 0.1);
  z-index: 5;
}

</style>
