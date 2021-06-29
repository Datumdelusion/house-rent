import { request } from "./request.js";

export function test() {
  return request({
    method: "get",
    url: "/house-rent-api/common/satoken"
  })
}