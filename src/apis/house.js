import { request } from "./request.js";

const baseUrl = '/houses';

export function pageHouses(form) {
  return request({
    url: `/common${baseUrl}`,
    method: "get",
    params: form
  })
}

export function getHouse(houseId) {
  return request({
    url: `/common${baseUrl}/detail/${houseId}`,
    method: "get"
  })
}

export function myHouses() {
  return request({
    url: "/lessor/houses",
    method: "get"
  })
}

export function deleteHouse(houseId) {
  return request({
    url: `/lessor${baseUrl}/deleteHouse/${houseId}`,
    method: "delete",
    data: {
      houseId: houseId
    }
  })
}

export function updateHouse(form) {
  return request({
    url: `/lessor${baseUrl}/editHouse/${form.id}`,
    method: "post",
    data: form
  })
}

export function addHouse(form) {
  return request({
    url: `/lessor${baseUrl}/newHouse`,
    method: "post",
    data: form
  })
}

export function shift2shelf(houseId) {
  return request({
    url: `/lessor/house/onShelf/${houseId}`,
    method: "put",
    data: {
      houseId
    }
  })
}