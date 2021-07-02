// 导入axios对应包
import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios);

// 401错误(访问拒绝)
const UNAUTHORIZED = 401;

export function request(config) {
  // 1.创建axios实例
  const instance = axios.create({
    baseURL: process.env.NODE_ENV == 'development' ? "http://192.168.2.8:8888/house-rent-api" : "https://www.baidu.com",
    timeout: 5000,
  });

  // 2.1请求拦截
  instance.interceptors.request.use((config) => {
    // 获取token, 若token存在则在请求头上添加token  
    // const token = uni.getStorageSync('satoken');
    // if (token) {
    //   config.headers.satoken = token;
    // }
    config.headers.satoken = "f12c232d-6518-464f-805e-b854bc3656e6";
    return config;
  },
    (err) => {
      console.log(err);
    }
  );

  // 2.2响应拦截
  instance.interceptors.response.use((res) => {
      // res.response中得到 "错误代号" 和 "信息”
      // const code  = res.data.code;
      // console.log(res);
      // 若出错, 尤其是401错误时, 即表示token过期
      // if (+code === UNAUTHORIZED) {
      //   alert("登录过期, 请重新登录");
      //   uni.switchTab()
      // }
      return res.data;
    }, (err) => {
        // 返回错误信息
        console.log(err);
        uni.showToast({
          title: err.userPromptMsg
        })
        return Promise.reject(err.response ? err.response.status : err); //返回接口返回的错误信息
    }
  );

  // 3.发送请求
  return instance(config);
}

// #ifdef MP-WEIXIN || APP-PLUS

//真机获取
axios.defaults.adapter = function (config) {
  return new Promise((resolve, reject) => {
    var settle = require('axios/lib/core/settle');
    var buildURL = require('axios/lib/helpers/buildURL');
    uni.request({
      method: config.method.toUpperCase(),
      url: config.baseURL + buildURL(config.url, config.params, config.paramsSerializer),
      header: config.headers,
      data: config.data,
      dataType: config.dataType,
      responseType: config.responseType,
      sslVerify: config.sslVerify,
      complete:function complete(response){
        response = {
          data: response.data,
          status: response.statusCode,
          errMsg: response.errMsg,
          header: response.header,
          config: config
        };
        
        settle(resolve, reject, response);
      }
    })
    })
}

// #endif