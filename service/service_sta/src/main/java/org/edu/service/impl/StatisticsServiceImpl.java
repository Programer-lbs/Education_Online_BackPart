package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.edu.bean.StatisticsDaily;
import org.edu.commonutil.Response;
import org.edu.mapper.StatisticsMapper;
import org.edu.service.StatisticsService;
import org.edu.service.UCenterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.StartDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;
    @Autowired
    private UCenterClient centerClient;

    @Override
    public StatisticsDaily saveRegisterCountOneDay(String day) {
        QueryWrapper<StatisticsDaily> statisticsDailyQueryWrapper = new QueryWrapper<>();
        statisticsDailyQueryWrapper.eq("date_calculated",day);
        StatisticsDaily statisticsDaily1 = statisticsMapper.selectOne(statisticsDailyQueryWrapper);

        //调用远程接口获取注册人数
        Response registerNum = centerClient.getRegisterNum(day);
        Integer num = (Integer) registerNum.getData().get("register_num");

        if(statisticsDaily1!=null){
            if(statisticsDaily1.getRegisterNum()!=num){
                statisticsDaily1.setRegisterNum(num);
                statisticsMapper.updateById(statisticsDaily1);
            }
            return statisticsDaily1;
        }
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(num);
        statisticsDaily.setDateCalculated(day);
        statisticsMapper.insert(statisticsDaily);
        return statisticsDaily;
    }

    @Override
    public Map<String, Object> statisticsData(String type, String start, String end) {
        List<StatisticsDaily> statisticsDailies = statisticsMapper.statisticsData(type, start, end);

        //存放日期
        List<String> date_calculated = new ArrayList<>();
        //存放数据
        List<Integer> data = new ArrayList<>();
        for(StatisticsDaily statisticsDaily:statisticsDailies){
            date_calculated.add(statisticsDaily.getDateCalculated());
            data.add(statisticsDaily.getData());
        }

        //封装集合，数据返回
        Map<String,Object> maps = new HashMap<>();
        maps.put("date",date_calculated);
        maps.put("data",data);
        return maps;
    }
}
