<template>
  <view class="exchange-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id">
            <template #right>
              <text style="background-color: #07C160;color:#fff;margin-bottom:8rpx;padding:0 20px;
              display:inline-block;text-align:center;height:160rpx;line-height:160rpx;" @click="handleAgree(item)">
                建立
              </text>
            </template>
            <list-card style="width: 100%;"
              :no="item.house.id"
              :thumb="item.house.head"
              :head="item.house.name"
              :price="item.house.moneyMonth"
              >
                <template #contentIntro>
                  <view style="color: #999; height: 80rpx;">
                    <view>
                      {{ '租客: '+ item.user.name + ', 电话: ' + item.user.phone }}
                    </view>
                    <view style="color: #ffbf2f;" v-if="item.time">
                      {{ "合约至: " + item.time }}
                    </view>
                  </view>
                </template>
              </list-card>
          </uni-swipe-action-item>
        </uni-swipe-action>
          <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>
import { geyAllContracts, setupContract } from "../../apis/con_exchange";

export default {
  name: "exchange",
  components: {},
  data() {
    return {
      dataList: []
    }
  },
  computed: {},
  methods: {
    getShoucang() {
      console.log("到底啦！");
    },
    handleAgree(item) {
      setupContract(item.id).then(res => {
        uni.showToast({
          title: "成功"
        });
        this.refreshPage();
      }).catch(err => {
        console.log(err);
      })
    },
    handleRefuse() {
      
    },
    refreshPage() {
      // 获取所有未建立合同
      geyAllContracts().then(res => {
        this.dataList = [];
        this.dataList.push(...res.data);
      }).catch(err => {
        console.log(err);
      });
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad() {
    // 获取所有未建立合同
    this.refreshPage();
    // // 建立的合同
    // getMyContracts().then(res => {
    //   this.dataList.push(...res.data);
    // }).then(err => {
    //   console.log(err);
    // });
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
