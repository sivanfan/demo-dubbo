package com.ule.demo.eltjob.exceptionhandler;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fanxl
 * @version 1.0
 * @className TestJobExceptionHandler
 * @description //TODO
 * @date 上午9:30  19-4-11
 **/
public class TestJobExceptionHandler implements JobExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(TestJobExceptionHandler.class);

    @Override
    public void handleException(String jobName, Throwable cause) {
        logger.error(String.format("任务[%s]调度异常", jobName), cause);
    }
}
