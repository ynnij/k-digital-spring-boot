package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

class Person {
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}

@RestController // 실행하자마자 생성자 호출하도록 만드는 어노테이션
public class BoardController {
	
	public BoardController() { // boot가 자동으로 생성자 호출 
		System.out.println("===> BoardController 생성");
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "hello : "+name; // localhost:8080/hello?name=이름
	}
	
	@GetMapping("/hello1")
	public String hello1(String name, Integer age) { // 객체 타입으로 입력 
		return "hello : "+name+"("+age+")"; 
	}
	
	@GetMapping("/person")
	public String person(Person person) { // localhost:8080/person?name=홍길동&age=10 -> 자동으로 객체 생성
		return person.toString(); 
	}
	
	@GetMapping("/jsonperson")
	public String jsonperson(@RequestBody Person person) { // body 안에 있는 json 데이터를 쓰겠다
		return person.toString()+" with JSON"; 
	}
	@PostMapping("/login")
	public String login(String id, String pwd) {
		return id+","+pwd;
	}
}
