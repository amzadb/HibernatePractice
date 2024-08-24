package com.training.hql;

import java.util.List;

import org.hibernate.Session;

import com.training.BookPOJO;
import com.training.util.HQLUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CriteriaTest1 {
    public static void main(String[] args) {
        Session session = HQLUtil.getSessionFactory().openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BookPOJO> criteriaQuery = criteriaBuilder.createQuery(BookPOJO.class);

        // Define the root entity
        Root<BookPOJO> bookRoot = criteriaQuery.from(BookPOJO.class);

        // Select all fields (optional, you can add specific selections)
        criteriaQuery.select(bookRoot);

        List<BookPOJO> books = session.createQuery(criteriaQuery).getResultList();

        for (BookPOJO book : books) {
            System.out.print(book.getBookId());
            System.out.print(" | " + book.getBookName());
            System.out.print(" | " + book.getPublisher().getPubName());
            System.out.println(" | " + book.getPrice());
        }
        session.close();
        System.exit(0);
    }
}
