package com.datum.houserent.model.qiniuyun;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author datum
 * @date 2021/5/28 20:39
 */
@Data
@ToString
@EqualsAndHashCode
public class QiNiuYunVO {
    @ApiModelProperty(value = "七牛云url")
    private String url;

    @ApiModelProperty(value = "上传凭证")
    private String uploadToken;
}
