<template>
  <view class="addhouse-wrapper">
    <uni-forms ref="form" :rules="rules">
      <uni-forms-item label="预览图" name="pics">
        <!-- <uni-file-picker v-model="formData.pics" title="最多选择五张图片" 
        limit="5" file-mediatype="image" :auto-upload="false" ref="files">
        </uni-file-picker>
        <button type="default" size="mini" style="background-color: #007AFF; margin-top: 10rpx; color: #fff;"
        @click="click2upload">上传</button> -->
        <van-uploader accept="image" :file-list="formData.pics" :max-count="3" @afterRead="afterRead">
        </van-uploader>
      </uni-forms-item>
      <uni-forms-item label="所在区域" name="location">
        <uni-list :border="false">
            <uni-list-item link to="../city/city" :rightText="formData.location?'':'请选择'">
              <template #body>
                <text> {{ formData.location }} </text>
              </template>
            </uni-list-item>
        </uni-list>
      </uni-forms-item>
      <uni-forms-item label="所在位置" name="region">
        <uni-list :border="false">
            <uni-list-item link to="../location/location" :rightText="formData.region?'':'请选择'">
              <template #body>
                <text> {{ formData.region }} </text>
              </template>
            </uni-list-item>
        </uni-list>
      </uni-forms-item>
      <uni-forms-item label="具体地址" name="address">
        <uni-easyinput type="text" v-model="formData.address" placeholder="请输入具体地址" />
      </uni-forms-item>
      <uni-forms-item label="房源名称" name="name">
        <uni-easyinput type="text" v-model="formData.name" placeholder="请输入房名" />
      </uni-forms-item>
      <uni-forms-item label="房屋简介" name="intro">
        <uni-easyinput type="textarea"  v-model="formData.intro" placeholder="请输入简介" :clearable="false" />
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
  const qiniu = require("../../utils/qiniuUploader.js");
  import { getUploadToken } from "../../apis/upload.js";

  export default {
    name: "addHouse",
    data() {
      return {
        url: "http://qvmeb7fx0.hn-bkt.clouddn.com",
        items: [{
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
          location: "",
          pics: [],
          region: "",
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
            rules: [{
              required: true,
              errorMessage: "请输入地址"
            }]
          },
          name: {
            rules: [{
              required: true,
              errorMessage: "请输入姓名"
            }]
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
      setMyCity(location) { // 设置城区名字
        this.formData.location = location;
      },
      setRegion(region) {
        this.formData.region = region;
      },
      submitForm() { // 提交按钮
        console.log("提交");
      },
      upload: async function (file) { // 上传文件
        console.log(file.file);
        let result = await getUploadToken();
        qiniu.upload(file.file.url, (res) => {
          console.log(res);
        }, (error) => {
          console.log(error);
        }, {
          region: "SCN",
          uptoken: result.data.uploadToken,
          domain: this.url,
          shouldUseQiniuFileName: true
        })
      },
      afterRead(event) { // 上传文件
        console.log(event.detail);
        this.upload(event.detail);
      }
    }
  }
</script>

<style lang="scss" scoped>

</style>
