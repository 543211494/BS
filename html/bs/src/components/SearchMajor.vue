<template>
    <div>
        <!-- 导航栏 -->
        <Header/>
        <div class="container">
            <div class="title">
                <div class="title-info">
                    <p>硕士招生专业目录</p>
                    <p>用户可查询{{year}}年硕士招生专业相关信息</p>
                </div>
            </div>
            <div class="search-container">
                <div class="search-title">
                    <p>2023年硕士专业目录查询</p>
                    <p>用户可根据条件筛选查询</p>
                </div>
                <div class="search-choice">
                    <div class="search-choice-input">
                        <el-select v-model="university" clearable placeholder="招生单位">
                            <el-option
                            v-for="item in universities" :key="item" :label="item" :value="item">
                            </el-option>
                        </el-select>
                        <el-select v-model="college" clearable placeholder="招生院系">
                            <el-option
                            v-for="item in colleges" :key="item" :label="item" :value="item">
                            </el-option>
                        </el-select>
                        <el-select v-model="majorName" clearable placeholder="专业名称">
                            <el-option v-for="item in majorNames" :key="item" :label="item" :value="item">
                            </el-option>
                        </el-select>
                    </div>
                    <div class="search-choice-btn" @click="search">查询</div>
                </div>
                <div class="my-table">
                    <div class="my-table-title">
                        <div>招生单位</div>
                        <div>招生专业</div>
                        <div>招生人数</div>
                        <div>学科评级</div>
                    </div>
                    <div v-if="majors.length==0" class="empty">未查询到符合要求的数据</div>
                    <div class="my-table-row" v-for="item in majors" :key="item.id">
                        <div>{{item.university}}--{{item.college}}</div>
                        <div>({{item.code}}){{item.majorName}}</div>
                        <div>20</div>
                        <div>{{item.level}}</div>
                    </div>
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
        </div>
        <Footer/>
    </div>
</template>

<script>
import Footer from './Footer'
import Header from './Header.vue'
export default {
    data(){
        return{
            year:2023,
            majors:[],
            universities:null,
            university:'',
            college:'',
            colleges:null,
            majorNames:null,
            majorName:'',
            save:{}
        }
    },
    components:{
        Footer:Footer,
        Header:Header,
    },
    methods:{
        goto(path){
            this.$router.push({
                path:path
            })
        },
        search(){
            console.log(this.university,this.college,this.majorName);
        },
        handleCurrentChange(val){
            console.log('当前页面:',val)
        },
    },
    mounted(){
        let str='[{"code":"083500","college":"软件学院","id":1,"level":"B+","majorName":"软件工程","university":"西安工业大学"},{"code":"0854","college":"软件学院","id":2,"level":"B+","majorName":"电子信息","university":"西北工业大学"},{"code":"080100","college":"航空学院","id":3,"level":"B+","majorName":"力学","university":"西北工业大学"},{"code":"082500","college":"航空学院","id":4,"level":"A+","majorName":"航空宇航科学与技术","university":"西北工业大学"},{"code":"085406","college":"航空学院","id":5,"level":"B+","majorName":"控制工程","university":"西北工业大学"},{"code":"070206","college":"航海学院","id":6,"level":"B+","majorName":"声学","university":"西北工业大学"},{"code":"0854","college":"软件学院","id":7,"level":"B+","majorName":"电子信息","university":"西安电子科技大学"}]';
        this.majors = JSON.parse(str);
        this.universities = new Set();
        this.colleges = new Set();
        this.majorNames = new Set();
        this.majors.forEach((value,index,array) => {
            this.universities.add(value.university);
            this.colleges.add(value.college);
            this.majorNames.add(value.majorName);

        });
        console.log(this.majors);
    }
}
</script>

