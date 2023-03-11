<template>
        <!-- 导航栏 -->
        <div class="nav">
            <div class="nav-logo">
                <img class="nav-img" :src="require('../assets/img/logo.jpeg')"/>
                <div class="nav-info">
                    <p>基于稳定匹配的招生择校系统</p>
                    <p>全国硕士研究生推免报名网站</p>
                </div>
            </div>
            <div class="nav-choice">
                <div class="nav-choice-left">
                    <div>首页</div>
                    <div>专业查询</div>
                    <div>录取查询</div>
                    <div>推免报名</div>
                    <div>留言板</div>
                </div>
                <div class="nav-choice-right">
                    <div v-if="hasLogin">退出登录</div>
                    <div v-else>登录</div>
                </div>
            </div>
        </div>
</template>

<script>
export default {
    data(){
        return{
            hasLogin:true,
        }
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
/* 导航栏 */
.nav{
    display: flex;
    width: 100%;
}
.user-container-right{
    border:1px red solid;
    box-sizing:border-box;
}
/* .nav *{
    border:1px red solid;
    box-sizing:border-box;
} */
/* logo */
.nav-logo{
    padding: 5px;
    display: flex;
    align-items:center;
}
.nav-img{
    width: 60px;
    height: 60px;
}
.nav-info{
    justify-content: center;
}
.nav-info >p:nth-child(1){
    color: #434343;
    font-size: 22px;
}
.nav-info >p:nth-child(2){
    color: #BABABA;
    letter-spacing:6px;
}
/* 跳转网址 */
.nav-choice{
    width: 60%;
    display: flex;
    justify-content:space-between;
}
.nav-choice-left{
    display: flex;
    justify-content: space-around;
    line-height: 70px;
}
.nav-choice-left > div,.nav-choice-right > div{
    color: #7B7A7A;
    cursor: pointer;
    padding-left: 10px;
    padding-right: 10px;
}
.nav-choice-left > div:hover,.nav-choice-right > div:hover{
    background: #E8E7E3;
}
.nav-choice-right{
    display: flex;
    line-height: 70px;
}
@media screen and (max-width: 900px){
.nav-info{
    display: none;
}
.nav-img{
    width: 30px;
    height: 30px;
}
.nav-choice-left,.nav-choice-right{
    line-height: 40px;
}
.nav-choice-left > div,.nav-choice-right > div{
    font-size: 10px;
    padding-left: 4px;
    padding-right: 4px;
}
.nav-choice{
    width: 85%;
}
}
</style>