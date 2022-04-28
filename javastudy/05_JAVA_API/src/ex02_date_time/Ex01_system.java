package ex02_date_time;

public class Ex01_system {

	public static void main(String[] args) {

		// 1. timestamp
		//	1970-01-01 0:00부터 1/1000초마다 증가하고 있는 long타입의 정수 값
		
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		
		// 2. nanotime     (nano :10억분의 1)
		// 	1) timestamp같은 기준점은 없다.
		//  2) 1/1,000,000,000초 단위의 long 타입의 정수 값
		//  3) 경과 시간을 계산하는 용도이다. 끝냈을 때의 시간 - 시작했을 때의 시간. 어느 정도 시간이 걸렸다.
		//  4) 성능 테스트, 벤치마크 테스트
		
		
		long begin = System.nanoTime();
		System.out.println("안녕하세요."); // 이 줄 수행한 작업(계산하고 메모리에 저장)하는 시간을 재는 것임.
		long end = System.nanoTime();
		System.out.println((end - begin) + "ns");
		
		
		
		
		
		
		
	}

}
