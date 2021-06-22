/**
 * 引入高德地图SDK
 */
let amap = require('../amap/amap-wx.js');

let amapPlugin = new amap.AMapWX({
  key: 'e7737c483d05646e9eb137e1ba12d2ab'
})

export { amapPlugin };