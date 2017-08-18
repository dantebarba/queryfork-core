package com.github.dantebarba.queryfork;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.dantebarba.queryfork.core.AbstractQuery;
import com.github.dantebarba.queryfork.core.Builder;
import com.github.dantebarba.queryfork.core.QueryBuilder;
import com.github.dantebarba.queryfork.core.SubQuery;

public class QueryBuilderTest {

	@Before
	public void startUp() {
	}

	@Test
	public void testQueryCreating() {
		AbstractQuery builder = new Builder().select("customer").from(
				"Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testWhere() {
		AbstractQuery builder = new Builder().select("customer")
				.from("Customer customer").where("customer = 1");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testAnd() {
		AbstractQuery builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testOr() {
		AbstractQuery builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testCount() {
		Builder builder = new Builder().count().from("Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testParameter() {

		AbstractQuery builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500")
				.and("customer.surname =").parameter("surname", "Snow");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testSubQuery() {
		AbstractQuery builder = new Builder()
				.select("customer")
				.from("Customer customer")
				.where("customer.id = 1")
				.and(new SubQuery("customer.name = John")
						.or("customer.salary > 500")).and("customer.surname =")
				.parameter("surname", "Snow");
		System.out.println(builder.getQuery());
		AbstractQuery builder2 = new Builder()
				.select("customer")
				.from("Customer customer")
				.where("customer.id = 1")
				.and(new SubQuery("customer.name =")
						.parameter("customername", "John")
						.or("customer.salary > ")
						.parameter("customersalary", 500).and("customer.cuit")
						.in("cuitList", Arrays.asList(202020, 303030)))
				.and("customer.surname =").parameter("surname", "Snow");
		System.out.println(builder2.getQuery());

	}

	@Test
	public void testIn() {
		AbstractQuery builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500")
				.and("customer.surname =").parameter("surname", "Snow")
				.and("customer.department")
				.in("department", Arrays.asList(1, 2, 3));
		System.out.print(builder.getQuery());
	}

}
