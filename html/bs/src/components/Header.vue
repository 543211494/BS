<template>
    <!-- 顶部导航栏 -->
    <div class="nav">
        <div class="nav-left">
            <div class="clearfix">
            <img class="nav-img" :src="require('../assets/img/logo.jpeg')"/>
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
</template>

<script>
export default {

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
}
</style>