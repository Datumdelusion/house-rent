<template>
	<view class="item-wrapper">
		<swiper style="height: 320rpx;" :indicator-dots="true" indicator-active-color="#fff" indicator-color="#999" :autoplay="true" :circular="true" :interval="3000" :duration="1000">
		  <swiper-item v-for="item in info.pics">
		    <image style="width:100%; height:100%" :src="item" />
		  </swiper-item>
		</swiper>
    <view class="item-detail-wrapper">
      <main-intro @starUpdate="starUpdate" :name="info.name" :moneyMonth="info.moneyMonth" :area="info.area" :orientation="info.orientation" :style="info.style" :star="info.star"/>
      <features-list :greenArea="info.greenArea" :elevator="info.elevator" :detailLocation="info.detailLocation" :storey="info.storey" :years="info.years"/>
      <house-owner :brief="info.brief"/>
      <item-condition :usp="info.usp"/>
      <item-location :latitude="info.latitude" :longitude="info.longitude" :markers="markers"/>
    </view>
    <uni-popup class="myPopup" ref="popup" type="bottom">
      <view style="background-color: #fff;border-top-left-radius: 10px;border-top-right-radius: 10px;" class="myPopup-content">
        <view style="padding: 10px;">
          <view style="margin-bottom: 20rpx;" class="owner-info">
            预计
            <text style="color: red">2021-07-15</text>
            可入住，需签约至
            <text style="color: red">2022-07-14</text>
          </view>
          <view style="margin-bottom: 20rpx;" class="owner-info">
            <view class="owner-info-prefix">
              <text style="color: #ff961e; font-size: 38rpx;" class="iconfont icon-dianhua" />
              <text style="color: #999;">联系方式:</text>
              <text style="text-decoration: underline;">12345678910</text>
            </view>
          </view>
          <view class="owner-info">
            <view style="margin-bottom: 20rpx;" class="owner-info-prefix">
              <text style="color: #1290BF; font-size: 38rpx;" class="iconfont icon-yonghu"/>
              <text style="color: #999;">转租者:</text>
              <text>小鹿姐姐</text>
            </view>
          </view>
        </view>
        <button type="default" @click="confirm">了解</button>
      </view>
    </uni-popup>
    <view class="tabbar-button-group">
      <button class="wantBtn" type="default" v-if="true" @click="want">想要</button>
      <button style="color: rgba(0,0,0,.3);background-color: #f7f7f7;" class="chosenBtn" type="default" v-else @click="seewhat">已选</button>
    </view>
	</view>
</template>

<script>
  import MainIntro from "./components/MainIntro.vue";
  import FeaturesList from "./components/FeaturesList.vue";
  import HouseOwner from "./components/HouseOwner.vue";
  import ItemCondition from "./components/ItemCondition.vue";
  import ItemLocation from "./components/ItemLocation.nvue";
  
  import { getHouse } from "../../apis/house.js";
  import { starIt, cancelStarByHouseId } from "../../apis/star.js";
  
	export default {
    name: "item",
    components: {
      MainIntro,
      FeaturesList,
      HouseOwner,
      ItemCondition,
      ItemLocation
    },
    onLoad(option) {
      // console.log("id: ", option.id);
      uni.showLoading({
        title: "拼命加载中……"
      })
      // 获取房屋具体信息
      getHouse(option.id).then(res => {
        /* 数据处理 */
        let data = res.data;
        if (data.pics.length > 5) {
          data.pics = data.pics.slice(0, 5);
        };
        this.info = data;
        // 处理markers
        this.markers = [];
        this.markers.push({
          id: 1,
          latitude: this.info.latitude,
          longitude: this.info.longitude,
          width: '20',
          height: '30'
        })
        uni.hideLoading();
      }).catch(err => {
        console.log(err);
        uni.hideLoading();
      })
    },
		data() {
			return {
				info: {},
        markers: []
			};
		},
    methods: {
      starUpdate() { // 收藏更改
        // console.log(this.info.id);
        if (this.info.star) {
          cancelStarByHouseId(this.info.id).then(res => {
            if (res.data) {
              this.info.star = false;
            }
          }).catch(err => {
            console.log(err);
          })
        } else {
          starIt(this.info.id).then(res => {
            if (res.data) {
              this.info.star = true;
            }
          }).catch(err => {
            console.log(err);
          })
        }
      },
      confirm() { // 确定并关闭弹出框
        this.$refs.popup.close();
      },
      want() { // 点击“想要”按钮
        uni.showLoading({
          title: '拼命加载中……'
        });
        // 发送请求
        this.$refs.popup.open();
        uni.hideLoading();
      },
      seewhat() { // 弹出框
        this.$refs.popup.open();
      }
    }
	}
</script>

<style lang="scss">
  .item-detail-wrapper {
    padding: 20rpx 32rpx;
    margin-bottom: 45px;
  }
  .tabbar-button-group {
    position: fixed;
    bottom: 0;
    height: 45px;
    width: 100%;
    .wantBtn {
      color: #fff;
      background-color: #ffbf2f;
      border-bottom-left-radius: 0;
      border-bottom-right-radius: 0;
    }
  }
</style>
