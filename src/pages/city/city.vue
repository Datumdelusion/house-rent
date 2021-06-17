<template>
  <view>
    <view class="nav-bar-wrapper">
      <nav-bar @onSearch="onSearch"/>
    </view>
    <van-index-bar>
      <view v-for="item in dataList" :key="item.index">
        <van-index-anchor :index="item.index" />
        <van-cell v-for="(i, index) in item.cities" :title="i" :key="index" />
      </view>
    </van-index-bar>
  </view>
</template>

<script>
import NavBar from "./components/NavBar";

export default {
  components: {
    NavBar
  },
  data() {
    return {
      dataList: [
        {index: "A", cities: ["A1城市", "A2城市", "A3城市", "A4城市", "A5城市", "A6城市"]},
        {index: "B", cities: ["B1城市", "B2城市", "B3城市", "B4城市", "B5城市", "B6城市"]},
        {index: "C", cities: ["C1城市", "C2城市", "C3城市", "C4城市", "C5城市", "C6城市"]},
        {index: "D", cities: ["D1城市", "D2城市", "D3城市", "D4城市", "D5城市", "D6城市"]},
        {index: "E", cities: ["E1城市", "E2城市", "E3城市", "E4城市", "E5城市", "E6城市"]}
      ]
    }
  },
  computed: {},
  methods: {
    onSearch(value) { // TODO:搜索
      console.log(value);
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

<style></style>
