<template>
  <view class="application">
    <app-head :range="range" />
  </view>
</template>

<script>
  import AppHead from "./components/AppHead.vue";
  export default {
    components: {
      AppHead
    },
    data() {
      return {
        range: [
          [1,11,12,13,14,15,16,17,18,19]
        ],
      }
    },
    computed: {},
    methods: {},
    watch: {},

    // 页面周期函数--监听页面加载
    onLoad() {},
    // 页面周期函数--监听页面初次渲染完成
    onReady() {},
    // 页面周期函数--监听页面显示(not-nvue)
    onShow() {},
    // 页面周期函数--监听页面隐藏
    onHide() {},
    // 页面周期函数--监听页面卸载
    onUnload() {},
    // 页面处理函数--监听用户下拉动作
    onPullDownRefresh() {
      uni.stopPullDownRefresh();
    },
    // 页面处理函数--监听用户上拉触底
    onReachBottom() {},
    // 页面处理函数--监听页面滚动(not-nvue)
    /* onPageScroll(event) {}, */
    // 页面处理函数--用户点击右上角分享
    /* onShareAppMessage(options) {}, */
  };
</script>

<style lang="scss" scoped>
  .application-options-head {
    width: 100%;
    padding: 18rpx 0;
    display: flex;
    justify-content: space-around;
    align-items: center;
    background: linear-gradient(to top, #FAF8F9, #F0EFF0);

    .option-active {
      color: #2281b3;
    }
  }
</style>
