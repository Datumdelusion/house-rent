<template>
  <van-tabs swipeable :active="active">
    <van-tab class="van-tab-wrapper" title="标签 1" name="a">内容 1</van-tab>
    <van-tab class="van-tab-wrapper" title="标签 2" name="b">内容 2</van-tab>
    <van-tab class="van-tab-wrapper" title="标签 3" name="c">内容 3</van-tab>
</van-tabs>
</template>

<script>

export default {
  props: {},
  data() {
    return {
      active: "a"
    }
  },
  computed: {},
  methods: {},
  watch: {},

  // 组件周期函数--监听组件挂载完毕
  mounted() {},
  // 组件周期函数--监听组件数据更新之前
  beforeUpdate() {},
  // 组件周期函数--监听组件数据更新之后
  updated() {},
  // 组件周期函数--监听组件激活(显示)
  activated() {},
  // 组件周期函数--监听组件停用(隐藏)
  deactivated() {},
  // 组件周期函数--监听组件销毁之前
  beforeDestroy() {},
};
</script>

<style scoped>
</style>