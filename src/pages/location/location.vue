<template>
  <!-- <div class="location">location</div> -->
  <map
    :longitude="longitude"
    :latitude="latitude"
    markers=""
    style="width:100%;height:100vh;"
  />
</template>

<script>

export default {
  components: {},
  data() {
    return {
      longitude: 118.685713,
      latitude : 40.013305,
      address: ""
    }
  },
  computed: {},
  methods: {
    // geUsertLocation() { // 获取用户地理坐标
    //   uni.getLocation({
    //     type: 'gcj02',
    //     success: (res) => {
    //       // console.log(res.latitude, res.longitude);
    //       console.log(res);
    //       this.longitude = res.longitude;
    //       this.latitude = res.latitude;
    //     },
    //     fail: (error) => {
    //       console.log(error);
    //       uni.navigateBack({ delta: 1 });
    //     }
    //   })
    // },
    // getAuthModal() { // 申请授权Modal
    //   wx.showModal({
    //     title: '地理位置获取请求',
    //     content: '需要获取您的地理位置，请确认授权，否则地图功能将无法使用',
    //     confirmText: "确认",
    //     cancelText: "取消",
    //     success: function (res) {
    //       // console.log(res);
    //       //点击“确认”时打开设置页面
    //       if (res.confirm) {
    //         console.log('用户点击确认')
    //         wx.openSetting({
    //           success: (res) => { }
    //         })
    //       } else {
    //         console.log('用户点击取消')
    //       }
    //     }
    //   });
    // },
    getUserAddress() { // 用户选择地址
      uni.chooseLocation({
        success: ({ name, address, longitude, latitude }) => {
          console.log(name, longitude, latitude);
          this.address = address;
        },
        fail: (error) => {
          console.log(error);
        }
      })
    }
  },
  watch: {},

  // 页面周期函数--监听页面加载
  onLoad() {
    let that = this;
    wx.getSetting({
      success(res) {
        // if (res.authSetting['scope.userLocation']) {
        //   that.geUsertLocation();
        // } else {
        //   that.getAuthModal();
        // }
        if (res.authSetting['scope.address']) {
          that.getUserAddress();
        }
        uni.getLocation({
          type: 'gcj02',
          success: ({ longitude, latitude }) => {},
          fail: (error) => {}
        })
      } 
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
