package net.prolancer.validation.common.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MockResponse {
    @NotNull
    @NotEmpty
    @Size(min = 36, max = 36)
    @ApiModelProperty(notes = "API ID")
    private String apiId;


    @ApiModelProperty(notes = "API Description")
    private String description;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    @Pattern(regexp = "JSON|XML", flags = Pattern.Flag.CASE_INSENSITIVE)
    @ApiModelProperty(notes = "Response Type (JSON or XML)")
    private String responseType;

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "Response Body Message")
    private String responseBody;

    public MockResponse() {
    }

    public MockResponse(String description, String responseType, String responseBody) {
        this.description = description;
        this.responseType = responseType;
        this.responseBody = responseBody;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return "MockResponse{" +
                "apiId='" + apiId + '\'' +
                ", description='" + description + '\'' +
                ", responseType='" + responseType + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
