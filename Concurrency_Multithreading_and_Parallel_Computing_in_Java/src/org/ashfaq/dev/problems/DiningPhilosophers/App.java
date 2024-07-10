package org.ashfaq.dev.problems.DiningPhilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = null;
		Philosopher[] philosophers = null;
		
		try{
			
			philosophers = new Philosopher[Constants.NUM_PHILOSOPHERS];
			Chopsticks[] chopSticks = new Chopsticks[Constants.NUM_PHILOSOPHERS];
			
			for(int i=0;i<Constants.NUM_CHOPSTICKS;i++){
				chopSticks[i] = new Chopsticks(i);
			}
			
			executorService = Executors.newFixedThreadPool(Constants.NUM_PHILOSOPHERS);
			
			for(int i=0;i<Constants.NUM_PHILOSOPHERS;i++){
				philosophers[i] = new Philosopher(i, chopSticks[i], chopSticks[(i+1) % Constants.NUM_PHILOSOPHERS]);
				executorService.execute(philosophers[i]);
			}
			
			Thread.sleep(Constants.Simulation_Running_Time);
			
			for(Philosopher philosopher : philosophers){
				philosopher.setFull(true);
			}		
		}finally{
			
			executorService.shutdown();
			
			while(!executorService.isTerminated()){
				Thread.sleep(1000);
			}
			
			for(Philosopher philosopher : philosophers ){
				System.out.println(philosopher+" eat #"+philosopher.getEatingCounter());
			}
			
		}
		
	}
}

