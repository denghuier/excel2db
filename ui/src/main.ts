import { createApp } from 'vue'
//去除后，可以使用element-plus container可自适应
// import './style.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 引入element-plus
import ElementPlus from "element-plus"
import "element-plus/dist/index.css"


import App from './App.vue'
import router from '../router/index'
// createApp(App).mount('#app')

const app = createApp(App)
app.use(router)
// 使用element-plus
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')