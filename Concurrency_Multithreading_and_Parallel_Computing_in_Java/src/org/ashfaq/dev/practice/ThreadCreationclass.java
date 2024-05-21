package org.ashfaq.dev.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

class X1 implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i <= 10; i++)
			System.out.println("Thread Runnable + " + i);

	}

}

class X2 extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i <= 10; i++)
			System.out.println("Thread + " + i);

	}
}

public class ThreadCreationclass {

	void add() {
		for (int i = 0; i <= 10; i++)
			System.out.println("Thread Lamba objects: " + i);

	}

	public static void main(String[] args) {
		ThreadCreationclass creationclass = new ThreadCreationclass();

		Thread t1 = new Thread(new X1());
		t1.setPriority(Thread.MAX_PRIORITY);

		Thread t2 = new X2();

		t2.setPriority(9);

		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i <= 100; i++)
					System.out.println("Thread anonymous: " + i);

			}
		});

		Thread t4 = new Thread(() -> {

			for (int i = 0; i <= 10; i++)
				System.out.println("Thread Lambasone: " + i);

		});

		Thread t5 = new Thread(creationclass::add);

		t5.setPriority(1);

		t1.start();

		t2.start();

		t3.start();
		t4.start();
		t5.start();

//		try {
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		System.out.println("All the threads are done");
	
	}
		
			
		public String getProxyID(String planld, Connection dbConnection) throws Exception {

			String proxyID= null;

			String qry= "select PROXY ID from prodready_json_data_pxd where plan_Id = ?  and Json_is_transformed=1 and record_type = 'Full'";

			try (PreparedStatement preparedStatement=dbConnection.prepareStatement(qry)) {

			preparedStatement.setString(1, planld);

			try (ResultSet resultSet = preparedStatement.executeQuery()){

			if (resultSet.next()) {

			proxyID = resultSet.getString("PROXY_ID");

			

			}} catch (SQLException e) {


			throw new Exception("Error retrieving data." + e.getMessage());
			}
			return proxyID;
			
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	}

}
