package com.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class RunJob3 {
	public static void main(String[] args) {

		try {
			// 得到默认的调度器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// 定义当前调度器的具体作业对象
			JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();

			// 作业的触发器
			// 和之前的 SimpleTrigger 类似，现在的 CronTrigger 也是一个接口，通过 Tribuilder 的
			// build()方法来实例化
			// 在任务调度器中，使用任务调度器的 CronScheduleBuilder 来生成一个具体的 CronTrigger 对象
			CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withSchedule(
							CronScheduleBuilder
									.cronSchedule("0 * 14 * * ? *")).build();
			// 注册作业和触发器
			scheduler.scheduleJob(jobDetail, cronTrigger);

			// 开始调度任务
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
