package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class BookPriceUpdate {
	public static void main(String[] args) {
		System.out.println("Books List BEFORE Update:");
		System.out.println("----------------------------------");
		showBooks();

		updateBooks();

		System.out.println("----------****************--------");
		System.out.println("Books List AFTER Update:");
		System.out.println("----------------------------------");
		showBooks();

		System.exit(0);
	}

	private static void updateBooks() {
		Session session = HQLUtil.getSessionFactory().openSession();
		NativeQuery<BookPOJO> query = session.createNativeQuery("Update Book set BOOK_PRICE = :price where PUB_ID = :pubId",
				BookPOJO.class);
		Double price[] = { 42.0, 10.0, 75.0, 89.0, 99.0 };
		int pubIds[] = { 1, 2, 3, 4, 5 };
		int update = 0;
		for (int i = 0; i < pubIds.length; i++) {
			query.setParameter("price", price[i]);
			query.setParameter("pubId", pubIds[i]);
			session.beginTransaction();
			update += query.executeUpdate();
			session.getTransaction().commit();
		}
		System.out.println("No. of rows affected: " + update);
		session.close();
	}

	private static void showBooks() {
		Session session = HQLUtil.getSessionFactory().openSession();
		NativeQuery<BookPOJO> query = session.createNativeQuery("SELECT * FROM Book", BookPOJO.class);
		List<BookPOJO> books = query.getResultList();
		if (books.size() == 0) {
			System.out.println("No Books available.");
		} else {
			for (BookPOJO book : books) {
				System.out.println(book.getBookId() + " | " + book.getBookName() + " | " + book.getPrice() + " | "
						+ book.getPublisher().getPubName());
			}
		}
		session.close();
	}
}
