<template>
	<view class="content">
		<uni-nav-bar>
		    <view slot="left">
				<text class="nav-bar-left-text">北京</text>
				<uni-icons size="20" type="arrowdown"></uni-icons>
			</view>
			<uni-easyinput placeholder="输入搜索" prefixIcon="search" v-model="textValue" confirmType="search"></uni-easyinput>
		    <view slot="right">搜索</view>
		</uni-nav-bar>
		
		<view class="hot-spot-wrapper">
			<text>
				<text class="iconfont icon-tubiaozhuanqu-05" style="color: red;"/>
				热搜:
			</text>
			<text v-for="(item, i) in hotspot" :key="i"> {{ item }} </text>
		</view>
		<type-icon />
		<!-- <view class="recommend-wrapper">
			<view>为您推荐</view>
			<van-card
			 v-for="i in 4"
			 :key="i"
			 desc="2室1厅|75.1㎡|草桥欣园三区"
			 price="535万"
  		 :thumb="require('@/static/logo.png')"
			>
				<template #title>
					<van-tag type="danger" style="margin-right:6rpx">必看好房</van-tag>
  		 		草桥欣园三区 央产证 南北通透 有钥匙 看两居室
				</template>
			</van-card> -->
			<view class="lookMore" @tap="turn2Application">
				点击查看更多
				<text class="iconfont icon-shenglve"></text>
			</view>
		</view>
	</view>
</template>

<script>
	import TypeIcon from "./components/TypeIcon.vue"
	export default {
		name: "Home",
		components: {
			TypeIcon
		},
		data() {
			return {
				textValue: "",
				hotspot: [
					"芍药居",
					"时代芳群",
					"海特花园小区",
					"新起点嘉园"
				],
				loading: true
			}
		},
		onLoad() {
			
		},
		methods: {
			turn2Application() { // 跳转到别的页面中
				uni.switchTab({ url: '/pages/application/application' })
			},
			isReady() {
				this.loading = false;
			}
		}
	}
</script>

<style lang="scss" scoped>
	/deep/ .uni-easyinput__content {
		border-radius: 36rpx;
		height: 60rpx;
	}
	.nav-bar-left-text {
		margin-left: 30rpx;
	}
	.content {
		height: 100vh;
	}
	.hot-spot-wrapper {
		margin: 10rpx 0 40rpx 28rpx;
		font-size: 24rpx;
		color: #808080;
	}
	.hot-spot-wrapper .hot-spot-icon {
		color:red;
		margin-right: 12rpx;
	}
	.hot-spot-wrapper > text {
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
