package demoschool.ITSchool.services;

import org.springframework.stereotype.Service;

import demoschool.ITSchool.data.Person;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirstProblemServices {
	public String nameAndAge(Person person) {
		String result = person.getName() + " " + String.valueOf(person.getAge());
		System.out.print(result);
		return result;
	}

}
