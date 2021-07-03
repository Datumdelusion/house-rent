<template>
  <view class="houses-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getShoucang">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id">
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
              :thumb="item.head"
              :head="item.name"
              :intro="item.style+'|'+item.area+'㎡|'+item.neighbourhood"
              :price="item.moneyMonth"
              >
              </list-card>
          </uni-swipe-action-item>
        </uni-swipe-action>
        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
    <view class="icon-plus-wrapper" @click="addhouse">
      <text class="iconfont icon-jia" style="font-size: 50rpx;" />
    </view>
  </view>
</template>

<script>
import { myHouses } from "../../apis/house.js";

export default {
  name: "shoucang",
  components: {},
  data() {
    return {
      dataList: []
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
  onShow() {
    myHouses().then(res => {
      this.dataList = res.data.records;
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
