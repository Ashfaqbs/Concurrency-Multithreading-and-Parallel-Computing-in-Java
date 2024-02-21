package org.ashfaq.dev;

public class Sequential_Processing {

	public static void main(String[] args) {

		System.out.println("OP 1");
		System.out.println("OP 2");
		System.out.println("OP 3");

		try {
			downloadImage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		showImage();
	}

	private static void showImage() {
		// TODO Auto-generated method stub
		System.out.println("Showing Image");

	}

	private static void downloadImage() throws InterruptedException {
		// TODO Auto-generated method stub\
		Thread.sleep(2000);
		System.out.println("Downloading Image");

	}

}
