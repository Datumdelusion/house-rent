import { request } from "./request.js";

const baseUrl = "/login";

export function loginTest() {
  return request({
    url: `/common${baseUrl}`,
    method: "get",
    params: {
      loginId: "1"
    }
  })
}

export function login(data) {
  return request({
    url: `/common${baseUrl}`,
    method: "post",
    data: data
  })
}