<template>
    <div class="body">
        <div class="board">
            <div class="tap" v-if="comments.length==0"><p>暂无留言</p></div>
            <!-- 一条留言及其回复 -->
            <div>
                <div class="message">
                    <div>
                        <img class="message-img" :src="require('../../assets/img/user.png')"/>
                    </div>
                    <div class="main">
                        <p>xxx留言:</p>
                        <p>您好！在这里可以向系统各部门咨询您关心的问题,也可以向系统各部门提出您宝贵的意见和建议，为系统建言献策。</p>
                        <p>2022-07-06 15:11:42</p>
                        <div class="message-opertor">
                            <p  @click="deleteReply()">删除</p>
                            <p @click="reply()">回复</p>
                        </div>
                        <div class="reply-board">
                            <!-- 一条回复 -->
                            <div class="reply">
                                <p>管理员xxx回复：</p>
                                <p>同学你好！学生宿舍自习室（活动室）由学生处管理，请你向学生处咨询。</p>
                                <p>2022-07-07 15:11:42</p>
                                <div class="message-opertor">
                                    <p @click="deleteReply()">删除</p>
                                </div>
                            </div>
                            <!-- 一条回复 -->
                            <div class="reply">
                                <p>管理员xxx回复：</p>
                                <p>同学你好！学生宿舍自习室（活动室）由学生处管理，请你向学生处咨询。</p>
                                <p>2022-07-07 15:11:42</p>
                                <div class="message-opertor">
                                    <p  @click="deleteReply()">删除</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="line"></div>
            </div>

            <!-- 一条留言及其回复 -->
            <div>
                <div class="message">
                    <div>
                        <img class="message-img" :src="require('../../assets/img/user.png')"/>
                    </div>
                    <div class="main">
                        <p>xxx留言:</p>
                        <p>您好！在这里可以向系统各部门咨询您关心的问题,也可以向系统各部门提出您宝贵的意见和建议，为系统建言献策。</p>
                        <p>2022-07-06 15:11:42</p>
                        <div class="message-opertor">
                            <p @click="deleteReply()">删除</p>
                            <p  @click="reply()">回复</p>
                        </div>
                        <div class="reply-board">
                            <div class="reply">
                                <p>管理员xxx回复：</p>
                                <p>同学你好！学生宿舍自习室（活动室）由学生处管理，请你向学生处咨询。</p>
                                <p>2022-07-07 15:11:42</p>
                                <div class="message-opertor">
                                    <p  @click="deleteReply()">删除</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="line"></div>
            </div>
            <!-- 底部分页 -->
            <div class="page">
                <el-pagination
                    layout="prev, pager, next"
                    :pager-count="5"
                    @current-change="handleCurrentChange"
                    :total="100">
                </el-pagination>
            </div>
        </div>
        <div class="choice">
            <el-input
                size="medium"
                placeholder="搜索留言"
                suffix-icon="el-icon-search"
                v-model="key">
            </el-input>
            <div class="search-btn">搜索</div>
        </div>
        <!-- 回复评论 -->
        <el-dialog
            title="回复评论"
            center
            width="70%"
            :visible.sync="dialogVisible"
            :before-close="handleClose"
            :lock-scroll="false">
            <div class="reply-dialog">
                <el-input
                    type="textarea"
                    :autosize="{ minRows: 3, maxRows: 5}"
                    maxlength="400"
                    show-word-limit
                    placeholder="请输入回复内容"
                    v-model="replyText">
                </el-input>
                <div class="dialog-btn-container">
                    <div class="dialog-btn" @click="dialogVisible=false">取消</div>
                    <div class="dialog-btn">提交</div>
                </div>

            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return{
            key:'',
            dialogVisible:false,
            replyText:'',
            comments:[1,2,3],
        }
    },
    methods:{
        handleCurrentChange(val){
            console.log('当前页面:',val)
        },
        handleClose(done){
            done();
        },
        reply(){
            this.dialogVisible = true;
        },
        deleteReply(){

        }
    }
}
</script>

