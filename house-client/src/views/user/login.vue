<template>
  <div>
    <!-- Login -->
    <section class="hv-100">
      <div class="container hv-100">
        <div class="row align-items-center hv-100">
          <div class="col-lg-4 col-md-4 mx-auto">
            <div class="card padding-card mb-0">
              <div class="card-body">
                <h5 class="card-title mb-4">房屋租赁系统-用户登录</h5>
                <form action="#">
                  <div class="form-group">
                    <label>账号 <span class="text-danger">*</span></label>
                    <el-input
                      v-model="loginUser.account"
                      placeholder="请输入账号"
                    ></el-input>
                  </div>
                  <div class="form-group">
                    <label>密码 <span class="text-danger">*</span></label>
                    <el-input
                      v-model="loginUser.password"
                      placeholder="请输入密码"
                      type="password"
                    ></el-input>
                  </div>
                  <el-button
                    type="primary"
                    class="submit_btn"
                    @click="doLogin('loginForm')"
                  >
                    登 录</el-button
                  >
                </form>
                <div class="mt-4 text-center">
                  已经有账号了? <a href="/register">注册</a> |
                  <a href="/user">返回首页</a>
                </div>
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

export default {
  name: "login",
  components: {},
  data() {
    return {
      notifyPromise: Promise.resolve(),
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
  methods: {
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

