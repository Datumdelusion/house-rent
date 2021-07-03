import { request } from "./request.js";

export function getMyContracts() {
  return request({
    url: "/user/contracts",
    method: "get",
    params: {
      contractStatus: "uncompleted"
    }
  })
}

export function confirmContracts(contractId, month) {
  return request({
    url: `/user/contracts/${contractId}/${month}`,
    method: "post",
    data: {
      contractId,
      month
    }
  })
}

export function rejectContracts(contractId) {
  return request({
    url: `/user/contract/reject/${contractId}`,
    method: "put",
    data: {
      contractId
    }
  })
}