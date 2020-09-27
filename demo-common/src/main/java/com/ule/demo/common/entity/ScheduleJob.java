package com.ule.demo.common.entity;

import com.ule.demo.common.springside.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;

/**
 * @author fanxl
 * @version 1.0
 * @className ScheduleJob
 * @description //TODO
 * @date 下午4:23  19-4-2
 **/
@Table(name = "task_schedule")
@Getter
@Setter
public class ScheduleJob extends AbstractEntity<Long> {
    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;

    private Date updateTime;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 任务分组
     */
    private String jobGroup;
    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 描述
     */
    private String description;
    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;
    /**
     * 任务是否有状态
     */
    private String isConcurrent;
    /**
     * spring bean
     */
    private String springId;
    /**
     * 任务调用的方法名
     */
    private String methodName;


    public static ScheduleJob transMap2Bean(Map<String, Object> map) {
        ScheduleJob job = new ScheduleJob();
        try {
            job.setId((Long) map.get("job_id"));
            job.setJobName((String) map.get("job_name"));
            job.setJobGroup((String) map.get("job_group"));
            job.setJobStatus((String) map.get("job_status"));
            job.setCronExpression((String) map.get("cron_expression"));
            job.setDescription((String) map.get("description"));
            job.setBeanClass((String) map.get("bean_class"));
            job.setIsConcurrent((String) map.get("is_concurrent"));
            job.setSpringId((String) map.get("spring_id"));
            job.setMethodName((String) map.get("method_name"));
            //job.setCreateTime(new Date((String) map.get("create_time")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return job;
    }
}
