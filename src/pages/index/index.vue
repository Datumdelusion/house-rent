<template>
  <view class="content">
    <uni-nav-bar>
      <view slot="left" @click="chooseCity">
        <text>{{location}}</text>
        <uni-icons size="20" type="arrowdown"></uni-icons>
      </view>
      <uni-easyinput placeholder="输入搜索" prefixIcon="search" v-model="textValue" confirmType="search" @confirm="onSearch" :clearable="false"/>
      <view slot="right" @click="onSearch">搜索</view>
    </uni-nav-bar>

    <view class="hot-spot-wrapper">
      <text>
        <text class="iconfont icon-tubiaozhuanqu-05" style="color: red;" />
        热搜:
      </text>
      <text v-for="(item, i) in hotspot" :key="i" @click="hotSearch(item)"> {{ item }} </text>
    </view>
    
    <swiper style="height: 320rpx;" :indicator-dots="true" indicator-active-color="#fff" indicator-color="#999" :autoplay="true" :circular="true" :interval="3000" :duration="1000">
      <swiper-item>
        <image @click="turn2detail(9)" style="width:100%; height:100%" src="https://imgpro.ziroom.com/f1f50727-70a6-4251-95df-e08e18e42b01_627.jpg_C_400_300_Q100.jpg" />
      </swiper-item>
      <swiper-item>
        <image @click="turn2detail(16)" style="width:100%; height:100%" src="https://imgpro.ziroom.com/e50701ce-507f-4a98-aedc-30a9be6ad6db_895.jpg_C_400_300_Q100.jpg" />
      </swiper-item>
      <swiper-item>
        <image @click="turn2detail(22)" style="width:100%; height:100%" src="https://imgpro.ziroom.com/ab915113-6442-4da7-b872-b77609cb8bc5_718.jpg_C_400_300_Q100.jpg" />
      </swiper-item>
    </swiper>
    
    <view class="seperator" />
    
    <list-card v-for="item in dataList" :key="item.id"
      :no="item.id"
      :thumb="item.head"
      :head="item.name"
      :intro="item.style+'|'+item.area+'㎡|'+item.neighbourhood"
      :price="item.moneyMonth"
      :shoucang="item.shoucang"
       />
     <van-empty v-if="dataList.length === 0" image="search" description="找到了一片荒漠" />
    <view class="lookMore" @tap="turn2Application" v-if="dataList.length">
      点击查看更多
      <text class="iconfont icon-shenglve"></text>
    </view>
    
  </view>
  </view>
</template>

<script>
  import { amapPlugin } from '../../utils/importMap.js';
  // import { loginTest } from "../../apis/login.js";
  import { pageHouses } from "../../apis/house.js";

  export default {
    name: "Home",
    data() {
      return {
        textValue: "",
        hotspot: [
          "芍药居",
          "时代芳群",
          "海特花园小区",
          "新起点嘉园"
        ],
        dataList: [],
        location: "紫禁城"
      }
    },
    onLoad() {
      // loginTest().then(res => {
      //   uni.setStorageSync("satoken", res.data.saToken);
      //   uni.setStorageSync("loginState", true);
      //   const metoken = uni.getStorageSync("satoken");
      // }).catch(err => {
      //   console.log(err);
      // });
      
      // #ifdef MP-WEIXIN || APP-PLUS
      /* 获取用户定位信息 */
      let _this = this;
      uni.showLoading({
        title: "获取地理位置中"
      })
      amapPlugin.getRegeo({
        success(res) {
          console.log('返回的信息位置', res);
          if(res.length) {
            _this.location = res[0].regeocodeData.addressComponent.district;
            uni.hideLoading();
            // 获取房屋信息
            pageHouses({current: 1, locationName:_this.location, size: 5}).then(res => {
              _this.dataList = [];
              _this.dataList = res.data.records;
            }).catch(err => {
              console.log(err);
            })
          }
        },
        fail(err) {
          console.log(err);
           uni.hideLoading();
        }
      })
      // #endif
    },
    methods: {
      turn2Application() { // 跳转到别的页面中
        uni.switchTab({
          url: '/pages/application/application'
        })
      },
      chooseCity() { // 跳转到"选择城市"页面
        uni.navigateTo({
          url: '/pages/city/city'
        });
      },
      hotSearch(hotspot) { // FIXME🧊🍺: 调用搜索函数
        this.textValue = hotspot;
        // 调用搜索函数...
      },
      onSearch() { // 点击搜索
        console.log(this.textValue);
      },
      setMyCity(location) { // 设置城区名字
        this.location = location;
        let that = this;
        pageHouses({current: 1, locationName: location, size: 5}).then(res => {
          that.dataList = [];
          that.dataList = res.data.records;
        })
      },
      turn2detail(id) {
        uni.navigateTo({
          url: `../item/item?id=${id}`
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  /deep/ .uni-easyinput__content {
    border-radius: 36rpx;
    height: 60rpx;
  }

  .content {
    height: 100vh;
  }

  .hot-spot-wrapper {
    margin: 10rpx 0 16rpx 28rpx;
    font-size: 24rpx;
    color: #808080;
  }

  .hot-spot-wrapper .hot-spot-icon {
    color: red;
    margin-right: 12rpx;
  }
  
  .seperator {
    width: 90%;
    margin: 60rpx 0 10rpx;
    height: 2rpx;
    background: linear-gradient(to right, rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
    position: relative;
    &::after {
      content: '为您推荐';
      color: #333;
      font-size: 30rpx;
      font-style: italic;
      position: absolute;
      bottom: 0;
      left: 10rpx;
    }
  }

  .hot-spot-wrapper>text {
    margin: 0 12rpx;
  }

  .recommend-wrapper {
    background-color: transparent;
  }

  .lookMore {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 80rpx;
  }
</style>
