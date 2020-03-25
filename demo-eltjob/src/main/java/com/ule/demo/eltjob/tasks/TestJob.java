package com.ule.demo.eltjob.tasks;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ule.demo.api.IDealProcessorService;
import com.ule.demo.api.req.Request;
import com.ule.demo.api.resp.Response;
import com.ule.demo.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author fanxl
 * @version 1.0
 * @className TestJob
 * @description //TODO
 * @date 下午1:04  19-4-10
 **/
@Component
public class TestJob implements SimpleJob {
    private Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("------Thread ID: {}, 任务总片数: {}, 当前分片项: {}",
                Thread.currentThread().getId(),shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
