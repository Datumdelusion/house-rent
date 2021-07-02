package com.datum.houserent.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.datum.houserent.config.QiNiuConfig;
import com.datum.houserent.model.qiniuyun.QiNiuYunVO;
import com.datum.houserent.support.ResponsePack;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datum
 * @date 2021/5/20 21:37
 */

@RestController
@ResponsePack
@Api(tags = "七牛云上传-前端控制器")
public class QiNiuUploadController {

    private final QiNiuConfig qiNiuConfig;

    private final Auth auth;

    public QiNiuUploadController(QiNiuConfig qiNiuConfig, Auth auth) {
        this.qiNiuConfig = qiNiuConfig;
        this.auth = auth;
    }

    @SaCheckRole(value = {"admin","lessor"},mode = SaMode.OR)
    @ApiOperation("获取上传凭证(管理端，失效时间为1小时")
    @GetMapping("/lessor/upload_token")
    public QiNiuYunVO getUploadToken() {
        QiNiuYunVO qiNiuYunVO = new QiNiuYunVO();
        qiNiuYunVO.setUploadToken(auth.uploadToken(qiNiuConfig.getBucket()));
        qiNiuYunVO.setUrl(qiNiuConfig.getUrl());
        return qiNiuYunVO;
    }
}
