package ex01.main;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerMain {

	public static void main(String[] args) {
		
		try {
		
			// Scheduler 생성
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			// Scheduler 인스턴스가 처리할 Job 생성
			JobDetail job = JobBuilder.newJob(HelloJob.class) // 클래스 전달이 필요할 때 .class를 사용
				.withIdentity("job1", "group1") // job 이름, group 이름 개발자가 정하면 된다
				.build();
			
			// Scheduler 인스턴스가 동작할 시점 생성(SimpleScheduleBuilder)
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1") // 이름 trigger1(개발자가 정함), 그룹 group1은 job의 그룹과 맞추기
					.startNow() // 지금부터 시작하겠다
					.withSchedule(SimpleScheduleBuilder.simpleSchedule() // simpleSchedule을 만들겠다
							.withIntervalInSeconds(5) // 5초 마다 동작한다
							.repeatForever())   // 평생
					.build(); // builder 패턴의 마지막은 build()
			
			// Scheduler 인스턴스에 동작할 Job과 동작할 시점 Trigger 알려주기
			scheduler.scheduleJob(job, trigger);
			
			// Scheduler 인스턴스 동작 시작
			scheduler.start();
			
			// 30초 sleep 일시중지
			// main 쓰레드가 30초 일시중지하는 동안
			// scheduler가 계속 동작한다(scheduler는 별도의 스레드이다.
			Thread.sleep(30000); // 30000 밀리초 = 30초
			
			// Scheduler 인스턴스 동작 중지
			scheduler.shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
