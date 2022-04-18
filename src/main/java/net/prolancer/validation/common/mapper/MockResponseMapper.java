package net.prolancer.validation.common.mapper;

import net.prolancer.validation.common.entity.MockResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MockResponseMapper {
    List<MockResponse> selectMockResponse();

    MockResponse selectMockResponseById(String apiId);

    int countMockResponseById(String apiId);

    int insertMockResponse(MockResponse mockResponse);

    int updateMockResponse(MockResponse mockResponse);

    int deleteMockResponse(String apiId);
}
