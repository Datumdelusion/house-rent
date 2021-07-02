<template>
  <view class="item-condition-wrapper">
    <view class="condition-title">房屋条件</view>
    <!-- 图标以及相关名字 -->
    <view style="display: flex; align-items: center;">
      <view class="condition-item" v-for="item in iconList">
        <text class="iconfont" :class="item"/>
        <view>{{ items[item] }}</view>
      </view>
    </view>
  </view>
</template>

<script>
  export default {
    name: "ItemCondition",
    props: ["usp"],
    mounted() {
      this.$nextTick(function(){
        console.log(this.$props);
        console.log(this.usp);
        // this.iconList = this.$props.usp.filter(v => {
        //   return this.items.hasOwnProperty(v);
        // });
      })
    },
    data() {
      return {
        items: {
            "icon-luyouqi": "路由器",
            "icon-bingxiang": "冰箱",
            "icon-kongtiao": "空调",
            "icon-youyanji": "油烟机",
            "icon-xiyiji": "洗衣机",
            "icon-chuang": "床",
            "icon-weibolu": "微波炉",
            "icon-yigui": "衣柜"
        },
        iconList: []
      }
    }
  }
</script>

<style lang="scss" scoped>
  .condition-title {
    font-size: 40rpx;
    font-weight: 700;
    margin: 25rpx 0;
    padding-top: 12rpx;
    border-top: 2rpx dashed #ccc;
  }
  .condition-item {
    text-align: center;
    margin-right: 30rpx;
    .iconfont {
      font-size: 60rpx;
    }
  }
</style>
