<template>
  <div>
    <!-- Navbar -->
    <header
      style="position: fixed; top: 0; height: 81px; z-index: 999; width: 100%"
    >
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container" style="margin: auto">
          <a class="navbar-brand text-success logo" href="/">
            <img style="height:80px;" src="/img/logo.png" alt="" />
          </a>
          <button
            class="navbar-toggler navbar-toggler-right"
            type="button"
            data-toggle="collapse"
            data-target="#navbarResponsive"
            aria-controls="navbarResponsive"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="/user">
                  <i class="fa fa-home"></i> 首页
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/order">
                  <i class="fa fa-pencil"></i> 订单管理
                </a>
              </li>
            </ul>
            <ul class="list-inline main-nav-right">
              <li v-if="user.name" class="list-inline-item">
                <el-dropdown
                  @command="setDialogInfo"
                  class="btn btn-link btn-sm"
                >
                  <span class="el-dropdown-link"
                    ><i class="fa fa-user"></i> {{ user.name }}
                    <i class="el-icon-caret-bottom el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="logout">退出</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </li>
              <li v-else class="list-inline-item">
                <a class="btn btn-link btn-sm" id="toLogin" href="/userlogin">
                  <i class="fa fa-user"></i> 登录/注册
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  </div>
</template>

<script>
import { removeUser, getUser } from '@/utils/auth'
import request from '@/utils/request'
export default {
  name: 'user',
  components: {},
  created() {
    this.getProfile()
  },
  data() {
    return {
      user: getUser(),
      info: {
        city: '上海'
      },
      data: {}
    }
  },
  methods: {
    setDialogInfo(cmdItem) {
      switch (cmdItem) {
        case 'logout':
          this.logout()
          break
      }
    },
    logout() {
      //清除用户信息
      removeUser()
      //设置vue store
      this.$store.dispatch('clearCurrentState')
      //跳转
      window.location.reload()
    },
    getProfile() {},
    search() {
      let that = this
      request({
        method: 'post',
        url: '/house/search',
        data: that.info
      })
        .then(function(res) {
          that.data = res.data.data
        })
        .catch(function(err) {
          that.$message({
            message: err,
            type: 'error'
          })
        })
    }
  }
}
</script>

<style scoped>
.index {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.rightContainer {
  position: relative;
  top: 0;
  left: 180px;
  width: calc(100% - 180px);
  height: calc(100% - 71px);
  overflow: auto;
}
</style>
