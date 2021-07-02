import { request } from "./request.js";

export async function getUploadToken() {
  return request({
    url: "/lessor/upload_token",
    method: "get"
  })
}