package com.training.hql;

import java.util.List;

import org.hibernate.Session;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaTest4 {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<BookPOJO> book = cq.from(BookPOJO.class);

		// Select specific fields and apply grouping
		cq.multiselect(cb.avg(book.get("price")).alias("avgPrice"), 
				cb.count(book.get("bookId")).alias("bookCount"),
				book.get("publisher").get("pubName").alias("publisherName"))
				.groupBy(book.get("publisher").get("pubName"));

		// Execute the query
		List<Tuple> results = session.createQuery(cq).getResultList();

		// Iterate over the results
		for (Tuple tuple : results) {
			Double avgPrice = tuple.get("avgPrice", Double.class);
			Long bookCount = tuple.get("bookCount", Long.class);
			String publisherName = tuple.get("publisherName", String.class);

			System.out.println(
					"Publisher: " + publisherName + ", Average Price: " + avgPrice + ", Book Count: " + bookCount);
		}

		session.close();
		System.exit(0);
	}
}
