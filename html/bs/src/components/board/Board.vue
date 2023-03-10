<template>
  <div>
      <!-- 顶部导航栏 -->
      <div class="nav">
          <div class="nav-left">
              <div class="clearfix">
                <img class="nav-img" :src="require('../../assets/img/logo.jpeg')"/>
                <p>欢迎使用招生择校系统留言板</p>
              </div>
              <div>首页</div>
              <div>首页</div>
              <div>首页</div>
              <div>首页</div>
              <div>首页</div>
          </div>
          <div class="nav-right">
              <div>退出登录</div>
          </div>
      </div>
      <!-- 背景图 -->
      <div class="title">
          <h1>留言板 Message Board</h1>
          <p>在这里可以向系统各部门咨询您关心的问题</p>
          <p>也可以向系统各部门提出您宝贵的意见和建议，为系统建言献策</p>
          <div class="choice">
              <router-link to="/board/allMessage" class="no-active" active-class="active">全部留言</router-link>
              <router-link to="/board/myMessage" class="no-active" active-class="active">我的留言</router-link>
              <router-link to="/board/submitMessage" class="no-active" active-class="active">我要留言</router-link>
          </div>
      </div>
      <!-- 指定组件的呈现位置 -->
      <router-view></router-view>
      <!-- 底部导航栏 -->
      <Footer/>
  </div>
</template>

<script>
import Footer from '../Footer'
export default {
    data(){
        return{

        }
    },
    components:{
        Footer:Footer,
    }
}
</script>

<style scoped>
/* 顶部导航栏 */
.nav{
    display: flex;
    justify-content:space-between;
    padding-top: 10px;
    padding-bottom: 10px;
    padding-left: 40px;
    padding-right: 40px;
}
.nav-img{
    width: 30px;
    height: 30px;
}
.nav-left{
    display: flex;
    justify-content:space-between;
    width: 50%;
}
.nav-left>div:nth-child(1) *{
    float: left;
}
.nav-right > div{
    cursor: pointer;
    line-height: 30px;
}
.nav-left > div:not(:first-child){
    cursor: pointer;
    line-height: 30px;
}
.nav-right > div:hover,.nav-left > div:not(:first-child):hover{
    color: #429DE4;
}
/* 背景图 */
.title{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    height: 300px;
    background-image: url('../../assets/img/board.jpg');
    image-rendering: -webkit-optimize-contrast;
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
.title > h1{
    color: #A72E38;
}
.title > p{
    display: block;
    color: #7F7E7C;
}
.choice{
    display: flex;
    justify-content: space-between;
    width: 40%;
    margin-top: 20px;
}
.choice > a{
    border: #A72E38 1px solid;
    padding-left: 20px;
    padding-right: 20px;
    padding-bottom: 10px;
    padding-top: 10px;
    text-decoration:none;
    border-radius: 5px;
}
.no-active{
    color: #A72E38;
}
.no-active:hover,.active{
    color: #FFFFFF;
    background-color: #A72E38;
}
@media screen and (max-width: 900px){
.nav{
    padding-left: 5px;
    padding-right: 5px;
}
.nav-left{
    width: 80%;
}
.nav-left p{
    display: none;
}
.title{
    height: 200px;
}
.title > h1{
    font-size: 18px;
}
.title > p{
    font-size: 10px;
}
.choice{
    width: 90%;
    font-size: 10px;
}
}
</style>