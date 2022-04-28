package ex02.tomcat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 * Application Lifecycle
 * 07_BATCH 프로젝트가 생성(tomcat 서버에 프로젝트가 올라갔을 때)되어서 종료될 때까지
 * 
 *
 * ServletContextListener 인터페이스
 * 1. 웹 애플리케이션의 Lifecycle로 동작한다.
 * 2. 시작할 때 contextInitialized() 메소드가 동작한다.
 * 3. 종료될 때 contextDestroyed() 메소드가 동작한다.
 */


/*
 * Application Lifecycle Listener implementation class MyListener
 */
@WebListener // 나는 리스너이다.
public class MyListener implements ServletContextListener {

	// field
	private Scheduler scheduler;
	
    /**
     * Default constructor. (생성자)
     * 
     * 생성자에서 할 일
     * Scheduler scheduler 생성하기
     */
    public MyListener() {
    	try {  // scheduler는 예외처리 필요
    		scheduler = StdSchedulerFactory.getDefaultScheduler();    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
     * contextDestroyed()
     * 
     * 웹 애플리케이션이 종료될 때 Scheduler 인스턴스 종료
     * 
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
    		if(scheduler != null) { // nullPointerException 방지
    			scheduler.shutdown();    			
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }

	/**
     * contextInitialized()
     * 
     * 웹 애플리케이션이 시작할 때 Scheduler 인스턴스 시작(Job, Trigger)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
    		
    		// 1. Scheduler가 동작 시킬 Job 생성
    		JobDetail job = JobBuilder.newJob(MyJob.class) 
    				.withIdentity("job2", "group2")
    				.build();
    		
    		// 2. Scheduler 인스턴스가 동작할 시점 Trigger 생성
    		Trigger trigger = TriggerBuilder.newTrigger()
    			.withIdentity("trigger2", "group2")
    			.startNow()
    			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
    					.withIntervalInSeconds(10)
    					.repeatForever())
    			.build();
    		
    		// 3. Scheduler 인스턴스에 동작할 Job과 동작할 시점 Trigger 등록
    		scheduler.scheduleJob(job, trigger);
    		
    		// 4. Scheduler 인스턴스 동작 시작
    		// scheduler.start();
    			
    		
    		
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
}
