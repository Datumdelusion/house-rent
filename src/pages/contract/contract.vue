<template>
  <view class="user-contract-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id">
            <template #right>
              <text style="background-color: #07C160;color:#fff;margin-bottom:8rpx;padding:0 20px;
              display:inline-block;text-align:center;height:182rpx;line-height:182rpx;" @click="handleConfirm(item)">
                确认
              </text>
              <text style="background-color: #dd524d;color:#fff;margin-bottom:8rpx;padding:0 20px;
              display:inline-block;text-align:center;height:182rpx;line-height:182rpx;" @click="handleDelete(item)">
                拒绝
              </text>
            </template>
            <uni-list style="width: 100%;">
              <uni-list-item :showArrow="true" :clickable="true" @click="turn2Page(item)">
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
                    <uni-tag text="租赁者同意" size="small" v-if="item.success" :inverted="true" type="success" :circle="true" style="margin-right: 50rpx;"></uni-tag>
                    <uni-tag text="出租者同意" size="small" v-if="item.userSign" :inverted="true" type="success" :circle="true"></uni-tag>                    
                  </view>
                </template>
              </uni-list-item>
            </uni-list> 
          </uni-swipe-action-item>
        </uni-swipe-action>
        <uni-popup type="dialog" ref="popup">
          <uni-popup-dialog mode="input" placeholder="租赁时长(单位:月)" @close="dialogClose" @confirm="dialogConfirm"></uni-popup-dialog>
        </uni-popup>
        
        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
import { getMyContracts, confirmContracts, rejectContracts } from "../../apis/contract.js";

export default {
  name: "contract",
  components: {},
  data() {
    return {
      currentId: 0,
      dataList: []
    }
  },
  computed: {},
  methods: {
    handleConfirm(item) { // 添加房屋
      this.currentId = item.id;
      this.$refs.popup.open();
    },
    handleDelete(item) { // 点击删除按钮
      // console.log("id: ", item.id);
      rejectContracts(item.id).then(res => {
        uni.showToast({
          title: "拒绝成功"
        });
        this.refreshPage();
      }).catch(err => {
        console.log(err);
      })
    },
    dialogClose() { // 关闭对话框
      this.$refs.popup.close();
    },
    dialogConfirm(event, value) { // 确认对话框
      // console.log(value);
      this.textValue = value;
      confirmContracts(this.currentId, value).then(res => {
        this.refreshPage();
        this.$refs.popup.close();
      }).catch(err => {
        console.log(err);
        this.$refs.popup.close();
      })
    },
    refreshPage() {
      uni.showLoading({
        title: '奋力加载中……'
      });
      getMyContracts().then(res => {
        this.dataList = res.data;
        uni.hideLoading();
      }).catch(err => {
        console.log(err);
        uni.hideLoading();
      })
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
    this.refreshPage();
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
