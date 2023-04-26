package com.rubypaper.persistence.hibernate;

import com.sun.xml.bind.v2.TODO;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeDAO {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	public EmployeeDAO() {
		sessionFactory = new Configuration().configure("com/rubypaper/persistence/hibernate/hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.getTransaction();
	}

	public void insertEmployee(EmployeeVO vo) {
		System.out.println("===> Hibernate 기반으로 직원 등록 기능 처리");
		try {
			transaction.begin();
			session.persist(vo);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}


	//TODO - h-1. 세번쨰. hibernate
	//쿼리는 jpql + POJO, 트랜젝션 처리는 위에 insertEmployee참조, db 설정은 .cfg.xml 참조
	public List<EmployeeVO> getEmployeeList() {
		System.out.println("===> Hibernate 기반으로 직원 목록 조회 기능 처리");
		String jpql = "select e from EmployeeVO e order by e.id";
		List<EmployeeVO> employeeList = session.createQuery(jpql).getResultList();
		return employeeList;
	}
}
