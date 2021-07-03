<template>
  <view class="user-contract-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id">
            <template #right>
              <text style="background-color: #4e92df;color:#fff;margin-bottom:8rpx;padding:0 20px;
                           display:inline-block;text-align:center;height:185rpx;line-height:185rpx;"
                @click="handleCheck(item)">
                租赁人
              </text>
              <text style="background-color: #07C160;color:#fff;margin-bottom:8rpx;padding:0 20px;
                           display:inline-block;text-align:center;height:185rpx;line-height:185rpx;"
                @click="handleConfirm(item)">
                确认
              </text>
              <text style="background-color: #dd524d;color:#fff;margin-bottom:8rpx;padding:0 20px;
                           display:inline-block;text-align:center;height:185rpx;line-height:185rpx;"
                @click="handleDelete(item)">
                拒绝
              </text>
            </template>
            <uni-list style="width: 100%;">
              <uni-list-item :clickable="true" :showArrow="true" @click="turn2Page(item)">
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
                    <uni-tag text="出租者拒绝" size="small" v-if="item.userSign===null" :inverted="true" type="success" :circle="true"></uni-tag>                   
                  </view>
                </template>
              </uni-list-item>
            </uni-list>
          </uni-swipe-action-item>
        </uni-swipe-action>
        <uni-popup type="dialog" ref="popup">
          <uni-popup-dialog title="租赁人信息" @close="dialogClose" @confirm="dialogConfirm">
            <template #default>
              <view style="padding: 20rpx 60rpx;">
                <view style="margin-bottom: 20rpx;">{{ "租赁人: " + userInfo.name }}</view>
                <view style="margin-bottom: 20rpx;">{{ "联系方式: " + userInfo.phone }}</view>                
              </view>
            </template>
          </uni-popup-dialog>
        </uni-popup>

        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
  import { getMyContracts, confirmContract, getUserInfo, rejectContract } from "../../apis/con_exchange.js";
  
  export default {
    name: "uncomplete",
    components: {},
    data() {
      return {
        userInfo: {},
        dataList: []
      }
    },
    computed: {},
    methods: {
      handleCheck(item) { //点击确认租赁人
        uni.showLoading({
          title: "奋力加载中……"
        });
        getUserInfo(item.userId).then(res => {
          this.userInfo = res.data;
          console.log(this.userInfo);
          this.$refs.popup.open();
          uni.hideLoading();
        }).catch(err => {
          console.log(err);
          uni.hideLoading();
        })
      },
      handleConfirm(item) { // 点击确定合同
        // console.log("1");
        uni.showLoading({
          title: "奋力加载中……"
        });
        confirmContract(item.id).then(res => {
          this.userInfo = res.data;
          this.refreshPage();
          uni.hideLoading();
        }).catch(err => {
          console.log(err);
          uni.hideLoading();
        })
      },
      handleDelete(item) { // 点击拒绝按钮
        // console.log("id: ", item.id);
        uni.showLoading({
          title: "奋力加载中……"
        });
        rejectContract(item.id).then(res => {
          this.userInfo = res.data;
          this.refreshPage();
          uni.hideLoading();
        }).catch(err => {
          console.log(err);
          uni.hideLoading();
        })
      },
      dialogClose() { // 关闭对话框
        this.$refs.popup.close();
      },
      dialogConfirm(event, value) { // 确认对话框
        this.$refs.popup.close();
      },
      turn2Page(item) {
        uni.navigateTo({
          url: `../item/item?id=${item.houseId}`
        })
      },
      refreshPage() {
        getMyContracts().then(res => {
          this.dataList = res.data;
        }).catch(err => {
          console.log(err);
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
