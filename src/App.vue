<script>
  export default {
    onLaunch: function() {
      console.log('App Launch')
    },
    onShow: function() {
      console.log('App Show')
    },
    onHide: function() {
      console.log('App Hide')
    },
  }
</script>

<style>
  /*每个页面公共css */
  @import url("static/font/iconfont.css");

  page {
    --bgColor: #fcfcfc;
    background-color: var(--bgColor);
  }

  .my-card {
    background-color: #fff;
    padding: 10rpx 6rpx;
    border-radius: 5rpx;
    margin: 4rpx 10rpx;
    box-shadow: 0 2rpx 6rpx rgba(0, 0, 0, .2);
  }
</style>
