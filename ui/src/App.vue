<script setup lang="ts">

</script>

<template>
  <router-view></router-view>
  <div class="app">
    <!-- 页面内容 -->
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body, #app {
  height: 100%;
}

body {
  background-color: #dc1bb5; /* 设置底色 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.app {
  width: 100%;
  height: 100%;
}
</style>
