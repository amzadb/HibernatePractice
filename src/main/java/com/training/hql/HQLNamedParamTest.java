package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class HQLNamedParamTest {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("from BookPOJO where bookName like :name");
		NativeQuery<BookPOJO> query = session.createNativeQuery("Select * from Book where book_Name like :name",
				BookPOJO.class);
		query.setParameter("name", "%Java%");
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
