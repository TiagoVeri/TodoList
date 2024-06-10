package com.study.user.security.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

@Component
@Getter @Setter
@Validated
@ConfigurationProperties("usertask.auth")
public class UserSecurityProperties {

    @NotBlank
    private String providerUrl;
}
