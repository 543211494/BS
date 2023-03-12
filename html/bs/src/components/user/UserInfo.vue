<template>
    <div class="userinfo-container">
        <div class="title">
            <h1>用户信息</h1>
        </div>
        <table class="my-table">
            <tr>
                <td><i class="el-icon-user"></i>用户姓名</td>
                <td>{{user.fullName}}</td>
            </tr>
            <tr>
                <td><i class="el-icon-male"></i>性别</td>
                <td>{{user.sex}}</td>
            </tr>
            <tr>
                <td><i class="el-icon-star-off"></i>身份证号</td>
                <td>{{user.identity}}</td>
            </tr>
            <tr>
                <td><i class="el-icon-setting"></i>用户权限</td>
                <td v-if="user.power=='user'">普通用户</td>
                <td v-else-if="user.power=='super'">一级管理员</td>
                <td v-else-if="user.power=='admin'">二级管理员</td>
                <td v-else-if="user.power=='teacher'">招生老师</td>
            </tr>
            <tr>
                <td><i class="el-icon-office-building"></i>所属单位</td>
                <td>{{user.university}}——{{user.college}}</td>
            </tr>
            <tr>
                <td><i class="el-icon-notebook-1"></i>所学专业</td>
                <td>{{user.majorName}}</td>
            </tr>
            <tr>
                <td><i class="el-icon-bank-card"></i>学工号</td>
                <td>{{user.studentId}}</td>
            </tr>
        </table>
        <div class="userinfo-bottom">
            <p>若对以上信息有疑问，请与人工客服联系</p>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            user:{}
        }
    },
    mounted(){
        let str = '{"college":"软件学院","email":"lha602@163.com","fullName":"刘智宇","id":1,"identity":"360203200203081256","majorName":"软件工程","password":"","power":"super","qualification":1,"sex":"男","studentId":"2019302654","university":"西北工业大学","year":2022}';
        this.user = JSON.parse(str);
        //console.log(this.user);
    }
}
</script>

<style scoped>
.title{
    margin-left: 1%;
    font-size: 14px;
    margin-bottom: 10px;
}
/* .userinfo-container{
    border:1px red solid;
    box-sizing:border-box;
} */
.my-table,.my-table td{
    border:1px #EBEEF5 solid;
    border-collapse: collapse;
    box-sizing:border-box;
}
.my-table{
    width: 98%;
    margin-left: 1%;
}
.my-table td{
    color: #A8AAAF;
    padding: 15px;
}
.my-table td:nth-child(1){
    width: 30%;
}
.my-table td:nth-child(12){
    width: 70%;
}
/* 底部提示 */
.userinfo-bottom{
    margin-top: 30px;
    color: #A8AAAF;
    width: 100%;
    display: flex;
    justify-content: center;
}
@media screen and (max-width: 900px){
.title{
    font-size: 8px;
    margin-bottom: 10px;
}

.my-table td{
    font-size: 5px;
    padding: 10px;
}
.my-table td:nth-child(1){
    width: 35%;
}
.my-table td:nth-child(12){
    width: 65%;
}
.userinfo-bottom{
    font-size: 8px;
}
}
</style>