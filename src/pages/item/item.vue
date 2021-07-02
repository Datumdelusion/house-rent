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
    <view class="tabbar-button-group">
      <button type="default" v-if="true">想要</button>
      <button :disabled="true" v-else>已选</button>
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
    button {
      color: #fff;
      background-color: #ffbf2f;
      border-bottom-left-radius: 0;
      border-bottom-right-radius: 0;
    }
  }
</style>
