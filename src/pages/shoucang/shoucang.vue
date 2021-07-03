<template>
  <view class="shoucang-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getShoucang">
        <list-card v-for="item in dataList" :key="item.id"
          :no="item.house.id"
          :thumb="item.house.head"
          :head="item.house.name"
          :intro="item.house.style+'|'+item.house.area+'㎡|'+item.house.neighbourhood"
          :price="item.house.moneyMonth" />
        <van-empty v-if="isLogin && dataList.length === 0" description="登录后查询" />
        <van-empty v-if="!isLogin && dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
import { myStars } from "../../apis/star.js";

export default {
  name: "shoucang",
  components: {},
  data() {
    return {
      isLogin: uni.getStorageSync("isLogin"),
      dataList: []
    }
  },
  computed: {},
  methods: {
    getShoucang() {
      console.log("到底啦！");
    },
    clickShoucang(item) {
      item.isShoucang = !item.isShoucang;
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad() {},
  // 页面周期函数--监听页面初次渲染完成
  onReady() {},
  // 页面周期函数--监听页面显示(not-nvue)
  onShow() {
    myStars().then(res => {
      this.dataList = res.data;
    }).catch(err => {
      console.log(err);
    })
  },
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
