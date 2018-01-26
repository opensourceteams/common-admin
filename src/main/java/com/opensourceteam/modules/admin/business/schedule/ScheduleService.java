package com.opensourceteam.modules.admin.business.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
@Service
public class ScheduleService {


    Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Scheduled(cron="0/10 * * * * ? ")   //每5秒执行一次
    public void cron() throws IOException {
        logger.info("[SheduledService cron] 调度");
       // businessService.monitorData();
       // businessService.monitorBusiness();

    }
}
