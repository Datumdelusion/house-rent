import { request } from "./request.js";

export function wantHouse(houseId) {
  return request({
    url: `/user/intention/${houseId}`,
    method: "get"
  })
}

export function myOrders() {
  return request({
    url: "/user/contracts?contractStatus=uncompleted",
    method: "get"
  })
}

export function historyOrders(contractStatus) {
  return request({
    url: "/user/contracts",
    method: "get",
    params: {
      contractStatus
    }
  })
}
