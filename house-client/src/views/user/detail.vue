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
                <span
                  class="badge"
                  :class="
                    house.status == '未出租' ? 'badge-success' : 'badge-dark'
                  "
                  >{{ house.status }}</span
                >
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
                  <el-form
                    label-position="right"
                    label-width="80px"
                    :inline="true"
                    :rules="rules"
                    :model="house"
                    ref="house"
                    size="mini"
                  >
                    <el-form-item prop="months" size="mini" label="租期">
                      <el-select
                        v-model="house.months"
                        style="width:180px"
                        placeholder="请选择"
                        @change="onMonthsChange"
                      >
                        <el-option
                          v-for="item in options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        >
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item size="mini" label="开始日期">
                      <el-date-picker
                        v-model="startdate"
                        readonly
                        style="width:180px"
                        type="date"
                        placeholder="开始日期"
                      >
                      </el-date-picker>
                    </el-form-item>
                    <el-form-item size="mini" label="结束日期">
                      <el-date-picker
                        v-model="enddate"
                        readonly
                        style="width:180px"
                        type="date"
                        placeholder="结束日期"
                      >
                      </el-date-picker>
                    </el-form-item>
                    <el-form-item size="mini" label="押金">
                      ¥ {{ house.price }}
                    </el-form-item>
                    <el-form-item v-if="house.months" size="mini" label="租金">
                      ¥ {{ house.price * house.months }}
                    </el-form-item>
                    <!-- <el-form-item prop="startend" size="mini">
                       <el-date-picker
                        style="width:310px"
                        v-model="house.startend"
                        type="daterange"
                        align="right"
                        readonly
                        unlink-panels
                        size="mini"
                        range-separator="-"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        :picker-options="pickerOptions"
                      >
                      </el-date-picker>  
                    </el-form-item> -->
                  </el-form>
                </div>
                <el-button
                  :disabled="house.status == '已出租'"
                  type="primary"
                  id="btn-addOrder"
                  class="btn btn-success btn-block"
                  @click="submit"
                >
                  预定
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { getUser } from '@/utils/auth'
import request from '@/utils/request'

function genDate(picker, n) {
  const end = new Date(new Date().getTime() + 3600 * 1000 * 24)
  const start = new Date(new Date().getTime() + 3600 * 1000 * 24)
  end.setMonth(start.getMonth() + n)
  picker.$emit('pick', [start, end])
}

export default {
  name: 'login',
  props: ['id'],
  components: {},
  data() {
    return {
      options: [
        {
          value: 3,
          label: '三个月'
        },
        {
          value: 6,
          label: '六个月'
        },
        {
          value: 12,
          label: '一年'
        },
        {
          value: 24,
          label: '两年'
        },
        {
          value: 36,
          label: '三年'
        }
      ],
      rules: {
        months: [{ required: true, message: '请选择租期' }]
      },
      notifyPromise: Promise.resolve(),
      pickerOptions: {
        disabledDate(time) {
          // return time <= new Date()
          return true
        },
        shortcuts: [
          {
            text: '一个月',
            onClick(picker) {
              genDate(picker, 1)
            }
          },
          {
            text: '三个月',
            onClick(picker) {
              genDate(picker, 3)
            }
          },
          {
            text: '六个月',
            onClick(picker) {
              genDate(picker, 6)
            }
          },
          {
            text: '一  年',
            onClick(picker) {
              genDate(picker, 12)
            }
          },
          {
            text: '两  年',
            onClick(picker) {
              genDate(picker, 24)
            }
          },
          {
            text: '三  年',
            onClick(picker) {
              genDate(picker, 36)
            }
          }
        ]
      },
      house: {},
      startdate: new Date(new Date().getTime() + 3600 * 1000 * 24),
      enddate: null
    }
  },
  created() {
    this.fetchHouse()
  },
  methods: {
    submit() {
      this.$refs['house'].validate((valid) => {
        if (valid) {
          this.orderHouse()
        }
      })
    },
    onMonthsChange() {
      const end = new Date(new Date().getTime() + 3600 * 1000 * 24)
      const start = new Date(new Date().getTime() + 3600 * 1000 * 24)
      end.setMonth(start.getMonth() + this.house.months)
      this.enddate = end
    },
    orderHouse() {
      var user = getUser()
      console.log(user)
      let that = this
      console.log(that.house)
      if (user.token) {
        request({
          method: 'post',
          url: '/order/apply',
          data: {
            houseid: that.id,
            startdate: that.startdate,
            enddate: that.enddate
          }
        })
          .then(function(res) {
            if (res.data.flag) {
              that.house = res.data.data
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
      } else {
        that.$message({
          message: '请先登录！',
          type: 'warn'
        })
      }
    },
    fetchHouse() {
      let that = this
      request({
        method: 'get',
        url: '/house/' + that.id
      })
        .then(function(res) {
          that.house = res.data.data
        })
        .catch(function(err) {})
    },
    //通知，解决element-ui，同时调用notify时，通知重叠的问题
    notify(msg) {
      this.notifyPromise = this.notifyPromise.then(this.$nextTick).then(() => {
        this.$notify.info({
          title: '公告',
          message: msg,
          offset: 70
        })
      })
    },
    isEmpty(value) {
      return (
        value === undefined ||
        value === null ||
        (typeof value === 'object' && Object.keys(value).length === 0) ||
        (typeof value === 'string' && value.trim().length === 0)
      )
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
