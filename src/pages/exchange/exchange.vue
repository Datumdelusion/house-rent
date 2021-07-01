<template>
  <view class="exchange-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getShoucang">
        <list-card v-for="item in dataList" :key="item.id"
          :no="item.id"
          :thumb="item.thumb"
          :tag="item.tag"
          :head="item.head"
          :intro="item.intro"
          :price="item.price"
          :isShoucang="item.isShoucang"
          :isBtn="true"
          >
            <template #btnGroup>
              <button type="default" size="mini" style="background-color: #07C160; color: #fff;margin-right: 10rpx;">同意</button>
              <button type="default" size="mini" style="background-color: #EE0A24; color: #fff;margin-right: 10rpx;">拒绝</button>
            </template>
          </list-card>
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
            head: "草桥欣园三区 央产证 南北通透 有钥匙 看两居室",
            intro: "2室1厅|75.1㎡|草桥欣园三区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 2,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            head: "草桥欣园一区 央产证 南北通透 有钥匙 看两居室",
            intro: "3室2厅|76.1㎡|草桥欣园一区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 3,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 4,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 5,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 6,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: true
          },
          {
            id: 7,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
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
