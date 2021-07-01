<template>
  <view class="houses-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getShoucang">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id" @click="bindClick">
            <template #right>
              <text style="background-color: #07C160;color:#fff;margin-bottom:8rpx;padding:0 20px;
              display:inline-block;text-align:center;height:160rpx;line-height:160rpx;" @click="handleChange(item)">
                修改
              </text>
              <text style="background-color: #dd524d;color:#fff;margin-bottom:8rpx;padding:0 20px;
              display:inline-block;text-align:center;height:160rpx;line-height:160rpx;" @click="handleDelete(item)">
                删除
              </text>
            </template>
            <list-card
              style="width: 100%;"
              :no="item.id"
              :thumb="item.thumb"
              :tag="item.tag"
              :head="item.head"
              :intro="item.intro"
              :price="item.price"
              :shoucang="item.shoucang"
              :isShoucang="item.isShoucang"
              :isBtn="true"
              >
                <template #btnGroup>
                  <button type="default" size="mini" style="background-color: #EE0A24; color: #fff;" v-if="true">下架</button>
                  <button type="default" size="mini" style="background-color: #07C160; color: #fff;" v-else>上架</button>
                </template>
              </list-card>
          </uni-swipe-action-item>
        </uni-swipe-action>
      </scroll-view>
    </view>
    <view class="icon-plus-wrapper" @click="addhouse">
      <text class="iconfont icon-jia" style="font-size: 50rpx;" />
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
            id: 2,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            head: "草桥欣园一区 央产证 南北通透 有钥匙 看两居室",
            intro: "3室2厅|76.1㎡|草桥欣园一区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          },
          {
            id: 3,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          },
          {
            id: 4,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          },
          {
            id: 5,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          },
          {
            id: 6,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          },
          {
            id: 7,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            tag: "SALE",
            head: "草桥欣园二区 央产证 南北通透 有钥匙 看两居室",
            intro: "5室4厅|77.1㎡|草桥欣园二区",
            price: "6500万",
            shoucang: false,
            isShoucang: true
          }
        ]
    }
  },
  computed: {},
  methods: {
    getShoucang() { // 滚动到底部
      console.log("到底啦！");
    },
    addhouse() { // 添加房屋
      uni.navigateTo({
        url: '../addhouse/addhouse'
      });
    },
    handleDelete(item) { // 点击删除按钮
      console.log("id: ", item.id);
    },
    handleChange(item) { // 点击修改按钮
      uni.navigateTo({
        url: `../addhouse/addhouse?id=${item.id}`
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
  .icon-plus-wrapper {
    border-radius: 50%;
    width: 80rpx;
    height: 80rpx;
    line-height: 80rpx;
    background-color: rgb(0, 122, 255);
    color: #fff;
    text-align: center;
    position: absolute;
    bottom: 80rpx;
    right: 20rpx;
  }
</style>
