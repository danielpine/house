import Vue from 'vue'
import Router from 'vue-router'
import Index from './views/index.vue'
import User from './views/user/index.vue'
import UserLogin from './views/user/login.vue'
import Register from './views/user/register.vue'
import Detail from './views/user/detail.vue'
import Houses from './views/user/houses.vue'
import Order from './views/user/order.vue'
import Pay from './views/user/pay.vue'
import NotFound from './views/404.vue'
import Login from './views/Login.vue'
import { getUser } from './utils/auth'
import Home from './views/Home.vue'
import UserList from './views/UserList.vue'
import HouseList from './views/HouseList.vue'
import PaidList from './views/PaidList.vue'
import SolveList from './views/SolveList.vue'
import ScheduleList from './views/ScheduleList.vue'
import PersonalPaidList from './views/PersonalPaidList.vue'
import PersonalHouseList from './views/PersonalHouseList.vue'
import PersonalScheduleList from './views/PersonalScheduleList.vue'
import PersonalSolveList from './views/PersonalSolveList.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      redirect: '/user'
    },
    {
      path: '/user',
      name: 'user',
      component: User,
      redirect: '/houses',
      children: [
        {
          path: '/detail/:id',
          name: 'detail',
          component: Detail,
          props: true
        },
        {
          path: '/houses',
          name: 'houses',
          component: Houses
        }
      ]
    },
    {
      path: '/userlogin',
      name: 'userlogin',
      component: UserLogin
    },
    {
      path: '/register',
      name: 'register',
      component: Register
    },
    {
      path: '/index',
      name: 'index',
      component: Index,
      children: [
        {
          path: '',
          component: Home
        },
        {
          path: '/home',
          name: 'home',
          component: Home
        },
        {
          path: '/userlist',
          name: 'userlist',
          component: UserList
        },
        {
          path: '/houselist',
          name: 'houselist',
          component: HouseList
        },
        {
          path: '/paidlist',
          name: 'paidlist',
          component: PaidList
        },
        {
          path: '/solvelist',
          name: 'solvelist',
          component: SolveList
        },
        {
          path: '/schedulelist',
          name: 'schedulelist',
          component: ScheduleList
        },
        {
          path: '/personalPaidList',
          name: 'personalPaidList',
          component: PersonalPaidList
        },
        {
          path: '/personalHouseList',
          name: 'personalHouseList',
          component: PersonalHouseList
        },
        {
          path: '/personalScheduleList',
          name: 'personalScheduleList',
          component: PersonalScheduleList
        },
        {
          path: '/personalSolveList',
          name: 'personalSolveList',
          component: PersonalSolveList
        }
      ]
    },
    {
      path: '*',
      name: '/404',
      component: NotFound
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/order',
      name: 'order',
      component: Order
    },
    {
      path: '/pay',
      name: 'pay',
      component: Pay
    }
  ]
})

//????????????
router.beforeEach((to, from, next) => {
  const isLogin = getUser().token ? true : false
  console.log(to.path)
  if (
    to.path == '/login' ||
    to.path == '/userlogin' ||
    to.path == '/user' ||
    to.path == '/houses' ||
    to.path == '/register' ||
    to.path.indexOf('/detail/') != -1
  ) {
    next()
  } else {
    isLogin ? next() : next('userlogin')
  }
})

export default router
