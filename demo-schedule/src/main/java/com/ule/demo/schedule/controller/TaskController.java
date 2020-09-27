package com.ule.demo.schedule.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.ule.demo.api.IDealProcessorService;
import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.constants.Constants;
import com.ule.demo.common.constants.FunctionNo;
import com.ule.demo.common.dto.RetObj;
import com.ule.demo.common.entity.ScheduleJob;
import com.ule.demo.common.utils.SpringUtils;
import com.ule.demo.schedule.quartz.QuartzJobFactory;
import com.ule.demo.schedule.quartz.QuartzJobFactoryDCE;
import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务监控
 * @author fanxl
 * @date 10:48 2019/3/15
 * @return
 */
@Controller
public class TaskController {
    public final static Logger log = LoggerFactory.getLogger(TaskController.class);
    @Reference(timeout =2000)
    private IDealProcessorService dealProcessorService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public static ConcurrentHashMap<String,String> taskExcuteTime=new ConcurrentHashMap();
    /**
     * 查询所有的定时任务
     *
     * @param request
     * @return
     */
    @RequestMapping("/task/taskList")
    public ModelAndView taskList(HttpServletRequest request) {
        log.info("进入定时任务监控页面");
        Request httpRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_200001, null);
        Response rep = dealProcessorService.dispatchCommand(httpRequest);
        List<ScheduleJob> taskList = (List<ScheduleJob>) rep.getData();
        ModelAndView view = new ModelAndView();
        view.setViewName("/task/taskList");
        view.addObject("taskList", taskList);
        view.addObject("timemap", taskExcuteTime);
        return view;
    }


    /**
     * 添加一个定时任务
     *
     * @param scheduleJob
     * @return retObj
     */
    @RequestMapping("/task/add")
    @ResponseBody
    public String addTask(HttpServletRequest request, ScheduleJob scheduleJob) {
        RetObj retObj = new RetObj();
        retObj.setFlag(false);
        try {
            CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        } catch (Exception e) {
            retObj.setMsg("cron表达式有误，不能被解析！");
            return JSON.toJSONString(retObj);
        }

       Object obj = null;
        try {
            if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
                obj = SpringUtils.getBean(scheduleJob.getSpringId());
            } else {
                Class clazz = Class.forName(scheduleJob.getBeanClass());
                obj = clazz.newInstance();
            }
        } catch (Exception e) {
            log.error("---------->Exception:",e);
        }
        if (obj == null) {
            retObj.setMsg("未找到目标类！");
            return JSON.toJSONString(retObj);
        } else {
            Class clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(scheduleJob.getMethodName(), null);
            } catch (Exception e) {
                log.error("---------->Exception:",e);
            }
            if (method == null) {
                retObj.setMsg("未找到目标方法！");
                return JSON.toJSONString(retObj);
            }
        }

        try {
            Request httpCreRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_200002, scheduleJob);
            Response rep = dealProcessorService.dispatchCommand(httpCreRequest);
            if (Constants.CODE_SUCCESS.equals(rep.getCode())) {
                retObj.setFlag(true);
                return JSON.toJSONString(retObj);
            }else {
                retObj.setFlag(false);
                retObj.setMsg("保存失败，检查 name group 组合是否有重复！");
                return JSON.toJSONString(retObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            retObj.setFlag(false);
            retObj.setMsg("保存失败，检查 name group 组合是否有重复！");
            return JSON.toJSONString(retObj);
        }

    }

    /**
     * 开启/关闭一个定时任务
     * @param request
     * @param jobId
     * @param cmd
     * @return
     */
    @RequestMapping("/task/changeJobStatus")
    @ResponseBody
    public String changeJobStatus(HttpServletRequest request, Long jobId, String cmd) {
        RetObj retObj = new RetObj();
        retObj.setFlag(false);
        try {

            Map map=new HashMap();
            map.put("jobId",jobId);

            if ("stop".equals(cmd)) {
                map.put("jobStatus",ScheduleJob.STATUS_NOT_RUNNING);
            } else if ("start".equals(cmd)) {
                map.put("jobStatus",ScheduleJob.STATUS_RUNNING);
            }

            Request httpRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_200003, map);
            Response rep = dealProcessorService.dispatchCommand(httpRequest);
            if (Constants.CODE_SUCCESS.equals(rep.getCode())) {
                ScheduleJob job =(ScheduleJob)rep.getData();
                if ("stop".equals(cmd)) {
                    deleteJob(job);
                } else if ("start".equals(cmd)) {
                    addJob(job);
                }
                log.info("------------>ChangeStatus a job，JobName：{}",job.getJobName());
                retObj.setFlag(true);
                return JSON.toJSONString(retObj);
            } else {
                retObj.setMsg("任务状态改变失败！");
                return JSON.toJSONString(retObj);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            retObj.setMsg("任务状态改变失败！");
            return JSON.toJSONString(retObj);
        }
    }

    /**
     * 修改定时任务的执行时间间隔
     * @param request
     * @param jobId
     * @param cron
     * @return
     */
    @RequestMapping("/task/updateCron")
    @ResponseBody
    public String updateCron(HttpServletRequest request, Long jobId, String cron) {
        RetObj retObj = new RetObj();
        retObj.setFlag(false);
        try {
            CronScheduleBuilder.cronSchedule(cron);
        } catch (Exception e) {
            retObj.setMsg("cron表达式有误，不能被解析！");
            return JSON.toJSONString(retObj);
        }
        try {
            Map map = new HashMap<String, String>();
            map.put("jobId", jobId);
            map.put("cron", cron);
            Request httpRequest = new Request(Constants.SYS_MANAGER, FunctionNo.FUNCTIONID_200005, map);
            Response rep = dealProcessorService.dispatchCommand(httpRequest);
            if (Constants.CODE_SUCCESS.equals(rep.getCode())) {
                ScheduleJob job =(ScheduleJob)rep.getData();
                if (ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
                    updateJobCron(job);
                }
                retObj.setFlag(true);
                return JSON.toJSONString(retObj);
            } else {
                retObj.setMsg("cron更新失败！");
                return JSON.toJSONString(retObj);
            }
        } catch (Exception e) {
            retObj.setMsg("cron更新失败！");
            return JSON.toJSONString(retObj);
        }
    }

    /**
     * 立即执行一个定时任务
     * @param request
     * @param jobId
     * @return
     */
    @RequestMapping("/task/runAJobNow")
    @ResponseBody
    public String runAJobNow(HttpServletRequest request, Long jobId) {
        RetObj retObj = new RetObj();
        retObj.setFlag(false);
        try {
            Request httpRequest = new Request(Constants.SYS_MANAGER,FunctionNo.FUNCTIONID_200004, jobId);
            Response rep = dealProcessorService.dispatchCommand(httpRequest);
            if (Constants.CODE_SUCCESS.equals(rep.getCode())) {
                ScheduleJob job =(ScheduleJob)rep.getData();
                Scheduler scheduler = schedulerFactoryBean.getScheduler();
                JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
                scheduler.triggerJob(jobKey);
                log.info("------------>Just run a job，JobName：{}",job.getJobName());
                retObj.setFlag(true);
                return JSON.toJSONString(retObj);
            } else {
                retObj.setMsg("任务立即执行失败！");
                return JSON.toJSONString(retObj);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            retObj.setMsg("任务立即执行失败！");
            return JSON.toJSONString(retObj);
        }
    }

    /**
     * 添加任务
     *
     * @param
     * @throws SchedulerException
     */
    public void addJob(ScheduleJob job) throws SchedulerException {
        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        log.info("------------>add a job，JobName：{}",job.getJobName());

        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDCE.class;
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }


    /**
     * 删除一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
        log.info("------------>delete a job，JobName：{}",scheduleJob.getJobName());
    }

    /**
     * 更新job时间表达式
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
        log.info("------------>UpdateCron a job，JobName：{}",scheduleJob.getJobName());
    }

    @PostConstruct
    public void init() throws Exception {
        Request httpRequest = new Request(Constants.SYS_MANAGER, "200001", null);
        Response rep = dealProcessorService.dispatchCommand(httpRequest);
        List<ScheduleJob> taskList = (List<ScheduleJob>) rep.getData();

        for (ScheduleJob job : taskList) {
            addJob(job);
        }
    }
}