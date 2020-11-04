package org.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.bean.StatisticsDaily;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsMapper extends BaseMapper<StatisticsDaily> {

    //查询某个时间段内的某一字段数据
    List<StatisticsDaily> statisticsData(@Param("type")String type,
                                         @Param("start")String start,
                                         @Param("end")String end);
}
