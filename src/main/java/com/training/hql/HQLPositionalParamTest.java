package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class HQLPositionalParamTest {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("from BookPOJO where bookName like ?1");
		NativeQuery<BookPOJO> query = session.createNativeQuery("Select * from Book where book_Name like ?1",
				BookPOJO.class);
		query.setParameter(1, "%Action%");
//		List<BookPOJO> books = query.list();
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
