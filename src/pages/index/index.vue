<template>
  <view class="content">
    <uni-nav-bar>
      <view slot="left" @click="chooseCity">
        <text class="nav-bar-left-text">北京</text>
        <uni-icons size="20" type="arrowdown"></uni-icons>
      </view>
      <uni-easyinput placeholder="输入搜索" prefixIcon="search" v-model="textValue" confirmType="search"></uni-easyinput>
      <view slot="right">搜索</view>
    </uni-nav-bar>

    <view class="hot-spot-wrapper">
      <text>
        <text class="iconfont icon-tubiaozhuanqu-05" style="color: red;" />
        热搜:
      </text>
      <text v-for="(item, i) in hotspot" :key="i"> {{ item }} </text>
    </view>
    <type-icon />
    <list-card 
      thumb="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
      tag="热卖"
      head="草桥欣园三区 央产证 南北通透 有钥匙 看两居室"
      intro="2室1厅|75.1㎡|草桥欣园三区"
      price="6500万"
      @turn2Page="turn2Page" />
    <view class="lookMore" @tap="turn2Application">
      点击查看更多
      <text class="iconfont icon-shenglve"></text>
    </view>
  </view>
  </view>
</template>

<script>
  import TypeIcon from "./components/TypeIcon.vue"
  export default {
    name: "Home",
    components: {
      TypeIcon
    },
    data() {
      return {
        textValue: "",
        hotspot: [
          "芍药居",
          "时代芳群",
          "海特花园小区",
          "新起点嘉园"
        ],
        loading: true
      }
    },
    onLoad() {

    },
    methods: {
      turn2Application() { // 跳转到别的页面中
        uni.switchTab({
          url: '/pages/application/application'
        })
      },
      chooseCity() { // 跳转到"选择城市"页面
         uni.navigateTo({
           url: '/pages/city/city'
         });
      },
      isReady() {
        this.loading = false;
      },
      turn2Page() { // 点击跳转页面
        // uni.switchTab({
        //   url: '/pages/application/application'
        // });
      }
    }
  }
</script>

<style lang="scss" scoped>
  /deep/ .uni-easyinput__content {
    border-radius: 36rpx;
    height: 60rpx;
  }

  .nav-bar-left-text {
    margin-left: 30rpx;
  }

  .content {
    height: 100vh;
  }

  .hot-spot-wrapper {
    margin: 10rpx 0 40rpx 28rpx;
    font-size: 24rpx;
    color: #808080;
  }

  .hot-spot-wrapper .hot-spot-icon {
    color: red;
    margin-right: 12rpx;
  }

  .hot-spot-wrapper>text {
    margin: 0 12rpx;
  }

  .recommend-wrapper {
    background-color: transparent;
  }

  .lookMore {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80rpx;
  }
</style>
