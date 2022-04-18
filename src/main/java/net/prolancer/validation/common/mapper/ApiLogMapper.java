package net.prolancer.validation.common.mapper;

import net.prolancer.validation.common.entity.ApiLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiLogMapper {
    int insertApiLog(ApiLog apiLog);

    int updateApiLog(ApiLog apiLog);
}
