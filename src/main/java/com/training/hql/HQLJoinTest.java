package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class HQLJoinTest {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();

//		String queryString = "Select B.book_Id, B.book_Name, P.pub_Id, P.pub_Name from Book B, Publisher P where B.pub_Id = P.Pub_Id";
		String queryString = "SELECT b FROM BookPOJO b JOIN b.publisher p";

//		NativeQuery query = session.createNativeQuery(queryString);
		Query<BookPOJO> query = session.createQuery(queryString, BookPOJO.class);

//				List<?> list = query.getResultList();
//				Iterator<?> iterator = list.iterator();
//
//				while (iterator.hasNext()) {
//					Object[] obj = (Object[]) iterator.next();
//					for (int i = 0; i < obj.length; i++) {
//						System.out.print(obj[i] + " | ");
//					}
//					System.out.println();
//				}

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
