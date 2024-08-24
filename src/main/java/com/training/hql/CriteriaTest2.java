package com.training.hql;

import java.util.List;

import org.hibernate.Session;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaTest2 {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<BookPOJO> criteriaQuery = criteriaBuilder.createQuery(BookPOJO.class);

		// Define the root entity
		Root<BookPOJO> bookRoot = criteriaQuery.from(BookPOJO.class);

		// Add your criteria here
//		criteriaQuery.where(criteriaBuilder.equal(bookRoot.get("price"), 10));
//		criteriaQuery.where(criteriaBuilder.greaterThan(bookRoot.get("price"), 10));
		criteriaQuery.where(criteriaBuilder.lessThan(bookRoot.get("price"), 10));

		List<BookPOJO> books = session.createQuery(criteriaQuery).getResultList();
		if (books.size() == 0) {
			System.out.println("No books available!");
		} else {
			for (BookPOJO book : books) {
				System.out.print(book.getBookId());
				System.out.print(" | " + book.getBookName());
				System.out.print(" | " + book.getPublisher().getPubName());
				System.out.println(" | " + book.getPrice());
			}
		}
		
		session.close();
		System.exit(0);
	}
}
