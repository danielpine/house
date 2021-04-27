<template>
  <div>
    <section class="section-padding" style="padding: 50px">
      <div class="section-title text-center mb-5">
        <h2>房屋信息</h2>
      </div>
      <div class="container">
        <div class="row">
          <div
            v-for="house in data"
            :key="house.houseId"
            class="col-lg-4 col-md-4"
          >
            <div class="card card-list">
              <router-link :to="/detail/ + house.houseId">
                <span
                  class="badge"
                  :class="
                    house.status == '未出租' ? 'badge-success' : 'badge-dark'
                  "
                  >{{ house.status }}</span
                >
                <img class="card-img-top" src="/img/3.jpg" />
                <div class="card-body">
                  <h5 class="card-title">{{ house.address }}</h5>
                  <h6 class="card-subtitle mb-2 text-muted">
                    <i class="mdi mdi-home-map-marker"></i>
                    {{ house.city }}
                  </h6>
                  <h2 class="text-success mb-0 mt-3">
                    ¥{{ house.price }} <small>/月</small>
                  </h2>
                </div>
                <div class="card-footer">
                  <span
                    ><i class="mdi mdi-sofa"></i> 卧室 :
                    <strong>3</strong></span
                  >
                  <span
                    ><i class="mdi mdi-scale-bathroom"></i> 洗手间 :
                    <strong>1</strong></span
                  >
                  <span
                    ><i class="mdi mdi-move-resize-variant"></i> 面积 :
                    <strong>100.0 m²</strong></span
                  >
                </div>
              </router-link>
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
export default {
  name: 'user',
  components: {},
  props: ['data'],
  created() {
    this.getProfile()
  },
  data() {
    return {
      user: getUser()
    }
  },
  methods: {
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
        .catch(function(err) {})
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
