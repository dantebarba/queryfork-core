package com.github.dantebarba.queryfork;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.github.dantebarba.queryfork.core.adapters.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.builders.AbstractQuery;
import com.github.dantebarba.queryfork.core.builders.QueryBuilder;
import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.concrete.Order;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.phases.concrete.SubQuery;
import com.github.dantebarba.queryfork.core.phases.concrete.Where;
import com.github.dantebarba.queryfork.core.queries.Query;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class QueryBuilderTest {

	@Before
	public void startUp() {
	}

	@Test
	public void testQueryCreating() {
		Where<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testWhere() {
		IsQuery builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer = 1").build()).build();
		System.out.print(builder.getQuery());
	}

	@Test
	public void testAnd() {
		Order<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1").and("customer.name = John").build());
		System.out.print(builder.getQuery());
	}

	@Test
	public void testOr() {
		Order<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1").and("customer.name = John").or("customer.salary > 500").build());
		System.out.print(builder.getQuery());
	}

	@Test
	public void testCount() {
		Where<IsQuery> builder = new QueryBuilder().count("*").from("Customer customer");
		System.out.print(builder.getQuery());
	}

	@Test
	public void testParameter() {

		Order<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1").and("customer.name = John").or("customer.salary > 500")
						.and("customer.surname =").parameter("surname", "Snow").build());
		System.out.print(builder.getQuery());
	}

	@Test
	public void testSubQuery() {
		Order<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1")
						.and(new SubQuery("customer.name = John").or("customer.salary > 500").build())
						.and("customer.surname =").parameter("surname", "Snow").build());
		System.out.println(builder.getQuery());
		Order<IsQuery> builder2 = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1")
						.and(new SubQuery("customer.name =").parameter("customername", "John").or("customer.salary > ")
								.parameter("customersalary", 500).and("customer.cuit")
								.in("cuitList", Arrays.asList(202020, 303030)).build())
						.and("customer.surname =").parameter("surname", "Snow").build());
		System.out.println(builder2.getQuery());

	}

	@Test
	public void testIn() {
		Order<IsQuery> builder = new QueryBuilder().select("customer").from("Customer customer")
				.where(new SubQuery("customer.id = 1").and("customer.name = John").or("customer.salary > 500")
						.and("customer.surname =").parameter("surname", "Snow").and("customer.department")
						.in("department", Arrays.asList(1, 2, 3)).build());
		System.out.print(builder.getQuery());
	}

	@Test
	public void testAdapter() {
		Query query = (Query) new QueryBuilder().select("c").from("Customer c").build();
		DatabaseAdapter adapter = query.getAdapter();
	}

	public class CustomQuery implements IsQuery {

		private HQLString textualQuery = new HQLString();

		@Override
		public HQLString getQuery() {
			return this.textualQuery;
		}

		@Override
		public CustomQuery paginate(Paginator paginator) {
			return null;
		}

		@Override
		public CustomQuery createQuery(HQLString query2) {
			this.textualQuery = query2;
			return this;
		}

		@Override
		public DatabaseAdapter getAdapter() {
			return null;
		}

		@Override
		public CustomQuery parameters(Parameter params) {
			if (params.getParameter("testParam") != null)
				System.out.println("The TEST Param has been found!!!");
			return this;
		}

	}

	public class CustomQueryBuilder extends AbstractQuery<CustomQuery> {

		CustomQuery instance = new CustomQuery();

		@Override
		public CustomQuery build() {
			instance.createQuery(this.getQuery());
			instance.parameters(this.getParameters());
			return instance;
		}

		@Override
		public IsQuery getBuildedQuery() {
			return instance;
		}

	}

	@Test
	public void testCustomAdapter() {
		IsQuery query = new CustomQueryBuilder().select().from("Customer customer")
				.where(new SubQuery("customer.age").eq().parameter("testParam", "test").build()).build();
	}

}
