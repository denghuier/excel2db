import { defineConfig,loadEnv  } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';//这个path用到了这个模块需要下载  npm install @types/node --save -dev




// https://vitejs.dev/config/
export default({command, mode}:any)=>{
  console.log(command,"command")
  console.log(mode,"mode")
  console.log(loadEnv(mode, process.cwd()))
  const env = loadEnv(mode, process.cwd(), '')
  // console.log(env)
  return defineConfig({

    define: {
      __APP_ENV__: JSON.stringify(env.APP_ENV),
      'process.env': env
    },
    plugins: [vue()],
    server: {
      host: '0.0.0.0',
      port: 80, // 将端口改为80
      headers: {
        'Cross-Origin-Opener-Policy': 'same-origin',
        'Cross-Origin-Embedder-Policy': 'require-corp'
      },
      open: true,
      proxy: {
        // detail: https://cli.vuejs.org/config/#devserver-proxy
        [env.VITE_APP_API]: {
          target: `http://localhost:8080`,
          changeOrigin: true,
          // pathRewrite: {
          //   ['^' + process.env.VITE_APP_API]: ''
          // },
          rewrite: (path) => path.replace(/^\/dev-api/, ''),
        }
      },
    },
    resolve: {
      alias: {
        "@": path.resolve("./src"), // @代替src
      },
    },
    build: {
      outDir: '../src/main/resources/static', // 将 'your-custom-path' 替换为你的目标路径
    },
    // // vite 配置
    // define: {
    //   'process.env': {}
    // },
  })
}




