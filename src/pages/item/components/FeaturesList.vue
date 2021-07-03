<template>
  <view class="feature-list-wrapper">
    <view class="feature-item-wrapper" style="display: block;width: 100%;">
      <text class="name">小区:</text>
      <text>{{ neighbourhood }}</text>
    </view>
    <view class="feature-item-wrapper" style="display: block;width: 100%;">
      <text class="name">位置:</text>
      <text>{{ detailLocation }}</text>
    </view>
    <view class="feature-item-wrapper">
      <text class="name">楼层:</text>
      <text>{{ storey }}</text>
    </view>
    <view class="feature-item-wrapper">
      <text class="name">电梯:</text>
      <text>{{ elevator ? '有' : '无' }}</text>
    </view>
    <view class="feature-item-wrapper">
      <text class="name">年代:</text>
      <text>{{ `${years}年建成` }}</text>
    </view>
    <view class="feature-item-wrapper">
      <text class="name">绿化:</text>
      <text>{{ parseInt(greenArea*100) + "%" }}</text>
    </view>
    <view class="feature-item-wrapper" style="display: block;width: 100%;">
      <text class="name">最低租赁时间:</text>
      <text>{{ leaseTerm + "月" }}</text>
    </view>
  </view>
</template>

<script>
  export default {
    name: "FeaturesList",
    props: ["neighbourhood", "greenArea", "elevator", "detailLocation", "storey", "years", "leaseTerm"]
  }
</script>

<style lang="scss" scoped>
  .feature-list-wrapper {
    & > .feature-item-wrapper {
      display: inline-block;
      width: 48%;
      margin-bottom: 10rpx;
    }
    .name {
      color: #999;
      margin-right: 4rpx;
    }
  }
</style>
