<template>
  <view class="title">
		<!-- 搜索框左侧 -->
		<view class="label" @click="turn2city">
			<text>{{ city }}</text>
			<van-icon name="arrow-down" />
		</view>
    <!-- 搜索框 -->
    <van-search
      class="search-wrapper"
      v-model="searchText"
      use-action-slot
      placeholder="请输入搜索关键词"
      @search="onSearch"
      @change="onChange"
    >
      <!-- 搜索框右侧文本 -->
      <view slot="action" @tap="onSearch">搜索</view>
    </van-search>
  </view>
</template>

<script>
import { getCity } from "@/apis/map";

	export default {
    name: "NavBar",
		data() {
			return {
				city: "北京市",  // 当前选择的城市
				searchText: "", // 需搜索的文本
				value: 0, 		  // 默认选项
				options: [ // 下拉选项
					{ text: '北京', value: 0 },
      		{ text: '重庆', value: 1 },
      		{ text: '海南', value: 2 }
				]
			}
		},
		methods: {
			turn2city() { // 跳转到选择地址界面
				uni.navigateTo({ url: '/pages/city/city' });
			},
			// getUserAddress() { // 用户选择地址
			// 	uni.chooseLocation({
			// 		success: ({ name, address, longitude, latitude }) => {
			// 			let location = latitude + "," + longitude;
			// 			getCity(location)
			// 				.then((res) => {
			// 					console.log(res);
			// 					this.city = res.result.address_component.city;
			// 				})
			// 				.catch((err) => {
			// 					console.log(err);
			// 				});
			// 		},
			// 		fail: (error) => {
			// 			console.log(error);
			// 		}
			// 	})
			// },
			onSearch() { // TODO:搜索事件
				console.log(this.searchText);
			},
			onChange(e) { // vant组件似乎不能双向绑定, 需要change事件额外修改
				this.searchText = e.detail;
			},
			getUsertLocation() { // 获取用户地理坐标
				// uni.getLocation({
				// 	type: 'gcj02',
				// 	success: (res) => { // 成功后返回该用户所在的地区
				// 		let address = res.latitude + "," + res.longitude;
				// 		getCity(address)
				// 			.then(res => { // 请求成功，修改位置
				// 				this.city = res.result.address_component.city;
				// 				this.$emit("isReady");
				// 			})
				// 			.catch(err => {
				// 				console.log(err);
				// 			})
				// 	},
				// 	fail: (error) => {
				// 		console.log(error);
				// 		this.$emit("isReady");
				// 	}
				// })
			},
			getAuthModal() { // 申请授权Modal
				let that = this;
				// uni.showModal({
				// 	title: '地理位置获取请求',
				// 	content: '需要获取您的地理位置',
				// 	confirmText: "确认",
				// 	cancelText: "取消",
				// 	success: function (res) {
				// 		// console.log(res);
				// 		//点击“确认”时打开设置页面
				// 		if (res.confirm) {
				// 			console.log('用户点击确认')
				// 			uni.openSetting({
				// 				success: (res) => {
				// 					that.getUsertLocation();
				// 				}
				// 			})
				// 		} else {
				// 			console.log('用户点击取消')
							that.$emit("isReady");
				// 		}
				// 	}
				// });
			}
		},
		created() { // 申请地址授权
			// let that = this;
			// uni.getSetting({
			// 	success(res) {
			// 		// console.log(res.authSetting);
			// 		if (res.authSetting['scope.userLocation']) {
			// 		  that.getUsertLocation();
			// 		} else {
			// 		  that.getAuthModal();
			// 		}
			// 	} 
			// })
		}
	}
</script>

<style>
	.title {
		box-shadow: 0 2rpx 5rpx rgba(0, 0, 0, .2);
		background-color: #fff;
		display: flex;
		align-items: center;
		justify-content: center;
		margin: 0 auto;
	}
	.label {
		margin-left: 20rpx;
	}
	.label text {
		display: inline-block;
		min-width: 64rpx;
	}
	.title .search-wrapper {
		flex: 1;
	}
</style>
