 
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	/**
	 * @author daniel
	 * @time 2016-4-22 下午3:49:51
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 任务时间
		 * 
		 * @author daniel
		 * @email 576699909@qq.com
		 * @time 2016-4-22 下午3:56:28
		 */
		class ActiveTime implements Comparable<ActiveTime> {
			private int begin;
			private int end;

			public int getBegin() {
				return begin;
			}

			public void setBegin(int begin) {
				this.begin = begin;
			}

			public int getEnd() {
				return end;
			}

			public void setEnd(int end) {
				this.end = end;
			}

			/**
			 * 按照结束时间从小到大排序
			 */
			@Override
			public int compareTo(ActiveTime o) {

				return this.end - o.end;
			}

		}

		Scanner input = new java.util.Scanner(System.in);
		int count = input.nextInt();

		List<ActiveTime> list = new ArrayList<ActiveTime>();
		for (int i = 0; i < count; i++) {

			int activeCount = input.nextInt();
			for (int j = 0; j < activeCount; j++) {
				ActiveTime obj = new ActiveTime();
				obj.setBegin(input.nextInt());
				obj.setEnd(input.nextInt());
				list.add(obj);
			}
            //按照活动结束时间升序
			Collections.sort(list);
			int current = -1;
			int activeSum = 0;
			for (ActiveTime obj : list) {

				//最早结束的活动的开始时间
				if (obj.getBegin() > current) {
					activeSum++;
					//结束时间赋值到当前时间
					current = obj.getEnd();
				}
			}
			System.out.println("" + activeSum);

		}

	}
}

