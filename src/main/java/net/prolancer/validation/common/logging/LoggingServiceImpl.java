package net.prolancer.validation.common.logging;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.prolancer.validation.common.entity.ApiLog;
import net.prolancer.validation.common.mapper.ApiLogMapper;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class LoggingServiceImpl implements LoggingService {

    private final ApiLogMapper apiLogMapper;

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        if (body != null) {
            stringBuilder.append("body=[" + body + "]");
        }

        // Print logging to console & files
        log.info(stringBuilder.toString());

        // Logging into Database Table
        ApiLog apiLog = new ApiLog();
        apiLog.setCorrId(MDC.get(CorrelationFilterConfig.DEFAULT_MDC_UUID_TOKEN_KEY));
        apiLog.setApiUrl(httpServletRequest.getRequestURI());
        apiLog.setRequestMessage(stringBuilder.toString());
        apiLog.setRequestDate(Instant.now());
        apiLog.setIpAddress(MDC.get(CorrelationFilterConfig.DEFAULT_MDC_CLIENT_IP_KEY));
        apiLogMapper.insertApiLog(apiLog);
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
        stringBuilder.append("responseBody=[").append(body).append("] ");

        // Print logging to console & files
        log.info(stringBuilder.toString());

        // Logging into Database Table
        ApiLog apiLog = new ApiLog();
        apiLog.setCorrId(MDC.get(CorrelationFilterConfig.DEFAULT_MDC_UUID_TOKEN_KEY));
        apiLog.setResponseMessage(stringBuilder.toString());
        apiLog.setResponseDate(Instant.now());
        apiLogMapper.updateApiLog(apiLog);
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }

}
