import { request } from "./request.js";

const baseUrl = "/location";

export function getLocations() {
  return request({
    url: `${baseUrl}`,
    method: "get",
    params: {
      id: 5001
    }
  })
}