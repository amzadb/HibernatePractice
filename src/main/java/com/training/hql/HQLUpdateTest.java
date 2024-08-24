package com.training.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.training.PublisherPOJO;
import com.training.util.HQLUtil;

public class HQLUpdateTest {
	public static void main(String[] args) {
		System.out.println("Publishers List BEFORE Update:");
		System.out.println("-----------------------------------------");
		showPublishers();

		updatePublisher();

		System.out.println("--------------***************------------");
		System.out.println("Publishers List AFTER Update:");
		System.out.println("-----------------------------------------");
		showPublishers();
		System.exit(0);
	}
	
	private static void updatePublisher() {
		Session session = HQLUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("Update PublisherPOJO set pubName = :pubName where pubId = :pubId");
		NativeQuery<PublisherPOJO> query = session.createNativeQuery("Update Publisher set PUB_NAME = :pubName where PUB_ID = :pubId",
				PublisherPOJO.class);
		query.setParameter("pubName", "McGraw-Hill Education");
		query.setParameter("pubId", 4);
		Transaction transaction = session.beginTransaction();
		int i = query.executeUpdate();
		transaction.commit();
		System.out.println("No. of rows affected: " + i);
		session.close();
	}

	private static void showPublishers() {
		Session session = HQLUtil.getSessionFactory().openSession();
//		Query query = session.createQuery("from PublisherPOJO");
		NativeQuery<PublisherPOJO> query = session.createNativeQuery("SELECT * FROM Publisher", PublisherPOJO.class);
//		List<PublisherPOJO> pubs = query.list();
		List<PublisherPOJO> pubs = query.getResultList();
		for (PublisherPOJO pub : pubs) {
			System.out.println(pub.getPubId() + " | " + pub.getPubName());
		}
		session.close();
	}
}
