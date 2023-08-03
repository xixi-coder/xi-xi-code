//package com.example.xixi.distribute.delayJob;
//
///**
// * @author : xi-xi
// */
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//public class MyJob implements Job {
//    @Override
//    public void execute(JobExecutionContext context)
//            throws JobExecutionException {
//        System.out.println("要去数据库扫描啦。。。");
//    }
//
//    public static void main(String[] args) throws Exception {
//        // 创建任务
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
//                .withIdentity("job1", "group1").build();
//        // 创建触发器 每3秒钟执行一次
//        Trigger trigger = TriggerBuilder
//                .newTrigger()
//                .withIdentity("trigger1", "group3")
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule()
//                                .withIntervalInSeconds(3).repeatForever())
//                .build();
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        // 将任务及其触发器放入调度器
//        scheduler.scheduleJob(jobDetail, trigger);
//        // 调度器开始调度任务
//        scheduler.start();
//    }
//}
