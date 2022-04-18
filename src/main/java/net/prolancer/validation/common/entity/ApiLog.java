package net.prolancer.validation.common.entity;

import java.time.Instant;

public class ApiLog {

    private String corrId;
    private String apiUrl;
    private String requestMessage;
    private Instant requestDate;
    private String responseMessage;
    private Instant responseDate;
    private String ipAddress;

    public ApiLog() {
    }

    public ApiLog(String corrId, String apiUrl, String requestMessage, Instant requestDate, String responseMessage, Instant responseDate, String ipAddress) {
        this.corrId = corrId;
        this.apiUrl = apiUrl;
        this.requestMessage = requestMessage;
        this.requestDate = requestDate;
        this.responseMessage = responseMessage;
        this.responseDate = responseDate;
        this.ipAddress = ipAddress;
    }

    public String getCorrId() {
        return corrId;
    }

    public void setCorrId(String corrId) {
        this.corrId = corrId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public Instant getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Instant requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Instant getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Instant responseDate) {
        this.responseDate = responseDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "ApiLog{" +
                "corrId='" + corrId + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", requestMessage='" + requestMessage + '\'' +
                ", requestDate=" + requestDate +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseDate=" + responseDate +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
