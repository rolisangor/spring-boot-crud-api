package com.crud.springbootcrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier(value = BeanIds.AUTHENTICATION_MANAGER)
    private AuthenticationManager authenticationManager;

    private String clientid = "secretId";
    private String clientSecret = "secretKey";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEAzXJo8elJhKskFjZn7hcXkvQTRyWB4w8EaOfdVushYmNqYVtK\n" +
            "xyNOna64sC67wBVvDSJhc53HxxI/WI43EQN1MwaTJ1lCYU1RftP85+pqziaYrS/f\n" +
            "cHHOFoNIPl5Oo1x372ZcGcSqnVQ5Qx0a0pncnAHtmHVk2Ww+OOmrOY8f0Y1hLMtF\n" +
            "995UbgkkVRMpg4SFQ6kudN8BdXPbkNw29zn4OCD8xnxWnoIz+v7Zsjbrteps5Inn\n" +
            "F/ifjexAr57nJUgWdR/oOiUQb4Hr1quw6cisK+pMdTjMvnOU6EqO6cjZj64Uiqob\n" +
            "4StOtvBJKUemK0t4y3WB2XKX4RV4LOJRcynfzwIDAQABAoIBAH5bw9gl16XVbNiM\n" +
            "ldeGNGWZ4NF9bCmu7tNm6GexLlNJOgCvGYrpWEz7TXRvCeLWS/sUDkQB4wG5Y2Sf\n" +
            "85ZvPgIoJRGqgH1ABMOwtngdYZtxea3rJ84M8Og/zostjchD8zA3EKZ4o0coqh9x\n" +
            "IzIsl9cMCIogiep//LUcYseAE93ZsJgTOVKGkYD96hVXolEGoJTUoLYSUzytrvnI\n" +
            "gEtQq4W9nxnTN01416lGio9AMHERVjskXLVvstRwmgAWm1vv+eWkkIK1jRUIRaO9\n" +
            "65ayVtjzQ3WatgMvREkmkqDmwMpgTT+G6urMVmnJebRYzh8Gu6Pq/RE5m2vnAy2e\n" +
            "7J5SY4ECgYEA68mW+yPbZkS5+WCP3xeXJddVFfjTyWzw59AMOTnzy2znthqX55ec\n" +
            "hWBTOFovT4BLMhhTg/RGtocoNQjBefC3gD0TMaOQ5vn2jq1L7kgAd0SIRXEJKRFO\n" +
            "xi3lXSxXAbWAJGFmlJHRS77+vEgApr/uc8L+H21XrmlgwZWdImMvL58CgYEA3w79\n" +
            "aPJYUHQnffS7B7Saas7JyMybdOiShvmYNyEH8Q2FnGOtp5kfuU+2qCQPb8trnMeF\n" +
            "hdsWLf+a+f3Lfp3YRC2KwZFIE79V5We+n9/1sjPptap+rMJSqnKvmuriZjYV6HB4\n" +
            "ojPK5qeQlcKMKH/VeGcoPljBYgB3uSCXz2GvodECgYB4/y8SW3k4RuZkuY+6/SEc\n" +
            "Ux8fslwETnplixbquvmHl7Bke8seFmhE7YRnM6h5lK2B3Ifm/BDZlAwnAwI+MDAK\n" +
            "h8z0uSofzYAgAYAonBxZF/sSH4qC35XcDytXn+xaPSiwAqegcB/GThHszso+x9l2\n" +
            "4EaGHBdVsk/rp3pGbj2FowKBgBWQnW9H/sHIeQj0YCCelU8+LsP7BAyJb6EdAFpU\n" +
            "FzB23jQAbwmFBEwmKMKYueVVBVJi98W5KKvJynHg14umNwJgZMnSAeXm2acUIJnU\n" +
            "TSytWCr4EMcjAlr4O4H+s2SuVe/i78a+ux8RlCjmZiaENV8umfVtH0UaRnjfbc3e\n" +
            "49wBAoGAJKPq7fF1HdZ64XSSldvukQvaidXriXQLyiascRl1rg0VkFx6MnUYZYv2\n" +
            "jbdBTVi/UMQU1V9D9lRmpaaJq0J/F+JzgTZqA6pXhI8wrJB2O4KtyXgIOY3j1O6l\n" +
            "A4bLFI/FvaMlLsKozjBXBcwZeCb/2kEfuEYV6WbpsSjAnhEGULQ=\n" +
            "-----END RSA PRIVATE KEY-----\n";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzXJo8elJhKskFjZn7hcX\n" +
            "kvQTRyWB4w8EaOfdVushYmNqYVtKxyNOna64sC67wBVvDSJhc53HxxI/WI43EQN1\n" +
            "MwaTJ1lCYU1RftP85+pqziaYrS/fcHHOFoNIPl5Oo1x372ZcGcSqnVQ5Qx0a0pnc\n" +
            "nAHtmHVk2Ww+OOmrOY8f0Y1hLMtF995UbgkkVRMpg4SFQ6kudN8BdXPbkNw29zn4\n" +
            "OCD8xnxWnoIz+v7Zsjbrteps5InnF/ifjexAr57nJUgWdR/oOiUQb4Hr1quw6cis\n" +
            "K+pMdTjMvnOU6EqO6cjZj64Uiqob4StOtvBJKUemK0t4y3WB2XKX4RV4LOJRcynf\n" +
            "zwIDAQAB\n" +
            "-----END PUBLIC KEY-----";


    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientid)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token", "client_credentials").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);
    }
}
