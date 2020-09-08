import axios from 'axios'
import router from '../router'

const service = axios.create({
  baseURL: '/api', // 关键代码
  timeout: 10000 // 请求超时时间
})
// axios.defaults.baseURL = '/api' // 关键代码
// // 请求超时时间
// axios.defaults.timeout = 10000
// // post请求头
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
// 请求拦截
service.interceptors.request.use(
  config => {
    if (localStorage.getItem('token')) {
      config.headers.Authorization = localStorage.getItem('token')
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
// 响应拦截器
service.interceptors.response.use(
  response => {
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  // 服务器状态码不是200的情况
  error => {
    let originalRequest = error.config
    if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout') !== -1 && !originalRequest._retry) {
      return Promise.reject(new Error('请检查网络再重新连接'))
    } else if (error.response && error.response.status === 401) { // token校验不通过
      localStorage.removeItem('token')
      router.replace({ path: '/' })
    } else if (error.response && error.response.status) {
      return Promise.reject(error.response)
    } else {
      return Promise.reject(error)
    }
  }
)
export default service
