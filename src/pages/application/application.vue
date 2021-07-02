<template>
  <view class="application-wrapper">
    <app-head @refresh="refresh"/>
    <view style="margin-top: 78rpx;">
      <scroll-view scroll-y="true" style="height: 93vh;" @scrolltolower="pullHouses">
        <list-card v-for="item in dataList" :key="item.id"
          :no="item.id"
          :thumb="item.head"
          :head="item.name"
          :intro="item.style+'|'+item.area+'㎡|'+item.neighbourhood"
          :price="item.moneyMonth"
        />
        <van-empty v-if="dataList.length === 0" image="search" description="找到了一片荒漠" />
      </scroll-view>
    </view>
  </view>
</template>

<script>
  import AppHead from "./components/AppHead.vue";
  import { pageHouses } from "../../apis/house.js";

  export default {
    components: {
      AppHead
    },
    data() {
      return {
        params: {
          current: 1,
          size: 7
        },
        dataList: []
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
      clickShoucang(item) { // 点击收藏
        item.isShoucang = !item.isShoucang;
      },
      getCard() { // 滚动到底部拉取更多
        console.log("到底啦！");
      },
      refresh(searchCondition) {
        this.params.total = undefined;
        this.params.current = 1;
        this.params.locationName = searchCondition.locationName;
        this.params.upperPrice = searchCondition.upperPrice;
        this.dataList = [];
        this.pullHouses();
      },
      pullHouses() {
        if (!this.params.total || this.params.total > this.dataList.length) {
          pageHouses(this.params).then(res => {
            let records = res.data.records || [];
            this.dataList.push(...records);
            this.params.total = res.data.total;
            this.params.current ++;
          }).catch(err => {
            console.log(err);
          })
        }
      }
    },
    watch: {},

    // 页面周期函数--监听页面加载
    onLoad() {
      this.pullHouses();
    },
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
