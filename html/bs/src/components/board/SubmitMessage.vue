<template>
    <div class="body">
        <div class="container">
            <div class="header"><p>新建留言</p></div>
            <div class="form">
                <div>
                    <el-select v-model="value" placeholder="是否匿名">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </div>
                <div>
                    <el-input
                        type="textarea"
                        :autosize="{ minRows: 8, maxRows: 8}"
                        maxlength="400"
                        show-word-limit
                        placeholder="请输入留言内容"
                        v-model="content">
                    </el-input>
                </div>
            </div>
            <div class="btn-container">
                <div class="btn">保存</div>
                <div class="btn">提交</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            options:[{
                value:1,
                label:'实名发布'
            },{
                value:2,
                label:'匿名发布'
            }],
            value:1,
            content:'',
        }
    }
}
</script>

<style scoped>
.body{
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 520px;
}
.container{
    width: 60%;
    background-color: #F5F5F5;
    border:1px #DDDDDD solid;
    box-sizing:border-box;
    border-radius: 3px;
}
/* 标题 */
.header{
    padding-left: 30px;
    padding-top: 15px;
    padding-bottom: 15px;
}
.header > p{
    color: #61171E;
    font-size: 20px;
}
/* 表单 */
.form{
    background-color: #FFFFFF;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
.form > div{
    width: 80%;
    margin-bottom: 40px;
}
.form >div:nth-child(1){
    margin-top: 40px;
}
/* 底部按钮 */
.btn-container{
    padding-top: 15px;
    padding-bottom: 15px;
    display: flex;
    justify-content: space-around;
}
.btn{
    color: #FFFFFF;
    cursor: pointer;
    border-radius: 3px;
    padding-top: 6px;
    padding-bottom: 6px;
    padding-left: 20px;
    padding-right: 20px;
    border: #A72E38 1px solid;
    background-color: #A72E38;
}
.btn:hover{
    background-color: #7D1A21;
}
@media screen and (max-width: 900px){
.body{
    height: 500px;
}
.body p,.btn{
    font-size: 10px;
}
.container{
    width: 90%;
}
.header{
    padding-left: 20px;
    padding-top: 10px;
    padding-bottom: 10px;
}
}
</style>