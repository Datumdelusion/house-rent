<template>
  <view class="contract-wrapper">
    <view>
      <scroll-view scroll-y="true" style="height: 100vh;">
        <uni-swipe-action>
          <uni-swipe-action-item v-for="item in dataList" :key="item.id">
            <template #right>
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
              :isBtn="true"
              >
                <template #btnGroup>
                  <button type="primary" size="mini" @click.stop="handleConfirm">确认合约</button>
                </template>
              </list-card>
          </uni-swipe-action-item>
        </uni-swipe-action>
        <uni-popup type="dialog" ref="popup">
          <uni-popup-dialog mode="input" placeholder="合同的截止日期(如2021-7-5)" @close="dialogClose" @confirm="dialogConfirm"></uni-popup-dialog>
        </uni-popup>
        
        <van-empty v-if="dataList.length === 0" description="噢, 这里似乎空空如也..." />
      </scroll-view>
    </view>
  </view>
</template>

<script>

export default {
  name: "contract",
  components: {},
  data() {
    return {
      textValue: "",
      dataList: [
          {
            id: 2,
            thumb: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
            head: "草桥欣园一区 央产证 南北通透 有钥匙 看两居室",
            intro: "3室2厅|76.1㎡|草桥欣园一区",
            price: "6500万",
          }
        ]
    }
  },
  computed: {},
  methods: {
    getShoucang() { // 滚动到底部
      console.log("到底啦！");
    },
    handleConfirm() { // 添加房屋
      // console.log("1");
      this.$refs.popup.open();
    },
    handleDelete(item) { // 点击删除按钮
      console.log("id: ", item.id);
    },
    dialogClose() { // 关闭对话框
      this.$refs.popup.close();
    },
    dialogConfirm(event, value) { // 确认对话框
      console.log(value);
      this.textValue = value;
      this.$refs.popup.close();
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

<style></style>
