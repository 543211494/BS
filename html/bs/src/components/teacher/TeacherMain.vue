<template>
  <div class="main">
      <el-container id="bigbox">
        <el-header>
            <teacher-header></teacher-header>
        </el-header>
        <el-container>
            <el-aside width="auto">
                <teacher-aside></teacher-aside>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
        <Footer/>
    </el-container>
  </div>
</template>

<script>
import TeacherHeader from '@/components/teacher/TeacherHeader'
import TeacherAside from '@/components/teacher/TeacherAside'
import Footer from '@/components/Footer'
export default {
  name: 'TeacherMain',
  components: {
    TeacherHeader,
    TeacherAside,
    Footer
  },
  data () {
    return {

    }
  }
}
</script>

<style lang="less" scoped>
.main {
  height: 100%;
}
#bigbox {
    height: 100vh;
}
.el-header {
    border-bottom:1px solid #000;
}
.el-main {
    padding: 0;
}
@media screen and (max-width: 900px) {
    
}
</style>
