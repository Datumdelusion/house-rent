<template>
  <view class="application-wrapper">
    <app-head />
    <view style="height: 80vh;">
      <scroll-view scroll-y="true" :refresher-enabled="true">
        <list-card v-for="item in dataList" :key="item.id"
          :thumb="item.thumb"
          :tag="item.tag"
          :head="item.head"
          :intro="item.intro"
          :price="item.price"
          :shoucang="item.shoucang"
          :isShoucang="item.isShoucang"
          @turn2Page="turn2Page"
          @clickShoucang="clickShoucang(item)" />
      </scroll-view>      
    </view>
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
        dataList: [
          {
            id: 1,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "热卖",
            head: "草桥欣园三区 央产证 南北通透 有钥匙 看两居室",
            intro: "2室1厅|75.1㎡|草桥欣园三区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 2,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "HOT",
            head: "草桥欣园一区 央产证 南北通透 有钥匙 看两居室",
            intro: "3室2厅|76.1㎡|草桥欣园一区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 3,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 4,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 5,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 6,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          },
          {
            id: 7,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: true,
            isShoucang: false
          }
        ]
      }
    },
    computed: {},
    methods: {
      clickShoucang(item) {
        this.dataList.forEach(v => {
          if (v.id === item.id) {
            v.isShoucang = !v.isShoucang;
          }
        })
      },
      turn2Page(item) { // 点击跳转页面
      console.log("111");
        // uni.navigateTo({
        //   url: '/pages/application/application'
        // });
      },
      clickShoucang(item) { // 点击收藏
        item.isShoucang = !item.isShoucang;
      },
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
