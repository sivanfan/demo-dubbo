package com.ule.demo.service.handler.taskschedule.impl;

import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.entity.ScheduleJob;
import com.ule.demo.service.api.autoinstall.anno.FunctionId;
import com.ule.demo.service.handler.taskschedule.ITaskScheduleHandler;
import com.ule.demo.service.service.taskschedule.TaskScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CerManagerHandlerImpl
 * @Author fanxl
 * @Description 证书管理方法类
 * @Date 17:04  2019/1/28
 * @Version 1.0
 */
@Slf4j
@Service
public class TaskScheduleHandlerImpl implements ITaskScheduleHandler {

    @Autowired
    private TaskScheduleService taskScheduleService;

    /**
     * 查询所有的定时任务
     */
    @FunctionId(value = "200001", desc = "查询所有的定时任务")
    public Response getAllJobs(Request request) {
        List<ScheduleJob> list= taskScheduleService.findAll();
        return Response.success(list);
    }
    /**
     * 添加一个定时任务
     */
    @FunctionId(value = "200002", desc = "添加一个定时任务")
    public Response addTask(Request request){
        ScheduleJob job=(ScheduleJob)request.getBody();
        job.setCreateTime(new Date());
        taskScheduleService.save(job);
        return Response.success();
    }
    /**
     * 更改任务状态
     *
     */
    @FunctionId(value = "200003", desc = "更改任务状态")
    public Response changeStatus(Request request) {
        Map map=(Map) request.getBody();
        Long jobId=new Long(map.get("jobId").toString());
        String jobStatus=map.get("jobStatus").toString();
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return Response.fail("任务不存在");
        }
        job.setJobStatus(jobStatus);
        taskScheduleService.updateNotNull(job);
        return Response.success(job);
    }
    /**
     * 立即执行job
     *
     */
    @FunctionId(value = "200004", desc = "立即执行job")
    public Response runAJobNow(Request request){
        ScheduleJob scheduleJob=getTaskById(new Long(request.getBody().toString()));
        return Response.success(scheduleJob);
    }
    /**
     * 更改任务 cron表达式
     *
     */
    @FunctionId(value = "200005", desc = "更改任务cron表达式")
    public Response updateCron(Request request){
        Map map=(Map) request.getBody();
        Long jobId=new Long(map.get("jobId").toString());
        String cron=map.get("cron").toString();
        ScheduleJob job = getTaskById(jobId);
        if (job == null) {
            return Response.fail("任务不存在");
        }
        job.setCronExpression(cron);
        taskScheduleService.updateNotNull(job);
        return Response.success(job);
    }
    /**
     * 从数据库中查询job
     */
    public ScheduleJob getTaskById(Long jobId) {
        ScheduleJob job = taskScheduleService.findOne(jobId);
        return job;
    }
}
