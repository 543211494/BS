<template>
    <div class="resetInfo-container">
        <div class="reset-email">
            <div class="reset-title">
                <p>修改绑定邮箱</p>
                <div class="line"></div>
            </div>
            <div class="reset-body">
                <img class="reset-img" :src="require('../../assets/img/resetEmail.png')"/>
                <div class="reset-operator">
                    <p><i class="el-icon-message"></i>当前绑定的邮箱为：lha602@163.com</p>
                    <p @click="showEmail=true">修改</p>
                </div>
            </div>
        </div>

        <div class="reset-password">
            <div class="reset-title">
                <p>修改密码</p>
                <div class="line"></div>
            </div>
            <div class="reset-body">
                <img class="reset-img" :src="require('../../assets/img/resetPassword.png')"/>
                <div class="reset-operator">
                    <p><i class="el-icon-lock"></i>建议您定期更换密码.密码必须是8位以上,包含数字、字母、特殊符号</p>
                    <p @click="showPassword=true">修改</p>
                </div>
            </div>
        </div>
        <el-dialog
            customClass="reset-dialog"
            title="修改邮箱"
            center
            :visible.sync="showEmail"
            :before-close="handleClose"
            :lock-scroll="false">
            <div class="form">
                <el-input
                    placeholder="请输入新邮箱"
                    prefix-icon="el-icon-search"
                    v-model="newEmail">
                </el-input>
                <div class="form-btn">
                    <div>提交</div>
                    <div @click="showEmail=!showEmail">取消</div>
                </div>
            </div>
        </el-dialog>
        <el-dialog
            customClass="reset-dialog"
            title="修改密码"
            center
            :visible.sync="showPassword"
            :before-close="handleClose"
            :lock-scroll="false">
            <div class="form">
                <el-input
                    placeholder="请输入原密码"
                    prefix-icon="el-icon-lock"
                    v-model="oldPassword">
                </el-input>
                <el-input
                    placeholder="请输入新密码"
                    prefix-icon="el-icon-lock"
                    v-model="newPassword">
                </el-input>
                <el-input
                    placeholder="请再次输入新密码"
                    prefix-icon="el-icon-lock"
                    v-model="passwordAgain">
                </el-input>
                <div class="form-btn">
                    <div>提交</div>
                    <div @click="showPassword=!showPassword">取消</div>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return{
            showEmail:false,
            showPassword:false,
            newEmail:'',
            oldPassword:'',
            newPassword:'',
            passwordAgain:'',
        }
    },
    methods:{
        handleClose(done){
            done();
        },
    }
}
</script>
<style scoped>
.reset-email,.reset-password{
    width: 96%;
    margin-left: 2%;
    margin-top: 20px;
}
.reset-title{
    display: flex;
    align-items:center;
    width: 100%;
    /* border:1px red solid;
    box-sizing:border-box; */
}
.reset-title > p{
    color: #656565;
    text-align: center;
    width: 15%;
}
.line{
    height: 2px;
    width: 85%;
    background-color: #E6E6E6;
}
.reset-body{
    width: 94%;
    margin-left: 3%;
    display: flex;
    align-items:center;
    margin-top: 20px;
    margin-bottom: 20px;
}
.reset-img{
    width: 80px;
    height: 80px;
    border-radius: 40px;
}
.reset-operator{
    width: 80%;
    margin-left: 5%;
    color: #656565;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.reset-operator >p:nth-child(2){
    color: #429DE4;
    cursor: pointer;
}
/* 对话框 */
.form{
    width: 80%;
    margin-left: 10%;
}
.form-btn{
    display: flex;
    justify-content: space-around;
    margin-top: 20px;
}
.form-btn > div{
    cursor: pointer;
    border-radius: 3px;
    padding-top: 6px;
    padding-bottom: 6px;
    padding-left: 20px;
    padding-right: 20px;
}
.form-btn > div:nth-child(1){
    color: #FFFFFF;
    border: #429DE4 1px solid;
    background-color: #429DE4;
}
.form-btn > div:nth-child(1):hover{
    background-color: #1586DE;
}
.form-btn > div:nth-child(2){
    color: #429DE4;
    border: #429DE4 1px solid;
}
.form-btn > div:nth-child(2):hover{
    color: #FFFFFF;
    background-color: #429DE4;
}
.form .el-input /deep/{
    margin-bottom: 20px;
}
@media screen and (max-width: 900px){
.reset-title > p{
    font-size: 10px;
    width: 30%;
}
.line{
    width: 70%;
}
.reset-img{
    width: 40px;
    height: 40px;
    border-radius: 20px;
}
.reset-operator{
    font-size: 10px;
}
.reset-operator > p:nth-child(1){
    width: 80%;
}
.reset-operator > p:nth-child(2){
    width: 20%;
}
/* .reset-dialog /deep/{
    width:90%;
    margin-left: 5%;
} */
.form{
    width: 100%;
    margin-left: 0;
}
.form .el-input /deep/{
    margin-bottom: 10px;
}
}
</style>

<style>
.reset-dialog{
    width:40%;
    margin-left: 30%;
}
@media screen and (max-width: 900px){
.reset-dialog{
    width:90%;
    margin-left: 5%;
}
}
</style>