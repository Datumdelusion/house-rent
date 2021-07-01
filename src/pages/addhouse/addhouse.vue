<template>
  <view class="addhouse-wrapper">
    <uni-forms ref="form" :rules="rules">
      <uni-forms-item label="所在地址" name="city">
        <uni-list :border="false" label="所在位置">
          <uni-list-item link to="../location/location"></uni-list-item>
        </uni-list>
      </uni-forms-item>
      <uni-forms-item label="具体地址" name="address">
        <uni-easyinput type="text" v-model="formData.address" placeholder="请输入具体地址" />
      </uni-forms-item>
      <uni-forms-item label="房源名称" name="name">
        <uni-easyinput type="text" v-model="formData.name" placeholder="请输入房名" />
      </uni-forms-item>
      <uni-forms-item label="房屋简介" name="intro">
        <uni-easyinput type="text" v-model="formData.intro" placeholder="请输入简介" />
      </uni-forms-item>
      <uni-forms-item label="价格" name="price">
        <uni-easyinput type="text" v-model="formData.price" placeholder="请输入价格" />
      </uni-forms-item>
      <uni-forms-item label="面积" name="area">
        <uni-easyinput type="text" v-model="formData.area" placeholder="请输入面积" />
      </uni-forms-item>
      <uni-forms-item label="朝向" name="direction">
        <uni-easyinput type="text" v-model="formData.direction" placeholder="请输入朝向" />
      </uni-forms-item>
      <uni-forms-item label="户型" name="type">
        <uni-easyinput type="text" v-model="formData.type" placeholder="请输入户型" />
      </uni-forms-item>
      <uni-forms-item label="所在楼层" name="floor">
        <uni-easyinput type="text" v-model="formData.floor" placeholder="请输入所在楼层" />
      </uni-forms-item>
      <uni-forms-item label="有无电梯" name="hasElevator">
        <checkbox-group name="hasElevator">
          <label>
            <checkbox :value="true" /><text>有</text>
            <checkbox :value="false" :checked="true" /><text>无</text>
          </label>
        </checkbox-group>
      </uni-forms-item>
      <uni-forms-item label="建造时间" name="buildYear">
        <uni-easyinput type="text" v-model="formData.buildYear" placeholder="请输入建造时间" />
      </uni-forms-item>
      <uni-forms-item label="绿化面积" name="green">
        <uni-easyinput type="text" v-model="formData.green" placeholder="请输入绿化百分比" />
      </uni-forms-item>
      <uni-forms-item label="联系方式" name="intro">
        <uni-easyinput type="text" v-model="formData.intro" placeholder="请输入联系方式" />
      </uni-forms-item>
      <uni-forms-item label="房屋条件" name="condition">
        <checkbox-group @change="conditionChange">
          <label v-for="item in items" :key="item.value" style="display: block; margin-bottom: 10rpx;">
              <checkbox :value="item.value" :checked="item.checked" /> <text>{{item.name}}</text>
          </label>
        </checkbox-group>
      </uni-forms-item>
      <button type="default" @click="submitForm" style="background-color: #ffbf2f; color: #fff;">提交</button>
    </uni-forms>
  </view>
</template>

<script>
  const qiniu = require('qiniu-js');

  export default {
    name: "addHouse",
    data() {
      return {
        items: [
          {
            value: "icon-luyouqi",
            name: "路由器",
            checked: false
          },
          {
            value: "icon-bingxiang",
            name: "冰箱",
            checked: false
          },
          {
            value: "icon-kongtiao",
            name: "空调",
            checked: false
          },
          {
            value: "icon-youyanji",
            name: "油烟机",
            checked: false
          },
          {
            value: "icon-xiyiji",
            name: "洗衣机",
            checked: false
          },
          {
            value: "icon-chuang",
            name: "床",
            checked: false
          },
          {
            value: "icon-weibolu",
            name: "微波炉",
            checked: false
          },
          {
            value: "icon-yigui",
            name: "衣柜",
            checked: false
          }
        ],
        formData: {
          address: "",
          name: "",
          intro: "",
          area: "",
          direction: "",
          type: "",
          floor: "",
          hasElevator: "",
          buildYear: "",
          green: "",
          condition: []
        },
        rules: {
          address: {
            rules: [
              {
                required: true,
                errorMessage: "请输入地址"
              }
            ]
          },
          name: {
            rules: [
              {
                required: true,
                errorMessage: "请输入姓名"
              }
            ]
          },
        }
      }
    },
    methods: {
      hasElevatorChange(value) { // 修改是否有电梯
        console.log(value);
      },
      conditionChange(value) { // 修改房屋条件
        console.log(value);
      },
      submitForm() { // 提交按钮
        console.log("提交");
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
