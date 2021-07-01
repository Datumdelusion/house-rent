package com.datum.houserent.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author datum
 * @date 2021/7/1 18:03
 */
@Data
@Configuration
@ConfigurationProperties("miniapp")
public class WxMaConfig {

    private String appid;

    private String secret;

    private String format;

    @Bean
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();

        config.setAppid(appid);
        config.setSecret(secret);
        config.setMsgDataFormat(format);

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }
}

