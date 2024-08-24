package com.training.hql;

import org.hibernate.Session;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class SessionLoad {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();
		try {
			int id = 5;

//			BookPOJO book = (BookPOJO) session.load(BookPOJO.class, id);
			BookPOJO book = session.byId(BookPOJO.class).getReference(id);

			if (book != null) {
				System.out.println(book.getBookId() + " | " + book.getBookName());
			} else {
				System.out.println("No book available with ID: " + id);
			}
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		} finally {
			session.close();
			System.exit(0);
		}
	}
}
