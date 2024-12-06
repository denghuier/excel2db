

<template>

  <el-upload
      style="width: 100%"
      drag
      action="/dev-api/excel/video"
      :on-success="handleUploadSuccess"
      :before-upload='beforeAvatarUploadVideo'
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

<el-button @click="ffmpeg.run()">执行</el-button>
  <p>{{ msg }}</p>
  <a :href="videoUrl" download="video.mp4">下载视频</a>
  <video id="video"  autoplay='autoplay' loop controls ></video>
</template>


<script  lang="ts" setup>
import ffmpeg from '@/utils/ffmpeg'
import {ref} from "vue";
import {UploadFilled} from "@element-plus/icons-vue";
const msg=ref('')
const videoUrl=ref('')
const beforeAvatarUploadVideo =(file)=>{
  msg.value = "请稍等，过大的视频正在压缩上传中...";

  let filename = file.name;
  let filetype = file.type;

  const videoUrl = ffmpeg.getObjectURL(file);
  const video = document.getElementById('video');
  // console.log(videoUrl)
  video.src = videoUrl;
  ffmpeg.getVideoData().then((videoObj) => {
    const {width, height} = videoObj;
    const fileV =ffmpeg.squeezVideo(file, filename, filetype, width, height, msg);
    console.log(fileV)
    return fileV;
  })
}
const handleUploadSuccess =(file)=>{

}


</script>

<style scoped>

</style>