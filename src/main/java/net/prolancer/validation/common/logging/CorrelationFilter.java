package net.prolancer.validation.common.logging;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class CorrelationFilter extends OncePerRequestFilter {

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private final String responseHeader;
    private final String mdcTokenKey;
    private final String mdcClientIpKey;
    private final String requestHeader;

    public CorrelationFilter() {
        responseHeader = CorrelationFilterConfig.DEFAULT_RESPONSE_TOKEN_HEADER;
        mdcTokenKey = CorrelationFilterConfig.DEFAULT_MDC_UUID_TOKEN_KEY;
        mdcClientIpKey = CorrelationFilterConfig.DEFAULT_MDC_CLIENT_IP_KEY;
        requestHeader = null;
    }

    public CorrelationFilter(final String responseHeader, final String mdcTokenKey, final String mdcClientIPKey, final String requestHeader) {
        this.responseHeader = responseHeader;
        this.mdcTokenKey = mdcTokenKey;
        this.mdcClientIpKey = mdcClientIPKey;
        this.requestHeader = requestHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = request;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);

        try {
            final String token = extractToken(request);
            final String clientIP = extractClientIP(request);
            MDC.put(mdcClientIpKey, clientIP);
            MDC.put(mdcTokenKey, token);
            // If there is no token header
            if (!StringUtils.hasText(request.getHeader(requestHeader))) {
                mutableRequest.putHeader(requestHeader, token);
            }
            // If Response Header is enabled and there is no token header
            if (StringUtils.hasText(responseHeader) && !StringUtils.hasText(response.getHeader(responseHeader))) {
                response.addHeader(responseHeader, token);
            }
            filterChain.doFilter(mutableRequest, response);
        } finally {
            MDC.remove(mdcTokenKey);
            MDC.remove(mdcClientIpKey);
        }
    }

    /**
     * Get Token from http-request
     * @param request
     * @return
     */
    private String extractToken(final HttpServletRequest request) {
        final String token;
        if (StringUtils.hasText(requestHeader) && StringUtils.hasText(request.getHeader(requestHeader))) {
            token = request.getHeader(requestHeader);
        } else {
            token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        }
        return token;
    }

    /**
     * Extract Client IP from http-request
     * @param request
     * @return
     */
    private String extractClientIP(final HttpServletRequest request) {
        final String clientIP;
        if (request.getHeader(X_FORWARDED_FOR) != null) {
            clientIP = request.getHeader(X_FORWARDED_FOR).split(",")[0];
        } else {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }

    @Override
    protected boolean isAsyncDispatch(final HttpServletRequest request) {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}
