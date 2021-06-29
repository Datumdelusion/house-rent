<template>
	<view class="item-wrapper">
		<swiper style="height: 320rpx;" :indicator-dots="true" indicator-active-color="#fff" indicator-color="#999" :autoplay="true" :circular="true" :interval="3000" :duration="1000">
		  <swiper-item>
		    <image style="width:100%; height:100%" src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" />
		  </swiper-item>
		  <swiper-item>
		    <image style="width:100%; height:100%" src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" />
		  </swiper-item>
		</swiper>
    <view class="item-detail-wrapper">
      <main-intro />
      <features-list />
      <house-owner />
      <item-condition />
      <item-location />
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
      console.log("id: ", option.id);
    },
		data() {
			return {
				
			};
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
