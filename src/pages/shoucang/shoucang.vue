<template>
  <view class="shoucang-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getShoucang">
        <list-card v-for="item in dataList" :key="item.id"
          :no="item.id"
          :thumb="item.thumb"
          :tag="item.tag"
          :head="item.head"
          :intro="item.intro"
          :price="item.price"
          :shoucang="item.shoucang"
          :isShoucang="item.isShoucang"
          @clickShoucang="clickShoucang(item)" />
        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>

export default {
  name: "shoucang",
  components: {},
  data() {
    return {
      dataList: [
          {
            id: 1,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "热卖",
            head: "草桥欣园三区 央产证 南北通透 有钥匙 看两居室",
            intro: "2室1厅|75.1㎡|草桥欣园三区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          }
        ]
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
