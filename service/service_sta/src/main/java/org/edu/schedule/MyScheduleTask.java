package org.edu.schedule;

import org.edu.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyScheduleTask {

    @Autowired
    private StatisticsService statisticsService;


    //定时任务，每天在凌晨1点调用该方法 进行数据统计
    @Scheduled(cron = "0 0 1 * * ?" )
    public void statisticsData(){
        Date dayAgo  = new Date(System.currentTimeMillis()-1000*60*60*24);
        statisticsService.saveRegisterCountOneDay(new SimpleDateFormat("yyyy-MM-dd").format(dayAgo));
    }
}
