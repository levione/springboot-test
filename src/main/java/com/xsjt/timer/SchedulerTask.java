/**  
 * ---------------------------------------------------------------------------
 * Copyright (c) 2018, xsjt- All Rights Reserved.
 * Project Name:spring-boot-learn  
 * File Name:SchedulerTask.java  
 * Package Name:com.xsjt.timer
 * 
 * Date:2018年4月22日下午5:48:34
 * ---------------------------------------------------------------------------  
*/  
  
package com.xsjt.timer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**  
 * ClassName:SchedulerTask 
 * 定时任务
 * Date:     2018年4月22日 下午5:48:34
 * @author   
 * @version    
 * @since    JDK 1.8
 */
//@Component
public class SchedulerTask {

	/**
	 * executeTask:(每5秒中执行一次).  
	 * @author 
	 * Date:2018年4月22日下午5:51:34
	 */
	@Scheduled(cron = "0/5 * * * * *")
	public void executeTask() {
		System.out.println("--executeTask方法，每隔5秒执行一次，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	/**
	 * executeTask2
	 * 在上一次执行完之后等待xxx毫秒(xxx就是fixedDelay = 5000中的5000)再执行，循环下去 ，
	 * 上一次执行多久都没关系 ，反正上一次执行完后xxx毫秒我才执行 
	 * @author 
	 * Date:2018年4月22日下午7:03:11
	 */
	@Scheduled(fixedDelay = 5000)
	public void executeTask2() {
		System.out.println("**executeTask2方法， 执行一次，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	/** 
     * fixedRate 举个例子：比如：假设有5个执行时间点 间隔是5000毫秒：分别是： 
     * T1:14:00:00 
     * T2:14:00:05 
     * T3:14:00:10 
     * T4:14:00:15 
     * T5:14:00:20 
     * 如果T1执行时间花了4秒，也就是到了14:00:04,那么你会看到14:00:05分就开始执行了T2,很正常，此时T1结束时间和T2开始时间只差1000毫秒，没毛病 
     * 如果T1执行时间花了8秒，怎么办？这时T1执行完的时间是14:00:08，已经覆盖了T2的时间，T2在14:00:05到14:00:08是等等状态。现在是14:00:08,看起来接着是T3执行， 
     * 但实际不是，而是立马执行T2，立马执行T2，立马执行T2（T2说:我不管，T1搞我超时了，我无论也是执行），这时你会发现T2的执行时间（也就是第2次执行时间 ）是：14:00:08，真的是立马。。。 
     * 如此类推，只要时执行时间被覆盖了的，到它了就立马执行 
     */  
	@Scheduled(fixedRate = 5000)
    public void executeTask3() {
        System.out.println(">>executeTask3方法， 一次，当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
	}
}