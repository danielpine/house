<template>
  <div>
    <!-- Navbar -->
    <Navbar />
    <div class="table_container" style="width: 100%; min-height: 668px">
      <el-table
        :data="tableData"
        style="width: 100%; margin-top: 80px"
        max-height="450"
        border
      >
        <el-table-column type="index" label="订单编号" align="center" width="150">
        </el-table-column>
        <el-table-column prop="name" label="租赁关系" align="center" width="150">
        </el-table-column>
        <el-table-column prop="address" label="租赁时间" align="center" width="400">
        </el-table-column>
        <el-table-column prop="price" label="金额" align="center" width="150">
        </el-table-column>
        <el-table-column
          prop="date"
          label="应缴纳日期"
          align="center"
          width="200"
          :formatter="dateFormat"
        >
        </el-table-column>
        <el-table-column
          prop="payDate"
          label="实际缴纳日期"
          align="center"
          width="200"
          :formatter="dateFormat"
        >
        </el-table-column>
        <el-table-column prop="price" label="订单状态" align="center" width="150">
        </el-table-column>
        <el-table-column label="操作" prop="operation" align="center">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="success"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
              >修改</el-button
            >
            <el-button
              size="small"
              type="danger"
              icon="el-icon-delete"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <section class="pt-4 pb-4">
      <div class="container">
        <div class="row align-items-center text-center text-md-left">
          <div class="col-md-12" style="margin: auto; text-align: center">
            <p class="mt-0 mb-0">
              好租房公司 © Copyright 2020 GoodHouse. All Rights Reserved
            </p>
            <p class="mt-0 mb-0">公安备案888888 工信部备案666666</p>
            <p class="mt-0 mb-0">
              违法和不良信息举报电话 +86 2323115 电子邮箱 goodhouse@gmail.com
            </p>
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

