module.exports = {
  publicPath: '/',
  devServer: {
    // vue工程服务配置
    open: true,
    proxy: {
      // 代理配置，将请求转发到后台的接口
      '/api': {
        target: 'http://localhost:9002',
        changeOrigin: true, // 开启跨域
        ws: true,
        pathRewrite: {
          '^/api': 'http://localhost:9002' // 重写路径
        }
      }
    }
  }
}