<style scoped>
.body{
    display: flex;
    width: 80%;
    margin-left: 10%;
}
.tap{
    margin-top: 20px;
    margin-bottom: 20px;
    display: flex;
    width: 80%;
    height: 500px;
    margin-left: 10%;
    justify-content: center;
    color: #59595A;
}
/* 左侧搜索栏 */
.choice{
    width: 20%;
    display: flex;
    flex-direction:column;
    background-color: #F1F1F1;
}
.choice > .el-input,.search-btn{
    width: 80%;
    margin-left: 10%;
}
.choice > .el-input{
    margin-top: 30px;
    margin-bottom: 20px;
}
.search-btn{
    cursor: pointer;
    color: #FFFFFF;
    background-color: #A72E38;
    text-align: center;
    padding-top: 7px;
    padding-bottom: 7px;
    border-radius: 2px;
}
.search-btn:hover{
    background-color: #7D1A21;
}
/* 右侧留言板 */
.board{
    width: 80%;
}
.message{
    display: flex;
    width: 90%;
    margin-left: 5%;
    margin-top: 10px;
}
.message p{
    color: #59595A;
}
.main >p:nth-child(1),.main >p:nth-child(2){
    margin-bottom: 20px;
}
.message-img{
    width: 50px;
    height: 50px;
    margin: 10px;
}
.message-opertor{
    display: flex;
    flex-direction:row-reverse;
}
.message-opertor > p{
    cursor: pointer;
    color: #59595A;
    margin-right: 20px;
}
.message-opertor > p:hover{
    color: #429DE4;
}
/* 回复 */
.reply-board{
    background-color: #F1F1F1;
    border-radius: 6px;
    padding: 20px;
}
.reply >p:nth-child(1),.reply >p:nth-child(2){
    margin-bottom: 10px;
}
.line{
    height: 1px;
    background-color: #F5F5F5;
    width: 90%;
    margin-left: 5%;
    margin-top: 20px;
}
/* 底部分页 */
.page{
    display: flex;
    justify-content: center;
}
/* 对话框 */
.dialog-btn-container{
    display: flex;
    flex-direction:row-reverse;
    margin-top: 20px;
}
.dialog-btn{
    cursor: pointer;
    border-radius: 3px;
    padding-top: 6px;
    padding-bottom: 6px;
    padding-left: 20px;
    padding-right: 20px;
    margin-left: 20px;
}
.dialog-btn:nth-child(2){
    color: #FFFFFF;
    border: #A72E38 1px solid;
    background-color: #A72E38;
}
.dialog-btn:nth-child(2):hover{
    background-color: #7D1A21;
}
.dialog-btn:nth-child(1){
    color: #A72E38;
    border: #A72E38 1px solid;
}
.dialog-btn:nth-child(1):hover{
    background-color: #DDDDDD;
}
@media screen and (max-width: 900px){
.body{
    width: 96%;
    margin-left: 2%;
    flex-direction: column-reverse;
}

.choice{
    width: 100%;
    margin-top: 10px;
    flex-direction:row;
    background-color: #FFFFFF;
}
.choice > .el-input{
    width: 70%;
    margin-left: 5%;
    margin-right: 5%;
}
.search-btn{
    width: 20%;
}
.choice > .el-input{
    margin-top: 0;
    margin-bottom: 0;
}
.search-btn{
    font-size: 14px;
}
.search-btn:hover{
    background-color: #7D1A21;
}
.board{
    width: 100%;
}
.main >p:nth-child(1),.main >p:nth-child(2){
    margin-bottom: 5px;
}
.message p{
    font-size: 10px;
}
.message,.line{
    width: 100%;
    margin-left: 0;
}
.line{
    margin-top: 8px;
}
.reply-board{
    border-radius: 4px;
    padding: 5px;
}
}
</style>