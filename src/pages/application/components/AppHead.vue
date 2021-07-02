<template>
  <view class="application-head-wrapper">
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
        <picker-view class="picker-view" :indicator-style="indicatorStyle" :value="searchCondition.locationName"
          @change="locationNameChange">
          <picker-view-column>
            <view class="item" v-for="(item, index) in chooseList.locationName" :key="index"> {{ item }} </view>
          </picker-view-column>
        </picker-view>
        <button type="default" @click="confirmArea">确定</button>
      </view>
    </uni-popup>
    <!-- 租金 -->
    <uni-popup class="myPopup" ref="popup1" type="top">
      <view class="myPopup-content">
        <picker-view class="picker-view" :indicator-style="indicatorStyle" :value="searchCondition.upperPrice"
          @change="upperPriceChange">
          <picker-view-column>
            <view class="item" v-for="(item, index) in chooseList.upperPrice" :key="index"> {{ item }} </view>
          </picker-view-column>
        </picker-view>
        <button type="default" @click="confirmMoney">确定</button>
      </view>
    </uni-popup>
    <!-- 房源特色 -->
    <!-- <uni-popup class="myPopup" ref="popup2" type="top">
      <view class="myPopup-content">
        <view style="height: 80rpx;"></view>
        <view class="myChoice" style="height: 300rpx;">
          <ul>
            <li v-for="item in chooseList.features" :key="item.id" :class="item.isSelect ? 'active' : ''" @click="chooseFeature(item)">{{ item.name }}</li>
          </ul>
        </view>
        <button type="default" @click="confirmFeatures">确定</button>
      </view>
    </uni-popup> -->
  </view>
</template>

<script>
  export default {
    beforeMount() {
      // console.log(this.range);
    },
    data() {
      return {
        currentOption: -1,
        options: ["区域", "租金"],
        searchCondition: {
          locationName: [],
          upperPrice: []
          // features: []
        },
        tempCondition: {
          locationName: [],
          upperPrice: []
          // features: []
        },
        chooseList: {
          locationName: ["不限", "北碚区", "巴南区", "璧山区","大渡口区", "大足区","涪陵区","合川区", "江北区", "江津区", 
          "九龙坡区","开州区","梁平区","南岸区", "南川区","黔江区", "綦江区","荣昌区","沙坪坝区",
          "潼南区","武隆区", "万州区","渝北区", "永川区", "渝中区","长寿区"],
          upperPrice: ["不限", "1500元以下", "2500元以下", "4000元以下", "6000元以下", "8000元以下"]
          // features: [
          //   {id: 1, isSelect: false, name: "健身房"},
          //   {id: 2, isSelect: false, name: "智能生活"},
          //   {id: 3, isSelect: false, name: "新小区"},
          //   {id: 4, isSelect: false, name: "有电梯"},
          //   {id: 5, isSelect: false, name: "离地铁近"}
          // ]
        },
        indicatorStyle: 'height: 40px;border:1px solid #ccc;'
      }
    },
    computed: {},
    methods: {
      changeActive(index) { // 根据点击修改激活状态并弹出对应弹窗
        if (this.currentOption !== -1 && this.currentOption !== index) { // 不是重复或初始态的情况
          this.$refs. [`popup${this.currentOption}`].close();
        }
        this.currentOption = index;
        this.$refs. [`popup${index}`].open();
      },
      locationNameChange(e) { // 滚动更改“区域”
        this.tempCondition.locationName = e.detail.value;
      },
      confirmArea() { // 确认更换"区域"
        let ln = this.tempCondition.locationName;
        this.searchCondition.locationName = ln === 0 ? undefined : this.chooseList.locationName[ln];
        this.$refs.popup0.close();
        this.$emit('refresh', this.searchCondition);
      },
      upperPriceChange(e) { // 滚动更改"租金"
        this.tempCondition.upperPrice = e.detail.value[0];
        console.log(e.detail.value);
      },
      confirmMoney(e) { // 滚动更改"租金"
        let up = this.tempCondition.upperPrice;
        this.searchCondition.upperPrice = up === 0 ? undefined : parseInt(this.chooseList.upperPrice[up]);
        this.$refs.popup1.close();
        this.$emit('refresh', this.searchCondition);
      }
      // chooseFeature(item) {
      //   item.isSelect = !item.isSelect;
      //   if (item.isSelect) { // 若是选中
      //     this.tempCondition.features.push(item);
      //   } else { // 若是取消选中
      //     this.tempCondition.features = this.tempCondition.features.filter(v => {
      //       if (v.id === item.id) {
      //         return false;
      //       }
      //       return true;
      //     });
      //   }
      // },
      // confirmFeatures() { // 确认更改"房源特色"
      //   this.searchCondition.features = this.tempCondition.features;
      //   this.$refs.popup3.close();
      // },
    }
  };
</script>

<style lang="scss" scoped>
  .application-head-wrapper {
    position: fixed;
    z-index: 100;
	  top: 0;
    width: 100%;
  }
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
    line-height: 40px;
    text-align: center;
  }
  .myChoice li{
    float: left;
    width: 20%;
    margin: 5rpx 10rpx 10rpx 10rpx;
    padding: 10rpx 5rpx;
    background-color: #eee;
    color: #aaa;
  }
  .myChoice li.active {
    background-color: rgba(18,150,219, .2);
    font-weight: bold;
    color: rgba(18, 150, 219, 0.7);
  }
</style>
