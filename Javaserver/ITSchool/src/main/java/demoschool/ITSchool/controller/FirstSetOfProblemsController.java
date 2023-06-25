package demoschool.ITSchool.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoschool.ITSchool.data.Person;
import demoschool.ITSchool.data.Triangle;
import demoschool.ITSchool.services.FirstProblemServices;
import demoschool.ITSchool.services.SecondProblemServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/first")
public class FirstSetOfProblemsController {

	private final FirstProblemServices firstProblemServices;
	private final SecondProblemServices secondProblemServices;

	@PostMapping("/firstproblem")
	public String firstProblem(@RequestBody Person person) throws IOException {
		return firstProblemServices.nameAndAge(person);
	}

	@PostMapping("/secondproblem")
	public ArrayList<Integer> firstProblem(@RequestBody Triangle triangle) throws IOException {
		return secondProblemServices.getPeremAndArea(triangle);
	}
}
