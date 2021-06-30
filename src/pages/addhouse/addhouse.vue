<template>
  <view class="addhouse-wrapper">
    <uni-card title="标题文字">
      <uni-easyinput  v-model="name" placeholder="请输入房名" />
      <uni-easyinput  v-model="attr2" placeholder="" />
      <uni-easyinput  v-model="attr3" placeholder="" />
      <uni-easyinput  v-model="attr4" placeholder="" />
    </uni-card>
  </view>
</template>

<script>
  const qiniu = require('qiniu-js');
  
  export default {
    name: "addHouse",
    data() {
      return {
        obj: {
          name: "",
          attr2: "",
          attr3: "",
          attr4: ""
        }
      }
    },
    methods: {
      
    }
  }
</script>

<style lang="scss" scoped>

</style>
