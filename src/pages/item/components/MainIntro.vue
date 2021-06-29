<template>
  <view class="intro-wrapper">
    <view style="font-size: 40rpx; font-weight: 700;">观塘云鼎观塘云鼎观塘云鼎观塘云鼎观塘云鼎观塘云鼎</view>
    <view style="font-size: 36rpx; color: #f40;">
      ￥4630/月
      <text class="mylove-icon iconfont" :class=" isShoucang ? 'icon-xihuan' : 'icon-xihuan-xianxing' "  @click="isShoucang = !isShoucang"/>
    </view>
    <view class="main-introduction-wrapper">
      <view class="intro-item">
        <view class="price">20.5㎡</view>
        <view class="remark">使用面积</view>
      </view>
      <view class="intro-item">
        <view class="direction">朝南</view>
        <view class="remark">朝向</view>
      </view>
      <view class="intro-item">
        <view class="size">三室一厅</view>
        <view class="remark">户型</view>
      </view>
    </view>
  </view>
</template>

<script>
  
  export default {
    name: "MainIntro",
    data() {
      return {
        isShoucang: false
      }
    }
  }
</script>

<style lang="scss" scoped>
  .intro-wrapper {
    .main-introduction-wrapper {
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 10rpx 0;
      .intro-item {
        width: 33.33%;
        height: 100rpx;
        box-sizing: border-box;
        color: rgba(0,0,0,.85);
        font-size: 38rpx;
        font-weight: bold;
        border-right: 2rpx solid #ccc;
        text-align: center;
        &:last-of-type {
          border-right: none;
        }
        .remark {
          color: rgba(0,0,0,.4);
          font-size: 26rpx;
          font-weight: normal;
        }
      }
    }
    .mylove-icon {
      color: #333;
      font-size: 50rpx;
      float: right;
    }
    .mylove-icon.icon-xihuan {
      color: red;
      animation: mylove 0.5s linear;
    }
  }
  
  @keyframes mylove {
    from { transform: scale(1);}
    50% { transform: scale(1.3);}
    to { transform: scale(1);}
  }
</style>