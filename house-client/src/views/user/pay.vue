<template>
  <div style="width: 100%;height: 100%">
    <Navbar />
    <div class="table_container" style="width: 100%;height: 90%">
      <div style="width: 100%; padding-top: 80px">
        <div class="container">
          <br />
          <h2>订单编号：{{ orderid }}</h2>
          <br />
          <div class="row">
            <el-table
              :data="tableData"
              max-height="450"
              border
              show-summary
              :summary-method="sum"
            >
              <el-table-column type="index" label="#" width="50" />
              <el-table-column prop="id" label="支付编号" width="100" />
              <el-table-column
                prop="orderid"
                label="订单编号"
                width="100"
                :show-overflow-tooltip="true"
              />
              <el-table-column prop="address" label="租赁关系" />
              <el-table-column prop="paytype" label="类型" />
              <el-table-column prop="price" label="金额" />
            </el-table>
          </div>
          <div style="width: 100%;text-align:center">
            <br />
            打开手机支付宝 扫一扫支付房租
            <br />
            <br />
            <div
              class="qrcode"
              style="width: 100%;text-align:center"
              ref="qrCodeUrl"
            ></div>
            <br />
            <br />
            <el-button type="primary" @click="paysuccess"
              >模拟支付成功</el-button
            >
          </div>
        </div>
      </div>
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
import QRCode from 'qrcodejs2'
export default {
  name: 'order',
  components: { Navbar },
  created() {},
  mounted() {
    this.list()
    this.creatQrCode()
  },
  data() {
    return {
      user: getUser(),
      orderid: this.$route.query.orderid,
      tableData: []
    }
  },
  methods: {
    sum(param) {
      const { columns, data } = param
      var sumprice = 0
      data.forEach((d) => {
        sumprice += d.price
      })
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计'
          return
        }
        switch (column.property) {
          case 'price':
            sums[index] = sumprice + ' 元'
            break
          default:
            break
        }
      })
      return sums
    },
    paysuccess() {
      let that = this
      request({
        method: 'get',
        url: '/order/paysuccess?orderid=' + that.orderid
      })
        .then(function(res) {
          console.log(res.data)
          if (res.data.flag) {
            that.$message({
              message: res.data.message,
              type: 'success'
            })
            that.$router.push('/order')
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
    },
    list() {
      let that = this
      request({
        method: 'get',
        url: '/paid/getpaids?orderid=' + that.orderid
      })
        .then(function(res) {
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
        .catch(function(err) {
          that.$message({
            message: err,
            type: 'error'
          })
        })
    },
    creatQrCode() {
      new QRCode(this.$refs.qrCodeUrl, {
        text: '支付宝扫码支付',
        width: 300,
        height: 300,
        colorDark: '#000000',
        colorLight: '#ffffff',
        correctLevel: QRCode.CorrectLevel.H
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
