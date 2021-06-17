<template>
	<view class="content">
		<uni-nav-bar>
		    <view slot="left">
				<text>北京</text>
				<uni-icons type="arrowdown"></uni-icons>
			</view>
			<uni-search-bar placeholder="输入搜索" v-model="textValue" :radius="100"></uni-search-bar>
		    <view slot="right">搜索</view>
		</uni-nav-bar>
		
		<view class="hot-spot-wrapper">
			<!-- <van-icon name="fire" class="hot-spot-icon"/> 热搜: -->
			<text>
				<text class="iconfont icon-tubiaozhuanqu-05" />
				热搜:
			</text>
			<text v-for="(item, i) in hotspot" :key="i"> {{ item }} </text>
		</view>
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
				<!-- <image style="height:40rpx;width:40rpx;" :src="require('static/image/elipsis.png')"/> -->
				<text class="iconfont icon-shenglve"></text>
			</view>
		</view>
	</view>
</template>

<script>
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

<style>
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
