package com.ule.demo.schedule.tasks;


import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorld {

    public static void execute() {

        System.out.println("=========Hello" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }

}
