<template>
  <view class="list-card-wrapper" @click="turn2Page">
    <view style="float: left;">
      <image class="list-card-left-image" v-show="thumb" :src="thumb" />
      <slot name="tag">
        <!-- <uni-tag class="list-card-tags" v-show="tag" :text="tag" type="error" :mark="true"></uni-tag> -->
        <view class="list-card-tags" v-show="tag"> {{ tag }} </view>
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
        <view v-if="shoucang">
          <text class="love-icon iconfont" :class="isShoucang ? 'icon-xihuan' : 'icon-xihuan-xianxing'" @click="clickShoucang(isShoucang)"></text>
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
      shoucang: {
        type: Boolean,
        default() {
          return false
        }
      },
      isShoucang: {
        type: Boolean,
        default() {
          return false
        }
      }
    },
    name: "ListCard",
    methods: {
      turn2Page() {
        this.$emit("turn2Page");
      },
      clickShoucang(isShoucang) {
        this.$emit('clickShoucang', isShoucang)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .list-card-wrapper {
    position: relative;
    background-color: #fff;
    border-bottom: 1rpx solid rgba(0, 0, 0, 0.2);
    overflow: hidden;
    .list-card-tags {
      position: absolute;
      top: 0;
      left: 0;
      color: #fff;
      background-color: #dd524d;
      border: 2rpx solid #dd524d;
      border-top-right-radius: 30rpx;
      border-bottom-right-radius: 30rpx;
      width: 100rpx;
      text-align: center;
      padding: 4rpx 0;
      font-size: 26rpx;
      height: 35rpx;
      line-height: 35rpx;
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
  .love-icon {
    position: absolute;
    right: 70rpx;
    bottom: 50%;
    transform: translateY(50%);
    font-size: 40rpx;
    &.icon-xihuan {
      color: #d81e06;
      animation: mylove 1s linear;
    }
  }
  @keyframes mylove {
    from { transform: scale(1) translateY(55%);}
    50% { transform: scale(1.3) translateY(55%);}
    to { transform: scale(1) translateY(55%);}
  }
</style>
