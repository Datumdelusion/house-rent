import { request } from "./request.js";

const baseUrl = "/location";

export function getLocations() {
  return request({
    url: `/common${baseUrl}`,
    method: "get",
    params: {
      id: 5001
    }
  })
}