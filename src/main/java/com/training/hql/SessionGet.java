package com.training.hql;

import org.hibernate.Session;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

public class SessionGet {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();
		try {
			int id = 9;
			BookPOJO book = (BookPOJO) session.get(BookPOJO.class, id);
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

