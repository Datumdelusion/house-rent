import { request } from "./request"
import md5 from "md5"

let key = "34WBZ-RUICD-XS344-HRI6O-Q53SF-DFBJ2";

export function getCity(location) {
  return request({
    url: "https://apis.map.qq.com/ws/geocoder/v1",
    method: "get",
    params: {
      key: key,
      location,
      sig: calcSIG(key, location)
    }
  })
}

function calcSIG(key, location) {
  let SK = "ttBX6JkUrAQH92xCvJD0pQnIYXkyRQV";
  let sig = `/ws/geocoder/v1?key=${key}&location=${location}${SK}`;
  SK = md5(sig);
  return encodeURI(SK);
}