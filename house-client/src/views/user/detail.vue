<template>
  <div>
    <!-- Property Single Slider header -->
    <section class="site-slider">
      <div class="property-single-title property-single-title-gallery">
        <div class="container">
          <div class="row">
            <div class="col-lg-8 col-md-8">
              <h2>{{ house.address }}</h2>
              <h6>
                <i class="mdi mdi-home-map-marker"></i>
                12栋2单元101室C卧 (合租)
              </h6>
            </div>
            <div class="col-lg-4 col-md-4 text-right">
              <h6 class="mt-2">
                <span>{{ house.status }}</span>
              </h6>
              <h2 class="text-success">
                ¥ {{ house.price }} <small>/月</small>
              </h2>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Property Single Slider header -->
    <!-- Property Single Slider -->
    <section class="section-padding" style="padding: 50px">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-8">
            <div class="card">
              <div class="card-body site-slider pl-0 pr-0 pt-0 pb-0">
                <div
                  id="sitesliderz"
                  class="carousel slide"
                  data-ride="carousel"
                >
                  <ol class="carousel-indicators">
                    <li
                      data-target="#sitesliderz"
                      data-slide-to="0"
                      class="active"
                    ></li>
                    <li data-target="#sitesliderz" data-slide-to="1"></li>
                    <li data-target="#sitesliderz" data-slide-to="2"></li>
                    <li data-target="#sitesliderz" data-slide-to="3"></li>
                    <li data-target="#sitesliderz" data-slide-to="4"></li>
                  </ol>
                  <div class="carousel-inner" role="listbox">
                    <div
                      class="carousel-item rounded active"
                      style="background-image: url(/img/1.jpg)"
                    ></div>
                    <div
                      class="carousel-item rounded"
                      style="background-image: url(/img/2.jpg)"
                    ></div>
                    <div
                      class="carousel-item rounded"
                      style="background-image: url(/img/3.jpg)"
                    ></div>
                    <div
                      class="carousel-item rounded"
                      style="background-image: url(/img/3.jpg)"
                    ></div>
                    <div
                      class="carousel-item rounded"
                      style="background-image: url(/img/2.jpg)"
                    ></div>
                  </div>
                  <a
                    class="carousel-control-prev"
                    href="#sitesliderz"
                    role="button"
                    data-slide="prev"
                  >
                    <span
                      class="carousel-control-prev-icon"
                      aria-hidden="true"
                    ></span>
                    <span class="sr-only">Previous</span>
                  </a>
                  <a
                    class="carousel-control-next"
                    href="#sitesliderz"
                    role="button"
                    data-slide="next"
                  >
                    <span
                      class="carousel-control-next-icon"
                      aria-hidden="true"
                    ></span>
                    <span class="sr-only">Next</span>
                  </a>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-4 col-md-4">
                <div class="list-icon">
                  <i class="mdi mdi-sofa"></i>
                  <strong>卧室:</strong>
                  <p class="mb-0">1 个</p>
                </div>
              </div>
              <div class="col-lg-4 col-md-4">
                <div class="list-icon">
                  <i class="mdi mdi-scale-bathroom"></i>
                  <strong>洗手间:</strong>
                  <p class="mb-0">1 个</p>
                </div>
              </div>
              <div class="col-lg-4 col-md-4">
                <div class="list-icon">
                  <i class="mdi mdi-move-resize-variant"></i>
                  <strong>面积:</strong>
                  <p class="mb-0">20.0 m²</p>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-4">
            <div class="card sidebar-card">
              <div class="card-body">
                <h5 class="card-title mb-3">详细介绍</h5>
                <div>
                  {{ house.detail }}
                </div>
              </div>
            </div>
            <div class="card sidebar-card">
              <div class="card-body">
                <div class="block">
                  <h5 class="card-title mb-4">立即租房</h5>
                  <el-date-picker
                    v-model="value"
                    type="daterange"
                    align="right"
                    unlink-panels
                    size="small"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    value-format="yyyyMMdd"
                    :picker-options="pickerOptions"
                  >
                  </el-date-picker>
                </div>
                <br />
                <button
                  type="button"
                  id="btn-addOrder"
                  class="btn btn-success btn-block"
                  @click="orderHouse"
                >
                  预定
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import userApi from "@/api/user";
import { setUser } from "@/utils/auth";
import scheduleApi from "@/api/schedule.js";
import request from "@/utils/request";
export default {
  name: "login",
  props: ["id"],
  components: {},
  data() {
    return {
      notifyPromise: Promise.resolve(),
      pickerOptions: {
        shortcuts: [
          {
            text: "一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(start.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(start.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
      house: {},
      value: "",
      loginUser: {
        account: "admin",
        password: "88888888",
      },
      rules: {
        account: [
          {
            required: true,
            message: "账号不能为空",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.fetchHouse();
  },
  methods: {
    orderHouse() {
      let that = this;
      request({
        method: "post",
        url: "/order/apply",
        data: {
          houseid: that.id,
          startdate: new Date(),
          enddate: new Date(),
        },
      })
        .then(function (res) {
          console.log(res.data);
        })
        .catch(function (err) {});
    },
    fetchHouse() {
      let that = this;
      request({
        method: "get",
        url: "/house/" + that.id,
      })
        .then(function (res) {
          that.house = res.data.data;
        })
        .catch(function (err) {});
    },
    //通知，解决element-ui，同时调用notify时，通知重叠的问题
    notify(msg) {
      this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(() => {
        this.$notify.info({
          title: "公告",
          message: msg,
          offset: 70,
        });
      });
    },
    isEmpty(value) {
      return (
        value === undefined ||
        value === null ||
        (typeof value === "object" && Object.keys(value).length === 0) ||
        (typeof value === "string" && value.trim().length === 0)
      );
    },
    doLogin(formName) {
      let pojo = {
        account: this.loginUser.account,
        password: this.loginUser.password,
      };
      userApi.login(pojo).then((res) => {
        //登录成功之后的处理
        if (res.data.flag) {
          let systemRole = res.data.systemRole;
          let id = res.data.userInfo.id;
          let name = res.data.userInfo.name;
          let token = res.data.token;

          //将用户信息存入cookie中
          setUser(systemRole, name, id, token);

          const decode = jwt_decode(token);

          // 存储数据
          this.$store.dispatch("setIsAutnenticated", !this.isEmpty(decode));
          this.$store.dispatch("setUser", decode);

          this.$message({
            message: "登录成功",
            type: "success",
          });
          this.$router.push("/user");

          scheduleApi.getScheduleInSevenDays().then((res) => {
            if (res.data.flag) {
              if (res.data.data.length != 0) {
                for (var i = 0; i < res.data.data.length; i++) {
                  this.notify(res.data.data[i].content);
                }
              }
            } else {
              this.$message({
                message: "获取公告失败",
                type: "warning",
              });
            }
          });
        } else {
          this.$message({
            message: "账号或密码错误",
            type: "error",
          });
          this.loginUser.account = "";
          this.loginUser.password = "";
        }
      });
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

