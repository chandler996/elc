import axios from 'axios'
console.log(process)
const service = axios.create({
  baseURL: process.env.TARO_APP_API_URL, // 从环境变量中读取
  timeout: 5000, // 请求超时时间
})

export default service