<template>
  <view class="item-condition-wrapper">
    <view class="condition-title">房屋条件</view>
    <!-- 图标以及相关名字 -->
    <view style="display: flex; align-items: center;">
      <view class="condition-item">
        <text class="iconfont icon-youyanji"/>
        <view>油烟机</view>
      </view>
      
      <view class="condition-item">
        <text class="iconfont icon-bingxiang"/>
        <view>冰箱</view>
      </view>
      
      <view class="condition-item">
        <text class="iconfont icon-xiyiji"/>
        <view>洗衣机</view>
      </view>
      
      <view class="condition-item">
        <text class="iconfont icon-luyouqi"/>
        <view>路由器</view>
      </view>      
    </view>
  </view>
</template>

<script>
  export default {
    name: "ItemCondition",
    data() {
      return {
        
      }
    }
  }
</script>

<style lang="scss" scoped>
  .condition-title {
    font-size: 40rpx;
    font-weight: 700;
    margin: 25rpx 0;
    padding-top: 12rpx;
    border-top: 2rpx dashed #ccc;
  }
  .condition-item {
    text-align: center;
    margin-right: 30rpx;
    .iconfont {
      font-size: 60rpx;
    }
  }
</style>
