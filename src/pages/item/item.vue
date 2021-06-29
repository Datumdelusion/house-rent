<template>
	<view>
		<swiper style="height: 320rpx;" :indicator-dots="true" indicator-active-color="#fff" indicator-color="#999" :autoplay="true" :circular="true" :interval="3000" :duration="1000">
		  <swiper-item>
		    <image style="width:100%; height:100%" src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" />
		  </swiper-item>
		  <swiper-item>
		    <image style="width:100%; height:100%" src="https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg" />
		  </swiper-item>
		</swiper>
    <item-detail />
	</view>
</template>

<script>
  import ItemDetail from "./components/ItemDetail.vue";
  
	export default {
    name: "item",
    components: {
      ItemDetail
    },
    onLoad(option) {
      // console.log("id: ", option.id);
    },
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss">

</style>
