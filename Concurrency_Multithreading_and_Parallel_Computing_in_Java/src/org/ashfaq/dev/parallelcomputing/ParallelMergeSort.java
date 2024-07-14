package org.ashfaq.dev.parallelcomputing;

public class ParallelMergeSort {

	private int[] nums;

	private int[] temparray;

	private void parallelMergesort(int low, int high, int numOfThreads) {

		if (numOfThreads <= 1) {
			mergesort(low, high);
			return;
		}

		int middle = (low + high) / 2;

		Thread leftSorted = createThread(low, middle, numOfThreads);
		Thread rightSorted = createThread(middle + 1, high, numOfThreads);

		leftSorted.start();

		rightSorted.start();

		try {
			leftSorted.join();
			rightSorted.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		merge(low, middle, high);

	}

	public Thread createThread(int low, int high, int numOfThreads) {

		return new Thread() {
			@Override
			public void run() {

				parallelMergesort(low, high, numOfThreads / 2);
			}

		};
	}

	public ParallelMergeSort(int[] nums) {
		this.nums = nums;
		this.temparray = new int[nums.length];
		// TODO Auto-generated constructor stub
	}

	public void sort() {
		mergesort(0, nums.length - 1);
	}

	private void mergesort(int low, int high) {
		if (low >= high) {
			return;
		}

		int mid = (low + high) / 2;

		mergesort(low, mid);
		mergesort(mid + 1, high);

		merge(low, mid, high);
	}

	private void merge(int low, int mid, int high) {
		for (int i = low; i <= high; i++) {
			temparray[i] = nums[i];
		}

		int i = low;
		int j = mid + 1;
		int k = low;

		while (i <= mid && j <= high) {
			if (temparray[i] < temparray[j]) {
				nums[k] = temparray[i];
				i++;
			} else {
				nums[k] = temparray[j];
				j++;
			}
			k++;
		}

		while (i <= mid) {
			nums[k] = temparray[i];
			i++;
			k++;
		}

		while (j <= high) {
			nums[k] = temparray[j];
			j++;
			k++;
		}

	}

	public void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void showArray() {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}
	}

	public static void main(String[] args) {

		int[] arr = { 1, 5, 4, 3, 2, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		MergeSort ms = new MergeSort(arr);
		ms.sort();
		ms.showArray();

	}

}
