import { request } from "./request.js";

const baseUrl = "/user"

export function getMyExchange() {
  return request({
    url: `${baseurl}/contracts`,
    method: "get"
  })
}

export function confirmContracts() {
  return request({
    url: `${baseUrl}/`,
    method; "post"
  })
}