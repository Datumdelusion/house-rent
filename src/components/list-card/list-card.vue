<template>
  <view class="list-card-wrapper" @click="turn2Page">
    <view style="float: left;">
      <image class="list-card-left-image" v-show="thumb" :src="thumb" />
      <slot name="tag">
        <!-- <uni-tag class="list-card-tags" v-show="tag" :text="tag" type="error" :mark="true"></uni-tag> -->
        <view class="list-card-tags" v-if="tag"> {{ tag }} </view>
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
          <text style="color: #f40; font-size: 1.2rem;">{{ parseInt(price)+ "." }}</text>
          <text style="color: #f40; font-size: 1rem;">{{ Math.round(price*100)%100 }}</text>
        </view>
        <view v-if="shoucang">
          <text class="love-icon iconfont" :class="isShoucang ? 'icon-xihuan' : 'icon-xihuan-xianxing'" @click.stop="clickShoucang(isShoucang)"></text>
        </view>
        <view style="display: inline-block; display: flex;" class="btn-group" v-if="isBtn">
          <slot name="btnGroup">
            <button type="default" size="mini">确定</button>
            <button type="default" size="mini">取消</button>
          </slot>
        </view>
    </view>
  </view>
</template>

<script>
  export default {
    props: {
      no: {
        type: Number,
        required: true
      },
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
          return false;
        }
      },
      isShoucang: {
        type: Boolean,
        default() {
          return false;
        }
      },
      isBtn: {
        type: Boolean,
        default() {
          return false;
        }
      }
    },
    name: "ListCard",
    methods: {
      turn2Page() { // 跳转到具体信息页面
        uni.navigateTo({
          url: `/pages/item/item?id=${this.no}`
        });
      },
      clickShoucang(isShoucang) { // 点击收藏
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
    margin-bottom: 8rpx;
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
      animation: mylove 0.5s linear;
    }
  }
  .btn-group {
    position: absolute;
    right: 35rpx;
    bottom: 35rpx;
    transform: translateY(50%);
    button {
      margin-right: 5rpx;
    }
  }
  @keyframes mylove {
    from { transform: scale(1) translateY(50%);}
    50% { transform: scale(1.3) translateY(50%);}
    to { transform: scale(1) translateY(50%);}
  }
</style>
