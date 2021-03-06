package net.prolancer.validation.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.prolancer.validation.common.config.MessageHelper;
import net.prolancer.validation.common.contants.AppConstants;
import net.prolancer.validation.common.entity.ResponseHandler;
import net.prolancer.validation.common.entity.MockResponse;
import net.prolancer.validation.common.service.MockResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AppConstants.REST_API_PREFIX + "/mock")
@AllArgsConstructor
@Slf4j
@Api(value = "MockResponseController", description = "Managing Mock Responses (Create / Update / Delete / Search)")
public class MockResponseController {

    private final MessageHelper messageHelper;

    private final MockResponseService mockResponseService;

    @GetMapping("/{apiId}")
    @ApiOperation(value = "Search Mock Response by API ID")
    public ResponseEntity<Object> find(@PathVariable String apiId) {
        MockResponse data = mockResponseService.find(apiId);
        return ResponseHandler.ok(messageHelper.get("default.search.success.message")
                , HttpStatus.OK
                , data);
    }

    @GetMapping("/list")
    @ApiOperation(value = "Search Mock Response")
    public ResponseEntity<Object> findAll() {
        List<MockResponse> data = mockResponseService.findAll();
        return ResponseHandler.ok(messageHelper.get("default.search.success.message")
                , HttpStatus.OK
                , data);
    }

    @PostMapping("/save")
    @ApiOperation(value = "Create or Update a New Mock Response")
    public ResponseEntity<Object> save(@Valid @RequestBody MockResponse mockResponse) {
        MockResponse data = mockResponseService.save(mockResponse);
        return ResponseHandler.ok(messageHelper.get("default.submit.success.message")
                , HttpStatus.OK
                , data);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete an Existing Mock Response")
    public ResponseEntity<Object> delete(@Valid @RequestBody MockResponse mockResponse) {
        int result = mockResponseService.delete(mockResponse.getApiId());
        return ResponseHandler.ok(messageHelper.get("default.delete.success.message")
                , HttpStatus.OK
                , result > 0 ? mockResponse : null);
    }

}
