<template>
  <header>
    <div class="left-content">
      <el-button @click="collapseMenu()" plain :icon="collapseIcon" size="mini"></el-button>
      <!-- <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item v-for="item in tabs" :key="item.label">
          <span class="breadcrumb-span">{{item.label}}</span>
        </el-breadcrumb-item>
      </el-breadcrumb> -->
    </div>
    <div class="right-content">
      <el-dropdown trigger="hover" size="mini">
        <span>
          <p>UserName</p>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="gotoMain()">回到用户主页</el-dropdown-item>
          <el-dropdown-item @click.native="gotoLogin()">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </header>
</template>

<script>
// import {mapState} from 'vuex'
export default {
  name: 'AdminHeader',
  data () {
    return {
      
    }
  },
  methods: {
    collapseMenu () {
      this.$store.commit('collapseMenu')
    },
    gotoMain () {
      console.log('go to main')
      // this.$router.push({ name: 'main' })
    },
    gotoLogin () {
      this.$store.commit('logout')
      this.$router.push({ path: '/login' })
    }
  },
  computed: {
    collapseIcon () {
      return this.$store.state.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    },
    // ...mapState({
    //   tabs: state => state.tabList
    // })
  }
}
</script>

<style lang="less" scoped>
header {
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.left-content {
  display: flex;
  align-items: center;
  .el-button {
    margin-right: 20px;
  }
  .breadcrumb-span {
    color: white;
  }
}
</style>
