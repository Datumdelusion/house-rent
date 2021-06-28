<template>
  <view class="type-icon">
    <view class="type-wrapper" v-for="item in types" :key="item.id">
      <!-- <uni-icons size="25" class="one-type-icon" :type="item.icon" :style="{'backgroundColor': randomColor(item.id)}" /> -->
      <view class="one-type-icon" :style="{'backgroundColor': randomColor(item.id)}">
        <text class="iconfont" :class="item.icon" />
      </view>
      <view>{{ item.name }}</view>
    </view>
  </view>
</template>

<script>
  export default {
    props: {},
    data() {
      return {
        types: [{
            id: "1",
            name: "二手房",
            icon: "icon-fangwu"
          },
          {
            id: "2",
            name: "新房",
            icon: "icon-fangwufangchan"
          },
          {
            id: "3",
            name: "租房",
            icon: "icon-fangwuzujin"
          },
        ]
      }
    },
    computed: {
      randomColor() {
        return (id) => {
          let r = Math.floor(Math.random() * 90 + 150);
          let g = Math.floor(Math.random() * 90 + 150);
          let b = Math.floor(Math.random() * 90 + 150);
          return "rgb(" + r + "," + g + "," + b + ")";
        }
      }
    },
    methods: {},
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

<style lang="scss" scoped>
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
    color: #fff;
    width: 80rpx;
    height: 80rpx;
    line-height: 80rpx;
    text-align: center;
    font-weight: normal;
    .iconfont {
      color: #333;
      font-size: 44rpx;
    }
  }
</style>
