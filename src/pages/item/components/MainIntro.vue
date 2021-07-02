<template>
  <view class="intro-wrapper">
    <view style="font-size: 40rpx; font-weight: 700;">{{ name }}</view>
    <view style="font-size: 36rpx; color: #f40;">
      {{ `￥${moneyMonth}/月` }}
      <text class="mylove-icon iconfont" :class=" star ? 'icon-xihuan' : 'icon-xihuan-xianxing' "  @click="starUpdate"/>
    </view>
    <view class="main-introduction-wrapper">
      <view class="intro-item">
        <view class="price">{{  area + "㎡" }}</view>
        <view class="remark">使用面积</view>
      </view>
      <view class="intro-item">
        <view class="direction">{{ direction[orientation] }}</view>
        <view class="remark">朝向</view>
      </view>
      <view class="intro-item">
        <view class="size">{{ style }}</view>
        <view class="remark">户型</view>
      </view>
    </view>
  </view>
</template>

<script>
  
  export default {
    name: "MainIntro",
    props: ["name", "moneyMonth", "area", "orientation", "style", "star"],
    data() {
      return {
        direction: {
          "EAST": "东",
          "NORTH": "北",
          "SOUTH": "南",
          "WEST": "西"
        }
      }
    },
    methods: {
      starUpdate() {
        this.$emit("starUpdate");
      }
    }
  }
</script>

<style lang="scss" scoped>
  .intro-wrapper {
    .main-introduction-wrapper {
      display: flex;
      justify-content: center;
      align-items: center;
      margin: 10rpx 0;
      .intro-item {
        width: 33.33%;
        height: 100rpx;
        box-sizing: border-box;
        color: rgba(0,0,0,.85);
        font-size: 38rpx;
        font-weight: bold;
        border-right: 2rpx solid #ccc;
        text-align: center;
        &:last-of-type {
          border-right: none;
        }
        .remark {
          color: rgba(0,0,0,.4);
          font-size: 26rpx;
          font-weight: normal;
        }
      }
    }
    .mylove-icon {
      color: #333;
      font-size: 50rpx;
      float: right;
    }
    .mylove-icon.icon-xihuan {
      color: red;
      animation: mylove 0.5s linear;
    }
  }
  
  @keyframes mylove {
    from { transform: scale(1);}
    50% { transform: scale(1.3);}
    to { transform: scale(1);}
  }
</style>