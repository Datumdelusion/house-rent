<template>
  <view class="list-card-wrapper">
    <view style="float: left;">
      <image class="list-card-left-image" v-show="thumb" :src="thumb" />
      <slot class="list-card-tags" name="tag">
        <uni-tag class="list-card-tags" v-show="tag" :text="tag" type="error" :mark="true"></uni-tag>
      </slot>      
    </view>
    <view class="list-card-content">
      <slot name="content">
        <view class="list-card-header">
          111
        </view>
        <view class="list-card-intro">
          简介
        </view>
        <view class="list-card-price">
          ￥33.3
        </view>
      </slot>      
    </view>
  </view>
</template>

<script>
  export default {
    props: {
      thumb: {
        type: String,
        default() {
          return "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg";
        }
      },
      tag: {
        type: String,
        default() {
          return "12";
        }
      }
    },
    name: "ListCard",
    data() {
      return {
        
      };
    }
  }
</script>

<style lang="scss" scoped>
  .list-card-wrapper {
    position: relative;
    background-color: #fff;
    overflow: hidden;
    .list-card-tags {
      position: absolute;
      top: 0;
      left: 0;
    }
    .list-card-left-image {
      width: 160rpx;
      height: 160rpx;
    }
    .list-card-content {
      text-indent: 10rpx;
      .list-card-header {
        
      }
      .list-card-intro {
        color: #999;
      }
    }
  }
</style>
