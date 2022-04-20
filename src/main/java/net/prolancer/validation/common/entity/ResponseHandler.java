package net.prolancer.validation.common.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom ResponseEntity Object
 * @author jaechulhan
 */
@Slf4j
public final class ResponseHandler {
    private ResponseHandler() {
        throw new IllegalStateException("ResponseHandler Class");
    }
    /**
     * Generate success response message
     * @param message
     * @param resObj
     * @param
     * @return
     */
    public static ResponseEntity<Object> ok(String message, Object resObj) {
        return ok(message, HttpStatus.OK, resObj);
    }

    /**
     * Generate success response message
     * @param message
     * @param status
     * @param resObj
     * @param
     * @return
     */
    public static ResponseEntity<Object> ok(String message, HttpStatus status, Object resObj) {
        Map<String, Object> resMap = new LinkedHashMap<>(); // To preserve insertion order, use LinkedHashMap

        resMap.put("result", "success");
        resMap.put("status", status.value());
        resMap.put("message", message);
        resMap.put("data", resObj);

        return new ResponseEntity<>(resMap, status);
    }

    /**
     * Generate error response message
     * @param message
     * @param resObj
     * @return
     */
    public static ResponseEntity<Object> error(String message, Object resObj) {
        return error(message, HttpStatus.OK, resObj);
    }

    /**
     * Generate error response message
     * @param message
     * @param status
     * @param resObj
     * @return
     */
    public static ResponseEntity<Object> error(String message, HttpStatus status, Object resObj) {
        Map<String, Object> resMap = new LinkedHashMap<>(); // To preserve insertion order, use LinkedHashMap

        resMap.put("result", "error");
        resMap.put("status", status.value());
        resMap.put("message", message);
        resMap.put("data", resObj);

        return new ResponseEntity<>(resMap, status);
    }
}
