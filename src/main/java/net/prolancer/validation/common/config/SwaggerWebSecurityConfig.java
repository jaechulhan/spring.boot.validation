package net.prolancer.validation.common.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SwaggerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static String[] SWAGGER_URL_PATHS = new String[]{"/swagger-ui.html**", "/swagger-resources/**",
            "/v2/api-docs**", "/webjars/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable();
        http.requestMatchers().antMatchers(SWAGGER_URL_PATHS)
                .and().authorizeRequests().antMatchers(SWAGGER_URL_PATHS).permitAll();
    }

}
