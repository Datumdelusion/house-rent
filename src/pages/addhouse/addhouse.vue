<template>
  <view class="addhouse-wrapper">
    <uni-forms ref="form" :rules="rules">
      <uni-forms-item label="预览图" name="pics" :required="true">
        <!-- <uni-file-picker v-model="formData.pics" title="最多选择五张图片" 
        limit="5" file-mediatype="image" :auto-upload="false" ref="files">
        </uni-file-picker>
        <button type="default" size="mini" style="background-color: #007AFF; margin-top: 10rpx; color: #fff;"
        @click="click2upload">上传</button> -->
        <van-uploader accept="image" :file-list="formData.pics" :max-count="3" @afterRead="afterRead" @delete="deletePics">
        </van-uploader>
      </uni-forms-item>
      <uni-forms-item label="所在区域" name="location" :required="true">
        <uni-list :border="false">
            <uni-list-item link to="../city/city" :rightText="formData.locationThreeName?'':'请选择'">
              <template #body>
                <text> {{ formData.locationThreeName }} </text>
              </template>
            </uni-list-item>
        </uni-list>
      </uni-forms-item>
      <uni-forms-item label="所在位置" name="region" :required="true">
        <uni-list :border="false">
            <uni-list-item link to="../location/location" :rightText="formData.latitude?'':'请选择'">
              <template #body>
                <text> {{ formData.latitude + " " + formData.longitude }} </text>
              </template>
            </uni-list-item>
        </uni-list>
      </uni-forms-item>
      <uni-forms-item label="具体地址" name="detailLocation" required>
        <uni-easyinput type="text" v-model="formData.detailLocation" placeholder="请输入具体地址" />
      </uni-forms-item>
      <uni-forms-item label="房源名称" name="name" :required="true">
        <uni-easyinput type="text" v-model="formData.name" placeholder="请输入房名" />
      </uni-forms-item>
      <uni-forms-item label="房屋简介" name="brief">
        <uni-easyinput type="textarea"  v-model="formData.brief" placeholder="请输入简介" :clearable="false" />
      </uni-forms-item>
      <uni-forms-item label="价格" name="moneyMonth" :required="true">
        <uni-easyinput type="text" v-model="formData.moneyMonth" placeholder="请输入价格" />
      </uni-forms-item>
      <uni-forms-item label="面积" name="area" :required="true">
        <uni-easyinput type="text" v-model="formData.area" placeholder="请输入面积" />
      </uni-forms-item>
      <uni-forms-item label="朝向" name="orientation" :required="true">
        <uni-easyinput type="text" v-model="formData.orientation" placeholder="请输入朝向" />
      </uni-forms-item>
      <uni-forms-item label="户型" name="style" :required="true">
        <uni-easyinput type="text" v-model="formData.style" placeholder="请输入户型" />
      </uni-forms-item>
      <uni-forms-item label="小区" name="neighbourhood" :required="true">
        <uni-easyinput type="text" v-model="formData.neighbourhood" placeholder="请输入小区名称" />
      </uni-forms-item>
      <uni-forms-item label="最低租赁时长" name="leaseTerm" :required="true">
        <uni-easyinput type="text" v-model="formData.leaseTerm" placeholder="请输入最低租赁时长(单位:月)" />
      </uni-forms-item>
      <uni-forms-item label="最快入住时间" name="checkInTime" :required="true">
        <!-- <uni-easyinput type="text" v-model="formData.checkInTime" placeholder="请输入最快入住时间" /> -->
        <uni-datetime-picker type="date" return-type="string" placeholder="请输入最快入住时间" v-model="formData.checkInTime" ></uni-datetime-picker>
      </uni-forms-item>
      <uni-forms-item label="所在楼层" name="storey" :required="true">
        <uni-easyinput type="text" v-model="formData.storey" placeholder="请输入所在楼层" />
      </uni-forms-item>
      <uni-forms-item label="有无电梯" name="elevator" :required="true">
        <uni-data-checkbox :localdata="range" v-model="formData.elevator"></uni-data-checkbox>
      </uni-forms-item>
      <uni-forms-item label="建造时间" name="years" :required="true">
        <!-- <uni-datetime-picker type="date" return-type="timestamp" placeholder="请选择建造时间" v-model="formData.years" ></uni-datetime-picker> -->
        <uni-easyinput type="text" v-model="formData.years" placeholder="请输入建造年代" />
      </uni-forms-item>
      <uni-forms-item label="绿化面积" name="greenArea" :required="true">
        <uni-easyinput type="text" v-model="formData.greenArea" placeholder="请输入绿化面积(%百分比)" />
      </uni-forms-item>
      <!-- <uni-forms-item label="联系方式" name="phone" :required="true">
        <uni-easyinput type="text" v-model="formData.phone" placeholder="请输入联系方式" />
      </uni-forms-item> -->
      <uni-forms-item label="房屋条件" name="usp">
        <checkbox-group @change="uspChange">
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
  import { addHouse, updateHouse, getHouse } from "../../apis/house.js";

  export default {
    name: "addHouse",
    onLoad(options) {
      // 存在id即是修改
      if (options.id) {
        this.isAdd = false;
        getHouse(options.id).then(res => {
          let pics = res.data.pics;
          // 避免图片过长
          if (pics.length > 5) {pics.slice(0, 3);}
          // 自动*100
          res.data.greenArea = res.data.greenArea * 100;
          // res.data.delete("lessor");
          // phone get⭐daze
          // this.formData.phone = res.data.phone;
          // 赋值
          this.formData = res.data;
          // 处理图片格式（初始化）
          this.formData.pics = [];
          // 处理图片（依据vant-upload组件的格式对数据进行修改）
          pics.forEach(v => {this.formData.pics.push({url: v, thumb: v, type: "image"});});
          this.formData.locationOne = 50;
          this.formData.locationTwo = 5001;
          this.formData.orientation = this.revertDirection[this.formData.orientation];
          this.items.forEach(v => { // 根据情况修改勾选条件的checked显示
            if (res.data.usp.includes(v.value)) {
              v.checked = true;
            }
          })
          console.log(this.formData);
        }).catch(err => {
          console.log(err);
        })
      }
    },
    data() {
      return {
        direction: {
          "东": "EAST",
          "北": "NORTH",
          "南": "SOUTH",
          "西": "WEST"
        },
        revertDirection: {
          "EAST": "东",
          "NORTH": "北",
          "SOUTH": "南",
          "WEST": "西"
        },
        isAdd: true,
        range: [{"value": true, "text": "有"}, {"value": false, "text": "无"}],
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
          locationOne: 50,
          locationTwo: 5001,
          locationThreeName: "",
          pics: [],
          detailLocation: "",
          checkInTime: "",
          name: "",
          brief: "",
          leaseTerm: "",
          longitude: "",
          latitude: "",
          area: "",
          moneyMonth: "",
          orientation: "",
          style: "",
          neighbourhood: "",
          storey: "",
          elevator: false,
          years: "",
          greenArea: "",
          usp: []
        },
        rules: {
          name: {
            rules: [{
              required: true,
              errorMessage: "请输入房名"
            }]
          },
          moneyMonth: {
            rules: [{
              required: true,
              errorMessage: "请输入价格"
            }]
          },
          name: {
            rules: [{
              required: true,
              errorMessage: "请输入房名"
            }]
          },
          area: {
            rules: [{
              required: true,
              errorMessage: "请输入面积"
            }]
          },
          orientation: {
            rules: [{
              required: true,
              errorMessage: "请输入朝向"
            }]
          },
          detailLocation: {
            rules: [{
              required: true,
              errorMessage: "请输入具体地址"
            }]
          },
          style: {
            rules: [{
              required: true,
              errorMessage: "请输入户型"
            }]
          },
          storey: {
            rules: [{
              required: true,
              errorMessage: "请输入楼层"
            }]
          },
          years: {
            rules: [{
                required: true,
                errorMessage: "请输入建造时间"
              }]
          },
          greenArea: {
            rules: [{
              required: true,
              errorMessage: "请输入绿化面积"
            }]
          }
        }
      }
    },
    methods: {
      uspChange(e) { // 修改房屋条件
        // console.log(e);
        this.formData.usp = e.detail.value;
      },
      setMyCity(location) { // 设置城区名字
        this.formData.locationThreeName = location;
      },
      setRegion(region) {
        this.formData.region = region;
      },
      setLocalPosition(longitude, latitude) {
        this.formData.longitude = longitude;
        this.formData.latitude = latitude;
      },
      submitForm() { // 提交按钮
        this.$refs.form.submit().then(res => {
          // 判断location、region是否填写完毕
          if(!this.formData.locationThreeName || !this.formData.latitude) {
            console.log(this.formData.elevator);
            throw new Error("没填捏");
          } else { // 发请求
            if (this.isAdd) {
              let tempData = Object.assign({}, this.formData);
              let pics = this.formData.pics;
              tempData.pics = [];
              pics.forEach(v => {tempData.pics.push(v.url)});
              tempData.head = pics[0].url;
              tempData.greenArea = res.greenArea/100;
              tempData.orientation = this.direction[res.orientation];
              addHouse(tempData).then(result => {
                console.log(result);
                uni.navigateBack();
              }).catch(err => {
                console.log(err);
              })
            } else {
              let tempData = Object.assign({}, this.formData);
              let pics = this.formData.pics;
              tempData.pics = [];
              pics.forEach(v => {tempData.pics.push(v.url)});
              tempData.head = pics[0].url;
              tempData.greenArea = res.greenArea/100;
              tempData.orientation = this.direction[res.orientation];
              updateHouse(tempData).then(result => {
                console.log(result);
                uni.navigateBack();
              }).catch(err => {
                console.log(err);
              })
            }
          }
        }).catch(err => {
          console.log(err);
          uni.showModal({
            title: "有点小意外",
            content: '少侠留步, 还没写完呢',
            showCancel: false,
          });
        })
      },
      upload: async function (file) { // 上传文件
        console.log(file.file);
        let result = await getUploadToken();
        qiniu.upload(file.file.url, (res) => {
          this.formData.pics.push({
            url: res.imageURL,
            thumb: res.imageURL,
            name: res.key,
            type: "image"
          });
        }, (error) => {
          console.log(error);
        }, {
          region: "SCN",
          uptoken: result.data.uploadToken,
          domain: this.url,
          shouldUseQiniuFileName: true
        })
      },
      deletePics(e) { // 删除图片
        console.log(e.detail.index);
        let index = e.detail.index;
        this.formData.pics.splice(index, 1);
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
