package org.ashfaq.dev.StreamAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParallelSaveOperation {

	public static final String DIRECTORY = System.getProperty("user.dir") + "/test/";

	public static void save(Person n) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(new File(DIRECTORY + n.getId() + ".txt"))) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// create directory

		try {
			Files.createDirectories(Paths.get(DIRECTORY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ParallelSaveOperation ps = new ParallelSaveOperation();

		// create a large number of list objects

		List<Person> personList = ps.generate(100000);

		// save them in sequentially
		long timeMillis = System.currentTimeMillis();

		personList.stream().forEach(ParallelSaveOperation::save);

		System.out.println("Time taken: " + (System.currentTimeMillis() - timeMillis));

//		Time taken: 10237

		// save them in parallel
		timeMillis = System.currentTimeMillis();
		personList.parallelStream().forEach(ParallelSaveOperation::save);
		System.out.println("Time taken: " + (System.currentTimeMillis() - timeMillis));

//		Time taken: 5704
	}

	private List<Person> generate(int i) {
		return Stream.iterate(0, n -> n + 1).limit(i).map(x -> {

			Person person = new Person(x);
			return person;
		}).toList();
	}

}
