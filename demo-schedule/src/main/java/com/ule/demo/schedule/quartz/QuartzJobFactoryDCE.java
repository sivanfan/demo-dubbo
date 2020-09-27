package com.ule.demo.schedule.quartz;

import com.ule.demo.common.entity.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fanxl
 * @version 1.0
 * @className QuartzJobFactoryDCE
 * @description 若一个方法一次执行不完下次轮转时则等待该方法执行完后才执行下一次操作
 * @date 下午5:16  19-4-2
 **/
@DisallowConcurrentExecution
public class QuartzJobFactoryDCE implements Job {
    public final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext context){
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }
}