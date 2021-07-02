<template>
  <view class="login-wrapper">
    <uni-card style="position:relative; top:15%" title="登录" v-if="isLogin">
      <uni-forms ref="login_form" :rules="rule1">
        <uni-forms-item label="用户名" name="username">
          <uni-easyinput  v-model="loginForm.username" placeholder="请输入用户名" />
        </uni-forms-item>
        <uni-forms-item label="密码" name="password">
          <uni-easyinput  v-model="loginForm.password" placeholder="请输入密码" />
        </uni-forms-item>
      </uni-forms>
      <text @click="isLogin = false" class="tranformText">注册</text>
      <button type="primary" @click="loginSubmit">登录</button>
    </uni-card>
    
    <uni-card style="position:relative; top:15%" title="注册" v-else>
      <uni-forms ref="register_form" :rules="rule2">
        <uni-forms-item label="用户名" name="username">
          <uni-easyinput  v-model="registerForm.username" placeholder="请输入用户名" />
        </uni-forms-item>
        <uni-forms-item label="密码" name="password">
          <uni-easyinput  v-model="registerForm.password" placeholder="请输入密码" />
        </uni-forms-item>
        <uni-forms-item label="再次输入" name="password2">
          <uni-easyinput  v-model="registerForm.password2" placeholder="请再次输入密码" />
        </uni-forms-item>
      </uni-forms>
      <text @click="isLogin = true" class="tranformText">登录</text>
      <button type="primary" @click="registerSubmit">注册</button>
    </uni-card>
  </view>
</template>

<script>
  export default {
    onShow(option) {
      console.log(option);
    },
    data() {
      return {
        isLogin: true,
        loginForm: {
          username: "",
          password: ""
        },
        registerForm: {
          username: "",
          password: "",
          password2: ""
        },
        rule1: {
          username: {
            rules: [{required: true, errorMessage: '用户名不能为空'}]
          },
          password: {
            rules: [{required: true, errorMessage: '密码不能为空'}]
          }
        },
        rule2: {
          username: {
            rules: [{required: true, errorMessage: '用户名不能为空'}]
          },
          password: {
            rules: [{required: true, errorMessage: '密码不能为空'}]
          },
          password2: {
            rules: [{required: true, errorMessage: '请再次输入密码'}]
          },
        }
      }
    },
    methods: {
      loginSubmit() { // 登录
        this.$refs.login_form.submit().then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err);
        })
      },
      registerSubmit() { // 注册
        this.$refs.register_form.submit().then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err);
        })
      }
    }
  }
</script>

<style>
  .login-wrapper {
    height: 100vh;
    background-color: #fcfcfc;
  }
  .tranformText {
    text-decoration: underline;
    color: #999;
    position: absolute;
    top: 20rpx;
    right: 20rpx;
    z-index: 10;
  }
</style>
