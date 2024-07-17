package org.ashfaq.dev.StreamAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FilesWithStream {

	public static void main(String[] args) {

		String path = "src/org/ashfaq/dev/StreamAPI/Names.txt";

		try {
			Stream<String> lines = Files.lines(Paths.get(path));
//			lines.forEach(System.out::println);
			List<String> stringList = lines.toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
