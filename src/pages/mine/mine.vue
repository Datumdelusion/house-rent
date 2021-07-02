<template>
  <view class="mine">
    <view class="mine-header" @click="turn2login">
      <image style="width: 130rpx; height: 130rpx; background-color: #eeeeee;border-radius: 50%;" src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"></image>
      <text style="margin-left: 30rpx; color: #fff;height: 70rpx;">点击注册/登录</text>
    </view>
    <uni-card>
      <uni-list :border="false">
        <uni-list-item clickable link to="../order/order" :ellipsis="1" title="历史交易">
          <template #header>
            <text class="iconfont icon-zhangdan-xianxing" style="font-size: 36rpx; margin-right: 10rpx"/>
          </template>
        </uni-list-item>
        <uni-list-item clickable link to="../exchange/exchange" :ellipsis="1" title="我的交易">
          <template #header>
            <text class="iconfont icon-caiwu-xianxing" style="font-size: 36rpx; margin-right: 10rpx"/>
          </template>
        </uni-list-item>
        <uni-list-item clickable link to="../houses/houses" :ellipsis="1" title="我的房子">
          <template #header>
            <text class="iconfont icon-fangwu" style="font-size: 36rpx; margin-right: 10rpx"/>
          </template>
        </uni-list-item>
        <uni-list-item clickable link="switchTab" to="../shoucang/shoucang" :ellipsis="1" title="我的收藏">
          <template #header>
            <text class="iconfont icon-shoucang-xianxing" style="font-size: 36rpx; margin-right: 10rpx"/>
          </template>
        </uni-list-item>
      </uni-list>
    </uni-card>
    <button type="default" class="mine-button" v-if="true">退出登录</button>
  </view>
</template>

<script>

export default {
  components: {},
  data() {
    return {
      
    }
  },
  computed: {},
  methods: {
    turn2login() { // 跳转到login页面
      uni.navigateTo({
        url: "../login/login"
      })
    }
  },
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
  .mine-header {
    background: linear-gradient(90deg, #00c6ff 0%, #0072ff 100%);
    width: 100%;
    display: flex;
    padding: 20rpx 0 20rpx 25rpx;
    justify-content: flex-start;
    align-items: center;
  }
  .mine-button {
    width: 90%;
    bottom: 35rpx;
    margin-top: 170rpx;
    background: linear-gradient(to right, #00d2ff, #3a7bd5);
    color: #fff;
  }
</style>
