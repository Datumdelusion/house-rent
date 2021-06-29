<template>
  <view class="intro-wrapper">
    <view style="font-size: 40rpx; font-weight: 700;">观塘云鼎观塘云鼎观塘云鼎观塘云鼎观塘云鼎观塘云鼎</view>
    <view style="font-size: 32rpx; color: #ff961e; ">￥4630/月</view>
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
    <features-list />
  </view>
</template>

<script>
  import FeaturesList from "./FeaturesList.vue";
  
  export default {
    name: "ItemDetail",
    components: {
      FeaturesList
    }
  }
</script>

<style lang="scss" scoped>
  .intro-wrapper {
    padding: 20rpx 32rpx;
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
        font-size: 32rpx;
        font-weight: bold;
        border-right: 2rpx solid #ccc;
        text-align: center;
        color: #f40;
        &:last-of-type {
          border-right: none;
        }
        .remark {
          color: rgba(0,0,0,.4);
          font-size: 24rpx;
          font-weight: normal;
        }
      }
    }
  }
</style>