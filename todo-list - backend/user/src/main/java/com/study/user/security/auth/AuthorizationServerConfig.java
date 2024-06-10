package com.study.user.security.auth;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class AuthorizationServerConfig {

    //TODO do the changes when add Roles.
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(autorize -> autorize
                    .requestMatchers(HttpMethod.POST, "api/user/login").permitAll()
                    .requestMatchers(HttpMethod.POST,"api/user/create").permitAll()
                    .anyRequest().permitAll()) // TODO change to authorize when the tests are over
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)  // Desabilitar frameOptions para H2 console, remover mais tarde.
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings providerSettings(UserSecurityProperties properties){
        return AuthorizationServerSettings.builder()
                .issuer(properties.getProviderUrl())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(JwKeyStorageProperties properties) throws Exception{
        char[] keyStorePass = properties.getPassword().toCharArray();
        String keypairAlias = properties.getKeypairAlias();

        Resource jksLocation = properties.getJksLocation();
        InputStream inputStream = jksLocation.getInputStream();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, keyStorePass);

        RSAKey rsaKey = RSAKey.load(keyStore, keypairAlias, keyStorePass);

        return new ImmutableJWKSet<>(new JWKSet(rsaKey));
    }


}
