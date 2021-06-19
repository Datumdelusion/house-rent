<template>
  <uni-popup ref="popup" type="center">
    <view class="popup-content">
      <uni-icons type="spinner-cycle" />
    </view>
  </uni-popup>
</template>

<script>
  export default {
    methods: {
      open() {
        this.$refs.popup.open();
      }
    }
  }
</script>

<style lang="scss" scoped>
  .popup-content {
    width: 200rpx;
    text-align: center;
    display: inline-block;
    line-height: 200rpx;
    height: 200rpx;
    background-color: #fff;
  }
</style>
