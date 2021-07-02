import { request } from "./request.js";

const baseUrl = "/star";

export function myStars() {
  return request({
    url: `${baseUrl}`,
    method: "get"
  })
}

export function starIt(houseId) {
  return request({
    url: `${baseUrl}/houseId`,
    method: "put",
    data: {
      houseId
    }
  })
}

export function cancelStar(houseId) {
  return request({
    url: `${baseUrl}/houseId`,
    method: "delete",
    data: {
      houseId
    }
  })
}