<style scoped>
/* 标题 */ 
.title{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    background-color: #1486DE;
    width: 100%;
    height: 150px;
    background-image: url('../assets/img/major-header.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
.title-info{
    color: #FFFFFF;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.title-info > p:nth-child(1){
    font-size: 36px;
    font-weight:bold;
}
.title-info > p:nth-child(2){
    font-size: 20px;
}
/* 主体 */
.search-container{
    display: flex;
    flex-direction: column;
    width: 90%;
    margin-left: 5%;
}
.search-title{
    margin-top: 20px;
    display: flex;
    align-items:flex-end;
}
.search-title > p:nth-child(1){
    color: #333333;
    font-size: 24px;
}
.search-title > p:nth-child(2){
    margin-left: 1%;
    color: #CACBCA;
    font-size: 16px;
}
/* 查询框 */
.search-choice{
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.search-choice-input{
    display: flex;
    justify-content: space-between;
    width: 80%;
}
.search-choice-input > .el-select /deep/{
    width: 20%;
}
.search-choice-btn{
    cursor: pointer;
    font-size: 16px;
    color: #FFFFFF;
    background-color: #1586DE;
    width: 8%;
    margin-left: 6%;
    line-height: 100%;
    text-align: center;
    padding-top: 10px;
    padding-bottom: 10px;
    border-radius: 2px;
}
.search-choice-btn:hover{
    background-color: #429DE4;
}
/* 表格 */
.my-table{
    margin-top: 30px;
}
.my-table-title{
    display: flex;
    justify-content: space-between;
    border:1px #E9E9E9 solid;
    box-sizing:border-box;
}
.my-table-title > div{
    padding: 10px;
    color: #333333;
    font-weight:600;
}
.my-table-title > div:nth-child(1),.my-table-title > div:nth-child(2){
    width: 35%;
}
.my-table-title > div:nth-child(3),.my-table-title > div:nth-child(4){
    width: 15%;
}
.my-table-row{
    display: flex;
    justify-content: space-between;
    border-left:1px #E9E9E9 solid;
    border-right:1px #E9E9E9 solid;
    border-bottom:1px #E9E9E9 solid;
    box-sizing:border-box;
}
.my-table-row > div{
    color: #666666;
    padding: 10px;
}
.my-table-row > div:nth-child(1),.my-table-row > div:nth-child(2){
    width: 35%;
}
.my-table-row > div:nth-child(3),.my-table-row > div:nth-child(4){
    width: 15%;
}
.page{
    display: flex;
    justify-content: center;
    margin-top: 20px;
    margin-bottom: 20px;
}
.empty{
    color: #666666;
    height: 300px;
    text-align: center;
    line-height: 300px;
}
@media screen and (max-width: 900px){
.title{
    height: 100px;
}
.title-info > p:nth-child(1){
    font-size: 18px;
}
.title-info > p:nth-child(2){
    font-size: 10px;
}
/* 主体 */
.search-title{
    margin-top: 10px;
}
.search-container{
    width: 96%;
    margin-left: 2%;
}
.search-title > p:nth-child(1){
    font-size: 16px;
}
.search-title > p:nth-child(2){
    font-size: 10px;
}
/* 查询框 */ 
.search-choice{
    margin-top: 10px;
    flex-direction: column;
}
.search-choice-input{
    width: 100%;
}
.search-choice-input > .el-select /deep/{
    font-size: 10px;
    width: 32%;
}
.search-choice-btn{
    font-size: 15px;
    width: 100%;
    margin-top: 10px;
    margin-left: 0px;
    text-align: center;
    border-radius: 3px;
}
/* 表格 */
.my-table{
    margin-top: 20px;
}
.my-table-title > div{
    font-size: 10px;
}
.my-table-title > div:nth-child(1),.my-table-title > div:nth-child(2){
    width: 30%;
}
.my-table-title > div:nth-child(3),.my-table-title > div:nth-child(4){
    width: 20%;
}
.my-table-row > div{
    font-size: 10px;
}
.my-table-row > div:nth-child(1),.my-table-row > div:nth-child(2){
    width: 30%;
}
.my-table-row > div:nth-child(3),.my-table-row > div:nth-child(4){
    width: 20%;
}
.page{
    margin-top: 10px;
    margin-bottom: 10px;
}
.empty{
    font-size: 10px;
}
}
</style>