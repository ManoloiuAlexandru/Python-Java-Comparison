package demoschool.ITSchool.services;

import org.springframework.stereotype.Service;

import demoschool.ITSchool.data.Person;
import lombok.RequiredArgsConstructor;
/*
 * Given a name and the age of a person show on screen: 'name' has age:'age'.
 * @author Manoloiu Ionut-Alexandru
 */
@Service
@RequiredArgsConstructor
public class FirstProblemServices {
	/*
	 * This method is used to return the string that is formed from the name and the age
	 * @param person an object of type Person the contains the name and the age
	 * @return String This returns the 'name' has age:'age'
	 */
	public String nameAndAge(Person person) {
		String result = person.getName() + " has age: " + String.valueOf(person.getAge());
		System.out.println(result);
		return result;
	}

}