<template>
  <view class="application">
    <view class="application-options-head">
      <text v-for="(item,index) in options" :class="currentOption === index ? 'option-active' : ''"
        @click="changeActive(index)">
        {{ item }}
        <text class="iconfont" :class="currentOption === index ? 'icon-biaotou-zhengxu' : 'icon-biaotou-daoxu'" />
      </text>
    </view>
	<!-- 区域 -->
    <uni-popup class="myPopup" ref="popup0" type="top">
      <view class="myPopup-content">
        <picker-view class="picker-view" :indicator-style="indicatorStyle" :value="searchCondition.area" @change="areaChange">
          <picker-view-column>
            <view class="item"> 第1个 </view>
            <view class="item"> 第2个 </view>
            <view class="item"> 第3个 </view>
          </picker-view-column>
        </picker-view>
      </view>
    </uni-popup>
	<!-- 类型 -->
	<uni-popup class="myPopup" ref="popup1" type="top">
      <view class="myPopup-content">
        <picker-view class="picker-view" :indicator-style="indicatorStyle" :value="searchCondition.type" @change="typeChange">
          <picker-view-column>
            <view class="item"> 第2个 </view>
            <view class="item"> 第3个 </view>
            <view class="item"> 第4个 </view>
          </picker-view-column>
        </picker-view>
      </view>
    </uni-popup>
	<!-- 租金 -->
	<uni-popup class="myPopup" ref="popup2" type="top">
      <view class="myPopup-content">
        <picker-view class="picker-view" :indicator-style="indicatorStyle" :value="searchCondition.money" @change="moneyChange">
          <picker-view-column>
            <view class="item"> 第5个 </view>
            <view class="item"> 第6个 </view>
            <view class="item"> 第7个 </view>
          </picker-view-column>
        </picker-view>
      </view>
    </uni-popup>
	<!-- 房源特色 -->
	<uni-popup class="myPopup" ref="popup3" type="top">
      <view class="myPopup-content">
        no
      </view>
    </uni-popup>
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
        searchCondition: {
			area: [0],
			type: [0],
			money: [0],
			features: []
		},
		indicatorStyle: 'height: 40px;border:1px solid #ccc;'
      }
    },
    computed: {},
    methods: {
      changeActive(index) { // 根据点击修改激活状态并弹出对应弹窗
		if (this.currentOption !== -1) {
			this.$refs.[`popup${this.currentOption}`].close();
		}
        this.currentOption = index;
        this.$refs.[`popup${index}`].open();
      },
      areaChange(e) { // 修改区域
        // this.searchCondition.area = e.detail.value;
      },
	  typeChange(e) { // 修改类型
        this.searchCondition.type = e.detail.value;
      },
	  moneyChange(e) { // 修改租金
        this.searchCondition.money = e.detail.value;
      },
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
  .picker-view {
    width: 100%;
    height: 380rpx;
   }
  .item {
	height: 40px;
	align-items: center;
	justify-content: center;
	text-align: center;
  }
</style>
