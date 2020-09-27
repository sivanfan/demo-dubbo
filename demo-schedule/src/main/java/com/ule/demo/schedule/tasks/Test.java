package com.ule.demo.schedule.tasks;


import com.ule.demo.api.req.Request;
import com.ule.demo.common.constants.Constants;
import com.ule.demo.common.utils.DateUtil;
import com.ule.demo.schedule.controller.TaskController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public void test() {
        TaskController.taskExcuteTime.put("hello", DateUtil.format(new Date(),DateUtil.DATE_TIME_FORMAT));
        Request httpRequest = new Request(Constants.SYS_MANAGER, "200001", null);
        //Response rep = HttpAccessUtil.httpPostSerialObject(Config.getValueByKey("serviceUrl"), 5 * 1000, 5 * 1000, httpRequest);
        System.out.println("=========Test" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //System.out.println("=========Test" + ((List) rep.getData()).size());
    }
}
