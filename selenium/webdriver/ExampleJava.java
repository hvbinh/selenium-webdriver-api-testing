package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ExampleJava {

	public static void main(String[] args) {
		List<String> listDuplicate = new ArrayList<String>();
		listDuplicate.add("JAVA");
		listDuplicate.add("J2EE");
		listDuplicate.add("SERVLETS");
		listDuplicate.add("JAVA");
		listDuplicate.add("J2EE");
		
		removeDuplicate(listDuplicate);

	}

	public static void removeDuplicate(List<String> list) {
		List<String> withoutDuplicate = list.stream().distinct().collect(Collectors.toList());
		System.out.println(withoutDuplicate);
	}

}
