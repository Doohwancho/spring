package org.example.jpashop.repository.jpql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.jpashop.controller.OrderController.OrderSearch;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    
    private final EntityManager em;
    
    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }
    
    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }
    
    public List<Order> findAllByString(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m"; //jpql에서는 조인을 이런식으로 하는구나. 이미 Member:Order = 1:N 양방향 관계인데, 이미 join맺어진 걸 가르키는 구나.
        
        boolean isFirstCondition = true;
        
        if (orderSearch.getOrderStatus() != null) {
            jpql += " where o.status = :status"; //아 조건에 부합하지 않으면, 이런식으로 jpql을 붙이네?
        }
        
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            
            jpql += " m.name like :name";
        }
        
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
            .setMaxResults(1000);
        
        if (orderSearch.getOrderStatus() != null) {
            query.setParameter("status", orderSearch.getOrderStatus());
        }
        
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query.setParameter("name", orderSearch.getMemberName());
        }
        
        return query.getResultList();
    }
    
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER);
        
        List<Predicate> criteria = new ArrayList<>();
        
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }
        
        if (orderSearch.getMemberName() != null) {
            Predicate name = cb.like(m.<String>get("name"),
                "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        
        return query.getResultList();
    }
}
