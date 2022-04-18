package net.prolancer.validation.common.service;

import lombok.AllArgsConstructor;
import net.prolancer.validation.common.entity.MockResponse;
import net.prolancer.validation.common.mapper.MockResponseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MockResponseServiceImpl implements MockResponseService {
    private final MockResponseMapper mockResponseMapper;

    @Override
    public MockResponse find(String apiId) {
        return mockResponseMapper.selectMockResponseById(apiId);
    }

    @Override
    public List<MockResponse> findAll() {
        return mockResponseMapper.selectMockResponse();
    }

    @Override
    public MockResponse save(MockResponse mockResponse) {
        if (mockResponseMapper.countMockResponseById(mockResponse.getApiId()) > 0) {
            mockResponseMapper.updateMockResponse(mockResponse);
        } else {
            mockResponseMapper.insertMockResponse(mockResponse);
        }
        return mockResponseMapper.selectMockResponseById(mockResponse.getApiId());
    }

    @Override
    public int delete(String apiId) {
        return mockResponseMapper.deleteMockResponse(apiId);
    }
}
