package org.ashfaq.dev.parallelcomputing;

class ParallelWorker extends Thread {

	private int[] nums;
	private int low;
	private int high;
	private int partialSum;

	public ParallelWorker(int[] nums, int low, int high) {
		this.nums = nums;
		this.low = low;
		this.high = Math.min(high, nums.length);
	}

	public int getPartialSum() {
		return partialSum;
	}

	@Override
	public void run() {

		partialSum = 0;

		for (int i = low; i < high; i++) {
			partialSum += nums[i];
		}
	}
}

public class Sum_Problem_Parallel {

	private ParallelWorker[] workers;

	int numofthreads;

	public Sum_Problem_Parallel(int numofthreads) {
		this.numofthreads = numofthreads;
		this.workers = new ParallelWorker[numofthreads]; // number of workers;
	}

	public int getSum(int[] nums) {

		int size = (int) Math.ceil(nums.length * 1.0 / numofthreads);

		for (int i = 0; i < numofthreads; i++) {
			workers[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
//			workers[i].start();
			workers[i].start();
		}

		try {

			for (ParallelWorker parallel_Worker : this.workers) {
				parallel_Worker.join();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		int total = 0;

		for (ParallelWorker parallel_Worker : this.workers) {
			total += parallel_Worker.getPartialSum();
		}

		return total;

	}

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Sum_Problem_Parallel sum_Problem_Parallel = new Sum_Problem_Parallel(3);
		System.out.println(sum_Problem_Parallel.getSum(nums));
	}

}
