<template>
  <div>
    <!-- Navbar -->
    <header
      style="position: fixed; top: 0; height: 81px; z-index: 999; width: 100%"
    >
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container" style="margin: auto">
          <a class="navbar-brand text-success logo" href="/">
            <img class="img-fluid" src="/img/logo.svg" alt="" />
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
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  <span>城市切换</span>
                </a>
                <div class="dropdown-menu" style="display: none">
                  <a class="dropdown-item" href="/post?cityId=1">北京</a
                  ><a class="dropdown-item" href="/post?cityId=2">上海</a
                  ><a class="dropdown-item" href="/post?cityId=3">广州</a
                  ><a class="dropdown-item" href="/post?cityId=4">深圳</a
                  ><a class="dropdown-item" href="/post?cityId=5">杭州</a
                  ><a class="dropdown-item" href="/post?cityId=6">武汉</a
                  ><a class="dropdown-item" href="/post?cityId=7">长沙</a
                  ><a class="dropdown-item" href="/post?cityId=8">大连</a
                  ><a class="dropdown-item" href="/post?cityId=9">厦门</a
                  ><a class="dropdown-item" href="/post?cityId=10">南京</a>
                </div>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/">
                  <i class="fa fa-home"></i> 首页
                </a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  href="/post;jsessionid=Pfyf4Zsjis3zpCTIvlYfJfGOy0RDlP6thMOn_KIg?cateId=1"
                >
                  <i class="fa fa-building"></i>

                  整租
                </a>
              </li>
              <li class="nav-item">
                <a
                  class="nav-link"
                  href="/post;jsessionid=Pfyf4Zsjis3zpCTIvlYfJfGOy0RDlP6thMOn_KIg?cateId=2"
                >
                  <i class="fa fa-cubes"></i>
                  合租
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/notice">
                  <i class="fa fa-newspaper-o"></i> 新闻公告
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/contact">
                  <i class="fa fa-envelope"></i> 联系我们
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/admin/post/new">
                  <i class="fa fa-pencil"></i> 房源发布
                </a>
              </li>
            </ul>
            <div class="my-2 my-lg-0">
              <ul class="list-inline main-nav-right">
                <li v-if="user.name" class="list-inline-item">
                  <i class="fa fa-user"></i> 您好,{{ user.name }}
                </li>
                <li v-else class="list-inline-item">
                  <a class="btn btn-link btn-sm" id="toLogin" href="/userlogin">
                    <i class="fa fa-user"></i> 登录/注册
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </header>
  </div>
</template>

<script>
import { getUser } from "@/utils/auth";
import request from "@/utils/request";
export default {
  name: "user",
  components: {},
  created() {
    this.getProfile();
  },
  data() {
    return {
      user: getUser(),
      info: {
        city: "上海",
      },
      data: {},
    };
  },
  methods: {
    getProfile() {},
    search() {
      let that = this;
      request({
        method: "post",
        url: "/house/search",
        data: that.info,
      })
        .then(function (res) {
          that.data = res.data.data;
        })
        .catch(function (err) {});
    },
  },
};
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

