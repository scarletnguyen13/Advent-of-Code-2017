/*
	 	--- Day 8: I Heard You Like Registers ---
	
	You receive a signal directly from the CPU. Because of your recent assistance with jump instructions, it would like you to compute the result of a series of unusual register instructions.
	
	Each instruction consists of several parts: the register to modify, whether to increase or decrease that register's value, the amount by which to increase or decrease it, and a condition. If the condition fails, skip the instruction without modifying the register. The registers all start at 0. The instructions look like this:
	
	b inc 5 if a > 1
	a inc 1 if b < 5
	c dec -10 if a >= 1
	c inc -20 if c == 10
	These instructions would be processed as follows:
	
	Because a starts at 0, it is not greater than 1, and so b is not modified.
	a is increased by 1 (to 1) because b is less than 5 (it is 0).
	c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
	c is increased by -20 (to -10) because c is equal to 10.
	After this process, the largest value in any register is 1.
	
	You might also encounter <= (less than or equal to) or != (not equal to). However, the CPU doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you to determine.
	
	What is the largest value in any register after completing the instructions in your puzzle input?
	
	Your puzzle answer was 4163.
	
	--- Part Two ---
	
	To be safe, the CPU also needs to know the highest value held in any register during this process so that it can decide how much memory to allocate to these operations. For example, in the above instructions, the highest value ever held was 10 (in register c after the third instruction was evaluated).
	
	Your puzzle answer was 5347.
	
	Both parts of this puzzle are complete! They provide two gold stars: **
 */

import java.util.*;
import java.io.*;

public class Day_8 {
	public static void main(String[] args) throws FileNotFoundException {
		List<String[]> list = new ArrayList<String[]>();
		Map<String,Integer> registerStorage = new HashMap<String,Integer>();
		List<Integer> largestValues = new ArrayList<Integer>();
		Scanner console = new Scanner(new File("day8input.txt"));
		while (console.hasNextLine()) {
			String[] line = console.nextLine().split(" "); 
			registerStorage.put(line[0], 0);
			registerStorage.put(line[4], 0);
			list.add(line);
		}
		for (String[] a : list) {
			switch(a[5]) {
			case "<":
				if (registerStorage.get(a[4]) < Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			case ">":
				if (registerStorage.get(a[4]) > Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			case "==":
				if (registerStorage.get(a[4]) == Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			case "<=":
				if (registerStorage.get(a[4]) <= Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			case ">=":
				if (registerStorage.get(a[4]) >= Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			case "!=":
				if (registerStorage.get(a[4]) != Integer.parseInt(a[6])) {
					if (a[1].equals("dec")) {
						registerStorage.put(a[0], registerStorage.get(a[0]) - Integer.parseInt(a[2]));
					} else {
						registerStorage.put(a[0], registerStorage.get(a[0]) + Integer.parseInt(a[2]));
					}
					largestValues.add(registerStorage.get(a[0]));
				}
				break;
			}	
		}
		
		int largest = Integer.MIN_VALUE;
		for (int a : registerStorage.values()) {
			largest = a > largest ? a : largest;	
		}
		System.out.println("Part 1: " + largest);
		
		largest = Integer.MIN_VALUE;
		for (int a : largestValues) {
			largest = a > largest ? a : largest;	
		}
		System.out.println("Part 2: " + largest);
	}
}
