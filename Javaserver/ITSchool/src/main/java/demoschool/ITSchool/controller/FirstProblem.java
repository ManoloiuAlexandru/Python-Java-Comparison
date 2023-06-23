package demoschool.ITSchool.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demoschool.ITSchool.data.Person;
import demoschool.ITSchool.services.FirstProblemServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/first")
public class FirstProblem {

	private final FirstProblemServices firstProblemServices;
	
	@PostMapping("/firstproblem")
	public String firstProblem(@RequestBody Person person) throws IOException {
		return firstProblemServices.nameAndAge(person);
	}
}
