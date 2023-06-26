package demoschool.ITSchool.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import demoschool.ITSchool.data.Person;
import demoschool.ITSchool.data.Triangle;
import demoschool.ITSchool.services.FirstProblemServices;
import demoschool.ITSchool.services.SecondProblemServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class FirstSetOfProblemsController {

	private final FirstProblemServices firstProblemServices;
	private final SecondProblemServices secondProblemServices;
	private StringBuilder result;

	@PostMapping("/first/firstproblem")
	public StringBuilder firstProblem(@RequestBody Person person) throws IOException {
		result = new StringBuilder(firstProblemServices.nameAndAge(person));
		return result;
	}

	@PostMapping("/first/secondproblem")
	public StringBuilder firstProblem(@RequestBody Triangle triangle) throws IOException {
		result = new StringBuilder(secondProblemServices.getPeremAndArea(triangle).toString());
		return result;
	}

	@GetMapping("/result")
	public String printResult() {
		return new Gson().toJson(result);
	}
}