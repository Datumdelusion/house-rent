<template>
  <view>
    <uni-search-bar shape="round" v-model="value" placeholder="请输入搜索关键词" />
    <uni-indexed-list :options="dataList" @click="onClick" style="margin-top: 88rpx;"/>
    <uni-popup ref="popup" type="center">
      <!-- <text class="popup-content">{{ msg }}</text> -->
      <uni-icons class="popup-content" size="25" type="spinner-cycle" />
    </uni-popup>
  </view>
</template>

<script>

export default {
  data() {
    return {
      dataList: [
        {letter: "A", data: ["A1城市", "A2城市", "A3城市", "A4城市", "A5城市", "A6城市"]},
        {letter: "B", data: ["B1城市", "B2城市", "B3城市", "B4城市", "B5城市", "B6城市"]},
        {letter: "C", data: ["C1城市", "C2城市", "C3城市", "C4城市", "C5城市", "C6城市"]},
        {letter: "D", data: ["D1城市", "D2城市", "D3城市", "D4城市", "D5城市", "D6城市"]},
        {letter: "E", data: ["E1城市", "E2城市", "E3城市", "E4城市", "E5城市", "E6城市"]},
        {letter: "F", data: ["F1城市", "F2城市", "F3城市", "F4城市", "F5城市", "F6城市"]},
        {letter: "G", data: ["G1城市", "G2城市", "G3城市", "G4城市", "G5城市", "G6城市"]},
        {letter: "H", data: ["H1城市", "H2城市", "H3城市", "H4城市", "H5城市", "H6城市"]},
        {letter: "I", data: ["I1城市", "I2城市", "I3城市", "I4城市", "I5城市", "I6城市"]},
        {letter: "J", data: ["J1城市", "J2城市", "J3城市", "J4城市", "J5城市", "J6城市"]},
        {letter: "K", data: ["K1城市", "K2城市", "K3城市", "K4城市", "K5城市", "K6城市"]},
        {letter: "L", data: ["L1城市", "L2城市", "L3城市", "L4城市", "L5城市", "L6城市"]},
        {letter: "M", data: ["M1城市", "M2城市", "M3城市", "M4城市", "M5城市", "M6城市"]},
        {letter: "N", data: ["N1城市", "N2城市", "N3城市", "N4城市", "N5城市", "N6城市"]},
        {letter: "O", data: ["O1城市", "O2城市", "O3城市", "O4城市", "O5城市", "O6城市"]},
        {letter: "P", data: ["P1城市", "P2城市", "P3城市", "P4城市", "P5城市", "P6城市"]},
        {letter: "Q", data: ["Q1城市", "Q2城市", "Q3城市", "Q4城市", "Q5城市", "Q6城市"]},
        {letter: "R", data: ["R1城市", "R2城市", "R3城市", "R4城市", "R5城市", "R6城市"]},
        {letter: "S", data: ["S1城市", "S2城市", "S3城市", "S4城市", "S5城市", "S6城市"]},
        {letter: "T", data: ["T1城市", "T2城市", "T3城市", "T4城市", "T5城市", "T6城市"]},
        {letter: "U", data: ["U1城市", "U2城市", "U3城市", "U4城市", "U5城市", "U6城市"]},
        {letter: "V", data: ["V1城市", "V2城市", "V3城市", "V4城市", "V5城市", "V6城市"]},
        {letter: "W", data: ["W1城市", "W2城市", "W3城市", "W4城市", "W5城市", "W6城市"]},
        {letter: "X", data: ["X1城市", "X2城市", "X3城市", "X4城市", "X5城市", "X6城市"]}
      ],
      value: "",
      msg: ""
    }
  },
  computed: {},
  methods: {
    onSearch(value) { // TODO:搜索
      console.log(value);
    },
    onClick(msg) { // {"item": {"key":"A", "name": "A2城市", "itemIndex": 1, "checked": false}, "select":[]}
      this.msg = msg.item.name;
      this.$refs.popup.open();
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
  .popup-content {
    width: 100rpx;
    text-align: center;
    display: inline-block;
    line-height: 100rpx;
    height: 100rpx;
    // background-color: #fff;
    color: #409eff;
    -webkit-animation: load 1.2s linear 0s infinite;
  }
  @-webkit-keyframes load {
    0% {transform: rotate(0);}
    100% {transform: rotate(360deg);}
  }
</style>
