package com.training.hql;

import java.util.Iterator;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import com.training.util.HQLUtil;

public class NamedQueryTest {
	public static void main(String[] args) {
		Session session = HQLUtil.getSessionFactory().openSession();
		Query query = session.getNamedQuery("query2");
		query.setParameter("pubId", 5);

		List list = query.list();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Object[] obj = (Object[]) iterator.next();
			for (int i = 0; i < obj.length; i++) {
				System.out.print(obj[i] + " | ");
			}
			System.out.println();
		}
		session.close();
		System.exit(0);
	}
}
