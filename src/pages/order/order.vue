<template>
  <view class="order-wrapper">
    <view>
      <van-dropdown-menu>
        <van-dropdown-item v-model="currentValue" :options="option" @change="changeValue"/>
      </van-dropdown-menu>
      <scroll-view scroll-y="true" style="height: 100vh;">
        <uni-list>
          <uni-list-item :showArrow="true" :clickable="true" @click="turn2Page(item)" v-for="item in dataList" :key="item.id">
            <template #body>
              <view>{{ item.houseName }}</view>
              <view style="color:#999;font-size:26rpx;margin-bottom: 5rpx;">
                <view>
                  {{ "租赁期限: " + (item.time?item.time:'暂未确定') }}
                </view>
                <view>
                  {{ "总价: " + (item.rentMoney?item.rentMoney:'暂未确定') }}
                </view>
              </view>
              <view style="display: flex;">
                <uni-tag text="出租者同意" size="small" v-if="item.success" :inverted="true" type="success" :circle="true" style="margin-right: 50rpx;"></uni-tag>
                <uni-tag text="租赁者同意" size="small" v-if="item.userSign" :inverted="true" type="success" :circle="true"></uni-tag>                    
                <uni-tag text="租赁者拒绝" size="small" v-if="item.userSign===false" :inverted="true" type="error" :circle="true" style="margin-right: 50rpx;"></uni-tag>
                <uni-tag text="出租者拒绝" size="small" v-if="item.userSign===null" :inverted="true" type="error" :circle="true"></uni-tag>                   
              </view>
            </template>
          </uni-list-item>
        </uni-list> 
        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
import { historyOrders } from "../../apis/order.js";

export default {
  name: "order",
  components: {},
  data() {
    return {
      option: [
        { text: '成功订单', value: "success" },
        { text: '失败订单', value: "failed" }
      ],
      currentValue: "success",
      dataList: []
    }
  },
  computed: {},
  methods: {
    getHistory() {
      historyOrders(this.currentValue).then(res => {
        this.dataList = [];
        this.dataList.push(...res.data);
      }).then(err => {console.log(err);})
    },
    changeValue(e) {
      // console.log(e.detail);
      this.currentValue = e.detail;
      this.getHistory();
    },
    turn2Page(item) {
      uni.navigateTo({
        url: `../item/item?id=${item.houseId}`
      })
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad() {
    this.getHistory();
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
