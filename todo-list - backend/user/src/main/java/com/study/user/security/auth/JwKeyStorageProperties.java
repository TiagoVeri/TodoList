package com.study.user.security.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter @Setter
@Component
@Validated
@ConfigurationProperties("task.jwt.keystore")
public class JwKeyStorageProperties {

    @NotNull
    private Resource jksLocation;

    @NotBlank
    private String password;

    @NotBlank
    private String keypairAlias;
}
