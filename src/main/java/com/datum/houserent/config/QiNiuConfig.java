package com.datum.houserent.config;

import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author datum
 * @date 2021/5/20 21:40
 */
@Data
@Configuration
@ConfigurationProperties("qiniu")
public class QiNiuConfig {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String url;

    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

}
