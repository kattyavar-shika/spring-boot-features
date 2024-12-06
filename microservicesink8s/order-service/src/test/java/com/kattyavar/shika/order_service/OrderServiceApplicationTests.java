package com.kattyavar.shika.order_service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderServiceApplicationTests {

	@BeforeAll
	public static void beforeAll(){
		System.out.println("Before All");
	}

	@BeforeEach
	public void beforeEach(){
		System.out.println("In before each method ...");
	}

	@AfterEach
	public void afterEach(){
		System.out.println("After each method execution...");
	}

	@Test
	void contextLoads() {

		System.out.println("In test case ...");
		Assertions.assertEquals("OK", "OK");
		//Assertions.assertNotNull();
		//Assertions.assertFalse(true);
	}

	@Test
	void contextLoadsSecondTestCase() {

		System.out.println("In Second test case ...");
		Assertions.assertEquals("OK", "OK");
		//Assertions.assertNotNull();
		//Assertions.assertFalse(true);
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("After all method...");

	}

}
