<template>
  <van-search shape="round" v-model="value" placeholder="请输入搜索关键词" @change="onChange" @search="onSearch"/>
</template>

<script>

export default {
  props: {},
  data() {
    return {
      value: ""
    }
  },
  computed: {},
  methods: {
    onChange(e) { // 手动修正value
      this.value = e.detail;
    },
    onSearch() { // 搜索
      this.$emit("onSearch", this.value);
    }
  },
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

<style></style>
