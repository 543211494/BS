<template>
  <div class="main">
      <el-container id="bigbox">
        <el-header>
            <admin-header></admin-header>
        </el-header>
        <el-container>
            <el-aside width="auto">
                <admin-aside></admin-aside>
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
import AdminHeader from '@/components/admin/AdminHeader'
import AdminAside from '@/components/admin/AdminAside'
import Footer from '@/components/Footer'
export default {
  name: 'AdminMain',
  components: {
    AdminHeader,
    AdminAside,
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
