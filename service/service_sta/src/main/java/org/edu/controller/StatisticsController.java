package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.StatisticsDaily;
import org.edu.commonutil.Response;
import org.edu.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("统计模块")
@RestController
@RequestMapping("/service_sta")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation("统计一天的注册人数")
    @PostMapping("/register_num")
    public Response getRegisterNum(@RequestParam String day){
        StatisticsDaily daily = statisticsService.saveRegisterCountOneDay(day);
        return Response.ok().data("data",daily);
    }

    @ApiOperation("查询某个时间段的内容")
    @GetMapping("/statistics")
    public Response statisticsData(String type,String start,String end){
        Map<String, Object> map = statisticsService.statisticsData(type, start, end);
        return Response.ok().data("data",map);
    }
}
