<template>
  <view class="list-card-wrapper" @click="turn2Page">
    <view style="float: left;">
      <image class="list-card-left-image" v-show="thumb" :src="thumb" />
      <slot class="list-card-tags" name="tag">
        <uni-tag class="list-card-tags" v-show="tag" :text="tag" type="error" :mark="true"></uni-tag>
      </slot>      
    </view>
    <view class="list-card-content">
      <slot name="content-header">
        <view class="list-card-header">
          {{ head }}
        </view>
      </slot>
      <slot name="content-intro">
        <view class="list-card-intro">
          {{ intro }}
        </view>
      </slot>
        <view class="list-card-price">
          <text>￥</text>
          <text style="color: #f40; font-size: 1.2rem;">{{ price }}</text>
        </view>
    </view>
  </view>
</template>

<script>
  export default {
    props: {
      thumb: {
        type: String,
        default() {
          return "";
        }
      },
      tag: {
        type: String,
        default() {
          return "";
        }
      },
      price: {
        type: String,
        default() {
          return "";
        }
      },
      head: {
        type: String,
        default() {
          return "";
        }
      },
      intro: {
        type: String,
        default() {
          return "";
        }
      },
    },
    name: "ListCard",
    data() {
      return {
      };
    },
    methods: {
      turn2Page() {
        this.$emit("turn2Page");
      }
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
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        
      }
      .list-card-intro {
        color: #999;
        height: 70rpx;
      }
    }
  }
</style>
