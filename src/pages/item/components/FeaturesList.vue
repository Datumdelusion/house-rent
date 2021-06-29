<template>
  <view class="feature-list-wrapper">
    <view>
      <text class="name">位置:</text>
      <text>坐北朝南</text>
    </view>
    <view>
      <text class="name">楼层:</text>
      <text>-1</text>
    </view>
    <view>
      <text class="name">电梯:</text>
      <text>有</text>
    </view>
    <view>
      <text class="name">年代:</text>
      <text>2007年建成</text>
    </view>
    <view>
      <text class="name">供暖:</text>
      <text>集体供暖</text>
    </view>
    <view>
      <text class="name">绿化:</text>
      <text>60%</text>
    </view>
  </view>
</template>

<script>
  export default {
    name: "FeaturesList",
    data() {
      return {
        
      }
    }
  }
</script>

<style lang="scss" scoped>
  .feature-list-wrapper {
    & > view {
      display: inline-block;
      width: 48%;
      margin-bottom: 4rpx;
    }
    .name {
      color: #999;
      margin-right: 4rpx;
    }
  }
</style>
