/*
	 	--- Day 3: Spiral Memory ---
	
	You come across an experimental new kind of memory stored on an infinite two-dimensional grid.
	
	Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:
	
	17  16  15  14  13
	18   5   4   3  12
	19   6   1   2  11
	20   7   8   9  10
	21  22  23---> ...
	While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
	
	For example:
	
	Data from square 1 is carried 0 steps, since it's at the access port.
	Data from square 12 is carried 3 steps, such as: down, left, left.
	Data from square 23 is carried only 2 steps: up twice.
	Data from square 1024 must be carried 31 steps.
	How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?
	
	Your puzzle answer was 419.
	
	--- Part Two ---
	
	As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1. Then, in the same allocation order as shown above, they store the sum of the values in all adjacent squares, including diagonals.
	
	So, the first few squares' values are chosen as follows:
	
	Square 1 starts with the value 1.
	Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
	Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
	Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4.
	Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
	Once a square is written, its value does not change. Therefore, the first few squares would receive the following values:
	
	147  142  133  122   59
	304    5    4    2   57
	330   10    1    1   54
	351   11   23   25   26
	362  747  806--->   ...
	What is the first value written that is larger than your puzzle input?
	
	Your puzzle answer was 295229.
	
	Both parts of this puzzle are complete! They provide two gold stars: **
 */

import java.util.*;
import java.awt.Point;
import java.io.*;

public class Day_3 {
	public static void main(String[] args) {
		int num = 10;
		//System.out.println(solvePart1(num));
		Map<Integer,Point> matrix = createMatrix(num*10);
		Map<Integer,Map<Point,Integer>> map = new TreeMap<Integer,Map<Point,Integer>>();
		Map<Point,Integer> col = new HashMap<Point,Integer>();
		
		for (int id : matrix.keySet()) {
			Map<Point,Integer> value = new HashMap<Point,Integer>();
			value.put(matrix.get(id), id == 1 ? 1 : 0);
			col.put(matrix.get(id), id == 1 ? 1 : 0);
			map.put(id, value);
		}
		
		for (int a = 2; a < num*10; a++) {
			Map<Point,Integer> value = new HashMap<Point,Integer>();
			Point point = matrix.get(a);
			int newVlue = sumFromNeighbor(point,col);
			value.put(matrix.get(a), newVlue);
			col.put(matrix.get(a), newVlue);
			map.put(a, value);
		}
		
		for (int a : map.keySet()) {
			System.out.println("Key: " + a + "\nValue: " + map.get(a));
		}		
	}
	
	public static int solvePart1(int num) {
		int square = 1, count = 0;
		while (num - Math.pow(square,2) > 0) { square += 2; count++; }
		int x = count;
		int y = x - ((int) (Math.pow(square,2) - num) % x);
		return y + x;
	}

	public static Map<Integer,Point> createMatrix(int num) {
		Map<Integer,Point> map = new TreeMap<Integer,Point>();
		int square = -1, count = 0;
		while (num - Math.pow(square,2) > 0) {
			square += 2; 
		
			Map<Integer,Integer> mapDir = new TreeMap<Integer,Integer>();
			for (int a = 3; a >= 0; a--) {
				mapDir.put(4-a, (int) (Math.pow(square,2) - a*2*count));
			}
			map.put(mapDir.get(1), new Point(count, count));
			map.put(mapDir.get(2), new Point(-count, count));
			map.put(mapDir.get(3), new Point(-count, -count));
			map.put(mapDir.get(4), new Point(count, -count));
			
			Map<Integer,Integer> mapQuar = new TreeMap<Integer,Integer>();
			for (int a = 0; a < 4; a++) {
				mapQuar.put(4-a, ((int) (Math.pow(square,2) - count)) - a*(2*count));
			}
			
			map.put(mapQuar.get(1), new Point(count, 0));
			map.put(mapQuar.get(2), new Point(0, count));
			map.put(mapQuar.get(3), new Point(-count, 0));
			map.put(mapQuar.get(4), new Point(0, -count));
			
			for (int a = mapQuar.get(1); a < mapDir.get(1); a++) {
				map.put(a + 1, new Point(count, a - mapQuar.get(1) + 1));
			}
			for (int a = mapDir.get(1); a < mapQuar.get(2); a++) {
				map.put(a + 1, new Point(mapQuar.get(2) - a - 1,count));
			}
			for (int a = mapQuar.get(2); a < mapDir.get(2); a++) {
				map.put(a + 1, new Point(mapQuar.get(2) - a - 1,count));
			}
			for (int a = mapDir.get(2); a < mapQuar.get(3); a++) {
				map.put(a + 1, new Point(-count, mapQuar.get(3) - a - 1));
			}
			for (int a = mapQuar.get(3); a < mapDir.get(3); a++) {
				map.put(a + 1, new Point(-count, -(a - mapQuar.get(3) + 1)));
			}	
			for (int a = mapDir.get(3); a < mapQuar.get(4); a++) {
				map.put(a + 1, new Point(- (mapQuar.get(4) - a - 1),-count));
			}
			for (int a = mapQuar.get(4); a < mapDir.get(4); a++) {
				map.put(a + 1, new Point(- (mapQuar.get(4) - a - 1),-count));
			}
			for (int a = mapDir.get(4) - count*8; a < mapQuar.get(1); a++) {
				map.put(a + 1, new Point(count, - (mapQuar.get(1) - a - 1)));
			}
			count++;
		}
		return map;
	}
	
	public static int sumFromNeighbor(Point p, Map<Point,Integer> col) {
		int result = 0;
		Point[] neighbors = {new Point(p.x+1,p.y), new Point(p.x+1, p.y+1),
							 new Point(p.x,p.y+1), new Point(p.x-1, p.y+1),
							 new Point(p.x-1,p.y), new Point(p.x-1, p.y-1),
							 new Point(p.x,p.y-1), new Point(p.x+1, p.y-1)}; 
		
		for (Point point : neighbors) {
			if (col.containsKey(point)) {
				result += col.get(point);
			}
		}
		return result;
	}
}