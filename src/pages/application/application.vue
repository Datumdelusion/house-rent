<template>
  <view class="application">
    <view class="application-options-head">
      <text v-for="(item,index) in options" :class="currentOption === index ? 'option-active' : ''" @click="changeActive(index)">
        {{ item }}
        <text class="iconfont" :class="currentOption === index ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
      </text>
    </view>
  </view>
</template>

<script>

export default {
  components: {},
  data() {
    return {
      currentOption: -1,
      options: ["区域", "类型", "租金", "房源特色"]
    }
  },
  computed: {},
  methods: {
    changeActive(index) { // 根据点击修改激活状态
      this.currentOption = index;
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
