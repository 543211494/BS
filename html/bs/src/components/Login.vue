<template>
    <div>
        <!-- 顶部导航栏 -->
        <div class="nav clearfix">
            <img class="nav-img" :src="require('../assets/img/logo.jpeg')"/>
            <div class="nav-info">
                <p>基于稳定匹配的招生择校系统</p>
                <p>全国硕士研究生推免报名网站</p>
            </div>
        </div>
        <!-- 主体 -->
        <div class="body">
            <div class="login">
                <h1>欢迎登录！</h1>
                <el-input placeholder="请输入帐号" v-model="userName" prefix-icon="el-icon-user-solid"></el-input>
                <el-input placeholder="请输入密码" v-model="password" show-password prefix-icon="el-icon-lock"></el-input>
                <div class="login-btn">登录</div>
                <p @click='goto("/resetPassword")'>找回密码</p>
                <div class="line"></div>
            </div>
        </div>
        <!-- 底部导航栏 -->
        <Footer/>
    </div>
</template>

<script>
import Footer from './Footer'
export default {
    data(){
        return{
            userName:'',
            password:''
        }
    },
    components:{
        Footer:Footer,
    },
    methods:{
        goto(path){
            this.$router.push({
                path:path
            })
        }
    }
}
</script>

<style scoped>
/* 顶部导航栏 */
.nav-img{
    width: 80px;
    height: 80px;
}
.nav{
    padding: 10px;
    background-color: #FFFFFF;
    /* border:1px red solid;
    box-sizing:border-box; */
}
.nav > img,.nav>div{
    float: left;
}
.nav-info{
    margin-top: 10px;
}
.nav-info >p:nth-child(1){
    color: #434343;
    font-size: 27px;
}
.nav-info >p:nth-child(2){
    color: #BABABA;
    letter-spacing:12px;
}
/* 主体 */
.body{
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #F0F2F4;
    height: 520px;
}
.login{
    background-color: #FFFFFF;
    height: 360px;
    width: 500px;
}
.login >h1{
    margin-top: 5%;
    margin-bottom: 5%;
    text-align: center;
}
.login >p{
    font-size: 14px;
    cursor:pointer;
    color: #1586DE;
    margin-left: 15%;
    margin-bottom: 15px;
}
.line{
    height: 1px;
    background-color: #E6E6E6;
    width: 70%;
    margin-left: 15%;
}
.login > .el-input /deep/ {
    width: 70%;
    margin-left: 15%;
    margin-bottom: 20px;
}

.login-btn{
    font-size: 20px;
    color: #FFFFFF;
    background-color: #1586DE;
    width: 70%;
    margin-left: 15%;
    margin-bottom: 15px;
    line-height: 30px;
    text-align: center;
    padding-top: 7px;
    padding-bottom: 7px;
    border-radius: 2px;
}
.login-btn:hover{
    background-color: #429DE4;
}
@media screen and (max-width: 900px){
.nav-img{
    width: 50px;
    height: 50px;
}
.nav-info{
    margin-top: 20px;
}
.nav-info{
    margin-top: 2px;
}
.nav-info >p:nth-child(1){
    font-size: 15px;
}
.nav-info >p:nth-child(2){
    font-size: 10px;
    letter-spacing:3px;
}
.login{
    width: 340px;
}
}
</style>