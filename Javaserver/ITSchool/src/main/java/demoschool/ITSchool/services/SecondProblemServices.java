package demoschool.ITSchool.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import demoschool.ITSchool.data.Triangle;
import lombok.RequiredArgsConstructor;

/*
 * Calculate the area and perimeter of a right triangle that has the 2 sides given.
 * @author Manoloiu Ionut-Alexandru
 */
@Service
@RequiredArgsConstructor
public class SecondProblemServices {
	/*
	 * This method is used to return the string that is formed from the Perimeter and the area
	 * @param triangle an object of type Triangle the contains side1 and side2
	 * @return String This returns Perimeter:'perimeter' and area:'area'
	 */
	public ArrayList<Integer> getPeremAndArea(Triangle triangle) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int side3 = (int) Math.sqrt((int) Math.pow(triangle.getSide1(), 2) + (int) Math.pow(triangle.getSide2(), 2));
		int perimeter = triangle.getSide1() + triangle.getSide2() + side3;
		int area = triangle.getSide1() * triangle.getSide2() / 2;
		result.add(perimeter);
		result.add(area);
		System.out.println("Perimeter:"+ perimeter+ " and area:"+ area);
		return result;
	}
}
