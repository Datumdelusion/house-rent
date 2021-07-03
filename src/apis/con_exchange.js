import { request } from "./request.js";

export function geyAllContracts() {
  return request({
    url: "/lessor/intention",
    method: "get"
  })
}

// 房主建立合约
export function setupContract(intentionId) {
  return request({
    url: `/lessor/contracts/intention/${intentionId}`,
    method: "put",
    data: {
      intentionId
    }
  })
}

// 获取未确定的合同
export function getMyContracts() {
  return request({
    url: "/lessor/contracts?contractStatus=uncompleted",
    method: "get"
  })
}

// 房主确认
export function confirmContract(contractId) {
  return request({
    url: `/lessor/contracts/${contractId}`,
    method: "put",
    data: {
      contractId
    }
  })
}

// 房主获取用户id
export function getUserInfo(userId) {
  return request({
    url: `/lessor/contract/userInfo/${userId}`,
    method: "get"
  })
}

// 房主拒绝
export function rejectContract(contractId) {
  return request({
    url: `/lessor/contract/reject/${contractId}`,
    method: "put",
    data: {
      contractId
    }
  })
}