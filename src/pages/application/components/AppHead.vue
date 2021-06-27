<template>
  <view class="application">
    <view class="application-options-head">
      <text v-for="(item,index) in options" :class="currentOption === index ? 'option-active' : ''"
        @click="changeActive(index)">
        {{ item }}
        <text class="iconfont" :class="currentOption === index ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
      </text>
    </view>
    <uni-popup class="myPopup" ref="popup" type="top">
      <view class="myPopup-content">
        <picker-view v-if="true" indicator-style="height: 100px;" :value="value" @change="bindChange">
          <picker-view-column>
            <view v-for="v in 10"> 第{{ v }}个 </view>
          </picker-view-column>
        </picker-view>
      </view>
    </uni-popup>
      <!-- <picker @change="bindChange" :value="value" :range="range" mode="multiSelector">
        <view :class="currentOption === 0 ? 'option-active' : ''" @click="changeActive(0)">
          <text>区域</text>
          <text class="iconfont" :class="currentOption === 0 ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
        </view>
      </picker>
      <picker @change="bindChange" :value="value" :range="range" mode="multiSelector">
        <view :class="currentOption === 1 ? 'option-active' : ''" @click="changeActive(1)">
          <text>类型</text>
          <text class="iconfont" :class="currentOption === 1 ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
        </view>
      </picker>
      <picker @change="bindChange" :value="value" :range="range" mode="multiSelector">
        <view :class="currentOption === 2 ? 'option-active' : ''" @click="changeActive(2)">
          <text>租金</text>                                       
          <text class="iconfont" :class="currentOption === 2 ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
        </view>                                                  
      </picker>                                                  
      <picker @change="bindChange" :value="value" :range="range" mode="multiSelector">
        <view :class="currentOption === 3 ? 'option-active' : ''" @click="changeActive(3)">
          <text>房源特色</text>
          <text class="iconfont" :class="currentOption === 3 ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
        </view>
      </picker> -->
  </view>
</template>

<script>
  export default {
    props: {
      range: {
        type: Array,
        default() {
          return [];
        }
      }
    },
    data() {
      return {
        currentOption: -1,
        options: ["区域", "类型", "租金", "房源特色"],
        value: [0,0],
      }
    },
    computed: {},
    methods: {
      changeActive(index) { // 根据点击修改激活状态
        this.currentOption = index;
        this.$refs.popup.open();
      },
      bindChange(e) { // 修改值
        console.log(e);
        this.value = e.detail.value;
      }
    }
  };
</script>

<style lang="scss" scoped>
  .application-options-head {
    position: relative;
    z-index: 100;
    width: 100%;
    height: 78rpx;
    line-height: 78rpx;
    padding: 18rpx 0;
    box-sizing: border-box;
    display: flex;
    justify-content: space-around;
    align-items: center;
    background: linear-gradient(to top, #FAF8F9, #F0EFF0);
    .option-active {
      color: #2281b3;
    }
  }
  .myPopup {
    top: 78rpx;
    .myPopup-content {
      height: 400rpx;
      background-color: #fff;
      text-align: center;
    }
  }
</style>
