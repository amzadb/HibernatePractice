package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class HQLFromTest {

	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();

//		Query query = session.createQuery("from BookPOJO");
//		List<BookPOJO> books = query.list();

		// Replace session.createQuery with session.createNativeQuery
		NativeQuery<BookPOJO> query = session.createNativeQuery("SELECT * FROM Book", BookPOJO.class);
		List<BookPOJO> books = query.getResultList();

		for (BookPOJO book : books) {
			System.out.print(book.getBookId());
			System.out.print(" | " + book.getBookName());
			System.out.println(" | " + book.getPublisher().getPubName());
		}
		session.close();
		System.exit(0);
	}
}
