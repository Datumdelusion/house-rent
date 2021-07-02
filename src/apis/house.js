import { request } from "./request.js";

const baseUrl = '/houses';

export function pageHouses(form) {
  return request({
    url: `${baseUrl}`,
    method: "get",
    params: form
  })
}

export function getHouse(houseId) {
  return request({
    url: `${baseUrl}/detail/${houseId}`,
    method: "get"
  })
}
  
export function deleteHouse(houseId) {
  return request({
    url: `${baseUrl}/deleteHouse/${houseId}`,
    method: "delete",
    data: {
      houseId: houseId
    }
  })
}

export function updateHouse(form) {
  return request({
    url: `${baseUrl}/editHouse/${form.id}`,
    method: "post",
    data: form
  })
}

export function addHouse(form) {
  return request({
    url: `${baseUrl}/newHouse`,
    method: "post",
    data: form
  })
}