<template>
  <div>
    <!-- Navbar -->
    <Navbar />

    <!-- Main Slider With Form -->
    <section class="site-slider">
      <div id="siteslider" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#siteslider" data-slide-to="0" class="active"></li>
          <li data-target="#siteslider" data-slide-to="1"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div
            class="carousel-item active"
            style="background-image: url('/img/1.jpg')"
          >
            <div class="overlay"></div>
          </div>
          <div
            class="carousel-item"
            style="background-image: url('/img/2.jpg')"
          >
            <div class="overlay"></div>
          </div>
        </div>
        <a
          class="carousel-control-prev"
          href="#siteslider"
          role="button"
          data-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a
          class="carousel-control-next"
          href="#siteslider"
          role="button"
          data-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
      <div class="slider-form">
        <div class="container">
          <div class="text-center mb-5">
            <h6 class="subtitle mb-1 mt-0 text-shadow text-dark">
              发现租房市场上的最佳报价。
            </h6>
            <h1 class="display-4 mt-0 font-weight-bold text-shadow">
              让我们带你回家吧
            </h1>
          </div>
          <div class="row no-gutters">
            <div class="col-md-2">
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="mdi mdi-map-marker-multiple"></i>
                </div>
                <input
                  class="form-control"
                  name="postTitle"
                  placeholder="请输入关键字"
                  type="text"
                />
              </div>
            </div>
            <div class="col-md-2">
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="mdi mdi-google-maps"></i>
                </div>
                <select
                  class="form-control select2 no-radius"
                  v-model="info.city"
                >
                  <option value="">城市</option>
                  <option value="北京">北京</option>
                  <option value="上海">上海</option>
                  <option value="广州">广州</option>
                  <option value="深圳">深圳</option>
                  <option value="杭州">杭州</option>
                  <option value="武汉">武汉</option>
                  <option value="长沙">长沙</option>
                  <option value="大连">大连</option>
                  <option value="厦门">厦门</option>
                  <option value="南京">南京</option>
                </select>
              </div>
            </div>
            <div class="col-md-2">
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="mdi mdi-security-home"></i>
                </div>
                <select class="form-control select2 no-radius" name="cateId">
                  <option value="">类型</option>
                  <option value="1">整租</option>
                  <option value="2">合租</option>
                </select>
              </div>
            </div>
            <div class="col-md-2">
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="mdi mdi-security-home"></i>
                </div>
                <select class="form-control select2 no-radius" name="area">
                  <option value="">面积</option>
                  <option value="0-20">20m²以下</option>
                  <option value="20-40">20-40m²</option>
                  <option value="40-60">40-60m²</option>
                  <option value="60-80">60-80m²</option>
                  <option value="80-100">80-100m²</option>
                  <option value="100-200">100-200m²</option>
                  <option value="200-10000">200²以上</option>
                </select>
              </div>
            </div>
            <div class="col-md-2">
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="mdi mdi-security-home"></i>
                </div>
                <select class="form-control select2 no-radius" name="price">
                  <option value="">价格</option>
                  <option value="0-1000">1000元/月以下</option>
                  <option value="1000-2000">1000元-2000元/月</option>
                  <option value="2000-3000">2000元-3000元/月</option>
                  <option value="3000-4000">3000元-4000元/月</option>
                  <option value="4000-5000">4000元-5000元/月</option>
                  <option value="5000-6000">5000元-6000元/月</option>
                  <option value="6000-7000">6000元-7000元/月</option>
                  <option value="7000-8000">7000元-8000元/月</option>
                  <option value="8000-9000">8000元-9000元/月</option>
                  <option value="9000-10000">9000元-10000元/月</option>
                  <option value="10000-1000000">10000元/月以上</option>
                </select>
              </div>
            </div>
            <div class="col-md-2">
              <button
                @click="search"
                class="btn btn-success btn-block no-radius font-weight-bold"
              >
                SEARCH
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <router-view :data="data"></router-view>
    <section class="pt-4 pb-4">
    <div class="container">
        <div class="row align-items-center text-center text-md-left">
            <div class="col-md-12" style="margin: auto;text-align: center;">
                <p class="mt-0 mb-0">好租房公司 © Copyright 2020 GoodHouse. All Rights Reserved</p>
                <p class="mt-0 mb-0">公安备案888888  工信部备案666666</p>
                <p class="mt-0 mb-0">违法和不良信息举报电话 +86 2323115 电子邮箱 goodhouse@gmail.com</p>
            </div>
        </div>
    </div>
</section>
  </div>
</template>

<script>
import { getUser } from "@/utils/auth";
import request from "@/utils/request";
import Navbar from "../../components/Navbar.vue";
export default {
  name: "user",
  components: { Navbar },
  created() {
    this.getProfile();
    this.search();
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
          that.$router.push("/houses");
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

