<template>
  <div style="width: 100%;height: 100%">
    <Navbar />
    <div class="table_container" style="width: 100%;height: 90%">
      <el-table
        :data="tableData"
        style="width: 100%; padding-top: 80px"
        max-height="450"
        border
      >
        <el-table-column type="index" label="#" width="50" />
        <el-table-column
          prop="orderid"
          label="订单编号"
          width="100"
          :show-overflow-tooltip="true"
        />
        <el-table-column prop="house.address" label="租赁关系" />
        <el-table-column prop="months" label="租赁期限(月)" width="110" />
        <el-table-column label="租赁时间" width="250">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px"
              >{{ formatDate(scope.row.startdate) }}
              至
              {{ formatDate(scope.row.enddate) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column prop="house.price" label="租金(元/月)" width="110" />
        <el-table-column prop="date" label="租金缴纳" width="350">
          <template slot-scope="scope">
            <table style="width:100%">
              <tr>
                <td>项目</td>
                <td>应缴</td>
                <td>状态</td>
              </tr>
              <tr v-for="p in scope.row.paids" :key="p.id">
                <td>{{ p.paytype }}</td>
                <td>{{ p.price }}</td>
                <td>{{ p.status }}</td>
              </tr>
            </table>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="订单状态" width="150">
          <template slot-scope="scope">
            <span v-if="scope.row.status == '生效中'" style="color:green">
              {{ scope.row.status }}
            </span>
            <span v-else>
              {{ scope.row.status }}
            </span>
            <a
              v-if="scope.row.status == '待支付'"
              type="primary"
              :href="'/pay?orderid=' + scope.row.orderid"
              target="_blank"
              >去支付</a
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="createdate"
          label="创建时间"
          width="170"
          :formatter="dateFormat"
        />
        <el-table-column label="操作" prop="operation" width="200">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="danger"
              icon="el-icon-delete"
              :disabled="'待支付' != scope.row.status"
              @click="cancel(scope.row.orderid)"
              >取消</el-button
            >
            <el-button
              size="small"
              type="success"
              icon="el-icon-edit"
              :disabled="'生效中' != scope.row.status"
              @click="surrender(scope.row.orderid)"
              >退租</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <section class="footer pt-4 pb-4" style="width: 100%;   height: 10%">
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
import { getUser } from '@/utils/auth'
import request from '@/utils/request'
import Navbar from '../../components/Navbar.vue'
export default {
  name: 'order',
  components: { Navbar },
  created() {
    this.list()
  },
  data() {
    return {
      user: getUser(),
      tableData: []
    }
  },
  methods: {
    dateFormat: function(row, column) {
      var date = row[column.property]
      if (date == undefined) {
        return ''
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss')
    },
    formatDate: function(date) {
      if (date == undefined) {
        return ''
      }
      return this.$moment(date).format('YYYY-MM-DD')
    },
    list() {
      let that = this
      request({
        method: 'get',
        url: '/order/list'
      }).then(function(res) {
        console.log(res.data)
        if (res.data.flag) {
          that.tableData = res.data.data
        } else {
          that.$message({
            message: res.data.message,
            type: 'error'
          })
        }
      })
    },
    surrender(orderid) {
      let that = this
      request({
        method: 'get',
        url: '/order/surrender?orderid=' + orderid
      })
        .then(function(res) {
          console.log(res.data)
          if (res.data.flag) {
            that.$message({
              message: res.data.message,
              type: 'success'
            })
            that.list()
          } else {
            that.$message({
              message: res.data.message,
              type: 'error'
            })
          }
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
