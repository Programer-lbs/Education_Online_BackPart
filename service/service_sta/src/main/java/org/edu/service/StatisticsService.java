package org.edu.service;

import com.sun.jersey.json.impl.provider.entity.JSONJAXBElementProvider;
import org.edu.bean.StatisticsDaily;

import java.util.Map;

public interface StatisticsService  {

    //统计某一天(表中不存在)的注册人数
    StatisticsDaily saveRegisterCountOneDay(String day);

    //统计某个时间段的数据
    Map<String,Object> statisticsData(String type,String start,String end);


}
