<template>
  <view>
    <uni-search-bar v-model="value" placeholder="请输入搜索关键词" @confirm="onSearch" @clear="onSearch" />
    <uni-indexed-list :options="dataList" @click="onClick" v-if="showCity" />
    <view v-else>
      <view class="search-head">找到{{ resultList.length }}个结果</view>
      <view class="result-list" v-for="(item, index) in resultList" :key="index" @click="onClick(item)">
        {{ item }}
      </view>
    </view>
  </view>
</template>

<script>
import { getLocations } from "../../apis/dictionary.js";

export default {
  data() {
    return {
      dataList: [
        {letter: "B", data: ["北碚区", "巴南区", "璧山区"]},
        {letter: "D", data: ["大渡口区", "大足区"]},
        {letter: "F", data: ["涪陵区"]},
        {letter: "H", data: ["合川区"]},
        {letter: "J", data: ["江北区", "江津区", "九龙坡区"]},
        {letter: "K", data: ["开州区"]},
        {letter: "L", data: ["梁平区"]},
        {letter: "N", data: ["南岸区", "南川区"]},
        {letter: "Q", data: ["黔江区", "綦江区"]},
        {letter: "R", data: ["荣昌区"]},
        {letter: "S", data: ["沙坪坝区"]},
        {letter: "T", data: ["潼南区"]},
        {letter: "W", data: ["武隆区", "万州区"]},
        {letter: "Y", data: ["渝北区", "永川区", "渝中区"]},
        {letter: "Z", data: ["长寿区"]}
      ],
      value: "",
      msg: "",
      showCity: true,
      resultList: []
    }
  },
  computed: {},
  methods: {
    onSearch(value) { // TODO:搜索
      uni.showLoading({
        title: "搜索ing..."
      });
      if (value && value.value) { // 若搜索内容不为空，查找相关内容
        this.resultList = this.filterBySearch(value.value);
        this.showCity = false;
      } else { // 若搜索内容为空
        this.resultList = [];
        this.showCity = true;
      }
      uni.hideLoading();
    },
    filterBySearch(value) {
      let arr = [];
      this.dataList.forEach(v => {
        v.data.forEach(i => {
          if (i.includes(value)) {
            arr.push(i);
          }
        })
      });
      return arr;
    },
    onClick(msg) {
      let pages = getCurrentPages();
      let prevPage = pages[pages.length - 2]; // 上一个页面
      if (msg.item) {
        // {"item": {"key":"A", "name": "A2城市", "itemIndex": 1, "checked": false}, "select":[]}
        prevPage.$vm.setMyCity(msg.item.name);
        uni.navigateBack();
      } else {
        // console.log(msg);
        prevPage.$vm.setMyCity(msg);
        uni.navigateBack();
      }
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad() {
    getLocations().then(res => { // 获取城区并监听
      let data = res.data || [];
      for(let i of data) {
        // console.log(i);
      }
    }).catch(err => {
      console.log(err);
    })
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

<style lang="scss" scoped>
  .search-head {
    background-color: #f7f7f7;
    padding: 14rpx 10rpx;
    border-bottom: 2rpx solid rgba(0, 0, 0 , 0.3);
  }
  /deep/ .uni-indexed-list {
    margin-top: 88rpx;
  }
  .result-list {
    margin: 28rpx;
    border-bottom: 2rpx solid #ccc;
  }
</style>
