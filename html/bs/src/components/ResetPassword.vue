<template>
    <div>
        <!-- 顶部导航栏 -->
        <div class="nav clearfix">
            <img class="nav-img" :src="require('../assets/img/logo.jpeg')"/>
            <div class="nav-info">
                <p>基于稳定匹配的招生择校系统</p>
                <p>全国硕士研究生推免报名网站</p>
            </div>
            <div class="nav-login-btn" @click='goto("/login")'><p>登录</p></div>
        </div>
        <!-- 主体 -->
        <div class="body">
            <div class="reset">
                <el-steps :active="step">
                    <el-step title="找回密码" icon="el-icon-s-order"></el-step>
                    <el-step title="身份验证" icon="el-icon-s-custom"></el-step>
                    <el-step title="重置密码" icon="el-icon-edit"></el-step>
                </el-steps>
                <div class="reset-first" v-if='step==1'>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您的账户邮箱还在使用 ，且记得所留证件号码，可通过邮箱验证码的方式重置密码。如果您的账户绑定邮箱已不使用，请联系人工客服重置密码。</p>
                    <div class="reset-btn" @click="nextStep()">继续</div>
                </div>
                <div  class="reset-second" v-if='step==2'>
                    <el-input placeholder="请输入身份证号码" v-model="userName" prefix-icon="el-icon-user-solid"></el-input>
                    <div class="reset-btn" @click="nextStep()">获取验证码</div>
                </div>
                <div  class="reset-third" v-if='step==3'>
                    <el-input placeholder="请输入新密码" v-model="password" show-password prefix-icon="el-icon-lock"></el-input>
                    <el-input placeholder="请再次输入新密码" v-model="passwordAgain" show-password prefix-icon="el-icon-lock"></el-input>
                    <el-input placeholder="请输入验证码" v-model="code" prefix-icon="el-icon-user-solid"></el-input>
                    <div class="reset-btn" @click="goto">重置密码</div>
                </div>
            </div>
        </div>
        <!-- 底部导航栏 -->
        <Footer/>
        <!-- <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
            <el-tab-pane label="用户管理" name="first">用户管理</el-tab-pane>
            <el-tab-pane label="配置管理" name="second">配置管理</el-tab-pane>
            <el-tab-pane label="角色管理" name="third">角色管理</el-tab-pane>
            <el-tab-pane label="定时任务补偿" name="fourth">定时任务补偿</el-tab-pane>
        </el-tabs> -->
    </div>
</template>

<script>
import Footer from './Footer'
export default {
    data(){
        return{
            step:1,
            userName:'',
            password:'',
            passwordAgain:'',
            code:'',
            // activeName:''
        }
    },
    components:{
        Footer:Footer,
    },
    methods:{
        nextStep(){
            this.step++;
        },
        goto(path){
            this.$router.push({
                path:path
            })
        },
    //     handleClick(tab, event) {
    //     console.log(tab.label,tab.name);
    //   }
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
}
.nav > img,.nav>div{
    float: left;
}
.nav-login-btn{
    float: right;
    line-height: 80px;
}
.nav-login-btn:hover{
    cursor: pointer;
    color: #429DE4;
}
.nav-info{
    margin-top: 10px;
    margin-right: 50%;
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
.reset{
    padding-left: 80px;
    padding-right: 80px;
    padding-top: 40px;
    background-color: #FFFFFF;
    height: 400px;
    width: 900px;
}
/* 第一步 */
.reset-first{
    margin-top: 6%;
}
.reset-first>p{
    color: #BABABA;
}
.reset-btn{
    font-size: 20px;
    color: #FFFFFF;
    background-color: #1586DE;
    width: 50%;
    margin-left: 25%;
    margin-bottom: 15px;
    margin-top: 10%;
    line-height: 30px;
    text-align: center;
    padding-top: 7px;
    padding-bottom: 7px;
    border-radius: 2px;
}
.reset-btn:hover{
    background-color: #429DE4;
}
/* 第二步 */
.reset-second{
    margin-top: 6%;
}
.reset-second > .el-input /deep/{
    width: 50%;
    margin-left: 25%;
}
/* 第三步 */
.reset-third{
    margin-top: 6%;
}
.reset-third > .el-input /deep/{
    width: 50%;
    margin-left: 25%;
    margin-bottom: 3%;
}
.reset-third > .reset-btn{
    margin-top: 0;
}

@media screen and (max-width: 900px){
.nav-img{
    width: 50px;
    height: 50px;
}
.nav-info{
    margin-top: 20px;
    margin-right: 10%;
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
.nav-login-btn{
    line-height: 50px;
    font-size: 12px;
}
/* 主体 */
.reset{
    padding-left: 15px;
    padding-right: 15px;
    padding-top: 40px;
    height: 380px;
    width: 330px;
}
.reset-btn{
    margin-top: 10%;
    margin-left: 10%;
    width: 80%;
}
.reset-second > .el-input /deep/{
    margin-top: 10%;
    margin-left: 10%;
    margin-bottom: 10%;
    width: 80%;
}
.reset-third > .el-input /deep/{
    margin-left: 10%;
    margin-bottom: 6%;
    width: 80%;
}
}
</style>