<template>
  <view class="exchange-wrapper">
    <uni-nav-bar>
      <view slot="left" @click="chooseCity">
        <text>{{location}}</text>
        <uni-icons size="20" type="arrowdown"></uni-icons>
      </view>
      <uni-easyinput placeholder="输入搜索" prefixIcon="search" v-model="textValue" confirmType="search" @confirm="onSearch" :clearable="false"/>
      <view slot="right" @click="onSearch">搜索</view>
    </uni-nav-bar>
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;" @scrolltolower="getSearch">
        <list-card v-for="item in dataList" :key="item.id"
          :no="item.id"
          :thumb="item.head"
          :head="item.name"
          :intro="item.style+'|'+item.area+'㎡|'+item.neighbourhood"
          :price="item.moneyMonth"
          >
          </list-card>
          <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
import { pageHouses } from "../../apis/house.js";

export default {
  name: "shoucang",
  components: {},
  data() {
    return {
      params: {
        current: 1,
        size: 7
      },
      textValue: "",
      location: "",
      dataList: []
    }
  },
  computed: {},
  methods: {
    getSearch() {
      console.log("到底啦！");
    },
    chooseCity() { // 跳转到"选择城市"页面
      uni.navigateTo({
        url: '/pages/city/city'
      });
    },
    setMyCity(location) { // 设置城区名字
      this.params.locationName = this.location = location;
      this.dataList = []; // 清空dataList
      this.params.current = 1; // 初始化页数
      pageHouses(this.params).then(res => {
        let records = res.data.records || [];
        this.dataList.push(...records);
        this.params.total = res.data.total;
        this.params.current ++;
      }).catch(err => {
        console.log(err);
      })
    },
    onSearch() {
      this.params.name = this.textValue;
      this.dataList = []; // 清空dataList
      this.params.current = 1; // 初始化页数
      pageHouses(this.params).then(res => {
        let records = res.data.records || [];
        this.dataList.push(...records);
        this.params.total = res.data.total;
        this.params.current ++;
      }).catch(err => {
        console.log(err);
      })
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad(options) {
    this.params.name = this.textValue = options.textValue;
    this.params.locationName = this.location = options.location;
    // 发出请求，搜索相关内容
    pageHouses(this.params).then(res => {
      let records = res.data.records || [];
      this.dataList.push(...records);
      this.params.total = res.data.total;
      this.params.current ++;
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

<style></style>
