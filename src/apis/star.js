import { request } from "./request.js";

const baseUrl = "/common/star";

export function myStars() {
  return request({
    url: `${baseUrl}`,
    method: "get"
  })
}

export function starIt(houseId) {
  return request({
    url: `${baseUrl}/${houseId}`,
    method: "put",
    data: {
      houseId
    }
  })
}

export function cancelStarByHouseId(houseId) {
  return request({
    url: `${baseUrl}/house/${houseId}`,
    method: "delete",
    data: {
      houseId
    }
  })
}

export function cancelStarByStarId(starId) {
  return request({
    url: `${baseUrl}/star/${starId}`,
    method: "delete",
    data: {
      starId
    }
  })
}