  <template>
  <div class="common-aside">

    <el-menu default-active="1-4-1" class="el-menu-vertical-demo" :collapse="isCollapse" :unique-opened="isUniqueOpened">
      <el-menu-item @click="clickMenu(item)" v-for="item in noChildren" :index="item.path" :key="item.path">
          <i :class="'el-icon-' + item.icon"></i>
          <span slot="title">{{item.label}}</span>
      </el-menu-item>
      <el-submenu v-for="item in hasChildren" :index="item.label" :key="item.label">
          <template slot="title">
            <i :class="'el-icon-' + item.icon"></i>
            <span slot="title">{{item.label}}</span>
          </template>
          <el-menu-item-group v-for="subitem in item.children" :key="subitem.path">
            <el-menu-item @click="clickMenu(subitem, item)" :index="subitem.path">
              <i :class="'el-icon-' + subitem.icon"></i>
              <span slot="title">{{subitem.label}}</span>
            </el-menu-item>
          </el-menu-item-group>
      </el-submenu>
    </el-menu>

  </div>
</template>

<script>
export default {
  name: 'CommonAside',
  data () {
    return {
      isUniqueOpened: true,
      menu: [
        {
          path: '/admin/home',
          label: '首页',
          icon: 's-home'
        }, {
          label: '用户管理',
          icon: 'user',
          children: [
            {
              path: '/admin/user',
              label: '用户列表',
              icon: 'user'
            },
            {
              path: '/admin/user/add',
              label: '新增用户',
              icon: 'plus'
            }
          ]
        }, {
          label: '公告管理',
          icon: 'user',
          children: [
            {
              path: '/admin/notice',
              label: '公告列表',
              icon: 'user'
            },
            {
              path: '/admin/notice/add',
              label: '新增公告',
              icon: 'plus'
            }
          ]
        }, {
          label: '专业管理',
          icon: 'user',
          children: [
            {
              path: '/admin/major',
              label: '专业列表',
              icon: 'user'
            },
            {
              path: '/admin/major/add',
              label: '新增用户',
              icon: 'plus'
            }
          ]
        }, 
      ]
    }
  },
  methods: {
    // handleOpen (key, keyPath) {
    //   console.log(key, keyPath)
    // },
    // handleClose (key, keyPath) {
    //   console.log(key, keyPath)
    // },
    clickMenu (item, fitem) {
      console.log(item, fitem)
      this.$router.push({ path: item.path })
      // location.reload()
      // if (fitem) {
      //   this.$store.commit('selectTab', [{'label': fitem.label}, item])
      // } else {
      //   this.$store.commit('selectTab', [item])
      // }
    }
  },
  computed: {
    noChildren () {
      return this.menu.filter(item => !item.children)
    },
    hasChildren () {
      return this.menu.filter(item => item.children)
    },
    isCollapse () {
      return this.$store.state.isCollapse
    }
  },
  mounted () {
  }
}
</script>

<style scoped>
.common-aside {
  height: 100%;
}
.el-menu {
  height: 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
}
@media screen and (max-width: 900px) {
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 100vw;
  }
}
</style>
