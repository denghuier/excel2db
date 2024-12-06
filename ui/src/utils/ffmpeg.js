import { createFFmpeg, fetchFile } from '@ffmpeg/ffmpeg';
let ffmpeg = {};

ffmpeg.squeezVideo = async function(file, filename, filetype, width, height, msg) {
    console.log('file', file);
    console.log('filename', filename);
    console.log('filetype', filetype);
    console.log('width', width);
    console.log('height', height);

    // 分辨率
    const resolution = `${width}x${height}`;
    // 实例化ffmpeg
    const ffmpegObj = createFFmpeg({
        // ffmpeg路径
        corePath: 'ffmpeg-core.js',
        // 日志
        log: true,
        // 进度
        progress: ({ ratio }) => {
            msg.value = `完成率: ${(ratio * 100.0).toFixed(1)}%`;
        }
    })

    var { name } = file;
    // msg = '正在加载 ffmpeg-core.js'
    // 开始加载
    await ffmpegObj.load();
    // msg = '开始压缩'
    // 把文件加到ffmpeg   写文件
    ffmpegObj.FS('writeFile', name, await fetchFile(file));
    // await ffmpeg.run('-i', name, '-b', '2000000', '-fs', '4194304', '-preset medium', 'superfast', 'output.mp4')
    // 开始压缩视频
    const compressedFileSize = this.computeFileSize(file);
    console.log("After compression,this file size is " + compressedFileSize + " 字节.");
    // await ffmpegObj.run('-i', name, '-b', '2000000', '-crf', '18', '-fs', compressedFileSize, '-s', resolution, 'output.mp4');
    // await ffmpegObj.run('-i', name, '-b', '2000000', '-crf', '40', '-fs', compressedFileSize,'-vf', 'scale=240:320','-threads','4', '-preset','ultrafast', 'output.mp4');
    await ffmpegObj.run('-i', name, '-c:v', 'libx264', '-vf', 'scale=240:320', '-r', '24','-crf', '50','-threads','4', '-preset','ultrafast', 'output.mp4');
    // msg = '压缩完成'
    // 压缩所完成，   读文件  压缩后的文件名称为 output.mp4
    const data = ffmpegObj.FS('readFile', 'output.mp4');

    //把压缩后的视频进行回显
    // vedioSrc = URL.createObjectURL(
    //     new Blob([data.buffer], { type: "video/mp4" })
    // );

    // 转换bolb类型
    const blob = new Blob([data], { type: 'text/plain;charset=utf-8' });


    return new Promise((resolve, reject) => {
        const file = new window.File([blob], filename, { type: filetype });
        console.log(file.size)
        resolve(file);
    })
}

ffmpeg.computeFileSize = function(file) {
    if(!file){
        return '0';
    }
    if(file.size / 1024 / 1024 > 60){
        //30M
        return '31457280';
    }else if(file.size / 1024 / 1024 <= 60 && file.size / 1024 / 1024 > 30){
        return file.size / 2;
    }else{
        return file.size;
    }
}

// 获取上传视频的url
ffmpeg.getObjectURL = function(file) {
    let url = null;
    window.URL = window.URL || window.webkitURL;
    if (window.URL) {
        url = window.URL.createObjectURL(file);
    } else {
        url = URL.createObjectURL(file);
    }
    return url;
}

// 获取视频的宽高分辨率
ffmpeg.getVideoData = function() {
    return new Promise((resolve, reject) => {
        const videoElement = document.getElementById('video');
        videoElement.addEventListener('loadedmetadata', function () {
            resolve({
                width: this.videoWidth,
                height: this.videoHeight,
                duration: this.duration
            })
        });
    })
}

export default ffmpeg;