package com.github.dantebarba.queryfork;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.dantebarba.queryfork.core.AbstractQuery;
import com.github.dantebarba.queryfork.core.Builder;
import com.github.dantebarba.queryfork.core.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.Order;
import com.github.dantebarba.queryfork.core.QueryBuilder;
import com.github.dantebarba.queryfork.core.SubQuery;

public class QueryBuilderTest {

	@Before
	public void startUp() {
	}

	@Test
	public void testQueryCreating() {
		AbstractQuery<QueryBuilder> builder = new Builder().select("customer").from(
				"Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testWhere() {
		AbstractQuery<QueryBuilder> builder = new Builder().select("customer")
				.from("Customer customer").where("customer = 1");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testAnd() {
		AbstractQuery<QueryBuilder> builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testOr() {
		AbstractQuery<QueryBuilder> builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testCount() {
		AbstractQuery<QueryBuilder> builder = new Builder().count().from("Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testParameter() {

		AbstractQuery<QueryBuilder> builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500")
				.and("customer.surname =").parameter("surname", "Snow");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testSubQuery() {
		AbstractQuery<QueryBuilder> builder = new Builder()
				.select("customer")
				.from("Customer customer")
				.where("customer.id = 1")
				.and(new SubQuery("customer.name = John")
						.or("customer.salary > 500").build()).and("customer.surname =")
				.parameter("surname", "Snow");
		System.out.println(builder.getQuery());
		AbstractQuery<QueryBuilder> builder2 = new Builder()
				.select("customer")
				.from("Customer customer")
				.where("customer.id = 1")
				.and(new SubQuery("customer.name =")
						.parameter("customername", "John")
						.or("customer.salary > ")
						.parameter("customersalary", 500).and("customer.cuit")
						.in("cuitList", Arrays.asList(202020, 303030)).build())
				.and("customer.surname =").parameter("surname", "Snow");
		System.out.println(builder2.getQuery());

	}

	@Test
	public void testIn() {
		AbstractQuery<QueryBuilder>  builder = new Builder().select("customer")
				.from("Customer customer").where("customer.id = 1")
				.and("customer.name = John").or("customer.salary > 500")
				.and("customer.surname =").parameter("surname", "Snow")
				.and("customer.department")
				.in("department", Arrays.asList(1, 2, 3));
		System.out.print(builder.getQuery());
	}
	
	@Test
	public void testAdapter() {
		QueryBuilder query = new Builder().select("c").from("Customer c").where("1=1").build();
		DatabaseAdapter adapter = query.getAdapter();
	}
	
	@Test
	public void testOrder() {
		AbstractQuery<QueryBuilder> builder = new Builder().select("customer").from(
				"Customer customer").orderBy("customer.id");
		System.out.print(builder.getQuery());
		AbstractQuery<QueryBuilder> builder2 = new Builder().select("customer").from(
				"Customer customer").orderBy("customer.id", Order.ASC);
		System.out.print(builder2.getQuery());
	}

}
