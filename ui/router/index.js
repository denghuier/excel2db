import Home from "../src/components/Home.vue";
import { createRouter, createWebHistory } from "vue-router"

const routes=[
    {
        path:"/",
        name:"home",
        component:Home,
        redirect:"index",
        children:[
            {
                path:"/index",
                name:"index",
                component:()=>import("../src/views/index.vue")
            },
            {
                path:"/task",
                name:"task",
                component:()=>import("../src/views/task/index.vue")

            },
            {
                path:"/taskAdd",
                name:"taskAdd",
                component:()=>import("../src/views/task/Add.vue")
            },
            {
                path:"/datasource",
                name:"datasource",
                component:()=>import("../src/views/datasource/index.vue")
            },
        ]
    },

    {
        path:"/test",
        name:"test",
        component:()=>import("../src/views/task/Submit.vue")
    },


    {
        path:"/up",
        name:"up",
        component:()=>import("../src/components/ffmpeg/up.vue")
    },

];
const router=createRouter({
    history:createWebHistory(),
    routes
})

export default router;