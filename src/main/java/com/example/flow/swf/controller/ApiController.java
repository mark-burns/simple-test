package com.example.flow.swf.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping("/hello/{name}")
	@ResponseBody
	public String helloEndpoint(@PathVariable("name") String name) {

		return "Hello " + name;
	}

}
