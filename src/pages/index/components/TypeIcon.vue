<template>
  <view class="type-icon">
    <view class="type-wrapper" v-for="item in types" :key="item.id">
      <van-icon class="one-type-icon" :name="item.icon" :style="{'backgroundColor': randomColor(item.id)}"/>
      <view>{{ item.name }}</view>
    </view>
  </view>
</template>

<script>

export default {
  props: {},
  data() {
    return {
      types: [
        {id: "1", name: "二手房", icon: "wap-home"},
        {id: "2", name: "新房", icon: "hotel-o"},
        {id: "3", name: "租房", icon: "goods-collect-o"},
      ]
    }
  },
  computed: {
    randomColor() {
      return (id) => {
        let r = Math.floor(Math.random()*130 + 110);
        let g = Math.floor(Math.random()*130 + 110);
        let b = Math.floor(Math.random()*130 + 110);
        return "rgb("+r+","+g+","+b+")";
      }
    }
  },
  methods: {
  },
  watch: {},

  // 组件周期函数--监听组件挂载完毕
  mounted() {},
  // 组件周期函数--监听组件数据更新之前
  beforeUpdate() {},
  // 组件周期函数--监听组件数据更新之后
  updated() {},
  // 组件周期函数--监听组件激活(显示)
  activated() {},
  // 组件周期函数--监听组件停用(隐藏)
  deactivated() {},
  // 组件周期函数--监听组件销毁之前
  beforeDestroy() {},
};
</script>

<style>
.type-icon {
  margin-bottom: 10rpx;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.type-wrapper {
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
  font-size: 28rpx;
  font-weight: bold;
}
.one-type-icon {
  border-radius: 50%;
  font-size: 40rpx;
  color: #fff;
  width: 80rpx;
  height: 80rpx;
  /* background-color: red; */
}
</style>
