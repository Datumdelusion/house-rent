<template>
  <view class="house-owner-wrapper">
    <view class="owner-title">转租须知</view>
    <view class="owner-info">
      预计
      <text style="color: red">2021-07-15</text>
      可入住，需签约至
      <text style="color: red">2022-07-14</text>
    </view>
    <view class="owner-info">
      <view class="owner-info-prefix">
        <text style="color: #ff961e; font-size: 38rpx;" class="iconfont icon-dianhua" />
        <text style="color: #999;">联系方式:</text>
      </view>
      <text style="text-decoration: underline;">12345678910</text>
    </view>
    <view class="owner-info">
      <view class="owner-info-prefix">
        <text style="color: #1290BF; font-size: 38rpx;" class="iconfont icon-yonghu"/>
        <text style="color: #999;">转租者:</text>
      </view>
      小鹿姐姐
    </view>
  </view>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss" scoped>
  .owner-title {
    font-size: 40rpx;
    font-weight: 700;
    margin: 25rpx 0;
    padding-top: 12rpx;
    border-top: 2rpx dashed #ccc;
  }
  .owner-info {
    margin-top: 10rpx;
    .owner-info-prefix {
      display:inline-block;
      width:180rpx;
    }
  }
</style>
