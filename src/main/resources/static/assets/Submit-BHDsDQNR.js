import{d as g,r as n,o as k,a as x,l as h,f as a,w as p,u as N,b as r,e as y,i as C,g as I,_ as S}from"./index-DUUratVh.js";import{s as T}from"./task-DEoRwVrV.js";import"./request-B8tej4z5.js";const B={class:"main"},E=g({__name:"Submit",props:{form:{type:Object,default:null}},setup(m){const f=N(),s=m,c=s.form.taskId,l=s.form.totalRowNum-s.form.removeEndNum,o=n(0),u=n("warning"),i=n(0);k(()=>{_(),d()});const d=()=>{let t=x({taskId:c});T(t).then(e=>{e.code==200})},_=()=>{if(typeof WebSocket>"u")alert("您的浏览器不支持socket");else{const t=new WebSocket("ws://localhost:8080/notice/"+c);t.onerror=function(){console.log("ws连接发生错误")},t.onopen=function(){console.log("ws连接成功")},t.onmessage=function(e){o.value=e.data,i.value=Math.round(o.value/l*100),o.value==l&&(u.value="success")},t.onclose=function(){console.log("ws连接关闭")}}};return(t,e)=>{const v=r("el-progress"),b=r("el-button"),w=r("el-result");return y(),h("div",B,[a(v,{percentage:i.value,"stroke-width":25,striped:"","striped-flow":""},null,8,["percentage"]),a(w,{icon:u.value,title:"传输数量","sub-title":o.value},{extra:p(()=>[a(b,{type:"primary",onClick:e[0]||(e[0]=M=>C(f).push({path:"/task"}))},{default:p(()=>e[1]||(e[1]=[I("返回")])),_:1})]),_:1},8,["icon","sub-title"])])}}}),j=S(E,[["__scopeId","data-v-0b666c56"]]);export{j as default};