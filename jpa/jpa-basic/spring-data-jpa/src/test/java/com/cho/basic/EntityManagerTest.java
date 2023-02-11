package com.cho.basic;

import com.cho.basic.vo.연관관계매핑.Member;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootTest
public class EntityManagerTest {

    //spring-legacy + pom.xml에서 persistence.xml에 "hello"라고 등록해 줘야 동작. springboot + gradle에서는 등록 가능은한데 복잡함
    //https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-access.use-traditional-persistence-xml
    @Disabled
    @Test
    public void EntityManagerBasic(){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUserId("user-id");

            em.persist(member);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        entityManagerFactory.close();
    }

}
