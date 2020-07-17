package com.laz.knowledge.eighteen;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class Main {
	@Test
	public void test1() {
		List<String> strings = Arrays.asList("Hollis", "", "HollisChuang", "H", "hollis");
		Stream<String> stream = strings.stream();
		stream.filter(string -> !string.isEmpty()).forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		numbers.stream().map( i -> i*i).forEach(System.out::println);
		//9,4,4,9,49,9,25
	}
	
	@Test
	public void test3() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		numbers.stream().distinct().forEach(System.out::println);
		//3,2,7,5
	}
	@Test
	public void test4() {
		List<String> strings = Arrays.asList("Hollis", "HollisChuang", "hollis", "Hello", "HelloWorld", "Hollis");
		Stream s = strings.stream().filter(string -> string.length()<= 6).map(String::length).sorted().limit(3)
		            .distinct();
		s.forEach(x->System.out.println(x));
	}
}
