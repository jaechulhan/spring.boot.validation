package net.prolancer.validation.common.service;

import net.prolancer.validation.common.entity.MockResponse;

import java.util.List;

public interface MockResponseService {
    MockResponse find(String apiId);

    List<MockResponse> findAll();

    MockResponse save(MockResponse mockResponse);

    int delete(String apiId);
}
