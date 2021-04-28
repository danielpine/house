import axios from 'axios'
import { getUser } from '@/utils/auth'

import { Message, Loading } from 'element-ui'
import { removeUser } from '@/utils/auth'
import router from '@/router'

let loading //定义loading变量

function startLoading() {
  //使用Element loading-start 方法
  loading = Loading.service({
    lock: true,
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.7)'
  })
}
function endLoading() {
  //使用Element loading-close 方法
  loading.close()
}

// 创建axios实例
const service = axios.create({
  baseURL: '/api', // api的base_url
  timeout: 30000, // 请求超时时间
  headers: { Authorization: 'Bearer ' + getUser().token },
  withCredentials: true
})

// 请求拦截  设置统一header
service.interceptors.request.use(
  (config) => {
    startLoading()
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截  401 token过期处理
service.interceptors.response.use(
  (response) => {
    endLoading()
    return response
  },
  (error) => {
    endLoading()
    const { status } = error.response
    if (status == 401) {
      Message.error('Token失效,请重新登录!')
      removeUser()
      router.push('/userlogin')
    } else {
      Message.error(error.response.data)
    }
    return Promise.reject(error)
  }
)

export default service
