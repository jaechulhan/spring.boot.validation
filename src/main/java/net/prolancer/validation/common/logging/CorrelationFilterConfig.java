package net.prolancer.validation.common.logging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Data
@Configuration
@ConfigurationProperties(prefix = "config.correlation")
public class CorrelationFilterConfig {

    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "X-Response-Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "CorrelationFilter.UUID";
    public static final String DEFAULT_MDC_CLIENT_IP_KEY = "CorrelationFilter.ClientIP";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String mdcClientIpKey = DEFAULT_MDC_CLIENT_IP_KEY;
    private String requestHeader = null;

    @Bean
    public FilterRegistrationBean<Filter> servletRegistrationBean() {
        final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        final CorrelationFilter correlationFilter = new CorrelationFilter(responseHeader, mdcTokenKey, mdcClientIpKey, requestHeader);
        registrationBean.setFilter(correlationFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
