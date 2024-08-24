package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.training.util.HQLUtil;

public class HQLGroupByTest {

	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();

		String queryString = "SELECT B.publisher.pubName, COUNT(B.bookId) FROM BookPOJO B GROUP BY B.publisher.pubName";

//		Query query = session.createQuery(queryString);
		Query<Object[]> query = session.createQuery(queryString, Object[].class);

//		List<?> books = query.getResultList();
//
//		Iterator<?> iterator = books.iterator();
//		while (iterator.hasNext()) {
//			Object[] row = (Object[]) iterator.next();
//			System.out.print("Publisher: " + row[0]);
//			System.out.println(" | Number of Books: " + row[1]);
//		}
		
		List<Object[]> books = query.getResultList();
		for(Object[] row : books) {
			System.out.print("Publisher: " + row[0]);
			System.out.println(" | Number of Books: " + row[1]);
		}
		
		session.close();
		System.exit(0);
	}
}
